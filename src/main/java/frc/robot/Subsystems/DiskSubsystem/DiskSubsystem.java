package frc.robot.Subsystems.DiskSubsystem;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DiskSubsystem extends SubsystemBase {
    
  private DiskIO diskIO;
  private DiskIOInputsAutoLogged loggedInputs = new DiskIOInputsAutoLogged();

  public DiskSubsystem(DiskIO diskIO) {
    this.diskIO = diskIO;
  }

  @Override
  public void periodic() {
    diskIO.updateInputs(loggedInputs);
    Logger.processInputs("Disk/disk", loggedInputs);
  }

  public void setSpeed(double speed) {
    diskIO.setSpeed(speed);
  }

  
  public void stop() {
    diskIO.stop();
  }
  

}
