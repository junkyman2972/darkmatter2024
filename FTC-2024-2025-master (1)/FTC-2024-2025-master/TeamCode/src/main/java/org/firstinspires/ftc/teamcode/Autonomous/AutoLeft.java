package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutoLeft", group = "Autonomous")

public class AutoLeft extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 537.7;    // eg: TETRIX Motor Encoder

    static final double COUNTS_PER_MOTOR_REV2 = 145.1;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double COUNTS_PER_INCH2 = (COUNTS_PER_MOTOR_REV2 * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;

    static final double DRIVE_SPEED_2 = 1.0;
    static final double TURN_SPEED = 0.5;

    long SLEEP_TIME = 500;

    DcMotorEx LeftT;

    DcMotorEx RightT;

    DcMotorEx LeftB;

    DcMotorEx RightB;

    DcMotorEx HorizontalIntake;

    DcMotorEx VerticalIntake;

    Servo IntakeRotate1;
    Servo IntakeRotate2;

    Servo ServoClaw;

    CRServo Star1;

    CRServo Star2;

    Servo MidArmServo;

    Servo ClawRotate;

    Servo ArmRotate1;

    Servo ArmRotate2;

    private ElapsedTime runtime = new ElapsedTime();

    private ElapsedTime runtime2 = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {

        LeftT = hardwareMap.get(DcMotorEx.class, "LeftT");
        LeftB = hardwareMap.get(DcMotorEx.class, "LeftB");
        RightT = hardwareMap.get(DcMotorEx.class, "RightT");
        RightB = hardwareMap.get(DcMotorEx.class, "RightB");
        Star1 = hardwareMap.get(CRServo.class, "Star1");
        Star2 = hardwareMap.get(CRServo.class, "Star2");
        IntakeRotate1 = hardwareMap.get(Servo.class, "IntakeRotate1");
        IntakeRotate2 = hardwareMap.get(Servo.class, "IntakeRotate2");
        ServoClaw = hardwareMap.get(Servo.class, "ServoClaw");
        HorizontalIntake = hardwareMap.get(DcMotorEx.class, "HorizontalIntake");
        VerticalIntake = hardwareMap.get(DcMotorEx.class, "VerticalIntake");
        MidArmServo = hardwareMap.get(Servo.class, "MidArmServo");
        ClawRotate = hardwareMap.get(Servo.class, "ClawRotate");
        ArmRotate1 = hardwareMap.get(Servo.class, "ArmRotate1");
        ArmRotate2 = hardwareMap.get(Servo.class, "ArmRotate2");


        LeftT.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightT.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HorizontalIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        VerticalIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HorizontalIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        ArmRotate1.setDirection(Servo.Direction.REVERSE);

        telemetry.addData("Path0", "Starting at %7d :%7d",
                LeftB.getCurrentPosition(),
                LeftT.getCurrentPosition());
        RightB.getCurrentPosition();
        RightT.getCurrentPosition();
        telemetry.update();


        //negative opens claw positive closes it
        init();
        ArmRotate1.setPosition(-0.2);
        ArmRotate2.setPosition(-0.2);
        MidArmServo.setPosition(0.35);
        ServoClaw.setPosition(0.22);
        waitForStart();


        encoderDrive(DRIVE_SPEED, 20, -20, -20, 20, 3);
        encoderDrive(DRIVE_SPEED, -10, 10, -10, 10, 3);
        encoderDriveSlide(DRIVE_SPEED_2, -10, 0, 1);
        VerticalIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(SLEEP_TIME);
        ArmRotate1.setPosition(0.9);
        ArmRotate2.setPosition(0.9);
        MidArmServo.setPosition(0.35);
        ClawRotate.setPosition(-0.7);
        ServoClaw.setPosition(0.22);
        sleep(1000);
        ServoClaw.setPosition(-0.22);
        sleep(SLEEP_TIME);
        ArmRotate1.setPosition(-0.2);
        ArmRotate2.setPosition(-0.2);
        sleep(SLEEP_TIME);
        //Move Left
        encoderDrive(DRIVE_SPEED, 35, -35, 35, -35, 3);
        //Move Forward
        encoderDrive(DRIVE_SPEED, 25, -25, -25, 25, 3);
        //Move Left
        encoderDrive(DRIVE_SPEED, 15, -15, 15, -15, 3);
        //Move Back
        encoderDrive(DRIVE_SPEED, -45, 45, 45, -45, 3);
        //Move Forward
        encoderDrive(DRIVE_SPEED, 45, -45, -45, 45, 3);
        //Move Left
        encoderDrive(DRIVE_SPEED, 15, -15, 15, -15, 3);
        //Move Back Medium
        encoderDrive(DRIVE_SPEED, -35, 35, 35, -35, 3);
        //Move Forward
        encoderDrive(DRIVE_SPEED, 45, -45, -45, 45, 3);
        //Move Left
        encoderDrive(DRIVE_SPEED, 15, -15, 15, -15, 3);
        //Move Back Short
        encoderDrive(DRIVE_SPEED, -25, 25, 25, -25, 3);

        //End Code

        //Turn Right
        encoderDrive(DRIVE_SPEED, 41, 41, 41, 41, 3);
        sleep(SLEEP_TIME);
        encoderDriveSlide(DRIVE_SPEED_2, 10, 0 ,3);
        sleep(SLEEP_TIME);

        //Rotating Arm
        ArmRotate1.setPosition(0.7);
        ArmRotate2.setPosition(0.7);
        MidArmServo.setPosition(0.3);
        ClawRotate.setPosition(0.7);
        sleep(1000);
        encoderDrive(DRIVE_SPEED, 5, -5, -5, 5, 3);
        sleep(SLEEP_TIME);
        ServoClaw.setPosition(0.22);
        sleep(SLEEP_TIME);
        encoderDriveSlide(DRIVE_SPEED_2, -10, 0 ,3);
        sleep(SLEEP_TIME);
        encoderDrive(0, 0, 0, 0, 0, 3);


        //commented out code for turning and picking up object
        /*encoderDrive(0.4, 6, -6, -6, 6, 3);
        sleep(SLEEP_TIME);
        servo5.setPosition(0.85);
        servo6.setPosition(0.85);
        servo3.setPosition(-0.35);
        servo4.setPosition(-0.7);
        sleep(SLEEP_TIME);
        encoderDrive(0.4, 6, -6, -6, 6, 3);
        sleep(SLEEP_TIME);
        servo1.setPosition(-0.3);
        servo2.setPosition(0.3);
        encoderDriveSlide(DRIVE_SPEED_2, 10, 0, 3);
        sleep(SLEEP_TIME);
        servo1.setPosition(0.3);
        servo2.setPosition(-0.3);
        sleep(SLEEP_TIME);
        encoderDriveSlide(DRIVE_SPEED_2, -5, 0, 3);*/

    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double rightBinches, double leftBinches,
                             double timeoutS) {
        int newLeftTarget;
        int newLeftBTarget;
        int newRightBTarget;
        int newRightTarget;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = LeftT.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = RightT.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newLeftBTarget = LeftB.getCurrentPosition() + (int) (leftBinches * COUNTS_PER_INCH);
            newRightBTarget = RightB.getCurrentPosition() + (int) (rightBinches * COUNTS_PER_INCH);

            LeftT.setTargetPosition(newLeftTarget);
            RightT.setTargetPosition(newRightTarget);
            LeftB.setTargetPosition(newLeftBTarget);
            RightB.setTargetPosition(newRightBTarget);

            // Turn On RUN_TO_POSITION
            LeftT.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightT.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            LeftT.setPower(Math.abs(speed));
            LeftB.setPower(Math.abs(speed));
            RightT.setPower(Math.abs(speed));
            RightB.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (LeftT.isBusy() && LeftB.isBusy() && RightT.isBusy() && RightB.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget, newRightBTarget, newLeftBTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        LeftT.getCurrentPosition(),
                        LeftB.getCurrentPosition());
                RightB.getCurrentPosition();
                RightT.getCurrentPosition();
                telemetry.update();
            }

            LeftT.setPower(0);
            LeftB.setPower(0);
            RightT.setPower(0);
            RightB.setPower(0);


            // Turn off RUN_TO_POSITION
            LeftT.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightT.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void encoderDriveSlide(double speed2,
                                  double VerticalInches, double HorizontalInches,
                                  double timeoutSS) {
        int NewVerticalTarget;
        int NewHorizontalTarget;

        if (opModeIsActive()) {
            NewVerticalTarget = VerticalIntake.getCurrentPosition() + (int) (VerticalInches * COUNTS_PER_INCH);
            NewHorizontalTarget = HorizontalIntake.getCurrentPosition() + (int) (HorizontalInches * COUNTS_PER_INCH2);

            VerticalIntake.setTargetPosition(NewVerticalTarget);
            HorizontalIntake.setTargetPosition(NewHorizontalTarget);

            HorizontalIntake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            VerticalIntake.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime2.reset();
            VerticalIntake.setPower(Math.abs(speed2));
            HorizontalIntake.setPower(Math.abs(speed2));

            while (opModeIsActive() && (runtime2.seconds() < timeoutSS) ||
                    (HorizontalIntake.isBusy() && VerticalIntake.isBusy())) {
                telemetry.addData("Path3", "Running to %7d :%7d", NewHorizontalTarget, NewVerticalTarget);
                telemetry.addData("Path4", "Running at %7d :%7d",
                        HorizontalIntake.getCurrentPosition(),
                        VerticalIntake.getCurrentPosition());
                telemetry.update();
            }

            HorizontalIntake.setPower(0);
            VerticalIntake.setPower(0);

            HorizontalIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            VerticalIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
