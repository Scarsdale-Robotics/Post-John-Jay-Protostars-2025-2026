package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

//TESTING GIT STUFF
public class DriveSubsystem implements Subsystem {
    public static final DriveSubsystem INSTANCE = new DriveSubsystem();

    private DriveSubsystem() { }

    private MotorEx frontLeftMotor = new MotorEx("frontLeftMotor");
    private MotorEx frontRightMotor = new MotorEx("frontRightMotor");
    private MotorEx backLeftMotor = new MotorEx("backLeftMotor");
    private MotorEx backRightMotor = new MotorEx("backRightMotor");

    private ControlSystem driveControlSystem = ControlSystem.builder()
            .posPid(0,0,0) // figure out what this is
            .build();

}
