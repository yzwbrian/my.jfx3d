package my.jfx3d.model;

import javafx.beans.property.SimpleObjectProperty;

public class ActionModel {
	
	private final SimpleObjectProperty<Axis3DAction> axis3DActionProperty = 
			new SimpleObjectProperty<Axis3DAction>(this, "axis3DAction");
	
	public ActionModel() {
		
	}

	public SimpleObjectProperty<Axis3DAction> axis3DActionProperty() {
		return axis3DActionProperty;
	}
	
	public void setAxis3DAction(Axis3DAction action) {
		axis3DActionProperty.set(action);
	}
	
	public Axis3DAction getAxis3DAction() {
		return axis3DActionProperty.get();
	}
	
	
}
