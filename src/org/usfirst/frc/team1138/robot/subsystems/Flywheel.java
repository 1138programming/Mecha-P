package org.usfirst.frc.team1138.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1138.robot.RobotMap;
import com.ctre.CANTalon;

/**
 *
 */
public class Flywheel extends Subsystem {

	//Setup the flywheel configuration
	public static final int KFlywheelIndexTalon = 9;
	public static final int KFlywheelAngleAdjusterTalon = 10;
	public static final int KFlywheelBottomTalon = 11;
	public static final int KFlywheelTopTalon = 12;
	
	//For now, these speeds are unified for all the motors in the subsystem until they need to be separate speeds.
	public static final double fullSpeed = 0.8;
	public static final double mediumSpeed = 0.5;
	public static final double lowSpeed = 0.3;
	public static final int stop = 0;
	
    private CANTalon topFlywheelMotor,
    				 bottomFlywheelMotor,
    				 angleAdjusterFlywheelMotor,
    				 indexerFlywheelMotor;
    public Flywheel()
    {
    	//Setup the motors
    	topFlywheelMotor = new CANTalon(KFlywheelTopTalon);
    	bottomFlywheelMotor = new CANTalon(KFlywheelBottomTalon);
    	angleAdjusterFlywheelMotor = new CANTalon(KFlywheelAngleAdjusterTalon);
    	indexerFlywheelMotor = new CANTalon(KFlywheelIndexTalon);
    	
    	//Configure masters and enable
    	topFlywheelMotor.setSafetyEnabled(true);
		bottomFlywheelMotor.setSafetyEnabled(true);
		angleAdjusterFlywheelMotor.setSafetyEnabled(true);
		indexerFlywheelMotor.setSafetyEnabled(true);
    	topFlywheelMotor.enableControl();
    	angleAdjusterFlywheelMotor.enableControl();
    	indexerFlywheelMotor.enableControl();
    	
    	//Configure the bottom motor as a slave
    	bottomFlywheelMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
    	bottomFlywheelMotor.set(topFlywheelMotor.getDeviceID());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void runShooter()
    {
    	topFlywheelMotor.set(fullSpeed);
    	bottomFlywheelMotor.set(fullSpeed);
    }
    
    public void runShooter(double speed)
    {
    	topFlywheelMotor.set(speed);
    	bottomFlywheelMotor.set(speed);
    }
    
    public void stopShooter()
    {
    	topFlywheelMotor.set(stop);
    	bottomFlywheelMotor.set(stop);
    }
    
    public void runAngleAdjuster()
    {
    	angleAdjusterFlywheelMotor.set(fullSpeed);
    }
    
    public void runAngleAdjuster(double speed)
    {
    	angleAdjusterFlywheelMotor.set(speed);
    }
    
    public void stopAngleAdjuster()
    {
    	angleAdjusterFlywheelMotor.set(stop);
    }
    
    public void runIndexer()
    {
    	indexerFlywheelMotor.set(fullSpeed);
    }
    
    public void runIndexer(double speed)
    {
    	indexerFlywheelMotor.set(speed);
    }
    
    public void stopIndexer()
    {
    	indexerFlywheelMotor.set(stop);
    }
}

