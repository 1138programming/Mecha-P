package frc.team1138.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1138.robot.Robot;

public class TurnWithGyro extends PIDCommand
{
	// Setup the P, I, and D of the loop along with creating the PID controller
	// itself
	private static double P = 0.7, I = 0.0, D = 0.0;
	private final PIDController turnController;

	// Initiate PID
	public TurnWithGyro()
	{
		super("Turn Angle", P, I, D);
		requires(Robot.DRIVE_BASE);
		turnController = this.getPIDController();
		turnController.setInputRange(-360, 360); // Max and min angle
		turnController.setOutputRange(-1, 1); // Max and min motor power (in percent output mode)
		turnController.setAbsoluteTolerance(1.5); // Error allowed
		turnController.setContinuous(true);
		System.out.println("Inited");
		Robot.DRIVE_BASE.resetGyro();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize()
	{

	}

	@Override
	protected double returnPIDInput()
	{
		return (Robot.DRIVE_BASE.getAngle());
	}

	public void setTarget(double angle)
	{
		this.turnController.setSetpoint(angle);
	}

	@Override
	protected void usePIDOutput(double output)
	{
		// THE SIDE WITH THE GEAR IS THE FRONT!
		if (!turnController.onTarget())
		{
			if ((this.returnPIDInput() - this.getSetpoint()) > 0)
			{ // need to turn left
				System.out.println("turn left");
				System.out.println("Left Motor: " + (-output) + "Right Motor: " + (output));
				Robot.DRIVE_BASE.tankDrive(-output, output);
			}
			else if ((this.returnPIDInput() - this.getSetpoint()) < 0)
			{ // need to turn right
				System.out.println("turn right");
				System.out.println("Left Motor: " + (-output) + "Right Motor: " + (output));
				Robot.DRIVE_BASE.tankDrive(-output, output);
			}
			System.out.println("set point: " + this.getSetpoint());
			System.out.println("Current Angle: " + Robot.DRIVE_BASE.getAngle());
			System.out.println("Error: " + (Robot.DRIVE_BASE.getAngle() - this.getSetpoint()));
			System.out.println("Input: " + this.returnPIDInput());
			System.out.println("On Target: " + turnController.onTarget());
		}
		else
		{
			Robot.DRIVE_BASE.tankDrive(0, 0);
			System.out.println("On Target: " + turnController.onTarget());
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute()
	{
		final double setAngle = SmartDashboard.getNumber("setAngle", 0);
		setTarget(setAngle);
		SmartDashboard.putBoolean("tracking", true);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished()
	{
		System.out.println("On Target: " + turnController.onTarget());
		return turnController.onTarget();
	}

	// Called once after isFinished returns true
	@Override
	protected void end()
	{
		Robot.DRIVE_BASE.tankDrive(0, 0);
		Robot.DRIVE_BASE.resetGyro();
		SmartDashboard.putNumber("setAngle", 0);
		SmartDashboard.putBoolean("tracking", false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted()
	{
	}
}