package frc.team1138.robot.utils;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 

/**
 * @author Zheyuan Hu
 * @version 1.0.0
 */

public class MotorUtils{
    private WPI_TalonSRX talon; 
    
    /**
     * 
     */
    public MotorUtils(final int port){
        this.talon = new WPI_TalonSRX(port);
        this.talon.setSafetyEnabled(true);
    }

    public MotorUtils slaveTo(MotorUtils master){
        this.talon.set(ControlMode.Follower, master.getDeviceID());
        this.talon.follow(master.getMotor());
        return this; 
    }

    public MotorUtils setInverted(boolean isInverted){
        this.talon.setInverted(isInverted);
        return this; 
    }

    public void set(double input){
        talon.set(input);
    }
    
    public int getDeviceID(){
        return talon.getDeviceID(); 
    }

    public WPI_TalonSRX getMotor(){
        return this.talon; 
    }

    // public MotorUtils setEncoder(final int codesPerRev){
    //     this.talon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    //     this.talon.configEncoderCodesPerRev(codesPerRev);
    //     this.talon.setEncPosition(0);
    //     return this; 
    // }

    // public void zeroEncoder(){
    //     this.talon.setEncPosition(0);
    // }

    public WPI_TalonSRX sendable(){
        return this.talon; 
    }
}