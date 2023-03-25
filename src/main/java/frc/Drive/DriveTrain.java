package frc.Drive;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveTrain {
    
    private ArrayList<TalonSRX> leftMotors = new ArrayList<>();
    private ArrayList<TalonSRX> rightMotors = new ArrayList<>();

    private ArrayList<TalonFX> leftFX = new ArrayList<>();
    private ArrayList<TalonFX> rightFX = new ArrayList<>();

    /*
   * Create a motor with TalonSRX
   * 'id' is the id you can find in Phoenix Tuner.
   * 'isLeft' is a variable that determines which list
   * the motor is added to.
   * isLeft can only be true or false.
   */
    public void CreateSRX(int id, boolean isLeft){

        TalonSRX motor = new TalonSRX(id);
        motor.setNeutralMode(NeutralMode.Coast);
    
        if (isLeft){
            leftMotors.add(motor);
        }
        else{
            rightMotors.add(motor);
        }
    }

    // Create a motor with TalonFX
    public void CreateFX(int id, boolean isLeft){

        TalonFX motor = new TalonFX(id);
        motor.setNeutralMode(NeutralMode.Coast);

        if (isLeft){
            leftFX.add(motor);
        }
        else{
            rightFX.add(motor);
        }
    }

    /* 
    * Use any motors created with the above methods.
    * This and the above methods were made to just make the process of making a motor
    * and using it in our drive code easier.
    * - AJ
    */
    public void driveSRX(double l, double r){

        // TalonSRX MOTORS
        // This code drives each motor in the lists.
        for (TalonSRX motor : leftMotors){
            motor.set(ControlMode.PercentOutput, l);
        }

        for (TalonSRX motor : rightMotors){
            motor.set(ControlMode.PercentOutput, r);
        }
    }

    /*
     * Same method as above but for TalonFX.
     * I made two methods so you aren't forced to use both.
     * If you want to use one type of motor controller use one.
     * If you want to use both use both driveSRX and driveFX.
     */
    public void driveFX(double l, double r){

        // TalonFX MOTORS
        // This code drives each motor in the lists.
        for (TalonFX motor : leftFX){
            motor.set(ControlMode.PercentOutput, l);
        }

        for (TalonFX motor : rightFX){
            motor.set(ControlMode.PercentOutput, r);
        }
    }
}
