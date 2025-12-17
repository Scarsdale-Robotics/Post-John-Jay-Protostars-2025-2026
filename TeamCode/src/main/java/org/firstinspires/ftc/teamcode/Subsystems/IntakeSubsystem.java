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
    // private final MotorEx intake2 = new MotorEx("intake2"); This motor just doesn't exist I guess
    // private final MotorGroup outerIntake = new MotorGroup(intake1, intake2);
    private final MotorEx outerIntake = new MotorEx("outer_intake");
    private final MotorEx innerIntake = new MotorEx("inner_intake");
    private final ServoEx intakeServo = new ServoEx("intake_servo");

    //Command myLambdaCommand = new LambdaCommand().set
    public Command innerPower(double power) {
        return new InstantCommand(() -> innerIntake.setPower(power));
    }
    public Command outerPower(double power) {
        return new InstantCommand(() -> outerIntake.setPower(power));
    }
    public Command high = new SetPosition(intakeServo,0.31).requires(this);
    public Command low = new SetPosition(intakeServo,0).requires(this);
//    public Command swapIntakeServo =  new IfElseCommand(
//                () -> 1 == 0,
//                high,
//                low
//        );
}

//invert first motor flywheel
//ParallelGroup()
//    Command1
//    Command2