package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "SAMPLE_AUTO", group = "Autonomous")
public class SampleAuto extends LinearOpMode {
    public static int liftScorePos  = -590;
    public static double armDepositPos = .15;
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
        double armDeposit = armDepositPos;

        double score = armDeposit - .05;
        double armIntake = 0;

        public OuttakeArm(HardwareMap hardwareMap) {
            right = hardwareMap.get(Servo.class, "armRight");
            left = hardwareMap.get(Servo.class, "armLeft");
        }

        public class IntakeArm implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                left.setPosition(armIntake);
                right.setPosition(1 - armIntake);
                return false;
            }
        }
        public Action intakeArm() {
            return new IntakeArm();
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

        public class ScoreArm implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                left.setPosition(score);
                right.setPosition(1 - score);
                return false;
            }
        }
        public Action scoreArm() {
            return new ScoreArm();
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

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(9, -62, Math.toRadians(270));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Lift lift = new Lift(hardwareMap);
        OuttakeArm arm = new OuttakeArm(hardwareMap);
        Claw claw = new Claw(hardwareMap);

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
