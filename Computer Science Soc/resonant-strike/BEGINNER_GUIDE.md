# Godot Beginner's Guide: Creating Your First Game Scenes

## 🎯 What Are Scenes in Godot?

Think of **scenes** like LEGO building blocks:
- Each scene is a reusable piece (like a player, enemy, or UI element)
- You combine scenes to build your complete game
- Scenes contain **nodes** (the actual game objects) arranged in a tree structure

**For Resonant Strike, we need 3 main scenes:**
1. **Player scene** - Your character that fights
2. **Enemy scene** - The opponents you fight
3. **Main scene** - The game world that brings everything together

---

## 🚀 Step 1: Open Your Project

1. **Launch Godot 4.5**
2. **Click "Import"** in the project manager
3. **Navigate to your project folder** and select `project.godot`
4. **Click "Import & Edit"**

You should now see the Godot editor with your project loaded!

**What you'll see:**
- **FileSystem** panel (bottom-left) - Shows all your project files
- **Scene** panel (top-left) - Shows the current scene structure  
- **Inspector** panel (right) - Shows properties of selected objects
- **3D Viewport** (center) - Where you build and view your 3D world

---

## 🎮 Step 2: Create Your First Scene (The Player)

### Creating the Scene Structure

1. **Click "Scene" menu → "New Scene"**
2. **Select "3D Scene"** (this creates a basic 3D world)
   - You'll see a "Node3D" appear in the Scene panel

3. **Right-click the "Node3D" in Scene panel**
4. **Select "Change Type"**
5. **Type "CharacterBody3D" and press Enter**
   - CharacterBody3D is perfect for characters that move and collide with things

6. **Right-click "CharacterBody3D" → "Rename"**
7. **Type "Player" and press Enter**

### Attaching the Player Script

8. **Click the Player node in Scene panel**
9. **Click the script icon** (📜) next to the Player name
10. **Click "Load"** button
11. **Navigate to `scripts/player/player_controller.gd` and select it**
12. **Click "Open"**
    - This connects your player logic to this scene!

### Adding Visual Components

Now we'll add the parts that make your player visible and functional:

13. **Right-click "Player" → "Add Child"**
14. **Type "Node3D" and press Enter**
15. **Rename it to "Visual"**
    - This will hold all the visual parts

16. **Right-click "Visual" → "Add Child"**
17. **Type "MeshInstance3D" and press Enter**
18. **Rename it to "PlayerMesh"**

19. **Click "PlayerMesh" in Scene panel**
20. **In Inspector panel, find "Mesh" property**
21. **Click dropdown arrow → "New CapsuleMesh"**
    - Your player now looks like a capsule (pill shape)!

22. **Right-click "Visual" → "Add Child"**
23. **Type "MeshInstance3D" and press Enter**
24. **Rename it to "StanceIndicator"**

25. **Click "StanceIndicator" in Scene panel**
26. **In Inspector panel, Mesh → "New SphereMesh"**
27. **Click the SphereMesh, set Radius to 0.3**
    - This small sphere will show your current stance color

### Adding Collision Detection

28. **Right-click "Player" → "Add Child"**
29. **Type "CollisionShape3D" and press Enter**

30. **Click "CollisionShape3D" in Scene panel**
31. **In Inspector panel, Shape → "New CapsuleShape3D"**
    - This invisible shape detects when your player hits things

### Final Structure Check
Your Scene panel should look like this:
```
Player (CharacterBody3D) 📜
├── Visual (Node3D)
│   ├── PlayerMesh (MeshInstance3D)
│   └── StanceIndicator (MeshInstance3D)
└── CollisionShape3D
```

### Save Your Scene

32. **Press Ctrl+S** (or Scene → Save Scene)
33. **Navigate to the `scenes` folder**
34. **Create a new folder called "player"**
35. **Name the file "Player.tscn"**
36. **Click "Save"**

🎉 **Congratulations! You've created your first scene!**

---

## 🤖 Step 3: Create an Enemy Scene

Let's create a simple enemy that your player can fight:

1. **Click "Scene" menu → "New Scene"**
2. **Select "3D Scene"**
3. **Change Node3D type to "CharacterBody3D"**
4. **Rename to "FastStriker"**

### Attach Enemy Script

5. **Click FastStriker node**
6. **Click script icon → Load**
7. **Select `scripts/enemies/fast_striker.gd`**

### Add Enemy Visual

8. **Right-click "FastStriker" → "Add Child" → "Node3D"**
9. **Rename to "Visual"**

10. **Right-click "Visual" → "Add Child" → "MeshInstance3D"**
11. **Rename to "EnemyMesh"**

12. **Click "EnemyMesh"**
13. **Inspector → Mesh → "New BoxMesh"**
    - Enemies are box-shaped to look different from player

### Make Enemy Red (So You Can Tell It's an Enemy!)

14. **With EnemyMesh selected, in Inspector:**
15. **Find "Material Override" → dropdown → "New StandardMaterial3D"**
16. **Click the new material**
17. **Find "Albedo" → click the white color box**
18. **Pick a red color → OK**

