// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FeedWheelCMD;
import frc.robot.commands.LaunchWheelCMD;
import frc.robot.commands.Auto.Auto01;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LauncherSub;
import frc.robot.subsystems.TankDriveSub;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private TankDriveSub tankDriveSub = new TankDriveSub();

  private Auto01 auto01 = new Auto01(tankDriveSub); 

  SendableChooser<Command> m_Chooser = new SendableChooser<>();
//careful setting the port for controller
  public CommandJoystick controller5 = new CommandJoystick(0);
  public LauncherSub launcherSub = new LauncherSub();
  public FeedWheelCMD feedWheelCMD = new FeedWheelCMD(launcherSub);
  public LaunchWheelCMD launchWheelCMD = new LaunchWheelCMD(launcherSub);
  // The robot's subsystems and commands are definelad here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings

    configureBindings();
    tankDriveSub.setDefaultCommand(
      new RunCommand(
        () -> tankDriveSub.drive(
          controller5.getRawAxis(1),
          controller5.getRawAxis(5)
        ), 
        tankDriveSub
      )
    );
    controller5.button(Constants.buttonList.y).whileTrue(launchWheelCMD);
    controller5.button(Constants.buttonList.a).whileTrue(feedWheelCMD);

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    m_Chooser.setDefaultOption("auto01", auto01);
    //m_Chooser.addOption(Name, auto02);
    SmartDashboard.putData("Auto Choices", m_Chooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_Chooser.getSelected();
  }
}
