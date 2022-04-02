package my.jfx3d.base;

import javafx.scene.robot.Robot;

public class MyRobot {

	private static Robot robot = new Robot();
	
	public static Robot get() {
		return robot;
	}
	
}
