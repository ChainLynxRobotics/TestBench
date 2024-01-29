package frc.robot;

import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;
import org.littletonrobotics.junction.networktables.LoggedDashboardNumber;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.Subsystems.DiskSubsystem.DiskIOSim;
import frc.robot.Subsystems.DiskSubsystem.DiskIOSparkMax;
import frc.robot.Subsystems.DiskSubsystem.DiskSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

public class RobotContainer {
  private DiskSubsystem diskSubsystem; 
  private final LoggedDashboardChooser<Command> autoChooser;
  private final LoggedDashboardNumber diskVelocityInput = new LoggedDashboardNumber("diskSpeed", 1);

  public RobotContainer() { 
    if (Robot.isReal()) {
      diskSubsystem = new DiskSubsystem(new DiskIOSparkMax());
    } else {
      diskSubsystem = new DiskSubsystem(new DiskIOSim());
    }

    //also try passing in NamedCommands.get("Run Disk") into default auto routine
    NamedCommands.registerCommand(
        "Run Disk",
        Commands.startEnd(
                () -> diskSubsystem.setSpeed(diskVelocityInput.get()), diskSubsystem::stop, diskSubsystem)
            .withTimeout(5.0));

    autoChooser = new LoggedDashboardChooser<>("Auto Choices", AutoBuilder.buildAutoChooser());
    autoChooser.addDefaultOption(
      "run disk", new RunCommand(() -> diskSubsystem.setSpeed(0.05), diskSubsystem));
    autoChooser.addOption(
      "sysID quasistatic forward", diskSubsystem.sysIdQuasistatic(Direction.kForward));
    autoChooser.addOption(
      "sysID quasistatic reverse", diskSubsystem.sysIdQuasistatic(Direction.kReverse));
    autoChooser.addOption(
      "sysID dynamic forward", diskSubsystem.sysIdDynamic(Direction.kForward));
    autoChooser.addOption(
      "sysID dynamic reverse", diskSubsystem.sysIdDynamic(Direction.kReverse));
  }

  public Command getAutonomousCommand() {
    return autoChooser.get();
  }

  public DiskSubsystem getDiskSubsystem() {
    return diskSubsystem;
  }

 }
