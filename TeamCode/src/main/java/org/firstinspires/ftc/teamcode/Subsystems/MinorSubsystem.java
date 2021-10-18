package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Abstract superclass to all minor subsystems (Vision, Control, and Drive)
 *
 */

public class MinorSubsystem extends Subsystem {
    protected LinearOpMode opMode;
    protected Telemetry telemetry;
    protected ElapsedTime timer;
    protected HardwareMap hardwareMap;
    public void init() {}
}