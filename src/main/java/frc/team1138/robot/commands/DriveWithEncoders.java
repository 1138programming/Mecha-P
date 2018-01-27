package frc.team1138.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1138.robot.Robot;

public class DriveWithEncoders extends PIDCommand
{
	private static double P = 0.7, I = 0.0, D = 0.0;
	private final PIDController driveController;

	public DriveWithEncoders()
	{
		super("Drive Distance", P, I, D);
		requires(Robot.DRIVE_BASE);
		driveController = this.getPIDController();
		driveController.setInputRange(-1000000000, 1000000000); // Technically the encoders can go forever
		driveController.setOutputRange(-1, 1); // Min and max input for talons
		driveController.setAbsoluteTolerance(10); // Error allowed
		driveController.setContinuous(true);
		Robot.DRIVE_BASE.resetEncoders();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{
	}

	@Override
	protected double returnPIDInput()
	{
		return (Robot.DRIVE_BASE.getLeftEncoderValue());
	}

	public void setTarget(double ticks)
	{
		this.driveController.setSetpoint(ticks);
	}

	@Override
	protected void usePIDOutput(double output)
	{
		// THE SIDE WITH THE GEAR IS THE FRONT!
		if (!driveController.onTarget())
		{
			if ((this.returnPIDInput() - this.getSetpoint()) > 0)
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
			System.out.println("Current Encoder Value: " + Robot.DRIVE_BASE.getLeftEncoderValue());
			System.out.println("Error: " + (Robot.DRIVE_BASE.getLeftEncoderValue() - this.getSetpoint()));
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
		final double setEncoder = SmartDashboard.getNumber("setEncoder", 0);

		setTarget(setEncoder);
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
		Robot.DRIVE_BASE.resetEncoders();
		SmartDashboard.putNumber("setEncoder", 0);
		SmartDashboard.putBoolean("tracking", false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
}