### Add Enemy Collision

19. **Right-click "FastStriker" → "Add Child" → "CollisionShape3D"**
20. **Inspector → Shape → "New BoxShape3D"**

### Save Enemy Scene

21. **Ctrl+S**
22. **Navigate to `scenes` folder → create "enemies" folder**
23. **Save as "FastStriker.tscn"**

---

## 🌍 Step 4: Create the Main Game Scene

This scene brings everything together - it's your game world!

1. **Scene → New Scene → 3D Scene**
2. **Rename Node3D to "Main"**

### Add Combat Manager (The Game Brain)

3. **Right-click "Main" → "Add Child" → "Node"**
4. **Rename to "CombatManager"**
5. **Attach script: `scripts/managers/combat_manager.gd`**

### Create the Game World

6. **Right-click "Main" → "Add Child" → "StaticBody3D"**
7. **Rename to "Ground"**

8. **Right-click "Ground" → "Add Child" → "MeshInstance3D"**
9. **Inspector → Mesh → "New PlaneMesh"**
10. **Click PlaneMesh → Size: X=20, Z=20**
    - This creates a big flat ground

11. **Right-click "Ground" → "Add Child" → "CollisionShape3D"**
12. **Inspector → Shape → "New BoxShape3D"**
13. **Click BoxShape3D → Size: X=20, Y=0.2, Z=20**

### Add Lighting

14. **Right-click "Main" → "Add Child" → "DirectionalLight3D"**
15. **In Inspector → Transform → Rotation: X=-45, Y=-30**
    - This creates sunlight coming from above

### Add Camera

16. **Right-click "Main" → "Add Child" → "Camera3D"**
17. **Transform → Position: X=0, Y=10, Z=8**
18. **Transform → Rotation: X=-45, Y=0, Z=0**
    - This positions camera to look down at the action

### Add Your Player to the World

19. **Right-click "Main" → "Add Child" → "instantiate"**
20. **Navigate to `scenes/player/Player.tscn` → Open**
    - Your player now appears in the world!

### Add Wave System

21. **Right-click "Main" → "Add Child" → "Node"**
22. **Rename to "WaveSystem"**
23. **Attach script: `scripts/waves/wave_system.gd`**

### Connect Enemy to Combat Manager

24. **Click "CombatManager" in Scene panel**
25. **In Inspector, find "Enemy Scenes" array**
26. **Click "Add Element"**
27. **Click the dropdown → "Load"**
28. **Select `scenes/enemies/FastStriker.tscn`**

### Save Main Scene

29. **Ctrl+S → Save as "Main.tscn" in scenes folder**

---

## 🎮 Step 5: Test Your Game!

1. **Click the "Play" button** (▶️) in top-right
2. **Select "Main.tscn" as your main scene**
3. **Click "Select Current"**

**Your game should now run!**

You should see:
- Your white capsule player in the center
- A red box enemy should spawn
- Ground plane beneath everything
- You can move and fight using the controls!

### Controls to Test:
- **W/A/S/D** - Change stance (watch the small sphere change color!)
- **I/J/K/L** - Attack in different directions
- **Arrow keys** - Move around

---

## 🐛 Common Beginner Issues & Solutions

### "Script failed to load"
- **Solution:** Make sure you selected the correct .gd file when attaching scripts
- **Check:** The script path shows correctly in Inspector

### "Player falls through ground"
- **Solution:** Make sure Ground has both MeshInstance3D AND CollisionShape3D
- **Check:** CollisionShape3D has a BoxShape3D assigned

### "Can't see anything"
- **Solution:** 
  - Check Camera3D position and rotation
  - Add DirectionalLight3D
  - Make sure meshes have materials

### "Enemy doesn't spawn"
- **Solution:** 
  - Check CombatManager has Enemy Scenes array filled
  - Make sure FastStriker.tscn is saved properly

### "Controls don't work"
- **Solution:** Check Project → Project Settings → Input Map
- **Verify:** W,A,S,D and I,J,K,L are mapped correctly

---

## 🎯 What You've Accomplished

🎉 **Amazing work!** You've just:

1. ✅ Created a complete 3D player character with movement and combat
2. ✅ Built an enemy that can fight your player  
3. ✅ Made a game world with ground, lighting, and camera
4. ✅ Connected all the systems together into a playable game
5. ✅ Learned the fundamentals of Godot scene creation

**You now have a working game!** The scripts handle all the complex combat logic, wave interactions, and AI - you just built the visual foundation that brings it all to life.

---

## 🚀 Next Steps

Now that you have the basics working:

1. **Experiment** - Try changing colors, sizes, positions
2. **Add more enemies** - Follow the enemy creation steps for Heavy Brute and Trickster
3. **Improve visuals** - Add better materials, particle effects
4. **Create a UI** - Add health bars and stance indicators

**Remember:** Game development is all about iteration. Start simple, test often, and gradually add more features!

**You're officially a game developer now! 🎮✨**