package org.firstinspires.ftc.teamcode.Subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.Vec3.ZERO;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Vec3;

import dev.nextftc.core.subsystems.Subsystem;


public class LocalizationSubsystem implements Subsystem {
    public static LocalizationSubsystem robot=new LocalizationSubsystem();

    public  static GoBildaPinpointDriver pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "Pinpoint");



    public Vec3 pos=ZERO.clone();
    public Vec3 vel=ZERO.clone();
    public long lastPosUpd=System.currentTimeMillis();
    public long lastDirUpd=System.currentTimeMillis();
    private Vec3 lastPos=ZERO.clone();
    public Vec3 dir=ZERO.clone();
    public Vec3 lastDir=ZERO.clone();
    public Vec3 turnVelVec=ZERO.clone();
    public Pose2D pose2D;

    public void updPos(double X, double Y, double Z) {
        lastPos.set(pos);
        pos.set(X, Y, Z);
        long time=(System.currentTimeMillis() - lastPosUpd)/1000;
        if (time > 0) {
            vel.set(pos);
            vel.sub(lastPos).div(time);
        }
        lastPosUpd=System.currentTimeMillis();
    }

    public void updPos(Vec3 newPos) {
        updPos(newPos.x, newPos.y, newPos.z);
    }

    public void updDir(double X, double Y, double Z) {
        lastDir.set(dir);
        pos.set(X,Y,Z);
        long time=(System.currentTimeMillis() - lastPosUpd)/1000;
        if (time > 0) {
            dir.set(pos);
            dir.sub(lastDir).div(time);
        }
        lastPosUpd=System.currentTimeMillis();
    }

    public void updDir(Vec3 newDir) {
        updDir(newDir.x, newDir.y, newDir.z);
    }

    public void upd() {
        pose2D =pinpoint.getPosition();
        updPos(pose2D.getX(DistanceUnit.MM), pose2D.getY(DistanceUnit.MM),0.);
        updDir(pose2D.getHeading(AngleUnit.RADIANS),0.,0.);

    }

    public void set() {
        lastPosUpd=System.currentTimeMillis();
        lastDirUpd=System.currentTimeMillis();
        upd();
    }


    public void setOdom(double startX, double startY, double startDir) {
        pinpoint.setPosition(new Pose2D(DistanceUnit.MM,0,0, AngleUnit.RADIANS, startDir));
        pinpoint.resetPosAndIMU();
        pinpoint.setOffsets(startX,startY, DistanceUnit.MM);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        set();
    }

    public Vec3 tolocPos(Vec3 v) {
        v.toLocal(pos);
        return v;
    }
    public Vec3 toGlobalPos(Vec3 v) {
        v.toLocal(pos);
        return v;
    }


    public Vec3 toLoc(Vec3 v) {
        v.toLocal(pos, dir);
        return v;
    }
}

