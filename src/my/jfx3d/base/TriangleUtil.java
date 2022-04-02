package my.jfx3d.base;

import my.jfx3d.model.Point;

public class TriangleUtil {
	//判断三角形顶点的方向。返回1表示顺时针，0表示三点共线，-1表示逆时针。
	public static final int ccw(Point a, Point b, Point c){
			float m00 = (float) a.getX();
			float m01 = (float) a.getY();
			float m02 = (float) a.getZ();
			
			float m10 = (float) b.getX();
			float m11 = (float) b.getY();
			float m12 = (float) b.getZ();
			
			float m20 = (float) c.getX();
			float m21 = (float) c.getY();
			float m22 = (float) c.getZ();
			
			if(m00 == 0 && m10 == 0 && m20 == 0) {
				return ccw_x0(a, b, c);
			}else if(m01 == 0 && m11 == 0 && m21 == 0) {
				return ccw_y0(a, b, c);
			}else if(m02 == 0 && m12 == 0 && m22 == 0) {
				return ccw_z0(a, b, c);
			}
			// 计算det.
			float f =
					m00 * (m11 * m22 - m12 * m21)
						+ m01 * (m12 * m20 - m10 * m22)
						+ m02 * (m10 * m21 - m11 * m20);
			
			return f > 0 ? 1 : f < 0 ? -1 : 0;
	}
	
	// 2d向量的叉乘
	public static final float crossProduct(Point a, Point b){
		float x1 = (float) a.getX(), y1 = (float) a.getY();
		float x2 = (float) b.getX(), y2 = (float) b.getY();
		return x1 * y2 - x2 * y1;
	}
	// 计算三角形的面积，可能为负
	public static final float triangleArea(Point a, Point b, Point c) {
		float x1 = (float) a.getX(), y1 = (float) a.getY();
		float x2 = (float) b.getX(), y2 = (float) b.getY();
		float x3 = (float) c.getX(), y3 = (float) c.getY();
		
		return ((x1 - x2) * (y2 - y3) - (x2 - x3) * (y1 - y2)) * 0.5f;
	}
	//判断三角形顶点的方向。返回1表示顺时针，0表示三点共线，-1表示逆时针。
	public static final int ccw_z0(Point a, Point b, Point c) {
		float ax = (float) a.getX(), ay = (float) a.getY();
		float bx = (float) b.getX(), by = (float) b.getY();
		float cx = (float) c.getX(), cy = (float) c.getY();
		
		float v = (bx - ax) * (cy - ay) - (cx - ax) * (by - ay);
		return v < 0 ? -1 : v > 0 ? 1 : 0;
	}
	public static final int ccw_x0(Point a, Point b, Point c) {
		float az = (float) a.getZ(), ay = (float) a.getY();
		float bz = (float) b.getZ(), by = (float) b.getY();
		float cz = (float) c.getZ(), cy = (float) c.getY();
		
		float v = (by - ay) * (cz - az) - (cy - ay) * (bz - az);
		return v < 0 ? -1 : v > 0 ? 1 : 0;
	}
	public static final int ccw_y0(Point a, Point b, Point c) {
		float ax = (float) a.getX(), az = (float) a.getZ();
		float bx = (float) b.getX(), bz = (float) b.getZ();
		float cx = (float) c.getX(), cz = (float) c.getZ();
		
		float v = (bx - ax) * (cz - az) - (cx - ax) * (bz - az);
		return v < 0 ? -1 : v > 0 ? 1 : 0;
	}
}
