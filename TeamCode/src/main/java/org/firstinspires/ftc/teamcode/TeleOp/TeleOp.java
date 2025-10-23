package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

public class TeleOp extends NextFTCOpMode {
    public TeleOp() {
        addComponents(
                new SubsystemComponent(ShooterSubsystem.INSTANCE)
        );
    }
    private final MotorEx frontLeftMotor = new MotorEx("frontLeft").reversed();
    private final MotorEx frontRightMotor = new MotorEx("frontRight");
    private final MotorEx backLeftMotor = new MotorEx("backLeft").reversed();
    private final MotorEx backRightMotor = new MotorEx("backRight");

    @Override
    public void onStartButtonPressed() {
        Command driverControl = new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );

        driverControl.schedule();

    }
}
