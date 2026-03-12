# Economic Progression Chain

## Overview
The economic tree of VillageCraft follows a progression system where villagers can be promoted through the ranks by meeting specific conditions.

## Progression Path

```
Merchant (Level 3) + Manager Notebook → Manager
Manager (Level 3) + Suppliers Manual → Outpost Liaison

Caravaneer (Level 3) + Diamond Block → Diplomat
```

## Profession Details

### Tier 1: Merchant
**Role**: Entry-level economic profession
**Unlocks**: Basic resource trading
**Workstation**: Auction House
**Trades**: Sells basic resources (logs, sand, arrows) at beginner prices
**Promotion**: Level 3 Merchant + Manager Notebook → Trader

### Tier 2: Trader
**Role**: Intermediate economic profession
**Unlocks**: Auction House block for buy/sell on in-game exchange
**Workstation**: Auction House
**Trades**: Better prices than Merchant, sells mid-tier goods
**Promotion**: Level 3 Trader + Block of Emeralds (placeholder) → Manager

### Tier 3: Manager
**Role**: Administrative profession
**Unlocks**: Village Manager block
**Features**:
- Auto-purchase profession tokens with village emeralds
- View individual villager happiness levels
- Set working schedules for villagers
**Workstation**: Village Manager
**Trades**: Documentation, books, organization tools
**Promotion**: Level 3 Manager + Suppliers Manual → Outpost Liaison

### Tier 4: Outpost Liaison
**Role**: Logistics coordinator
**Unlocks**: Village Job Board block
**Features**:
- Create job orders for villagers
- Assign tasks to skilled villagers
- Process orders before villager assignment
**Workstation**: Village Manager (shares with Manager)
**Trades**: Signs, maps, compasses, coordination tools

### Caravaneer → Diplomat Path

#### Caravaneer
**Role**: Trade route specialist
**Unlocks**: Caravan Station block
**Features**:
- Create inter-village trade routes
- Specialize villages instead of requiring all professions
- Tame and maintain llamas
**Workstation**: Caravan Stop
**Trades**: Leads, llamas, transport goods
**Promotion**: Level 3 Caravaneer + Diamond Block → Diplomat

#### Diplomat
**Role**: Political representative
**Unlocks**: Embassy block
**Features**:
- Define relations with other villages and players
- Declare wars to attempt takeover
- Manage diplomatic gifts and tribute
**Workstation**: Embassy
**Trades**: Rare items, diplomatic gifts, books

## Implementation Status

| Profession | Registered | POI | Trades | Status |
|------------|------------|-----|--------|--------|
| Merchant | ✅ | ✅ | ✅ | Complete |
| Trader | ✅ | ✅ | ✅ | Complete |
| Manager | ✅ | ✅ | ✅ | Complete |
| Outpost Liaison | ✅ | ✅ | ✅ | Complete |
| Caravaneer | ✅ | ✅ | ✅ | Complete |
| Diplomat | ✅ | ✅ | ✅ | Complete |

## Notes
- Promotion items (Manager Notebook, Suppliers Manual) need implementation
- Promotion logic handled separately from profession trades
- All professions use NeoForge 1.20.2 event-based trade registration
- Each profession has 5 trade tiers following vanilla villager patterns
