package my.jfx3d.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MouseDragModel {

	private final DoubleProperty dragStartXProperty = new SimpleDoubleProperty(),
			dragStartYProperty = new SimpleDoubleProperty(),
			dragEndXProperty = new SimpleDoubleProperty(),
			dragEndYProperty = new SimpleDoubleProperty();
	
	public MouseDragModel() {
		
	}
	
	public DoubleProperty dragStartXProperty() { return dragStartXProperty; }
	public DoubleProperty dragStartYProperty() { return dragStartYProperty; }

	public DoubleProperty dragEndXProperty() { return dragEndXProperty; }
	public DoubleProperty dragEndYProperty() { return dragEndYProperty; }
	
	public void setDragStartX(double value) { dragStartXProperty.set(value); }
	public double getDragStartX() { return dragStartXProperty.get(); }
	
	public void setDragStartY(double value) { dragStartYProperty.set(value); }
	public double getDragStartY() { return dragStartYProperty.get(); }
	
	public void setDragEndX(double value) { dragEndXProperty.set(value); }
	public double getDragEndX() { return dragEndXProperty.get(); }
	
	public void setDragEndY(double value) { dragEndYProperty.set(value); }
	public double getDragEndY() { return dragEndYProperty.get(); }
	
	public void clear() {
		setDragStartX(0);
		setDragStartY(0);
		setDragEndX(0);
		setDragEndY(0);
	}
	
	public void debug() {
		System.out.println("startX:"+getDragStartX()+", startY:"+getDragStartY());
		System.out.println("endX:"+getDragEndX()+", endY:"+getDragEndY());
	}
}
