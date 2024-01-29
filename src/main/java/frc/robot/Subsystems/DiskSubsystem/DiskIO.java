package frc.robot.Subsystems.DiskSubsystem;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.networktables.LoggedDashboardNumber;

import java.util.Optional;

import com.revrobotics.CANSparkBase.ControlType;

public interface DiskIO {
    public LoggedDashboardNumber curSetpoint = new LoggedDashboardNumber("curSetpoint", 0.0);

    /** called periodically to update system inputs*/
    public default void updateInputs(DiskIOInputs inputs) {}

    /** set disk setpoint*/
    public default void setTargetPosition(double angleRad, Optional<ControlType> type) {}

    /** set proportional, integral, derivative, and feedforward gains*/
    public default void setControlLoopGains(double kP, double kI, double kD, double FF) {};

    /** set disk speed*/
    public default void setSpeed(double speed) {}

    /** set disk voltage*/
    public default void setVoltage(double voltage) {}

    /** stop any inputs to disk*/
    public default void stop() {}

    @AutoLog
    static class DiskIOInputs {
        public double diskVelocityRadPerSec;
        public double diskPositionRad;
        public double curDiskSetpoint;

        public double motorTemp;
        public double motorCurrentDrawAmps;
        public double motorAppliedVolts;
    }

}

//"/media/sda1/"
//'Log_ad76bfcc9c0e2eae.wpilog' to 'Log_24-01-27_13-57-42.wpilog' ﻿
