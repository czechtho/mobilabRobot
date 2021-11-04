package mobilabRobot;

import robocode.*;
import java.awt.Color;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Lucky13 - a robot by (your name here)
 */
public class Lucky13 extends AdvancedRobot {

	int enemy_robots; // Number of other robots in the game
	int drive_distance_after_hit = 70;
	//static int corner = 0; // 0,1,2,3
	//double gun_turn_deg = 13;
//	int cnt_gun_turns;
	//String target_name = "";
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
		
		//get the bearing of the bullet for counter attack
		//turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
		//move forward
		ahead(dist);
		//next move back
		dist *= -1;
		
		scan();
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
	
	public void onHitRobot(HitRobotEvent e) {
		//ram and fire hard
		double turn_gun_deg = (e.getBearing() + getHeading() - getGunHeading())%360;

		turnGunRight(turn_gun_deg);
		fire(3);
		
       if (e.getBearing() > -90 && e.getBearing() <= 90) {
           back(50);
       } else {
           ahead(100);
       }
	   
   }
}
