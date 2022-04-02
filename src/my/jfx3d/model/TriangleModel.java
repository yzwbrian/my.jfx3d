package my.jfx3d.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import my.jfx3d.view.MyPoint;
import my.jfx3d.view.MyTriangle;

public class TriangleModel {
	private MyPoint[] mps = new MyPoint[3];
	private int pointIndex = -1;
	
	private final IntegerProperty pointSize = new SimpleIntegerProperty();
	
	public TriangleModel() {
	}
	
	public final IntegerProperty pointSizeProperty() { return pointSize; }
	
	public void clear() {
		pointIndex = -1;
		for(int i=0;i<mps.length;i++) {
			if(mps[i] != null)
				mps[i].setMaterial(MyPoint.MATERIAL);
			mps[i] = null;
		}
	}
	
	public boolean add(MyPoint mp) {
		if(pointIndex > 2 || mp == null) {
			return false;
		}
		pointIndex++;
		mps[pointIndex] = mp;
		mps[pointIndex].setMaterial(MyPoint.TRIANGLE_MATERIAL);
		pointSize.set(pointIndex+1);
		return true;
	}
	
	public int getPointsSize() { return pointSize.get(); }
	
	public MyTriangle createTriangle() {
		return new MyTriangle(mps[0], mps[1], mps[2]);
	}
	
}
