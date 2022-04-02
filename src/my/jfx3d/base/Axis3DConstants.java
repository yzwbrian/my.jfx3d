package my.jfx3d.base;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Axis3DConstants {

	public static final double CAMERA_INITIAL_DISTANCE = -150;

	// public static final double CAMERA_INITIAL_X_ANGLE =0.0;
	// public static final double CAMERA_INITIAL_Y_ANGLE =0.0;
	// public static final double CAMERA_INITIAL_Z_ANGLE =0.0;

	public static final double CAMERA_INITIAL_X_ANGLE = 45.0;
	public static final double CAMERA_INITIAL_Y_ANGLE = 180.0;
	public static final double CAMERA_INITIAL_Z_ANGLE = -45.0;

	public static final double CAMERA_NEAR_CLIP = 0.1;
	public static final double CAMERA_FAR_CLIP = 10000.0;

	public static final double AXIS_LENGTH = 130.0;
	public static final double AXIS_WIDTH = 0.1;
	public static final double AXIS_GRID_WIDTH = 0.1;
	
	public static final Color AXISXCOLOR = Color.RED, 
			AXISYCOLOR  = Color.GREEN,
			AXISZCOLOR = Color.BLUE;
	
	//--grid--
	public static final double GRID_SIZE = 5;
	public static final Color GRID_COLOR = Color.DIMGRAY;
	public static final PhongMaterial GRID_MATERIAL = new PhongMaterial(GRID_COLOR);
	//
	
	public static final Color FACECOLOR = Color.rgb(230, 230, 230);
	public static final PhongMaterial FACEMATERIAL = new PhongMaterial(FACECOLOR);
	
	public static final double DEFAULT_VIEW_WIDTH = 400;
	public static final double DEFAULT_VIEW_HEIGHT = 300;
	
	public static final double CONTROL_MULTIPLIER = 0.1;    
	public static final double SHIFT_MULTIPLIER = 10.0;    
	public static final double MOUSE_SPEED = 0.1;    
	public static final double ROTATION_SPEED = 1.0;   
	
}
