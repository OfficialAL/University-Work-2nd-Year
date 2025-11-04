extends Node
class_name WingChunArmController

## Controls Wing Chun arm animations and positions for authentic martial arts
## Handles stance changes and technique animations

# Reference to the arm components
@onready var forearm: MeshInstance3D = get_parent().get_node("ArmGeometry/Forearm")
@onready var hand: MeshInstance3D = get_parent().get_node("ArmGeometry/Hand")
@onready var animation_player: AnimationPlayer = get_parent().get_node("AnimationPlayer") if get_parent().has_node("AnimationPlayer") else null
@onready var hand_hitbox: Area3D = get_parent().get_node("Hitboxes/HandHitbox")

# Wing Chun stance positions and rotations
const WING_CHUN_POSITIONS = {
	# BONG_SAU - Wing arm deflection (Red)
	0: {
		"forearm_position": Vector3(-0.2, 0.1, -0.3),
		"forearm_rotation": Vector3(-30, 25, 15),
		"hand_rotation": Vector3(0, -15, 10),
		"description": "Wing arm - elevated blocking position"
	},
	# TAN_SAU - Dispersing hand (Blue)  
	1: {
		"forearm_position": Vector3(-0.3, -0.1, -0.4),
		"forearm_rotation": Vector3(-15, 35, 0),
		"hand_rotation": Vector3(-10, 20, 0),
		"description": "Dispersing hand - redirecting energy"
	},
	# WU_SAU - Protecting hand (Green)
	2: {
		"forearm_position": Vector3(-0.3, -0.15, -0.4),
		"forearm_rotation": Vector3(-20, 15, 5),
		"hand_rotation": Vector3(0, 0, 0),
		"description": "Protecting hand - centerline guard"
	},
	# CHI_SAU - Sticky hands (Yellow)
	3: {
		"forearm_position": Vector3(-0.25, -0.05, -0.35),
		"forearm_rotation": Vector3(-25, 20, 8),
		"hand_rotation": Vector3(-5, 10, 5),
		"description": "Sticky hands - sensitive contact"
	}
}

# Wing Chun technique animations
const TECHNIQUE_ANIMATIONS = {
	# CHAIN_PUNCH - Straight centerline power
	0: {
		"extension": Vector3(0, 0, -0.2),
		"hand_rotation": Vector3(0, 0, 0),
		"speed": 0.15,
		"description": "Chain punch - straight line power"
	},
	# TAN_DA - Simultaneous block and strike
	1: {
		"extension": Vector3(0.1, 0, -0.15),
		"hand_rotation": Vector3(-10, 15, 0),
		"speed": 0.2,
		"description": "Tan Da - deflect and counter"
	},
	# LAP_SAU - Pulling hand technique
	2: {
		"extension": Vector3(-0.1, 0, -0.1),
		"hand_rotation": Vector3(0, -20, 10),
		"speed": 0.25,
		"description": "Lap Sau - grab and strike"
	},
	# PAK_SAU - Slapping hand opening
	3: {
		"extension": Vector3(0.15, 0, -0.1),
		"hand_rotation": Vector3(0, 25, -5),
		"speed": 0.1,
		"description": "Pak Sau - quick opening creation"
	}
}

# Current state
var current_stance: int = 2  # Start with Wu Sau (protecting hand)
var current_technique: int = -1
var is_animating: bool = false
var base_position: Vector3
var base_rotation: Vector3

func _ready() -> void:
	# Wait for all nodes to be ready
	await get_tree().process_frame
	
	# Verify all required nodes exist
	if not forearm:
		push_error("Forearm node not found at path: ArmGeometry/Forearm")
		return
	if not hand:
		push_error("Hand node not found at path: ArmGeometry/Hand") 
		return
	if not hand_hitbox:
		push_error("Hand hitbox not found at path: Hitboxes/HandHitbox")
		return
	
	# Store initial positions
	base_position = forearm.position
	base_rotation = forearm.rotation_degrees
	
	# Set initial Wing Chun stance
	set_wing_chun_stance(current_stance)
	
	print("Wing Chun arm controller ready - stance: ", WING_CHUN_POSITIONS[current_stance].description)

## Set Wing Chun stance with smooth transition
func set_wing_chun_stance(stance_id: int) -> void:
	if stance_id < 0 or stance_id > 3:
		push_warning("Invalid Wing Chun stance ID: " + str(stance_id))
		return
	
	# Ensure nodes are valid
	if not forearm or not hand:
		push_warning("Arm nodes not ready for stance change")
		return
	
	current_stance = stance_id
	var stance_data = WING_CHUN_POSITIONS[stance_id]
	
	# Smooth transition to new stance
	var tween = create_tween()
	tween.set_parallel(true)
	
	# Animate forearm position and rotation
	tween.tween_property(forearm, "position", stance_data.forearm_position, 0.3)
	tween.tween_property(forearm, "rotation_degrees", stance_data.forearm_rotation, 0.3)
	
	# Animate hand rotation
	tween.tween_property(hand, "rotation_degrees", stance_data.hand_rotation, 0.3)
	
	print("Wing Chun stance changed to: ", stance_data.description)

