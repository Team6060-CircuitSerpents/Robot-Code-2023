package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Limelight {

    private Robot robot;

    private NetworkTable table;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry targetArea;
    private NetworkTableEntry tv;

    private final double minX;
    private final double maxX;

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

    public void positionRobot(double left, double right){

        double x = getX();

        if (x >= getMaxX()){
            //robot.driveTrain.driveFX(robot.maxSpeed, robot.maxSpeed);
            robot.driveTrain.driveFX(left, right); // right
        }
        else if (x <= getMinX()){
            robot.driveTrain.driveFX(-left, -right); // left
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