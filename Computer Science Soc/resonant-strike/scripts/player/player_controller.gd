extends CharacterBody3D
class_name PlayerController

## Player controller for Resonant Strike
## Handles stance switching (W/A/S/D) and directional attacks (I/J/K/L)

# Stance enumeration - Wing Chun inspired
enum Stance {
	BONG_SAU,    # S - Deflecting/Wing arm - high risk, redirects force
	TAN_SAU,     # W - Dispersing hand - absorbs and neutralizes  
	WU_SAU,      # A - Guarding hand - safe, protective
	CHI_SAU      # D - Sticky hands - advanced sensing/trapping
}

# Attack direction enumeration - Wing Chun techniques
enum AttackDirection {
	CHAIN_PUNCH,     # I - Rapid centerline punch
	TAN_DA,          # J - Simultaneous block and strike
	LAP_SAU,         # K - Pulling hand/redirect
	PAK_SAU          # L - Slapping hand/trap
}

# Player stats
@export var max_health: float = 100.0
@export var max_resonance: float = 100.0
@export var movement_speed: float = 5.0

# Current state
var current_health: float
var current_resonance: float
var current_stance: Stance = Stance.WU_SAU  # Start in guard position
var is_attacking: bool = false
var can_switch_stance: bool = true

# Wing Chun stance properties  
const STANCE_COLORS = {
	Stance.BONG_SAU: Color.RED,     # Deflecting wing - dangerous
	Stance.TAN_SAU: Color.BLUE,     # Dispersing hand - controlled
	Stance.WU_SAU: Color.GREEN,     # Guarding hand - safe
	Stance.CHI_SAU: Color.YELLOW    # Sticky hands - advanced
}

const STANCE_DAMAGE_MULTIPLIERS = {
	Stance.BONG_SAU: 1.5,    # High damage when deflecting
	Stance.TAN_SAU: 0.8,     # Lower damage, builds energy
	Stance.WU_SAU: 1.0,      # Balanced damage
	Stance.CHI_SAU: 1.2      # Sensing advantage
}

const STANCE_DEFENSE_MULTIPLIERS = {
	Stance.BONG_SAU: 1.4,    # High risk - can be overwhelmed
	Stance.TAN_SAU: 0.6,     # Excellent absorption
	Stance.WU_SAU: 0.8,      # Good protection  
	Stance.CHI_SAU: 0.7      # Sensitivity allows evasion
}

# Attack cooldowns
@export var attack_cooldown: float = 0.3
var attack_timer: float = 0.0

# References (to be set in scene)
@onready var visual_mesh: MeshInstance3D = $Visual/PlayerMesh
@onready var stance_indicator: MeshInstance3D = $Camera3D/StanceIndicator
@onready var left_hand: MeshInstance3D = $Camera3D/LeftHand if has_node("Camera3D/LeftHand") else null
@onready var right_hand: MeshInstance3D = $Camera3D/RightHand if has_node("Camera3D/RightHand") else null
@onready var animation_player: AnimationPlayer = $AnimationPlayer if has_node("AnimationPlayer") else null

# Signals
signal stance_changed(new_stance: Stance)
signal attack_performed(direction: AttackDirection, stance: Stance)
signal resonance_changed(new_value: float)
signal health_changed(new_value: float)
signal player_overloaded()

func _ready() -> void:
	current_health = max_health
	current_resonance = 0.0
	
	# Debug stance indicator
	if stance_indicator:
		print("Stance indicator found!")
		print("Stance indicator position: ", stance_indicator.global_position)
		print("Stance indicator visible: ", stance_indicator.visible)
	else:
		print("ERROR: Stance indicator not found!")
	
	update_stance_visual()

func _process(delta: float) -> void:
	# Update attack cooldown
	if attack_timer > 0:
		attack_timer -= delta
		is_attacking = attack_timer > 0

func _physics_process(_delta: float) -> void:
	# Handle stance switching
	handle_stance_input()
	
	# Handle attacks
	handle_attack_input()
	
	# No movement - this is a stationary first-person combat game
	# Player stays in position and focuses on stance/attack timing

## First-person combat - no movement needed
func handle_movement() -> void:
	# Movement disabled - pure stance-based combat
	velocity = Vector3.ZERO

## Handle stance switching with W/A/S/D
func handle_stance_input() -> void:
	if not can_switch_stance or is_attacking:
		return
	
	var new_stance: Stance = current_stance
	
	if Input.is_action_just_pressed("stance_counter"):  # W
		new_stance = Stance.TAN_SAU
		print("Switched to TAN SAU - Dispersing Hand (Blue)")
	elif Input.is_action_just_pressed("stance_palm"):  # A
		new_stance = Stance.WU_SAU
		print("Switched to WU SAU - Guarding Hand (Green)")
	elif Input.is_action_just_pressed("stance_rigid"):  # S
		new_stance = Stance.BONG_SAU
		print("Switched to BONG SAU - Wing Arm (Red)")
	elif Input.is_action_just_pressed("stance_reserved"):  # D
		new_stance = Stance.CHI_SAU
		print("Switched to CHI SAU - Sticky Hands (Yellow)")
	
	if new_stance != current_stance:
		switch_stance(new_stance)

