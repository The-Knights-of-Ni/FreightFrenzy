package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.DetectMarkerPipeline;
import org.firstinspires.ftc.teamcode.util.AllianceColor;

import java.io.IOException;

@Autonomous(name = "Vision Test", group = "Concept")
public class VisionTest extends LinearOpMode {
    private Robot robot;
    private ElapsedTime timer;

    private void initOpMode() throws IOException {
        telemetry.addData("Init Robot", "");
        telemetry.update();
        timer = new ElapsedTime();

        this.robot = new Robot(this, timer, AllianceColor.BLUE);

        telemetry.addData("Wait for start", "");
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        try {
            this.initOpMode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DetectMarkerPipeline.MarkerLocation location;

        waitForStart();

        while (opModeIsActive()) {
            location = robot.vision.detectMarkerRun();
            switch (location) {
                case LEFT:
                    telemetry.addData("Location", "LEFT");
                    break;
                case MIDDLE:
                    telemetry.addData("Location", "MIDDLE");
                    break;
                case RIGHT:
                    telemetry.addData("Location", "RIGHT");
                    break;
                case NOT_FOUND:
                    telemetry.addData("Location", "NOT_FOUND");
                    break;
            }
            telemetry.update();
        }
    }
}
