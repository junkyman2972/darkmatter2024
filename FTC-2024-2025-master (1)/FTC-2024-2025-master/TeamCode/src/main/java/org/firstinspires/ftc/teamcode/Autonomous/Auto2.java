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
public class Auto2 extends LinearOpMode {

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

    private ElapsedTime runtime = new ElapsedTime();

    private ElapsedTime runtime2 = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {

        LeftT = hardwareMap.get(DcMotorEx.class, "LeftT");
        LeftB = hardwareMap.get(DcMotorEx.class, "LeftB");
        RightT = hardwareMap.get(DcMotorEx.class, "RightT");
        RightB = hardwareMap.get(DcMotorEx.class, "RightB");
        Intake = hardwareMap.get(CRServo.class, "Intake");
        Intake1 = hardwareMap.get(Servo.class, "Intake1");
        Intake2 = hardwareMap.get(Servo.class, "Intake2");
        HorizontalIntake = hardwareMap.get(DcMotorEx.class, "HorizontalIntake");
        VerticalIntake = hardwareMap.get(DcMotorEx.class, "VerticalIntake");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        servo4 = hardwareMap.get(Servo.class, "servo4");
        servo5 = hardwareMap.get(Servo.class, "servo5");
        servo6 = hardwareMap.get(Servo.class, "servo6");
        LeftT.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightT.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HorizontalIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        VerticalIntake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Intake1.setDirection(Servo.Direction.REVERSE);
        HorizontalIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        servo6.setDirection(Servo.Direction.REVERSE);

        telemetry.addData("Path0", "Starting at %7d :%7d",
                LeftB.getCurrentPosition(),
                LeftT.getCurrentPosition());
        RightB.getCurrentPosition();
        RightT.getCurrentPosition();
        telemetry.update();

        init();
        servo5.setPosition(-0.2);
        servo6.setPosition(-0.2);
        servo3.setPosition(0.35);
        waitForStart();


        encoderDrive(DRIVE_SPEED, 25, -25, -25, 25, 3);
        encoderDrive(DRIVE_SPEED, -10, 10, -10, 10, 3);
        encoderDriveSlide(DRIVE_SPEED_2, -10, 0, 3);
        VerticalIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(500);
        servo5.setPosition(0.9);
        servo6.setPosition(0.9);
        servo3.setPosition(0.35);
        servo4.setPosition(-0.7);
        servo1.setPosition(0.3);
        servo2.setPosition(-0.3);
        sleep(2000);
        servo1.setPosition(-0.3);
        servo2.setPosition(0.3);
        sleep(500);
        servo5.setPosition(0.5);
        servo6.setPosition(0.5);
        sleep(500);
        encoderDrive(DRIVE_SPEED, 30, -30, 30, -30, 3);
        encoderDrive(DRIVE_SPEED, -20, 20, 20, -20, 3);


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
