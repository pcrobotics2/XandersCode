// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.TankDriveSub;

public class MoveAuto extends Command {
  /** Creates a new MoveAuto. */
  public TankDriveSub tankDriveSub;
  public MoveAuto(TankDriveSub tankDriveSub) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.tankDriveSub = tankDriveSub;
    addRequirements(tankDriveSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.tankDriveSub.drive(Constants.autoDriveSpeed, Constants.autoDriveSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
//Didn't stay running without this
    this.tankDriveSub.drive(Constants.autoDriveSpeed, Constants.autoDriveSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.tankDriveSub.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
