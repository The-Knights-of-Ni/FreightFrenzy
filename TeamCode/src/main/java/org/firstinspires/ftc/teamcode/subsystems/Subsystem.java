package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Superclass to all subsystems, it does some bootstrapping for them (Vision, Control, and Drive)
 */
public abstract class Subsystem {
    // protected because of inheritance
    protected Telemetry telemetry;
    protected ElapsedTime timer;
    protected HardwareMap hardwareMap;

    public Subsystem(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
        this.timer = timer;
    }
}
