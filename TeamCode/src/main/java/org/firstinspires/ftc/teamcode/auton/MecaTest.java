package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MecaTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        // Hardware
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        
        // int slidesPos = 0;
        // boolean isSlidesStart = true;
        // DcMotor vertSlides = hardwareMap.get(DcMotor.class, "vertSlides");
        
        
        // vertSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        

        // Servo servo0 = hardwareMap.get(Servo.class, "servo0");
        // Servo servo1 = hardwareMap.get(Servo.class, "servo1");
        // Servo servoArm = hardwareMap.get(Servo.class, "servoArm");
        // Servo servoClaw = hardwareMap.get(Servo.class, "servoClaw");
        // Servo sClaw = hardwareMap.get(Servo.class, "sClaw");
        // Servo intakeLeft = hardwareMap.get(Servo.class, "intakeLeft");
        // Servo intakeRight = hardwareMap.get(Servo.class, "intakeRight");
        // Servo intakeSwivel = hardwareMap.get(Servo.class, "intakeSwivel");
        // Servo intakeClaw = hardwareMap.get(Servo.class, "intakeClaw");

        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       

        // // Known positions for Servo 0
        // double servo0StartPosition = .4;
        // double servo0EndPosition = .03;
        // boolean isServo0AtStart = true; // Track if servo0 is at its start position
        // servo0.setPosition(servo0StartPosition);

        // // Known positions for Servo 1 (adjusted for physical direction)
        // double servo1StartPosition = 0.6; // Matches servo0 start
        // double servo1EndPosition = .97;   // Matches servo0 end
        // boolean isServo1AtStart = true; // Track if servo1 is at its start position
        // servo1.setPosition(servo1StartPosition);

        // boolean bumperPressed = false; // Prevent rapid toggling

        // // Servo arm positions
        // double servoArmStartPosition = .5;
        // double servoArmEndPosition = .5;
        // boolean isServoArmAtStart = true; // Track if servoArm is at its start position
        // servoArm.setPosition(servoArmStartPosition);
        
        // // Servo arm positions
        // double servoClawStartPosition = .1;
        // double servoClawEndPosition = 1;
        // boolean isServoClawAtStart = true; // Track if servoArm is at its start position
        // servoClaw.setPosition(servoClawStartPosition);
        
        // // Servo claw positions
        // double sClawStartPosition = .96;
        // double sClawEndPosition = .62;
        // boolean isSClawAtStart = true; // Track if servoArm is at its start position
        // sClaw.setPosition(sClawStartPosition);
        
        // // Servo claw positions
        // double intakeStart = .92;
        // double intakeEnd = .06;
        // boolean intakeAtStart = true; // Track if servoArm is at its start position
        // intakeLeft.setPosition(intakeStart);
        // intakeRight.setPosition(intakeStart);
        
        // double intakeSwivelStart = .52;
        // double intakeSwivelMid = .347;
        // double intakeSwivelEnd = .175;
        // boolean isIntakeSwivelAtStart = true; // Track if servoArm is at its start position
        // intakeSwivel.setPosition(intakeSwivelStart);
        
        // // Servo claw positions
        // double intakeClawStartPosition = .7;
        // double intakeClawEndPosition = .35;
        // boolean isIntakeClawAtStart = true; // Track if servoArm is at its start position
        // intakeClaw.setPosition(intakeClawStartPosition);

        // boolean leftBumperPressed = false;
        // boolean aPressed = false;
        // boolean bPressed = false;
        // boolean yPressed = false;
        // boolean dpadPressed = false;
        // boolean dpadRightPressed = false;
        // boolean dpadLeftPressed = false;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            
            double y = -gamepad1.left_stick_y;
            double x = -gamepad1.right_stick_x * .7;
            double rx = -gamepad1.left_stick_x;

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
            
            

            // // Toggle Servo 0 and Servo 1 positions with the right bumper
            // if (gamepad1.right_bumper && !bumperPressed) {
            //     // Toggle Servo 0
            //     if (isServo0AtStart) {
            //         servo0.setPosition(servo0EndPosition);
            //     } else {
            //         servo0.setPosition(servo0StartPosition);
            //     }
            //     isServo0AtStart = !isServo0AtStart; // Toggle the state

            //     // Toggle Servo 1 (same physical direction)
            //     if (isServo1AtStart) {
            //         servo1.setPosition(servo1EndPosition); // Matches servo0
            //     } else {
            //         servo1.setPosition(servo1StartPosition); // Matches servo0
            //     }
            //     isServo1AtStart = !isServo1AtStart; // Toggle the state

            //     bumperPressed = true;
            // }

            // if (!gamepad1.right_bumper) {
            //     bumperPressed = false;
            // }


            // if (gamepad1.dpad_right && !dpadRightPressed) {
            //     if (isIntakeSwivelAtStart || (intakeSwivel.getPosition() > intakeSwivelMid - .05 && intakeSwivel.getPosition() < intakeSwivelMid + .05)) {
            //         intakeSwivel.setPosition(intakeSwivelEnd);
            //     } else {
            //         intakeSwivel.setPosition(intakeSwivelStart);
            //     }
            //     isIntakeSwivelAtStart = !isIntakeSwivelAtStart; // Toggle the state
            //     dpadRightPressed = true;
            // }

            // if (!gamepad1.dpad_right) {
            //     dpadRightPressed = false;
            // }

            // if (gamepad1.dpad_left && !dpadLeftPressed) {
            //     if (isIntakeSwivelAtStart || (intakeSwivel.getPosition() > intakeSwivelEnd - .05 && intakeSwivel.getPosition() < intakeSwivelEnd + .05)) {
            //         intakeSwivel.setPosition(intakeSwivelMid);
            //     } else {
            //         intakeSwivel.setPosition(intakeSwivelStart);
            //     }
            //     isIntakeSwivelAtStart = !isIntakeSwivelAtStart; // Toggle the state
            //     dpadLeftPressed = true;
            // }

            // if (!gamepad1.dpad_left) {
            //     dpadLeftPressed = false;
            // }
            
            // if (gamepad1.left_bumper && !leftBumperPressed) {
            //     if (isServoArmAtStart) {
            //         servoArm.setPosition(servoArmEndPosition);
            //         // servoClaw.setPosition(servoClawEndPosition);
            //     } else {
            //         servoArm.setPosition(servoArmStartPosition);
            //         // servoClaw.setPosition(servoClawStartPosition);
            //     }
            //     isServoArmAtStart = !isServoArmAtStart; // Toggle the state
            //     leftBumperPressed = true;
            // }

            // if (!gamepad1.left_bumper) {
            //     leftBumperPressed = false;
            // }
            
            
            // if (gamepad1.a && !aPressed) {
            //     if (isIntakeClawAtStart) {
            //         intakeClaw.setPosition(intakeClawEndPosition);
            //     } else {
            //         intakeClaw.setPosition(intakeClawStartPosition);
            //     }
            //     isIntakeClawAtStart = !isIntakeClawAtStart; // Toggle the state
            //     aPressed = true;
            // }

            // if (!gamepad1.a) {
            //     aPressed = false;
            // }
            
            // // if (gamepad1.y && !aPressed) {
            // //     if (isSlidesStart) {
            // //         setVert(100, .25);
            // //         isSlidesStart = false;
            // //     } else {
            // //         setVert(0, .25);
            // //         isSlidesStart = true;
            // //     }
            // //     yPressed = true;
            // // }

            // // if (!gamepad1.y) {
            // //     yPressed = false;
            // // }
            
            
            // if (gamepad1.b && !bPressed) {
            //     if (isSClawAtStart) {
            //         sClaw.setPosition(sClawEndPosition);
            //     } else {
            //         sClaw.setPosition(sClawStartPosition);
            //     }
            //     isSClawAtStart = !isSClawAtStart; // Toggle the state
            //     bPressed = true;
            // }

            // if (!gamepad1.b) {
            //     bPressed = false;
            // }

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
            
            // // Telemetry for debugging
            // telemetry.addData("Front Left Power", frontLeftPower);
            // telemetry.addData("Back Left Power", backLeftPower);
            // telemetry.addData("Front Right Power", frontRightPower);
            // telemetry.addData("Back Right Power", backRightPower);
            // telemetry.addData("Servo 0 Position", servo0.getPosition());
            // telemetry.addData("Servo 1 Position", servo1.getPosition());
            // telemetry.addData("Servo Arm Position", servoArm.getPosition());
            // telemetry.addData("Swivel Position", intakeSwivel.getPosition());
            // telemetry.addData("Servo Arm State", isServoArmAtStart ? "Start" : "End");
            // telemetry.update();
            
            // public void setVert(int slidesTarget, double speed)
            // {
            //     slidesPos += slidesTarget;
                
            //     vertSlides.setTargetPosition(slidesPos);
            //     vertSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
            //     vertSlides.setPower(speed);
                
            //     while(opModeIsActive() && vertSlides.isBusy())
            //     {
            //         idle();
            //     }
            // }
        }
    }
    
    
}
