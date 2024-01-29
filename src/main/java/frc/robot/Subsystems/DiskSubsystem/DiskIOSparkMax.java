package frc.robot.Subsystems.DiskSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.MotorConstants;

import java.util.Optional;

public class DiskIOSparkMax implements DiskIO {
    public CANSparkMax rotation;
    private SparkPIDController controller;


    public DiskIOSparkMax() {
        rotation = new CANSparkMax(MotorConstants.CONTROLLER_ID, MotorType.kBrushless);
        controller = rotation.getPIDController();

        rotation.getEncoder().setPositionConversionFactor(2*Math.PI);
        rotation.getEncoder().setVelocityConversionFactor(2*Math.PI);
    }
    
    @Override
    public void updateInputs(DiskIOInputs inputs) {
        inputs.diskPositionRad = rotation.getEncoder().getPosition();
        inputs.diskVelocityRadPerSec = rotation.getEncoder().getVelocity();
        inputs.curDiskSetpoint = curSetpoint.get();

        inputs.motorAppliedVolts = rotation.getBusVoltage();
        inputs.motorCurrentDrawAmps = rotation.getOutputCurrent();
        inputs.motorTemp = rotation.getMotorTemperature();
    }

    @Override
    public void setTargetPosition(double angleRad, Optional<ControlType> type) {
        controller.setReference(angleRad, type.get());
    }

    @Override
    public void setControlLoopGains(double kP, double kI, double kD, double FF) {
        controller.setP(kP);
        controller.setI(kI);
        controller.setD(kD);
        controller.setFF(FF);
    };

    @Override
    public void setSpeed(double speed) {
        System.out.println("Setting speed to " + speed);
        rotation.set(speed);
    }

    @Override
    public void setVoltage(double voltage) {
        rotation.setVoltage(voltage);
    }

    @Override
    public void stop() {
        rotation.set(0);
    }
}
