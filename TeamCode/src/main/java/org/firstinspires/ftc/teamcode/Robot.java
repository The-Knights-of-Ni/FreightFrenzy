package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Subsystems.Control;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

import java.io.IOException;
import java.util.List;

public class Robot {
  public String name;
  public ElapsedTime timer;
  // DC Motors
  public DcMotorEx frontLeftDriveMotor;
  public DcMotorEx frontRightDriveMotor;
  public DcMotorEx rearRightDriveMotor;
  public DcMotorEx rearLeftDriveMotor;
  public DcMotorEx intake;
  public DcMotorEx bucket;
  // Servos
  public CRServo duckWheel;
  // Odometry
  public List<LynxModule> allHubs;
  public DigitalChannel odometryRA;
  public DigitalChannel odometryRB;
  public DigitalChannel odometryBA;
  public DigitalChannel odometryBB;
  public DigitalChannel odometryLA;
  public DigitalChannel odometryLB;
  public int odRCount = 0;
  public int odBCount = 0;
  public int odLCount = 0;
  /**
   * Control Hub
   *
   * <p>-------------------- Expansion Hub 2
   */

  // Sensors
  public BNO055IMU imu;
  // Declare game pad objects
  public double leftStickX;
  public double leftStickY;
  public double rightStickX;
  public double rightStickY;
  public float triggerLeft;
  public float triggerRight;
  public boolean aButton = false;
  public boolean bButton = false;
  public boolean xButton = false;
  public boolean yButton = false;
  public boolean dPadUp = false;
  public boolean dPadDown = false;
  public boolean dPadLeft = false;
  public boolean dPadRight = false;
  public boolean bumperLeft = false;
  public boolean bumperRight = false;
  public double leftStickX2;
  public double leftStickY2;
  public double rightStickX2;
  public double rightStickY2;
  public float triggerLeft2;
  public float triggerRight2;
  public boolean aButton2 = false;
  public boolean bButton2 = false;
  public boolean xButton2 = false;
  public boolean yButton2 = false;
  public boolean dPadUp2 = false;
  public boolean dPadDown2 = false;
  public boolean dPadLeft2 = false;
  public boolean dPadRight2 = false;
  public boolean bumperLeft2 = false;
  public boolean bumperRight2 = false;
  public boolean isaButtonPressedPrev = false;
  public boolean isbButtonPressedPrev = false;
  public boolean isxButtonPressedPrev = false;
  public boolean isyButtonPressedPrev = false;
  public boolean isdPadUpPressedPrev = false;
  public boolean isdPadDownPressedPrev = false;
  public boolean isdPadLeftPressedPrev = false;
  public boolean isdPadRightPressedPrev = false;
  public boolean islBumperPressedPrev = false;
  public boolean isrBumperPressedPrev = false;
  public boolean isaButton2PressedPrev = false;
  public boolean isbButton2PressedPrev = false;
  public boolean isxButton2PressedPrev = false;
  public boolean isyButton2PressedPrev = false;
  public boolean isdPadUp2PressedPrev = false;
  public boolean isdPadDown2PressedPrev = false;
  public boolean isdPadLeft2PressedPrev = false;
  public boolean isdPadRight2PressedPrev = false;
  public boolean islBumper2PressedPrev = false;
  public boolean isrBumper2PressedPrev = false;
  // Subsystems
  public Drive drive;
  public Control control;
  public Vision vision;
  private AllianceColor allianceColor;
  private HardwareMap hardwareMap;
  private LinearOpMode opMode;
  private double joystickDeadZone = 0.1;

  public Robot(LinearOpMode opMode, ElapsedTime timer) throws IOException {
    hardwareMap = opMode.hardwareMap;
    this.opMode = opMode;
    this.timer = timer;
    init();
  }

  /**
   * @param opMode
   * @param timer
   * @param allianceColor the alliance color o: no camera is initialized 1: only armWebcam is
   *     initialized for OpenCV 2: backWebcam is initialized for Vuforia 3: backWebcam is
   *     initialized for Vuforia and frontWebcam is initialized for OpenCV 4: armWebcam is
   *     initialized for OpenCV and frontWebcam is initialized for OpenCV
   */
  public Robot(LinearOpMode opMode, ElapsedTime timer, AllianceColor allianceColor)
      throws IOException {
    hardwareMap = opMode.hardwareMap;
    this.opMode = opMode;
    this.timer = timer;
    this.allianceColor = allianceColor;
    init();
  }

