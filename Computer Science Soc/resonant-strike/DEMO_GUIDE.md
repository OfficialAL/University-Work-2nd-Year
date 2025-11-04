# Resonant Strike - Working Demo in 1 Hour

This guide will get you a **playable demo** as fast as possible. We'll skip polish and focus on functionality.

## ⚡ Quick Setup Path (60 minutes)

### Phase 1: Open Project (5 minutes)

1. **Launch Godot 4.5**
2. **Import Project** → Navigate to your `resonant-strike` folder
3. Select `project.godot` and open
4. Wait for Godot to import assets

### Phase 2: Create Player Scene (10 minutes)

1. **Scene → New Scene** → Select **Other Node** → Choose `CharacterBody3D`
2. Rename root node to **"Player"**
3. **Attach script**: Click script icon → Navigate to `res://scripts/player/player_controller.gd` → Open
4. **Add Visual Container**:
   - Right-click Player → Add Child Node → `Node3D` → Name it "Visual"
   
5. **Add Player Mesh**:
   - Right-click Visual → Add Child Node → `MeshInstance3D` → Name it "Mesh"
   - In Inspector: Mesh property → New CapsuleMesh
   - Expand CapsuleMesh: Radius = 0.5, Height = 2.0
   
6. **Add Stance Indicator**:
   - Right-click Visual → Add Child Node → `MeshInstance3D` → Name it "StanceIndicator"
   - In Inspector: Mesh property → New SphereMesh
   - Expand SphereMesh: Radius = 0.3
   - In Transform: Position Y = 1.5 (above head)
   
7. **Add Collision**:
   - Right-click Player → Add Child Node → `CollisionShape3D`
   - In Inspector: Shape property → New CapsuleShape3D
   - Expand CapsuleShape3D: Radius = 0.5, Height = 2.0
   
8. **Add to Group**:
   - Select Player node → Click "Node" tab (next to Inspector)
   - Under "Groups" → Type "player" → Click "Add"
   
9. **Set Position**: Select Player → In Transform: Position Y = 1.0

10. **Save**: Ctrl+S → Save as `res://scenes/player/player.tscn`

**Your scene tree should look like:**
```
Player (CharacterBody3D) [player_controller.gd]
├── Visual (Node3D)
│   ├── Mesh (MeshInstance3D) - CapsuleMesh
│   └── StanceIndicator (MeshInstance3D) - SphereMesh
└── CollisionShape3D - CapsuleShape3D
```

### Phase 3: Create Enemy Scene (10 minutes)

1. **Scene → New Scene** → `CharacterBody3D` → Name it "FastStriker"
2. **Attach script**: `res://scripts/enemies/fast_striker.gd`
3. **Add Visual**:
   - Add child → `Node3D` → Name "Visual"
   - Add child to Visual → `MeshInstance3D` → Name "Mesh"
   - Mesh property → New BoxMesh
   - BoxMesh: Size = (1, 2, 1)
   
