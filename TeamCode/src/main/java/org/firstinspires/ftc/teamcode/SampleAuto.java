package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
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
    public static int liftScorePos  = -1590;
    public static double armTransferPos = .705;
    public static double armDepositPos = .06;
    public static double extensionVal = .30;
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
                lift.setPower(1);
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
                lift.setPower(1);

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

            left.setPosition(armTransfer);
            right.setPosition(1 - armTransfer);
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
        double clawClosed = .59;

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
        double intakeStart = .24;
        double intakeEnd = .01;

        // Servo claw positions
        double intakeTurnStart = 0;
        double intakeTurnEnd = 1;


        double swivelStart = .55;
        double swivelMid = .517;
        double swivelEnd = .225;

        // Servo claw positions
        double clawOpen = .48;
        double clawClosed = 1;

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
                sleep(1500);
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

        public class OpenIntakeClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(clawOpen);
                return false;
            }
        }

        public Action openIntakeClaw() {
            return new OpenIntakeClaw();
        }

        public class SwivelRight implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                swivel.setPosition(swivelMid);
                return false;
            }
        }

        public Action swivelRight() {
            return new SwivelRight();
        }

        public class SwivelReg implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                swivel.setPosition(swivelStart);
                return false;
            }
        }

        public Action swivelReg() {
            return new SwivelReg();
        }

        public class EndPos implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intake.setPosition(intakeStart + .06);
                intakeTurn.setPosition(intakeTurnStart);
                return false;
            }
        }

        public Action endPos() {
            return new EndPos();
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

        public class ExtendLong implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                right.setPosition(rightStart - (ext + .02));
                left.setPosition(leftStart + (ext + .02));
                return false;
            }
        }

        public Action extendLong() {
            return new ExtendLong();
        }

        public class ExtendShort implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                right.setPosition(rightStart - (ext - .01));
                left.setPosition(leftStart + (ext - .01));
                return false;
            }
        }

        public Action extendShort() {
            return new ExtendShort();
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

        public class Dodge implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                right.setPosition(.59);
                left.setPosition(.59);
                return false;
            }
        }

        public Action dodge() {
            return new Dodge();
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

        Action slidesDown = new SequentialAction(
                extension.dodge(),
                lift.down(),
                new SleepAction(1.15),
                extension.retract()
        );

        Action intakeAction = new SequentialAction(
                extension.extend(),
                intake.intake()
        );

        Action intakeActionShort = new SequentialAction(
                extension.extendShort(),
                new SleepAction(.05),
                intake.intake()
        );

        Action intakeActionWSwivel = new SequentialAction(
                extension.extendLong(),
                new SleepAction(.6),
                intake.swivelRight(),
                intake.intake(),
                new SleepAction(1.25)
        );


        Action intakeRetract = new ParallelAction(
                extension.retract(),
                intake.transfer()
        );

        Action transferSample = new SequentialAction(
                claw.closeClaw(),
                new SleepAction(.1),
                intake.openIntakeClaw()
        );

        TrajectoryActionBuilder auto = drive.actionBuilder(initialPose)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-56, -56, Math.toRadians(45)), Math.toRadians(225))
                .afterTime(0, lift.up())
                .afterTime(1, arm.depositArm())
                .afterTime(2, claw.openClaw())
                .afterTime(2.15, arm.transferArm())
                .afterTime(2.5, slidesDown)
                .waitSeconds(2.5)
                .turnTo(Math.toRadians(85))
                .afterTime(.25, intakeActionShort)
                .afterTime(1.65, intakeRetract)
                .afterTime(3.15, claw.closeClaw())
                .afterTime(3.25, intake.openIntakeClaw())
                .waitSeconds(3.25)
                .turnTo(Math.toRadians(45))
                .afterTime(0, lift.up())
                .afterTime(1, arm.depositArm())
                .afterTime(2, claw.openClaw())
                .afterTime(2.15, arm.transferArm())
                .afterTime(2.5, extension.dodge())
                .afterTime(2.5, slidesDown)
                .waitSeconds(2.5)
                .turnTo(Math.toRadians(105.5))
                .afterTime(.25, intakeActionShort)
                .afterTime(1.55, extension.retract())
                .afterTime(1.65, intake.transfer())
                .afterTime(3.05, claw.closeClaw())
                .afterTime(3.15, intake.openIntakeClaw())
//                .afterTime(3.05, transferSample)
                .waitSeconds(3.15)
                .turnTo(Math.toRadians(45))
                .afterTime(0, lift.up())
                .afterTime(1, arm.depositArm())
                .afterTime(2, claw.openClaw())
                .afterTime(2.15, arm.transferArm())
                .afterTime(2.5, extension.dodge())
                .afterTime(2.5, slidesDown)
                .waitSeconds(2.5)
                .turnTo(Math.toRadians(122.5))
                .afterTime(.25, intakeActionWSwivel)
                .afterTime(1.05, intake.swivelReg())
                .afterTime(1.25, extension.retract())
                .afterTime(1.95, intake.transfer())
                .afterTime(3.25, claw.closeClaw())
                .afterTime(3.35, intake.openIntakeClaw())
                .waitSeconds(3.25)
                .turnTo(Math.toRadians(45))
                .afterTime(0, lift.up())
                .afterTime(1, arm.depositArm())
                .afterTime(2, claw.openClaw())
                .afterTime(2.15, arm.transferArm())
                .afterTime(2.5, extension.dodge())
                .afterTime(2.5, slidesDown)
                .afterTime(2.5, intake.endPos())
                .waitSeconds(4);


        waitForStart();

        if (isStopRequested()) return;

        Action traj1 = auto.build();

        Actions.runBlocking(
                new SequentialAction(
                        traj1
                )
        );


    }

}
