package frc.team1138.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1138.robot.utils.DriveTrain;
//import frc.team1138.robot.RobotMap; Uncomment if RobotMap is needed.
import frc.team1138.robot.utils.MotorUtils;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team1138.robot.commands.DriveWithJoysticks;

/**
 *This will be what the finished subsystem should look like when everything is included.
 *Most of this is simply borrowed from JavaMomentum until we know what sensors and solenoids
 *are where and such.
 */


public class DriveBase extends Subsystem {

	//Setup the base configuration by assigning talons
	public static final int KLeftRearBaseTalon =  1;
	public static final int KLeftFrontBaseTalon = 2 ;
	public static final int KLeftTopBaseTalon = 3;
	public static final int KRightRearBaseTalon = 4 ;
	public static final int KRightFrontBaseTalon = 5 ;
	public static final int KRightTopBaseTalon = 6;
	
	public static final int KLeftBaseMaster = 1; //KLeftMaster = Master Talon for left side
	public static final int KRightBaseMaster = 2; //KRightMaster = Master Talon for right side
	
	//all of the solenoids are doubles, so they need 2 numbers each.  If you change one, be sure to change
	//the other one of the pair.
	public static final int KShifterSolenoid1 = 0;
	public static final int KShifterSolenoid2 = 1;
	
	private MotorUtils leftFrontBaseMotor,
					 rightFrontBaseMotor,
					 leftRearBaseMotor,
					 leftTopBaseMotor,
					 rightRearBaseMotor,
					 rightTopBaseMotor;
	private DriveTrain driveTrain; 
	private DoubleSolenoid shifterSolenoid; //There will probably be a shift solenoid
	
//	private AHRS gyroAccel;
	public DriveBase() {
		// Motors
		// Enable master motors and encoders 
		leftFrontBaseMotor = new MotorUtils(KLeftFrontBaseTalon);
		leftFrontBaseMotor.enableControl().setInverted(true).setEncoder(4095); 
		rightFrontBaseMotor = new MotorUtils(KRightFrontBaseTalon);
		rightFrontBaseMotor.enableControl().setEncoder(4095);
		//Enable slave motors 
		leftRearBaseMotor = new MotorUtils(KLeftRearBaseTalon);
		leftRearBaseMotor.slaveTo(leftFrontBaseMotor); 
		leftTopBaseMotor = new MotorUtils(KLeftTopBaseTalon);
		leftTopBaseMotor.slaveTo(leftFrontBaseMotor);
		rightRearBaseMotor = new MotorUtils(KRightRearBaseTalon);
		rightRearBaseMotor.slaveTo(rightFrontBaseMotor); 
		rightTopBaseMotor = new MotorUtils(KRightTopBaseTalon);
		rightTopBaseMotor.slaveTo(rightFrontBaseMotor); 
		// Drive Train 
		driveTrain = new DriveTrain(leftFrontBaseMotor, rightFrontBaseMotor); 
		// Solenoids 
		shifterSolenoid = new DoubleSolenoid(KShifterSolenoid1, KShifterSolenoid2);
		//Gyro & Accel
//		gyroAccel = new AHRS(Port.kMXP);
//		gyroAccel.zeroYaw();
		}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoysticks());
	}
	
    public void tankDrive(double left, double right) {
		driveTrain.tankDrive(left, right);
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