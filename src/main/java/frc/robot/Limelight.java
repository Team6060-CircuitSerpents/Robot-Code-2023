package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Limelight {

    Robot robot;

    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry targetArea;
    NetworkTableEntry tv;

    double minX;
    double maxX;

    public Limelight(Robot robot, double min, double max) {
        this.robot = robot;

        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        targetArea = table.getEntry("ta");
        tv = table.getEntry("tv");

        minX = min;
        maxX = max;
    }

    public void positionRobot(){

        double x = getX();

        if (x >= getMaxX()){
            robot.driveTrain.driveFX(robot.maxSpeed, robot.maxSpeed);
        }
        else if (x <= getMinX()){
            robot.driveTrain.driveFX(-robot.maxSpeed, -robot.maxSpeed);
        }
        else{
            robot.driveTrain.driveFX(0, 0);
        }
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
        return tx.getDouble(0.0);

    }

    public double getY() {
        ty = table.getEntry("ty");
        return ty.getDouble(0.0);        
    }

    public double getTargetArea(){
        targetArea = table.getEntry("ta");
        return targetArea.getDouble(0.0);
    }
    public double getV(){
        tv = table.getEntry("tv");
        return tv.getDouble(0);
    }

    public double getMinX(){
        return minX;
    }
    public double getMaxX(){
        return maxX;
    }
}