package frc.team1138.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1138.robot.RobotMap;
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
}

