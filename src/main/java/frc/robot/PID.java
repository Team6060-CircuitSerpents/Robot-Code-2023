package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class PID {
    public class Robot extends TimedRobot {
        private static final String kDefaultAuto = "0.5";
        private static final String kCustomAuto = "1.5";
        //private String m_autoSelected;
        private final SendableChooser<String> m_chooser = new SendableChooser<>();
      
        /**
         * This function is run when the robot is first started up and
      should be used for any
         * initialization code.
         */
        @Override
        public void robotInit() {
          m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
          m_chooser.addOption("My Auto", kCustomAuto);
          //SmartDashboard.putData("1", m_chooser);
        }
      
        /**
         * This function is called every robot packet, no matter the mode.
      Use this for items like
         * diagnostics that you want ran during disabled, autonomous,
      teleoperated and test.
         *
         * <p>This runs after the mode specific periodic functions, but
      before LiveWindow and
         * SmartDashboard integrated updating.
         
        //@Override
        public void robotPeriodic() {}
      
        /**
         * This autonomous (along with the chooser code above) shows how to
      select between different
         * autonomous modes using the dashboard. The sendable chooser code
      works with the Java
         * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all
      of the chooser code and
         * uncomment the getString line to get the auto name from the text
      box below the Gyro
         *
         * <p>You can add additional auto modes by adding additional
      comparisons to the switch structure
         * below with additional strings. If using the SendableChooser make
      sure to add them to the
         * chooser code above as well.
         */
       // @Override
        //public void autonomousInit() {
          //m_autoSelected = m_chooser.getSelected();
          // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
          //System.out.println("Auto selected: " + m_autoSelected);
        }
      
        /** This function is called periodically during autonomous. */
        //@Override
        //public void autonomousPeriodic() {
      
        //}
      
        /** This function is called once when teleop is enabled. */
        //@Override
        //public void teleopInit() {}
      
        /** This function is called periodically during operator control. */
        //@Override
        //public void teleopPeriodic() {}
      
        /** This function is called once when the robot is disabled. */
        //@Override
        //public void disabledInit() {}
      
        /** This function is called periodically when disabled. */
        //@Override
        //public void disabledPeriodic() {}
      
        /** This function is called once when test mode is enabled. */
        //@Override
        //public void testInit() {}
      
        /** This function is called periodically during test mode. */
        //@Override
        //public void testPeriodic() {}
      
    /*
    * I'm trying to learn how to use PID and this is where I got
    * the code from:
    * https://www.youtube.com/watch?v=VoxeXqy1bdQ
    *
    * I'm not entirely sure how it all works right now. 
    *
    * Also here is the introduction and documentation for PID
    * INTRO:
    * https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/introduction-to-pid.html#introduction-to-pid
    *
    * DOCUMENTATION:
    * https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/pidcontroller.html
    *
    *
    * - AJ
    */
    //private final double kDriveTick2Feet = 1.0 / 128 * 6 * Math.PI / 12;
    //private final double kElevatorEncoderTick2Meter = 1.0 / 4096.0 * 0.1 * Math.PI;
    /* is the standard math-equation-focused way to notate the constant. */

    public final double kP;
    public final double kI;
    public final double kD;
    public final double kF;
    public int encoderTicksPerRevolution = 4096;
    public int targetPos = 0;

    public PID(double kp, double ki, double kd, double kf) {
        this.kP = kp;
        this.kI = kp;
        this.kD = kp;
        this.kF = kp;
    }

    public int update(TalonFX motor){

        int currentPosition = (int) motor.getSelectedSensorPosition();
        System.out.println("Position: " + currentPosition);

        return currentPosition;
    }
}
