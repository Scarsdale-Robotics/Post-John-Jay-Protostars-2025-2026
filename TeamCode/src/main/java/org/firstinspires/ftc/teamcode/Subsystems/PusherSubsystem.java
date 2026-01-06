package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class PusherSubsystem implements Subsystem {
    public static PusherSubsystem INSTANCE = new PusherSubsystem();

    public ServoEx pusherServo = new ServoEx("pusher_servo");


}