## Switch to a new stance
func switch_stance(new_stance: Stance) -> void:
	current_stance = new_stance
	update_stance_visual()
	stance_changed.emit(new_stance)
	
	# Play stance switch sound
	# AudioManager.play_stance_switch(new_stance)

## Handle attack input with I/J/K/L
func handle_attack_input() -> void:
	if is_attacking:
		return
	
	var attack_dir: AttackDirection = AttackDirection.TAN_DA
	var should_attack: bool = false
	
	if Input.is_action_just_pressed("attack_left"):  # I
		attack_dir = AttackDirection.CHAIN_PUNCH
		should_attack = true
		print("CHAIN PUNCH - Rapid centerline strike!")
	elif Input.is_action_just_pressed("attack_forward"):  # J
		attack_dir = AttackDirection.TAN_DA
		should_attack = true
		print("TAN DA - Simultaneous block and strike!")
	elif Input.is_action_just_pressed("attack_right"):  # K
		attack_dir = AttackDirection.LAP_SAU
		should_attack = true
		print("LAP SAU - Pulling hand redirect!")
	elif Input.is_action_just_pressed("attack_redirect"):  # L
		attack_dir = AttackDirection.PAK_SAU
		should_attack = true
		print("PAK SAU - Slapping hand trap!")
	
	if should_attack:
		perform_attack(attack_dir)

## Perform an attack
func perform_attack(direction: AttackDirection) -> void:
	is_attacking = true
	attack_timer = attack_cooldown
	
	# Create visual energy wave
	create_energy_wave(direction)
	
	# Animate hands based on attack direction
	animate_attack_hands(direction)
	
	# Emit attack signal for wave system to handle
	attack_performed.emit(direction, current_stance)
	
	# Play attack animation
	if animation_player:
		animation_player.play("attack_" + AttackDirection.keys()[direction].to_lower())
	
	# Consume resonance for redirect attacks (Lap Sau)
	if direction == AttackDirection.LAP_SAU and current_stance == Stance.TAN_SAU:
		var _resonance_damage = current_resonance * 2.0  # Amplified damage
		current_resonance = 0.0
		resonance_changed.emit(current_resonance)

## Create visual energy wave based on Wing Chun technique
func create_energy_wave(direction: AttackDirection) -> void:
	var wave = MeshInstance3D.new()
	var wave_mesh = SphereMesh.new()
	wave_mesh.radius = 0.12
	wave_mesh.height = 0.24
	wave.mesh = wave_mesh
	
	# Enhanced material with metallic and emission properties
	var material = StandardMaterial3D.new()
	material.albedo_color = get_wave_color(direction)
	
	# Make it glow and look energetic
	material.emission_enabled = true
	material.emission = material.albedo_color * 2.0  # Brighter emission
	material.metallic = 0.3
	material.roughness = 0.2
	material.rim_enabled = true
	material.rim = 1.0
	material.rim_tint = 0.5
	
	# Transparency and fresnel effect
	material.transparency = BaseMaterial3D.TRANSPARENCY_ALPHA
	material.albedo_color.a = 0.8
	material.flags_transparent = true
	material.flags_use_point_size = true
	
	wave.material_override = material
	
	# Position wave at hand/attack origin
	wave.position = global_position + Vector3(0, 1.2, -0.3)  # More at chest level
	get_tree().current_scene.add_child(wave)
	
	# Animate wave toward enemy
	animate_wave_to_target(wave, direction)
	
	print("🌊 Created ", get_technique_name(direction), " energy wave!")

## Get wave color based on technique and stance
func get_wave_color(direction: AttackDirection) -> Color:
	var base_color = STANCE_COLORS[current_stance]
	
	match direction:
		AttackDirection.CHAIN_PUNCH:
			return base_color.lerp(Color.YELLOW, 0.3)  # Fast energy
		AttackDirection.TAN_DA:
			return base_color.lerp(Color.WHITE, 0.4)   # Balanced energy
		AttackDirection.LAP_SAU:
			return base_color.lerp(Color.CYAN, 0.5)    # Pulling energy
		AttackDirection.PAK_SAU:
			return base_color.lerp(Color.MAGENTA, 0.3) # Trapping energy
	
	return base_color

## Get technique name for display
func get_technique_name(direction: AttackDirection) -> String:
	match direction:
		AttackDirection.CHAIN_PUNCH:
			return "CHAIN PUNCH"
		AttackDirection.TAN_DA:
			return "TAN DA"
		AttackDirection.LAP_SAU:
			return "LAP SAU"
		AttackDirection.PAK_SAU:
			return "PAK SAU"
	return "UNKNOWN"

