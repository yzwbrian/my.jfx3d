package my.jfx3d.old;

import static my.jfx3d.base.Axis3DConstants.AXISXCOLOR;
import static my.jfx3d.base.Axis3DConstants.AXISYCOLOR;
import static my.jfx3d.base.Axis3DConstants.AXISZCOLOR;
import static my.jfx3d.base.Axis3DConstants.AXIS_GRID_WIDTH;
import static my.jfx3d.base.Axis3DConstants.AXIS_LENGTH;
import static my.jfx3d.base.Axis3DConstants.AXIS_WIDTH;
import static my.jfx3d.base.Axis3DConstants.CAMERA_FAR_CLIP;
import static my.jfx3d.base.Axis3DConstants.CAMERA_INITIAL_DISTANCE;
import static my.jfx3d.base.Axis3DConstants.CAMERA_INITIAL_X_ANGLE;
import static my.jfx3d.base.Axis3DConstants.CAMERA_INITIAL_Y_ANGLE;
import static my.jfx3d.base.Axis3DConstants.CAMERA_INITIAL_Z_ANGLE;
import static my.jfx3d.base.Axis3DConstants.CAMERA_NEAR_CLIP;
import static my.jfx3d.base.Axis3DConstants.DEFAULT_VIEW_HEIGHT;
import static my.jfx3d.base.Axis3DConstants.DEFAULT_VIEW_WIDTH;
import static my.jfx3d.base.Axis3DConstants.FACEMATERIAL;
import static my.jfx3d.base.Axis3DConstants.GRID_MATERIAL;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import my.jfx3d.base.Xform;
import my.jfx3d.model.Point;
import my.jfx3d.view.MyRectangle;

public class Axis3DController {
	
	private final Xform cameraXform = new Xform();
	private final PerspectiveCamera camera = new PerspectiveCamera(true);
	private final Xform axis3DXform = new Xform();
	private final Group root = new Group();
	private final Xform lightXform = new Xform();
	private final Xform otherXform = new Xform();
	
	private final Xform pointsXform = new Xform(),
			trianglesXform = new Xform();
	
	
	private final Xform faceXform = new Xform();
	private final Xform xyFaceXform = new Xform(),
			xzFaceXform = new Xform(),
			yzFaceXform = new Xform();
	
	public BooleanProperty xyFaceVisibleProperty() {
		return xyFaceXform.visibleProperty();
	}
	public BooleanProperty xzFaceVisibleProperty() {
		return xzFaceXform.visibleProperty();
	}
	public BooleanProperty yzFaceVisibleProperty() {
		return yzFaceXform.visibleProperty();
	}
	
	public Xform getXYFace() {
		return xyFaceXform;
	}
	public Xform getXZFace() {
		return xzFaceXform;
	}
	public Xform getYZFace() {
		return yzFaceXform;
	}
	
	private final Xform axisXYGridXform = new Xform(), 
			axisXZGridXform = new Xform(),
			axisYZGridXform = new Xform();
	
	public Xform XYGrid() {
		return axisXYGridXform;
	}
	public Xform XZGrid() {
		return axisXZGridXform;
	}
	public Xform YZGrid() {
		return axisYZGridXform;
	}
	
	public BooleanProperty axisXYGridVisibleProperty() {
		return axisXYGridXform.visibleProperty();
	}
	public BooleanProperty axisXZGridVisibleProperty() {
		return axisXZGridXform.visibleProperty();
	}
	public BooleanProperty axisYZGridVisibleProperty() {
		return axisYZGridXform.visibleProperty();
	}
	
	private final Box axisX = new Box(AXIS_LENGTH, AXIS_WIDTH, AXIS_WIDTH),
			axisY = new Box(AXIS_WIDTH, AXIS_LENGTH, AXIS_WIDTH),
			axisZ = new Box(AXIS_WIDTH, AXIS_WIDTH, AXIS_LENGTH);
	
	public BooleanProperty axisXVisibleProperty() {
		return axisX.visibleProperty();
	}
	public BooleanProperty axisYVisibleProperty() {
		return axisY.visibleProperty();
	}
	public BooleanProperty axisZVisibleProperty() {
		return axisZ.visibleProperty();
	}
	
	private final DoubleProperty gridSizeProperty = new SimpleDoubleProperty(5);
	public DoubleProperty gridSizeProperty() {
		return gridSizeProperty;
	}
	
	private final SubScene subScene;
	
