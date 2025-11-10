package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Vec3;

//if you want to move the robot, you need forces for each wheel.
// with this you can set the wheel positions (there shoudl be a default)
//and then set the center, (read the center thing)
//and then set the move & turn.
//you can then set the  force with the function.
//you now have the wForce array. all values should be between -1 and 1 (max force in either direction)
//if some force is more than max, the robot wont be going in the direction you want. same if you don't change them all by the right amount.
//


public class wheels {
    //    0 1   is is pattern of pos local to robot
    //    2 3

    public Vec3 wPos[] = new Vec3[4];

    /**
     * wheels will be at the corners of the rectangle which is defined by x & y (robot length & width)
     * @param X length
     * @param Y width
     */
    public wheels(double X, double Y) {
        wPos[0] =new Vec3(X, 0,0);
        wPos[1] =new Vec3(X, Y,0);
        wPos[2] =new Vec3(0, 0,0);
        wPos[3] =new Vec3(0, Y,0);
    }
    public wheels() {//someone set this to default robot size and center
        this(100, 100);
        center(50,50);
    }

    /**
     *center this X Y position. this center is where the robot rotates.
     * @- if the center is the center of mass, the robot will turn most efficiently
     * @- if the center is the turret,the turret will turn in place
     */
    public void center(double X, double Y) {
        Vec3 center=new Vec3(X, Y, 0);
        center.add(wPos[0]);
        for (Vec3 pos : wPos)
            pos.sub(center);
    }



    public double wForce[] = new double[4];//0 is not moving, 1 or -1 is full speed forward or back
    public double wTurn[]=new double[4]; //wheel turn forces
    public double wMove =0.;       //wheel move forces, they are all the same
    private static final double side =0.57735026918962576450914878050196;//maybe this is side multiplier.

    public void setDirMove(double force, double direction) {//directional move
        if (Math.abs(force)>1)
            force = Math.signum(force);
        wMove =Math.cos(direction)*force;
        wMove +=Math.sin(direction)*force*side; // MAKE THIS SUBTRACT IF:   DIRECTION IS .5PI (90degrees, ↺, left) AND ROBOT MOVES RIGHT
    }

    public void setMove(double x, double y) {
        setDirMove(Math.hypot(x, y), Math.atan2(y, x));
    }


    public void setTurn(double turn) {//positive should be counter clockwise
        int most = 0;
        for (int i=0; i<4; i++) {
            wTurn[i]=turn*wPos[i].y;
            wTurn[i]+=turn*wPos[i].x*side; // MAKE THIS SUBTRACT IF:   ROBOT DOESNT TURN AND JUST BREAKS
            if (Math.abs(wTurn[i])>Math.abs(wTurn[most]))
                most =i;
        }
        if (Math.abs(wTurn[most])>1.)
            for (int i=0; i<4; i++)
                wTurn[i]/= Math.abs(wTurn[most]);
    }

    public void setForce() {
        for (int i = 0; i < 4; i++) {
            wForce[i]= wMove+wTurn[i];
        }
        reduce(wForce);
    }


    public void setForceMaxTurn() {
        double diff=0;

        for (int i=0;i<4;i++) {
            wForce[i] = wTurn[i];
            diff=1.-wTurn[i];


        }

    }



    public void reduce(double a[]) {//reduce values  to the 1 proportionaly
        double mostForce = Math.abs(a[0]);
        for (int i = 1; i < 4; i++) {
            double thisForce=Math.abs(a[i]);
            if (thisForce>mostForce)
                mostForce=thisForce;
        }
        if (mostForce<=1.)
            return;
        for (int i=0; i<4; i++)
            a[i]/= mostForce;
    }
}
