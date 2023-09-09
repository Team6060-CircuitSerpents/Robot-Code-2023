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
    private double[] times = new double[6];

    public AutoDrive(Robot robot){

        this.robot = robot;
    }

    public void initialize(){
        robot.limelight.setLight(true);
        // The times variable isn't used.
        times[0] = 1;
        times[1] = 2;
        times[2] = 3.6;
        times[3] = 7;
        times[4] = 9;
        timer.reset();
        timer.start();

        /*times[0] = 2;
        times[1] = 6;
        times[2] = 8;
        times[3] = 7;
        times[4] = 9;
        */

    }

    public void autonomousDrive(){
       // robot.limelight.positionRobot();
    }
    
    public void timedAutonomousDrive(){
        if (timer.get() <= times[0]){ // Score
            robot.aControl.outtake();
        }
        else if (timer.get() >= times[1] && timer.get() < times[2]){ // Drive away, backwards
            robot.aControl.stopIntake();
            robot.aControl.extend(-0.3); // Retracts while driving
            robot.driveTrain.driveFX(-0.3, 0.3);
        }
        else{ // Stop
            robot.aControl.extend(0);
            robot.driveTrain.driveFX(0, 0);
        }
        
        /*
        if (timer.get() <= times[0]){ // Pull arm inward
            //robot.aControl.outtake();
            robot.aControl.extend(-0.4);
        }
        else if (timer.get() >= times[1] && timer.get() < times[2]){ // Move arm up
            robot.aControl.extend(0);
            robot.aControl.moveUp();
            robot.aControl.update();
            double postion = robot.aControl.getPosition();
            if (postion > robot.aControl.maxPos || robot.aControl.getPosition() < 0){
                robot.aControl.moveStop();

                if (robot.aControl.getPosition() < 0){
                    robot.aControl.resetArm();
                }
            }
        }
        */


        /*
        else if (timer.get() >= times[2] && timer.get() < times[3]){ // Extend outward
            robot.aControl.moveStop();
            robot.aControl.extend(0.4);
        }
        else if (timer.get() >= times[3] && timer.get() <= times[4]){ // Score
            robot.aControl.extend(0);
            robot.aControl.outtake();
        }
        else{ // Stop
            robot.aControl.stopIntake();
        }
       /* else if (timer.get() >= times[2] && timer.get() <= times[3]){ // Position itself
            robot.limelight.positionRobot();
        }
        else if (timer.get() > times[3] && timer.get() <= times[4]){
            robot.aControl.move(0.2);
            robot.driveTrain.driveFX(0, 0);
        }
        else if (timer.get() > times[4] && timer.get() <= times[5]){
            robot.aControl.open();
            robot.aControl.move(0);
        }*/
    }
}