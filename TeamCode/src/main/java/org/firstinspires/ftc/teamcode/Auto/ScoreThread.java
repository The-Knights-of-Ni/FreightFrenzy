package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class ScoreThread extends Thread implements Runnable{
    DcMotorEx slide;
    boolean direction;
    int placementLevel;

    ScoreThread(DcMotorEx slide, boolean direction, int placementLevel) {
        this.slide = slide;
        this.direction = direction;
        this.placementLevel = placementLevel;
    }

    @Override
    public void run() {
        if (direction) {
            //extend code goes into the switch.
            switch(placementLevel) {
                case 1:
                    //lower level
                    break;
                case 2:
                    //middle level
                    break;
                case 3:
                    //upper level
                    break;
            }
        } else {
            //retract code goes here.
        }
    }

}
