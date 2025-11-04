# Resonant Strike - Development Notes

## Week 1-2 Progress (COMPLETED)

### ✅ Core Systems Implemented
- Player controller with full stance system
- Wave propagation and collision system
- Enemy AI framework with decision-making
- Three enemy types: Fast Striker, Heavy Brute, Trickster
- Combat manager for game flow
- HUD system for player feedback

## Tuning Values & Balance

### Player Stats (Current)
- Max Health: 100
- Max Resonance: 100
- Movement Speed: 5.0
- Attack Cooldown: 0.3s

### Stance Multipliers
**Damage Output:**
- Rigid: 1.5x (aggressive)
- Counter: 0.8x (building resonance)
- Palm: 1.0x (balanced)

**Damage Taken:**
- Rigid: 1.5x (risky)
- Counter: 0.7x (defensive)
- Palm: 0.85x (safe)

### Enemy Stats
**Fast Striker:**
- Health: 60
- Damage: 12
- Attack Speed: 0.8s
- Parry: 15%
- Aggression: 1.5x

**Heavy Brute:**
- Health: 120
- Damage: 25
- Attack Speed: 2.5s
- Parry: 40%
- Aggression: 0.7x

**Trickster:**
- Health: 70
- Damage: 15
- Attack Speed: 1.2s
- Parry: 50%
- Aggression: 1.0x

### Wave Properties
- Speed: 12 units/sec
- Lifetime: 3 seconds
- Expand Rate: 2 units/sec
- Reflected Damage Bonus: 1.3x
- Redirect Damage Multiplier: 2.0x

## Week 3 TODO List

### High Priority
1. **Create Visual Assets**
   - [ ] Player 3D model (low-poly fighter silhouette)
   - [ ] Enemy models (distinct shapes per type)
   - [ ] Arena with wave-reactive floor shader
   - [ ] Particle effects for wave impacts

2. **Polish Wave System**
   - [ ] Add trail renderer for waves
   - [ ] Implement interference patterns (wave + wave)
   - [ ] Create ripple effect on surfaces
   - [ ] Add wave "charge" visual for charging attacks

3. **Audio Integration**
   - [ ] Create/source sound effects:
     * Stance switch (3 unique sounds)
     * Wave launch (per type)
     * Wave impact
     * Wave cancel/interference
     * Redirect/reflect
     * Player hit
     * Enemy hit
   - [ ] Background music (rhythmic, builds with combos)

4. **Animation System**
   - [ ] Player attack animations (4 directions)
   - [ ] Stance switch animations
   - [ ] Enemy attack animations
   - [ ] Hit reactions
   - [ ] Defeat animations

### Medium Priority
5. **UI Improvements**
   - [ ] Polish health/resonance bars
   - [ ] Add combo counter
   - [ ] Show active wave count
   - [ ] Tutorial overlay (first run)
   - [ ] Damage numbers

6. **Camera Work**
   - [ ] Subtle camera shake on impacts
   - [ ] Zoom in on redirects
   - [ ] Follow player movement smoothly

### Nice to Have
7. **Extra Polish**
   - [ ] Screen flash on critical hits
   - [ ] Slow-motion on successful parry
   - [ ] Post-processing effects
   - [ ] Victory poses

## Week 4 TODO List

### Balancing
- [ ] Playtesting sessions (get 5+ people)
- [ ] Adjust damage values based on feedback
- [ ] Tune enemy AI difficulty curve
- [ ] Balance resonance accumulation rate
- [ ] Fine-tune attack cooldowns

### Content
- [ ] Create mini-boss enemy type
- [ ] Design 10+ enemy wave patterns
- [ ] Add boss fight at wave 5, 10, 15
- [ ] Create multiple arena variations

### Menus & Screens
- [ ] Main menu
- [ ] Options menu (audio, graphics)
- [ ] Pause menu
- [ ] Game over screen with stats
- [ ] Victory screen
- [ ] Credits

### Final Polish
- [ ] Optimize performance
- [ ] Fix any bugs from playtesting
- [ ] Final audio mix
- [ ] Final visual polish pass
- [ ] Build for distribution

## Known Issues / Future Considerations

### Current Limitations
- Wave collision is sphere-based (simple but effective)
- Enemy AI is timer-based (could be more reactive)
- No special abilities yet (reserved D stance)
- Single arena only

### Possible Enhancements (Post-Jam)
- **Stance D (Reserved)**: Could be dodge/dash mechanic
- **Combo System**: Reward stance switching mid-combat
- **Ultimate Ability**: Spend full resonance for massive attack
- **Environmental Hazards**: Arena-specific mechanics
- **Multiplayer**: PvP mode with same mechanics
- **Roguelike Elements**: Random powerups, procedural waves
- **Training Mode**: Practice specific enemy patterns

## Design Philosophy

### Core Pillars
1. **Clarity**: Player should always understand what's happening
2. **Feedback**: Every action has visual and audio response
3. **Risk/Reward**: Aggressive play = higher damage but more danger
4. **Flow**: Combat should feel rhythmic and energetic
5. **Mastery**: Skill ceiling through timing and stance management

### Visual Language
- **Red**: Danger, aggression (Rigid stance, enemy attacks)
- **Blue**: Defense, absorption (Counter stance)
- **Green**: Safety, dispersion (Palm stance)
- **Orange**: Enemy energy
- **Purple/White**: Interference, cancellation

### Audio Language
- **Sharp**: Hits, strikes, direct damage
- **Deep**: Absorption, heavy impacts, bass on parries
- **Echo**: Redirects, bounces, reflections
- **Whoosh**: Wave travel, movement
- **Ding/Chime**: Resonance buildup, milestones

## Testing Checklist

### Core Mechanics
- [ ] All stances work correctly
- [ ] All attacks create appropriate waves
- [ ] Waves hit enemies accurately
- [ ] Waves hit player accurately
- [ ] Reflection works properly
- [ ] Absorption builds resonance
- [ ] Dispersion reduces damage
- [ ] Redirect consumes resonance
- [ ] Phase-inverted waves cancel others

### Combat Flow
- [ ] Enemies spawn correctly
- [ ] Enemy AI attacks regularly
- [ ] Enemy AI can parry
- [ ] Difficulty scales per wave
- [ ] Player death triggers game over
- [ ] Enemy death spawns next enemy
- [ ] Wave completion works

### UI
- [ ] Health bar updates
- [ ] Resonance bar updates
- [ ] Stance indicator changes color
- [ ] Wave counter increments
- [ ] Enemy health displays

### Feel
- [ ] Combat feels responsive
- [ ] Stance switching is smooth
- [ ] Wave travel looks good
- [ ] Hit feedback is satisfying
- [ ] Audio timing is tight

## Performance Targets
- **Target FPS**: 60
- **Max Active Waves**: 20 simultaneous
- **Enemy Count**: 1 active (by design)
- **Particle Budget**: TBD based on effects

## Art Style Reference
- Low-poly geometric fighters
- Clean silhouettes (easy to read in combat)
- Emissive materials for energy
- Minimalist arena (focus on action)
- Color-coded systems (stance, waves)

## Inspiration
- Fighting games (stance switching, timing)
- Rhythm games (wave flow, timing windows)
- Bullet hells (wave dodging, pattern recognition)
- Wave physics (interference, reflection, absorption)

---

**Next Session Goals:**
1. Create basic player and enemy meshes
2. Build main scene with arena
3. Test core gameplay loop
4. Add basic sound effects

**Remember:** Keep it simple, polish what's there, make it FEEL good!
