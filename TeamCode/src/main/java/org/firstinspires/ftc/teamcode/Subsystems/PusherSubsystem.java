package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class PusherSubsystem implements Subsystem {
    public static PusherSubsystem INSTANCE = new PusherSubsystem();
    public ServoEx pusherServo = new ServoEx("pusher_servo");
    public double upPosition = 0.2;
    public double downPosition = 0;
    public Command pusherUp = new SetPosition(pusherServo, upPosition);
    public Command pusherDown = new SetPosition(pusherServo, downPosition);


}
