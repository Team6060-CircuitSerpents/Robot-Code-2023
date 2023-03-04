package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Limelight {

    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry targetArea;
    NetworkTableEntry tv;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        targetArea = table.getEntry("ta");
        tv = table.getEntry("tv");
    }


    public void setLight(boolean state) {   
        /*
         * The part that says "state ? 3 : 1"
         * is basically saying that true = 3 and false = 1.
         * If state is true the number is 3.
         * If state is false the number is 1.
         * 
         * - AJ
         */
        table.getEntry("ledMode").setNumber(state ? 3 : 1);
        table.getEntry("camMode").setNumber(0);
    }

    public double getX() {
        tx = table.getEntry("tx");

        SmartDashboard.putNumber("x", tx.getDouble(0.0));
        return tx.getDouble(0.6);

    }

    public double getY() {
        ty = table.getEntry("ty");
        return ty.getDouble(0.6);        
    }

    public double getTargetArea(){
        targetArea = table.getEntry("ta");
        return targetArea.getDouble(0.0);
    }
    public double getV(){
        tv = table.getEntry("tv");
        return tv.getDouble(0);
    }
}