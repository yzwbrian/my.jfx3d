package my.jfx3d.controller;

import static my.jfx3d.base.Axis3DConstants.AXIS_LENGTH;
import static my.jfx3d.base.Axis3DConstants.FACEMATERIAL;

import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import my.jfx3d.base.Point3DUtil;
import my.jfx3d.model.ActionModel;
import my.jfx3d.model.Axis3DAction;
import my.jfx3d.model.Point;
import my.jfx3d.view.Axis3DView;
import my.jfx3d.view.MyRectangle;

public class Axis3DFaceCtl {

	private Axis3DView view;
	private EventHandler<MouseEvent> faceMouseMoved;
	private ActionModel actionModel;
	
	public Axis3DFaceCtl(Axis3DView view, ActionModel model) {
		this.view = view;
		view.getXZFace().setVisible(false);
		view.getYZFace().setVisible(false);
		actionModel = model;
		
		faceMouseMoved = e->{
			if(actionModel.getAxis3DAction() == Axis3DAction.AddPoint) {
				this.view.getPreviewPoint().setVisible(true);
				this.view.getPreviewPoint().setTranslateX(Point3DUtil.toDouble(e.getX()));
				this.view.getPreviewPoint().setTranslateY(Point3DUtil.toDouble(e.getY()));
				this.view.getPreviewPoint().setTranslateZ(Point3DUtil.toDouble(e.getZ()));
			}else {
				this.view.getPreviewPoint().setVisible(false);
			}
		};
	}
	
	public BooleanProperty xyFaceVisibleProperty() {
		return view.getXYFace().visibleProperty();
	}
	public BooleanProperty xzFaceVisibleProperty() {
		return view.getXZFace().visibleProperty();
	}
	public BooleanProperty yzFaceVisibleProperty() {
		return view.getYZFace().visibleProperty();
	}
	
	public void createFace() {
		double len = AXIS_LENGTH/2;
		
		MyRectangle xyRect = new MyRectangle(
			new Point(-len, len, 0),
			new Point(len,len,0),
			new Point(-len,-len,0),
			new Point(len,-len,0));
		xyRect.setMaterial(FACEMATERIAL);
		xyRect.setOnMouseMoved(faceMouseMoved);
		view.getXYFace().getChildren().add(xyRect);
		
		MyRectangle xzRect = new MyRectangle(
				new Point(-len, 0, len),
				new Point(len,0, len),
				new Point(-len,0,-len),
				new Point(len,0,-len));
		xzRect.setMaterial(FACEMATERIAL);
		xzRect.setOnMouseMoved(faceMouseMoved);
		view.getXZFace().getChildren().add(xzRect);
		
		MyRectangle yzRect = new MyRectangle(
				new Point(0,-len, len),
				new Point(0,len,len),
				new Point(0,-len,-len),
				new Point(0,len,-len));
		yzRect.setMaterial(FACEMATERIAL);
		yzRect.setOnMouseMoved(faceMouseMoved);
		view.getYZFace().getChildren().add(yzRect);
	}
	
}
