package my.jfx3d.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import my.jfx3d.base.Xform;
import my.jfx3d.view.MyPoint;

public class PointModel {

	private MyPoint selectedPoint;
	private final ObservableList<Point> points = FXCollections.observableArrayList();
	
	public PointModel(Xform pointsXform) {
		pointsXform.getChildren().addListener((ListChangeListener<Node>)e->{
			while(e.next()) {
				if(e.wasAdded()) {
					for(Node node: e.getAddedSubList()) {
						MyPoint mp = (MyPoint) node;
						points.add(mp.getPoint());
					}
				}
				if(e.wasRemoved()) {
					for(Node node: e.getRemoved()) {
						MyPoint mp = (MyPoint) node;
						points.remove(mp.getPoint());
					}
				}
			}
		});
	}

	public MyPoint getSelectedPoint() {
		return selectedPoint;
	}

	public void setSelectedPoint(MyPoint selectedPoint) {
		if(this.selectedPoint != null)
			this.selectedPoint.setMaterial(MyPoint.MATERIAL);
		this.selectedPoint = selectedPoint;
		if(this.selectedPoint != null)
			this.selectedPoint.setMaterial(MyPoint.SELECTED_MATERIAL);
	}
	
	public ObservableList<Point> points(){ return points; }
	
}
