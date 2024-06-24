package views;

import graphics.Draw;
import models.JediShip;
import models.Venator;
import utils.CustomThread;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static graphics.Draw3d.projection;

public class CanvasPanel extends JPanel {
    public BufferedImage buffer;
    public BufferedImage starsBuffer;
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
    public JediShip anakin;
    public JediShip obiWan;
    public String projection;
    public boolean starsDraw;

    public int[][] stars;

    int starsy;

    public CanvasPanel(int width, int height) {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.starsBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;

        this.director = new int[]{0, 0, 1500};
        this.projection = "perspective";

        this.origin2D = new int[]{width / 2, height / 2};

        // Transformations
        this.scale = 1;
        this.angle = 0;
        this.x = 500;
        this.growing = false;
        this.toRight = true;

        this.starsDraw = false;
        this.starsy = 0;

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
        int cosa = 0;
        CustomThread rotateThread = new CustomThread(() -> {
            angle += 0.01;
            if (angle >= 2 * Math.PI) {
                angle = 0;
            }
            //venator.rotate(new double[]{0.1, 0, 0});

        }, 50, () -> false);


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

        //this.venator = new Venator(450, 260, 310, director, projection, buffer);
        this.venator = new Venator(350, 290, 610, director, projection, buffer);
        //venator.rotate(new double[]{0,0, 0});


        this.anakin = new JediShip(350, 200, 500, director, projection, new Color(228, 175, 54), buffer);
        this.obiWan = new JediShip(250, 340, 500, director, projection, new Color(152, 65, 60), buffer);


       /* anakin.rotate(new double[]{Math.PI / 2, 0,  Math.PI / 2});
        obiWan.rotate(new double[]{Math.PI / 2, 0,  Math.PI / 2});*/
        //anakin.rotate(new double[]{0, Math.PI / 2, 0});


        rotateThread.start();

        AnimationThread animation = new AnimationThread(this);
        animation.start();
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;

        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        origin2D = new int[]{width / 2, height / 2};
    }


    public void drawStars() {
        Graphics gStarsBuffer = starsBuffer.getGraphics();
        gStarsBuffer.setColor(new Color(10, 10, 10));
        gStarsBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());

        if(!starsDraw) {
            stars = generateStars(width, height + 1800, 30, 400);
            starsDraw = true;
            CustomThread starsThread = new CustomThread(() -> {
                for(int i = 0; i < stars.length; i++) {
                    stars[i][0]++;
                    if(stars[i][0] >= width) stars[i][0] = 0;
                }
            }, 3, () -> false);
            starsThread.start();
        }

        for (int i = 0; i < stars.length; i++) {
            Draw.draw(stars[i][0], stars[i][1] - 1200 - 300 + starsy, Color.white, starsBuffer);
        }

    }

    public static int[][] generateStars(int ancho, int largo, double separacionPromedio, int cantidadPuntos) {
        ArrayList<int[]> puntosList = new ArrayList<>();
        Random random = new Random();

        while (puntosList.size() < cantidadPuntos) {
            int x = random.nextInt(ancho);
            int y = random.nextInt(largo);
            int[] nuevoPunto = {x, y};

            boolean esValido = true;
            for (int[] punto : puntosList) {
                if (distancia(nuevoPunto, punto) < separacionPromedio) {
                    esValido = false;
                    break;
                }
            }

            if (esValido) {
                puntosList.add(nuevoPunto);
            }
        }

        int[][] puntos = new int[puntosList.size()][2];
        for (int i = 0; i < puntosList.size(); i++) {
            puntos[i] = puntosList.get(i);
        }

        return puntos;
    }

    private static double distancia(int[] punto1, int[] punto2) {
        int dx = punto1[0] - punto2[0];
        int dy = punto1[1] - punto2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void paint(Graphics g) {
        super.paint(g);


        drawStars();



        Graphics gBuffer = buffer.getGraphics();
        gBuffer.drawImage(starsBuffer, 0, 0, this);


        venator.draw(director, projection, false, buffer, angle);

        anakin.draw(true, director, projection, false, buffer, angle);
        obiWan.draw(true, director, projection, false, buffer, angle);


        g.drawImage(buffer, 0, 0, this);
    }

}