  public void init() throws IOException {
    // DC Motors
    frontLeftDriveMotor = (DcMotorEx) hardwareMap.dcMotor.get("fl");
    frontRightDriveMotor = (DcMotorEx) hardwareMap.dcMotor.get("fr");
    rearLeftDriveMotor = (DcMotorEx) hardwareMap.dcMotor.get("bl");
    rearRightDriveMotor = (DcMotorEx) hardwareMap.dcMotor.get("br");

    frontRightDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    rearRightDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    //        frontLeftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    //        rearLeftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    frontLeftDriveMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    frontRightDriveMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    rearLeftDriveMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    rearRightDriveMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

    intake = (DcMotorEx) hardwareMap.dcMotor.get("intake");
    intake.setDirection(DcMotorSimple.Direction.REVERSE);
    intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    intake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    intake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    //        intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    intake.setPower(0.0);

    bucket = (DcMotorEx) hardwareMap.dcMotor.get("bucket");
    bucket.setDirection(DcMotorSimple.Direction.REVERSE);
    bucket.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    bucket.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    bucket.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    //        intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    bucket.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    bucket.setPower(0.0);

    // Servos
    duckWheel = new CRServo(hardwareMap, "duckWheel");

    imu = hardwareMap.get(BNO055IMU.class, "imu");

    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    parameters.calibrationDataFile =
        "BNO055IMUCalibration.json"; // see the calibration sample opmode
    parameters.loggingEnabled = true;
    parameters.loggingTag = "IMU";
    parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

    opMode.telemetry.addData("Status", " IMU initializing...");
    opMode.telemetry.update();
    imu.initialize(parameters);
    opMode.telemetry.addData("Status", " IMU calibrating...");
    opMode.telemetry.update();
    // make sure the imu gyro is calibrated before continuing.
    while (opMode.opModeIsActive() && !imu.isGyroCalibrated()) {
      opMode.sleep(50);
      opMode.idle();
    }

    // Subsystems
    opMode.telemetry.addData("Status", " drive initializing...");
    opMode.telemetry.update();
    drive =
        new Drive(
            frontLeftDriveMotor,
            frontRightDriveMotor,
            rearLeftDriveMotor,
            rearRightDriveMotor,
            imu,
            opMode,
            timer);

    //        drive.setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //        drive.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    //        control = new Control(intake, launch1, launch2, imu, opMode, timer, wobbleClaw,
    // wobbleGoalArm);
    //        control = new Control(intake, launch1, launch2a, launch2b, imu, opMode, timer,
    // wobbleClaw, wobbleGoalArm);
    opMode.telemetry.addData("Status", " control initializing...");
    control = new Control(intake, bucket, duckWheel, imu, opMode, timer);

    opMode.telemetry.addData("Status", " vision initializing...");
    opMode.telemetry.update();
    vision = new Vision(opMode.telemetry, hardwareMap, timer, allianceColor);
    opMode.telemetry.addData("Status", " done, wait for start");
  }

  public void initServosAuto() {
    // code here
  }

  public OpMode getOpmode() {
    return this.opMode;
  }

  public void getGamePadInputs() {
    isaButtonPressedPrev = aButton;
    isbButtonPressedPrev = bButton;
    isxButtonPressedPrev = xButton;
    isyButtonPressedPrev = yButton;
    isdPadUpPressedPrev = dPadUp;
    isdPadDownPressedPrev = dPadDown;
    isdPadLeftPressedPrev = dPadLeft;
    isdPadRightPressedPrev = dPadRight;
    islBumperPressedPrev = bumperLeft;
    isrBumperPressedPrev = bumperRight;
    leftStickX = joystickDeadzoneCorrection(opMode.gamepad1.left_stick_x);
    leftStickY = joystickDeadzoneCorrection(-opMode.gamepad1.left_stick_y);
    rightStickX = joystickDeadzoneCorrection(opMode.gamepad1.right_stick_x);
    rightStickY = joystickDeadzoneCorrection(opMode.gamepad1.right_stick_y);
    triggerLeft = opMode.gamepad1.left_trigger;
    triggerRight = opMode.gamepad1.right_trigger;
    aButton = opMode.gamepad1.a;
    bButton = opMode.gamepad1.b;
    xButton = opMode.gamepad1.x;
    yButton = opMode.gamepad1.y;
    dPadUp = opMode.gamepad1.dpad_up;
    dPadDown = opMode.gamepad1.dpad_down;
    dPadLeft = opMode.gamepad1.dpad_left;
    dPadRight = opMode.gamepad1.dpad_right;
    bumperLeft = opMode.gamepad1.left_bumper;
    bumperRight = opMode.gamepad1.right_bumper;

    isaButton2PressedPrev = aButton2;
    isbButton2PressedPrev = bButton2;
    isxButton2PressedPrev = xButton2;
    isyButton2PressedPrev = yButton2;
    isdPadUp2PressedPrev = dPadUp2;
    isdPadDown2PressedPrev = dPadDown2;
    isdPadLeft2PressedPrev = dPadLeft2;
    isdPadRight2PressedPrev = dPadRight2;
    islBumper2PressedPrev = bumperLeft2;
    isrBumper2PressedPrev = bumperRight2;
    leftStickX2 = joystickDeadzoneCorrection(opMode.gamepad2.left_stick_x);
    leftStickY2 = joystickDeadzoneCorrection(-opMode.gamepad2.left_stick_y);
    rightStickX2 = joystickDeadzoneCorrection(opMode.gamepad2.right_stick_x);
    rightStickY2 = joystickDeadzoneCorrection(-opMode.gamepad2.right_stick_y);
    triggerLeft2 = opMode.gamepad2.left_trigger;
    triggerRight2 = opMode.gamepad2.right_trigger;
    aButton2 = opMode.gamepad2.a;
    bButton2 = opMode.gamepad2.b;
    xButton2 = opMode.gamepad2.x;
    yButton2 = opMode.gamepad2.y;
    dPadUp2 = opMode.gamepad2.dpad_up;
    dPadDown2 = opMode.gamepad2.dpad_down;
    dPadLeft2 = opMode.gamepad2.dpad_left;
    dPadRight2 = opMode.gamepad2.dpad_right;
    bumperLeft2 = opMode.gamepad2.left_bumper;
    bumperRight2 = opMode.gamepad2.right_bumper;
  }

  public double joystickDeadzoneCorrection(double joystickInput) {
    double joystickOutput;
    if (joystickInput > joystickDeadZone) {
      joystickOutput = (joystickInput - joystickDeadZone) / (1.0 - joystickDeadZone);
    } else if (joystickInput > -joystickDeadZone) {
      joystickOutput = 0.0;
    } else {
      joystickOutput = (joystickInput + joystickDeadZone) / (1.0 - joystickDeadZone);
    }
    return joystickOutput;
  }
}
