package frc.team1138.robot.utils;

/**
 * @author Zheyuan Hu
 * @version 1.0.0
 */
import static java.util.Objects.requireNonNull;

public class DriveTrain{
    final MotorUtils leftMasterMotor, rightMasterMotor; 
    //This is a limit to make sure that the joystick isn't potentially stuck for the function tankDrive
    public static final double KDeadZoneLimit = 0.1;
    
    public DriveTrain(MotorUtils left, MotorUtils right){
        this.leftMasterMotor = left;
        this.rightMasterMotor = right; 
    }

    public void driveWithCurve(double power, double curve){
        
    }
    
    public void tankDrive(double left, double right){
        requireNonNull(leftMasterMotor, "Left Front Motor was null"); 
        requireNonNull(rightMasterMotor, "Right Front Motor was null"); 
        if(leftMasterMotor != null) {
            if(left > KDeadZoneLimit || left < -KDeadZoneLimit) {
                leftMasterMotor.set(left);
            }
            else {
                leftMasterMotor.set(0);
            } 
        }
        if(rightMasterMotor != null) {
            if(right > KDeadZoneLimit || right < -KDeadZoneLimit) {
                rightMasterMotor.set(right);
            }
            else {
                rightMasterMotor.set(0);
            } 
        }
    }
    
}