package frc.team1138.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1138.robot.Robot;

public class DriveWithUltrasonic extends PIDCommand
{
	public static final double KSlowSpeed = 0.4;
	private static double P = 0.7, I = 0.0, D = 0.0;
	private final PIDController driveController;

	public DriveWithUltrasonic()
	{
		super("Drive Distance", P, I, D);
		requires(Robot.DRIVE_BASE);
		driveController = this.getPIDController();
		driveController.setInputRange(1.5, 115); // Ultrasonic range for VEX ultrasonic (might be changed)
		driveController.setOutputRange(-1, 1); // Min and max input for talons
		driveController.setAbsoluteTolerance(3); // Amount of error allowed
		driveController.setContinuous(true);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
	}

	@Override
	protected double returnPIDInput()
	{
		return (Robot.DRIVE_BASE.getUltrasonic());
	}

	private void setTarget(double range)
	{
		this.driveController.setSetpoint(range);
	}

	@Override
	protected void usePIDOutput(double output)
	{
		// THE SIDE WITH THE GEAR IS THE FRONT!
		if (!driveController.onTarget())
		{
			if (Robot.DRIVE_BASE.getUltrasonic() == -1) // If not picking up signal
			{
				Robot.DRIVE_BASE.tankDrive(KSlowSpeed, KSlowSpeed);
			}
			else if ((this.returnPIDInput() - this.getSetpoint()) > 0)
			{ // need to move forward
				System.out.println("Move Forward");
				System.out.println("Left Motor: " + (output) + "Right Motor: " + (output));
				Robot.DRIVE_BASE.tankDrive(output, output);
			}
			else if ((this.returnPIDInput() - this.getSetpoint()) < 0)
			{ // need to move backward
				System.out.println("Move Backward");
				System.out.println("Left Motor: " + (-output) + "Right Motor: " + (-output));
				Robot.DRIVE_BASE.tankDrive(-output, -output);
			}
			System.out.println("set point: " + this.getSetpoint());
			System.out.println("Current Ultrasonic Value: " + Robot.DRIVE_BASE.getUltrasonic());
			System.out.println("Error: " + (Robot.DRIVE_BASE.getUltrasonic() - this.getSetpoint()));
			System.out.println("Input: " + this.returnPIDInput());
			System.out.println("On Target: " + driveController.onTarget());
		}
		else
		{
			Robot.DRIVE_BASE.tankDrive(0, 0);
			System.out.println("On Target: " + driveController.onTarget());
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		final double setRangeValue = SmartDashboard.getNumber("setRange", 0);

		setTarget(setRangeValue);
		SmartDashboard.putBoolean("tracking", true);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		System.out.println("On Target: " + driveController.onTarget());
		return driveController.onTarget();
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.DRIVE_BASE.tankDrive(0, 0);
		SmartDashboard.putBoolean("tracking", false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
}
