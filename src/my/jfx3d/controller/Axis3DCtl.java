package my.jfx3d.controller;

import static my.jfx3d.base.POVConstants.MOUSE_SPEED;
import static my.jfx3d.base.POVConstants.SHIFT_MULTIPLIER;

import java.net.URISyntaxException;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import my.jfx3d.model.ActionModel;
import my.jfx3d.model.Axis3DAction;
import my.jfx3d.model.MeshViewModel;
import my.jfx3d.model.MouseDragModel;
import my.jfx3d.res.ObjRes;
import my.jfx3d.view.ActionButtonVSBox;
import my.jfx3d.view.Axis3DOptionView;
import my.jfx3d.view.Axis3DPTMPView;
import my.jfx3d.view.Axis3DView;
import my.jfx3d.view.MyPoint;

public class Axis3DCtl {
	private final ActionModel actionModel = new ActionModel();
	private final MouseDragModel dragModel = new MouseDragModel();
	private final MeshViewModel meshViewModel = new MeshViewModel();
	
	private final Axis3DView view = new Axis3DView();
	private final Axis3DOptionView optionView = new Axis3DOptionView();
	private final ActionButtonVSBox vsBox = new ActionButtonVSBox(actionModel);
	private final Axis3DPTMPView ptmpView;
	
	private final Axis3DCameraCtl cameraCtl;
	private final Axis3DGridCtl gridController;
	private final Axis3DFaceCtl faceController;
	private final Axis3DPointsCtl pointsController;
	private final Axis3DTriangleCtl triangleCtl;
	
	public Axis3DCtl() throws URISyntaxException {
		
		cameraCtl = new Axis3DCameraCtl(view.getCameraXform(), 
				(PerspectiveCamera) view.getScene().getCamera());
		
		gridController = new Axis3DGridCtl(view, actionModel);
		gridController.createGrid();
		
		faceController = new Axis3DFaceCtl(view, actionModel);
		faceController.createFace();
		
		triangleCtl = new Axis3DTriangleCtl(view.getTriangleXform(), actionModel);
		pointsController = new Axis3DPointsCtl(view.getPointXform(), actionModel, 
				triangleCtl.getTriangleModel());

		handleSceneMouseEvent();
		handleSceneScrollEvent();
		
		bindOptionSelectedProperty();
		handleActionModelChaneListener();
		bindPreviewPointEvent();
		
		ptmpView = new Axis3DPTMPView(pointsController.getPointModel());
		
//		Box box = new Box(10,10,10);
//		view.getMeshViewXform().getChildren().add(
//				box);
//		box.setOnMousePressed(e->{
//			System.out.println(e.getX()+","+e.getY()+","+e.getZ());
//		});
		ObjModelImporter omi = new ObjModelImporter();
		omi.read(ObjRes.getObj("d2.obj"));
		MeshView mv1 = omi.getImport()[0];
		view.getMeshViewXform().getChildren().add(mv1);
		TriangleMesh tm = (TriangleMesh)mv1.getMesh();
		
		
	}

	public Axis3DCameraCtl getCameraController() {
		return cameraCtl;
	}
	
	public Axis3DFaceCtl getFaceController() {
		return faceController;
	}
	
	public Axis3DGridCtl getGridController() {
		return gridController;
	}
	
	public Axis3DPointsCtl getPointsController() {
		return pointsController;
	}
	
	public SubScene getScene() {
		return view.getScene();
	}
	
	public Axis3DOptionView getOptionView() { return optionView; }
	public ActionButtonVSBox getVSBox() { return vsBox; }
	public Axis3DPTMPView getPTMPView() { return ptmpView; }
	
	private void handleSceneMouseEvent() {
		view.getScene().setOnMousePressed(e->{
			if(actionModel.getAxis3DAction() == Axis3DAction.Rotate 
					||actionModel.getAxis3DAction() == Axis3DAction.Move)
			{
				dragModel.setDragStartX(e.getSceneX());
				dragModel.setDragStartY(e.getSceneY());
				dragModel.setDragEndX(e.getSceneX());
				dragModel.setDragEndY(e.getSceneY());
			}
		});
		view.getScene().setOnMouseDragged(e->{
			double dx = 0, dy = 0;
			if(actionModel.getAxis3DAction() == Axis3DAction.Rotate 
					|| actionModel.getAxis3DAction() == Axis3DAction.Move)
			{
				dragModel.setDragStartX(dragModel.getDragEndX());
				dragModel.setDragStartY(dragModel.getDragEndY());
				dragModel.setDragEndX(e.getSceneX());
				dragModel.setDragEndY(e.getSceneY());
				dx = dragModel.getDragEndX() - dragModel.getDragStartX();
				dy = dragModel.getDragEndY() - dragModel.getDragStartY();
			}
			
			if(Axis3DAction.Rotate == actionModel.getAxis3DAction()) {
				double modifier = 7.0;
			
				if (e.isShiftDown()) {
					modifier = SHIFT_MULTIPLIER;
				}
				if(e.isControlDown())
					modifier = 1.0;
				if (e.isPrimaryButtonDown()) {
					cameraCtl.rotateX(dy * modifier * MOUSE_SPEED);
					cameraCtl.rotateZ(dx * modifier * MOUSE_SPEED);
				}
			}else if(Axis3DAction.Move == actionModel.getAxis3DAction()) {
				if(e.isPrimaryButtonDown()) {
					dx *= 0.1;
					dy *= 0.1;
					cameraCtl.move(dx, true);
					cameraCtl.move(dy, false);
				}
			}
		});
		view.getScene().setOnMouseReleased(e->{
			if(actionModel.getAxis3DAction() == Axis3DAction.Rotate 
					||actionModel.getAxis3DAction() == Axis3DAction.Move)
			{
				dragModel.clear();
			}
		});
	}
	
