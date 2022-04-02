package my.jfx3d.controller;

import static my.jfx3d.base.Axis3DConstants.CAMERA_INITIAL_DISTANCE;

import javafx.beans.property.DoubleProperty;
import javafx.scene.PerspectiveCamera;
import my.jfx3d.base.Xform;

public class Axis3DCameraCtl {

	private PerspectiveCamera camera;
	private Xform cameraXform;
	
	public Axis3DCameraCtl(Xform cameraXform, PerspectiveCamera camera) {
		this.cameraXform = cameraXform;
		this.camera =camera;
	}
	
	public void resetScale() {
		camera.translateZProperty().setValue(CAMERA_INITIAL_DISTANCE);
	}
	
	public void Scale(double value) {
		value += camera.translateZProperty().get();
		camera.translateZProperty().setValue(value);
	}
	
	public void resetMove() {
		camera.setTranslateX(0);
		camera.setTranslateY(0);
	}
	
	public void move(double value, boolean isAxisX) {
		value += isAxisX ? camera.getTranslateX() : camera.getTranslateY();
		if(isAxisX)
			camera.setTranslateX(value);
		else
			camera.setTranslateY(value);
	}
	
	public void resetMoveHeight() {
		cameraXform.t.zProperty().set(0);
	}
	
	public void moveHeight(double value) {
		value += cameraXform.t.zProperty().get();
		cameraXform.t.zProperty().set(value);
	}
	
	public void resetRotateX() { cameraXform.rx.setAngle(0); }
	public void resetRotateY() { cameraXform.ry.setAngle(0); }
	public void resetRotateZ() { cameraXform.rz.setAngle(0); }
	
	public void rotateX(double value) {
		value += cameraXform.rx.getAngle();
		cameraXform.setRx(value);
	}
	public void rotateY(double value) {
		value += cameraXform.ry.getAngle();
		cameraXform.setRy(value);
	}
	public void rotateZ(double value) {
		value += cameraXform.rz.getAngle();
		cameraXform.setRz(value);
	}
	
	public DoubleProperty rxProperty(){
		return cameraXform.rx.angleProperty();
	}
	public DoubleProperty ryProperty(){
		return cameraXform.ry.angleProperty();
	}
	public DoubleProperty rzProperty(){
		return cameraXform.rz.angleProperty();
	}
}
