package org.firstinspires.ftc.teamcode.Teleop;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Control;

public class IntakeThread extends Thread {
    Robot robot;
    Telemetry telemetry;
    boolean status, direction;
    Control.BucketState bucketState;

    public IntakeThread(Telemetry telemetry, boolean status, boolean direction, Control.BucketState bucketState) {
        this.telemetry = telemetry;
        this.status = status;
        this.direction = direction;
        this.bucketState = bucketState;
    }

    @Override
    public void run() {
        while(!this.isInterrupted()) {
            try {
                robot.control.setIntakeDirection(status, direction);
                Thread.sleep(1000);
                robot.control.setBucketState(bucketState);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
