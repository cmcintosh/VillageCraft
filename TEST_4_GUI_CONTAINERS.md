# Test 4: GUI/Containers

**Date:** 2026-03-11  
**Branch:** `feature/neoforge-1.20.1-port`  
**Minecraft Version:** 1.20.2  
**NeoForge Version:** 20.2.88  
**Test Status:** CODE REVIEWED

---

## Overview

Testing the GUI and Container system for VillageCraft blocks, particularly the VillageCenter block's user interface and inventory management.

---

## Container Architecture

### Class Diagram

```
AbstractContainerMenu (Minecraft)
└── VillageCenterContainer (VillageCraft)
    └── BasicVillageCraftContainer (partial)

AbstractContainerScreen (Minecraft)
└── VillageCenterScreen (VillageCraft)
    └── BaseVillageCraftScreen (partial)
```

---

## VillageCenterContainer Analysis

**Location:** `src/main/java/com/villagecraft/container/VillageCenterContainer.java`

**Status:** ⚠️ Skeleton - Needs 1.20.2 Implementation

### Current Implementation

```java
public class VillageCenterContainer extends AbstractContainerMenu {
    
    public VillageCenterContainer(int id, Inventory inv, final BlockEntity tile) {
        super(null, id);  // ⚠️ MenuType is null!
    }
    
    @Override
    public boolean stillValid(Player player) {
        return true;  // ⚠️ No distance check
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;  // ⚠️ Not implemented
    }
}
```

### Required Implementation Checklist

| Feature | Status | Notes |
|---------|--------|-------|
| MenuType Registration | ⚠️ Missing | `super(null, id)` - needs MenuType |
| Slot Initialization | ⬜ Not Done | No slots added |
| Player Inventory | ⬜ Not Done | `inv` parameter unused |
| Tile Entity Binding | ⚠️ Weak | Stored but not used |
| stillValid() | ⚠️ Basic | No range check |
| quickMoveStack() | ⬜ Not Done | Returns EMPTY directly |
| Container Sync | ⬜ Not Done | No data synchronization |

### Fixed Implementation (Recommended)

**Reference Code for 1.20.2:**
```java
public class VillageCenterContainer extends AbstractContainerMenu {
    private final BlockEntity tileEntity;
    
    // Needs MenuType registration in ModContainer
    public VillageCenterContainer(MenuType<?> type, int id, Inventory inv, 
            BlockEntity tile, ContainerData data) {
        super(type, id);
        this.tileEntity = tile;
        
        // Add tile entity slots (0-8)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlot(new Slot(/* tile inventory */, j + i * 3, ...));
            }
        }
        
        // Add player inventory slots (9-35)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, ...));
            }
        }
        
        // Add player hotbar slots (36-44)
        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(inv, k, ...));
        }
    }
    
    @Override
    public boolean stillValid(Player player) {
        // Check if player is within range of block
        return ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos())
            .evaluate((level, pos) -> level.getBlockEntity(pos) == tileEntity && 
                      player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0)
            .orElse(false);
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // Implement shift-click behavior
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            // Logic for moving between tile and player inventory
        }
        return ItemStack.EMPTY;
    }
}
```

---

## VillageCenterScreen Analysis

**Location:** `src/main/java/com/villagecraft/gui/VillageCenterScreen.java`

**Status:** ⚠️ Skeleton - Needs 1.20.2 Rendering

### Current Implementation

```java
@OnlyIn(Dist.CLIENT)
public class VillageCenterScreen extends AbstractContainerScreen<VillageCenterContainer> {
    
    public VillageCenterScreen(VillageCenterContainer container, Inventory inv, 
            Component title) {
        super(container, inv, Component.translatable("Village Info"));
    }
    
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // TODO: Reimplement rendering for 1.20.2
        // New GuiGraphics API replaces PoseStack
    }
}
```

### 1.20.2 Rendering Changes

**Old API (pre-1.20):**
```java
@Override
protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
    RenderSystem.setShaderTexture(0, GUI_TEXTURE);
    blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
}
```

