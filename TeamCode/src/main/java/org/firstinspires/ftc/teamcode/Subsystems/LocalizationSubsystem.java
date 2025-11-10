package org.firstinspires.ftc.teamcode.Subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.Vec3.ZERO;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.Vec3;

import java.util.ArrayList;

import dev.nextftc.core.subsystems.Subsystem;


public class LocalizationSubsystem implements Subsystem {
    public static LocalizationSubsystem INSTANCE=new LocalizationSubsystem();

    public  static GoBildaPinpointDriver pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "Pinpoint");

    public LocalizationSubsystem(){
        pos = new Vec3(0,0,0);
        vel = new Vec3(0,0,0);
        lastPosUpd=System.currentTimeMillis();
        lastDirUpd=System.currentTimeMillis();
        lastPos=new Vec3();
        dir=new Vec3();
        lastDir=new Vec3();
        turnVelVec=new Vec3();
    }

    @Override
    public void periodic(){
        upd();
    }

    Vec3 pos;
    Vec3 vel;
    long lastPosUpd;
    long lastDirUpd;
     Vec3 lastPos;
    Vec3 dir;
    Vec3 lastDir;
    Vec3 turnVelVec;
     Pose2D pose2D;

    public Pose2D getPose2D() {
        return new Pose2D(DistanceUnit.MM,pos.x, pos.y, AngleUnit.RADIANS, dir.x);
    }
    public double getX(){
        return pos.x;
    }
    public double getY(){
        return pos.y;
    }
    public double getH(){
        return dir.x;
    }



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

    public Vec3 toLocalPos(Vec3 v) {
        v.sub(pos);
        return v;
    }

    public Vec3 toGlobalPos(Vec3 v) {
        v.add(pos);
        return v;
    }


    public Vec3 toLocalAngle(Vec3 v) {
        dir.mult(-1);
        v.rotEuler(dir);
        dir.mult(-1);
        return v;
    }
    public Vec3 toGlobalAngle(Vec3 v) {
        v.rotEuler(dir);
        return v;
    }


    public Vec3 toLocal(Vec3 v) {
        return toLocalAngle(toLocalPos(v));
    }
    public Vec3 toGlobal(Vec3 v) {
        return toLocalPos(toLocalAngle(v));
    }


}

