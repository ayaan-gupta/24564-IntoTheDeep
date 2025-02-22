package org.firstinspires.ftc.teamcode.TELE;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class CompTele extends LinearOpMode {

    public static double servo0val = 0;
    public static double servo1Val = 1;
    public static double intakeS = .48;
    public static double intakeE = .30;
    public static double intakeTurnS = 1;
    public static double intakeTurnE = 0;
    public static double armVal = .76;

    @Override
    public void runOpMode() throws InterruptedException {
        // Hardware
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        
        // frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        DcMotor slideMotor = hardwareMap.get(DcMotor.class, "vertSlides");
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Encoder target positions
        int SLIDE_POSITION_DOWN = 0;    // Change this based on your encoder values
        int SLIDE_POSITION_UP = -1620;   // Adjust for max height
        

        Servo servo0 = hardwareMap.get(Servo.class, "servo0");
        Servo servo1 = hardwareMap.get(Servo.class, "servo1");
        Servo armRight = hardwareMap.get(Servo.class, "armRight");
        Servo armLeft = hardwareMap.get(Servo.class, "armLeft");
        Servo armClaw = hardwareMap.get(Servo.class, "armClaw");
        Servo intakeLeft = hardwareMap.get(Servo.class, "intakeLeft");
        Servo intakeRight = hardwareMap.get(Servo.class, "intakeRight");
        Servo intakeSwivel = hardwareMap.get(Servo.class, "intakeSwivel");
        Servo intakeClaw = hardwareMap.get(Servo.class, "intakeClaw");

        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double servo0StartPosition = servo0val; // regular is .68
        double servo0EndPosition = .6; //.29
        boolean isServo0AtStart = true; // Track if servo0 is at its start position
        servo0.setPosition(servo0StartPosition);

        // Known positions for Servo 1 (adjusted for physical direction)
        double servo1StartPosition = servo1Val; //regular is .5
        double servo1EndPosition = .4;   // .89
        boolean isServo1AtStart = true; // Track if servo1 is at its start position
        servo1.setPosition(servo1StartPosition);

        boolean bumperPressed = false; // Prevent rapid toggling

        // Servo arm positions
        double servoArmStartPosition = armVal;
        double servoArmEndPosition = .2;
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
        double intakePast = intakeE - .12;
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
            
            if (gamepad2.a) {
                moveSlideToPosition(SLIDE_POSITION_DOWN, slideMotor);
                servo0.setPosition(.10);
                servo1.setPosition(.64);
                if(slideMotor.getCurrentPosition() > -100) {
                    servo0.setPosition(servo0StartPosition);
                    servo1.setPosition(servo1StartPosition);
                }
            } 
            else if (gamepad2.b) {
                moveSlideToPosition(SLIDE_POSITION_UP, slideMotor);

            }
            else {
                // Hold position when joystick is released
                slideMotor.setTargetPosition(slideMotor.getCurrentPosition());
                slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideMotor.setPower(1); // Small holding power to maintain position
            }

            
            double y = -gamepad1.left_stick_y * .8;
            double x = -gamepad1.right_stick_x * .8;
            double rx = -gamepad1.left_stick_x * .8;

            // Calculate motor powers using mecanum drive kinematics
            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;

            // Normalize motor powers
            double maxPower = Math.max(Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower)),
                                      Math.max(Math.abs(frontRightPower), Math.abs(backRightPower)));

            if (maxPower > 1.0) {
                frontLeftPower /= maxPower;
                backLeftPower /= maxPower;
                frontRightPower /= maxPower;
                backRightPower /= maxPower;
            }

            // Set motor powers
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            if (gamepad1.x && !xPressed) {
                intakeLeft.setPosition(intakePast);
                sleep(500);
//                intakeClaw.setPosition(intakeClawEndPosition);
//                sleep(100);
                intakeLeft.setPosition(intakeEnd);
                intakeSwivel.setPosition(intakeSwivelStart);
            }

            if(!gamepad1.x) {
                xPressed = false;
            }

            // Toggle Servo 0 and Servo 1 positions with the right bumper
            if (gamepad1.right_bumper && !bumperPressed) {
                // Toggle Servo 0
                if (isServo0AtStart) {
                    servo0.setPosition(servo0EndPosition);
                } else {
                    if(!intakeAtStart)
                    {
                        intakeLeft.setPosition(intakeStart);
                        intakeRight.setPosition(intakeTurnStart);
                        intakeAtStart = !intakeAtStart;
                        intakeTurnAtStart = !intakeTurnAtStart;
                    }
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

            telemetry.addData("Front Left Power", frontLeftPower);



            if (gamepad2.left_bumper && !leftBumperPressed) {
                if (isServoArmAtStart) {
                    armLeft.setPosition(servoArmEndPosition);
                    armRight.setPosition(1 - servoArmEndPosition);
                    // servoClaw.setPosition(servoClawEndPosition);
                } else {
                    armLeft.setPosition(servoArmStartPosition);
                    armRight.setPosition(1 - servoArmStartPosition);
                    // servoClaw.setPosition(servoClawStartPosition);
                }
                isServoArmAtStart = !isServoArmAtStart; // Toggle the state
                leftBumperPressed = true;
            }

            telemetry.addData("Back Left Power", backLeftPower);
            
            if (!gamepad1.left_bumper) {
                leftBumperPressed = false;
            }
            
            
            if (gamepad1.a && !aPressed) {
                if (isIntakeClawAtStart) {
                    intakeClaw.setPosition(intakeClawEndPosition);
                } else {
                    intakeClaw.setPosition(intakeClawStartPosition);
                }
                isIntakeClawAtStart = !isIntakeClawAtStart; // Toggle the state
                aPressed = true;
            }

            if (!gamepad1.a) {
                aPressed = false;
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

            telemetry.addData("intake at start", intakeAtStart);
            telemetry.addData("y pressed", yPressed);

            if (gamepad2.x && !xPressed && intakeAtStart && isServoArmAtStart) {
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
                xPressed = true;
            }

            if (!gamepad2.x) {
                xPressed = false;
            }
            
            telemetry.addData("Front Right Power", frontRightPower);
            telemetry.addData("Back Right Power", backRightPower);
            
            if (gamepad2.right_bumper && !rightBumper2Pressed) {
                if (isArmClawAtStart) {
                    armClaw.setPosition(armClawEndPosition);
                } else {
                    armClaw.setPosition(armClawStartPosition);
                }
                isArmClawAtStart = !isArmClawAtStart; // Toggle the state
                rightBumper2Pressed = true;
            }

            if (!gamepad2.right_bumper) {
                rightBumper2Pressed = false;
            }

            // if (gamepad1.dpad_down && !dpadPressed) {
            //     if (intakeAtStart) {
            //         intakeLeft.setPosition(intakeEnd);
            //         intakeRight.setPosition(intakeEnd);
            //     } else {
            //         intakeLeft.setPosition(intakeStart);
            //         intakeRight.setPosition(intakeStart);
            //     }
            //     intakeAtStart = !intakeAtStart; // Toggle the state
            //     dpadPressed = true;
            // }

            // if (!gamepad1.dpad_down) {
            //     dpadPressed = false;
            // }
            
            // Telemetry for debugging
            
            
            
            telemetry.addData("Servo 0 Position", servo0.getPosition());
            telemetry.addData("front right", frontRightMotor.getCurrentPosition());
            telemetry.addData("Swivel Position", intakeSwivel.getPosition());
            telemetry.addData("Servo Arm State", isServoArmAtStart ? "Start" : "End");
            telemetry.update();
            
        }
        
    }
    private void moveSlideToPosition(int targetPosition, DcMotor slideMotor) {
        slideMotor.setTargetPosition(targetPosition);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(targetPosition > slideMotor.getCurrentPosition())
        {
            slideMotor.setPower(1); // Full power until position is reached
        }
        else
        {
            slideMotor.setPower(1);
        }

    }
    
    
}
