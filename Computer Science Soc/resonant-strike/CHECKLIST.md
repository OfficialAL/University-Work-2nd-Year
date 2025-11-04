# Resonant Strike - Development Checklist

Use this checklist to track your progress through the game jam!

## ✅ Week 1-2: Core Systems (COMPLETE!)

- [x] Project structure created
- [x] Player controller with stance system
- [x] Wave propagation system
- [x] Enemy AI base class
- [x] Three enemy types (Fast Striker, Heavy Brute, Trickster)
- [x] Combat manager and game loop
- [x] UI system
- [x] Input mapping configured
- [x] Documentation written

## 📋 Immediate Setup (30 minutes)

- [ ] Open project in Godot 4.5
- [ ] Create player scene (`scenes/player/player.tscn`)
  - [ ] CharacterBody3D with player_controller.gd
  - [ ] Visual/Mesh with CapsuleMesh
  - [ ] Visual/StanceIndicator with SphereMesh
  - [ ] CollisionShape3D
  - [ ] Add to "player" group
- [ ] Create Fast Striker scene (`scenes/enemies/fast_striker.tscn`)
  - [ ] CharacterBody3D with fast_striker.gd
  - [ ] Visual/Mesh with BoxMesh (red)
  - [ ] CollisionShape3D
- [ ] Create main scene (`scenes/main.tscn`)
  - [ ] Node3D root
  - [ ] CombatManager with combat_manager.gd
  - [ ] PlayerSpawnPoint (Marker3D)
  - [ ] EnemySpawnPoint (Marker3D)
  - [ ] Arena (StaticBody3D with plane)
  - [ ] Camera3D
  - [ ] DirectionalLight3D
  - [ ] Player instance
- [ ] Create GameHUD
  - [ ] CanvasLayer with game_hud.gd
  - [ ] MarginContainer → VBoxContainer structure
  - [ ] HealthBar, ResonanceBar
  - [ ] StanceLabel, WaveCounter
  - [ ] EnemyHealthBar
- [ ] Set main.tscn as main scene
- [ ] Test run (F5) - should spawn enemies!

## 📋 Week 3: Visual & Audio Polish

### Scene Polish
- [ ] Player visuals
  - [ ] Create/import low-poly character model
  - [ ] Add AnimationPlayer
  - [ ] Create attack animations (4 directions)
  - [ ] Create stance switch animations
  - [ ] Add hit reaction animations
- [ ] Enemy visuals
  - [ ] Create distinct models for each enemy type
  - [ ] Add enemy animations
  - [ ] Create defeat animations
  - [ ] Add visual variation per type
- [ ] Create Heavy Brute scene
  - [ ] Larger model/mesh
  - [ ] Attach heavy_brute.gd
  - [ ] Configure visual style
- [ ] Create Trickster scene
  - [ ] Unique model/mesh
  - [ ] Attach trickster.gd
  - [ ] Configure visual style

### Wave System Visuals
- [ ] Enhance wave visuals
  - [ ] Add trail renderer/particles
  - [ ] Create ripple effect shader
  - [ ] Add impact particles
  - [ ] Interference pattern effects
- [ ] Arena visuals
  - [ ] Create wave-reactive floor shader
  - [ ] Add boundary visual effects
  - [ ] Create multiple arena variations
  - [ ] Add environmental details

### UI Polish
- [ ] Style health/resonance bars
  - [ ] Custom textures
  - [ ] Smooth animations
  - [ ] Glow effects
- [ ] Enhance HUD
  - [ ] Add combo counter
  - [ ] Show active wave count
  - [ ] Add damage numbers
  - [ ] Tutorial overlay
- [ ] Camera improvements
  - [ ] Add camera shake on impacts
  - [ ] Zoom effects on special moves
  - [ ] Smooth follow player

### Audio Implementation
- [ ] Create/source sound effects
  - [ ] Stance switch (3 variations)
  - [ ] Wave launch (per type)
  - [ ] Wave impact
  - [ ] Wave cancel/interference
  - [ ] Redirect/reflect sounds
  - [ ] Player hit
  - [ ] Enemy hit
  - [ ] Enemy defeat
  - [ ] Resonance building chime
- [ ] Add AudioStreamPlayers to scenes
- [ ] Connect sounds to signals
- [ ] Create/source background music
  - [ ] Main combat theme
  - [ ] Victory theme
  - [ ] Defeat theme
- [ ] Implement audio manager
- [ ] Mix and balance audio levels

## 📋 Week 4: Content & Polish

