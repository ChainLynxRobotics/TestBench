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

    double motorRotation = ((DiskIOSparkMax)(diskIO)).rotation.getEncoder().getPosition();
    Logger.recordOutput("Disk/motorPos", motorRotation);
  }

  public void setSpeed(double speed) {
    System.out.println("setspeed from disk subsystem");
    diskIO.setSpeed(speed);
  }

  
  public void stop() {
    diskIO.stop();
  }
  

}
