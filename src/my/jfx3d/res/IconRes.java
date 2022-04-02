package my.jfx3d.res;

public class IconRes {

	private IconRes() {}
	
	private static final IconRes ICONRES = new IconRes();
	private static final String ICON_ROOT_PATH = "/my/jfx3d/res/icon";
	
	public static String getIcon(String name) {
		return ICONRES.getClass().getResource(ICON_ROOT_PATH+"/"+name)
				.toExternalForm();
	}
	
	public static String ARROW_LEFT_16_GIF = getIcon("arrow_left_16.gif");
	public static String ARROW_RIGHT_16_GIF = getIcon("arrow_right_16.gif");
	public static String ARROW_UP_16_GIF = getIcon("arrow_up_16.gif");
	public static String ARROW_DOWN_16_GIF = getIcon("arrow_down_16.gif");
	
	public static String MOVE_16_GIF = getIcon("move_16.gif");
	public static String SCALE_16_GIF = getIcon("scale_16.gif");
	public static String SELECT_16_GIF = getIcon("select_16.gif");
	public static String POINT_16_GIF = getIcon("point_16.gif");
	public static String TRIANGLE_16_GIF = getIcon("triangle_16.gif");
	public static String DELETE_16_GIF = getIcon("delete_16.gif");
	public static String ROTATE_16_GIF = getIcon("rotate_16.gif");
	public static String HEIGHT_16_GIF = getIcon("height_16.gif");
	public static String MESH_16_GIF = getIcon("mesh_16.gif");
	
	public static String X_16_GIF = getIcon("x_16.gif");
	public static String Y_16_GIF = getIcon("y_16.gif");
	public static String Z_16_GIF = getIcon("z_16.gif");
	
	
}
