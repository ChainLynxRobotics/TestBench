package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Subsystems.DiskSubsystem.DiskIOSim;
import frc.robot.Subsystems.DiskSubsystem.DiskIOSparkMax;
import frc.robot.Subsystems.DiskSubsystem.DiskSubsystem;

public class RobotContainer {
  private DiskSubsystem diskSubsystem; 

  public RobotContainer() { 
    if (Robot.isReal()) {
      System.out.println("Is Real");
      diskSubsystem = new DiskSubsystem(new DiskIOSparkMax());
    } else {
      diskSubsystem = new DiskSubsystem(new DiskIOSim());
    }
  }

  public Command getAutonomousCommand() {
    return new RunCommand(() -> diskSubsystem.setSpeed(0.05), diskSubsystem);
  }

  public DiskSubsystem getDiskSubsystem()
  {
    return diskSubsystem;
  }

 }
