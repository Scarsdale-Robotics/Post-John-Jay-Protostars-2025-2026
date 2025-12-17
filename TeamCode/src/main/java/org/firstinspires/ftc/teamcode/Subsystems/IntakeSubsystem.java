package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.conditionals.IfElseCommand;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;
import dev.nextftc.hardware.powerable.SetPower;

public class IntakeSubsystem implements Subsystem {
    public static final IntakeSubsystem INSTANCE = new IntakeSubsystem();
    private IntakeSubsystem() {}
    // private final MotorEx intake2 = new MotorEx("intake2"); I think this motor doesn't exist
    // private final MotorGroup outerIntake = new MotorGroup(intake1, intake2);
    private final MotorEx outerIntake = new MotorEx("outer_intake");
    private final MotorEx innerIntake = new MotorEx("inner_intake");
    private final ServoEx intakeServo = new ServoEx("intake_servo");

    public Command innerPower(double power) {
        return new InstantCommand(() -> innerIntake.setPower(power));
    }
    public Command outerPower(double power) {
        return new InstantCommand(() -> outerIntake.setPower(power));
    }

    public Command swapIntakeServo() {
        return new IfElseCommand(
                () -> intakeServo.getPosition() == 0,
                new InstantCommand(() -> intakeServo.setPosition(0.31)),
                new InstantCommand(() -> intakeServo.setPosition(0))
        );
    }
}
