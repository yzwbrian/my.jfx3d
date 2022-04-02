package my.jfx3d.model;

import javafx.beans.binding.LongBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import my.jfx3d.base.Point3DUtil;

public class Point {

	private final DoubleProperty x = new SimpleDoubleProperty(),
			y = new SimpleDoubleProperty(),
			z = new SimpleDoubleProperty();
	private final IntegerProperty index = new SimpleIntegerProperty(-1);
	
	private final LongProperty bits = new SimpleLongProperty();

	public Point() {
		this(0,0,0);
	}
	
	public Point(double px, double py, double pz){
		setX(px);
		setY(py);
		setZ(pz);
		
		bits.bind(
			new LongBinding() {
				{
					super.bind(x, y, z);
				}
				@Override
				protected long computeValue() {
					return Point3DUtil.toLong(x.get(), y.get(), z.get());
				}
			}
		);
	}
	
	public DoubleProperty xProperty() { return x; }
	public double getX() { return x.get(); }
	public void setX(double value) { x.set(value); }
	
	public DoubleProperty yProperty() { return y; }
	public double getY() { return y.get(); }
	public void setY(double value) { y.set(value); }
	
	public DoubleProperty zProperty() { return z; }
	public double getZ() { return z.get(); }
	public void setZ(double value) { z.set(value); }
	
	public long getBits() { return bits.get(); }
	
	public IntegerProperty indexProperty() { return index; }
	public int getIndex() { return index.get(); }
	public void setIndex(int index) { this.index.set(index); }
	
	public void update(Point p) {
		if(p != null) {
			p.setX(getX());
			p.setY(getY());
			p.setZ(getZ());
		}
	}
	
}
