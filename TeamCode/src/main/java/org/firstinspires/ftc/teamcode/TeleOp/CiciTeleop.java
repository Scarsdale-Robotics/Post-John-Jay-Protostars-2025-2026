package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.FieldCentric;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.Direction;
import dev.nextftc.hardware.impl.IMUEx;
import dev.nextftc.hardware.impl.MotorEx;
@TeleOp(name="Cici TeleOp")
public class CiciTeleop extends NextFTCOpMode {

    public CiciTeleop() {
        addComponents(
                new SubsystemComponent(IntakeSubsystem.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    // MecanumDriverControlled command
    private final MotorEx frontLeftMotor = new MotorEx("frontLeft").reversed();
    private final MotorEx frontRightMotor = new MotorEx("frontRight").reversed();
    private final MotorEx backLeftMotor = new MotorEx("backLeft").reversed();
    private final MotorEx backRightMotor = new MotorEx("backRight");
    private final IMUEx imu = new IMUEx("imu", Direction.UP, Direction.FORWARD).zeroed();

    @Override
    public void onStartButtonPressed() {
        Command driverControl = new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX(),
                new FieldCentric(imu)
        );
        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(IntakeSubsystem.INSTANCE.swapIntakeServo());

        driverControl.schedule();





        //if (ShooterSubsystem.INSTANCE.shooterPower == 0)
            //Gamepads.gamepad1().dpadDown().whenBecomesTrue(ShooterSubsystem.INSTANCE.shooterOn());

        //new sequential group()






    //----------
        // Gamepad bindings
        // onStartButtonPressed() ->
        // onInit() -> called when OpMode is initialized but-
        // -before it transitions to the "waiting for start"
        // Gamepads.gamepad2().dpadUp().whenBecomesTrue()
        // localization: odometry, matrix of

        //Gamepads.gamepad2().leftBumper().whenBecomesTrue(
        //        new ParallelGroup(
        //            ShooterSubsystem.INSTANCE.shooterOn(9)
        //      ));


    }
}
