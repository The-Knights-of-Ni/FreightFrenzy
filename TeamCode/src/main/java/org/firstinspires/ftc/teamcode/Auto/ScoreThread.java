package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class ScoreThread extends Thread implements Runnable{
    DcMotorEx slide;
    boolean direction;

    ScoreThread(DcMotorEx slide, boolean direction) {
        this.slide = slide;
        this.direction = direction;
    }

    @Override
    public void run() {
        if (direction) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //paused for 5 sec to simulate workflow.
            //extend code goes here.
        } else {
            //retract code goes here.
        }
    }

}
