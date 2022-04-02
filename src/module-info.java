module my.jfx3d {
	requires javafx.graphics;
	requires javafx.fxml;
	
	requires javafx.controls;
	requires javafx.base;
	requires java.desktop;
	requires jimObjModelImporterJFX;
	opens my.jfx3d.samples to javafx.graphics, javafx.fxml;
}