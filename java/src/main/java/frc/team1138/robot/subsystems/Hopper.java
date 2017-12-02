package frc.team1138.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1138.robot.RobotMap;
import com.ctre.CANTalon;

/**
 *
 */
public class Hopper extends Subsystem {

	//Setup the hopper configuration
	public static final int KHopperTalon = 8;
	
	private CANTalon hopperMotor;
	
	public Hopper() {
		//Setup the motor
		hopperMotor = new CANTalon(KHopperTalon);
		//Configure and enable
		hopperMotor.setSafetyEnabled(true);
		hopperMotor.enableControl();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

