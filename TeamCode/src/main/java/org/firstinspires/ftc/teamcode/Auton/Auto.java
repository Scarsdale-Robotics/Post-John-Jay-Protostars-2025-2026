package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LocalizationSubsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
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
                new SubsystemComponent(LocalizationSubsystem.INSTANCE),
                BulkReadComponent.INSTANCE
        );
    }


    private Command autoRoutine() {
        return new SequentialGroup(
                //an auto of all time
            LocalizationSubsystem.INSTANCE.setOdom(0,0,0),
            DriveSubsystem.INSTANCE.driveRobotCentric(0,1,Math.toRadians(20)),
            new Delay(0.5),
            DriveSubsystem.INSTANCE.brake,
            ShooterSubsystem.INSTANCE.shooterOn(50),
            new Delay(1),
            ShooterSubsystem.INSTANCE.shooterOff
        );
    }

    @Override
    public void onStartButtonPressed() {
        autoRoutine().schedule();
    }

}
