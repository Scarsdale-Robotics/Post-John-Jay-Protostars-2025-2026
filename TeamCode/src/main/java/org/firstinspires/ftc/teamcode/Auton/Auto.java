package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LocalizationSubsystem;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.control.feedback.FeedbackElement;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
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

    public Auto() {
        addComponents(
                new SubsystemComponent(ShooterSubsystem.INSTANCE),
                new SubsystemComponent(LocalizationSubsystem.INSTANCE),
                BulkReadComponent.INSTANCE
        );

    }

    static double[] previousError = new double[3];
    public Command driveToPosRoboCentric(double spX, double spY, double h) {

        double posX = LocalizationSubsystem.INSTANCE.getX();
        double posY = LocalizationSubsystem.INSTANCE.getY();

        double error = Math.sqrt((spX - posX)*(spX - posX) + (spY - posY)*(spY - posY)); //get distance from target point


        double kP = 0.01;
        double kD = 0.001;

        for (int i = 0; i < 4; i++)
            if (i != 0) {
                previousError[i] = previousError[i - 1];
            } else {
                previousError[i] = error;
            }

        ElapsedTime runtime = new ElapsedTime(0);
        double deltaTime = runtime.seconds();

        double derivative = (-error+8*previousError[0]-8*error*previousError[1]+previousError[2])/12*deltaTime;

        double u_t = kP * error + kD * derivative;

        double angle = Math.atan2(spX, spY);
        double strafe = u_t*Math.sin(angle);
        double forward = u_t*Math.cos(angle);

        return DriveSubsystem.INSTANCE.driveRobotCentric(strafe, forward, h);

    }
    private Command autoRoutine() {
        return new SequentialGroup(
                //an auto of all time
            LocalizationSubsystem.INSTANCE.setOdom(0,0,0),
            driveToPosRoboCentric(2,2,0),
            //DriveSubsystem.INSTANCE.brake,
            ShooterSubsystem.INSTANCE.shooterOn(0.85),
            new Delay(1),
            ShooterSubsystem.INSTANCE.shooterOff
        );
    }

    @Override
    public void onStartButtonPressed() {
        autoRoutine().schedule();
    }

}
