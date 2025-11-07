package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.Direction;
import dev.nextftc.hardware.impl.IMUEx;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class DriveSubsystem implements Subsystem {
    public static final DriveSubsystem INSTANCE = new DriveSubsystem();

    private DriveSubsystem() { }

      private final MotorEx frontLeftMotor = new MotorEx("frontLeft").reversed();
      private final MotorEx frontRightMotor = new MotorEx("frontRight");
      private final MotorEx backLeftMotor = new MotorEx("backLeft").reversed();
      private final MotorEx backRightMotor = new MotorEx("backRight");
      private final IMUEx imu = new IMUEx("imu", Direction.UP, Direction.FORWARD).zeroed();

    private MotorGroup allWheels = new MotorGroup(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);

    private ControlSystem driveControlSystem = ControlSystem.builder()
            .posPid(0,0,0) // figure out what this is
            .build();

    @Override
    public void periodic() {

    //i forgor
    }

    public Command driveRobotCentric(double x, double y, double h) {
        return new InstantCommand(() -> {
            frontLeftMotor.setPower(y + x + h);
            frontRightMotor.setPower(y - x - h);
            backLeftMotor.setPower(y - x + h);
            backRightMotor.setPower(y + x - h);
        });
    }

    public Command brake = new SetPower(allWheels, 0);

}

//drive robot centric DONE
//drive to position