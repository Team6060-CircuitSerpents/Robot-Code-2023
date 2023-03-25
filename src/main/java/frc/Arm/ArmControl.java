package frc.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ArmControl {
    
    // Everything that is commented out was for PID but it don't work.
    
    private TalonFX moveMotor;
    private TalonFX extendMotor;
    private TalonSRX intake1;
    private TalonSRX intake2;
    private final int targetPos = 10;
    public final double maxPos = 70575.0;
    public final double midPos = 62575.0;
    public double currentPos = maxPos;

    // Sets up ArmControl class
    public ArmControl(int moveID, int extendID, int intakeID, int intakeID2){
        
        moveMotor = new TalonFX(moveID);
        extendMotor = new TalonFX(extendID);
        moveMotor.setNeutralMode(NeutralMode.Brake);
        extendMotor.setNeutralMode(NeutralMode.Brake);

        intake1 = new TalonSRX(intakeID);
        intake2 = new TalonSRX(intakeID2);
        intake1.setNeutralMode(NeutralMode.Brake);
        intake2.setNeutralMode(NeutralMode.Brake);
        

        configMotor(moveMotor);
        //configMotor(extendMotor);
    }

    private void configMotor(TalonFX motor){
        motor.setSensorPhase(false);
        motor.setInverted(false);

        motor.config_kP(0, 0.1);
        motor.config_kI(0, 0);
        motor.config_kD(0, 0);

        motor.config_IntegralZone(0, 0, 10);
        motor.configClosedLoopPeakOutput(0, 1);
        motor.configAllowableClosedloopError(0, 0);

        motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        motor.set(ControlMode.Position, targetPos);
    }

    public void resetArm(){
        moveMotor.setSelectedSensorPosition(0);
    }

    public double getPosition(){
        return moveMotor.getSelectedSensorPosition();
    }

    public void midUp(){
        currentPos = midPos;
        moveMotor.set(ControlMode.PercentOutput, 0.2);
    }

    public void moveUp(){
        currentPos = maxPos;
        moveMotor.set(ControlMode.PercentOutput, 0.2);
    }

    public void moveDown(){
        moveMotor.set(ControlMode.PercentOutput, -0.1);
    }

    public void moveStop(){
        moveMotor.set(ControlMode.PercentOutput, 0);
    }

    // Extends the arm
    public void extend(double output){
        extendMotor.set(ControlMode.PercentOutput, output);
    }

    public void intake(){
        intake1.set(ControlMode.PercentOutput, -0.3);
        intake2.set(ControlMode.PercentOutput, -0.3);
    }

    public void outtake(){
        intake1.set(ControlMode.PercentOutput, 0.3);
        intake2.set(ControlMode.PercentOutput, 0.3);
    }

    public void stopIntake(){
        intake1.set(ControlMode.PercentOutput, 0);
        intake2.set(ControlMode.PercentOutput, 0);
    }

    public void update(){
        double postion = getPosition();
        System.out.println("" + postion);
        if (postion > currentPos || postion < 0){
            moveStop();

            if (postion < 0){
                resetArm();
            }
        }
    }
}