**New API (1.20.2):**
```java
@Override
protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
    // GuiGraphics wraps PoseStack + MultiBufferSource
    graphics.blit(GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, 
                  this.imageWidth, this.imageHeight);
}
```

### Required Assets

| Asset | Type | Status |
|-------|------|--------|
| GUI Background Texture | PNG | ⬜ Needed |
| Slot Sprite | PNG | ⬜ Vanilla can be used |
| Title Font | Built-in | ✅ Available |
| Container Title | Lang file | ⬜ Needs `"container.villagecraft.village_center"` |

---

## ModContainer Registration

**Location:** `src/main/java/com/villagecraft/init/ModContainer.java`

**Status:** Needs Verification

### Expected Registration Pattern (1.20.2)

```java
public class ModContainer {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = 
        DeferredRegister.create(Registries.MENU, Reference.MODID);
    
    public static final DeferredHolder<MenuType<?>, MenuType<VillageCenterContainer>> VILLAGE_CENTER_CONTAINER = 
        CONTAINERS.register("village_center", 
            () -> new MenuType<>(VillageCenterContainer::new, FeatureFlags.DEFAULT_SET));
}
```

---

## Test Checklist

### ✅ Build Verification
- [x] Container classes compile
- [x] Screen classes compile
- [x] No compilation errors

### ⬜ Container Functionality
- [ ] Menu type properly registered
- [ ] Slots initialized correctly
- [ ] Item transfer works
- [ ] Shift-click handling works
- [ ] Container syncs between client/server

### ⬜ Screen Rendering
- [ ] GUI texture displays
- [ ] Background image renders
- [ ] Slots render correctly
- [ ] Item tooltips show
- [ ] Container title displays

### ⬜ Interaction
- [ ] Right-click opens GUI
- [ ] Items can be added
- [ ] Items can be removed
- [ ] GUI closes on block break
- [ ] GUI closes when player moves away

### ⬜ Edge Cases
- [ ] Works with hoppers
- [ ] Works with pipes (if applicable)
- [ ] Handles full inventory
- [ ] Handles empty container

---

## 1.20.2 Migration Requirements

### Critical Changes

1. **GuiGraphics Parameter**
   - All render methods use `GuiGraphics` instead of `PoseStack`
   - Simplifies drawing operations

2. **MenuType Registration**
   - Must register with `FeatureFlags.DEFAULT_SET` or game version features

3. **AbstractContainerMenu Constructor**
   - Must pass valid `MenuType` or game crashes
   - Use `super(MenuType, id)` not `super(null, id)`

### Code Conversion Examples

**Texture Binding:**
```java
// Old
RenderSystem.setShaderTexture(0, texture);
blit(poseStack, x, y, u, v, width, height);

// New (1.20.2)
graphics.blit(texture, x, y, u, v, width, height);
```

**Text Rendering:**
```java
// Old
font.draw(poseStack, text, x, y, color);

// New (1.20.2)
graphics.drawString(font, text, x, y, color, shadow);
```

---

## Test Results Summary

| Component | Status | Score |
|-----------|--------|-------|
| Container Base | ⚠️ Skeleton | 3/10 |
| Screen Base | ⚠️ Skeleton | 2/10 |
| MenuType Registration | ⬜ Unknown | 0/10 |
| Slot Implementation | ⬜ None | 0/10 |
| Rendering | ⬜ Not Implemented | 0/10 |

**Overall: Needs Full Implementation**

---

## Implementation Priority

### Phase 1: Container (Critical)
1. Fix VillageCenterContainer constructor
2. Add MenuType registration to ModContainer
3. Implement slot initialization
4. Implement quickMoveStack

### Phase 2: Screen (High Priority)
5. Create texture assets
6. Implement renderBg
7. Add title rendering
8. Add slot highlighting

### Phase 3: Integration (Medium)
9. Link TileEntity to Container
10. Implement data sync
11. Add container listener
12. Test with items

### Phase 4: Polish (Low)
13. Add custom tooltips
14. Add button interactions
15. Optimize rendering
16. Add sound effects

---

## Next Test

**Test 5:** Trading

---

*Last Updated: 2026-03-11 22:15 CDT*  
*Tested by: OpenClaw Subagent*
