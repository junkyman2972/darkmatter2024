package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ServoTest", group = "Testing")

public class servo12856 extends LinearOpMode {

    //Servo Intake1;
    //Servo Intake2;

    Servo servo1;
    Servo servo2;

    Servo servo3;

    Servo servo4;

    Servo servo5;

    Servo servo6;

    //CRServo Intake;

   static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
   static final int    CYCLE_MS    =   50;     // period of each cycle
   static final double MAX_POS     =  0.3;     // Maximum rotational position
   static final double MIN_POS     =  0.0;

   static final double MAX_POS2     =  0.35;     // Maximum rotational position
    static final double MIN_POS2     =  0.0;

    static final double MAX_POS3     =  0.65;     // Maximum rotational position
    static final double MIN_POS3    =  0.0;

    static final double MAX_POS4     =  0.5;     // Maximum rotational

    static final double MAX_POS4part2     =  0.65;     // Maximum rotational

    static final double MAX_POS4part3     =  0.65;     // Maximum rotational

    static final double MIN_POS4part3    =  1.0;


    static final double MIN_POS4    =  0.0;

    static final double MIN_POS4part2    =  0.0;
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position

    double  position2 = (MAX_POS2 - MIN_POS2) / 2; // Start at halfway position

    double  position3 = (MAX_POS3 - MIN_POS3) / 2; // Start at halfway position

    double  position4 = (MAX_POS4 - MIN_POS4) / 2; // Start at halfway position

    double position4part2 = (MAX_POS4part2 - MIN_POS4part2) / 2;
    double position4part3 = (MAX_POS4part3 - MIN_POS4part3) / 2;



    boolean rampUp = true;

    @Override
    public void runOpMode() throws InterruptedException {
       // Intake1 = hardwareMap.get(Servo.class, "Intake1");
     //   Intake2 = hardwareMap.get(Servo.class, "Intake2");
        //Intake = hardwareMap.get(CRServo.class, "Intake");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        servo4 = hardwareMap.get(Servo.class, "servo4");
        servo5 = hardwareMap.get(Servo.class, "servo5");
        servo6 = hardwareMap.get(Servo.class, "servo6");

       // Intake1.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            ServoControl();
            ContinueServo();
            FourServosIntakeCloser();
            FourServoRotate();
            FourServoRotateIntake();
            FourServoBottomRotation();
        }
    }
    public void ServoControl () {
        /*if (rampUp) {
            // Keep stepping up until we hit the max value.
            position += INCREMENT ;
            if (position >= MAX_POS ) {
                position = MAX_POS;
            }
        }
        else {
            // Keep stepping down until we hit the min value.
            position -= INCREMENT ;
            if (position <= MIN_POS ) {
                position = MIN_POS;
            }
        }
        if (gamepad1.left_bumper) {
            Intake1.setPosition(position);
            Intake2.setPosition(position);
        }
        if (gamepad1.right_bumper) {
            Intake2.setPosition(-position);
            Intake1.setPosition(-position);
        }*/
    }
    public void ContinueServo () {
        /*if (gamepad1.dpad_up) {
            Intake.setPower(0.5);
        }
        else if (gamepad1.dpad_down) {
            Intake.setPower(-0.5);
        }
        else {
            Intake.setPower(0);
        }*/
    }
    public void FourServosIntakeCloser () {
        if (rampUp) {
            // Keep stepping up until we hit the max value.
            position += INCREMENT ;
            if (position >= MAX_POS ) {
                position = MAX_POS;
            }
        }
        else {
            // Keep stepping down until we hit the min value.
            position -= INCREMENT ;
            if (position <= MIN_POS ) {
                position = MIN_POS;
            }
        }
        if (gamepad2.left_bumper) {
            servo1.setPosition(-position);
            servo2.setPosition(position);
        }
        if (gamepad2.right_bumper) {
            servo1.setPosition(position);
            servo2.setPosition(-position);
        }
    }
    public void FourServoRotate () {
        if (rampUp) {
            // Keep stepping up until we hit the max value.
            position2 += INCREMENT ;
            if (position2 >= MAX_POS2 ) {
                position2 = MAX_POS2;
            }
        }
        else {
            // Keep stepping down until we hit the min value.
            position2 -= INCREMENT ;
            if (position2 <= MIN_POS2 ) {
                position2 = MIN_POS2;
            }
        }
        if (gamepad2.dpad_up) {
            servo3.setPosition(position2);
        }
        if (gamepad2.dpad_down) {
            servo3.setPosition(-position2);
        }
    }
    public void FourServoRotateIntake () {
        if (rampUp) {
            // Keep stepping up until we hit the max value.
            position3 += INCREMENT ;
            if (position3 >= MAX_POS3 ) {
                position3 = MAX_POS3;
            }
        }
        else {
            // Keep stepping down until we hit the min value.
            position3 -= INCREMENT ;
            if (position3 <= MIN_POS3 ) {
                position3 = MIN_POS3;
            }
        }
        if (gamepad2.x) {
            servo4.setPosition(position3);
        }
        if (gamepad2.b) {
            servo4.setPosition(position3 / 2);
        }
        if (gamepad2.y) {
            servo4.setPosition(-position3);
        }
    }
    public void FourServoBottomRotation () {
        if (rampUp) {
            // Keep stepping up until we hit the max value.
            position4 += INCREMENT ;
            if (position4 >= MAX_POS4 ) {
                position4 = MAX_POS4;
            }
        }
        else {
            // Keep stepping down until we hit the min value.
            position4 -= INCREMENT ;
            if (position4 <= MIN_POS4) {
                position4 = MIN_POS4;
            }
        }
        if (rampUp) {
            // Keep stepping up until we hit the max value.
            position4part2 += INCREMENT ;
            if (position4part2 >= MAX_POS4part2) {
                position4part2 = MAX_POS4part2;
            }
        }
        else {
            // Keep stepping down until we hit the min value.
            position4part2 -= INCREMENT ;
            if (position4part2 <= MIN_POS4part2) {
                position4part2 = MIN_POS4part2;
            }
        }
        if (rampUp) {
            // Keep stepping up until we hit the max value.
            position4part3 += INCREMENT ;
            if (position4part3 >= MAX_POS4part3) {
                position4part3 = MAX_POS4part3;
            }
        }
        else {
            // Keep stepping down until we hit the min value.
            position4part3 -= INCREMENT ;
            if (position4part3 <= MIN_POS4part3) {
                position4part3 = MIN_POS4part3;
            }
        }
        if (gamepad2.dpad_left) {
            servo5.setPosition(position4);
            servo6.setPosition(-position4);
        }
        if (gamepad2.dpad_right) {
            servo5.setPosition(-position4);
            servo6.setPosition(position4);
        }
        if (gamepad2.a) {
            servo6.setDirection(Servo.Direction.REVERSE);
            servo5.setPosition(position4part2);
            servo6.setPosition(position4part3);
        }
    }
}
