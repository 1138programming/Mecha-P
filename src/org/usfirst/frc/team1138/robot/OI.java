package org.usfirst.frc.team1138.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team1138.robot.commands.ExampleCommand;
//import org.usfirst.frc.team1138.robot.commands.LiftBase;
import org.usfirst.frc.team1138.robot.commands.ShiftBase;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick leftController, rightController, xBoxController;
	private JoystickButton shiftBtn /*liftBtn*/; // Logitech Button 
	private JoystickButton btnA, btnB, btnX, btnY, btnLB, btnRB; 
	
	public OI() {
		leftController = new Joystick(RobotMap.KLeftJoystick);
		rightController = new Joystick(RobotMap.KRightJoystick);
		xBoxController = new Joystick(RobotMap.KXBoxController);
		
		//Logitech Buttons
		shiftBtn = new JoystickButton(leftController, 1); //Shifts the Base from Low Gear to High Gear and vice versa
//		liftBtn = new JoystickButton(leftController, 7);   //Shifts the Base to Lift the robot and vice versa
		
		//XBox Definitions (the functions of the buttons will change with time)
		btnA	= new JoystickButton(xBoxController, RobotMap.KButtonA) ;	//Toggle Vision
		btnB = new JoystickButton(xBoxController, RobotMap.KButtonB) ;	//Toggle Esophagus
		btnX = new JoystickButton(xBoxController, RobotMap.KButtonX) ;	//Turn on shooter
		btnY = new JoystickButton(xBoxController, RobotMap.KButtonY) ;	//Turn off shooter
		btnLB = new JoystickButton(xBoxController, RobotMap.KLeftBumper) ;	//Decrease Flywheel Speed
		btnRB = new JoystickButton(xBoxController, RobotMap.KRightBumper) ;	//Increase Flywheel Speed
		
		shiftBtn.whenPressed(new ShiftBase()); //TODO create ShiftBase function
//		liftBtn.whenPressed(new LiftBase());
//		buttonX->WhenPressed(new EngageShooter());
//		buttonY->WhenPressed(new DisengageShooter());
//		buttonB->WhenPressed(new ToggleEsophagus());
//		buttonA->WhenPressed(new VisionTracking());
//		buttonLB->WhenPressed(new OpenEsophagus());		//buttonLB->WhenPressed(new FlywheelDecreaseSpeed());
//		buttonRB->WhenPressed(new CloseEsophagus());	//buttonRB->WhenPressed(new FlywheelIncreaseSpeed());
	}
	public double getRightController() {			//Right controller is right side drive
		return rightController.getY();
	}

	public double getLeftController() {			//Left controller is left side drive
		return leftController.getY();
	}

/*	public boolean getLeftTrigger() {				//left controller's trigger is the shifter
		return liftBtn.get(); 
	}*/

	public boolean getRightTrigger() {			//right controller's trigger engages the lift
		return shiftBtn.get(); 
	}

	public double getLeftXBoxAxis() {			//left xbox axis controls the collector
		return (-xBoxController.getRawAxis(1));
	}

	//Fine control from collector, signal from joystick is reversed
	public double getRightXBoxAxis() {
		return (-xBoxController.getRawAxis(5));
	}
}
