package my.jfx3d.base;

import javafx.geometry.Point3D;
import my.utils.base.BitUtil;

public class Point3DUtil {

	public static double toDouble(double d) {
		return toDouble(toInt(d));
	}
	/**
	 * 只保留一位小数,其余小数全部舍去,并转换为整数<br>
	 */
	public static int toInt(double d) {
		return (int)(d*10);
	}
	
	/**
	 * 整数的个位数转为浮点数的小数部分,其余为浮点数的整数部分
	 */
	public static double toDouble(int n) {
		return n/10.0;
	}
	
	public static long toLong(Point3D p) {
		return toLong(p.getX(), p.getY(), p.getZ());
	}
	/**
	 * 将javafx Point3D类型转为long类型，保留一定的精度
	 * @param p
	 * @return 返回大于等于0的整数
	 */
	public static long toLong(double px, double py, double pz) {
		long l = 0;
		int x = toInt(px), y = toInt(py), z = toInt(pz);
		//设置符号
		l = x<0 ? BitUtil.setBit(l, 62, true) : l;
		l = y<0 ? BitUtil.setBit(l, 61, true) : l;
		//System.out.println("z--:"+l);
		l = z<0 ? BitUtil.setBit(l, 60, true) : l;
		
		//2B = 16bit = (0 ~ 65535)
		
		x = Math.abs(x);
		y= Math.abs(y);
		z = Math.abs(z);
		//System.out.println("l-x-y-z:"+l+","+x+","+y+","+z);
		x = BitUtil.setBits(x, 16, 16, false);
		y = BitUtil.setBits(y, 16, 16, false);
		z = BitUtil.setBits(z, 16, 16, false);
		//System.out.println("l__-x-y-z:"+l+","+x+","+y+","+z);
		
		//System.out.println("x<<32: "+(x<<32));
		//System.out.println("y<<16: "+(y<<16));
		//System.out.println("z<<0"+z);
		return l + ((long)x<<32) + ((long)y<<16) + z;
	}
	
	/**
	 * 将long类型解析为javafx Point3D类型
	 * @param l
	 * @return
	 */
	public static Point3D toPoint3D(long l) {
		if(l < 0)
			return Point3D.ZERO;
		//System.out.println("l-value:"+l);
		int x = (int)BitUtil.getBits(l, 32, 16),
			y = (int)BitUtil.getBits(l, 16, 16),
			z = (int)BitUtil.getBits(l, 0, 16);
		
		x = BitUtil.getBit(l, 62) ? -x : x;
		y = BitUtil.getBit(l, 61) ? -y : y;
		z = BitUtil.getBit(l, 60) ? -z : z;
		
		return new Point3D(toDouble(x), toDouble(y), toDouble(z));
	}
	
	public static Point3D format(double x, double y, double z) {
		return new Point3D(toDouble(x), toDouble(y), toDouble(z));
	}
	
}
