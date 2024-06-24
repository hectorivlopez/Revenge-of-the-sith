package views;

import models.JediShip;
import models.Venator;
import utils.CustomThread;

public class AnimationThread extends Thread {
    CanvasPanel canvas;
    JediShip obiWan;
    JediShip anakin;
    Venator venator;
    int count;

    boolean forward;

    double angle;

    boolean up;

    public AnimationThread(CanvasPanel canvas) {
        this.canvas = canvas;
        this.obiWan = canvas.obiWan;
        this.anakin = canvas.anakin;
        this.venator = canvas.venator;
        this.count = 0;
        this.forward = true;
        this.angle = 0;
        this.up = true;
    }

    public void run() {
        //venator.scale = 1.2;

       /* venator.rotate(new double[]{0, 0, Math.PI - 0.26});
        venator.rotate(new double[]{5.6, 0, 0});*/

        venator.rotate(new double[]{Math.PI / 2, 0 , 0});
        anakin.scale = 0.4;
        anakin.rotate(new double[]{Math.PI / 2 - 0.8, 0, 0});

        obiWan.scale = 0.4;
        obiWan.rotate(new double[]{Math.PI / 2 - 0.7, 0, 0});

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CustomThread counter = new CustomThread(() -> {
            count++;
            if(count % 50 == 0) forward = !forward;

            if(count % 100 == 0) {
                up = !up;

            }

        }, 100, () -> false);
        counter.start();

        CustomThread cameraThread = new CustomThread(() -> {
            if(up) canvas.director[1]++;
            else canvas.director[1]--;

        }, 30, () -> false);
        cameraThread.start();

        CustomThread rotateThread = new CustomThread(() -> {

            venator.rotate(new double[]{0, 0, 0.01});


        }, 5, () -> false);
        rotateThread.start();



        CustomThread move = new CustomThread(() -> {
            if(forward) {
                anakin.move(1, 0, 0);
                obiWan.move(1, 0, 0);
                anakin.scale -= 0.00035;
                obiWan.scale -= 0.00035;

            }
            else {
                anakin.move(-1, 0, 0);
                obiWan.move(-1, 0, 0);
                anakin.scale += 0.00035;
                obiWan.scale += 0.00035;
            }


        }, 5, () -> false);
        move.start();

        while (true) {

            CustomThread rotateShip = new CustomThread(() -> {
                angle += 0.1;
                anakin.rotate(new double[]{0.1, 0, 0});
                obiWan.rotate(new double[]{0.1, 0, 0});


            }, 20, () -> angle >= 4 * Math.PI);
            rotateShip.start();
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            angle = 0;
        }


        //venator.rotate(new double[]{0, 6.1, 0});

        /*try {
            sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CustomThread rotateThread = new CustomThread(() -> {
            count++;
            venator.rotate(new double[]{-0.01, -0.01, -0.01});
           *//* venator.rotate(new double[]{-0.01, 0, 0});
            venator.rotate(new double[]{0, -0.01, 0});*//*

        }, 200, () -> count >= 18);
        rotateThread.start();

        CustomThread move = new CustomThread(() -> {
            venator.move(-1, 0, 1);

        }, 5, () -> count >= 18);
        move.start();

        try {
            sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        count = 0;


        CustomThread scale = new CustomThread(() -> {
            count++;
            venator.scale += 0.01;

        }, 100, () -> count >= 60);
        scale.start();

        rotateThread = new CustomThread(() -> {
            if (count < 40) venator.rotate(new double[]{0, -0.01, -0.01});
            else venator.rotate(new double[]{0, -0.01, 0});


        }, 50, () -> count >= 60);
        rotateThread.start();


        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        move = new CustomThread(() -> {
            venator.move(1, 1, -2);

        }, 3, () -> count >= 40);
        move.start();

        try {
            sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count = 0;

        move = new CustomThread(() -> {
            count++;
            venator.move(2, 0, 0);

        }, 3, () -> count >= 100);
        move.start();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count = 0;

        rotateThread = new CustomThread(() -> {
            count++;
            venator.rotate(new double[]{-0.1, -0.1, 0});
            venator.move(0, 0, -2);


        }, 50, () -> count >= 50);
        rotateThread.start();*/
    }
}
