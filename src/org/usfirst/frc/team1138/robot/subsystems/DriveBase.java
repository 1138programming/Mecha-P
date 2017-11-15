package org.usfirst.frc.team1138.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1138.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.lang.Object ;

/**
 *This will be what the finished subsystem should look like when everything is included.
 *Most of this is simply borrowed from JavaMomentum until we know what sensors and solenoids
 *are where and such.
 */
public class DriveBase extends Subsystem {

	private CANTalon leftFrontBaseMotor,
					 rightFrontBaseMotor,
					 leftRearBaseMotor,
					 leftTopBaseMotor,
					 rightRearBaseMotor,
					 rightTopBaseMotor;
	private DoubleSolenoid shifterSolenoid /*liftSolenoid*/; //There will probably be a shift solenoid
//	private AHRS gyroAccel;
	public DriveBase() {
		// Motors
		// master motors
		leftFrontBaseMotor = new CANTalon(RobotMap.KLeftFrontBaseTalon);
		rightFrontBaseMotor = new CANTalon(RobotMap.KRightFrontBaseTalon);
		//slave motors 
		leftRearBaseMotor = new CANTalon(RobotMap.KLeftRearBaseTalon);
		leftTopBaseMotor = new CANTalon(RobotMap.KLeftTopBaseTalon);
		rightRearBaseMotor = new CANTalon(RobotMap.KRightRearBaseTalon);
		rightTopBaseMotor = new CANTalon(RobotMap.KRightTopBaseTalon);
		// Config the masters and enable
		leftFrontBaseMotor.setInverted(true);
		initSafeMotor();
		rightFrontBaseMotor.enableControl();
		leftFrontBaseMotor.enableControl();
		// Config the slaves
		leftRearBaseMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftTopBaseMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightRearBaseMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightTopBaseMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftRearBaseMotor.set(leftFrontBaseMotor.getDeviceID());
		leftTopBaseMotor.set(leftFrontBaseMotor.getDeviceID());
		rightRearBaseMotor.set(rightFrontBaseMotor.getDeviceID());
		rightTopBaseMotor.set(rightFrontBaseMotor.getDeviceID());
		
		// Solenoids 
		shifterSolenoid = new DoubleSolenoid(RobotMap.KShifterSolenoid1, RobotMap.KShifterSolenoid2);
//		liftSolenoid = new DoubleSolenoid(RobotMap.KLiftSolenoid1, RobotMap.KLiftSolenoid2); 
		
		//Gyro & Accel
//		gyroAccel = new AHRS(Port.kMXP);
//		gyroAccel.zeroYaw();
		
		//Encoders 
		leftFrontBaseMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		rightFrontBaseMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		leftFrontBaseMotor.configEncoderCodesPerRev(4095);
		rightFrontBaseMotor.configEncoderCodesPerRev(4095);
		leftFrontBaseMotor.setEncPosition(0);
		rightFrontBaseMotor.setEncPosition(0);
		
		// LiveWindow
//		LiveWindow.addSensor("SubDriveBase", "Gyro", gyroAccel);
//		LiveWindow.addActuator("SubDriveBase", "Left Front Motor", leftFrontBaseMotor);
//		LiveWindow.addActuator("SubDriveBase", "Right Front Motor", rightFrontBaseMotor);
//		LiveWindow.addActuator("SubDriveBase", "Left Rear Motor", leftRearBaseMotor);
//		LiveWindow.addActuator("SubDriveBase", "Right Rear Motor", rightRearBaseMotor);
		}

	private void initSafeMotor() {
		leftFrontBaseMotor.setSafetyEnabled(true);
		rightFrontBaseMotor.setSafetyEnabled(true);
		leftRearBaseMotor.setSafetyEnabled(true);
		rightRearBaseMotor.setSafetyEnabled(true);
		leftTopBaseMotor.setSafetyEnabled(true);
		rightTopBaseMotor.setSafetyEnabled(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void tankDrive(double left, double right) {
		if(left > RobotMap.KDeadZoneLimit || left < -RobotMap.KDeadZoneLimit) leftFrontBaseMotor.set(left);
		else leftFrontBaseMotor.set(0);
		if(right > RobotMap.KDeadZoneLimit || right < -RobotMap.KDeadZoneLimit) rightFrontBaseMotor.set(right);
		else rightFrontBaseMotor.set(0);
	}
    public void highShiftBase() {
		shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void lowShiftBase() {
		shifterSolenoid.set(DoubleSolenoid.Value.kForward);
	}
    public void toggleShift() {
		if (shifterSolenoid.get() == DoubleSolenoid.Value.kForward) {
			highShiftBase();
		} else {
			lowShiftBase();
		}
	}
}