# VillageCraft Profession Progression

## Overview
VillageCraft uses a multi-track progression system where villagers can follow different career paths. Each track starts from an unemployed villager receiving a crafted profession item.

---

## Leadership Track

### How to Start
Give an **unemployed villager** a **Manager Notebook** to create a Manager.

### Progression Chain
```
Manager (Level 1) → Outpost Liaison (Level 3 + Suppliers Manual) → Mayor (Level 3 Promotion Item)
```

| Profession | Workstation | Unlocks | Created By |
|------------|-------------|---------|------------|
| **Manager** | Village Manager | Auto-purchase profession tokens<br>View villager happiness<br>Set working schedules | Manager Notebook (given to unemployed villager) |
| **Outpost Liaison** | Village Job Board | Create job orders<br>Assign tasks to skilled villagers<br>Process supply requisitions | Level 3 Manager + Suppliers Manual |
| **Mayor** | Town Hall | Village leadership<br>Policy decisions<br>Tax collection | Level 3 Outpost Liaison + [Promotion Item] |

---

## Economic Track

### How to Start
Give an **unemployed villager** a **Merchant Ledger** to create a Merchant.

### Progression Chain
```
Merchant (Level 1) → Trader (Level 3 + Gold Ingot) → [Advanced Economic Roles]
```

| Profession | Workstation | Unlocks | Created By |
|------------|-------------|---------|------------|
| **Merchant** | Auction House | Basic buy/sell<br>Entry-level trades | Merchant Ledger |
| **Trader** | Auction House | Better prices<br>Auction access<br>Advanced goods | Level 3 Merchant + Gold Ingot |
| **Caravaneer** | Caravan Station | Inter-village trade routes<br>Llama taming | [Promotion Item] |
| **Diplomat** | Embassy | Inter-village relations<br>War declaration<br>Tribute management | Level 3 Caravaneer + Diamond Block |

---

## Worker Profession

### Overview
**NOT related to Manager track.** Workers are standalone villagers that perform basic labor tasks.

### How to Start
Give an **unemployed villager** a **Hammer** (or similar tool) to create a Worker.

### Worker Abilities
- ✅ Harvest resources (wood, stone, crops)
- ✅ Haul items (move goods between storage)
- ✅ Fill supply orders
- ✅ Flee enemies when alert signaled
- ✅ Retreat to Town Hall during danger

### Important Note
⚠️ **Keep 2 villagers as Workers** — If promoted to certain jobs, villagers may lose the ability to gather or haul. Workers are essential for basic village operations.

### Promotion Path
Workers can be promoted to specialized professions using profession tokens:
- Worker + Miner Token → Miner
- Worker + Builder Token → Builder
- Worker + Farmer Token → Farmer
- etc.

---

## Professional Professions (Token-Based)

These professions require a **Profession Token** in the villager's inventory before they can claim a workstation.

### Available Professions
| Profession | Workstation | Token Item | Role |
|------------|-------------|------------|------|
| **Alchemist** | Alchemist Table | Alchemist Token | Brewing, potions |
| **Architect** | Drafting Table | Architect Token | Building design |
| **Bard** | Bard Stand | Bard Token | Musical entertainment |
| **Beekeeper** | Beekeeper's Hive | Beekeeper Token | Honey production |
| **Brawler** | Brawler Box | Brawler Token | Village defense |
| **Builder** | Builder's Chest | Builder Token | Construction |
| **Farmer** | Farm Plots | Farmer Token | Crop production |
| **Fisherman** | [Water source] | Fisherman Token | Fishing |
| **Innkeeper** | Inn | Innkeeper Token | Hospitality |
| **Landlord** | Title Office | Landlord Token | Housing management |
| **Miner** | Ore Box | Miner Token | Resource gathering |
| **Musician** (Singer/Drummer/Bassist) | Bard Stand | [Musical Token] | Ensemble performances |
| **Potter** | Potter's Wheel | Potter Token | Ceramic goods |
| **Pyrotechnic** | Pyrotechnic Table | Pyrotechnic Token | Fireworks |

---

## Token Acquisition

### Primary Source: Trader
The **Trader** sells profession tokens for emeralds:

**Tier 1 (Novice):**
- Worker Token (8 emeralds)
- Trader Token (10 emeralds)
- Beekeeper Token (6 emeralds)

**Tier 2 (Apprentice):**
- Miner Token (12 emeralds)
- Caravaneer Token (14 emeralds)
- Fisherman Token (10 emeralds)

**Tier 3+ (Higher Tiers):**
- Innkeeper, Mayor, Landlord tokens
- Manager, Pyrotechnic, Alchemist tokens
- Bundles, enchanted books

### Alternative Sources
- Found in chests
- Crafted using specific recipes
- Rewards from quests

---

## Summary Table

| Track | Start Item | Progression | End Role |
|-------|------------|-------------|----------|
| **Leadership** | Manager Notebook | Manager → Outpost Liaison → Mayor | Village governance |
| **Economic** | Merchant Ledger | Merchant → Trader → [Caravaneer/Diplomat] | Trade/Commerce |
| **Worker** | Hammer | Worker (can promote to specialists) | General labor |
| **Specialist** | Profession Token | [Various by profession] | Specialized roles |

---

## Implementation Notes

- Each profession requires its specific workstation POI
- Profession tokens must be in villager's inventory before claiming workstation
- Promotion requires both level requirement AND specific promotion item
- Workers are the fallback labor force for all villages
- Musical professions (Bard/Singer/Drummer/Bassist) share BARD_POI workstation