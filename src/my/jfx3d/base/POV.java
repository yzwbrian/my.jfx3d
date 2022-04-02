package my.jfx3d.base;

/**
 * 视图观察点
 * @author mvw
 *
 */
public enum POV {
	/**用户视图*/User(0),
	/**正交前视图*/Front(1), 
	/**正交后视图*/Back(2), 
	/**正交左视图*/Left(3), 
	/**正交右视图*/Right(4), 
	/**正交顶视图*/Top(5), 
	/**正交底视图*/Bottom(6);
	

	public static final String[] DESC = {
		"用户视图", "正交前视图", "正交后视图" , "正交左视图" , "正交右视图", 
		"正交顶视图", "正交底视图"
	};
	public static final double[] RX = {
		0, 90, 90, 90, 90, 0, 180
	};
	public static final double[] RY = {
		0, 180, 180, 180, 180, 180, 180 
	};
	public static final double[] RZ = {
		0, 180, 0, 90, -90, 180, 180
	};
	
	private int value = 0;
	
	POV(int val) {
		value = val;
	} 
	
	public int getValue() { return value; }
	public double getRx() { return RX[value]; }
	public double getRy() { return RY[value]; }
	public double getRz() { return RZ[value]; }
	public String getDesc() { return DESC[value]; }
};