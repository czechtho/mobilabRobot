package mobilabRobot;

import robocode.*;
import java.awt.Color;

/**
 * Lucky13 - a robot by (your name here)
 */
public class Lucky13 extends AdvancedRobot {
	int forward = 0;
	double startEnergy = getEnergy();
	int enemy_robots; // Number of other robots in the game
	int drive_distance_after_hit = 70;
	double gun_turn_deg = 13;
	int cnt_gun_turns = 1;
	/**
	 * run: Lucky13's default behavior
	 */
	public void run() {

		setColors(Color.red,Color.red,Color.red); // body,gun,radar
		

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			enemy_robots = getOthers();
			
			//spinn phase
			while(enemy_robots >= 5 && getEnergy() > startEnergy/2) {
				setTurnRight(700);
				// Start moving (and turning)
				setAhead(100);
				forward = 1;
				execute();
			}
			
			//target phase
			while (true) {
				turnGunRight(gun_turn_deg);
				cnt_gun_turns++;
				// after 2 turns look leftforward = 0;
				if (cnt_gun_turns > 2) 
					gun_turn_deg = -13;
				if (cnt_gun_turns > 5)
					gun_turn_deg = 13;
				if (cnt_gun_turns > 11)
					cnt_gun_turns = 1;
			}

		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
	
		if(e.getDistance() < 50 && getEnergy() > 50)	// close robot
			fire(3);
		else if(e.getDistance() < 100 && getOthers() > 6 && getEnergy() > 20)	// lot of targets
			fire(1.5);
		else
			fire(1);
			
		scan();//scan after stop moving.
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		
	
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		if (forward == 1) // wenn wir hier hineinspringen, haben wir jetzt die
		// Information, was wir zuletzt gemacht haben.
			back(150);
		else
			ahead(150);
		

		setTurnRight(90);
  		setBack(100);
		execute();
		//move away from x/y point
	}	
	
	public void onHitRobot(HitRobotEvent e) {
		//ram and fire hard
		
       if (e.getBearing() > -90 && e.getBearing() <= 90) {
           back(50);
       } else {
           ahead(100);
       }
	   
   }
}
