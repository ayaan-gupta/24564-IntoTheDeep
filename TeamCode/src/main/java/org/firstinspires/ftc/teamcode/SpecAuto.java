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
@Autonomous(name = "SPEC_AUTO", group = "Autonomous")
public class SpecAuto extends LinearOpMode {
//    public class Lift {
//        private DcMotor lift;
//
//        public Lift(HardwareMap hardwareMap) {
//            lift = hardwareMap.get(DcMotor.class, "vertSlides");
//            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            lift.setDirection(DcMotorSimple.Direction.REVERSE);
//        }
//
////        lift.setTargetPosition(1000);
////        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//    }
//    public class OuttakeArm {
//        private Servo left;
//        private Servo right;
//        double armDeposit = .12;
//        double armIntake = 0;
//
//        public OuttakeArm(HardwareMap hardwareMap) {
//            right = hardwareMap.get(Servo.class, "armRight");
//            left = hardwareMap.get(Servo.class, "armLeft");
//        }
//
//        public class IntakeArm implements Action {
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                left.setPosition(armIntake);
//                right.setPosition(1 - armIntake);
//                return false;
//            }
//        }
//        public Action intakeArm() {
//            return new IntakeArm();
//        }
//
//        public class DepositArm implements Action {
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                left.setPosition(armDeposit);
//                right.setPosition(1 - armDeposit);
//                return false;
//            }
//        }
//        public Action depositArm() {
//            return new DepositArm();
//        }
//    }
//    public class Claw {
//        private Servo claw;
//        double clawOpen = .96;
//        double clawClosed = .62;
//
//        public Claw(HardwareMap hardwareMap) {
//            claw = hardwareMap.get(Servo.class, "armClaw");
//            claw.setPosition(clawClosed);
//        }
//
//        public class OpenClaw implements Action {
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                claw.setPosition(clawOpen);
//                return false;
//            }
//        }
//        public Action openClaw() {
//            return new OpenClaw();
//        }
//
//        public class CloseClaw implements Action {
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet) {
//                claw.setPosition(clawClosed);
//                return false;
//            }
//        }
//        public Action closeClaw() {
//            return new CloseClaw();
//        }
//    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(9, -62, Math.toRadians(270));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

//        Lift lift = new Lift(hardwareMap);
//        OuttakeArm arm = new OuttakeArm(hardwareMap);
//        Claw claw = new Claw(hardwareMap);

//        int visionOutputPosition = 1;

        TrajectoryActionBuilder auto = drive.actionBuilder(initialPose)
//                .lineToConstantHeading(new Vector2d(9, -34))
                .lineToY(-34)
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(38, -34, Math.toRadians(90)), Math.toRadians(90))
                .setTangent(Math.toRadians(90))
                .lineToY(-16)
//                .lineToConstantHeading(new Vector2d(38, -16))
                .splineToConstantHeading(new Vector2d(48, -16), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(48, -56), Math.toRadians(270))
                .lineToY(-16)
//                .lineToConstantHeading(new Vector2d(48, -16))
                .splineToConstantHeading(new Vector2d(59, -16), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(59, -56), Math.toRadians(270))
                .waitSeconds(.25)
                .setTangent(180)
                .splineToConstantHeading(new Vector2d(38, -62), Math.toRadians(270))
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(6, -34, Math.toRadians(270)), Math.toRadians(90))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(38, -62, Math.toRadians(90)), Math.toRadians(270))
                .waitSeconds(.25)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(3, -34, Math.toRadians(270)), Math.toRadians(90))
                .setTangent(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(38, -62, Math.toRadians(90)), Math.toRadians(270))
                .waitSeconds(.25)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(0, -34, Math.toRadians(270)), Math.toRadians(90));
                //.setTangent(Math.toRadians(270));


        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        auto.build()
                )
        );
    }

}
