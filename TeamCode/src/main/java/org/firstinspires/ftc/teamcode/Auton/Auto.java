package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.SubsystemComponent;
//import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.Drivetrain;
import dev.nextftc.hardware.driving.FieldCentric;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.Direction;
import dev.nextftc.hardware.impl.IMUEx;
import dev.nextftc.hardware.impl.MotorEx;

@Autonomous(name="Autonomous Program")
public class Auto extends NextFTCOpMode {
    double forwardPower = 0;
    double strafePower = 0;
    double turnPower = 0;
    public Drivetrain Auto;
    public Auto() {
        addComponents(
                new SubsystemComponent(ShooterSubsystem.INSTANCE),
                BulkReadComponent.INSTANCE
        );
    }
    private final MotorEx frontLeftMotor = new MotorEx("frontLeft").reversed();
    private final MotorEx frontRightMotor = new MotorEx("frontRight");
    private final MotorEx backLeftMotor = new MotorEx("backLeft").reversed();
    private final MotorEx backRightMotor = new MotorEx("backRight");
    private final IMUEx imu = new IMUEx("imu", Direction.UP, Direction.FORWARD).zeroed();

    @Override
    public void onStartButtonPressed() {


    }

}
