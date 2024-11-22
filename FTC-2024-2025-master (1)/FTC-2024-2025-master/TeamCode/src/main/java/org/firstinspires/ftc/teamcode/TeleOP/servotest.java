package org.firstinspires.ftc.teamcode.TeleOP;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "servotest", group = "Test")
@Disabled
public class servotest extends LinearOpMode {

    CRServo one;
    CRServo two;
    @Override
    public void runOpMode() throws InterruptedException {
        one = hardwareMap.get(CRServo.class, "one");
        two = hardwareMap.get(CRServo.class, "two");

        two.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            oneman();
        }
    }
    public void oneman () {
        if (gamepad1.left_bumper) {
            one.setPower(1);
            two.setPower(1);
        }
        else if (gamepad1.right_bumper) {
            one.setPower(-1);
            two.setPower(-1);
        }
        else {
            one.setPower(0);
            two.setPower(0);
        }
    }
}
