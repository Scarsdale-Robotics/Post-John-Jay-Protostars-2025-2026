package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class ShooterSubsystem implements Subsystem {
    public double target = 0;
    private double integral = 0, prevError = 0;
    private long prevTime = 00;

    public static final ShooterSubsystem INSTANCE = new ShooterSubsystem();
    private ShooterSubsystem() { }
    private MotorEx motor = new MotorEx("shooterMotor_1");

        public double getFlywheelError(){
            return target-motor.getVelocity();
        }

        public double P, I, D;
        @Override
        public void periodic() { // put pid here
            double rate = (getFlywheelError()-prevError)/(double)(System.nanoTime()-prevTime)*1000000.;
            integral += getFlywheelError();

            motor.setPower(P* getFlywheelError()+
                            I*integral+
                            D*rate);

            prevError = getFlywheelError();
            prevTime = System.nanoTime();
        }

        public Command setFlywheelVel(double targetVelocity){
            return new InstantCommand(() -> {
                target = targetVelocity;
            });
        }

        //single Command needed if no parameter input
        public Command shooterOff = new SetPower(motor, 0).requires(this);

}
