package org.firstinspires.ftc.teamcode;

public class Vec3 {
    public double x;
    public double y;
    public double z;

    public static final Vec3 ZERO = new Vec3(0, 0, 0);
    public static Vec3 temp;

    public Vec3(double X, double Y, double Z) {
        x=X;
        y=Y;
        z=Z;
    }

    public Vec3(Vec3 v) {

        x = v.x;
        y = v.y;
        z = v.z;
    }

    public Vec3 clone() {
        return new Vec3(x, y, z);
    }

    public double mag() {
        if ((x==0)&&(y==0)&&(z==0)) return 0;
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

    public Vec3 rotZY(double az, double ay) {
        rotZ(az);
        rotY(ay);
        return  this;
    }
    public Vec3 rotXYZ(double ax, double ay, double az) {
        rotX(ax);
        rotY(ay);
        rotZ(az);
        return this;
    }
    public Vec3 rotEuler(double ax, double ay, double az) {
        rotX(az);
        rotZ(ax);
        rotY(ay);
        return this;
    }


    public Vec3 rotEuler(Vec3 v) {
        rotEuler(v.x, v.y, v.z);
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

    public double angZto(Vec3 a) {
        return Math.atan2(a.y - y, a.x - x);
    }

    public double angXZ() {
        return Math.atan2(z, Math.hypot(x, y));
    }

    public Vec3 anglizeXYZ() {
        set(angX(), angY(), angZ());
        return this;
    }
    public Vec3 anglizeEuler() {
        set(angZ(), angXZ(), angX());
        return this;
    }



    public Vec3 angXYZ() {
        return clone().anglizeXYZ();
    }
    public Vec3 angEuler() {
        return clone().anglizeEuler();
    }


    public Vec3 toLocal(Vec3 v) {
        x-=v.x;
        y-=v.y;
        z-=v.z;
        return this;
    }

    public Vec3 toLocal(Vec3 v, Vec3 angleEuler) {
        toLocal(v);
        rotEuler(angleEuler);
        return this;
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
