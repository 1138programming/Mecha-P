package frc.team1138.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.usfirst.frc.team1138.robot.RobotMap; Uncomment if RobotMap is needed.
import frc.team1138.robot.commands.DriveWithJoysticks;

/**
 * This will be what the finished subsystem should look like when everything is
 * included. Most of this is simply borrowed from JavaMomentum until we know
 * what sensors and solenoids are where and such.
 * 
 * @author Christian Gideon
 */

public class DriveBase extends Subsystem
{
	// Setup the base configuration by assigning talons
	public static final int KLeftRearBaseTalon = 1;
	public static final int KLeftFrontBaseTalon = 2;
	public static final int KLeftTopBaseTalon = 3;
	public static final int KRightRearBaseTalon = 4;
	public static final int KRightFrontBaseTalon = 5;
	public static final int KRightTopBaseTalon = 6;
	// All of the solenoids are doubles, so they need 2 numbers each. If you change
	// one,
	// be sure to change the other one of the pair also.
	public static final int KShifterSolenoid1 = 0;
	public static final int KShifterSolenoid2 = 1;

	// Variable for base ultrasonic
	// TODO figure out what these numbers will be based on where they're gonna be
	// plugged in
	public static final int KBaseUltrasonic = 1;

	// This is a limit to make sure that the joystick isn't potentially stuck for
	// the function tankDrive
	public static final double KDeadZoneLimit = 0.1;

	// Setup the talons for the base
	private final TalonSRX leftFrontBaseMotor, rightFrontBaseMotor, leftRearBaseMotor, leftTopBaseMotor,
			rightRearBaseMotor, rightTopBaseMotor;

	// Setup the shifter solenoid
	// private final DoubleSolenoid shifterSolenoid;

	// Setup the gyro
	private final PigeonIMU pigeonIMU;

	// Setup the ultrasonic (like for boats, but for a robot instead)
	// private final Ultrasonic baseUltrasonic;

