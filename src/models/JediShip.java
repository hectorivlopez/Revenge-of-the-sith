package models;

import graphics.Surface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static graphics.Draw.*;
import static graphics.Draw3d.*;
import static graphics.Transformations.*;

public class JediShip {
    public int xc;
    public int yc;
    public int zc;

    public int[] center;

    public int x0;
    public int y0;
    public int z0;

    public boolean povUp;
    public boolean povLeft;
    public boolean povFront;

    public double[] angles;
    public double scale;
    public double ang;

    private int[][] leftAxis;
    private int[][] rightAxis;
    private double xInclination;
    int thickness;
    int zBottom;


    int[][] leftWingTop;
    int[][] scaledLeftWingTop;
    int[][] leftWingBottom;
    int[][] scaledLeftWingBottom;

    int[][] rightWingTop;
    int[][] scaledRightWingTop;
    int[][] rightWingBottom;
    int[][] scaledRightWingBottom;

    public JediShip(int xc, int yc, int zc, double scale, double[] angles) {
        this.xc = xc;
        this.yc = yc;
        this.zc = zc;
        this.center = new int[]{xc, yc, zc};

        this.x0 = xc + 100;
        this.y0 = yc + 100;
        this.z0 = zc + 100;

        this.angles = angles;
        this.scale = scale;
        this.ang = 0;

        this.xInclination = 0.25;
        this.thickness = 12;
        this.zBottom = zc - thickness;

        this.leftAxis = new int[][]{new int[]{xc + 240, yc - 30, zc}, new int[]{xc - 10, yc - 30, zc},};
        this.rightAxis = new int[][]{new int[]{xc + 240, yc + 30, zc}, new int[]{xc - 10, yc + 30, zc},};

    }

    public void draw(int[] director, String projection, BufferedImage buffer, double ang) {
        this.ang = ang;

        calWings();

        boolean[] povCenter = calPov(center, director, projection);
        povUp = povCenter[0];
        povLeft = povCenter[1];
        povFront = povCenter[2];

    }

