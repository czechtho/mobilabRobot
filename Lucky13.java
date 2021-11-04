package mobilabRobot;

import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Lucky13 - a robot by (your name here)
 */
public class Lucky13 extends AdvancedRobot
{
	/**
	 * run: Lucky13's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		 setColors(Color.blue,Color.red,Color.blue); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(150);
			turnGunRight(360);	//also turns the radar right!
			turnRight(45);
			turnGunRight(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		if(e.getDistance() >= 800 && e.getDistance() <= 848.5)	//possible corner camper
			fire(3);
		else if(e.getDistance() < 50)
			//fire(3);
			if(getEnergy() > 90)
				fire(3);
			else
				fire(1);
		else if(e.getDistance() < 100)
			fire(2);
		else if(e.getDistance() < 200)
			fire(1);
		else if(e.getDistance() < 300)
			fire(1);
		else
			fire(0.5);
	
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		turnRight(45);
		setBack(100);
		
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		setTurnRight(90);
  		setBack(100);
		execute();
		//move away from x/y point
	}	
	
	public void onHitRobot(HitRobotEvent event) {
       if (event.getBearing() > -90 && event.getBearing() <= 90) {
           back(100);
       } else {
           ahead(100);
       }
   }
}