## Execute Wing Chun technique animation
func execute_wing_chun_technique(technique_id: int) -> void:
	if technique_id < 0 or technique_id > 3 or is_animating:
		return
	
	is_animating = true
	current_technique = technique_id
	var technique_data = TECHNIQUE_ANIMATIONS[technique_id]
	
	# Store current stance position for return
	var stance_data = WING_CHUN_POSITIONS[current_stance]
	var return_position = stance_data.forearm_position
	var _return_rotation = stance_data.forearm_rotation
	var return_hand_rotation = stance_data.hand_rotation
	
	# Calculate technique position
	var technique_position = return_position + technique_data.extension
	var technique_hand_rotation = return_hand_rotation + technique_data.hand_rotation
	
	# Execute technique animation
	var tween = create_tween()
	tween.set_parallel(true)
	
	# Technique execution (forward motion)
	tween.tween_property(forearm, "position", technique_position, technique_data.speed)
	tween.tween_property(hand, "rotation_degrees", technique_hand_rotation, technique_data.speed * 0.8)
	
	# Wait for technique completion
	await tween.finished
	
	# Return to stance (recovery)
	var return_tween = create_tween()
	return_tween.set_parallel(true)
	
	return_tween.tween_property(forearm, "position", return_position, technique_data.speed * 1.2)
	return_tween.tween_property(hand, "rotation_degrees", return_hand_rotation, technique_data.speed)
	
	await return_tween.finished
	
	is_animating = false
	current_technique = -1
	
	print("Wing Chun technique completed: ", technique_data.description)

## Get current arm extension for hitbox positioning
func get_arm_extension() -> float:
	var stance_data = WING_CHUN_POSITIONS[current_stance]
	return abs(stance_data.forearm_position.z)

## Check if arm can execute technique (not currently animating)
func can_execute_technique() -> bool:
	return not is_animating

## Get current stance description for UI feedback
func get_current_stance_description() -> String:
	return WING_CHUN_POSITIONS[current_stance].description

## Enable/disable hand hitbox based on technique state
func set_hand_hitbox_active(active: bool) -> void:
	if hand_hitbox:
		hand_hitbox.monitoring = active
		hand_hitbox.monitorable = active

## Get hand position in world space for energy wave spawning
func get_hand_world_position() -> Vector3:
	if hand:
		return hand.global_position
	return Vector3.ZERO

## Create Wing Chun specific hand positioning for different techniques
func set_hand_shape_for_technique(technique_id: int) -> void:
	# This would control finger positioning for different Wing Chun techniques
	# For now, we'll use basic hand shapes
	match technique_id:
		0:  # CHAIN_PUNCH - closed fist
			print("Hand shape: Closed fist for Chain Punch")
		1:  # TAN_DA - open palm deflection
			print("Hand shape: Open palm for Tan Da")
		2:  # LAP_SAU - grabbing hand
			print("Hand shape: Grabbing position for Lap Sau")  
		3:  # PAK_SAU - slapping palm
			print("Hand shape: Slapping palm for Pak Sau")

## Advanced Wing Chun positioning based on opponent distance
func adjust_for_wing_chun_range(distance_to_opponent: float) -> void:
	# Wing Chun operates in close range (1.5-2.5 meters)
	var optimal_range = clamp(distance_to_opponent, 1.5, 2.5)
	var range_factor = optimal_range / 2.0
	
	# Adjust arm extension based on range
	var stance_data = WING_CHUN_POSITIONS[current_stance]
	var adjusted_position = stance_data.forearm_position
	adjusted_position.z *= range_factor
	
	var tween = create_tween()
	tween.tween_property(forearm, "position", adjusted_position, 0.2)

## Validate Wing Chun form and provide feedback
func validate_wing_chun_form() -> Dictionary:
	var form_data = {
		"centerline_maintained": is_centerline_maintained(),
		"elbow_position_correct": is_elbow_position_correct(),
		"wrist_alignment_good": is_wrist_alignment_good(),
		"stance_stability": get_stance_stability()
	}
	
	return form_data

func is_centerline_maintained() -> bool:
	# Check if arm position maintains Wing Chun centerline theory
	return abs(forearm.position.x) < 0.4

func is_elbow_position_correct() -> bool:
	# Wing Chun elbows should be relatively low and close to body
	return forearm.rotation_degrees.x > -45 and forearm.rotation_degrees.x < 15

func is_wrist_alignment_good() -> bool:
	# Wrist should be aligned for proper energy transfer
	return abs(hand.rotation_degrees.z) < 20

# ===== EXTERNAL INTERFACE FOR PLAYER CONTROLLER =====

## Set stance (external interface)
func set_stance(stance_id: int) -> void:
	set_wing_chun_stance(stance_id)

## Play technique animation (external interface)
func play_technique_animation(technique_name: String, _is_primary: bool = true) -> void:
	var technique_id = -1
	
	# Convert technique name to ID
	match technique_name:
		"CHAIN_PUNCH":
			technique_id = 0
		"TAN_DA":
			technique_id = 1
		"LAP_SAU":
			technique_id = 2
		"PAK_SAU":
			technique_id = 3
		_:
			push_warning("Unknown technique: " + technique_name)
			return
	
	# Execute technique animation
	execute_wing_chun_technique(technique_id)

func get_stance_stability() -> float:
	# Calculate stance stability based on position consistency
	var stance_data = WING_CHUN_POSITIONS[current_stance]
	var position_accuracy = forearm.position.distance_to(stance_data.forearm_position)
	return max(0.0, 1.0 - position_accuracy * 2.0)
