package my.jfx3d.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import my.jfx3d.base.Xform;
import my.jfx3d.model.ActionModel;
import my.jfx3d.model.Axis3DAction;
import my.jfx3d.model.PointModel;
import my.jfx3d.model.TriangleModel;
import my.jfx3d.view.MyPoint;

public class Axis3DPointsCtl {
	public static final int MAX_POINT_SIZE = 4096;
	
	private Xform pointsXform;
	private final PointModel pointModel;
	private ObservableList<Node> points;
	
	private ActionModel actionModel;
	private TriangleModel triangleModel;
	
	private EventHandler<MouseEvent> handlePointMouseClicked;
	
	public Axis3DPointsCtl(Xform xform, ActionModel actionModel, TriangleModel triangleModel) {
		
		pointsXform = xform;
		points = pointsXform.getChildren();
		pointModel = new PointModel(pointsXform);
		this.actionModel = actionModel;
		this.triangleModel = triangleModel;
		pointsXform.getChildren().addListener((ListChangeListener<Node>)e -> {
			while(e.next()){
				if(e.wasAdded() && e.getAddedSize() == 1) {
					for(Node node: e.getAddedSubList()) {
						MyPoint mp = (MyPoint) node;
						mp.setIndex(e.getFrom());
						//System.out.println("added-index:"+mp.getIndex());
					}
				}
			}
		});
		handlePointMouseClicked = e->{
			MyPoint p = (MyPoint) e.getSource();
			if(p.isDeleted() || !p.isVisible())
				return;
			
			if(this.actionModel.getAxis3DAction() == Axis3DAction.MovePointX) {
				select(p);
			}else if(this.actionModel.getAxis3DAction() == Axis3DAction.MovePointY) {
				select(p);
			}else if(this.actionModel.getAxis3DAction() == Axis3DAction.MovePointZ) {
				select(p);
			}else if(this.actionModel.getAxis3DAction() == Axis3DAction.Delete) {
				select(p);
				remove(p);
				clearSelect();
			}else if(this.actionModel.getAxis3DAction() == Axis3DAction.AddTriangle) {
				clearSelect();
				this.triangleModel.add(p);
			}
		};
	}
	
	public PointModel getPointModel() { return pointModel; }
	
	public ObservableList<Node> points(){ return points; }
	
	public MyPoint get(int index) {
		return (MyPoint) points.get(index);
	}
	
	public boolean add(MyPoint point) {
		if(points.size() > MAX_POINT_SIZE)
			throw new IndexOutOfBoundsException("PointModel.MAX_POINT_SIZE="+MAX_POINT_SIZE);
		point.setOnMouseClicked(handlePointMouseClicked);
		return points.add(point);
	}
	
	public MyPoint remove(int index) {
		MyPoint p = (MyPoint) points.get(index);
		remove(p);
		return p;
	}
	
	public void remove(MyPoint point) {
		point.setDeleted(true);
		point.setVisible(false);
		point.setOnMouseClicked(null);
	}
	
	public void removeAll(MyPoint... point) {
		for(MyPoint p: point)
			remove(p);
	}
	
	public void clear() {
		for(Node p: points)
			remove((MyPoint)p);
		points.clear();
	}
	
	public void select(MyPoint point) {
		pointModel.setSelectedPoint(point);
	}
	public MyPoint getSelected() { return pointModel.getSelectedPoint(); }
	
	public void clearSelect() { pointModel.setSelectedPoint(null); }
	
	
}
