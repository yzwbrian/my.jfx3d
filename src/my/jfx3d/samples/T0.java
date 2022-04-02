package my.jfx3d.samples;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import my.jfx3d.base.POVConstants;
import my.jfx3d.controller.POVCtl;
import my.jfx3d.old.Axis3DController;

public class T0 extends Application {
	public static void main(String[] args) {
		System.out.println(Math.max(-1, -2));
		launch(args);
	}
	
	private final Robot robot = new Robot();
	
	double mousePosX, mousePosY,
	mouseOldX, mouseOldY,
	mouseOX, mouseOY;

	@Override
	public void start(Stage mainStage) throws Exception {
		String appCss = getClass().getResource("app.css").toExternalForm();
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 800, 600, true, SceneAntialiasing.BALANCED);
		scene.getStylesheets().add(appCss);
		
		mainStage.setScene(scene);
		mainStage.show();
		
		VBox leftPane = new VBox();
		leftPane.getStyleClass().add("left-pane");
		
		VBox labelsPane = new VBox();
		Label lx = new Label(), ly = new Label(), lz = new Label();
		lx.getStyleClass().add("l3d-x");
		ly.getStyleClass().add("l3d-y");
		lz.getStyleClass().add("l3d-z");
		labelsPane.getChildren().addAll(lx,ly,lz);
		
		leftPane.getChildren().add(labelsPane);
		
		POVCtl pc = new POVCtl();
		
		SubScene povScene = pc.getScene();
		povScene.setTranslateX(5);
		povScene.setTranslateY(5);
		povScene.setFill(Color.rgb(101, 101, 101, 0.5));
		Circle circle = new Circle(POVConstants.VIEW_WIDTH/2);
		circle.setTranslateX(circle.getRadius());
		circle.setTranslateY(circle.getRadius());
		povScene.setClip(circle);
		
		povScene.setOnMouseEntered(e->{
			povScene.setFill(Color.rgb(101, 101, 101));
		});
		povScene.setOnMouseExited(e->{
			if(povScene.isPressed())
				povScene.setFill(Color.rgb(101, 101, 101));
			else
				povScene.setFill(Color.rgb(101, 101, 101, 0.5));
		});
		
		lx.textProperty().bind(new SimpleStringProperty("x: ")
				.concat(pc.rotateXProperty().asString()));
		ly.textProperty().bind(new SimpleStringProperty("y: ")
				.concat(pc.rotateYProperty().asString()));
		lz.textProperty().bind(new SimpleStringProperty("z: ")
				.concat(pc.rotateZProperty().asString()));
		
		leftPane.getChildren().add(povScene);
		root.setLeft(leftPane);
		
		VBox vbox = new VBox();
		vbox.getStyleClass().add("lbl-vbox");
		vbox.setSpacing(4);
		for(int i=0;i<10;i++) {
			Label label = new Label(i+"");
			label.getStyleClass().add("lbl");
			vbox.getChildren().add(label);
		}
		ScrollPane vsp = new ScrollPane(vbox);
		vsp.getStyleClass().add("vsp-pane");
		vsp.setPrefHeight(100);
		vsp.setMaxHeight(100);
		vsp.setLayoutX(24);
		vsp.setLayoutY(10);
		vsp.setFocusTraversable(false);
		vsp.setTranslateX(28);
		vsp.setTranslateY(10);
		
		Rectangle rect = new Rectangle(28, 100);
		vsp.setClip(rect);
		
		//leftPane.getChildren().add(vsp);
		
		ToolBar tb = new ToolBar();
		tb.getStyleClass().add("my-toolbar");
		tb.setOrientation(Orientation.VERTICAL);
		tb.setTranslateX(24);
		tb.setTranslateY(10);
		
		Button btnScale = new Button("Ëõ·Å");
		btnScale.getStyleClass().add("toolbar-button");
		btnScale.setFocusTraversable(false);
		
		Button btnMove = new Button("ÒÆ¶¯");
		btnMove.getStyleClass().add("toolbar-button");
		btnScale.setFocusTraversable(false);
		
		tb.getItems().addAll(btnScale, btnMove);
		leftPane.getChildren().add(tb);
		tb.prefHeightProperty().bind(root.heightProperty().subtract(150));
		/*
		mainStage.titleProperty().bind(
				vsp.vvalueProperty().isEqualTo(0).asString());
		*/
		
		AnchorPane centerPane = new AnchorPane();
		centerPane.getStyleClass().add("center-pane");
		root.setCenter(centerPane);
		
		//
		
		Axis3DController a3d = new Axis3DController();
		SubScene axis3dScene = a3d.getScene();
		centerPane.getChildren().add(axis3dScene);
		AnchorPane.setLeftAnchor(axis3dScene, 0.);
		AnchorPane.setRightAnchor(axis3dScene, 0.);
		AnchorPane.setTopAnchor(axis3dScene, 0.);
		AnchorPane.setBottomAnchor(axis3dScene, 0.);
		
		axis3dScene.widthProperty().bind(root.widthProperty().subtract(leftPane.getMaxWidth()));
		axis3dScene.heightProperty().bind(root.heightProperty().subtract(leftPane.getMaxHeight()));
		a3d.getRxProperty().bind(pc.rotateXProperty());
		a3d.getRyProperty().bind(pc.rotateYProperty());
		a3d.getRzProperty().bind(pc.rotateZProperty());
		
		//
		btnScale.setOnScroll(e->{
			double value = e.getDeltaY() * 0.1;
			if(e.isControlDown())
				value = value * 5.0;
			a3d.Scale(value);
		});
		
		btnMove.setOnMousePressed(e->{
			mousePosX = e.getSceneX();
			mousePosY = e.getSceneY();
			mouseOldX = e.getSceneX();
			mouseOldY = e.getSceneY();
			mouseOX = e.getScreenX();
			mouseOY = e.getScreenY();
		});
		btnMove.setOnMouseDragged(e->{
			btnMove.setCursor(Cursor.NONE);
			mouseOldX = mousePosX;
			mouseOldY = mousePosY;
			mousePosX = e.getSceneX();
			mousePosY = e.getSceneY();
			double mouseDeltaX = (mousePosX - mouseOldX) * 0.1;
			double mouseDeltaY = (mousePosY - mouseOldY) * 0.1;
			
			if(e.isControlDown()) {
				mouseDeltaX *= 5;
				mouseDeltaY *= 5;
			}
			a3d.move(mouseDeltaX, true);
			a3d.move(mouseDeltaY, false);
		});
		btnMove.setOnMouseReleased(e->{
			robot.mouseMove(mouseOX, mouseOY);
			btnMove.setCursor(Cursor.DEFAULT);
		});
				
	}

}
