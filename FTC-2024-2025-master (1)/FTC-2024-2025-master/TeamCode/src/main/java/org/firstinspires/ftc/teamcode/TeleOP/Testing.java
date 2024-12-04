package org.firstinspires.ftc.teamcode.TeleOP;

import androidx.appcompat.app.ActionBar;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "TeleOp", name = "Test")

public class Testing extends LinearOpMode {

    DcMotorEx LeftT;
    DcMotorEx RightT;
    DcMotorEx LeftB;
    DcMotorEx RightB;
    Servo ServoClaw;

    DcMotorEx HorizontalIntake;

    DcMotorEx VerticalIntake;

    Servo IntakeRotate1;
    Servo IntakeRotate2;

    CRServo Star1;

    CRServo Star2;

    Servo MidArmServo;

    Servo ClawRotate;

    Servo ArmRotate1;

    Servo ArmRotate2;

    boolean horizontalIntakeStop = false;

    double DriveSpeed = 0.4;

    double VerticalIntakeSpeed = 1;

    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  0.33;     // Maximum rotational position
    static final double MIN_POS     =  0.0;
    static final double MAX_POSpoint    =  0.22;
    static final double MIN_POSpoint     =  0.0;

    double positionpoint = (MAX_POSpoint - MIN_POSpoint) / 2;
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;

    boolean aDepressed = true;

    static final double MAX_POS2     =  0.3;     // Maximum rotational position
    static final double MIN_POS2     =  0.0;      // Minimum rotational position

    static final double MAX_POS3     =  0.7;     // Maximum rotational position
    static final double MIN_POS3    =  0.0;

    int CYCLETIME = 1000;

    static final double MAX_POS4     =  0.2;     // Maximum rotational

    static final double MAX_POS4part2     =  0.6;     // Maximum rotational

    static final double MAX_POS4part3     =  0.6;     // Maximum rotational

    static final double MIN_POS4part3    =  0.0;


    static final double MIN_POS4    =  0.0;

    static final double MIN_POS4part2    =  0.0;
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
        IntakeRotate1 = hardwareMap.get(Servo.class, "IntakeRotate1");
        IntakeRotate2 = hardwareMap.get(Servo.class, "IntakeRotate2");
        Star1 = hardwareMap.get(CRServo.class, "Star1");
        Star2 = hardwareMap.get(CRServo.class, "Star2");
        HorizontalIntake = hardwareMap.get(DcMotorEx.class, "HorizontalIntake");
        VerticalIntake = hardwareMap.get(DcMotorEx.class, "VerticalIntake");
        ServoClaw = hardwareMap.get(Servo.class, "ServoClaw");
        MidArmServo = hardwareMap.get(Servo.class, "MidArmServo");
        ClawRotate = hardwareMap.get(Servo.class, "ClawRotate");
        ArmRotate1 = hardwareMap.get(Servo.class, "ArmRotate1");
        ArmRotate2 = hardwareMap.get(Servo.class, "ArmRotate2");

        HorizontalIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        IntakeRotate1.setDirection(Servo.Direction.REVERSE);
        HorizontalIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        ArmRotate1.setDirection(Servo.Direction.REVERSE);



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

        /*if (gamepad2.a = false) {
            aDepressed = true;
        }
        if (gamepad2.a && aDepressed) {
            if (IntakeRotate1.getPosition() == position && IntakeRotate2.getPosition() == position) {
                IntakeRotate1.setPosition(position);
                IntakeRotate2.setPosition(position);
            }
            else {
                IntakeRotate1.setPosition(-position);
                IntakeRotate2.setPosition(-position);
            }
            aDepressed = false;
        }*/
        //IntakeRotate1.setPosition(position * (gamepad2.right_stick_y) - (gamepad2.right_stick_y) + (gamepad2.right_stick_x));
        //IntakeRotate2.setPosition(position * (gamepad2.right_stick_y) - (gamepad2.right_stick_y) + (gamepad2.right_stick_x));


        if (gamepad2.a) {
            IntakeRotate1.setPosition(0.67);
            IntakeRotate2.setPosition(0.67);
        }
        else {
            IntakeRotate2.setPosition(-position);
            IntakeRotate1.setPosition(-position);
        }
    }

    //set to Servo Port 2
    public void ContinueServo () {
        Star1.setPower(0.5 * (gamepad2.right_trigger) - (gamepad2.left_trigger));
        Star2.setPower(0.5 * -(gamepad2.right_trigger) + (gamepad2.left_trigger));
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


        if (horizontalIntakeStop == false) {
            HorizontalIntake.setPower(0.5 * (gamepad2.left_stick_y) - (gamepad2.left_stick_y));
        }
        else if (horizontalIntakeStop == true) {
            HorizontalIntake.setPower(0);
        }


        if (gamepad2.right_bumper) {
            VerticalIntake.setPower(-1);
        }
        else if (gamepad2.left_bumper) {
            VerticalIntake.setPower(1);
        }
        else {
            VerticalIntake.setPower(-0.1);
        }
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
        if (gamepad2.dpad_down) {
            ServoClaw.setPosition(-positionpoint);
            horizontalIntakeStop = false;
        }
        if (gamepad2.x) {
            ServoClaw.setPosition(positionpoint);
        }
        else if (gamepad2.y) {
            ServoClaw.setPosition(-positionpoint);
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
            MidArmServo.setPosition(position2);
        }
        if (gamepad2.dpad_down) {
            sleep(500);
            MidArmServo.setPosition(-position2);
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
        if (gamepad2.b) {
            sleep(200);
            ClawRotate.setPosition(-position3);
        }
        if (gamepad2.dpad_up) {
            ClawRotate.setPosition(-position3);
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
        if (gamepad2.dpad_up) {
            ArmRotate1.setPosition(0.35);
            ArmRotate2.setPosition(0.35);
            horizontalIntakeStop = true;
        }
        else if (gamepad2.dpad_left) {
            ArmRotate1.setPosition(position4part3);
            ArmRotate2.setPosition(position4part3);
            MidArmServo.setPosition(position2);
            ClawRotate.setPosition(-position3);
            horizontalIntakeStop = true;
        }
        else if (gamepad2.dpad_right) {
            VerticalIntake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            VerticalIntake.setTargetPosition(-1000);
            ArmRotate1.setPosition(position4part2);
            ArmRotate2.setPosition(position4part2);
            MidArmServo.setPosition(-position2);
            ClawRotate.setPosition(-position3);
            horizontalIntakeStop = true;
        }
        if (gamepad2.dpad_down) {
            VerticalIntake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            VerticalIntake.setTargetPosition(0);
            ClawRotate.setPosition(0.65);
        }
        if (gamepad2.dpad_down) {
            sleep(500);
            ArmRotate1.setPosition(-position4);
            ArmRotate2.setPosition(-position4);
        }
        while (VerticalIntake.isMotorEnabled() && VerticalIntake.isBusy()) {
            HorizontalIntake.setMotorDisable();
            HorizontalIntake.setPower(0);
        }
        /*if (gamepad2.dpad_right) {

        }*/
        telemetry.addData("Rotate1 Position:", ArmRotate1.getPosition());
        telemetry.addData("Rotate2 Position:", ArmRotate2.getPosition());
        telemetry.update();
    }
 }
