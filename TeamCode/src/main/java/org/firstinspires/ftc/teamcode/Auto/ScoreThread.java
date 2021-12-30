package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class ScoreThread extends Thread implements Runnable{
    DcMotorEx slide;
    boolean direction;
    int placementLevel;
    final int RETRACTED = 0;
    final int BOTTOM = -481;
    final int MIDDLE = -758;
    final int TOP = -1292;

    public ScoreThread(DcMotorEx slide, boolean direction, int placementLevel) {
        this.slide = slide;
        this.direction = direction;
        this.placementLevel = placementLevel;
    }

    @Override
    public void run() {
        if (direction) {
            switch(placementLevel) {
                case 1:
                    //lower level
                    slide.setTargetPosition(BOTTOM);
                    slide.setPower(0.5);
                    slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    break;
                case 2:
                    //middle level
                    slide.setTargetPosition(MIDDLE);
                    slide.setPower(0.5);
                    slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    break;
                case 3:
                    //upper level
                    slide.setTargetPosition(TOP);
                    slide.setPower(0.5);
                    slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    break;
            }
        } else {
            //retraction
            slide.setTargetPosition(RETRACTED);
            slide.setPower(0.5);
            slide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        }
    }

}
