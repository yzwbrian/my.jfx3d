package my.jfx3d.controller;

import static my.jfx3d.base.POVConstants.MOUSE_SPEED;
import static my.jfx3d.base.POVConstants.ROTATION_SPEED;
import static my.jfx3d.base.POVConstants.SHIFT_MULTIPLIER;
import static my.jfx3d.base.POVConstants.WHITE;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import my.jfx3d.base.MyRobot;
import my.jfx3d.base.POV;
import my.jfx3d.base.Xform;
import my.jfx3d.model.MouseDragModel;
import my.jfx3d.view.POVView;

public class POVCtl {

	private MouseDragModel dragModel = new MouseDragModel();
	
	public POVCtl() {
		handleMouse();
	}
	
	public SubScene getScene() { return povView.getScene(); }
	
	public DoubleProperty rotateXProperty() {
		return povView.getCameraXform().rx.angleProperty();
	}
	public DoubleProperty rotateYProperty() {
		return povView.getCameraXform().ry.angleProperty();
	}
	public DoubleProperty rotateZProperty() {
		return povView.getCameraXform().rz.angleProperty();
	}
	
	private POVView povView = new POVView();
	
	private void handleMouse() {
		handleMouse(povView.getBoxX(false), POV.Right);
		handleMouse(povView.getBoxX(true), POV.Left);
		handleMouse(povView.getBoxY(false), POV.Back);
		handleMouse(povView.getBoxY(true), POV.Front);
		handleMouse(povView.getBoxZ(false), POV.Top);
		handleMouse(povView.getBoxZ(true), POV.Bottom);
		handleMouse(povView.getScene(), povView.getCameraXform());
	}
	
	private void handleMouse(Box s, POV pov) {
		final PhongMaterial pm = (PhongMaterial) s.getMaterial();
		final Color r = pm.getDiffuseColor();
		s.setOnMouseEntered(e->{
			pm.setDiffuseColor(WHITE);
			povView.getTooltip().setText("ÇÐ»»µ½"+pov.getDesc());
			povView.getTooltip().show(s, e.getScreenX() + 16, e.getScreenY() + 16);
		});
		s.setOnMouseExited(e->{
			pm.setDiffuseColor(r);
			povView.getTooltip().hide();
		});
		s.setOnMouseClicked(e->{
			povView.getTooltip().hide();
			povView.getCameraXform().setRx(pov.getRx());
			povView.getCameraXform().setRy(pov.getRy());
			povView.getCameraXform().setRz(pov.getRz());
		});
	}
	
	private Point2D po;
	private void handleMouse(SubScene subScene, Xform cameraXform) {
		
		subScene.setOnMousePressed(e->{
			dragModel.setDragStartX(e.getSceneX());
			dragModel.setDragStartY(e.getSceneY());
			dragModel.setDragEndX(e.getSceneX());
			dragModel.setDragEndY(e.getSceneY());
			po = new Point2D(e.getSceneX(), e.getSceneY());
		});
		subScene.setOnMouseDragged(e->{
			//subScene.setCursor(Cursor.NONE);
			dragModel.setDragStartX(dragModel.getDragEndX());
			dragModel.setDragStartY(dragModel.getDragEndY());
			dragModel.setDragEndX(e.getSceneX());
			dragModel.setDragEndY(e.getSceneY());
			
			double dx = dragModel.getDragEndX() - dragModel.getDragStartX();
			double dy = dragModel.getDragEndY() - dragModel.getDragStartY();
			double modifier = 7.0;
		
			if (e.isShiftDown()) {
				modifier = SHIFT_MULTIPLIER;
			}
			if(e.isControlDown())
				modifier = 1.0;
			if (e.isPrimaryButtonDown()) {
				cameraXform.rx.angleProperty().setValue(cameraXform.rx.getAngle()
						+ dy * MOUSE_SPEED * modifier
						* ROTATION_SPEED);
//				cameraXform.ry.angleProperty().setValue(cameraXform.ry.getAngle()
//						+ dy * MOUSE_SPEED * modifier
//						* ROTATION_SPEED);
				
				cameraXform.rz.angleProperty().setValue(cameraXform.rz.getAngle()
							+ dx * MOUSE_SPEED * modifier
							* ROTATION_SPEED);
			}
		});
		
		subScene.setOnMouseReleased(e->{
			po=subScene.localToScreen(po);
			MyRobot.get().mouseMove(po.getX(), po.getY());
			//subScene.setCursor(Cursor.DEFAULT);
		});
	}
	
	
}
