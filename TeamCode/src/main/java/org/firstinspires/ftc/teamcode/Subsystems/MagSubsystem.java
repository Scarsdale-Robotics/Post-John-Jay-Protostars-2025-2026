package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class MagSubsystem implements Subsystem {
    public static MagSubsystem INSTANCE = new MagSubsystem();

    private final MotorEx magMotor = new MotorEx("mag_motor");

    public Command magPower(double power){
        return new InstantCommand(() -> magMotor.setPower(power));
        }
}