## Animate wave traveling to target
func animate_wave_to_target(wave: MeshInstance3D, direction: AttackDirection) -> void:
	if not wave:
		return
		
	var target_position = global_position + Vector3(0, 1, -2.5)  # Closer enemy position
	var start_position = wave.position
	var travel_time = 0.8
	
	# Create smooth wave motion
	var tween = create_tween()
	tween.set_parallel(true)
	
	# Position animation
	tween.tween_property(wave, "position", target_position, travel_time)
	
	# Scale animation (wave grows as it travels)
	tween.tween_property(wave, "scale", Vector3(1.5, 1.5, 1.5), travel_time)
	
	# Rotation for visual effect
	tween.tween_property(wave, "rotation", Vector3(0, PI * 2, 0), travel_time)
	
	# Fade out animation
	if wave.material_override is StandardMaterial3D:
		var mat = wave.material_override as StandardMaterial3D
		tween.tween_method(fade_wave_alpha, 0.7, 0.0, travel_time)
		
	# Clean up wave after animation
	await tween.finished
	if wave and is_instance_valid(wave):
		wave.queue_free()
		print("🌊 Wave dispersed!")

## Fade wave alpha during animation
func fade_wave_alpha(alpha: float) -> void:
	# This will be called by tween to fade the wave
	pass

## Animate hands during Wing Chun techniques
func animate_attack_hands(direction: AttackDirection) -> void:
	if not left_hand or not right_hand:
		return
		
	# Wing Chun guard position
	var left_guard = Vector3(-0.2, -0.1, -0.4)
	var right_guard = Vector3(0.2, -0.1, -0.4)
	
	match direction:
		AttackDirection.CHAIN_PUNCH:  # I key - Rapid centerline
			print("CHAIN PUNCH - Centerline attack!")
			right_hand.position = right_guard + Vector3(-0.1, 0, -0.5)  # Right punch center
			left_hand.position = left_guard + Vector3(0.1, 0, -0.2)     # Left guards
			
		AttackDirection.TAN_DA:  # J key - Block and strike
			print("TAN DA - Deflect and counter!")
			left_hand.position = left_guard + Vector3(-0.2, 0.2, -0.3)  # Deflecting
			right_hand.position = right_guard + Vector3(0, 0, -0.4)     # Striking
			
		AttackDirection.LAP_SAU:  # K key - Pull/redirect
			print("LAP SAU - Pulling energy!")
			left_hand.position = left_guard + Vector3(0.2, -0.2, -0.1)  # Pull motion
			right_hand.position = right_guard + Vector3(-0.2, 0.1, -0.5) # Counter
			
		AttackDirection.PAK_SAU:  # L key - Trap
			print("PAK SAU - Trapping hand!")
			left_hand.position = left_guard + Vector3(0.3, 0, -0.3)     # Slap across
			right_hand.position = right_guard + Vector3(-0.1, 0, -0.4)  # Follow up
	
	# Return to guard position
	await get_tree().create_timer(0.4).timeout
	if left_hand and right_hand:
		left_hand.position = left_guard
		right_hand.position = right_guard

## Update visual representation of current stance
func update_stance_visual() -> void:
	if stance_indicator:
		# Create or update material
		var material = stance_indicator.get_surface_override_material(0) as StandardMaterial3D
		if not material:
			material = StandardMaterial3D.new()
			stance_indicator.set_surface_override_material(0, material)
		
		material.albedo_color = STANCE_COLORS[current_stance]
		
		# Make sure it's visible and properly positioned for first-person
		stance_indicator.visible = true
		print("Stance indicator color changed to: ", STANCE_COLORS[current_stance])
		print("Stance indicator position: ", stance_indicator.position)

## Take damage from waves or attacks
func take_damage(amount: float, wave_type: String = "") -> void:
	var actual_damage = amount * STANCE_DEFENSE_MULTIPLIERS[current_stance]
	current_health -= actual_damage
	health_changed.emit(current_health)
	
	if current_health <= 0:
		player_overloaded.emit()
		# Handle player defeat
	
	# Visual feedback
	flash_damage()

## Absorb wave energy (Tan Sau - Dispersing Hand)
func absorb_wave_energy(amount: float) -> void:
	if current_stance == Stance.TAN_SAU:
		current_resonance = min(current_resonance + amount, max_resonance)
		resonance_changed.emit(current_resonance)
		# Visual feedback for absorption

## Get current damage multiplier based on stance
func get_damage_multiplier() -> float:
	var base_multiplier = STANCE_DAMAGE_MULTIPLIERS[current_stance]
	
	# Bonus damage if using resonance with Tan Sau
	if current_stance == Stance.TAN_SAU and current_resonance > 50:
		base_multiplier *= 1.5
	
	return base_multiplier

## Visual feedback for taking damage
func flash_damage() -> void:
	# TODO: Implement damage flash effect
	pass

## Get the direction vector for Wing Chun techniques
func get_attack_direction_vector(direction: AttackDirection) -> Vector3:
	match direction:
		AttackDirection.CHAIN_PUNCH:
			return -transform.basis.z  # Straight centerline
		AttackDirection.TAN_DA:
			return -transform.basis.z  # Forward deflect and strike
		AttackDirection.LAP_SAU:
			return -transform.basis.z  # Pull forward energy
		AttackDirection.PAK_SAU:
			return transform.basis.x   # Across the centerline
	
	return Vector3.ZERO
