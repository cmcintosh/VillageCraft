# AI Villager Goals Implementation

## Overview
This implementation adds complete AI behavior for VillageCraft villagers using Minecraft's Goal system with a state machine architecture.

## Files Created/Modified

### Modified Files:
1. **VillagerGoalBase.java** - Updated with state machine and behavior selection
2. **VillagerCraftBaseProfession.java** - Updated to register new goals

### New Files:
1. **VillagerGoalWork.java** - Profession-based work behavior
2. **VillagerGoalEat.java** - Hunger-driven eating behavior
3. **VillagerGoalSleep.java** - Day/night sleep cycle
4. **VillagerGoalTests.java** - Unit tests

## State Machine

The villager AI uses a four-state machine:
- **IDLE** - Default state, no active behavior
- **WORKING** - Profession-specific activities during day
- **EATING** - Consuming food to restore hunger
- **SLEEPING** - Resting during night hours

### State Priorities:
1. **Critical Hunger** (> priority sleep/work) - Starves if not addressed
2. **Hunger** (> priority work, < sleep at night)
3. **Night Time** - Sleep priority
4. **Day Time** - Work priority
5. **IDLE** - Default fallback

## Work Schedule

```
Day:   6000-18000 ticks  (6am - 6pm)  → WORK
Night: 18000-6000 ticks  (6pm - 6am)  → SLEEP
```

## Hunger System

- **Hunger Threshold (30%)**: 12/40 points triggers eating
- **Critical Threshold (10%)**: 4/40 points - will steal from others
- **Max Hunger**: 40 points

## Profession Work Behaviors

Each profession has specific work:
- **FARMER**: Harvests crops, replants seeds
- **FISHERMAN**: Simulates fishing at job site
- **SHEPHERD**: Shears sheep (placeholder)
- **FLETCHER**: Works at fletching table
- **LIBRARIAN**: Studies at lectern
- **CLERIC**: Works at brewing stand
- **SMITHS**: Works at grindstone/smithing table/blast furnace
- **BUTCHER**: Works at smoker
- **LEATHERWORKER**: Works at cauldron
- **MASON**: Works at stonecutter
- **Others**: Generic work at job site

## Goal Registration

Goals are registered with priorities:
1. Eat (priority 2) - Critical for survival
2. Work (priority 3) - Economic activity
3. Sleep (priority 4) - Rest cycle
4. Base (priority 5) - State management

## Testing

The test suite includes:
- `testVillagerEatsWhenHungry` - Hunger triggers eating
- `testVillagerWorksDuringDay` - Work schedule 6am-6pm
- `testVillagerFullWorkCycle` - Complete day/night cycle
- State machine verification
- Day/night detection tests
- Profession filtering tests

## Integration

The goals integrate with:
- Minecraft Forge's Goal/GoalSelector system
- VillageCraft's Capability system (hunger/honor)
- Minecraft Brain/Memory system (job sites, home)
- Point of Interest (POI) system for beds and job sites

## Known Limitations

1. Requires proper setup of job sites via POI system
2. Sleeping animation uses vanilla setSleeping (may need custom animation)
3. Crop harvesting is simplified (could be improved with mod-specific crops)
4. Some profession behaviors are placeholders for future expansion

## Future Enhancements

- Add custom work animations
- Improve pathfinding for complex environments
- Add more sophisticated profession chains
- Implement "tiredness" for extended work periods
- Add social behaviors during breaks
