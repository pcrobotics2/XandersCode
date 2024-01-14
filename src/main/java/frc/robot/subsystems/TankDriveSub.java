// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDriveSub extends SubsystemBase {
  /** Creates a new Drivetrain. */
  public MotorController motorControllerLeftFront;
  public MotorController motorControllerLeftBack;
  public MotorController motorControllerRightFront;
  public MotorController motorControllerRightBack;
  
  public MotorControllerGroup leftMotors;
  public MotorControllerGroup rightMotors;

  DifferentialDrive tankDriveSub;
  public TankDriveSub() {

    motorControllerLeftFront = new Spark(0);
    motorControllerLeftBack = new Spark(1);
    motorControllerRightFront = new Spark(2);
    motorControllerRightBack = new Spark(3);

    this.leftMotors = new MotorControllerGroup(
      this.motorControllerLeftBack,
      this.motorControllerLeftFront
    );
    this.rightMotors = new MotorControllerGroup(
      this.motorControllerRightBack,
      this.motorControllerRightFront
    );

    tankDriveSub = new DifferentialDrive(leftMotors, rightMotors);

    rightMotors.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public void drive(double leftSpeed, double rightSpeed) {
  leftSpeed *= Constants.driveSpeed;
  rightSpeed *= Constants.driveSpeed;



  tankDriveSub.tankDrive(leftSpeed, rightSpeed);

}
}
