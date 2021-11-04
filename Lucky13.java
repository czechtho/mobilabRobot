package mobilabRobot;

import robocode.*;

public class Lucky13 extends AdvancedRobot  {
	double space = 42;
	/**
	 * run: Lucky13's default behavior
	 */
	public void run() {
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			setTurnGunRight(13);
			setAhead(20);
			execute();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
	
		double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
		setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians()));
			
		if(e.getEnergy() < 16 && e.getDistance() < 150) {
			fire(3);
			return;
		}
	
		if(e.getDistance() < 50 && getEnergy() > 25)	// close robot
			fire(3);
		else if(e.getDistance() < 150)	// lot of targets
			fire(1.5);
		else
			fire(1);
			
		scan();//scan after stop moving.
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		//get bearing of bullet and scan -> fire back
		turnRight(robocode.util.Utils.normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
		ahead(space);
		space *= -1;
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
		//enemy close calculate direction of hit
		setTurnGunRight(robocode.util.Utils.normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading()));
		fire(3);
		//ram
      setTurnRight(e.getBearing());
           setAhead(100);
       
	   execute();
	   
   }
}