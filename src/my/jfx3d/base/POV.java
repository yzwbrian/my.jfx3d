package my.jfx3d.base;

/**
 * ��ͼ�۲��
 * @author mvw
 *
 */
public enum POV {
	/**�û���ͼ*/User(0),
	/**����ǰ��ͼ*/Front(1), 
	/**��������ͼ*/Back(2), 
	/**��������ͼ*/Left(3), 
	/**��������ͼ*/Right(4), 
	/**��������ͼ*/Top(5), 
	/**��������ͼ*/Bottom(6);
	

	public static final String[] DESC = {
		"�û���ͼ", "����ǰ��ͼ", "��������ͼ" , "��������ͼ" , "��������ͼ", 
		"��������ͼ", "��������ͼ"
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