	public DriveBase()
	{
		// Gyro
		pigeonIMU = new PigeonIMU(6);
		pigeonIMU.setYaw(0, 0);
		// Master talons
		leftFrontBaseMotor = new TalonSRX(KLeftFrontBaseTalon);
		rightFrontBaseMotor = new TalonSRX(KRightFrontBaseTalon);
		// Slave talons
		leftRearBaseMotor = new TalonSRX(KLeftRearBaseTalon);
		leftTopBaseMotor = new TalonSRX(KLeftTopBaseTalon);
		rightRearBaseMotor = new TalonSRX(KRightRearBaseTalon);
		rightTopBaseMotor = new TalonSRX(KRightTopBaseTalon);

		// Config the talons and enable
		leftFrontBaseMotor.setInverted(true);
		leftRearBaseMotor.setInverted(true);
		// Slaves
		leftRearBaseMotor.set(ControlMode.Follower, leftFrontBaseMotor.getDeviceID());
		leftTopBaseMotor.set(ControlMode.Follower, leftFrontBaseMotor.getDeviceID());
		rightRearBaseMotor.set(ControlMode.Follower, rightFrontBaseMotor.getDeviceID());
		rightTopBaseMotor.set(ControlMode.Follower, rightFrontBaseMotor.getDeviceID());

		leftTopBaseMotor.setInverted(false);
		rightTopBaseMotor.setInverted(true);
		// Solenoid
		// shifterSolenoid = new DoubleSolenoid(KShifterSolenoid1, KShifterSolenoid2);

		// Ultrasonic
		// Output, then input. Auto sets to inches, but can be changed with a third
		// parameter
		// baseUltrasonic = new Ultrasonic(KBaseUltrasonic, KBaseUltrasonic);
		// baseUltrasonic.setAutomaticMode(true);

		// Encoders on talons
		leftFrontBaseMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, // Use Primary
																									// Closed Loop
				0);// timeoutMS
		rightFrontBaseMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, // Use Primary
																										// Closed Loop
				0);// timeoutMS
		// leftFrontBaseMotor.configEncoderCodesPerRev(4096); //Will come in handy when
		// dealing with encoders
		// rightFrontBaseMotor.configEncoderCodesPerRev(4096);
		leftFrontBaseMotor.setSelectedSensorPosition(0, // New Sensor Position
				0, // Use Primary Closed Loop
				0);// timeoutMS
		rightFrontBaseMotor.setSelectedSensorPosition(0, // New Sensor Position
				0, // Use Primary Closed Loop
				0);// timeoutMS
	}

	@Override
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoysticks());
	}

	// Used to drive tank control style in teleop mode
	// (Programming secret: if what's in your loop is only one line long you don't
	// need {})

	public void tankDrive(double left, double right)
	{
		if ((left > KDeadZoneLimit) || (left < -KDeadZoneLimit))
		{
			leftFrontBaseMotor.set(ControlMode.PercentOutput, left);
		}
		else
		{
			leftFrontBaseMotor.set(ControlMode.PercentOutput, 0);
		}
		if ((right > KDeadZoneLimit) || (right < -KDeadZoneLimit))
		{
			rightFrontBaseMotor.set(ControlMode.PercentOutput, right);
		}
		else
		{
			rightFrontBaseMotor.set(ControlMode.PercentOutput, 0);
		}
		report();
	}

	// High shifts base
	// public void highShiftBase()
	// {
	// 	shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
	// }

	// // Low shifts base
	// public void lowShiftBase()
	// {
	// 	shifterSolenoid.set(DoubleSolenoid.Value.kForward);
	// }

	// // Toggles the shift mode of the base using highShiftBase() and lowShiftBase()
	// public void toggleShift()
	// {
	// 	if (shifterSolenoid.get() == DoubleSolenoid.Value.kForward)
	// 	{
	// 		highShiftBase();
	// 	}
	// 	else
	// 	{
	// 		lowShiftBase();
	// 	}
	// }

	// Returns angle of the gyro
	public double getAngle()
	{
		final double[] ypr = new double[3]; // Create an array to take in yaw, pitch, and roll of gyro
		pigeonIMU.getYawPitchRoll(ypr);
		return (-ypr[0]);
	}

	// Resets the gyro
	public void resetGyro()
	{
		pigeonIMU.setYaw(0, 0);
	}

	// Returns value of the left encoder
	public double getLeftEncoderValue()
	{
		return leftFrontBaseMotor.getSensorCollection().getQuadraturePosition();
	}

	// Returns value of the right encoder
	public double getRightEncoderValue()
	{
		return rightFrontBaseMotor.getSensorCollection().getQuadraturePosition();
	}

	// Resets both encoders
	public void resetEncoders()
	{
		leftFrontBaseMotor.getSensorCollection().setQuadraturePosition(0, 0);
		rightFrontBaseMotor.getSensorCollection().setQuadraturePosition(0, 0);
	}

	// Returns value of the ultrasonic
	public double getUltrasonic()
	{
		// return baseUltrasonic.getRangeInches();
		return 0;
	}

	public void report()
	{
		SmartDashboard.putString("Left Front: ", leftFrontBaseMotor.getControlMode() + " " + leftFrontBaseMotor.getInverted() + " " 
		+ leftFrontBaseMotor.getMotorOutputPercent());
		SmartDashboard.putString("Left Rear: ", leftRearBaseMotor.getControlMode() + " " + leftRearBaseMotor.getInverted() + " " 
		+ leftRearBaseMotor.getMotorOutputPercent());
		SmartDashboard.putString("Left Top: ", leftTopBaseMotor.getControlMode() + " " + leftTopBaseMotor.getInverted() + " " 
		+ leftTopBaseMotor.getMotorOutputPercent());

		SmartDashboard.putString("Right Front: ", rightFrontBaseMotor.getControlMode() + " " + rightFrontBaseMotor.getInverted() + " " 
		+ rightFrontBaseMotor.getMotorOutputPercent());
		SmartDashboard.putString("Right Rear: ", rightRearBaseMotor.getControlMode() + " " + rightRearBaseMotor.getInverted() + " " 
		+ rightRearBaseMotor.getMotorOutputPercent());
		SmartDashboard.putString("Right Top: ", rightTopBaseMotor.getControlMode() + " " + rightTopBaseMotor.getInverted() + " " 
		+ rightTopBaseMotor.getMotorOutputPercent());

		SmartDashboard.putNumber("Left Encoder", getLeftEncoderValue());
		SmartDashboard.putNumber("Right Encoder", getRightEncoderValue());
	}
}