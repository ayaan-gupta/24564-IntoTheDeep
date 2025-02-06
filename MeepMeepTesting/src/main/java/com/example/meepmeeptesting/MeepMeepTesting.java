package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(13,17)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(9, -62, Math.toRadians(270)))
                        .lineToConstantHeading(new Vector2d(9, -34))
                        .setTangent(Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(38, -34, Math.toRadians(90)), Math.toRadians(90))
                        .setTangent(Math.toRadians(90))
                        .lineToConstantHeading(new Vector2d(38, -16))
                        .splineToConstantHeading(new Vector2d(48, -16), Math.toRadians(270))
                        .splineToConstantHeading(new Vector2d(48, -56), Math.toRadians(270))
                        .lineToConstantHeading(new Vector2d(48, -16))
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
                        .splineToLinearHeading(new Pose2d(0, -34, Math.toRadians(270)), Math.toRadians(90))
                        .setTangent(Math.toRadians(270))


//                        .splineToConstantHeading(new Vector2d(58, -56), Math.toRadians(270))
//                        .setTangent
//                        .splineTo(new Vector2d(44, -10), Math.toRadians(65))
//                        .lineToLinearHeading(new Pose2d(47, -10, Math.toRadians(90)))
//                        .back(40)
//                        .forward(40)
//                        .strafeRight(11)
//                        .back(45)
//                        .lineToLinearHeading(new Pose2d(9, -38, Math.toRadians(270)))
//                        .back(3)
//                        .lineToLinearHeading(new Pose2d(40, -55, Math.toRadians(90)))
//                        .back(3)
//                        .lineToLinearHeading(new Pose2d(9, -38, Math.toRadians(270)))
//                        .back(3)
//                        .lineToLinearHeading(new Pose2d(40, -55, Math.toRadians(90)))
//                        .back(3)
//                        .lineToLinearHeading(new Pose2d(9, -38, Math.toRadians(270)))
//                        .back(3)
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}