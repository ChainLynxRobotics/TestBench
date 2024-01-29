package frc.robot.Subsystems.DiskSubsystem;

import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class DiskSubsystem extends SubsystemBase {

  private DiskIO diskIO;
  private DiskIOInputsAutoLogged loggedInputs = new DiskIOInputsAutoLogged();
  private final SysIdRoutine sysId;

  public DiskSubsystem(DiskIO diskIO) {
    this.diskIO = diskIO;

    sysId = new SysIdRoutine(
      new SysIdRoutine.Config(), 
      new SysIdRoutine.Mechanism((voltage) -> setVoltage(voltage.magnitude()), null, this));
  }
  

  @Override
  public void periodic() {
    diskIO.updateInputs(loggedInputs);
    Logger.processInputs("Disk/disk", loggedInputs);
  }

  public void setSpeed(double speed) {
    System.out.println("set speed from disk subsystem");
    diskIO.setSpeed(speed);
    Logger.recordOutput("Disk/diskSpeed", speed); //look under RealOutputs to see result of log
  }

  public void setVoltage(double voltage) {
    diskIO.setVoltage(voltage);
  }

  //should do the same thing as Logger.processOutput, but leaving for the sake of testing to see what works
  @AutoLogOutput(key = "Disk/diskSpeed") 
  public double getSpeed() {
    return loggedInputs.diskVelocityRadPerSec;
  }

  /** Returns a command to run a quasistatic test in the specified direction. */
  public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
    return sysId.quasistatic(direction);
  }

  /** Returns a command to run a dynamic test in the specified direction. */
  public Command sysIdDynamic(SysIdRoutine.Direction direction) {
    return sysId.dynamic(direction);
  }

  
  public void stop() {
    diskIO.stop();
  }
  

}