	private void handleSceneScrollEvent() {
		view.getScene().setOnScroll(e->{
			Axis3DAction action = actionModel.getAxis3DAction();
			double v = 0;
			if(e.isShiftDown())
				v = e.getDeltaX() < 0 ? -5 : 5;
			else if(e.isControlDown()) {
				v = e.getDeltaY() < 0 ? -0.1 : 0.1;
			}else
				v = e.getDeltaY() < 0 ? -1 : 1;
			
			if(action == Axis3DAction.Scale) {
				cameraCtl.Scale(v);
			}else if(action == Axis3DAction.Height) {
				cameraCtl.moveHeight(v);
			}else if(action == Axis3DAction.MovePointX) {
				MyPoint sel = pointsController.getSelected();
				if(sel != null) {
					sel.setTranslateX(sel.getPoint().getX() + v);
				}
			}else if(action == Axis3DAction.MovePointY) {
				MyPoint sel = pointsController.getSelected();
				if(sel != null) {
					sel.setTranslateY(sel.getPoint().getY() + v);
				}
			}else if(action == Axis3DAction.MovePointZ) {
				MyPoint sel = pointsController.getSelected();
				if(sel != null) {
					sel.setTranslateZ(sel.getPoint().getZ() + v);
				}
			}
		});
	}
	
	private void handleActionModelChaneListener() {
		actionModel.axis3DActionProperty().addListener((e, o, n) ->{
			if(n == null || n == Axis3DAction.None 
					|| n == Axis3DAction.Rotate || n == Axis3DAction.Move) {
				dragModel.clear();
			}
			
			if(Axis3DAction.AddPoint == n) {
				view.getPreviewPoint().setVisible(true);
			}
			else {
				view.getPreviewPoint().setVisible(false);
			}
			
			if(Axis3DAction.AddTriangle != n) {
				triangleCtl.getTriangleModel().clear();
			}
			
			if(Axis3DAction.TriangleMesh != n) {
				//meshViewModel.clear();
			}
			
			if(Axis3DAction.TriangleMesh == n) {
				generateMeshView();
			}
		});
	}
	
	private void bindOptionSelectedProperty() {
		optionView.rx().textProperty().bind(new SimpleStringProperty("X:").concat(
				view.getCameraXform().rx.angleProperty().asString()
			));
		optionView.ry().textProperty().bind(new SimpleStringProperty("Y:").concat(
				view.getCameraXform().ry.angleProperty().asString()
			));
		optionView.rz().textProperty().bind(new SimpleStringProperty("Z:").concat(
				view.getCameraXform().rz.angleProperty().asString()
			));
		
		optionView.xyFace().selectedProperty().bindBidirectional (view.getXYFace().visibleProperty());
		optionView.xzFace().selectedProperty().bindBidirectional(view.getXZFace().visibleProperty());
		optionView.yzFace().selectedProperty().bindBidirectional(view.getYZFace().visibleProperty());
		
		optionView.xyGrid().selectedProperty().bindBidirectional(view.getXYGrid().visibleProperty());
		optionView.xzGrid().selectedProperty().bindBidirectional(view.getXZGrid().visibleProperty());
		optionView.yzGrid().selectedProperty().bindBidirectional(view.getYZGrid().visibleProperty());
		
		optionView.xAxis().selectedProperty().bindBidirectional(view.getAxisX().visibleProperty());
		optionView.yAxis().selectedProperty().bindBidirectional(view.getAxisY().visibleProperty());
		optionView.zAxis().selectedProperty().bindBidirectional(view.getAxisZ().visibleProperty());
		
		optionView.points().selectedProperty().bindBidirectional(view.getPointXform().visibleProperty());
		optionView.triangles().selectedProperty().bindBidirectional(
				view.getTriangleXform().visibleProperty());
		optionView.meshs().selectedProperty().bindBidirectional(
				view.getMeshViewXform().visibleProperty());
	}
	
	private void bindPreviewPointEvent() {
		
		view.getPreviewPoint().setOnMouseClicked(e->{
			if(actionModel.getAxis3DAction() == Axis3DAction.AddPoint) {
				pointsController.add(view.getPreviewPoint().clone());
			}
		});
	}
	
	private void generateMeshView() {
		view.getMeshViewXform().getChildren().clear();
		view.getMeshViewXform().getChildren().add(meshViewModel.generateMeshView(view.getPointXform(),
				view.getTriangleXform()));
	}
	
}
