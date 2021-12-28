package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class ScoreThread extends Thread implements Runnable{
    DcMotorEx slide;

    ScoreThread(DcMotorEx slide) {
        this.slide = slide;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //paused for 5 sec to simulate workflow.
        //actual code goes here.
    }

}
