package frc.robot.Subsystems.DiskSubsystem;

import com.revrobotics.CANSparkBase.ControlType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.Constants;
import frc.robot.Constants.MotorConstants;

import java.util.Optional;


public class DiskIOSim implements DiskIO {
    private DCMotorSim rotationSim;
    private final PIDController rotationalController =
      new PIDController(
        Constants.rotkP,
        Constants.rotkI,
        Constants.rotkD);

    private double feedAppliedVolts = 0.0;

    public DiskIOSim() {
        rotationSim = new DCMotorSim(DCMotor.getNEO(1), 1, MotorConstants.neoMomentOfInertia);
    }

    @Override
    public void updateInputs(DiskIOInputs inputs) {
        rotationSim.update(Constants.GLOBAL_TIMESTEP);

        inputs.diskPositionRad = rotationSim.getAngularPositionRad();
        inputs.diskVelocityRadPerSec = rotationSim.getAngularVelocityRadPerSec();
        inputs.curDiskSetpoint = curSetpoint.get();

        inputs.motorAppliedVolts = feedAppliedVolts;
        inputs.motorCurrentDrawAmps = rotationSim.getCurrentDrawAmps();
    }

    @Override
    public void setTargetPosition(double angleRad, Optional<ControlType> type) {
        rotationalController.setSetpoint(angleRad);
    }

    @Override
    public void setSpeed(double speed) {
        feedAppliedVolts = 12*speed;
        rotationSim.setInputVoltage(MathUtil.clamp(12*speed, -12.0, 12.0));
    }

    @Override
    public void setVoltage(double voltage) {
        rotationSim.setInputVoltage(voltage);
    }

    @Override
    public void stop() {
        rotationSim.setInputVoltage(0);
    }
    
}
