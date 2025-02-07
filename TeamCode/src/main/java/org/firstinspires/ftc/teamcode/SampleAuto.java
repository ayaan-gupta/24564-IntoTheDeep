package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "SAMPLE_AUTO", group = "Autonomous")
public class SampleAuto extends LinearOpMode {
    public static int liftScorePos  = -590;
    public static double armTransferPos = .69;
    public static double armDepositPos = .15;
    public static double extensionVal = .18;
    public class Lift {
        private DcMotor lift;
        public int endPos = liftScorePos;


        public Lift(HardwareMap hardwareMap) {
            lift = hardwareMap.get(DcMotor.class, "vertSlides");
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        public class Up implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                lift.setTargetPosition(endPos);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                lift.setPower(.5);
                return false;
            }
        }

        public Action up() {
            return new Up();
        }

        public class Down implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                lift.setTargetPosition(0);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                lift.setPower(.5);
                return false;
            }
        }

        public Action down() {
            return new Down();
        }


    }
    public class OuttakeArm {
        private Servo left;
        private Servo right;
        double armTransfer = armTransferPos;
        double armDeposit = armDepositPos;

        public OuttakeArm(HardwareMap hardwareMap) {
            right = hardwareMap.get(Servo.class, "armRight");
            left = hardwareMap.get(Servo.class, "armLeft");
        }

        public class TransferArm implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                left.setPosition(armTransfer);
                right.setPosition(1 - armTransfer);
                return false;
            }
        }
        public Action transferArm() {
            return new TransferArm();
        }

        public class DepositArm implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                left.setPosition(armDeposit);
                right.setPosition(1 - armDeposit);
                return false;
            }
        }
        public Action depositArm() {
            return new DepositArm();
        }

    }
    public class Claw {
        private Servo claw;
        double clawOpen = .96;
        double clawClosed = .66;

        public Claw(HardwareMap hardwareMap) {
            claw = hardwareMap.get(Servo.class, "armClaw");
            claw.setPosition(clawClosed);
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(clawOpen);
                return false;
            }
        }
        public Action openClaw() {
            return new OpenClaw();
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(clawClosed);
                return false;
            }
        }
        public Action closeClaw() {
            return new CloseClaw();
        }
    }
    public class IntakeMech {
        private Servo intake;
        private Servo intakeTurn;
        private Servo swivel;
        private Servo claw;
        double intakeStart = .26;
        double intakeEnd = .14;

        // Servo claw positions
        double intakeTurnStart = .02;
        double intakeTurnEnd = 1;


        double swivelStart = .55;
        double swivelMid = .397;
        double swivelEnd = .225;

        // Servo claw positions
        double clawOpen = 0;
        double clawClosed = .4;

        public IntakeMech(HardwareMap hardwareMap) {
            intake = hardwareMap.get(Servo.class, "intakeLeft");
            intakeTurn = hardwareMap.get(Servo.class, "intakeRight");
            swivel = hardwareMap.get(Servo.class, "intakeSwivel");
            claw = hardwareMap.get(Servo.class, "intakeClaw");

            intake.setPosition(intakeStart);
            intakeTurn.setPosition(intakeTurnStart);
            swivel.setPosition(swivelStart);
            claw.setPosition(clawOpen);
        }

        public class Intake implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intake.setPosition(intakeEnd);
                intakeTurn.setPosition(intakeTurnEnd);
                sleep(10);
                claw.setPosition(clawClosed);
                return false;
            }
        }

        public Action intake() {
            return new Intake();
        }

        public class Transfer implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intake.setPosition(intakeStart);
                intakeTurn.setPosition(intakeTurnStart);
                swivel.setPosition(swivelStart);
                return false;
            }
        }

        public Action transfer() {
            return new Transfer();
        }
    }
    public class Extension {
        private Servo right;
        private Servo left;
        double rightStart = .68; // regular is .68
//        double rightEnd = .29;

        double ext = extensionVal;

        // Known positions for Servo 1 (adjusted for physical direction)
        double leftStart = 0.5; //regular is .5
//        double leftEnd = .89;   // Matches servo0 end

        public Extension(HardwareMap hardwareMap) {
            right = hardwareMap.get(Servo.class, "servo0");
            left = hardwareMap.get(Servo.class, "servo1");

            right.setPosition(rightStart);
            left.setPosition(leftStart);
        }

        public class Extend implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                right.setPosition(rightStart - ext);
                left.setPosition(leftStart + ext);
                return false;
            }
        }

        public Action extend() {
            return new Extend();
        }

        public class Retract implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                right.setPosition(rightStart);
                left.setPosition(leftStart);
                return false;
            }
        }

        public Action retract() {
            return new Retract();
        }
    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-32, -62, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Lift lift = new Lift(hardwareMap);
        OuttakeArm arm = new OuttakeArm(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        IntakeMech intake = new IntakeMech(hardwareMap);
        Extension extension = new Extension(hardwareMap);

//        int visionOutputPosition = 1;

        TrajectoryActionBuilder auto = drive.actionBuilder(initialPose)
                .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-54, -54, Math.toRadians(45)), Math.toRadians(225));
//                        .turn(Math.toRadians(35))
//                        .turn(Math.toRadians(-35))
//                        .turn(Math.toRadians(55))
//                        .turn(Math.toRadians(-55))
//                        .turn(Math.toRadians(75))
//                        .turn(Math.toRadians(-75));
        TrajectoryActionBuilder auto2 = drive.actionBuilder(initialPose)
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(-54, -54, Math.toRadians(45)), Math.toRadians(225))
                .turn(Math.toRadians(35))
                .turn(Math.toRadians(-35))
                .turn(Math.toRadians(55))
                .turn(Math.toRadians(-55))
                .turn(Math.toRadians(75))
                .turn(Math.toRadians(-75));


        waitForStart();

        if (isStopRequested()) return;

        Action traj1 = auto.build();

        Actions.runBlocking(
                new SequentialAction(
                        traj1,
                        lift.up(),
                        arm.depositArm(),
                        claw.openClaw()

                )
        );
    }

}
