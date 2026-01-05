package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LocalizationSubsystem;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.Drivetrain;

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

    public Command driveToPosRoboCentric(double spX, double spY, double h) {

        double posX = LocalizationSubsystem.INSTANCE.getX();
        double posY = LocalizationSubsystem.INSTANCE.getY();

        double error = Math.sqrt((spX - posX)*(spX - posX) + (spY - posY)*(spY - posY)); //get distance from target point

        /*
        double kP = 0.01;
        double kD = 0.001;

        double[] previousError = new double[3];

        ElapsedTime runtime = new ElapsedTime(0);
        double deltaTime = runtime.seconds();

        double derivative = (-error+8*previousError[0]-8*error*previousError[1]+previousError[2])/12*deltaTime;

        double u_t = kP * error + kD * derivative;
         */

        ControlSystem robotCentricControlSystem = ControlSystem.builder()
            .posPid(0.01, 1, 0.001)
            .build();

        double u_t = robotCentricControlSystem.calculate(new KineticState(error));

        double angle = Math.atan2(spX, spY);
        double strafe = u_t*Math.sin(angle);
        double forward = u_t*Math.cos(angle);

        return DriveSubsystem.INSTANCE.driveRobotCentric(strafe, forward, h);

    }
    private Command autoRoutine() {
        return new SequentialGroup(
                //an auto of all time
            LocalizationSubsystem.INSTANCE.setOdom(0,0,0),
            driveToPosRoboCentric(5,4,0),
            //DriveSubsystem.INSTANCE.brake,
            ShooterSubsystem.INSTANCE.shooterSetTarget(50),
            new Delay(1),
            ShooterSubsystem.INSTANCE.shooterOff
        );
    }

    @Override
    public void onStartButtonPressed() {
        autoRoutine().schedule();
    }

}
