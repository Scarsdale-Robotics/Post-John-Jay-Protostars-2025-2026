package org.firstinspires.ftc.teamcode.Auton;

import androidx.annotation.Nullable;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ControlSystem;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LocalizationSubsystem;

import dev.nextftc.core.commands.Command;
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
import dev.nextftc.control.builder.ControlSystemBuilder;

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


    private Command autoRoutine() {
        return new SequentialGroup(
                ShooterSubsystem.INSTANCE.shooterOn(9)
        );
    }

   // ControlSystem controlSystem = ControlSystem.builder().pospid(0, 1, 0).build();

    /* @Override
    public boolean equals(@Nullable Object obj) {
    }

    public class FullPowerFeedback: FeedbackElement {
        @Override
        public double calculate(KineticState error) {
            return 1;
        } */


    @Override
    public void onStartButtonPressed() {

    }

    /*double INCH_PER_TICK = 0.02208;
    while (opModeIsActive()) {
        double forward = -gamepad2.left_stick_y;
        DriveSubsystem.driveRobotCentric(0, 0, 0); */
    }


