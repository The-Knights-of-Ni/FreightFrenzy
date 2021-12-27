package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class ScoreThread extends Thread implements Runnable{
    DcMotorEx left;
    DcMotorEx right;

    ScoreThread(DcMotorEx left, DcMotorEx right) {
        this.left = left;
        this.right = right;
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
