package frc.Drive;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

/*
 * This code is for autonomous but it only uses limelight right now.
 * I think the limelight is good at turning to the target.
 * Everything that has 'robot.whatever' comes from the Robot.java file.
 * 'this.robot' is the robot variable in AutoDrive.
 * - AJ
 */
public class AutoDrive {
    
    private Robot robot;

    // Timer Variables
    private Timer timer = new Timer();
    private double[] times = new double[5];

    public AutoDrive(Robot robot){

        this.robot = robot;
    }

    public void initialize(){
        robot.limelight.setLight(true);
        times[0] = 4;
        times[1] = 5;
        times[2] = 10;
        times[3] = 14;
        times[4] = 17;
        timer.reset();
        timer.start();
    }

    private void limelightDrive(){
        double px = 0.01;
        double py = 0.04;

        double x = robot.limelight.getX() * px;
        double y = robot.limelight.getY() * py;

        double leftOut = (x-y) * robot.maxSpeed;
        double rightOut = (x-y) * robot.maxSpeed;

        System.out.println("LEFT: " + leftOut);
        System.out.println("RIGHT: " + rightOut);
        //System.out.println("Target Area: " + robot.limelight.getTargetArea());
        if (robot.limelight.getV() == 1){
            // Once the robot finds a target, run code to position itself
            robot.driveTrain.driveSRX(leftOut, rightOut);
            // Check how close the target is to the robot
            if (robot.limelight.getTargetArea() >= 26 && robot.limelight.getTargetArea() <= 28){
                System.out.println("Do something");
            }
        }
        else if (robot.limelight.getV() != 1){
            // Spins the robot until they find a target    
            robot.driveTrain.driveSRX(0, 0);
        }
    }

    public void autonomousDrive(){
        robot.limelight.positionRobot();
    }
    
    public void timedAutonomousDrive(){
        // Code isn't finished yet but you get the idea.
        if (timer.get() <= times[0]){ // Move toward node
            robot.driveTrain.driveSRX(0.4, 0);
        }
        else if (timer.get() >= times[1] && timer.get() < times[2]){ // Position itself
            limelightDrive();
        }
        else if (timer.get() >= times[2] && timer.get() < times[3]){ // Move Arm
            robot.aControl.move(0.15);
        }
        else{ // Stop
            robot.driveTrain.driveSRX(0, 0);
        }
    }
}