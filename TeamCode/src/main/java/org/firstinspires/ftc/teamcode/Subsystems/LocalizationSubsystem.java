package org.firstinspires.ftc.teamcode.Subsystems;

import org.opencv.core.Point;
import org.opencv.core.Point3;

import dev.nextftc.core.subsystems.Subsystem;



public class LocalizationSubsystem implements Subsystem {
    public static LocalizationSubsystem INSTANCE = new LocalizationSubsystem();

    public static double timer;
    public double xVel;
    public double yVel;
    public double zVel;

    public static void update(double xPos, double yPos, double zPos) {
//        xVel=(xPos-x)*timer;
//        yVel=(yPos-y)*timer;
//        zVel=(zPos-z)*timer;
//        P point = new Point(xPos, yPos);


            xPos = 0;
            yPos = 0;
            zPos = 0;

        timer = 0;


    }


}