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
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(9, -62, Math.toRadians(270)))
                        .back(28)
                        .forward(8)
//                        .splineTo(new Vector2d(34, -30), Math.toRadians(65))
                        .splineTo(new Vector2d(44, -10), Math.toRadians(65))
                        .lineToLinearHeading(new Pose2d(47, -10, Math.toRadians(90)))
                        .back(40)
                        .forward(40)
                        .strafeRight(11)
                        .back(45)
                        .lineToLinearHeading(new Pose2d(9, -38, Math.toRadians(270)))
                        .back(3)
                        .lineToLinearHeading(new Pose2d(40, -55, Math.toRadians(90)))
                        .back(3)
                        .lineToLinearHeading(new Pose2d(9, -38, Math.toRadians(270)))
                        .back(3)
                        .lineToLinearHeading(new Pose2d(40, -55, Math.toRadians(90)))
                        .back(3)
                        .lineToLinearHeading(new Pose2d(9, -38, Math.toRadians(270)))
                        .back(3)

//                        .turn(Math.toRadians(90))
//                        .forward(30)
//                        .turn(Math.toRadians(90))
//                        .forward(30)
//                        .turn(Math.toRadians(90))
//                        .forward(30)
//                        .turn(Math.toRadians(90))
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}