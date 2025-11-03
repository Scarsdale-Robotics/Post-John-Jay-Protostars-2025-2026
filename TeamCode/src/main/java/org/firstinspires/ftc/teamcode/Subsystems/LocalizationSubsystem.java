package org.firstinspires.ftc.teamcode.Subsystems;
import static org.firstinspires.ftc.teamcode.Vec3.ZERO;
import org.firstinspires.ftc.teamcode.Vec3;
import dev.nextftc.core.subsystems.Subsystem;


public class LocalizationSubsystem implements Subsystem {
    public static LocalizationSubsystem robot=new LocalizationSubsystem();

    public Vec3 pos=ZERO.clone();
    public Vec3 vel=pos.clone();
    public double lastUpdate=0;
    private Vec3 lastPos=vel.clone();
    public double dir=0;


    public void updPos(double x, double y, double z) {
        lastPos.set(pos);
        pos.set(x, y, z);
        //vel.set(pos.sub(lastPos).div(/*time-lastUpdate*/));
    }

    public void updPos(Vec3 v) {
        updPos(v.x, v.y, v.z);
    }

    }


}

