package frc.team1138.robot.utils;

import com.ctre.CANTalon;

/**
 * @author Zheyuan Hu
 * @version 1.0.0
 */

public class MotorUtils{
    private CANTalon talon; 
    
    /**
     * 
     */
    public MotorUtils(final int port){
        this.talon = new CANTalon(port);
        this.talon.setSafetyEnabled(true);
    }

    public MotorUtils slaveTo(MotorUtils master){
        this.talon.changeControlMode(CANTalon.TalonControlMode.Follower);
        this.talon.set(master.getDeviceID());
        return this; 
    }

    public MotorUtils enableControl(){
        this.talon.enableControl();
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

    public MotorUtils setEncoder(final int codesPerRev){
        this.talon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        this.talon.configEncoderCodesPerRev(codesPerRev);
        this.talon.setEncPosition(0);
        return this; 
    }

    public void zeroEncoder(){
        this.talon.setEncPosition(0);
    }

    public CANTalon sendable(){
        return this.talon; 
    }
}