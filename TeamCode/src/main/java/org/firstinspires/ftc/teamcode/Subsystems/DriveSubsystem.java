package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class DriveSubsystem implements Subsystem {
    public static final DriveSubsystem INSTANCE = new DriveSubsystem();

    private DriveSubsystem() { }

    private MotorEx frontLeftMotor = new MotorEx("frontLeftMotor");
    private MotorEx frontRightMotor = new MotorEx("frontRightMotor");
    private MotorEx backLeftMotor = new MotorEx("backLeftMotor");
    private MotorEx backRightMotor = new MotorEx("backRightMotor");

    private MotorGroup allWheels = new MotorGroup(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);

    private ControlSystem driveControlSystem = ControlSystem.builder()
            .posPid(0,0,0) // figure out what this is
            .build();

    @Override
    public void periodic() {

    //i forgor
    }

    public class driveRobotCentric extends Command{
        public driveRobotCentric(double x, double y, double h){
            frontLeftMotor.setPower(y + x + h);
            frontRightMotor.setPower(y - x - h);
            backLeftMotor.setPower(y - x + h);
            backRightMotor.setPower(y + x - h);
        }

        @Override
        public boolean isDone() {
            return false;
        }
    }
    public Command brake = new SetPower(allWheels, 0);

}

//drive robot centric DONE
//drive to position