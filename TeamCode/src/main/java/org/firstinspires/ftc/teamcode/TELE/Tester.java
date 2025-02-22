package org.firstinspires.ftc.teamcode.TELE;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class Tester extends LinearOpMode {

    public static double intakeTurnS = 1;
    public static double intakeTurnE = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        // Hardware

        Servo intakeLeft = hardwareMap.get(Servo.class, "intakeLeft");
        Servo intakeRight = hardwareMap.get(Servo.class, "intakeRight");

//        // Servo claw positions
        double intakeStart = .52;
        double intakeEnd = .62;
        boolean intakeAtStart = true; // Track if servoArm is at its start position
        intakeLeft.setPosition(intakeStart);

        double intakeTurnStart = intakeTurnS;
        double intakeTurnEnd = intakeTurnE;
        boolean intakeTurnAtStart = true; // Track if servoArm is at its start position
        boolean intakeTurnAtMid = true; // Track if servoArm is at its start position
        intakeRight.setPosition(intakeTurnStart);
//
//        // Servo claw positions
        boolean xPressed = false;
        boolean yPressed = false;
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {


            if (gamepad1.y && !yPressed) {
                if (intakeAtStart) {
                    intakeLeft.setPosition(intakeEnd);
                    intakeRight.setPosition(intakeTurnEnd);
                } else {
                    intakeLeft.setPosition(intakeStart);
                    intakeRight.setPosition(intakeTurnStart);
                }
                intakeAtStart = !intakeAtStart;
                yPressed = true;
            }

            if (!gamepad1.y) {
                yPressed = false;
            }

            if (gamepad1.x) {
                intakeLeft.setPosition(.75);
                sleep(1000);
//                intakeClaw.setPosition(intakeClawEndPosition);
//                sleep(1000);
                intakeLeft.setPosition(.62);
//                intakeSwivel.setPosition(intakeSwivelStart);
            }


        }

    }

}
