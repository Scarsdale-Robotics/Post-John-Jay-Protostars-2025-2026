package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class ShooterSubsystem implements Subsystem {
    public static double shooterPower = 0;
    public static final ShooterSubsystem INSTANCE = new ShooterSubsystem(shooterPower);
    private ShooterSubsystem(double shooterPower) { }
    private MotorEx motor = new MotorEx("shooterMotor_1");


        @Override
        public void periodic() { // put pid here
        }

        public Command shooterSetTarget(double shooterPower){
            return new InstantCommand(() -> {
                motor.setPower(shooterPower);
            });
        }

        //single Command needed if no parameter input
        public Command shooterOff = new SetPower(motor, 0).requires(this);

}
