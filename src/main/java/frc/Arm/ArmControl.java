package frc.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ArmControl {
    
    // Everything that is commented out was for PID but it don't work.
    
    private TalonFX moveMotor;
    private TalonFX extendMotor;
    private TalonSRX clawMotor;
    private TalonSRX tiltMotor;

    // Sets up ArmControl class
    public ArmControl(int moveID, int extendID, int clawID, int tiltID){
        moveMotor = new TalonFX(moveID);
        extendMotor = new TalonFX(extendID);
        moveMotor.setNeutralMode(NeutralMode.Brake);
        extendMotor.setNeutralMode(NeutralMode.Brake);

        clawMotor = new TalonSRX(clawID);
        tiltMotor = new TalonSRX(tiltID);
        clawMotor.setNeutralMode(NeutralMode.Brake);
        tiltMotor.setNeutralMode(NeutralMode.Brake);

        //configMotor(moveMotor);
        //configMotor(extendMotor);
    }

    /*
    private void configMotor(TalonFX motor){
        motor.setSensorPhase(false);
        motor.configNominalOutputForward(0, 10);
        motor.configNominalOutputReverse(0, 10);
        motor.configPeakOutputForward(1, 10);
        motor.configPeakOutputReverse(-1, 10);

        motor.config_kP(0, pid.kP, 10);
        motor.config_kI(0, pid.kI, 10);
        motor.config_kD(0, pid.kD, 10);
        motor.config_kF(0, pid.kF, 10);

        motor.config_IntegralZone(0, 0, 10);
        motor.configClosedLoopPeakOutput(0, 1, 10);
        motor.configAllowableClosedloopError(0, 0, 10);
        motor.configMotionCruiseVelocity(100, 10);
        motor.configMotionAcceleration(100, 10);

        motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        motor.set(ControlMode.Position, pid.targetPos);
    }

    public void resetExtendMotor(){
        extendMotor.setSelectedSensorPosition(0);
    }

    public void setExtendPosition(int pos){
        extendMotor.setSelectedSensorPosition(pos);
    }

    public int getExtendPosition(){
        int pos = pid.update(extendMotor);
        return pos;
    }

    public void resetMoveMotor(){
        moveMotor.setSelectedSensorPosition(0);
        System.out.println("Reset Motor");
    }

    public void setMovePosition(int pos){
        moveMotor.setSelectedSensorPosition(pos);
    }

    public int getMovePosition(){
        int pos = pid.update(moveMotor);
        return pos;
    }
    */

    // Moves the arm up and down
    public void move(double output){
        moveMotor.set(ControlMode.PercentOutput, output);
    }

    // Extends the arm
    public void extend(double output){
        extendMotor.set(ControlMode.PercentOutput, output);
    }

    public void open(){
        clawMotor.set(ControlMode.PercentOutput, 1);
    }

    public void close(){
        clawMotor.set(ControlMode.PercentOutput, -1);
    }

    public void stopClaw(){
        clawMotor.set(ControlMode.PercentOutput, 0);
    }

    public void tiltUp(){
        tiltMotor.set(ControlMode.PercentOutput, 1);
    }

    public void tiltDOwn(){
        tiltMotor.set(ControlMode.PercentOutput, -1);
    }

    public void stopTilt(){
        tiltMotor.set(ControlMode.PercentOutput, 0);
    }
}
