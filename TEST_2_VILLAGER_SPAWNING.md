# Test 2: Villager Spawning

**Date:** 2026-03-11  
**Branch:** `feature/neoforge-1.20.1-port`  
**Minecraft Version:** 1.20.2  
**NeoForge Version:** 20.2.88  
**Test Status:** CODE REVIEWED

---

## Overview

Testing the custom villager entity spawning system for VillageCraft, including entity registration, custom data handling, and spawn mechanics.

---

## Technical Specifications

### Entity: VillageCraftVillager

| Property | Value |
|----------|-------|
| **Class** | `VillageCraftVillager` |
| **Extends** | `net.minecraft.world.entity.npc.Villager` |
| **Data Class** | `VillageCraftVillagerData` |
| **Registration** | `ModEntity.ENTITY_TYPES` |

### Code Location
- **Entity Class:** `src/main/java/com/villagecraft/entity/VillageCraftVillager.java`
- **Entity Registration:** `src/main/java/com/villagecraft/init/ModEntity.java`
- **Data Class:** `src/main/java/com/villagecraft/data/VillageCraftVillagerData.java`

---

## Entity Registration Status

### ModEntity.java ✅

```java
public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
    DeferredRegister.create(Registries.ENTITY_TYPE, Reference.MODID);

public static final DeferredHolder<EntityType<?>, EntityType<IronGolem>> GOLEM = 
    ENTITY_TYPES.register("iron_golem", ...);
```

| Component | Status | Notes |
|-----------|--------|-------|
| Deferred Register | ✅ PASS | Uses `Registries.ENTITY_TYPE` |
| IronGolem Entity | ✅ PASS | Fully configured |
| Custom Villager Registration | ⚠️ PARTIAL | Class exists, needs registration entry |

### EntityType Builder Pattern

**IronGolem (Verified Working):**
```java
EntityType.Builder.<IronGolem>of(IronGolem::new, MobCategory.MISC)
    .sized(1.4F, 2.7F)
    .clientTrackingRange(10)
    .build(new ResourceLocation(Reference.MODID, "iron_golem").toString())
```

---

## VillageCraftVillager Analysis

### Constructors

```java
// Constructor 1: Basic
public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, 
    VillagerType villagerType)

// Constructor 2: With Custom Data
public VillageCraftVillager(EntityType<? extends Villager> type, Level worldIn, 
    VillagerType villagerType, VillageCraftVillagerData data)
```

**Status:** ✅ Both constructors implemented

### Brain System (TODO)

```java
private void initBrain(Brain<Villager> villagerBrain) {
    // TODO: Reimplement initBrain for 1.20.2 - Brain API changed significantly
    VillagerProfession profession = this.getVillagerData().getProfession();
    // Simplified for now - full implementation needs new Activity/behavior packages 
}
```

**Status:** ⚠️ Method stubbed - Brain API significantly changed in 1.20.2

---

## Test Checklist

### ✅ Build Verification
- [x] Entity classes compile without errors
- [x] Entity registration class compiles
- [x] JAR contains entity classes

### ⬜ Entity Registration
- [ ] Custom villager registered in `ModEntity`
- [ ] Spawn egg registered (if applicable)
- [ ] Entity attributes registered (health, movement, etc.)

### ⬜ Spawning Mechanics
- [ ] Natural spawn rules defined
- [ ] Spawn placement conditions
- [ ] Spawn rate/frequency configured

### ⬜ Custom Data
- [ ] VillageCraftVillagerData persistence
- [ ] NBT save/load working
- [ ] Data synchronization (client/server)

### ⬜ Rendering
- [ ] Entity renders with correct model
- [ ] Texture loads properly
- [ ] Animations work (walking, idle)

---

## Brain API Migration Notes (1.20.2)

**Old API (pre-1.20):**
```java
// Previous used direct Brain initialization
brain.addActivity(Activity.CORE, ...)
```

**New 1.20.2 API:**
- Packages restructured to `net.minecraft.world.entity.ai.behavior`
- Activity registration pattern changed
- Villager tasks need reimplementation

**Required Changes:**
1. Update import statements
2. Reimplement `initBrain` with new Activity system
3. Register goals with Brain properly
4. Add schedules and memories if needed

---

## Comparison: VillageCraft vs Vanilla

| Feature | Vanilla Villager | VillageCraft Villager |
|---------|------------------|----------------------|
| Base Class | `Villager` | `Villager` ✅ |
| Custom Data | None | `VillageCraftVillagerData` ✅ |
| Brain AI | Full | Stubbed ⚠️ |
| Professions | Standard | Custom professions planned |
| Trading | Standard | Custom trades (via RandomTradeBuilder) ✅ |

---

## Integration Points

### Required Connections

1. **ModEntity.java** - Registration
   ```java
   // Needs to be added:
   public static final DeferredHolder<EntityType<?>, EntityType<VillageCraftVillager>> VILLAGER = 
       ENTITY_TYPES.register("village_craft_villager", ...)
   ```

2. **ModVillagerProfessions.java** - Professions
   ```java
   // Custom professions need to be linked
   ```

3. **Event Hooks** - Spawn events
   ```java
   // Entity spawn event subscription
   ```

---

## Test Results

| Test | Status | Notes |
|------|--------|-------|
| Entity Class Compilation | ✅ PASS | No errors |
| Data Class Compilation | ✅ PASS | `VillageCraftVillagerData` exists |
| Registration Compilation | ✅ PASS | `ModEntity` compiles |
| IronGolem Registration | ✅ PASS | Fully implemented |
| Custom Villager Registration | ⚠️ MISSING | Not in `ModEntity` |
| Brain Initialization | ⚠️ TODO | `initBrain` stubbed |
| In-Game Spawning | ⬜ NOT TESTED | Requires Minecraft |

---

## Issues Found

### Issue #1: Missing Custom Villager Registration
**Priority:** High
**Location:** `ModEntity.java`

The `VillageCraftVillager` class exists but is not registered in `ModEntity.ENTITY_TYPES`.

**Fix Required:**
```java
public static final DeferredHolder<EntityType<?>, EntityType<VillageCraftVillager>> VILLAGER = 
    ENTITY_TYPES.register("village_craft_villager",
        () -> EntityType.Builder.<VillageCraftVillager>of(
                (type, world) -> new VillageCraftVillager(type, world, VillagerType.PLAINS), 
                MobCategory.CREATURE)
            .sized(0.6F, 1.95F)
            .clientTrackingRange(10)
            .build(new ResourceLocation(Reference.MODID, "village_craft_villager").toString()));
```

### Issue #2: Brain API Not Migrated
**Priority:** High
**Location:** `VillageCraftVillager.java`

The `initBrain` method is stubbed with a TODO.

**Impact:** Villagers will not have any AI behaviors.

### Issue #3: No Spawn Egg Registration
**Priority:** Medium
**Location:** `ModItems.java` (presumed)

No spawn egg found for easy testing.

---

## Recommendations

### Immediate Actions
1. Add `VillageCraftVillager` registration to `ModEntity`
2. Create spawn egg item for testing
3. Implement basic Brain initialization

### Short Term
4. Implement custom spawn rules
5. Add villager attributes (health, speed)
6. Test NBT data persistence

### Long Term
7. Full profession system integration
8. Custom spawn conditions
9. Village generation integration

---

## Next Test

**Test 3:** Goals/AI (Eating, Working, Sleeping)

---

*Last Updated: 2026-03-11 21:55 CDT*  
*Tested by: OpenClaw Subagent*
