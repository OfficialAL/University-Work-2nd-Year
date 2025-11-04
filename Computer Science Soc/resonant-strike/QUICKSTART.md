# Resonant Strike - Quick Start Guide

## Immediate Next Steps

### 1. Open Your Project in Godot
- Launch Godot 4.5
- Open the project from: `resonant-strike/project.godot`
- You should see all the scripts in the FileSystem panel

### 2. Verify Input Actions
The input actions are already configured! Go to **Project > Project Settings > Input Map** to verify:
- Stances: W, A, S, D
- Attacks: I, J, K, L

### 3. Create the Player Scene (5 minutes)

**Step-by-step:**
1. Click **Scene > New Scene**
2. Select **3D Scene** (creates Node3D root)
3. Right-click root → Change Type → `CharacterBody3D`
4. Rename to "Player"
5. Click the script icon (attach script) → Select `res://scripts/player/player_controller.gd`
6. Add child nodes:
   ```
   Player (CharacterBody3D)
   ├── Visual (Node3D)
   │   ├── Mesh (MeshInstance3D)
   │   │   └── Set Mesh to CapsuleMesh
   │   └── StanceIndicator (MeshInstance3D)
   │       └── Set Mesh to SphereMesh (radius 0.3)
   └── CollisionShape3D
       └── Set Shape to CapsuleShape3D
   ```
7. Add Player to group "player": Select Player node → Groups tab → Type "player" → Add
8. Save as: `scenes/player/player.tscn`

### 4. Create a Simple Enemy Scene (5 minutes)

1. **Scene > New Scene > 3D Scene**
2. Change root to `CharacterBody3D`, rename to "FastStriker"
3. Attach script: `res://scripts/enemies/fast_striker.gd`
4. Add nodes:
   ```
   FastStriker (CharacterBody3D)
   ├── Visual (Node3D)
   │   └── Mesh (MeshInstance3D)
   │       └── Set Mesh to BoxMesh (make it Red in material)
   └── CollisionShape3D
       └── Set Shape to BoxShape3D
   ```
5. Save as: `scenes/enemies/fast_striker.tscn`

### 5. Create the Main Game Scene (10 minutes)

1. **Scene > New Scene > 3D Scene**
2. Rename root to "Main"
3. Add children:
   ```
   Main (Node3D)
   ├── CombatManager (Node)
   │   ├── Attach script: combat_manager.gd
   │   ├── PlayerSpawnPoint (Marker3D)
   │   │   └── Position: (0, 1, 3)
   │   └── EnemySpawnPoint (Marker3D)
   │       └── Position: (0, 1, -5)
   ├── Arena (StaticBody3D)
   │   ├── MeshInstance3D
   │   │   └── Mesh: PlaneMesh (size 20x20)
   │   └── CollisionShape3D
   │       └── Shape: BoxShape3D (size 20, 0.1, 20)
   ├── Camera3D
   │   └── Position: (0, 10, 8), Rotation: (-45, 0, 0)
   ├── DirectionalLight3D
   │   └── Rotation: (-45, -30, 0)
   └── Player
       └── Instance: scenes/player/player.tscn
   ```
4. **Important**: In CombatManager node inspector, add the enemy scene:
   - Find "Enemy Scenes" property
   - Click Add Element
   - Load: `res://scenes/enemies/fast_striker.tscn`
5. Save as: `scenes/main.tscn`

### 6. Create the HUD (10 minutes)

