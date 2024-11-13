package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "TeleOp", name = "Test")

public class Testing extends LinearOpMode {

    DcMotorEx LeftT;
    DcMotorEx RightT;
    DcMotorEx LeftB;
    DcMotorEx RightB;

    DcMotorEx HorizontalIntake;

    DcMotorEx VerticalIntake;

    Servo Intake1;
    Servo Intake2;

    CRServo Intake;

    Servo servo1;
    Servo servo2;

    Servo servo3;

    Servo servo4;

    Servo servo5;

    Servo servo6;

    double DriveSpeed = 0.4;

    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  0.55;     // Maximum rotational position
    static final double MIN_POS     =  0.0;
    static final double MAX_POSpoint    =  0.3;
    static final double MIN_POSpoint     =  0.0;

    double positionpoint = (MAX_POSpoint - MIN_POSpoint) / 2;
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;

    static final double MAX_POS2     =  0.35;     // Maximum rotational position
    static final double MIN_POS2     =  0.0;

    static final double MAX_POS3     =  0.7;     // Maximum rotational position
    static final double MIN_POS3    =  0.0;

    int CYCLETIME = 1000;

    static final double MAX_POS4     =  0.3;     // Maximum rotational

    static final double MAX_POS4part2     =  0.0;     // Maximum rotational

    static final double MAX_POS4part3     =  0.8;     // Maximum rotational

    static final double MIN_POS4part3    =  0.0;


    static final double MIN_POS4    =  0.0;

    static final double MIN_POS4part2    =  0.65;
    double  position2 = (MAX_POS2 - MIN_POS2) / 2; // Start at halfway position

    double  position3 = (MAX_POS3 - MIN_POS3) / 2; // Start at halfway position

    double  position4 = (MAX_POS4 - MIN_POS4) / 2; // Start at halfway position

    double position4part2 = (MAX_POS4part2 - MIN_POS4part2) / 2;
    double position4part3 = (MAX_POS4part3 - MIN_POS4part3) / 2;

    @Override
    public void runOpMode() throws InterruptedException {
        LeftT = hardwareMap.get(DcMotorEx.class, "LeftT");
        RightT = hardwareMap.get(DcMotorEx.class, "RightT");
        LeftB = hardwareMap.get(DcMotorEx.class, "LeftB");
        RightB = hardwareMap.get(DcMotorEx.class, "RightB");
        Intake1 = hardwareMap.get(Servo.class, "Intake1");
        Intake2 = hardwareMap.get(Servo.class, "Intake2");
        Intake = hardwareMap.get(CRServo.class, "Intake");
        HorizontalIntake = hardwareMap.get(DcMotorEx.class, "HorizontalIntake");
        VerticalIntake = hardwareMap.get(DcMotorEx.class, "VerticalIntake");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        servo4 = hardwareMap.get(Servo.class, "servo4");
        servo5 = hardwareMap.get(Servo.class, "servo5");
        servo6 = hardwareMap.get(Servo.class, "servo6");

        Intake1.setDirection(Servo.Direction.REVERSE);
        HorizontalIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        servo6.setDirection(Servo.Direction.REVERSE);



        waitForStart();


        while (opModeIsActive() && !isStopRequested()) {
            GP1Controls();
            Servo();
            ContinueServo();
            HoriIntake();
            FourServoBottom();
            FourServoIntake();
            FourServoIntakeCloser();
            FourServoRotate();
        }
    }
    public void GP1Controls () {


        LeftT.setPower(DriveSpeed * ((gamepad1.left_stick_y) + (gamepad1.left_stick_x) - (gamepad1.right_stick_x)));
        LeftB.setPower(DriveSpeed * ((gamepad1.left_stick_y) - (gamepad1.left_stick_x) - (gamepad1.right_stick_x)));
        RightT.setPower(DriveSpeed * ((-gamepad1.left_stick_y) - (gamepad1.left_stick_x) - (gamepad1.right_stick_x)));
        RightB.setPower(DriveSpeed * ((-gamepad1.left_stick_y) + (gamepad1.left_stick_x) - (gamepad1.right_stick_x)));

        if (gamepad1.right_bumper) {
            DriveSpeed = 1;
        }
        else {
            DriveSpeed = 0.4;
        }
        /*if (gamepad1.right_bumper) {
            DriveSpeed = 1;
        }
        else if (gamepad1.left_bumper) {
            DriveSpeed = 0.4;
        }*/
    }

    //set to Servo Port 0 and 1
    public void Servo () {
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

        //Intake1.setPosition(position * (gamepad2.right_stick_y) - (gamepad2.right_stick_y));
        //Intake2.setPosition(position * (gamepad2.right_stick_y) - (gamepad2.right_stick_y));


        if (gamepad2.left_bumper) {
            Intake1.setPosition(position);
            Intake2.setPosition(position);
        }
        else if (gamepad2.right_bumper) {
            Intake2.setPosition(-position);
            Intake1.setPosition(-position);
        }
    }

    //set to Servo Port 2
    public void ContinueServo () {
        Intake.setPower(0.5 * -(gamepad2.right_trigger) + (gamepad2.left_trigger));
        /*if (gamepad2.dpad_up) {
            Intake.setPower(0.5);
        }
        else if (gamepad2.dpad_down) {
            Intake.setPower(-0.5);
        }
        else {
            Intake.setPower(0);
        }*/
    }
    public void HoriIntake () {
        HorizontalIntake.setPower(0.5 * -(gamepad2.left_stick_y) + (gamepad2.left_stick_y));


        VerticalIntake.setPower(1 * (gamepad2.right_stick_y) - (gamepad2.right_stick_y));
        /*if (gamepad2.right_bumper) {
            VerticalIntake.setPower(1);
        }
        else if (gamepad2.left_bumper) {
            VerticalIntake.setPower(-1);
        }
        else {
            VerticalIntake.setPower(0);
        }*/
    }
    public void FourServoIntakeCloser () {
        if (rampUp) {
            // Keep stepping up until we hit the max value.
            positionpoint += INCREMENT ;
            if (positionpoint >= MAX_POSpoint ) {
                positionpoint = MAX_POSpoint;
            }
        }
        else {
            // Keep stepping down until we hit the min value.
            positionpoint -= INCREMENT ;
            if (positionpoint <= MIN_POSpoint ) {
                positionpoint = MIN_POSpoint;
            }
        }
        if (gamepad2.b) {
            servo1.setPosition(-positionpoint);
            servo2.setPosition(positionpoint);
        }
        if (gamepad2.x) {
            servo1.setPosition(-positionpoint);
            servo2.setPosition(positionpoint);
        }
        if (gamepad2.y) {
            servo1.setPosition(positionpoint);
            servo2.setPosition(-positionpoint);
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
        if (gamepad2.b) {
            sleep(500);
            servo3.setPosition(-position2);
        }
    }
    public void FourServoIntake () {
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
        if (gamepad2.dpad_up) {
            sleep(200);
            servo4.setPosition(-position3);
        }
        if (gamepad2.b) {
            servo4.setPosition(0.35);
        }
        if (gamepad2.dpad_down) {
            servo4.setPosition(position3);
        }
    }
    public void FourServoBottom () {
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
            servo6.setPosition(position4);
        }
        else if (gamepad2.a) {
            servo5.setPosition(position4part3);
            servo6.setPosition(position4part3);
        }
        if (gamepad2.dpad_right) {
            servo5.setPosition(-position4);
            servo6.setPosition(-position4);
        }
        telemetry.addData("Servo5 Position:", servo5.getPosition());
        telemetry.addData("Servo6 Position:", servo6.getPosition());
        telemetry.update();
    }
 }
