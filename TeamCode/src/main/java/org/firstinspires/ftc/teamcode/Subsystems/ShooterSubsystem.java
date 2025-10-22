package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class ShooterSubsystem implements Subsystem {
    public static final ShooterSubsystem INSTANCE = new ShooterSubsystem();
    private ShooterSubsystem() {}
    private MotorEx motor1 = new MotorEx("shoot_motor_1");
    private MotorEx motor2 = new MotorEx("shoot_motor_2");

        private ControlSystem controlSystem = ControlSystem.builder()
                .posPid(0,0,0)
                .build();

}
