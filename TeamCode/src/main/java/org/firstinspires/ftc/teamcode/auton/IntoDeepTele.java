package org.firstinspires.ftc.teamcode.auton;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class IntoDeepTele  extends LinearOpMode {


   //private int incr = 0;

        public int incr = 0;
        public int i = 0;
        public boolean turnReady, clawReady, seq;
        public static int maxSamplesSteps = 9;




   @Override
   public void runOpMode() throws InterruptedException {


       // Hardware
       DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
       DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
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
       int SLIDE_POSITION_DOWN = 5;    // Change this based on your encoder values
       int SLIDE_POSITION_UP = 1610;   // Adjust for max height




       //horizontal slides
       Servo servo0 = hardwareMap.get(Servo.class, "servo0");
       Servo servo1 = hardwareMap.get(Servo.class, "servo1");
       //depot
       Servo armRight = hardwareMap.get(Servo.class, "armRight");
       Servo armLeft = hardwareMap.get(Servo.class, "armLeft");
       //depot claw
       Servo armClaw = hardwareMap.get(Servo.class, "armClaw");
       //intake pivots
       Servo intakeLeft = hardwareMap.get(Servo.class, "intakeLeft");
       Servo intakeRight = hardwareMap.get(Servo.class, "intakeRight");
       //intake turn
       Servo intakeSwivel = hardwareMap.get(Servo.class, "intakeSwivel");
       //intake claw
       Servo intakeClaw = hardwareMap.get(Servo.class, "intakeClaw");


       frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
       backRightMotor.setDirection(DcMotor.Direction.REVERSE);
       backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
       frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        

       // Known positions for Servo 0
       double servo0IdlePosition = .68;
       double servo0ExtendPosition = .29;
       boolean isServo0AtStart = true; // Track if servo0 is at its start position
       servo0.setPosition(servo0IdlePosition);


       // Known positions for Servo 1 (adjusted for physical direction)
       double servo1IdlePosition = 0.5; // Matches servo0 start
       double servo1ExtendPosition = .89;   // Matches servo0 end
       boolean isServo1AtStart = true; // Track if servo1 is at its start position
       servo1.setPosition(servo1IdlePosition);


       boolean bumperPressed = false; // Prevent rapid toggling


       // Servo arm positions
       double servoArmIdlePosition = .69;
       double servoArmDepotPosition = .1;
       boolean isServoArmAtStart = true; // Track if servoArm is at its start position
       armLeft.setPosition(servoArmIdlePosition);
       armRight.setPosition(1 - servoArmIdlePosition);


       // Servo claw positions
       double armClawOpenPosition = .96;
       double armClawClosedPosition = .62;
       boolean isArmClawAtStart = true; // Track if servoArm is at its start position
       armClaw.setPosition(armClawOpenPosition);


       // Servo claw positions
       double intakeIdle = .25;
       double intakeIntake = .15;
       boolean intakeAtStart = true; // Track if servoArm is at its start position
       intakeLeft.setPosition(intakeIdle);


       // Servo claw positions
       double intakeTurnIdle = 0.1;
       double intakeTurnIntake = 1;
       boolean intakeTurnAtStart = true; // Track if servoArm is at its start position
       boolean intakeTurnAtMid = true; // Track if servoArm is at its start position
       intakeRight.setPosition(intakeTurnIdle);


       double intakeSwivelStart = .7; //.74
       double intakeSwivelMid = .557;
       double intakeSwivelEnd = .385;
       boolean isIntakeSwivelAtStart = true; // Track if servoArm is at its start position
       intakeSwivel.setPosition(intakeSwivelStart);


       // Servo claw positions
       double intakeClawOpenPosition = 0;
       double intakeClawClosedPosition = .4;
       boolean isIntakeClawAtStart = true; // Track if servoArm is at its start position
       intakeClaw.setPosition(intakeClawOpenPosition);


//        boolean leftBumperPressed = false;
        boolean aPressed = false;
//        boolean bPressed = false;
//        boolean yPressed = false;
//        boolean xPressed = false;
//        boolean dpadPressed = false;
//        boolean dpadRightPressed = false;
//        boolean dpadLeftPressed = false;
//        boolean rightBumper2Pressed = false;

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();


       waitForStart();


       if (isStopRequested()) return;






       while (opModeIsActive()) {

        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);
        
        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);
            
           turnReady = clawReady = true;
           
            increment(currentGamepad1.right_bumper && !previousGamepad1.right_bumper, currentGamepad1.left_bumper && !previousGamepad1.left_bumper);
           
            seq = true;
            
            if (gamepad1.x && !intakeAtStart) {
                if(!isIntakeClawAtStart)
                {
                    intakeClaw.setPosition(intakeClawOpenPosition);
                }
                intakeLeft.setPosition(0);
                sleep(50);
                intakeClaw.setPosition(intakeClawClosedPosition);
                sleep(50);
                intakeLeft.setPosition(intakeIntake);
                sleep(50);
                intakeSwivel.setPosition(intakeSwivelStart);
            }
            
           double y = -gamepad1.left_stick_y * .6;
           double x = -gamepad1.right_stick_x * .6;
           double rx = -gamepad1.left_stick_x * .6;


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
          
           /*if (gamepad1.right_bumper && seq) {
               incr++;
               if(incr>maxSamplesSteps) {
                   incr = 0;
               }
               seq = false;
           }
           if (gamepad1.left_bumper && seq) {
               incr--;
               if(incr<0) {
                   incr = maxSamplesSteps;
               }
               seq = false;
           }*/


           if (gamepad1.dpad_up && turnReady) {
                intakeSwivel.setPosition(intakeSwivelStart);

               turnReady = false;
           }
           if (gamepad1.dpad_right && turnReady) {

                intakeSwivel.setPosition(intakeSwivelMid);
               turnReady = false;
           }
           if (gamepad1.dpad_down && turnReady) {

                intakeSwivel.setPosition(intakeSwivelEnd);
               turnReady = false;
           }
           if (gamepad1.dpad_left && turnReady) {


               turnReady = false;
           }


           if (gamepad1.a && !aPressed) {
                if (isIntakeClawAtStart) {
                    intakeClaw.setPosition(intakeClawClosedPosition);
                } else {
                    intakeClaw.setPosition(intakeClawOpenPosition);
                }
                isIntakeClawAtStart = !isIntakeClawAtStart; // Toggle the state
                aPressed = true;
            }

            if (!gamepad1.a) {
                aPressed = false;
            }

           switch (incr) {
               case 0:
                   //vertical slides down
                   moveSlideToPosition(SLIDE_POSITION_DOWN, slideMotor);
                   //depot arm idle
                   armLeft.setPosition(servoArmIdlePosition);
                   armRight.setPosition(1 - servoArmIdlePosition);
                   //horizontal slides idle
                   servo0.setPosition(servo0IdlePosition);
                   servo1.setPosition(servo1IdlePosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   //depot claw open
                   armClaw.setPosition(armClawOpenPosition);
                   break;
               case 1:
                   //vertical slides down
                   moveSlideToPosition(SLIDE_POSITION_DOWN, slideMotor);
                   //depot arm idle
                   armLeft.setPosition(servoArmIdlePosition);
                   armRight.setPosition(1 - servoArmIdlePosition);
                   //horizontal slides extend
                   servo0.setPosition(servo0ExtendPosition);
                   servo1.setPosition(servo1ExtendPosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   //depot claw open
                   armClaw.setPosition(armClawOpenPosition);
                   break;
               case 2:
                   //vertical slides down
                   moveSlideToPosition(SLIDE_POSITION_DOWN, slideMotor);
                   //depot arm idle
                   armLeft.setPosition(servoArmIdlePosition);
                   armRight.setPosition(1 - servoArmIdlePosition);
                   //horizontal slides extend
                   servo0.setPosition(servo0ExtendPosition);
                   servo1.setPosition(servo1ExtendPosition);
                   //big pivot intake
                   intakeLeft.setPosition(intakeIntake);
                   //small pivot intake
                   intakeRight.setPosition(intakeTurnIntake);
                   //depot claw open
                   armClaw.setPosition(armClawOpenPosition);
                   break;
               case 3:
                   //vertical slides down
                   moveSlideToPosition(SLIDE_POSITION_DOWN, slideMotor);
                   //depot arm idle
                   armLeft.setPosition(servoArmIdlePosition);
                   armRight.setPosition(1 - servoArmIdlePosition);
                   //horizontal slides extend
                   servo0.setPosition(servo0ExtendPosition);
                   servo1.setPosition(servo1ExtendPosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   //depot claw open
                   armClaw.setPosition(armClawOpenPosition);
                   break;
               case 4:
                   //vertical slides down
                   moveSlideToPosition(SLIDE_POSITION_DOWN, slideMotor);
                   //depot arm idle
                   armLeft.setPosition(servoArmIdlePosition);
                   armRight.setPosition(1 - servoArmIdlePosition);
                   //horizontal slides idle
                   servo0.setPosition(servo0IdlePosition);
                   servo1.setPosition(servo1IdlePosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   //depot claw open
                   armClaw.setPosition(armClawOpenPosition);
                   break;
               case 5:
                   //depot claw close
                   armClaw.setPosition(armClawClosedPosition);
                   //intake claw open
                   intakeClaw.setPosition(intakeClawOpenPosition);
                   //vertical slides down
                   moveSlideToPosition(SLIDE_POSITION_DOWN, slideMotor);
                   //depot arm depot
                   armLeft.setPosition(servoArmIdlePosition);
                   armRight.setPosition(1 - servoArmIdlePosition);
                   //horizontal slides idle
                   servo0.setPosition(servo0IdlePosition);
                   servo1.setPosition(servo1IdlePosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   break;
                 case 6:
                   //depot claw close
                   armClaw.setPosition(armClawClosedPosition);
                   //intake claw open
                   intakeClaw.setPosition(intakeClawOpenPosition);
                   //vertical slides down
                   moveSlideToPosition(SLIDE_POSITION_DOWN, slideMotor);
                   //depot arm depot
                   armLeft.setPosition(servoArmDepotPosition);
                   armRight.setPosition(1 - servoArmDepotPosition);
                   //horizontal slides idle
                   servo0.setPosition(servo0IdlePosition);
                   servo1.setPosition(servo1IdlePosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   break;
               case 7:
                   //vertical slides up
                   moveSlideToPosition(SLIDE_POSITION_UP, slideMotor);
                   //depot arm depot
                   armLeft.setPosition(servoArmDepotPosition);
                   armRight.setPosition(1 - servoArmDepotPosition);
                   //horizontal slides idle
                   servo0.setPosition(servo0IdlePosition);
                   servo1.setPosition(servo1IdlePosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   //depot claw close
                   armClaw.setPosition(armClawClosedPosition);
                   break;
               case 8:
                   //vertical slides up
                   moveSlideToPosition(SLIDE_POSITION_UP, slideMotor);
                   //depot arm depot
                   armLeft.setPosition(servoArmDepotPosition);
                   armRight.setPosition(1 - servoArmDepotPosition);
                   //horizontal slides idle
                   servo0.setPosition(servo0IdlePosition);
                   servo1.setPosition(servo1IdlePosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   //depot claw open
                   armClaw.setPosition(armClawOpenPosition);
                   break;
               case 9:
                   //vertical slides up
                   moveSlideToPosition(SLIDE_POSITION_UP, slideMotor);
                   //depot arm idle
                   armLeft.setPosition(servoArmIdlePosition);
                   armRight.setPosition(1 - servoArmIdlePosition);
                   //horizontal slides idle
                   servo0.setPosition(servo0IdlePosition);
                   servo1.setPosition(servo1IdlePosition);
                   //big pivot idle
                   intakeLeft.setPosition(intakeIdle);
                   //small pivot idle
                   intakeRight.setPosition(intakeTurnIdle);
                   //depot claw open
                   armClaw.setPosition(armClawOpenPosition);
                   break;
           }
           telemetry.addData("incr", incr);
           telemetry.update();
       }
   }
   
   private void increment(boolean up, boolean down) {
       if(up) {
           incr++;
           if(incr>maxSamplesSteps) {
               incr = 0;
           }
       }
       else if(down) {
           incr--;
           if(incr<0) {
               incr = maxSamplesSteps;
           }
       }
   }
   private void moveSlideToPosition(int targetPosition, DcMotor slideMotor) {
        slideMotor.setTargetPosition(targetPosition);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if(targetPosition > slideMotor.getCurrentPosition())
        {
            slideMotor.setPower(.9); // Full power until position is reached
        }
        else
        {
            slideMotor.setPower(.9);
        }

    }
}

