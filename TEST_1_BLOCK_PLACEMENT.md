# Test 1: VillageCenter Block Placement

**Date:** 2026-03-11  
**Branch:** `feature/neoforge-1.20.1-port`  
**Minecraft Version:** 1.20.2  
**NeoForge Version:** 20.2.88  
**Test Status:** MANUAL TEST REQUIRED

---

## Build Information

| Attribute | Value |
|-----------|-------|
| JAR Built | ✅ YES |
| JAR Location | `build/libs/VillageCraft-1.0.0.jar` |
| JAR Size | 12.7 MB |
| Java Version Used | OpenJDK 17.0.18 |
| Build Status | SUCCESSFUL |

---

## Target Block: VillageCenter

### Technical Specifications

| Property | Value |
|----------|-------|
| **Block ID** | `villagecraft:village_center` |
| **Item ID** | `villagecraft:village_center` |
| **Hardness** | 3.5F |
| **Sound Type** | WOOD |
| **No Occlusion** | true (allows light through) |
| **Hitbox** | Full block (0, 0, 0) to (16, 18, 16) - 1 block wide, 1.125 blocks tall |
| **Block Entity** | `TILE_VILLAGE_CENTER` (TileEntityVillageCenter) |
| **Facing Property** | Yes (HORIZONTAL_FACING) |
| **Rotation** | Returns player's facing direction opposite |

### Code Location
- **Block Class:** `src/main/java/com/villagecraft/block/BlockVillageCenter.java`
- **Item Class:** `src/main/java/com/villagecraft/item/blockitems/ItemVillageCenter.java`
- **Tile Entity:** `src/main/java/com/villagecraft/tile/TileEntityVillageCenter.java`

---

## Test Checklist

### ✅ Build Verification
- [x] Mod compiles without errors
- [x] JAR file generated in `build/libs/`
- [x] JAR contains expected classes

### ⬜ In-Game Block Placement Tests

#### Test 1.1: Basic Placement
- [ ] VillageCenter block can be crafted/obtained
- [ ] Block places on solid surfaces (grass, dirt, stone)
- [ ] Block places in all horizontal orientations
- [ ] Block faces player when placed (uses `getOpposite`)

#### Test 1.2: Hitbox Verification
- [ ] Block shows correct selection outline (16x18x16 pixels)
- [ ] Player cannot walk through block
- [ ] Block extends 2 pixels above 1-block height

#### Test 1.3: Rendering
- [ ] Block renders with correct model texture
- [ ] Block renders from all angles
- [ ] No texture missing (purple/black) issues
- [ ] Block appears correctly in inventory/hotbar

#### Test 1.4: Breaking & Drops
- [ ] Block breaks with appropriate tool (axe)
- [ ] Block drops itself when broken
- [ ] BlockEntity drops contents (if any)
- [ ] Drop rate is 100% (no fortune needed)

#### Test 1.5: Block Entity
- [ ] Right-click opens GUI (MenuProvider)
- [ ] Block entity persists data after break/place
- [ ] No crash on interaction

#### Test 1.6: Edge Cases
- [ ] Block can be placed underwater
- [ ] Block doesn't crash when placed near world height limit
- [ ] Block respects world protection (spawn protection, claims)
- [ ] Block works with hoppers/pipes (if applicable)

---

## How to Test

### Prerequisites
1. Minecraft Java Edition 1.20.2 installed
2. NeoForge 20.2.88 installed in launcher
3. VillageCraft JAR copied to `mods/` folder

### Test Steps

```
1. Launch Minecraft with NeoForge profile
2. Create new single-player world (Creative mode recommended)
3. Open inventory, search for "Village Center"
4. Place VillageCenter block on ground
5. Observe:
   - Model renders correctly
   - Hitbox is visible when targeted
   - Block faces player upon placement
6. Break block by hand or with tool
7. Collect dropped item
8. Verify item appears in inventory
```

### Commands for Testing

| Command | Purpose |
|---------|---------|
| `/give @p villagecraft:village_center 1` | Obtain the block item |
| `/setblock ~ ~ ~ villagecraft:village_center` | Place block via command |
| `/data get block ~ ~ ~` | Verify block entity data |

---

## Notes & Observations

### Code Review Findings

1. **Hitbox:** The block has an 18-pixel tall hitbox (1.125 blocks), extending slightly above standard 1-block height.
   ```java
   private static final VoxelShape AABB = Block.box(0, 0, 0, 16, 18, 16);
   ```

2. **Rotation:** Block faces opposite of player's horizontal facing direction:
   ```java
   return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
   ```

3. **Block Entity:** Associates with `ModTiles.TILE_VILLAGE_CENTER` - opens container GUI on right-click.

4. **Drops:** Uses `onRemove` to drop container contents when broken.

### Potential Issues to Watch
- Height extension (18px) may cause placement issues under low ceilings
- No direct `getShape` override - uses default `AABB` - verify collision works
- Block is `noOcclusion` - should let light pass through

---

## Test Results

| Test | Status | Notes |
|------|--------|-------|
| Build | ✅ PASS | JAR compiled successfully |
| In-Game Placement | ⬜ NOT TESTED | Requires Minecraft runtime |
| Hitbox | ⬜ NOT TESTED | Requires in-game verification |
| Rendering | ⬜ NOT TESTED | Requires in-game verification |
| Breaking/Drops | ⬜ NOT TESTED | Requires in-game verification |

### Overall Status: `NOT_TESTED` (Build Successful, Manual Testing Required)

---

## Next Test Recommendation

**Test 2: GUI Integration**  
Verify VillageCenter GUI opens correctly and container inventory works as expected.

---

*Generated by OpenClaw Subagent - villagecraft-test-1*  
*Session: agent:main:subagent:e243de7a-c79e-4121-8c8b-fae8be918bef*
