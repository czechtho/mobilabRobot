package mobilabRobot;

import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Lucky13 - a robot by (your name here)
 */
public class Lucky13 extends AdvancedRobot {

	int enemy_robots; // Number of other robots in the game
	static int corner = 0; // 0,1,2,3
	double gun_turn_deg = 13;
	int cnt_gun_turns;
	String target_name = "";
	/**
	 * run: Lucky13's default behavior
	 */
	public void run() {

		setColors(Color.blue,Color.red,Color.blue); // body,gun,radar
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			enemy_robots = getOthers();
			
			//spinn phase
			while(enemy_robots >= 6) {
				setTurnRight(700);
				// Start moving (and turning)
				setAhead(100);
				execute();
			}
			
			//target phase
			while (true) {
				turnGunRight(gun_turn_deg);
				cnt_gun_turns++;
				// after 2 turns look left
				if (cnt_gun_turns > 2) 
					gun_turn_deg = -13;
				if (cnt_gun_turns > 5)
					gun_turn_deg = 13;
				if (cnt_gun_turns > 11)
					target_name = null;
			}

		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		if(e.getDistance() < 50)
			fire(2);
		else if(e.getDistance() < 100)
			fire(1.5);
		else
			fire(1);
	
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
