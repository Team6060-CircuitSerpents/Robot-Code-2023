package frc.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ArmControl {
    
    // Everything that is commented out was for PID but it don't work.
    
    public TalonFX moveMotor;
    private TalonFX extendMotor;
    private TalonSRX clawMotor;
    private TalonSRX tiltMotor;
    private final int targetPos = 10;
    public final double maxPos = 24575.0;

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

    public void moveUp(){
        moveMotor.set(ControlMode.PercentOutput, 0.2);
    }

    public void moveDown(){
        moveMotor.set(ControlMode.PercentOutput, -0.2);
    }

    public void moveStop(){
        moveMotor.set(ControlMode.PercentOutput, 0);
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

    public void tiltDown(){
        tiltMotor.set(ControlMode.PercentOutput, -1);
    }

    public void stopTilt(){
        tiltMotor.set(ControlMode.PercentOutput, 0);
    }
}
