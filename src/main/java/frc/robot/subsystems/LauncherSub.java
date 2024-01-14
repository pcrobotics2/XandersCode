// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSub extends SubsystemBase {
  /** Creates a new launcher. */
  //motor up is higher than motor down
  public MotorController upLauncher;
  public MotorController downLauncher;
  public MotorControllerGroup launcherGroup;

  public LauncherSub() {
    upLauncher = new Talon(0);
    downLauncher = new Talon(1); 

    this.launcherGroup = new MotorControllerGroup(
      downLauncher, 
      upLauncher
    );
  }

  // public Command getIntakeCommand() {
  //   return this.startEnd(
  //     () -> {
  //       setFeedWheel(kIntakeFeederSpeed);
  //       setLaunchWheel(kIntakeLauncherSpeed);
  //     }, 
  //     () -> {
  //       stop();
  //     });
  // }
  public void setLaunchWheel(double speed) {
    launcherGroup.set(speed);
  }
  public void setFeedWheel(double speed) {
    launcherGroup.set(speed);
  }
  public void stop() {
    launcherGroup.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
