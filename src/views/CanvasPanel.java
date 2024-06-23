package views;

import graphics.Draw;
import models.JediShip;
import models.Venator;
import utils.CustomThread;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static graphics.Draw3d.projection;

public class CanvasPanel extends JPanel {
    public BufferedImage buffer;
    public int[] director;
    public int[] origin2D;
    public int width;
    public int height;

    public double scale;
    public double angle;
    public int x;
    public boolean growing;
    public boolean toRight;
    public Venator venator;
    public JediShip jediShip;
    public String projection;

    public CanvasPanel(int width, int height) {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;

        this.director = new int[]{0, 0, 1500};
        this.projection = "oblique";

        this.origin2D = new int[]{width / 2, height / 2};

        // Transformations
        this.scale = 1;
        this.angle = 0;
        this.x = 500;
        this.growing = false;
        this.toRight = true;

        CustomThread scaleThread = new CustomThread(() -> {
            if (growing) {
                scale += 0.01;
                if (scale >= 1.5) growing = false;
            } else {
                scale -= 0.01;
                if (scale <= 0.8) growing = true;
            }
        }, 20, () -> false);
        scaleThread.start();

        CustomThread rotateThread = new CustomThread(() -> {
            angle += 0.01;
            if (angle >= 2 * Math.PI) {
                angle = 0;
            }
            venator.rotate(new double[]{0, 0, angle});

        }, 10, () -> false);


        CustomThread moveThread = new CustomThread(() -> {
            x--;
           /* if (toRight) {
                if (x >= 50) toRight = false;
            }
            else {
                x --;
                if (x <= -50) toRight = true;
            }*/
        }, 10, () -> false);
        moveThread.start();

        this.venator = new Venator(500, 250, 300, director, projection, buffer);
        //venator.rotate(new double[]{0, 0, Math.PI / 2});
        rotateThread.start();


        //this.jediShip = new JediShip(500, 250, 500, 1, new double[]{0, 0, 0});
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;

        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        origin2D = new int[]{width / 2, height / 2};
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics gBuffer = buffer.getGraphics();
        gBuffer.setColor(new Color(10, 10, 10));
        gBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());



        venator.draw(director, projection, buffer, angle);




        g.drawImage(buffer, 0, 0, this);
    }

}