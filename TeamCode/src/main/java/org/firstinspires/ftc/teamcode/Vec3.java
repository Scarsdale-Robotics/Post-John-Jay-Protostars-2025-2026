package org.firstinspires.ftc.teamcode;

public class Vec3 {
    public double x;
    public double y;
    public double z;

    public Vec3(double X, double Y, double Z) {
        x=X;
        y=Y;
        z=Z;
    }

    public Vec3 clone(Vec3 v) {
        return new Vec3(v.x, v.y, v.z);
    }
    public Vec3 clone() {
        return new Vec3(x, y, z);
    }

    public double mag() {
        return Math.hypot(Math.hypot(x, y), z);
    }

    public Vec3 norm() {
        Vec3 v=clone();
        v.div(norm());
        return v;
    }

    public void normalize() {
        div(mag());
    }

    public void rot(double a) {
        set(a);
    }


    public void add(Vec3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    public void add(double v) {
        x += v;
        y += v;
        z += v;
    }

    public void sub(Vec3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }

    public void sub(double v) {
        x -= v;
        y -= v;
        z -= v;
    }

    public void mult(Vec3 v) {
        x *= v.x;
        y *= v.y;
        z *= v.z;
    }

    public void mult(double v) {
        x *= v;
        y *= v;
        z *= v;
    }

    public void div(Vec3 v) {
        x /= v.x;
        y /= v.y;
        z /= v.z;
    }

    public void div(double v) {
        x /= v;
        y /= v;
        z /= v;
    }

    public void set(double X,double Y,double Z) {
        x=X;
        y=Y;
        z=Z;
    }
}
