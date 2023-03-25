package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {

    private Joystick controller;
    private Joystick control2;
    private double[] sticks1 = new double[2];
    private double[] sticks2 = new double[2];

    private boolean lb, rb, lb2, rb2;
    private boolean A, B, A2, B2;
    private boolean X, Y, X2, Y2;

    private double lt, rt;
    
    public Controls(Joystick controller, Joystick control2){
        this.controller = controller;
        this.control2 = control2;
    }

    public double[] getSticksOne(){
        sticks1[0] = controller.getRawAxis(1);
        sticks1[1] = controller.getRawAxis(5);
        return sticks1;
    }

    public double[] getSticksTwo(){
        sticks2[0] = control2.getRawAxis(1);
        sticks2[1] = control2.getRawAxis(5);
        return sticks2;
    }

    public double getLT(){
        lt = control2.getRawAxis(2);
        return lt;
    }

    public double getRT(){
        rt = control2.getRawAxis(3);
        return rt;
    }

    public boolean getLB2(){
        lb2 = control2.getRawButton(5);
        return lb2;
    }

    public boolean getRB2(){
        rb2 = control2.getRawButton(6);
        return rb2;
    }

    public boolean getLB(){
        lb = controller.getRawButton(5);
        return lb;
    }

    public boolean getRB(){
        rb = controller.getRawButton(6);
        return rb;
    }

    public boolean getA(){
        A = controller.getRawButton(1);
        return A;
    }

    public boolean getB(){
        B = controller.getRawButton(2);
        return B;
    }
    public boolean getA2(){
        A2 = control2.getRawButton(1);
        return A2;
    }

    public boolean getB2(){
        B2 = control2.getRawButton(2);
        return B2;
    }

    public boolean getX(){
        X = controller.getRawButton(3);
        return X;
    }

    public boolean getY(){
        Y = controller.getRawButton(4);
        return Y;
    }

    public boolean getX2(){
        X2 = control2.getRawButton(3);
        return X2;
    }

    public boolean getY2(){
        Y2 = control2.getRawButton(4);
        return Y2;
    }
}
