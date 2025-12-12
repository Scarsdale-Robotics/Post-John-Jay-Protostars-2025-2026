package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

public class IntakeSubsystem implements Subsystem {
    public static final IntakeSubsystem INSTANCE = new IntakeSubsystem();
    private IntakeSubsystem() {}
    private final MotorEx intake1 = new MotorEx("intake1");
    private final MotorEx intake2 = new MotorEx("intake2");
    private final ServoEx intakeServo = new ServoEx("intake_servo");





}
