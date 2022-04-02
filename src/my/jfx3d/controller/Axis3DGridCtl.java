package my.jfx3d.controller;

import static my.jfx3d.base.Axis3DConstants.AXIS_GRID_WIDTH;
import static my.jfx3d.base.Axis3DConstants.AXIS_LENGTH;
import static my.jfx3d.base.Axis3DConstants.GRID_MATERIAL;
import static my.jfx3d.base.Axis3DConstants.GRID_SIZE;

import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Box;
import my.jfx3d.base.Point3DUtil;
import my.jfx3d.model.ActionModel;
import my.jfx3d.model.Axis3DAction;
import my.jfx3d.view.Axis3DView;

public class Axis3DGridCtl {

	private Axis3DView view;
	private ActionModel actionModel;

	private EventHandler<MouseEvent> axisMouseMoved,
		gridMouseMoved;
	
	public Axis3DGridCtl(Axis3DView view, ActionModel model) {
		this.view = view;
		this.view.getXZGrid().visibleProperty().set(false);
		this.view.getYZGrid().visibleProperty().set(false);
		actionModel = model;
		
		axisMouseMoved = e->{
			if(actionModel.getAxis3DAction() == Axis3DAction.AddPoint) {
				this.view.getPreviewPoint().setVisible(true);
				this.view.getPreviewPoint().setTranslateX(Point3DUtil.toDouble(e.getX()));
				this.view.getPreviewPoint().setTranslateY(Point3DUtil.toDouble(e.getY()));
				this.view.getPreviewPoint().setTranslateZ(Point3DUtil.toDouble(e.getZ()));
			}else {
				this.view.getPreviewPoint().setVisible(false);
			}
		};
		
		view.getAxisX().setOnMouseMoved(axisMouseMoved);
		view.getAxisY().setOnMouseMoved(axisMouseMoved);
		view.getAxisZ().setOnMouseMoved(axisMouseMoved);
		
		gridMouseMoved = e->{
			if(actionModel.getAxis3DAction() == Axis3DAction.AddPoint) {
				this.view.getPreviewPoint().setVisible(true);
				Box box = (Box) e.getSource();
				double x = box.getTranslateX(),
					y = box.getTranslateY(),
					z = box.getTranslateZ();
				this.view.getPreviewPoint().setTranslateX(Point3DUtil.toDouble(x+e.getX()));
				this.view.getPreviewPoint().setTranslateY(Point3DUtil.toDouble(y+e.getY()));
				this.view.getPreviewPoint().setTranslateZ(Point3DUtil.toDouble(z+e.getZ()));
			}else {
				this.view.getPreviewPoint().setVisible(false);
			}
		};
	}
	
	public BooleanProperty axisXVisibleProperty() {
		return view.getAxisX().visibleProperty();
	}
	public BooleanProperty axisYVisibleProperty() {
		return view.getAxisY().visibleProperty();
	}
	public BooleanProperty axisZVisibleProperty() {
		return view.getAxisZ().visibleProperty();
	}
	
	public BooleanProperty xyGridVisibleProperty() {
		return view.getXYGrid().visibleProperty();
	}
	public BooleanProperty xzGridVisibleProperty() {
		return view.getXZGrid().visibleProperty();
	}
	public BooleanProperty yzGridVisibleProperty() {
		return view.getYZGrid().visibleProperty();
	}
	
	public void createGrid() {
		double gridSize = GRID_SIZE;
		int len = (int) (AXIS_LENGTH/2/gridSize);
		for(int i=0;i<=len;i++) {
			final Box[] xs = new Box[4],
					ys = new Box[4], zs = new Box[4];
			for(int j=0;j<4;j++) {
				xs[j] = new Box(AXIS_LENGTH, AXIS_GRID_WIDTH, AXIS_GRID_WIDTH);
				ys[j] = new Box(AXIS_GRID_WIDTH,AXIS_LENGTH, AXIS_GRID_WIDTH);
				zs[j] = new Box(AXIS_GRID_WIDTH, AXIS_GRID_WIDTH,AXIS_LENGTH);
				xs[j].setMaterial(GRID_MATERIAL);
				ys[j].setMaterial(GRID_MATERIAL);
				zs[j].setMaterial(GRID_MATERIAL);
				
				xs[j].setOnMouseMoved(gridMouseMoved);
				ys[j].setOnMouseMoved(gridMouseMoved);
				zs[j].setOnMouseMoved(gridMouseMoved);
			}
			xs[0].setTranslateY(gridSize*i);
			xs[1].setTranslateY(-gridSize * i);
			ys[0].setTranslateX(gridSize*i);
			ys[1].setTranslateX(-gridSize*i);
			
			xs[2].setTranslateZ(gridSize * i);
			xs[3].setTranslateZ(-gridSize*i);
			zs[0].setTranslateX(gridSize*i);
			zs[1].setTranslateX(-gridSize*i);
			
			
			ys[2].setTranslateZ(gridSize*i);
			ys[3].setTranslateZ(-gridSize*i);
			zs[2].setTranslateY(gridSize*i);
			zs[3].setTranslateY(-gridSize*i);
			
			view.getXYGrid().getChildren().addAll(xs[0], xs[1], ys[0], ys[1]);
			view.getXZGrid().getChildren().addAll(xs[2], xs[3], zs[0], zs[1]);
			view.getYZGrid().getChildren().addAll(ys[2], ys[3], zs[2], zs[3]);
		}
	}
	
}