4. **Color it Red** (so you can see it's an enemy):
   - Select Mesh → Inspector → Geometry section
   - Material Override → New StandardMaterial3D
   - Click the material → Albedo → Color → Pick Red
   
5. **Add Collision**:
   - Add child to FastStriker → `CollisionShape3D`
   - Shape → New BoxShape3D
   - Size = (1, 2, 1)
   
6. **Save**: `res://scenes/enemies/fast_striker.tscn`

**Scene tree:**
```
FastStriker (CharacterBody3D) [fast_striker.gd]
├── Visual (Node3D)
│   └── Mesh (MeshInstance3D) - Red BoxMesh
└── CollisionShape3D - BoxShape3D
```

### Phase 4: Create Main Scene (15 minutes)

1. **Scene → New Scene** → `Node3D` → Name it "Main"

2. **Add Combat Manager**:
   - Add child → `Node` → Name "CombatManager"
   - Attach script: `res://scripts/managers/combat_manager.gd`
   
3. **Add Spawn Points**:
   - Add child to CombatManager → `Marker3D` → Name "PlayerSpawnPoint"
   - Inspector → Transform → Position: (0, 0, 5)
   - Add child to CombatManager → `Marker3D` → Name "EnemySpawnPoint"
   - Inspector → Transform → Position: (0, 0, -5)
   
4. **Add Arena Floor**:
   - Add child to Main → `StaticBody3D` → Name "Arena"
   - Add child to Arena → `MeshInstance3D`
   - Mesh → New PlaneMesh
   - PlaneMesh: Size = (20, 20)
   - Add child to Arena → `CollisionShape3D`
   - Shape → New BoxShape3D
   - BoxShape3D: Size = (20, 0.2, 20)
   - CollisionShape3D Transform → Position Y = -0.1
   
5. **Add Camera**:
   - Add child to Main → `Camera3D`
   - Transform → Position: (0, 12, 12)
   - Transform → Rotation Degrees: (-45, 0, 0)
   
6. **Add Light**:
   - Add child to Main → `DirectionalLight3D`
   - Transform → Rotation Degrees: (-45, -30, 0)
   
7. **Add Player Instance**:
   - Right-click Main → **Instantiate Child Scene**
   - Select `res://scenes/player/player.tscn`
   - Select the Player instance → Transform → Position: (0, 1, 3)
   
8. **Configure Combat Manager** (IMPORTANT):
   - Select CombatManager node
   - Inspector → Find "Enemy Scenes" array property
   - Click dropdown arrow → Set Size = 1
   - Element 0 → Click folder icon → Select `res://scenes/enemies/fast_striker.tscn`
   
9. **Save**: `res://scenes/main.tscn`

**Scene tree:**
```
Main (Node3D)
├── CombatManager (Node) [combat_manager.gd]
│   ├── PlayerSpawnPoint (Marker3D)
│   └── EnemySpawnPoint (Marker3D)
├── Arena (StaticBody3D)
│   ├── MeshInstance3D - PlaneMesh
│   └── CollisionShape3D - BoxShape3D
├── Camera3D
├── DirectionalLight3D
└── Player (instance of player.tscn)
```

### Phase 5: Create Simple HUD (15 minutes)

**In the Main scene** (keep it open):

1. **Add HUD**:
   - Add child to Main → `CanvasLayer` → Name "GameHUD"
   - Attach script: `res://scripts/ui/game_hud.gd`

2. **Add Container**:
   - Add child to GameHUD → `MarginContainer`
   - Inspector → Layout → Anchors Preset → Full Rect
   - Custom Minimum Size: Leave default
   - Theme Overrides → Constants → Margin Left: 20
   - Margin Right: 20, Margin Top: 20, Margin Bottom: 20

3. **Add VBoxContainer**:
   - Add child to MarginContainer → `VBoxContainer`

4. **Add Player Stats Section**:
   - Add child to VBoxContainer → `HBoxContainer` → Name "PlayerStats"
   
   **Health Bar:**
   - Add child to PlayerStats → `Label` → Text: "HEALTH: "
   - Add child to PlayerStats → `ProgressBar` → Name "HealthBar"
   - HealthBar Inspector:
     - Max Value: 100
     - Value: 100
     - Show Percentage: Off
     - Custom Minimum Size → X: 200
   
   **Resonance Bar:**
   - Add child to PlayerStats → `Label` → Text: "  RESONANCE: "
   - Add child to PlayerStats → `ProgressBar` → Name "ResonanceBar"
   - ResonanceBar Inspector:
     - Max Value: 100
     - Value: 0
     - Show Percentage: Off
     - Custom Minimum Size → X: 200

5. **Add Stance Info**:
   - Add child to VBoxContainer → `HBoxContainer` → Name "StanceInfo"
   - Add child to StanceInfo → `Label` → Name "StanceLabel"
   - StanceLabel: Text: "STANCE: PALM"
   - Theme Overrides → Font Sizes → Font Size: 24

6. **Add Wave Info**:
   - Add child to VBoxContainer → `HBoxContainer` → Name "WaveInfo"
   - Add child to WaveInfo → `Label` → Name "WaveCounter"
   - WaveCounter: Text: "WAVE: 1"
   - Font Size: 20

7. **Add Enemy Stats**:
   - Add child to VBoxContainer → `VBoxContainer` → Name "EnemyStats"
   - Add child to EnemyStats → `Label` → Text: "ENEMY:"
   - Add child to EnemyStats → `ProgressBar` → Name "EnemyHealthBar"
   - EnemyHealthBar:
     - Max Value: 100
     - Value: 100
     - Custom Minimum Size → X: 300

8. **Save the scene** (Ctrl+S)

**HUD tree:**
```
GameHUD (CanvasLayer) [game_hud.gd]
└── MarginContainer
    └── VBoxContainer
        ├── PlayerStats (HBoxContainer)
        │   ├── Label ("HEALTH: ")
        │   ├── HealthBar (ProgressBar)
        │   ├── Label ("RESONANCE: ")
        │   └── ResonanceBar (ProgressBar)
        ├── StanceInfo (HBoxContainer)
        │   └── StanceLabel (Label)
        ├── WaveInfo (HBoxContainer)
        │   └── WaveCounter (Label)
        └── EnemyStats (VBoxContainer)
            ├── Label ("ENEMY:")
            └── EnemyHealthBar (ProgressBar)
```

### Phase 6: Set Main Scene & Test (5 minutes)

1. **Set Main Scene**:
   - Menu: **Project → Project Settings**
   - Application → Run tab
   - Main Scene → Click folder icon → Select `res://scenes/main.tscn`
   - Click Close

2. **Save Everything**: Ctrl+Shift+S (Save All)

3. **Run the Game**: Press **F5** or click the Play button ▶️

### 🎮 Testing Your Demo

**What Should Happen:**
1. ✅ Game starts, you see the arena from above
2. ✅ Player (capsule) appears in front
3. ✅ After 2 seconds, enemy (red box) spawns in back
4. ✅ HUD shows health, resonance, stance, wave counter

**Try These Controls:**

**Stance Switching:**
- Press **W** → Stance label turns BLUE (Counter)
- Press **A** → Stance label turns GREEN (Palm)
- Press **S** → Stance label turns RED (Rigid)
- Watch the sphere above player change color!

**Attacks:**
- Press **I** → Wave shoots left (colored sphere)
- Press **J** → Wave shoots forward
- Press **K** → Wave shoots right
- Press **L** → Phase-inverted wave (if resonance built)

**Combat:**
- Waves travel across arena
- When wave hits enemy, enemy takes damage
- Enemy will attack back (orange waves)
- When enemy dies, new one spawns
- Health bars update as you fight

## 🐛 Troubleshooting

### "Script errors" on startup
- **Fix**: Check that player_controller.gd is attached to Player node
- **Fix**: Check that fast_striker.gd is attached to FastStriker node
- **Fix**: Check that combat_manager.gd is attached to CombatManager node
- **Fix**: Check that game_hud.gd is attached to GameHUD node

### No enemy spawns
- **Fix**: Select CombatManager → Check "Enemy Scenes" array has the enemy scene
- **Fix**: Check EnemySpawnPoint exists and is child of CombatManager
- **Fix**: Look in Output panel (bottom) for error messages

### Waves don't appear
- **Fix**: Make sure you're pressing I/J/K/L (not arrow keys)
- **Fix**: Check player is in "player" group
- **Fix**: Wave system creates automatically, but check for errors in Output

### HUD not updating
- **Fix**: Check all node names match exactly (HealthBar, ResonanceBar, etc.)
- **Fix**: Make sure nodes are in the right hierarchy
- **Fix**: Player must be in "player" group

### Player/Enemy falls through floor
- **Fix**: Make sure Arena has CollisionShape3D
- **Fix**: Check Arena is StaticBody3D (not just Node3D)

### Camera view is wrong
- **Fix**: Camera Position: (0, 12, 12)
- **Fix**: Camera Rotation: (-45, 0, 0)

## ✨ Quick Improvements (If You Have Extra Time)

### Make Waves More Visible (5 min)
1. Waves are created by wave_system.gd automatically
2. They're already colored based on stance
3. To make bigger: Edit `wave_system.gd` line ~35
   - Change `sphere_mesh.radius = 0.5` to `= 1.0`

### Add Background Color (2 min)
1. Scene → Environment
2. Add child to Main → `WorldEnvironment`
3. Environment → New Environment
4. Background → Mode: Sky → New Sky
5. Or just set Background → Mode: Color → Pick a color

### Make UI Prettier (5 min)
1. Select HealthBar → Theme Overrides → Styles
2. Can add custom colors and styles
3. Or just increase font sizes for better visibility

### Add Simple Audio (10 min)
1. Add child to Player → `AudioStreamPlayer`
2. Find free sound effects online (freesound.org)
3. Import to project (drag into FileSystem)
4. Set to AudioStreamPlayer → Stream property
5. In player_controller.gd, play on attack (for later)

## 🎯 Your Working Demo Should Have:

- ✅ Player character with colored stance indicator
- ✅ Stance switching (W/A/S/D) with visual feedback
- ✅ Directional attacks (I/J/K/L) that create waves
- ✅ Colored wave projectiles that travel
- ✅ Enemy that spawns and fights back
- ✅ Wave collision and damage
- ✅ Health bars that update
- ✅ Resonance system (Counter stance builds it)
- ✅ Enemy AI that attacks periodically
- ✅ New enemies spawn when defeated
- ✅ Functional UI showing all stats

## 📈 Next Steps After Demo Works

Once you have this working:

1. **Iterate on Feel**:
   - Adjust damage values in scripts
   - Tweak enemy spawn timing
   - Balance stance multipliers

2. **Add More Enemies**:
   - Create heavy_brute.tscn (follow same steps as fast_striker)
   - Create trickster.tscn
   - Add to CombatManager's enemy array

3. **Visual Polish**:
   - Import better 3D models
   - Add particle effects
   - Create shaders for waves

4. **Audio**:
   - Add sounds for all actions
   - Background music

5. **Menus**:
   - Main menu scene
   - Pause functionality
   - Game over screen

## 💡 Pro Tips

- **Save often!** Ctrl+S
- **Test frequently** - Run after each major addition
- **Check Output panel** - Shows helpful errors
- **Use Remote tab** when playing - See scene tree while running
- **Copy existing scenes** - Duplicate enemy scenes for variants
- **Read the scripts** - They're heavily commented

## 🎊 You Did It!

If you followed this guide, you now have:
- A **playable combat system**
- **Working stance mechanics**
- **Wave-based attacks**
- **Enemy AI**
- **Progressive difficulty**

This is a **real game prototype** - everything else is polish!

---

**Time Check:**
- ⏱️ Phase 1: 5 min
- ⏱️ Phase 2: 10 min (Player)
- ⏱️ Phase 3: 10 min (Enemy)
- ⏱️ Phase 4: 15 min (Main)
- ⏱️ Phase 5: 15 min (HUD)
- ⏱️ Phase 6: 5 min (Test)
- **Total: ~60 minutes**

**Now go surf those waves!** 🌊⚔️
