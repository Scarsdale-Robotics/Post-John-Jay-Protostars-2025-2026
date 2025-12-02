package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.FieldCentric;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.Direction;
import dev.nextftc.hardware.impl.IMUEx;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

/*
* DriveFieldCentric
*Drive subsystem specifications:

Required methods:

Driverobotcentricpower(double strafepower,forwardpower,turnpower)

Drivefieldcentric(theta, speed, turnpower, heading)

Required commands:
Brake

drivefieldcentric:
Theta: direction of drive, in radians.
Speed: driving speed, in/sec
Turn: turn speed, rad/sec
Heading: robots current heading.

Basically youll calculate how fast the robot would move if you went full speed in the direction,
then use desired speed to get the actual speed needed. You can then like add turn.

*
* */
public class DriveSubsystem implements Subsystem {
    public static final DriveSubsystem INSTANCE = new DriveSubsystem();

    private DriveSubsystem() { }

      private final MotorEx frontLeftMotor = new MotorEx("frontLeft").reversed();
      private final MotorEx frontRightMotor = new MotorEx("frontRight");
      private final MotorEx backLeftMotor = new MotorEx("backLeft").reversed();
      private final MotorEx backRightMotor = new MotorEx("backRight");
      private final IMUEx imu = new IMUEx("imu", Direction.UP, Direction.FORWARD).zeroed();

    private MotorGroup allWheels = new MotorGroup(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);

    /*
    private ControlSystem driveControlSystem = ControlSystem.builder()
            .posPid(0,0,0) // figure out what this is
            .build(); //why is this here this shouldn't. be here. it should be in auto.
    */
    @Override
    public void periodic() {

    //i forgor
    }

    /**
     *
     * @param theta is the angle at which the robot should move relative to field
     * @param forward (in in/s) - constant force of speed
     * @param turn is the speed at which a robot should (degrees per second)
     * @param heading the robot's current angle, used to define true north relative to field
     * @return nothing (haskell hates this one weird trick)
     */
    public void driveFieldCentric(double theta, double forward, double turn, double heading) {
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                // takes in four values, uses setPower() to send to motors, (one forward value is defined)
                // time is not a factor
                //new FieldCentric(imu) <-- BOOOOOOO FOR CHEATERS
        );


    };
    // x = forward ; y = strafe ; h = turn
    public void driveRobotCentric(double forward, double strafe, double turn) {
            frontLeftMotor.setPower(strafe + forward + turn);
            frontRightMotor.setPower(strafe - forward - turn);
            backLeftMotor.setPower(strafe - forward + turn);
            backRightMotor.setPower(strafe + forward - turn);
        };
    }

    public Command brake = new SetPower(allWheels, 0);

}

//drive robot centric DONE
//drive to position