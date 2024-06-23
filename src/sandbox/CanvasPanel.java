package sandbox;

import graphics.Draw;
import models.JediShip;
import models.Venator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import utils.CustomThread;

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

    public CanvasPanel(int width, int height) {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;

        this.director = new int[]{0, 0, 1500};

        this.origin2D = new int[]{width / 2, height / 2};

        // Transformations
        this.scale = 1;
        this.angle = 0;
        this.x = 2000;
        this.growing = false;
        this.toRight = true;

        CustomThread scaleThread = new CustomThread(() -> {
            if (growing) {
                scale += 0.01;
                if (scale >= 1.5) growing = false;
            }
            else {
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

        }, 10, () -> false);
        rotateThread.start();

        CustomThread moveThread = new CustomThread(() -> {
                x --;
           /* if (toRight) {
                if (x >= 50) toRight = false;
            }
            else {
                x --;
                if (x <= -50) toRight = true;
            }*/
        }, 10, () -> false);
        moveThread.start();

       /* this.venator = new Venator(500, 250, 500, 1, new double[]{0, 0, 0});
        this.jediShip = new JediShip(500, 250, 500, 1, new double[]{0, 0, 0});*/
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;

        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        origin2D = new int[]{width / 2, height / 2};
    }

    // ------------------------------ Guides ------------------------------
    public void grid() {
        Color gray2 = new Color(68, 75, 114);
        Color gray = new Color(49, 55, 91);

        // Vertical Lines
        for (int i = 0; i < width; i++) {
            if (i % 50 == 0) {
                for (int j = 0; j < height; j++) {
                    if (i % 100 == 0) {
                        this.buffer.setRGB(i, j, gray2.getRGB());
                    } else {
                        this.buffer.setRGB(i, j, gray.getRGB());
                    }
                }
            }
        }

        // Horizontal Lines
        for (int j = 0; j < height; j++) {
            if (j % 50 == 0) {
                for (int i = 0; i < width; i++) {
                    if (j % 100 == 0) {
                        this.buffer.setRGB(i, j, gray2.getRGB());
                    } else {
                        this.buffer.setRGB(i, j, gray.getRGB());
                    }
                }
            }
        }
    }

    public void axis(int[] director, double scale, BufferedImage buffer) {
        int[][] points = new int[][]{
                new int[]{0, 0, 0},
                new int[]{500, 0, 0},
                new int[]{-500, 0, 0},
                new int[]{0, 500, 0},
                new int[]{0, -500, 0},
                new int[]{0, 0, 500},
                new int[]{0, 0, -500},
        };
        int[][] projectedPoints = projection(points, director, "oblique");

        int[] xPoints = projectedPoints[0];
        int[] yPoints = projectedPoints[1];

        for (int i = 1; i <= 3; i++) {
            Draw.drawLine(xPoints[(i * 2) - 1], yPoints[(i * 2) - 1], xPoints[i * 2], yPoints[i * 2], null, buffer);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics gBuffer = buffer.getGraphics();
        gBuffer.setColor(new Color(39, 43, 76));
        gBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
        //grid();
        //axis(director, 1, buffer);

        venator.angles = new double[]{Math.PI / 2, 0, Math.PI};
        //venator.xc = x;
        //venator.yc = x;
        //venator.zc = x;
        //venator.draw(director, "perspective", buffer, angle);

        /*jediShip.angles = new double[]{Math.PI / 2, 0, Math.PI / 2};
        jediShip.draw(false, director, "oblique", buffer, angle);*/



        g.drawImage(buffer, 0, 0, this);
    }

}