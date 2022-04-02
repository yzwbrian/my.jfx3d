package my.jfx3d.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import my.jfx3d.base.TriangleUtil;
import my.jfx3d.base.Xform;
import my.jfx3d.model.ActionModel;
import my.jfx3d.model.Axis3DAction;
import my.jfx3d.model.TriangleModel;
import my.jfx3d.view.MyTriangle;

public class Axis3DTriangleCtl {

	private ActionModel actionModel;
	private Xform triangleXform;
	private final TriangleModel triangleModel = new TriangleModel();
	private final EventHandler<MouseEvent> trianglePressed;
	public static final PhongMaterial MS[] = {
		new PhongMaterial(Color.RED), new PhongMaterial(Color.YELLOW), new PhongMaterial(Color.BLUE),
		new PhongMaterial(Color.GREEN), new PhongMaterial(Color.CHARTREUSE),
		new PhongMaterial(Color.DARKGOLDENROD), new PhongMaterial(Color.BLUEVIOLET)
	};
	
	public Axis3DTriangleCtl(Xform xform, ActionModel model) {
		actionModel = model;
		triangleXform = xform;
		
		if(actionModel.getAxis3DAction() == Axis3DAction.Select) {
			
		}else if(actionModel.getAxis3DAction() == Axis3DAction.Delete) {
			
		}
		
		triangleModel.pointSizeProperty().addListener((e, o, n)->{
			if(n.intValue() == 3) {
				addTriangle(triangleModel.createTriangle());
				triangleModel.clear();
			}
		});
		
		trianglePressed = e->{
			if(actionModel.getAxis3DAction() == Axis3DAction.ChangeTriangleFace) {
				MyTriangle mt = (MyTriangle) e.getSource();
				System.out.println("ccw-1:"+TriangleUtil.ccw(mt.getA().getPoint(), 
						mt.getB().getPoint(),
						mt.getC().getPoint()));
				mt.swapFaces();
				System.out.println("ccw-2:"+TriangleUtil.ccw(mt.getA().getPoint(), 
						mt.getB().getPoint(),
						mt.getC().getPoint()));
				
			}
			
		};
	}
	
	public void addTriangle(MyTriangle mt) {
		mt.setOnMousePressed(trianglePressed);
		triangleXform.getChildren().add(mt);
	}
	
	public void removeTriangle(MyTriangle mt) {
		mt.setOnMouseClicked(null);
		triangleXform.getChildren().remove(mt);
	}
	
	public TriangleModel getTriangleModel() { return triangleModel; }
	
}
