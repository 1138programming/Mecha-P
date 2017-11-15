package org.usfirst.frc.team1138.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1138.robot.RobotMap;
import com.ctre.CANTalon;

/**
 *
 */
public class Flywheel extends Subsystem {

    private CANTalon topFlywheelMotor,
    				 bottomFlywheelMotor,
    				 angleAdjusterFlywheelMotor,
    				 indexerFlywheelMotor;
    public Flywheel()
    {
    	//Setup the motors
    	topFlywheelMotor = new CANTalon(RobotMap.KFlywheelTopTalon);
    	bottomFlywheelMotor = new CANTalon(RobotMap.KFlywheelBottomTalon);
    	angleAdjusterFlywheelMotor = new CANTalon(RobotMap.KFlywheelAngleAdjusterTalon);
    	indexerFlywheelMotor = new CANTalon(RobotMap.KFlywheelIndexTalon);
    	
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

