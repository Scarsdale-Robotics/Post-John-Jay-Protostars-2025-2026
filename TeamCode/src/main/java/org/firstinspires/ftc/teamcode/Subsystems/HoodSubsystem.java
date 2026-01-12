package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.powerable.SetPower;

public class HoodSubsystem { // Not finished lots of stuff
    //public static final HoodSubsystem INSTANCE = new HoodSubsystem();
    private ServoEx servo = new ServoEx("hoodServo");

    private HoodSubsystem() {
        servo.setPosition(0);
    }

    public Command turn(double direction) {
        return new InstantCommand(() ->
            servo.setPosition(direction)
        );
    }

    //single Command needed if no parameter input
    //public Command shooterOff = new SetPower(motor, 0).requires(this);

}
