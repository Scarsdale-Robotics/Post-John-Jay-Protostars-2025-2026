package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.controllable.RunToState;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class ShooterSubsystem implements Subsystem {
    public static double shooterPower = 0;
    public static final ShooterSubsystem INSTANCE = new ShooterSubsystem(shooterPower);
    private ShooterSubsystem(double shooterPower) { }
    private MotorEx motor1 = new MotorEx("flywheelMotor_1");
    private MotorEx motor2 = new MotorEx("flywheelMotor_2");
    private MotorGroup shooterGroup = new MotorGroup(motor1, motor2);

        private ControlSystem controlSystem = ControlSystem.builder()
                .posPid(0,0,0)
                .build();

        @Override
        public void periodic() {
            shooterGroup.setPower(controlSystem.calculate(motor1.getState()));
        }

        public Command shooterOn = new SetPower(shooterGroup, shooterPower).requires(this);
        public Command shooterOff = new SetPower(shooterGroup, 0).requires(this);

}
