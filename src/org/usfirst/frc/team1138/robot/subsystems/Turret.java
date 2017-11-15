package org.usfirst.frc.team1138.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1138.robot.RobotMap;
import com.ctre.CANTalon;
/**
 *
 */
public class Turret extends Subsystem {

	private CANTalon turretMotor;
	
	public Turret() {
		//Setup the motor
		turretMotor = new CANTalon(RobotMap.KTurretTalon);
		//Configure and enable
		turretMotor.setSafetyEnabled(true);
		turretMotor.enableControl();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

