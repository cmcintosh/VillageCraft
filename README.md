# VillageCraft

A Minecraft mod for NeoForge 1.20.2 that adds an enhanced villager simulation system with professions, needs management, and village community features.

## Features

### 10 Custom Villager Professions
Each profession has unique trades across 5 tiers:

- **Worker** - Basic laborer tier, foundation of village economy
- **Trader** - Variety goods and rare items (Totem of Undying)
- **Farmer** - Crops, seeds, and food (Wheat, Carrots, Golden Apples)
- **Builder** - Building materials and construction items
- **Architect** - Decorative blocks and design items
- **Alchemist** - Potions and magical items
- **Miner** - Ores, tools, and rare finds (Diamonds, Netherite)
- **Mayor** - Leadership trades and village management
- **Innkeeper** - Food and hospitality
- **Landlord** - Housing-related trades
- **Bard** - Musical instruments and entertainment (songs with lyrics!)

### Villager Needs System

Villagers now have realistic needs that affect gameplay:

- **Hunger** - Activity-based decay (Idle < Walking < Working < Combat)
  - Starvation causes damage over time
  - All food items restore hunger
  - Starvation particles (black smoke) indicate hungry villagers
  
- **Thirst** - Tracks hydration needs

- **Honor** - Reputation within village

### Enhanced AI Goals

7 new AI behaviors for villagers:

1. **VillagerGoalBase** - Core behavior and nearby entity tracking
2. **VillagerHungerGoal** - Manages hunger and seeks food
3. **HealGolemGoal** - Clerics heal damaged Iron Golems
4. **VillagerGoalLocateBlock** - Search and navigate to blocks
5. **VillagerGoalGoToBlock** - Range-based navigation with stay behavior
6. **VillagerGoalGotoVillageCenter** - Socialization at village centers
7. **VillagerGoalDeliverToStorage** - Item delivery to chests
8. **WanderBardPerformGoal** - Musical performances with buffs

### Bard Entertainment System

Bards perform songs that provide benefits:

**4 Unique Songs:**
- "The Village Life" - Guitar, community celebration
- "Morning Cheer" - Harp, uplifting melody
- "Traveler's Rest" - Flute, welcoming visitors
- "The Golem's Watch" - Chimes, honoring guardians

**Performance Features:**
- Lyrics broadcast to nearby players via chat
- Health buffs: 1-2 hearts for villagers, 0.5-1 for players every 2s
- Mood buffs: Inspirational messages and slight saturation
- Visual effects: Green/gold particles, hearts orbiting bard
- All employed villagers can perform (Priority 4 goal)

### Village Infrastructure

- **VillageCenter** - Central hub block for village management
- **Block Scanner** - Detects and tracks village structure blocks
- **Capablity System** - Hunger, thirst, honor storage (NeoForge 1.20.2 API)
- **Data Storage** - World-saving village data (VillageCraftData)

### Iron Golem Enhancements

- Enhanced protection logic
- Cleric healing interaction
- Village tracking
- Persistence requirements

## Technical Information

### NeoForge 1.20.2 API

This mod has been fully updated for NeoForge 1.20.2:

- Updated registries (DeferredRegister, DeferredHolder)
- NeoForge events (VillagerTradesEvent, EntityJoinLevelEvent)
- MenuType registration with IMenuTypeExtension
- Capability system without LazyOptional
- Proper event bus subscriptions

### Build Status

- **Status**: BUILD SUCCESSFUL
- **JAR Size**: ~12MB
- **Java Version**: 17+
- **Minecraft Version**: 1.20.2

```bash
./gradlew build
```

### Installation

1. Install NeoForge 1.20.2
2. Place `villagecraft-1.0.0.jar` in mods folder
3. Launch Minecraft

## Development

### Project Structure

```
src/main/java/com/villagecraft/
├── block/          # Custom blocks (VillageCenter)
├── container/      # GUI containers (VillageCenterContainer)
├── capabilities/   # Needs system (Hunger, Thirst, Honor)
├── data/           # World storage (VillageCraftData)
├── entity/
│   ├── goal/       # AI goals (8 custom goals)
│   └── professions/# Profession trades (10 professions)
├── init/           # Registration (ModBlocks, ModProfessions)
├── screen/         # GUI screens
├── util/           # Utilities (BlockScanner)
└── VillageCraft.java # Main mod class
```

### Adding New Professions

1. Create class in `entity/professions/`
2. Extend `VillagerProfessionBase` or implement trade registration
3. Register in `ModVillagerProfessions`
4. Add trades via `@SubscribeEvent` on `VillagerTradesEvent`

## Credits

Developed by L0stxRising for VillageCraft.

## License

This project is licensed under the MIT License.
