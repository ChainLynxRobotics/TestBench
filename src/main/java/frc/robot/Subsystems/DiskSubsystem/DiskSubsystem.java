package frc.robot.Subsystems.DiskSubsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.DiskSubsystem.DiskIO.DiskIOInputs;

public class DiskSubsystem extends SubsystemBase {
    
    private DiskIO diskIO;
    private DiskIOInputs inputs;

    public DiskSubsystem(DiskIO diskIO) {
        this.diskIO = diskIO;
        inputs = new DiskIOInputs();
    }

    @Override
    public void periodic() {
        diskIO.updateInputs(inputs);
    }

    public DiskIOInputs getInputs() {
        return inputs;
      }
    
      public void stop() {
        diskIO.stop();
      }
    

}
