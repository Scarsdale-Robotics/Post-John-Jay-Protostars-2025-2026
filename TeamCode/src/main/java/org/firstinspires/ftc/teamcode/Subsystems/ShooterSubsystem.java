package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.controllable.RunToState;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class ShooterSubsystem implements Subsystem {
    public static double shooterPower = 0;


    public static final ShooterSubsystem INSTANCE = new ShooterSubsystem(shooterPower);

    private ShooterSubsystem(double shooterPower) {

    }
    private MotorEx motor1 = new MotorEx("flywheelMotor_1").reversed();
    private MotorEx motor2 = new MotorEx("flywheelMotor_2");
    private MotorGroup flywheel = new MotorGroup(motor1, motor2);

    private ControlSystem pos_ctrl = ControlSystem.builder()
            .posPid(0,0,0)
            .build();
    private ControlSystem vel_ctrl = ControlSystem.builder()
            .velPid(0,0,0)
            .build();


    @Override
    public void periodic() {
        flywheel.setPower(pos_ctrl.calculate(motor1.getState()));
    }
    /// speed to set motor to, returns error
    public Command shooterOn(double speed){
        return new InstantCommand(() -> {
            flywheel.setPower(vel_ctrl.calculate(flywheel.getVelocity()));


        });
    }

    //single Command needed if no parameter input
    public Command shooterOff = new SetPower(flywheel, 0).requires(this);

}
