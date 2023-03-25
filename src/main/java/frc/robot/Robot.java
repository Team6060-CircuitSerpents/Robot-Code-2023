// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.Arm.ArmControl;
import frc.Drive.AutoDrive;
import frc.Drive.DriveTrain;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //PID pid = new PID(5, 3.4, 1.0, 0);

  /*
   * The maxSpeed variable is the maximum speed the motors will move.
   * I made a variable for it so if we decide to change the speed
   * we just need to change this one variable.
   * 1 = 100%
   * 0.5 = 50%
   */
  public double maxSpeed = 0.35;

  // Limelight is public so AutoDrive can access it.
  public Limelight limelight = new Limelight(this, -5, 5);

  // We have a camera to help the driver see.
  UsbCamera camera;
  VideoSink server;

  /*
   * AutoDrive comes from another folder so we need to import it.
   * I did this because I can but also because someone may hopefully learn how
   * packages and import work. Even though it's not necessary, I thought it
   * made the code more organized.
   * 
   * The 'this' you see in the parenthesis is the Robot class.
   * 'this' basically means itself.
   * - AJ
   */
  public DriveTrain driveTrain = new DriveTrain(); // DriveTrain is public so AutoDrive can access it.
  AutoDrive aDrive = new AutoDrive(this);
  public ArmControl aControl = new ArmControl(3, 2, 7, 0);

  private Joystick controller = new Joystick(0);
  private Joystick control2 = new Joystick(1);

  private Controls controls = new Controls(controller, control2);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    aControl.resetArm();

    // Set up the camera. You can find it on the SmartDashboard
    camera = CameraServer.startAutomaticCapture(0);
    server = CameraServer.getServer();
    server.setSource(camera);

    /*
     * These methods are supposed to make creating drive motors easier.
     * All you have to do is tell it the ID and whether it is on the left side or right.
     */
    // Test Bot Motors
    //driveTrain.CreateSRX(4, true);
    //driveTrain.CreateSRX(6, true);
    //driveTrain.CreateSRX(20, false);
    //driveTrain.CreateSRX(3, false);

    driveTrain.CreateFX(5, true);
    driveTrain.CreateFX(4, true);
    driveTrain.CreateFX(6, false);
    driveTrain.CreateFX(1, false);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    aDrive.initialize();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    aDrive.timedAutonomousDrive();
    
    // This switch statement isn't used
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
        break;
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // I tried to organize and make the code less long, I don't think it did much.
    double[] sticks = controls.getSticksOne();

    // Sticks for tilt
    double left = 0.0; // movement for left motors
    double right = 0.0; // movement for right motors

    // LEFT
    if (sticks[0] > 0.05 || sticks[0] < -0.05){
      left = sticks[0] * maxSpeed;
    }
    // RIGHT
    if (sticks[1] > 0.05 || sticks[1] < -0.05){
      right = sticks[1] * maxSpeed;
    }

    // Extend
    if (controls.getX2()){
      aControl.extend(0.3);
    }
    else if (controls.getY2()){
      aControl.extend(-0.3);
    }
    else{
      aControl.extend(0);
    }

    // Move
    if (controls.getLB2()){
      aControl.moveUp();
    } 
    else if (controls.getRB2()){
      aControl.moveDown();
    }
    else if (controls.getA2()){
      aControl.midUp();
    }
    aControl.update();

    // Intake
    if (controls.getLB() || controls.getRT() == 1){
      aControl.intake();
    }
    else if (controls.getRB() || controls.getLT() == 1){
      aControl.outtake();
    }
    else{
      aControl.stopIntake();
    }

    driveTrain.driveFX(-left, right);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
