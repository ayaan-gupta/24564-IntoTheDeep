package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(13,17)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-34, -62, Math.toRadians(0)))
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-54, -54, Math.toRadians(45)), Math.toRadians(225))
                        .turn(Math.toRadians(35))
                        .turn(Math.toRadians(-35))
                        .turn(Math.toRadians(55))
                        .turn(Math.toRadians(-55))
                        .turn(Math.toRadians(75))
                        .turn(Math.toRadians(-75))
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}