1. In the Main scene, add a child node: `CanvasLayer`
2. Rename to "GameHUD"
3. Attach script: `res://scripts/ui/game_hud.gd`
4. Build this UI structure:
   ```
   GameHUD (CanvasLayer)
   └── MarginContainer
       └── Margins: 20 all sides
       └── VBoxContainer
           ├── PlayerStats (HBoxContainer)
           │   ├── Label (text: "HEALTH:")
           │   ├── HealthBar (ProgressBar)
           │   │   └── Max Value: 100, Show Percentage: false
           │   ├── Label (text: "RESONANCE:")
           │   └── ResonanceBar (ProgressBar)
           │       └── Max Value: 100, Show Percentage: false
           ├── StanceInfo (HBoxContainer)
           │   └── StanceLabel (Label)
           │       └── Text: "STANCE: PALM"
           │       └── Font size: 24
           ├── WaveInfo (HBoxContainer)
           │   └── WaveCounter (Label)
           │       └── Text: "WAVE: 1"
           └── EnemyStats (VBoxContainer)
               ├── Label (text: "ENEMY:")
               └── EnemyHealthBar (ProgressBar)
   ```
5. Save the scene

### 7. Set Main Scene as Default

1. **Project > Project Settings > Application > Run**
2. Set **Main Scene** to: `res://scenes/main.tscn`
3. Click **Close**

### 8. Test Your Game!

Press **F5** or click the Play button!

**What should happen:**
- Game starts with player in arena
- After 2 seconds, a Fast Striker enemy appears
- You can switch stances (W/A/S/D) - stance indicator color changes
- You can attack (I/J/K/L) - colored wave projectiles shoot out
- Waves travel and can hit enemies
- Enemy AI will attack back

## Controls Reminder

**Stances:**
- **W** = Counter (Blue) - Absorbs waves → builds resonance
- **A** = Palm (Green) - Disperses waves → safe play
- **S** = Rigid (Red) - Reflects waves → high damage/risk

**Attacks:**
- **I** = Strike Left (Compression)
- **J** = Strike Forward (Shear/Palm)
- **K** = Strike Right (Compression)
- **L** = Counter Release (Phase-inverted)

## Troubleshooting

**"Script not found" errors:**
- Make sure all scripts are in the correct folders
- Check that script paths match in the scene

**Input not working:**
- Verify Input Map in Project Settings
- Check that player_controller.gd is attached to player

**No waves appear:**
- Check that WaveSystem node is created (combat_manager creates it automatically)
- Enable "Visible" in the editor to see 3D elements

**Enemy doesn't spawn:**
- Make sure enemy scene is added to CombatManager's "Enemy Scenes" array
- Check Output/Debugger for errors

## Next Development Steps

Once basic gameplay works:

1. **Visual Polish**
   - Add wave trail effects
   - Create proper 3D models
   - Add particle effects on impacts
   - Shader for arena wave distortions

2. **Audio**
   - Create/import sound effects
   - Add AudioStreamPlayers to scenes
   - Connect to wave events

3. **More Enemy Types**
   - Create Heavy Brute scene
   - Create Trickster scene
   - Add variety to wave patterns

4. **Game Feel**
   - Screen shake on impacts
   - Slow-mo on successful parries
   - Visual feedback improvements

5. **Menus & Polish**
   - Main menu
   - Pause menu
   - Game over screen
   - Victory screen

## File Checklist

**Scripts Created:**
- ✓ `scripts/player/player_controller.gd`
- ✓ `scripts/waves/wave_system.gd`
- ✓ `scripts/enemies/enemy_base.gd`
- ✓ `scripts/enemies/fast_striker.gd`
- ✓ `scripts/enemies/heavy_brute.gd`
- ✓ `scripts/enemies/trickster.gd`
- ✓ `scripts/managers/combat_manager.gd`
- ✓ `scripts/ui/game_hud.gd`

**Scenes to Create:**
- ☐ `scenes/player/player.tscn`
- ☐ `scenes/enemies/fast_striker.tscn`
- ☐ `scenes/enemies/heavy_brute.tscn`
- ☐ `scenes/enemies/trickster.tscn`
- ☐ `scenes/main.tscn`

**Configuration:**
- ✓ Input actions in `project.godot`
- ✓ Project structure created
- ✓ README and documentation

---

**You're all set to build Resonant Strike! 🌊⚔️**

Start with creating the scenes and you'll have a playable prototype in ~30 minutes!