### Content Creation
- [ ] Create mini-boss enemy
  - [ ] Design unique mechanics
  - [ ] Create model and animations
  - [ ] Implement boss script
  - [ ] Add boss intro/defeat sequence
- [ ] Design wave patterns
  - [ ] Create 10+ unique enemy sequences
  - [ ] Add boss fights (waves 5, 10, 15)
  - [ ] Progressive difficulty curve
- [ ] Create multiple arenas
  - [ ] At least 3 variations
  - [ ] Unique visual themes
  - [ ] Same gameplay space

### Menus & Screens
- [ ] Main menu
  - [ ] Play button
  - [ ] Options button
  - [ ] Credits button
  - [ ] Quit button
- [ ] Options menu
  - [ ] Audio sliders
  - [ ] Graphics settings
  - [ ] Controls display
- [ ] Pause menu
  - [ ] Resume
  - [ ] Options
  - [ ] Main menu
- [ ] Game over screen
  - [ ] Stats display (enemies defeated, waves cleared)
  - [ ] Retry button
  - [ ] Main menu button
- [ ] Victory screen
  - [ ] Celebration animation
  - [ ] Final stats
  - [ ] Continue/Quit
- [ ] Credits screen
  - [ ] Team credits
  - [ ] Asset credits
  - [ ] Tools used

### Balancing & Testing
- [ ] Playtesting sessions
  - [ ] Session 1: Core mechanics feedback
  - [ ] Session 2: Difficulty curve
  - [ ] Session 3: Final balance
  - [ ] Session 4: Polish feedback
  - [ ] Session 5: Bug hunting
- [ ] Balance adjustments
  - [ ] Player health/damage
  - [ ] Enemy stats per type
  - [ ] Wave damage values
  - [ ] Resonance accumulation rate
  - [ ] Attack cooldowns
  - [ ] Difficulty scaling percentage
- [ ] Fix reported bugs
- [ ] Performance optimization
  - [ ] Profile frame rate
  - [ ] Optimize wave pooling
  - [ ] Reduce draw calls
  - [ ] Test on target hardware

### Final Polish
- [ ] Visual effects pass
  - [ ] Screen shake tuning
  - [ ] Particle effect optimization
  - [ ] Color grading
  - [ ] Post-processing
- [ ] Audio mixing pass
  - [ ] Balance all volumes
  - [ ] Add reverb/effects
  - [ ] Ensure no clipping
  - [ ] Test on different speakers
- [ ] Animation polish
  - [ ] Smooth transitions
  - [ ] Add secondary motion
  - [ ] Timing adjustments
- [ ] UI polish
  - [ ] Consistent styling
  - [ ] Smooth transitions
  - [ ] Controller support (if time)
- [ ] Tutorial/onboarding
  - [ ] Controls screen
  - [ ] First-time tips
  - [ ] Practice mode (optional)

### Pre-Release
- [ ] Build for target platform(s)
  - [ ] Windows build
  - [ ] Mac build (if needed)
  - [ ] Linux build (if needed)
  - [ ] Web build (if needed)
- [ ] Test builds on clean systems
- [ ] Create README for players
- [ ] Prepare marketing materials
  - [ ] Screenshots
  - [ ] Gameplay GIF/video
  - [ ] Feature list
  - [ ] itch.io page
- [ ] Final bug sweep
- [ ] Submit to game jam!

## 📋 Optional Enhancements (Post-Jam)

- [ ] Implement stance D mechanics
- [ ] Add combo system with rewards
- [ ] Ultimate ability (full resonance)
- [ ] Environmental hazards
- [ ] PvP multiplayer mode
- [ ] Roguelike elements
  - [ ] Random powerups
  - [ ] Procedural wave generation
  - [ ] Meta progression
- [ ] Training/practice mode
- [ ] Leaderboards
- [ ] Achievement system
- [ ] More enemy types (10+ total)
- [ ] More arenas (10+ total)
- [ ] Story mode
- [ ] Boss rush mode
- [ ] Survival mode

## 📝 Notes

**Current Status:**
- All core systems: COMPLETE ✅
- Scenes to create: PENDING ⏳
- Visuals/Audio: TODO 📋
- Polish: TODO 📋

**Time Estimates:**
- Setup scenes: 30 minutes
- Week 3 tasks: 20-30 hours
- Week 4 tasks: 20-30 hours
- Total to completion: 40-60 hours

**Priorities:**
1. Get playable (scenes) - DO FIRST
2. Visual polish - Week 3
3. Audio - Week 3
4. Balance & menus - Week 4
5. Final polish - Week 4

---

**Track your progress by checking off items as you complete them!**

Good luck! 🌊⚔️✨