    // ---------- Calculations ----------
    public void calWings() {
        // Top
        leftWingTop = new int[][]{
                new int[]{xc + 240, xc + 250, xc + 100, xc + 85, xc + 50, xc + 50, xc - 10, xc - 115, xc - 210, xc - 200, xc - 150, xc - 100, xc - 70, xc - 10},
                new int[]{yc - 30, yc - 60, yc - 100, yc - 90, yc - 90, yc - 110, yc - 190, yc - 190, yc - 60, yc - 40, yc - 50, yc - 50, yc - 45, yc - 30},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        scaledLeftWingTop = transform3D(leftWingTop, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        rightWingTop = new int[][]{
                new int[]{xc + 240, xc + 250, xc + 100, xc + 85, xc + 50, xc + 50, xc - 10, xc - 115, xc - 210, xc - 200, xc - 150, xc - 100, xc - 70, xc - 10},
                new int[]{yc + 30, yc + 60, yc + 100, yc + 90, yc + 90, yc + 110, yc + 190, yc + 190, yc + 60, yc + 40, yc + 50, yc + 50, yc + 45, yc + 30},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        scaledRightWingTop = transform3D(rightWingTop, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        // Bottom
        leftWingBottom = new int[][]{
                leftWingTop[0],
                leftWingTop[1],
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        scaledLeftWingBottom = transform3D(leftWingBottom, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        rightWingBottom = new int[][]{
                rightWingTop[0],
                rightWingTop[1],
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        scaledRightWingBottom = transform3D(rightWingBottom, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

    }

    // ---------- Drawing ----------
    // Top
    public void drawLeftWingTop(int[] director, String projection, Color color, BufferedImage buffer) {

        int[][] top1 = new int[][]{
                new int[]{leftWingTop[0][0], leftWingTop[0][1], leftWingTop[0][2], leftWingTop[0][3], leftWingTop[0][4], xc + 50,},
                new int[]{leftWingTop[1][0], leftWingTop[1][1], leftWingTop[1][2], leftWingTop[1][3], leftWingTop[1][4], yc - 30},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop1 = transform3D(top1, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][] top2 = new int[][]{
                new int[]{leftWingTop[0][5], leftWingTop[0][6], leftWingTop[0][11], leftWingTop[0][12], leftWingTop[0][13], xc + 50,},
                new int[]{leftWingTop[1][5], leftWingTop[1][6], leftWingTop[1][11], leftWingTop[1][12], leftWingTop[1][13], yc - 30,},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop2 = transform3D(top2, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][] top3 = new int[][]{
                new int[]{leftWingTop[0][6], leftWingTop[0][7], leftWingTop[0][8], leftWingTop[0][9], leftWingTop[0][10], leftWingTop[0][11]},
                new int[]{leftWingTop[1][6], leftWingTop[1][7], leftWingTop[1][8], leftWingTop[1][9], leftWingTop[1][10], leftWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop3 = transform3D(top3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        // Bottom

        int[][] bottom1 = new int[][]{
                new int[]{leftWingBottom[0][0], leftWingBottom[0][1], leftWingBottom[0][2], leftWingBottom[0][3], leftWingBottom[0][4], xc + 50,},
                new int[]{leftWingBottom[1][0], leftWingBottom[1][1], leftWingBottom[1][2], leftWingBottom[1][3], leftWingBottom[1][4], yc - 30},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom1 = transform3D(bottom1, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][] bottom2 = new int[][]{
                new int[]{leftWingBottom[0][5], leftWingBottom[0][6], leftWingBottom[0][11], leftWingBottom[0][12], leftWingBottom[0][13], xc + 50,},
                new int[]{leftWingBottom[1][5], leftWingBottom[1][6], leftWingBottom[1][11], leftWingBottom[1][12], leftWingBottom[1][13], yc - 30,},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom2 = transform3D(bottom2, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][] bottom3 = new int[][]{
                new int[]{leftWingBottom[0][6], leftWingBottom[0][7], leftWingBottom[0][8], leftWingBottom[0][9], leftWingBottom[0][10], leftWingBottom[0][11]},
                new int[]{leftWingBottom[1][6], leftWingBottom[1][7], leftWingBottom[1][8], leftWingBottom[1][9], leftWingBottom[1][10], leftWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom3 = transform3D(bottom3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][][] leftFaces = new int[][][]{scaledLeftWingTop, scaledLeftWingBottom};
        //drawPolyhedronFaces(leftFaces, null, -1, director, projection, Color.white, Color.red, buffer);

        // --------------- Drawing ---------------
        drawWingSides(scaledLeftWingTop, scaledLeftWingBottom, 1, director, projection, buffer);

        drawSurface(scaledTop1, director, projection, -1, false, null, color, buffer);
        drawSurface(scaledTop2, director, projection, -1, false, null, color, buffer);
        //drawSurface(scaledTop3, director, projection, -1, false, null, color, buffer);

        drawSurface(scaledBottom1, director, projection, 1, false, null, color, buffer);
        drawSurface(scaledBottom2, director, projection, 1, false, null, color, buffer);
        //drawSurface(scaledBottom3, director, projection, 1, false, null, color, buffer);

    }

    public void drawRightWingTop(int[] director, String projection, Color color, BufferedImage buffer) {
        int zBottom = zc - thickness;

        // Top

        int[][] top1 = new int[][]{
                new int[]{rightWingTop[0][0], rightWingTop[0][1], rightWingTop[0][2], rightWingTop[0][3], rightWingTop[0][4], xc + 50,},
                new int[]{rightWingTop[1][0], rightWingTop[1][1], rightWingTop[1][2], rightWingTop[1][3], rightWingTop[1][4], yc + 30},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop1 = transform3D(top1, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][] top2 = new int[][]{
                new int[]{rightWingTop[0][5], rightWingTop[0][6], rightWingTop[0][11], rightWingTop[0][12], rightWingTop[0][13], xc + 50,},
                new int[]{rightWingTop[1][5], rightWingTop[1][6], rightWingTop[1][11], rightWingTop[1][12], rightWingTop[1][13], yc + 30,},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop2 = transform3D(top2, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][] top3 = new int[][]{
                new int[]{rightWingTop[0][6], rightWingTop[0][7], rightWingTop[0][8], rightWingTop[0][9], rightWingTop[0][10], rightWingTop[0][11]},
                new int[]{rightWingTop[1][6], rightWingTop[1][7], rightWingTop[1][8], rightWingTop[1][9], rightWingTop[1][10], rightWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop3 = transform3D(top3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        // Bottom
        int[][] bottom1 = new int[][]{
                new int[]{rightWingBottom[0][0], rightWingBottom[0][1], rightWingBottom[0][2], rightWingBottom[0][3], rightWingBottom[0][4], xc + 50,},
                new int[]{rightWingBottom[1][0], rightWingBottom[1][1], rightWingBottom[1][2], rightWingBottom[1][3], rightWingBottom[1][4], yc + 30},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom1 = transform3D(bottom1, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][] bottom2 = new int[][]{
                new int[]{rightWingBottom[0][5], rightWingBottom[0][6], rightWingBottom[0][11], rightWingBottom[0][12], rightWingBottom[0][13], xc + 50,},
                new int[]{rightWingBottom[1][5], rightWingBottom[1][6], rightWingBottom[1][11], rightWingBottom[1][12], rightWingBottom[1][13], yc + 30,},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom2 = transform3D(bottom2, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][] bottom3 = new int[][]{
                new int[]{rightWingBottom[0][6], rightWingBottom[0][7], rightWingBottom[0][8], rightWingBottom[0][9], rightWingBottom[0][10], rightWingBottom[0][11]},
                new int[]{rightWingBottom[1][6], rightWingBottom[1][7], rightWingBottom[1][8], rightWingBottom[1][9], rightWingBottom[1][10], rightWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom3 = transform3D(bottom3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][][] leftFaces = new int[][][]{scaledRightWingTop, scaledRightWingBottom};
        //drawPolyhedronFaces(leftFaces, null, -1, director, projection, Color.white, Color.red, buffer);

        // --------------- Drawing ---------------
        drawWingSides(scaledRightWingTop, scaledRightWingBottom, -1, director, projection, buffer);

        drawSurface(scaledTop1, director, projection, 1, false, Color.black, color, buffer);
        drawSurface(scaledTop2, director, projection, 1, false, Color.black, color, buffer);
       // drawSurface(scaledTop3, director, projection, 1, false, Color.black, color, buffer);

        drawSurface(scaledBottom1, director, projection, -1, false, Color.black, color, buffer);
        drawSurface(scaledBottom2, director, projection, -1, false, Color.black, color, buffer);
       // drawSurface(scaledBottom3, director, projection, -1, false, Color.black, color, buffer);

    }


    public void drawLeftWingTopBack(int[] director, String projection, Color color, BufferedImage buffer) {
        int[][] top3 = new int[][]{
                new int[]{leftWingTop[0][6], leftWingTop[0][7], leftWingTop[0][8], leftWingTop[0][9], leftWingTop[0][10], leftWingTop[0][11]},
                new int[]{leftWingTop[1][6], leftWingTop[1][7], leftWingTop[1][8], leftWingTop[1][9], leftWingTop[1][10], leftWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop3 = transform3D(top3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        // Bottom
        int[][] bottom3 = new int[][]{
                new int[]{leftWingBottom[0][6], leftWingBottom[0][7], leftWingBottom[0][8], leftWingBottom[0][9], leftWingBottom[0][10], leftWingBottom[0][11]},
                new int[]{leftWingBottom[1][6], leftWingBottom[1][7], leftWingBottom[1][8], leftWingBottom[1][9], leftWingBottom[1][10], leftWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom3 = transform3D(bottom3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        // --------------- Drawing ---------------
        drawSurface(scaledTop3, director, projection, -1, false, null, color, buffer);
        drawSurface(scaledBottom3, director, projection, 1, false, null, color, buffer);
    }

    public void drawRightWingTopBack(int[] director, String projection, Color color, BufferedImage buffer) {
        int zBottom = zc - thickness;

        // Top
        int[][] top3 = new int[][]{
                new int[]{rightWingTop[0][6], rightWingTop[0][7], rightWingTop[0][8], rightWingTop[0][9], rightWingTop[0][10], rightWingTop[0][11]},
                new int[]{rightWingTop[1][6], rightWingTop[1][7], rightWingTop[1][8], rightWingTop[1][9], rightWingTop[1][10], rightWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop3 = transform3D(top3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        // Bottom
        int[][] bottom3 = new int[][]{
                new int[]{rightWingBottom[0][6], rightWingBottom[0][7], rightWingBottom[0][8], rightWingBottom[0][9], rightWingBottom[0][10], rightWingBottom[0][11]},
                new int[]{rightWingBottom[1][6], rightWingBottom[1][7], rightWingBottom[1][8], rightWingBottom[1][9], rightWingBottom[1][10], rightWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom3 = transform3D(bottom3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        // --------------- Drawing ---------------
         drawSurface(scaledTop3, director, projection, 1, false, Color.black, color, buffer);
         drawSurface(scaledBottom3, director, projection, -1, false, Color.black, color, buffer);
    }

    // Bottom
    public void drawLeftWingBottom(int[] director, String projection, Color color, BufferedImage buffer) {

        int[][] top1 = new int[][]{
                new int[]{leftWingTop[0][0], leftWingTop[0][1], leftWingTop[0][2], leftWingTop[0][3], leftWingTop[0][4], xc + 50,},
                new int[]{leftWingTop[1][0], leftWingTop[1][1], leftWingTop[1][2], leftWingTop[1][3], leftWingTop[1][4], yc - 30},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop1 = transform3D(top1, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][] top2 = new int[][]{
                new int[]{leftWingTop[0][5], leftWingTop[0][6], leftWingTop[0][11], leftWingTop[0][12], leftWingTop[0][13], xc + 50,},
                new int[]{leftWingTop[1][5], leftWingTop[1][6], leftWingTop[1][11], leftWingTop[1][12], leftWingTop[1][13], yc - 30,},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop2 = transform3D(top2, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][] top3 = new int[][]{
                new int[]{leftWingTop[0][6], leftWingTop[0][7], leftWingTop[0][8], leftWingTop[0][9], leftWingTop[0][10], leftWingTop[0][11]},
                new int[]{leftWingTop[1][6], leftWingTop[1][7], leftWingTop[1][8], leftWingTop[1][9], leftWingTop[1][10], leftWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop3 = transform3D(top3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        // Bottom

        int[][] bottom1 = new int[][]{
                new int[]{leftWingBottom[0][0], leftWingBottom[0][1], leftWingBottom[0][2], leftWingBottom[0][3], leftWingBottom[0][4], xc + 50,},
                new int[]{leftWingBottom[1][0], leftWingBottom[1][1], leftWingBottom[1][2], leftWingBottom[1][3], leftWingBottom[1][4], yc - 30},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom1 = transform3D(bottom1, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][] bottom2 = new int[][]{
                new int[]{leftWingBottom[0][5], leftWingBottom[0][6], leftWingBottom[0][11], leftWingBottom[0][12], leftWingBottom[0][13], xc + 50,},
                new int[]{leftWingBottom[1][5], leftWingBottom[1][6], leftWingBottom[1][11], leftWingBottom[1][12], leftWingBottom[1][13], yc - 30,},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom2 = transform3D(bottom2, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][] bottom3 = new int[][]{
                new int[]{leftWingBottom[0][6], leftWingBottom[0][7], leftWingBottom[0][8], leftWingBottom[0][9], leftWingBottom[0][10], leftWingBottom[0][11]},
                new int[]{leftWingBottom[1][6], leftWingBottom[1][7], leftWingBottom[1][8], leftWingBottom[1][9], leftWingBottom[1][10], leftWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom3 = transform3D(bottom3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        int[][][] leftFaces = new int[][][]{scaledLeftWingTop, scaledLeftWingBottom};
        //drawPolyhedronFaces(leftFaces, null, -1, director, projection, Color.white, Color.red, buffer);

        // --------------- Drawing ---------------
        drawWingSides(scaledLeftWingTop, scaledLeftWingBottom, 1, director, projection, buffer);

        drawSurface(scaledTop1, director, projection, -1, false, null, color, buffer);
        drawSurface(scaledTop2, director, projection, -1, false, null, color, buffer);
        //drawSurface(scaledTop3, director, projection, -1, false, null, color, buffer);

        drawSurface(scaledBottom1, director, projection, 1, false, null, color, buffer);
        drawSurface(scaledBottom2, director, projection, 1, false, null, color, buffer);
        //drawSurface(scaledBottom3, director, projection, 1, false, null, color, buffer);

    }

    public void drawRightWingBottom(int[] director, String projection, Color color, BufferedImage buffer) {
        int zBottom = zc - thickness;

        // Top

        int[][] top1 = new int[][]{
                new int[]{rightWingTop[0][0], rightWingTop[0][1], rightWingTop[0][2], rightWingTop[0][3], rightWingTop[0][4], xc + 50,},
                new int[]{rightWingTop[1][0], rightWingTop[1][1], rightWingTop[1][2], rightWingTop[1][3], rightWingTop[1][4], yc + 30},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop1 = transform3D(top1, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][] top2 = new int[][]{
                new int[]{rightWingTop[0][5], rightWingTop[0][6], rightWingTop[0][11], rightWingTop[0][12], rightWingTop[0][13], xc + 50,},
                new int[]{rightWingTop[1][5], rightWingTop[1][6], rightWingTop[1][11], rightWingTop[1][12], rightWingTop[1][13], yc + 30,},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop2 = transform3D(top2, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][] top3 = new int[][]{
                new int[]{rightWingTop[0][6], rightWingTop[0][7], rightWingTop[0][8], rightWingTop[0][9], rightWingTop[0][10], rightWingTop[0][11]},
                new int[]{rightWingTop[1][6], rightWingTop[1][7], rightWingTop[1][8], rightWingTop[1][9], rightWingTop[1][10], rightWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop3 = transform3D(top3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        // Bottom
        int[][] bottom1 = new int[][]{
                new int[]{rightWingBottom[0][0], rightWingBottom[0][1], rightWingBottom[0][2], rightWingBottom[0][3], rightWingBottom[0][4], xc + 50,},
                new int[]{rightWingBottom[1][0], rightWingBottom[1][1], rightWingBottom[1][2], rightWingBottom[1][3], rightWingBottom[1][4], yc + 30},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom1 = transform3D(bottom1, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][] bottom2 = new int[][]{
                new int[]{rightWingBottom[0][5], rightWingBottom[0][6], rightWingBottom[0][11], rightWingBottom[0][12], rightWingBottom[0][13], xc + 50,},
                new int[]{rightWingBottom[1][5], rightWingBottom[1][6], rightWingBottom[1][11], rightWingBottom[1][12], rightWingBottom[1][13], yc + 30,},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom2 = transform3D(bottom2, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][] bottom3 = new int[][]{
                new int[]{rightWingBottom[0][6], rightWingBottom[0][7], rightWingBottom[0][8], rightWingBottom[0][9], rightWingBottom[0][10], rightWingBottom[0][11]},
                new int[]{rightWingBottom[1][6], rightWingBottom[1][7], rightWingBottom[1][8], rightWingBottom[1][9], rightWingBottom[1][10], rightWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom3 = transform3D(bottom3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        int[][][] leftFaces = new int[][][]{scaledRightWingTop, scaledRightWingBottom};
        //drawPolyhedronFaces(leftFaces, null, -1, director, projection, Color.white, Color.red, buffer);

        // --------------- Drawing ---------------
        drawWingSides(scaledRightWingTop, scaledRightWingBottom, -1, director, projection, buffer);

        drawSurface(scaledTop1, director, projection, 1, false, Color.black, color, buffer);
        drawSurface(scaledTop2, director, projection, 1, false, Color.black, color, buffer);
        // drawSurface(scaledTop3, director, projection, 1, false, Color.black, color, buffer);

        drawSurface(scaledBottom1, director, projection, -1, false, Color.black, color, buffer);
        drawSurface(scaledBottom2, director, projection, -1, false, Color.black, color, buffer);
        // drawSurface(scaledBottom3, director, projection, -1, false, Color.black, color, buffer);

    }


    public void drawLeftWingBottomBack(int[] director, String projection, Color color, BufferedImage buffer) {
        int[][] top3 = new int[][]{
                new int[]{leftWingTop[0][6], leftWingTop[0][7], leftWingTop[0][8], leftWingTop[0][9], leftWingTop[0][10], leftWingTop[0][11]},
                new int[]{leftWingTop[1][6], leftWingTop[1][7], leftWingTop[1][8], leftWingTop[1][9], leftWingTop[1][10], leftWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop3 = transform3D(top3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        // Bottom
        int[][] bottom3 = new int[][]{
                new int[]{leftWingBottom[0][6], leftWingBottom[0][7], leftWingBottom[0][8], leftWingBottom[0][9], leftWingBottom[0][10], leftWingBottom[0][11]},
                new int[]{leftWingBottom[1][6], leftWingBottom[1][7], leftWingBottom[1][8], leftWingBottom[1][9], leftWingBottom[1][10], leftWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom3 = transform3D(bottom3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        // --------------- Drawing ---------------
        drawSurface(scaledTop3, director, projection, -1, false, null, color, buffer);
        drawSurface(scaledBottom3, director, projection, 1, false, null, color, buffer);
    }

    public void drawRightWingBottomBack(int[] director, String projection, Color color, BufferedImage buffer) {
        int zBottom = zc - thickness;

        // Top
        int[][] top3 = new int[][]{
                new int[]{rightWingTop[0][6], rightWingTop[0][7], rightWingTop[0][8], rightWingTop[0][9], rightWingTop[0][10], rightWingTop[0][11]},
                new int[]{rightWingTop[1][6], rightWingTop[1][7], rightWingTop[1][8], rightWingTop[1][9], rightWingTop[1][10], rightWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        int[][] scaledTop3 = transform3D(top3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        // Bottom
        int[][] bottom3 = new int[][]{
                new int[]{rightWingBottom[0][6], rightWingBottom[0][7], rightWingBottom[0][8], rightWingBottom[0][9], rightWingBottom[0][10], rightWingBottom[0][11]},
                new int[]{rightWingBottom[1][6], rightWingBottom[1][7], rightWingBottom[1][8], rightWingBottom[1][9], rightWingBottom[1][10], rightWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledBottom3 = transform3D(bottom3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        // --------------- Drawing ---------------
        drawSurface(scaledTop3, director, projection, 1, false, Color.black, color, buffer);
        drawSurface(scaledBottom3, director, projection, -1, false, Color.black, color, buffer);
    }



    public void drawWingSides(int[][] top, int[][] bottom, int direction, int[] director, String projection, BufferedImage buffer) {
        ArrayList<Surface> surfaces = new ArrayList<>();
        Color sideColor = Color.black;

        for (int i = 0; i < 9; i++) {
            int[][] side = new int[3][4];
            int next = i + 1;

            for (int j = 0; j < 3; j++) {
                side[j] = new int[]{top[j][i], top[j][next], bottom[j][next], bottom[j][i]};
            }

            surfaces.add(new Surface(side, direction, Color.gray, sideColor));
        }

        int[][] side = new int[3][4];
        for (int j = 0; j < 3; j++) {
            side[j] = new int[]{top[j][top[0].length - 1], top[j][0], bottom[j][0], bottom[j][top[0].length - 1]};
        }
        surfaces.add(new Surface(side, direction, Color.gray, sideColor));

        drawSortedSurfaces(surfaces, director, projection, buffer);
    }

    public void drawCabinFront(int[] director, String projection, boolean fill, BufferedImage buffer) {
        int numSides = 16;
        double angleStep = 2 * Math.PI / numSides; // Paso del ángulo en radianes

        int[] z0Points = new int[numSides];
        int[] y0Points = new int[numSides];

        int[] z1Points = new int[numSides];
        int[] y1Points = new int[numSides];

        int[] z2Points = new int[numSides];
        int[] y2Points = new int[numSides];

        int[] z3Points = new int[numSides];
        int[] y3Points = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            double angle = i * angleStep + angleStep / 2;

            z0Points[i] = (int) (zc + 25 * Math.cos(angle)) + 5;
            y0Points[i] = (int) (yc + 25 * Math.sin(angle));

            z1Points[i] = (int) (zc + 30 * Math.cos(angle)) + 5;
            y1Points[i] = (int) (yc + 30 * Math.sin(angle));

            z2Points[i] = (int) (zc + 45 * Math.cos(angle)) + 7;
            y2Points[i] = (int) (yc + 45 * Math.sin(angle));

            z3Points[i] = (int) (zc + 50 * Math.cos(angle)) + 7;
            y3Points[i] = (int) (yc + 50 * Math.sin(angle));
        }

        int[] x0Points = new int[numSides];
        int[] x1Points = new int[numSides];
        int[] x2Points = new int[numSides];
        int[] x3Points = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            x0Points[i] = xc;
            x1Points[i] = xc - 10;
            x2Points[i] = xc - 70;
            x3Points[i] = xc - 100;
        }

        int[][] face0 = new int[][]{x0Points, y0Points, z0Points};
        int[][] face1 = new int[][]{x1Points, y1Points, z1Points};
        int[][] face2 = new int[][]{x2Points, y2Points, z2Points};
        int[][] face3 = new int[][]{x3Points, y3Points, z3Points};


        int[][] transformedFace0 = transform3D(face0, center, scale, angles, null, null);
        int[][] transformedFace1 = transform3D(face1, center, scale, angles, null, null);
        int[][] transformedFace2 = transform3D(face2, center, scale, angles, null, null);
        int[][] transformedFace3 = transform3D(face3, center, scale, angles, null, null);


       /* drawSurface(transformedFace0, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace1, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace2, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace3, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace4, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace5, director, projection, -1, false, Color.red, null, buffer);
*/
        int[][][] levels = new int[][][]{transformedFace0, transformedFace1, transformedFace2, transformedFace3, };
        drawPolyhedronFaces(levels, new int[]{1}, 1, director, projection, Color.white, Color.blue, buffer);


    }

    public void drawCabinBack(int[] director, String projection, boolean fill, BufferedImage buffer) {
        int numSides = 16;
        double angleStep = 2 * Math.PI / numSides; // Paso del ángulo en radianes

        int[] z3Points = new int[numSides];
        int[] y3Points = new int[numSides];

        int[] z4Points = new int[numSides];
        int[] y4Points = new int[numSides];

        int[] z5Points = new int[numSides];
        int[] y5Points = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            double angle = i * angleStep + angleStep / 2;

            z3Points[i] = (int) (zc + 50 * Math.cos(angle)) + 7;
            y3Points[i] = (int) (yc + 50 * Math.sin(angle));

            z4Points[i] = (int) (zc + 50 * Math.cos(angle)) + 10;
            y4Points[i] = (int) (yc + 50 * Math.sin(angle));

            z5Points[i] = (int) (zc + 40 * Math.cos(angle)) + 10;
            y5Points[i] = (int) (yc + 40 * Math.sin(angle));

        }

        int[] x3Points = new int[numSides];
        int[] x4Points = new int[numSides];
        int[] x5Points = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            x3Points[i] = xc - 100;
            x4Points[i] = xc - 150;
            x5Points[i] = xc - 200;
        }

        int[][] face3 = new int[][]{x3Points, y3Points, z3Points};
        int[][] face4 = new int[][]{x4Points, y4Points, z4Points};
        int[][] face5 = new int[][]{x5Points, y5Points, z5Points};

        int[][] transformedFace3 = transform3D(face3, center, scale, angles, null, null);
        int[][] transformedFace4 = transform3D(face4, center, scale, angles, null, null);
        int[][] transformedFace5 = transform3D(face5, center, scale, angles, null, null);

       /* drawSurface(transformedFace0, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace1, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace2, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace3, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace4, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace5, director, projection, -1, false, Color.red, null, buffer);
*/
        int[][][] levels = new int[][][]{transformedFace3, transformedFace4, transformedFace5};
        drawPolyhedronFaces(levels, null, 1, director, projection, Color.white, Color.blue, buffer);


    }

    public void drawArturito() {

    }


    private boolean[] calPov(int[] povCenter, int[] director, String projection) {
        int xc = povCenter[0];
        int yc = povCenter[1];
        int zc = povCenter[2];

        int[][] xyPlane = new int[][]{
                new int[]{xc - 100, xc - 100, xc + 100, xc + 100},
                new int[]{yc + 100, yc - 100, yc - 100, yc + 100},
                new int[]{zc, zc, zc, zc},
        };
        int[][] rotatedXYPlane = rotateRespectObject(xyPlane, center, angles);
        //drawSurface(rotatedXYPlane, director, projection, 1, false, Color.red, null, buffer);

        int[][] xzPlane = new int[][]{
                new int[]{xc - 100, xc - 100, xc + 100, xc + 100},
                new int[]{yc, yc, yc, yc},
                new int[]{zc + 100, zc - 100, zc - 100, zc + 100},
        };
        int[][] rotatedXZPlane = rotateRespectObject(xzPlane, center, angles);
        //drawSurface(rotatedXZPlane, director, projection, 1, false, Color.green, null, buffer);

        int[][] yzPlane = new int[][]{
                new int[]{xc, xc, xc, xc},
                new int[]{yc + 100, yc + 100, yc - 100, yc - 100},
                new int[]{zc + 100, zc - 100, zc - 100, zc + 100},
        };
        int[][] rotatedYZPlane = rotateRespectObject(yzPlane, center, angles);

        boolean povUp = backFaceCulling(rotatedXYPlane, null, director, projection, 1);
        boolean povLeft = backFaceCulling(rotatedXZPlane, null, director, projection, 1);
        boolean povFront = backFaceCulling(rotatedYZPlane, null, director, projection, 1);

        return new boolean[]{povUp, !povLeft, !povFront};
    }

}
