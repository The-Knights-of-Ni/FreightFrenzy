package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Subsystems.Control;
import org.firstinspires.ftc.teamcode.Subsystems.Control.PlacementLevel;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Util.AllianceColor;

@Autonomous(name = "Auto Red Secondary", group = "Auto Red")
public class AutoRedSecondary extends Auto {
    @Override
    public void runOpMode() throws InterruptedException {
        initAuto(AllianceColor.RED);
        PlacementLevel placementLevel = getHubLevel();
        waitForStart();
        Drive drive = robot.drive;

        if(placementLevel != PlacementLevel.NOT_FOUND) {
            telemetry.addData("Level", placementLevel);
            telemetry.update();
        }

        robot.control.setBucketState(Control.BucketState.LEVEL);
        robot.control.setLidPosition(Control.LidPosition.CLOSED);

        drive.moveForward(2 * mmPerInch);
        drive.turnByAngle(40);

        double adjustment = 0;
        switch(placementLevel) {
            case BOTTOM:
                adjustment = 2.5;
                break;
            case MIDDLE:
            case TOP:
                adjustment = 5;
                break;
        }
        drive.moveForward((12 + adjustment) * mmPerInch);

        drive.moveBackward(10 * mmPerInch);


    }
}
