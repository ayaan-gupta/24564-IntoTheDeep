package org.firstinspires.ftc.teamcode.TELE;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class MecaTestFr extends LinearOpMode {

    public static double servo0val = .06;
    public static double servo1Val = .74;
    public static double intakeS = .5;
    public static double intakeE = .64;
    public static double intakeTurnS = 1;
    public static double intakeTurnE = .1;
    public static double armVal = .74;


    @Override
    public void runOpMode() throws InterruptedException {
        // Hardware

        Servo servo0 = hardwareMap.get(Servo.class, "servo0");
        Servo servo1 = hardwareMap.get(Servo.class, "servo1");
        Servo armRight = hardwareMap.get(Servo.class, "armRight");
        Servo armLeft = hardwareMap.get(Servo.class, "armLeft");
        Servo armClaw = hardwareMap.get(Servo.class, "armClaw");
        Servo intakeLeft = hardwareMap.get(Servo.class, "intakeLeft");
        Servo intakeRight = hardwareMap.get(Servo.class, "intakeRight");
        Servo intakeSwivel = hardwareMap.get(Servo.class, "intakeSwivel");
        Servo intakeClaw = hardwareMap.get(Servo.class, "intakeClaw");

        // Known positions for Servo 0
        double servo0StartPosition = servo0val; // regular is .68
        double servo0EndPosition = .655; //.29
        boolean isServo0AtStart = true; // Track if servo0 is at its start position
        servo0.setPosition(servo0StartPosition);

        // Known positions for Servo 1 (adjusted for physical direction)
        double servo1StartPosition = servo1Val; //regular is .5
        double servo1EndPosition = .14;   // .89
        boolean isServo1AtStart = true; // Track if servo1 is at its start position
        servo1.setPosition(servo1StartPosition);

        boolean bumperPressed = false; // Prevent rapid toggling

        // Servo arm positions
        double servoArmStartPosition = armVal;
        double servoArmEndPosition = 0;
        boolean isServoArmAtStart = true; // Track if servoArm is at its start position
        armLeft.setPosition(servoArmStartPosition);
        armRight.setPosition(1 - servoArmStartPosition);
//
//        // Servo claw positions
        double armClawStartPosition = .65;
        double armClawEndPosition = .22;
        boolean isArmClawAtStart = false; // Track if servoArm is at its start position
        armClaw.setPosition(armClawStartPosition);
//
//        // Servo claw positions
        double intakeStart = intakeS;
        double intakeMid = .21;
        double intakeEnd = intakeE;
        boolean intakeAtStart = true; // Track if servoArm is at its start position
        boolean intakeAtMid = true; // Track if servoArm is at its start position
        intakeLeft.setPosition(intakeStart);
//
//        // Servo claw positions
        double intakeTurnStart = intakeTurnS;
        double intakeTurnEnd = intakeTurnE;
        boolean intakeTurnAtStart = true; // Track if servoArm is at its start position
        boolean intakeTurnAtMid = true; // Track if servoArm is at its start position
        intakeRight.setPosition(intakeTurnStart);
//
        double intakeSwivelStart = .51;
        double intakeSwivelRight = .397;
        double intakeSwivelLeft = .703;
        double intakeSwivelEnd = .225;
        boolean isIntakeSwivelAtStart = true; // Track if servoArm is at its start position
        intakeSwivel.setPosition(intakeSwivelStart);
//
//        // Servo claw positions
        double intakeClawStartPosition = .35;
        double intakeClawEndPosition = .05;
        boolean isIntakeClawAtStart = true; // Track if servoArm is at its start position
        intakeClaw.setPosition(intakeClawStartPosition);

        boolean leftBumperPressed = false;
        boolean aPressed = false;
        boolean bPressed = false;
        boolean yPressed = false;
        boolean xPressed = false;
        boolean dpadPressed = false;
        boolean dpadRightPressed = false;
        boolean dpadLeftPressed = false;
        boolean dpadDownPressed = false;
        boolean dpadUpPressed = false;
        boolean rightBumper2Pressed = false;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // Toggle Servo 0 and Servo 1 positions with the right bumper
            if (gamepad1.right_bumper && !bumperPressed) {
                // Toggle Servo 0
                if (isServo0AtStart) {
                    servo0.setPosition(servo0EndPosition);
                } else {
//                    if(!intakeAtStart)
//                        {
//                            intakeLeft.setPosition(intakeStart);
//                            intakeRight.setPosition(intakeTurnStart);
//                        intakeAtStart = !intakeAtStart;
//                        intakeTurnAtStart = !intakeTurnAtStart;
//                    }
                    servo0.setPosition(servo0StartPosition);
                }
                isServo0AtStart = !isServo0AtStart; // Toggle the state

                // Toggle Servo 1 (same physical direction)
                if (isServo1AtStart) {
                    servo1.setPosition(servo1EndPosition); // Matches servo0
                } else {
                    servo1.setPosition(servo1StartPosition); // Matches servo0
                }
                isServo1AtStart = !isServo1AtStart; // Toggle the state

                bumperPressed = true;
            }

            if (!gamepad1.right_bumper) {
                bumperPressed = false;
            }


            if (gamepad1.y && !yPressed) {
                if (intakeAtStart) {
                    intakeLeft.setPosition(intakeEnd);
                    intakeRight.setPosition(intakeTurnEnd);
                } else {
                    intakeLeft.setPosition(intakeStart);
                    intakeRight.setPosition(intakeTurnStart);
                }
                intakeAtStart = !intakeAtStart;
                intakeTurnAtStart = !intakeTurnAtStart;
                yPressed = true;
            }

            if (!gamepad1.y) {
                yPressed = false;
            }

            if (gamepad1.x) {
                intakeLeft.setPosition(.72);
                sleep(75);
                intakeClaw.setPosition(intakeClawEndPosition);
                intakeLeft.setPosition(intakeEnd);
                intakeSwivel.setPosition(intakeSwivelStart);
            }

            if (gamepad1.b && !bPressed && intakeAtStart && isServoArmAtStart) {
                if(!isArmClawAtStart){
                    armClaw.setPosition(armClawStartPosition);
                    isArmClawAtStart = true;
                }
                armClaw.setPosition(armClawEndPosition);
                sleep(100);
                intakeClaw.setPosition(intakeClawStartPosition);



                isIntakeClawAtStart = true;

                armLeft.setPosition(servoArmEndPosition);
                armRight.setPosition(1 - servoArmEndPosition);
                isServoArmAtStart = false;
                bPressed = true;
            }

            if (!gamepad1.b) {
                bPressed = false;
            }

            if (gamepad1.dpad_right && !dpadRightPressed) {
                intakeSwivel.setPosition(intakeSwivelRight);

                isIntakeSwivelAtStart = false; // Toggle the state
                dpadRightPressed = true;
            }

            if (!gamepad1.dpad_right) {
                dpadRightPressed = false;
            }

            if (gamepad1.dpad_left && !dpadLeftPressed) {
                intakeSwivel.setPosition(intakeSwivelLeft);

                isIntakeSwivelAtStart = false; // Toggle the state
                dpadLeftPressed = true;
            }

            if (!gamepad1.dpad_left) {
                dpadLeftPressed = false;
            }

            if (gamepad1.dpad_down && !dpadDownPressed) {
                intakeSwivel.setPosition(intakeSwivelEnd);

                isIntakeSwivelAtStart = false; // Toggle the state
                dpadDownPressed = true;
            }

            if (!gamepad1.dpad_down) {
                dpadDownPressed = false;
            }

            if (gamepad1.dpad_up && !dpadUpPressed) {
                intakeSwivel.setPosition(intakeSwivelStart);

                isIntakeSwivelAtStart = false; // Toggle the state
                dpadUpPressed = true;
            }

            if (!gamepad1.dpad_up) {
                dpadUpPressed = false;
            }

        }

    }


}
