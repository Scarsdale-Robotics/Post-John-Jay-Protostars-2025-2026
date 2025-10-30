package org.firstinspires.ftc.teamcode;

public class Vec3 {
    public double x;
    public double y;
    public double z;

    public static final Vec3 ZERO = new Vec3(0, 0, 0);

    public Vec3(double X, double Y, double Z) {
        x=X;
        y=Y;
        z=Z;
    }

    public Vec3 clone() {
        return new Vec3(x, y, z);
    }

    public double mag() {
        return Math.hypot(Math.hypot(x, y), z);
    }

    public Vec3 norm() {
        return clone().normalize();
    }

    public Vec3 normalize() {
        div(mag());
        return this;
    }

//    public boolean equals(Vec3 v) {
//        return ((x == v.x) && (y == v.y) && (z == v.z));
//    }

    public Vec3 rotX(double a) {
        double c = Math.cos(a);
        double s = Math.sin(a);
        set(x,y*c-z*s,z*c+y*s);
        return this;
    }
    public Vec3 rotY(double a) {
        double c = Math.cos(a);
        double s = Math.sin(a);
        set(x*c-z*s,y,z*c+x*s);
        return this;
    }
    public Vec3 rotZ(double a) {
        double c = Math.cos(a);
        double s = Math.sin(a);
        set(x*c-y*s,y*c+x*s,z);
        return this;
    }

    public Vec3 rotYZ(double ay, double az) {
        rotY(ay);
        rotZ(az);
        return  this;
    }
    public Vec3 rot(double ax, double ay, double az) {
        rotX(ax);
        rotY(ay);
        rotZ(az);
        return this;
    }

    public void cross(Vec3 v) {
        set(y * v.z - z * v.y, x * v.y - y * v.x, z * v.x - x * v.z);
    }

    public double angX() {
        return Math.atan2(z, y);
    }
    public double angY() {
        return Math.atan2(z, x);
    }
    public double angZ() {
        return Math.atan2(y,x);
    }

    public double angXZ() {
        return Math.atan2(z, Math.hypot(x, y));
    }

    public Vec3 anglize() {
        set(angX(), angY(), angZ());
        return this;
    }

    public Vec3 ang() {
        return clone().anglize();
    }



    public Vec3 add(Vec3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public Vec3 add(double v) {
        x += v;
        y += v;
        z += v;
        return this;
    }

    public Vec3 sub(Vec3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    public Vec3 sub(double v) {
        x -= v;
        y -= v;
        z -= v;
        return this;
    }

    public Vec3 mult(Vec3 v) {
        x *= v.x;
        y *= v.y;
        z *= v.z;
        return this;
    }

    public Vec3 mult(double v) {
        x *= v;
        y *= v;
        z *= v;
        return this;
    }

    public double dot(Vec3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    public Vec3 div(Vec3 v) {
        x /= v.x;
        y /= v.y;
        z /= v.z;
        return this;
    }

    public Vec3 div(double v) {
        x /= v;
        y /= v;
        z /= v;
        return this;
    }

    public Vec3 set(double X,double Y,double Z) {
        x=X;
        y=Y;
        z=Z;
        return this;
    }
    public Vec3 set(Vec3 v) {
        x=v.x;
        y=v.y;
        z=v.z;
        return this;
    }

    public String toString() {
        return "[ "+x+",\t"+y+",\t"+z+" ]";
    }
    public String toString(int d) {
        String p="%."+d+"f",x;
        return "[ "+p+",\t"+p+",\t"+p+" ]";
    }
}
