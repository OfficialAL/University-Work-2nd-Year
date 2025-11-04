# Resonant Strike - Game Jam Project

**Tagline:** *Surf the wave. Ride the energy. Master the flow.*

## Overview
Resonant Strike is a rhythm-inspired 1v1 PvE combat game built for a game jam with the theme "Waves". Players engage in dynamic duels using stance-based combat and energy wave mechanics.

## Core Mechanics

### Player Stances (W/A/S/D)
- **W - Counter/Redirect**: Absorbs wave energy to build resonance for powerful counterstrikes
- **A - Palm/Disperse**: Safely disperses wave energy with moderate damage output
- **S - Rigid**: Aggressive stance that reflects waves for high damage (high risk/reward)
- **D - Reserved**: For future advanced mechanics

### Player Attacks (I/J/K/L)
- **I**: Strike Left (Compression wave)
- **J**: Strike Forward/Palm (Shear wave)
- **K**: Strike Right (Compression wave)
- **L**: Counter/Redirect (Phase-inverted wave)

## Game Loop
1. Enemy appears and attacks with visible wave energy
2. Player reacts by choosing stance and attack
3. Wave interaction: energy absorbed, reflected, or dispersed
4. Enemy AI responds: attacks, parries, or redirects
5. Resolution: player or enemy defeated
6. Next enemy spawns with increasing difficulty

## Enemy Types
- **Fast Striker**: Quick attacks, low health, aggressive
- **Heavy Brute**: High health, powerful attacks, slow
- **Trickster**: High parry/redirect chance, unpredictable
- **Mini-Boss**: (To be implemented)

## Project Structure

```
resonant-strike/
├── scripts/
│   ├── player/
│   │   └── player_controller.gd      # Main player controller
│   ├── enemies/
│   │   ├── enemy_base.gd              # Base enemy AI
│   │   ├── fast_striker.gd            # Fast enemy type
│   │   ├── heavy_brute.gd             # Tank enemy type
│   │   └── trickster.gd               # Defensive enemy type
│   ├── waves/
│   │   └── wave_system.gd             # Wave propagation & interaction
│   ├── managers/
│   │   └── combat_manager.gd          # Combat loop & spawning
│   └── ui/
│       └── game_hud.gd                # HUD system
├── scenes/
│   ├── player/                        # Player scenes
│   ├── enemies/                       # Enemy scenes
│   ├── arenas/                        # Arena scenes
│   └── ui/                            # UI scenes
├── resources/
│   ├── enemy_types/                   # Enemy configurations
│   └── wave_types/                    # Wave configurations
├── assets/
│   ├── models/                        # 3D models
│   └── materials/                     # Materials & shaders
└── audio/
    ├── sfx/                           # Sound effects
    └── music/                         # Background music
```

## Development Roadmap

### Week 1-2: Core Systems ✓
- [x] Player controller with stance switching
- [x] Attack system (I/J/K/L)
- [x] Wave propagation system
- [x] Enemy AI base class
- [x] Enemy types (Fast Striker, Heavy Brute, Trickster)
- [x] Combat manager

### Week 3: Combat Loop & Visuals
- [ ] Create player scene with mesh and animations
- [ ] Create enemy scenes with visuals
- [ ] Build arena scene
- [ ] Implement UI (health bars, resonance meter, stance indicator)
- [ ] Add wave visual effects and trails
- [ ] Implement directional attack animations
- [ ] Audio cue placeholders

### Week 4: Polish & Balance
- [ ] Balance damage values and enemy stats
- [ ] Add multiple enemy wave patterns
- [ ] Polish visual effects (wave ripples, impacts)
- [ ] Add audio (hits, cancels, redirects, stance switches)
- [ ] Playtesting and difficulty tuning
- [ ] Main menu and game over screens
- [ ] Tutorial/controls screen

## How to Setup Scenes in Godot

### 1. Create Player Scene
1. Create new Scene: `scenes/player/player.tscn`
2. Root node: `CharacterBody3D` (attach `player_controller.gd`)
3. Add children:
   - `Visual/Mesh` (MeshInstance3D with CapsuleMesh)
   - `Visual/StanceIndicator` (MeshInstance3D with small mesh for stance color)
   - `CollisionShape3D` (CapsuleShape3D)
   - `AnimationPlayer` (for attack animations)
4. Add to group "player"

### 2. Create Enemy Scenes
Create three scenes in `scenes/enemies/`:
- `fast_striker.tscn` (attach `fast_striker.gd`)
- `heavy_brute.tscn` (attach `heavy_brute.gd`)
- `trickster.tscn` (attach `trickster.gd`)

Each with:
- Root: `CharacterBody3D`
- `Visual/Mesh` (MeshInstance3D with BoxMesh)
- `CollisionShape3D`
- `AnimationPlayer`

### 3. Create Main Game Scene
1. Create `scenes/main.tscn`
2. Root: `Node3D`
3. Add children:
   - `CombatManager` (Node with `combat_manager.gd`)
     - `PlayerSpawnPoint` (Marker3D)
     - `EnemySpawnPoint` (Marker3D)
   - `Arena` (StaticBody3D with floor mesh)
   - `Camera3D` (positioned to view combat)
   - `DirectionalLight3D`
   - Instance of player scene
   - `GameHUD` (CanvasLayer with `game_hud.gd`)

### 4. Create HUD
In `GameHUD` CanvasLayer:
```
GameHUD (CanvasLayer)
└── MarginContainer
    └── VBoxContainer
        ├── PlayerStats
        │   ├── HealthBar (ProgressBar)
        │   └── ResonanceBar (ProgressBar)
        ├── StanceInfo
        │   └── StanceLabel (Label)
        ├── WaveInfo
        │   └── WaveCounter (Label)
        └── EnemyStats
            └── EnemyHealthBar (ProgressBar)
```

## Controls

### Stance Switching
- **W**: Counter stance (Blue)
- **A**: Palm stance (Green)
- **S**: Rigid stance (Red)
- **D**: Reserved

### Attacks
- **I**: Strike Left
- **J**: Strike Forward
- **K**: Strike Right
- **L**: Counter/Redirect

## Wave Colors
- **Red**: Rigid stance / Compression waves
- **Blue**: Counter stance / Phase-inverted waves
- **Green**: Palm stance / Shear waves
- **Orange**: Enemy attack waves

## Design Goals
- Simple yet skillful combat
- Intuitive wave mechanics
- Dynamic PvE duels with energy surfing
- Risk-reward through stance switching
- Rhythm-inspired, emergent energy flow combat

## Next Steps
1. Open Godot and create the scene files as described above
2. Assign the scripts to the appropriate nodes
3. Configure the input actions in Project Settings
4. Test basic player movement and stance switching
5. Add visual polish and effects
6. Implement audio feedback
7. Balance and playtest

## Notes
- All scripts are written and ready to use
- Input actions are configured in `project.godot`
- Wave system handles collision detection and energy flow
- Combat manager controls enemy spawning and progression
- Difficulty scales by 15% each wave

---
**Built with Godot 4.5** | Game Jam Theme: *Waves*