	public Axis3DController() {
		subScene = createSubScene();
		//axisXYGridXform.setVisible(false);
		axisXZGridXform.setVisible(false);
		axisYZGridXform.setVisible(false);
		
		//xyFaceXform.setVisible(false);
		xzFaceXform.setVisible(false);
		yzFaceXform.setVisible(false);
	}
	public SubScene getScene() {
		return subScene;
	}
	public DoubleProperty getRxProperty() {
		return cameraXform.rx.angleProperty();
	}
	public DoubleProperty getRyProperty() {
		return cameraXform.ry.angleProperty();
	}
	public DoubleProperty getRzProperty() {
		return cameraXform.rz.angleProperty();
	}
	
	public Xform getOtherXform() {
		return otherXform;
	}
	
	public Xform getPointsXform() {
		return pointsXform;
	}
	
	public Xform getTrianglesXform() {
		return trianglesXform;
	}
	
	private AmbientLight light = new AmbientLight(Color.WHITE);
	
	public void addLight() {
		if(!lightXform.getChildren().contains(light))
			lightXform.getChildren().add(light);
		System.out.println("lightOn:"+lightXform.getChildren().size());
	}
	public void removeLight() {
		if(lightXform.getChildren().contains(light))
			lightXform.getChildren().remove(light);
		System.out.println("lightOff:"+lightXform.getChildren().size());
	}
	
	private SubScene createSubScene() {
		camera.setNearClip(CAMERA_NEAR_CLIP);
		camera.setFarClip(CAMERA_FAR_CLIP);
		camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
		
		cameraXform.setRx(CAMERA_INITIAL_X_ANGLE);
		cameraXform.setRy(CAMERA_INITIAL_Y_ANGLE);
		cameraXform.setRz(CAMERA_INITIAL_Z_ANGLE);

		final PhongMaterial pmSx = new PhongMaterial(AXISXCOLOR),
				pmSy = new PhongMaterial(AXISYCOLOR),
				pmSz = new PhongMaterial(AXISZCOLOR);
		
		axisX.setMaterial(pmSx);
		axisY.setMaterial(pmSy);
		axisZ.setMaterial(pmSz);

		cameraXform.getChildren().add(camera);
		axis3DXform.getChildren().addAll(axisX, axisY, axisZ);
		
		faceXform.getChildren().addAll(xyFaceXform, xzFaceXform, yzFaceXform);
		lightXform.getChildren().add(light);
		root.getChildren().addAll(
				cameraXform,
				faceXform,
				axisXYGridXform, axisXZGridXform, axisYZGridXform, 
				axis3DXform,
				trianglesXform,
				pointsXform,
				otherXform, 
				lightXform);
		
		SubScene subScene = new SubScene(root, DEFAULT_VIEW_WIDTH, DEFAULT_VIEW_HEIGHT, true, SceneAntialiasing.BALANCED);
		subScene.setCamera(camera);
		
		createFace();
		createGrid();
		
		return subScene;
	}
	
	private void createFace() {
		double len = AXIS_LENGTH/2;
		
		MyRectangle xyRect = new MyRectangle(
			new Point(-len, len, 0),
			new Point(len,len,0),
			new Point(-len,-len,0),
			new Point(len,-len,0));
		xyRect.setMaterial(FACEMATERIAL);
		xyFaceXform.getChildren().add(xyRect);
		
		MyRectangle xzRect = new MyRectangle(
				new Point(-len, 0, len),
				new Point(len,0, len),
				new Point(-len,0,-len),
				new Point(len,0,-len));
		xzRect.setMaterial(FACEMATERIAL);
		xzFaceXform.getChildren().add(xzRect);
		
		MyRectangle yzRect = new MyRectangle(
				new Point(0,-len, len),
				new Point(0,len,len),
				new Point(0,-len,-len),
				new Point(0,len,-len));
		yzRect.setMaterial(FACEMATERIAL);
		yzFaceXform.getChildren().add(yzRect);
	}
	
	private void createGrid() {
		double gridSize = gridSizeProperty.doubleValue();
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
			
			axisXYGridXform.getChildren().addAll(xs[0], xs[1], ys[0], ys[1]);
			axisXZGridXform.getChildren().addAll(xs[2], xs[3], zs[0], zs[1]);
			axisYZGridXform.getChildren().addAll(ys[2], ys[3], zs[2], zs[3]);
		}
	}
	
	public void resetScale() {
		camera.translateZProperty().setValue(CAMERA_INITIAL_DISTANCE);
	}
	
	public void Scale(double value) {
		value += camera.translateZProperty().get();
		camera.translateZProperty().setValue(value);
	}
	
	public void resetMove() {
		cameraXform.t.xProperty().set(0.);
		cameraXform.t.yProperty().set(0.);
	}
	
	public void move(double value, boolean isAxisX) {
		value += isAxisX ? cameraXform.t.xProperty().get()
				: cameraXform.t.yProperty().get();
		if(isAxisX)
			cameraXform.t.xProperty().setValue(value);
		else
			cameraXform.t.yProperty().setValue(value);
	}
}
