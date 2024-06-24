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

    public int[][] ends;
    public int[][] ends2;

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
    int cabinSides;


    int[][] leftWingTop;
    int[][] scaledLeftWingTop;
    int[][] leftWingBottom;
    int[][] scaledLeftWingBottom;

    int[][] rightWingTop;
    int[][] scaledRightWingTop;
    int[][] rightWingBottom;
    int[][] scaledRightWingBottom;

    int[][] rotatedXYPlane;
    int[][] rotatedXZPlane;
    int[][] rotatedYZPlane;

    int[] director;
    String projection;
    BufferedImage buffer;

    // Top
    int[][] leftWingTop1;
    int[][] scaledLeftWingTop1;

    int[][] leftWingTop2;
    int[][] scaledLeftWingTop2;

    int[][] leftWingTop3;
    int[][] scaledLeftWingTop3;

    int[][] rightWingTop1;
    int[][] scaledRightWingTop1;

    int[][] rightWingTop2;
    int[][] scaledRightWingTop2;

    int[][] rightWingTop3;
    int[][] scaledRightWingTop3;

    // Bottom
    int[][] leftWingBottom1;
    int[][] scaledLeftWingBottom1;

    int[][] leftWingBottom2;
    int[][] scaledLeftWingBottom2;

    int[][] leftWingBottom3;
    int[][] scaledLeftWingBottom3;

    int[][] rightWingBottom1;
    int[][] scaledRightWingBottom1;

    int[][] rightWingBottom2;
    int[][] scaledRightWingBottom2;

    int[][] rightWingBottom3;
    int[][] scaledRightWingBottom3;

    ArrayList<Surface> leftWingSides;
    ArrayList<Surface> rightWingSides;

    int[][][] cabinFrontLevels;
    int[][][] cabinBackLevels;

    int[][] cabinBackFace;
    int[][] cabinBackUp;

    int[][][] cabinFront;

    Color shipColor;

    public int propulsor;


    public JediShip(int xc, int yc, int zc, int[] director, String projection, Color shipColor, BufferedImage buffer) {
        this.xc = xc;
        this.yc = yc;
        this.zc = zc;
        this.center = new int[]{xc, yc, zc};

        this.x0 = xc + 100;
        this.y0 = yc + 100;
        this.z0 = zc + 100;

        this.ends = new int[][]{
                new int[]{xc + 500, yc, zc},
                new int[]{xc, yc + 500, zc},
                new int[]{xc, yc, zc + 500},
        };

        this.ends2 = new int[][]{
                new int[]{xc + 300, yc, zc},
                new int[]{xc, yc + 300, zc},
                new int[]{xc, yc, zc + 300},
        };

        this.angles = new double[]{0, 0, 0};
        this.scale = 1;
        this.ang = 0;

        this.xInclination = 0.25;
        this.thickness = 12;
        this.zBottom = zc - thickness;

        this.leftAxis = new int[][]{new int[]{xc + 240, yc - 30, zc}, new int[]{xc - 10, yc - 30, zc},};
        this.rightAxis = new int[][]{new int[]{xc + 240, yc + 30, zc}, new int[]{xc - 10, yc + 30, zc},};

        this.cabinSides = 32;

        this.director = director;
        this.projection = projection;
        this.buffer = buffer;

        this.shipColor = shipColor;

        this.propulsor = 5;

        update();
    }

    public synchronized void update() {
        calWings();
        calWingsSides();
        calCabin();

        boolean[] povCenter = calPovCenter(center, director, projection);
        povUp = povCenter[0];
        povLeft = povCenter[1];
        povFront = povCenter[2];


    }

    // ------------------------------ Calculations ------------------------------
    public synchronized void rotate(double[] angles) {
        int[][] cosa = new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{1, 2, 3, 4},
                new int[]{1, 2, 3, 4}
        };

        this.ends2 = rotateAxis(cosa, center, ends2, angles)[1];

        double[] v1 = new double[]{(double) ends2[0][0] - xc, (double) ends2[0][1] - yc, (double) ends2[0][2] - zc};
        double[] v2 = new double[]{(double) ends2[1][0] - xc, (double) ends2[1][1] - yc, (double) ends2[1][2] - zc};
        double[] v3 = new double[]{(double) ends2[2][0] - xc, (double) ends2[2][1] - yc, (double) ends2[2][2] - zc};

        double[] eulerAngles = getEulerAngles(v1, v2, v3);

        this.angles = eulerAngles;

        update();
    }

    public synchronized void moveOnSpace(int dx, int dy, int dz) {
        for(int i = 0; i < 3; i++) {
            ends[i][0] += dx;
            ends[i][1] += dy;
            ends[i][2] += dz;

            ends2[i][0] += dx;
            ends2[i][1] += dy;
            ends2[i][2] += dz;
        }

        this.xc = this.xc + dx;
        this.yc = this.yc + dy;
        this.zc = this.zc + dz;

        this.center[0] = xc;
        this.center[1] = yc;
        this.center[2] = zc;

        update();
    }

    public synchronized void setPos(int xc, int yc, int zc) {
        int dx = xc - this.xc ;
        int dy = yc - this.yc ;
        int dz = zc - this.zc ;

        moveOnSpace(dx, dy, dz);
    }

    public synchronized void move(int dx, int dy, int dz) {
        double[] xDir = normalize(new double[]{ends2[0][0] - xc, ends2[0][1] - yc, ends2[0][2] - zc});
        double[] yDir = normalize(new double[]{ends2[1][0] - xc, ends2[1][1] - yc, ends2[1][2] - zc});
        double[] zDir = normalize(new double[]{ends2[2][0] - xc, ends2[2][1] - yc, ends2[2][2] - zc});

        double[] xMove = new double[]{xDir[0] * (double) dx, xDir[1] * (double) dx, xDir[2] * (double) dx};
        double[] yMove = new double[]{yDir[0] * (double) dy, yDir[1] * (double) dy, yDir[2] * (double) dy};
        double[] zMove = new double[]{zDir[0] * (double) dz, zDir[1] * (double) dz, zDir[2] * (double) dz};

        int[] moveRes = new int[]{
                (int) (xMove[0] + yMove[0] + zMove[0]),
                (int) (xMove[1] + yMove[1] + zMove[1]),
                (int) (xMove[2] + yMove[2] + zMove[2]),
        };

        moveOnSpace(moveRes[0], moveRes[1], moveRes[2]);

    }


    // ------------------------------ Calculations ------------------------------
    private boolean[] calPovCenter(int[] povCenter, int[] director, String projection) {
        int xc = povCenter[0];
        int yc = povCenter[1];
        int zc = povCenter[2];

        int[][] xyPlane = new int[][]{
                new int[]{xc - 100, xc - 100, xc + 100, xc + 100},
                new int[]{yc + 100, yc - 100, yc - 100, yc + 100},
                new int[]{zc, zc, zc, zc},
        };
        rotatedXYPlane = rotateRespectEuler(xyPlane, center, angles);

        int[][] xzPlane = new int[][]{
                new int[]{xc - 100, xc - 100, xc + 100, xc + 100},
                new int[]{yc, yc, yc, yc},
                new int[]{zc + 100, zc - 100, zc - 100, zc + 100},
        };
        rotatedXZPlane = rotateRespectEuler(xzPlane, center, angles);

        int[][] yzPlane = new int[][]{
                new int[]{xc, xc, xc, xc},
                new int[]{yc + 100, yc + 100, yc - 100, yc - 100},
                new int[]{zc + 100, zc - 100, zc - 100, zc + 100},
        };
        rotatedYZPlane = rotateRespectEuler(yzPlane, center, angles);

        boolean povUp = backFaceCulling(rotatedXYPlane, null, director, projection, 1);
        boolean povLeft = backFaceCulling(rotatedXZPlane, null, director, projection, 1);
        boolean povFront = backFaceCulling(rotatedYZPlane, null, director, projection, 1);

        return new boolean[]{povUp, povLeft, povFront};
    }

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

        // Top
        leftWingTop1 = new int[][]{
                new int[]{leftWingTop[0][0], leftWingTop[0][1], leftWingTop[0][2], leftWingTop[0][3], leftWingTop[0][4], xc + 50,},
                new int[]{leftWingTop[1][0], leftWingTop[1][1], leftWingTop[1][2], leftWingTop[1][3], leftWingTop[1][4], yc - 30},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        scaledLeftWingTop1 = transform3D(leftWingTop1, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        leftWingTop2 = new int[][]{
                new int[]{leftWingTop[0][5], leftWingTop[0][6], leftWingTop[0][11], leftWingTop[0][12], leftWingTop[0][13], xc + 50,},
                new int[]{leftWingTop[1][5], leftWingTop[1][6], leftWingTop[1][11], leftWingTop[1][12], leftWingTop[1][13], yc - 30,},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        scaledLeftWingTop2 = transform3D(leftWingTop2, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        leftWingTop3 = new int[][]{
                new int[]{leftWingTop[0][6], leftWingTop[0][7], leftWingTop[0][8], leftWingTop[0][9], leftWingTop[0][10], leftWingTop[0][11]},
                new int[]{leftWingTop[1][6], leftWingTop[1][7], leftWingTop[1][8], leftWingTop[1][9], leftWingTop[1][10], leftWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        scaledLeftWingTop3 = transform3D(leftWingTop3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        rightWingTop1 = new int[][]{
                new int[]{rightWingTop[0][0], rightWingTop[0][1], rightWingTop[0][2], rightWingTop[0][3], rightWingTop[0][4], xc + 50,},
                new int[]{rightWingTop[1][0], rightWingTop[1][1], rightWingTop[1][2], rightWingTop[1][3], rightWingTop[1][4], yc + 30},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        scaledRightWingTop1 = transform3D(rightWingTop1, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        rightWingTop2 = new int[][]{
                new int[]{rightWingTop[0][5], rightWingTop[0][6], rightWingTop[0][11], rightWingTop[0][12], rightWingTop[0][13], xc + 50,},
                new int[]{rightWingTop[1][5], rightWingTop[1][6], rightWingTop[1][11], rightWingTop[1][12], rightWingTop[1][13], yc + 30,},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        scaledRightWingTop2 = transform3D(rightWingTop2, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        rightWingTop3 = new int[][]{
                new int[]{rightWingTop[0][6], rightWingTop[0][7], rightWingTop[0][8], rightWingTop[0][9], rightWingTop[0][10], rightWingTop[0][11]},
                new int[]{rightWingTop[1][6], rightWingTop[1][7], rightWingTop[1][8], rightWingTop[1][9], rightWingTop[1][10], rightWingTop[1][11]},
                new int[]{zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc, zc,}
        };
        scaledRightWingTop3 = transform3D(rightWingTop3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        // Bottom
        leftWingBottom1 = new int[][]{
                new int[]{leftWingBottom[0][0], leftWingBottom[0][1], leftWingBottom[0][2], leftWingBottom[0][3], leftWingBottom[0][4], xc + 50,},
                new int[]{leftWingBottom[1][0], leftWingBottom[1][1], leftWingBottom[1][2], leftWingBottom[1][3], leftWingBottom[1][4], yc - 30},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        scaledLeftWingBottom1 = transform3D(leftWingBottom1, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        leftWingBottom2 = new int[][]{
                new int[]{leftWingBottom[0][5], leftWingBottom[0][6], leftWingBottom[0][11], leftWingBottom[0][12], leftWingBottom[0][13], xc + 50,},
                new int[]{leftWingBottom[1][5], leftWingBottom[1][6], leftWingBottom[1][11], leftWingBottom[1][12], leftWingBottom[1][13], yc - 30,},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        scaledLeftWingBottom2 = transform3D(leftWingBottom2, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        leftWingBottom3 = new int[][]{
                new int[]{leftWingBottom[0][6], leftWingBottom[0][7], leftWingBottom[0][8], leftWingBottom[0][9], leftWingBottom[0][10], leftWingBottom[0][11]},
                new int[]{leftWingBottom[1][6], leftWingBottom[1][7], leftWingBottom[1][8], leftWingBottom[1][9], leftWingBottom[1][10], leftWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        scaledLeftWingBottom3 = transform3D(leftWingBottom3, center, scale, angles, new double[]{-xInclination}, new int[][][]{leftAxis});

        rightWingBottom1 = new int[][]{
                new int[]{rightWingBottom[0][0], rightWingBottom[0][1], rightWingBottom[0][2], rightWingBottom[0][3], rightWingBottom[0][4], xc + 50,},
                new int[]{rightWingBottom[1][0], rightWingBottom[1][1], rightWingBottom[1][2], rightWingBottom[1][3], rightWingBottom[1][4], yc + 30},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        scaledRightWingBottom1 = transform3D(rightWingBottom1, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        rightWingBottom2 = new int[][]{
                new int[]{rightWingBottom[0][5], rightWingBottom[0][6], rightWingBottom[0][11], rightWingBottom[0][12], rightWingBottom[0][13], xc + 50,},
                new int[]{rightWingBottom[1][5], rightWingBottom[1][6], rightWingBottom[1][11], rightWingBottom[1][12], rightWingBottom[1][13], yc + 30,},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        scaledRightWingBottom2 = transform3D(rightWingBottom2, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});

        rightWingBottom3 = new int[][]{
                new int[]{rightWingBottom[0][6], rightWingBottom[0][7], rightWingBottom[0][8], rightWingBottom[0][9], rightWingBottom[0][10], rightWingBottom[0][11]},
                new int[]{rightWingBottom[1][6], rightWingBottom[1][7], rightWingBottom[1][8], rightWingBottom[1][9], rightWingBottom[1][10], rightWingBottom[1][11]},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        scaledRightWingBottom3 = transform3D(rightWingBottom3, center, scale, angles, new double[]{xInclination}, new int[][][]{rightAxis});
    }

    public void calWingSides(ArrayList<Surface> surfaces, int[][] top, int[][] bottom, int direction, Color sideColor) {
        int r = 120;
        for (int i = 0; i < 9; i++) {
            int[][] side = new int[3][4];
            int next = i + 1;

            for (int j = 0; j < 3; j++) {
                side[j] = new int[]{top[j][i], top[j][next], bottom[j][next], bottom[j][i]};
            }

            surfaces.add(new Surface(side, direction, Color.gray, new Color(r - i, r - i, r - i)));
        }

        int[][] side = new int[3][4];
        for (int j = 0; j < 3; j++) {
            side[j] = new int[]{top[j][top[0].length - 1], top[j][0], bottom[j][0], bottom[j][top[0].length - 1]};
        }
        surfaces.add(new Surface(side, direction, Color.gray, new Color(121, 121, 121)));
    }

    public void calWingsSides() {
        leftWingSides = new ArrayList<Surface>();
        rightWingSides = new ArrayList<Surface>();

        calWingSides(leftWingSides, scaledLeftWingTop, scaledLeftWingBottom, 1, new Color(120, 120, 120));
        calWingSides(rightWingSides, scaledRightWingTop, scaledRightWingBottom, -1, new Color(120, 121, 120));
    }

    public void calCabin() {
        int numSides = cabinSides;
        double angleStep = 2 * Math.PI / numSides; // Paso del ángulo en radianes

        int[] z0Points = new int[numSides];
        int[] y0Points = new int[numSides];

        int[] z1Points = new int[numSides];
        int[] y1Points = new int[numSides];

        int[] z2Points = new int[numSides];
        int[] y2Points = new int[numSides];

        int[] z3Points = new int[numSides];
        int[] y3Points = new int[numSides];

        int[] z4Points = new int[numSides];
        int[] y4Points = new int[numSides];

        int[] z4BackPoints = new int[numSides];
        int[] y4BackPoints = new int[numSides];

        int[] z5Points = new int[numSides];
        int[] y5Points = new int[numSides];

        int[] zBack1Points = new int[16];
        int[] yBack1Points = new int[16];
        int[] xBack1Points = new int[16];

        int[] xBackPoints = new int[14];
        int[] zBackPoints = new int[14];
        int[] yBackPoints = new int[14];
        //7, 8, 9, 10, 11, 23, 24, 25, 26, 27

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

            z4Points[i] = (int) (zc + 50 * Math.cos(angle)) + 10;
            y4Points[i] = (int) (yc + 50 * Math.sin(angle));
        }

        for(int i = 0; i < 10; i++) {
            double angle = i * angleStep + angleStep / 2;

            z4BackPoints[i] = (int) (zc + 50 * Math.cos(angle)) + 10;
            y4BackPoints[i] = (int) (yc + 50 * Math.sin(angle));

            z5Points[i] = (int) (zc + 40 * Math.cos(angle)) + 10;
            y5Points[i] = (int) (yc + 40 * Math.sin(angle));
        }

        for(int i = 10; i < 22; i++) {
            double angle = i * angleStep + angleStep / 2;

            z4BackPoints[i] = zc - 24;
            y4BackPoints[i] = (int) (yc + 50 * Math.sin(angle));


            z5Points[i] = zc - 24;
            y5Points[i] = (int) (yc + 40 * Math.sin(angle));


        }

        for(int i = 22; i < numSides; i++) {
            double angle = i * angleStep + angleStep / 2;

            z4BackPoints[i] = (int) (zc + 50 * Math.cos(angle)) + 10;
            y4BackPoints[i] = (int) (yc + 50 * Math.sin(angle));

            z5Points[i] = (int) (zc + 40 * Math.cos(angle)) + 10;
            y5Points[i] = (int) (yc + 40 * Math.sin(angle));
        }

        for(int i = 9; i < 23; i++) {
            double angle = i * angleStep + angleStep / 2;

            z4BackPoints[i] = zc - 24;
            y4BackPoints[i] = (int) (yc + 50 * Math.sin(angle));

            z5Points[i] = zc - 24;
            y5Points[i] = (int) (yc + 40 * Math.sin(angle));

            xBackPoints[i - 9] = xc - 100;
            zBackPoints[i - 9] = (int) (zc + 50 * Math.cos(angle)) + 7;
            yBackPoints[i - 9] = (int) (yc + 50 * Math.sin(angle));
        }


        // Back up

        for(int i = 0; i < 5; i++) {
            double angleStep2 = 2 * Math.PI / 16; // Paso del ángulo en radianes
            double angle = i * angleStep2 + angleStep2 / 2;

            zBack1Points[i] = (int) (zc + 40 * Math.cos(angle)) + 10;
            yBack1Points[i] = (int) (yc + 40 * Math.sin(angle));
            xBack1Points[i] = (int) xc - 200;
        }

        for(int i = 5; i < 11; i++) {
            double angleStep2 = 2 * Math.PI / 16; // Paso del ángulo en radianes
            double angle = i * angleStep2 + angleStep2 / 2;

            zBack1Points[i] = zc - 24;
            yBack1Points[i] = (int) (yc + 40 * Math.sin(angle));
            xBack1Points[i] = (int) xc - 200;
        }

        for(int i = 11; i < 16; i++) {
            double angleStep2 = 2 * Math.PI / 16; // Paso del ángulo en radianes
            double angle = i * angleStep2 + angleStep2 / 2;

            zBack1Points[i] = (int) (zc + 40 * Math.cos(angle)) + 10;
            yBack1Points[i] = (int) (yc + 40 * Math.sin(angle));
            xBack1Points[i] = (int) xc - 200;
        }

        // Front

        int[] zFrontPoints = new int[8];
        int[] yFrontPoints = new int[8];
        int[] xFrontPoints = new int[8];

        int[] z0FrontPoints = new int[8];
        int[] y0FrontPoints = new int[8];
        int[] x0FrontPoints = new int[8];

        for(int i = 0; i < 8; i++) {
            double angleStep2 = 2 * Math.PI / 8; // Paso del ángulo en radianes
            double angle = i * angleStep2 + angleStep2 / 2;

            z0FrontPoints[i] = (int) (zc + 25 * Math.cos(angle)) + 5;
            y0FrontPoints[i] = (int) (yc + 25 * Math.sin(angle));
            x0FrontPoints[i] = xc;

            zFrontPoints[i] = (int) (zc + 15 * Math.cos(angle)) + 5;
            yFrontPoints[i] = (int) (yc + 15 * Math.sin(angle));
            xFrontPoints[i] = xc + 5;
        }

        int[] x0Points = new int[numSides];
        int[] x1Points = new int[numSides];
        int[] x2Points = new int[numSides];
        int[] x3Points = new int[numSides];
        int[] x4Points = new int[numSides];
        int[] x5Points = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            x0Points[i] = xc;
            x1Points[i] = xc - 10;
            x2Points[i] = xc - 70;
            x3Points[i] = xc - 100;
            x4Points[i] = xc - 150;
            x5Points[i] = xc - 200;
        }

        int[][] face0 = new int[][]{x0Points, y0Points, z0Points};
        int[][] face1 = new int[][]{x1Points, y1Points, z1Points};
        int[][] face2 = new int[][]{x2Points, y2Points, z2Points};
        int[][] face3 = new int[][]{x3Points, y3Points, z3Points};
        int[][] face4 = new int[][]{x4Points, y4Points, z4Points};
        int[][] faceBack4 = new int[][]{x4Points, y4BackPoints, z4BackPoints};
        int[][] face5 = new int[][]{x5Points, y5Points, z5Points};

        int[][] backFace = new int[][]{xBackPoints, yBackPoints, zBackPoints};
        int[][] backUpFace = new int[][]{xBack1Points, yBack1Points, zBack1Points};

        int[][] frontFace0 = new int[][]{x0FrontPoints, y0FrontPoints, z0FrontPoints};
        int[][] frontFace = new int[][]{xFrontPoints, yFrontPoints, zFrontPoints};

        int[][] transformedFace0 = transform3D(face0, center, scale, angles, null, null);
        int[][] transformedFace1 = transform3D(face1, center, scale, angles, null, null);
        int[][] transformedFace2 = transform3D(face2, center, scale, angles, null, null);
        int[][] transformedFace3 = transform3D(face3, center, scale, angles, null, null);
        int[][] transformedFace4 = transform3D(face4, center, scale, angles, null, null);

        int[][] transformedFaceBack4 = transform3D(faceBack4, center, scale, angles, null, null);
        int[][] transformedFace5 = transform3D(face5, center, scale, angles, null, null);

        int[][] transformedFrontFace0 = transform3D(frontFace0, center, scale, angles, null, null);
        int[][] transformedFrontFace = transform3D(frontFace, center, scale, angles, null, null);

        cabinBackFace = transform3D(backFace, center, scale, angles, null, null);
        cabinBackUp = transform3D(backUpFace, center, scale, angles, null, null);

        cabinFrontLevels = new int[][][]{transformedFace0, transformedFace1, transformedFace2, transformedFace3, transformedFace4};
        cabinBackLevels = new int[][][]{transformedFaceBack4, transformedFace5};

        cabinFront = new int[][][]{transformedFrontFace0, transformedFrontFace};

    }


      // ------------------------------ Drawing ------------------------------
    public synchronized void draw(boolean perspective, int[] director, String projection, boolean develop, BufferedImage buffer, double ang) {
        this.ang = ang;

        int r = shipColor.getRed();
        int g = shipColor.getGreen();
        int b = shipColor.getBlue();

        /*Color leftTopColor = new Color(r, g, b);
        Color rightTopColor = new Color(r + 1, g, b);
        Color leftBottomColor = new Color(r, g + 1, b);
        Color rightBottomColor = new Color(r, g, b + 1);*/

        Color leftTopColor = shipColor;
        Color rightTopColor = shipColor;
        Color leftBottomColor = shipColor;
        Color rightBottomColor = shipColor;

        boolean[] povCenter = calPovCenter(center, director, projection);
        povUp = povCenter[0];
        povLeft = povCenter[1];
        povFront = povCenter[2];

        update();

        /*drawCabinFront(director, projection, true, buffer);
        drawCabinBack(director, projection, true, buffer);

        drawLeftWingTop(director, projection, leftColor, buffer);
        drawLeftWingTopBack(director, projection, leftColor, buffer);
        drawRightWingTop(director, projection, rightColor, buffer);
        drawRightWingTopBack(director, projection, rightColor, buffer);

        drawLeftWingBottom(director, projection, leftColor, buffer);
        drawLeftWingBottomBack(director, projection, leftColor, buffer);
        drawRightWingBottom(director, projection, rightColor, buffer);
        drawRightWingBottomBack(director, projection, rightColor, buffer);*/

        if (perspective) {
            if (povUp) {
                if (povFront) {
                    if (povLeft) {
                        drawSidesRight(director, projection, buffer);

                        drawRightWingBottomBack(director, projection, rightBottomColor, buffer);
                        drawRightWingBottom(director, projection, rightBottomColor, buffer);

                        drawRightWingTopBack(director, projection, rightTopColor, buffer);
                        drawRightWingTop(director, projection, rightTopColor, buffer);

                        drawCabinBack(director, projection, true, buffer);
                        drawCabinFront(director, projection, true, buffer);

                        drawSidesLeft(director, projection, buffer);

                        drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);
                        drawLeftWingBottom(director, projection, leftBottomColor, buffer);

                        drawLeftWingTopBack(director, projection, leftTopColor, buffer);
                        drawLeftWingTop(director, projection, leftTopColor, buffer);
                    }
                    else {
                        drawSidesLeft(director, projection, buffer);

                        drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);
                        drawLeftWingBottom(director, projection, leftBottomColor, buffer);

                        drawLeftWingTopBack(director, projection, leftTopColor, buffer);
                        drawLeftWingTop(director, projection, leftTopColor, buffer);

                        drawCabinBack(director, projection, true, buffer);
                        drawCabinFront(director, projection, true, buffer);

                        drawSidesRight(director, projection, buffer);

                        drawRightWingBottomBack(director, projection, rightBottomColor, buffer);
                        drawRightWingBottom(director, projection, rightBottomColor, buffer);

                        drawRightWingTopBack(director, projection, rightTopColor, buffer);
                        drawRightWingTop(director, projection, rightTopColor, buffer);
                    }
                } else {
                    if (povLeft) {
                        drawSidesRight(director, projection, buffer);

                        drawRightWingBottom(director, projection, rightBottomColor, buffer);
                        drawRightWingBottomBack(director, projection, rightBottomColor, buffer);

                        drawRightWingTop(director, projection, rightTopColor, buffer);
                        drawRightWingTopBack(director, projection, rightTopColor, buffer);

                        drawCabinFront(director, projection, true, buffer);
                        drawCabinBack(director, projection, true, buffer);

                        drawSidesLeft(director, projection, buffer);

                        drawLeftWingBottom(director, projection, leftBottomColor, buffer);
                        drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);

                        drawLeftWingTop(director, projection, leftTopColor, buffer);
                        drawLeftWingTopBack(director, projection, leftTopColor, buffer);
                    } else {
                        drawSidesLeft(director, projection, buffer);

                        drawLeftWingBottom(director, projection, leftBottomColor, buffer);
                        drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);

                        drawLeftWingTop(director, projection, leftTopColor, buffer);
                        drawLeftWingTopBack(director, projection, leftTopColor, buffer);

                        drawCabinFront(director, projection, true, buffer);
                        drawCabinBack(director, projection, true, buffer);

                        drawSidesRight(director, projection, buffer);

                        drawRightWingBottom(director, projection, rightBottomColor, buffer);
                        drawRightWingBottomBack(director, projection, rightBottomColor, buffer);

                        drawRightWingTop(director, projection, rightTopColor, buffer);
                        drawRightWingTopBack(director, projection, rightTopColor, buffer);
                    }

                }

            } else {
                if (povFront) {
                    if (povLeft) {
                        drawSidesRight(director, projection, buffer);

                        drawRightWingTopBack(director, projection, rightTopColor, buffer);
                        drawRightWingTop(director, projection, rightTopColor, buffer);

                        drawRightWingBottomBack(director, projection, rightBottomColor, buffer);
                        drawRightWingBottom(director, projection, rightBottomColor, buffer);

                        drawCabinBack(director, projection, true, buffer);
                        drawCabinFront(director, projection, true, buffer);

                        drawSidesLeft(director, projection, buffer);

                        drawLeftWingTopBack(director, projection, leftTopColor, buffer);
                        drawLeftWingTop(director, projection, leftTopColor, buffer);

                        drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);
                        drawLeftWingBottom(director, projection, leftBottomColor, buffer);
                    } else {
                        drawSidesLeft(director, projection, buffer);

                        drawLeftWingTopBack(director, projection, leftTopColor, buffer);
                        drawLeftWingTop(director, projection, leftTopColor, buffer);

                        drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);
                        drawLeftWingBottom(director, projection, leftBottomColor, buffer);

                        drawCabinBack(director, projection, true, buffer);
                        drawCabinFront(director, projection, true, buffer);

                        drawSidesRight(director, projection, buffer);

                        drawRightWingTopBack(director, projection, rightTopColor, buffer);
                        drawRightWingTop(director, projection, rightTopColor, buffer);

                        drawRightWingBottomBack(director, projection, rightBottomColor, buffer);
                        drawRightWingBottom(director, projection, rightBottomColor, buffer);
                    }
                } else {
                    if (povLeft) {
                        drawSidesRight(director, projection, buffer);

                        drawRightWingTop(director, projection, rightTopColor, buffer);
                        drawRightWingTopBack(director, projection, rightTopColor, buffer);

                        drawRightWingBottom(director, projection, rightBottomColor, buffer);
                        drawRightWingBottomBack(director, projection, rightBottomColor, buffer);

                        drawCabinFront(director, projection, true, buffer);
                        drawCabinBack(director, projection, true, buffer);

                        drawSidesLeft(director, projection, buffer);

                        drawLeftWingTop(director, projection, leftTopColor, buffer);
                        drawLeftWingTopBack(director, projection, leftTopColor, buffer);

                        drawLeftWingBottom(director, projection, leftBottomColor, buffer);
                        drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);
                    } else {
                        drawSidesLeft(director, projection, buffer);

                        drawLeftWingTop(director, projection, leftTopColor, buffer);
                        drawLeftWingTopBack(director, projection, leftTopColor, buffer);

                        drawLeftWingBottom(director, projection, leftBottomColor, buffer);
                        drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);

                        drawCabinFront(director, projection, true, buffer);
                        drawCabinBack(director, projection, true, buffer);

                        drawSidesRight(director, projection, buffer);

                        drawRightWingTop(director, projection, rightTopColor, buffer);
                        drawRightWingTopBack(director, projection, rightTopColor, buffer);

                        drawRightWingBottom(director, projection, rightBottomColor, buffer);
                        drawRightWingBottomBack(director, projection, rightBottomColor, buffer);
                    }
                }
            }
        } else {
            drawSidesRight(director, projection, buffer);

            drawRightWingBottomBack(director, projection, rightBottomColor, buffer);
            drawRightWingBottom(director, projection, rightBottomColor, buffer);

            drawRightWingTopBack(director, projection, rightTopColor, buffer);
            drawRightWingTop(director, projection, rightTopColor, buffer);

            drawCabinBack(director, projection, true, buffer);
            drawCabinFront(director, projection, true, buffer);

            drawSidesLeft(director, projection, buffer);

            drawLeftWingBottomBack(director, projection, leftBottomColor, buffer);
            drawLeftWingBottom(director, projection, leftBottomColor, buffer);

            drawLeftWingTopBack(director, projection, leftTopColor, buffer);
            drawLeftWingTop(director, projection, leftTopColor, buffer);
        }



        if(develop) {
            drawPlanes(director, projection, buffer);
            drawAxis(director, projection, true, buffer);
        }
    }

    // Top
    public void drawLeftWingTop(int[] director, String projection, Color color, BufferedImage buffer) {
        drawSurface(scaledLeftWingTop1, director, projection, -1, false, null, color, buffer);
        drawSurface(scaledLeftWingTop2, director, projection, -1, false, null, color, buffer);
        //drawSurface(scaledTop3, director, projection, -1, false, null, color, buffer);
    }

    public void drawRightWingTop(int[] director, String projection, Color color, BufferedImage buffer) {
        drawSurface(scaledRightWingTop1, director, projection, 1, false, null, color, buffer);
        drawSurface(scaledRightWingTop2, director, projection, 1, false, null, color, buffer);
        // drawSurface(scaledTop3, director, projection, 1, false, Color.black, color, buffer);
    }

    public void drawLeftWingTopBack(int[] director, String projection, Color color, BufferedImage buffer) {
        drawSurface(scaledLeftWingTop3, director, projection, -1, false, null, color, buffer);
    }

    public void drawRightWingTopBack(int[] director, String projection, Color color, BufferedImage buffer) {
        drawSurface(scaledRightWingTop3, director, projection, 1, false, null, color, buffer);
    }

    // Bottom
    public void drawLeftWingBottom(int[] director, String projection, Color color, BufferedImage buffer) {
        drawSurface(scaledLeftWingBottom1, director, projection, 1, false, null, color, buffer);
        drawSurface(scaledLeftWingBottom2, director, projection, 1, false, null, color, buffer);
        //drawSurface(scaledBottom3, director, projection, 1, false, null, color, buffer);
    }

    public void drawRightWingBottom(int[] director, String projection, Color color, BufferedImage buffer) {
        drawSurface(scaledRightWingBottom1, director, projection, -1, false, null, color, buffer);
        drawSurface(scaledRightWingBottom2, director, projection, -1, false, null, color, buffer);
        // drawSurface(scaledBottom3, director, projection, -1, false, Color.black, color, buffer);

    }

    public void drawLeftWingBottomBack(int[] director, String projection, Color color, BufferedImage buffer) {
        drawSurface(scaledLeftWingBottom3, director, projection, 1, false, null, color, buffer);
    }

    public void drawRightWingBottomBack(int[] director, String projection, Color color, BufferedImage buffer) {
        drawSurface(scaledRightWingBottom3, director, projection, -1, false, null, color, buffer);
    }

    // Sides
    public void drawSidesLeft(int[] director, String projection, BufferedImage buffer) {
        drawSortedSurfaces(leftWingSides, director, projection, buffer);
    }

    public void drawSidesRight(int[] director, String projection, BufferedImage buffer) {
        drawSortedSurfaces(rightWingSides, director, projection, buffer);
    }


    public void drawCabinFront(int[] director, String projection, boolean fill, BufferedImage buffer) {
        drawPolyhedronFaces(cabinFrontLevels, new int[]{1}, 1, director, projection, null, new Color(28, 47, 55), buffer);
        drawPolyhedronFaces(
                cabinFrontLevels,
                new int[]{
                        0, 1,
                        34, 35, 36, 38, 39, 40, 41, 57, 58, 59, 60, 62, 63, 64, 65,
                        66, 67, 68, 70, 71, 72, 73, 89, 90, 91, 92, 94, 95, 96, 97
                },
                1,
                director,
                projection,
                Color.gray,
                new Color(135, 135, 135),
                buffer
        );

        drawPolyhedronFaces(cabinFront, new int[]{0}, -1, director, projection, Color.gray, null, buffer);

    }

    public void drawCabinBack(int[] director, String projection, boolean fill, BufferedImage buffer) {
        if(povUp && povFront) {
            drawSurface(cabinBackFace, director, projection, 1, false, Color.gray, new Color(120, 120, 120), buffer);
            drawMotor(new int[]{xc - 150, yc + 15, zc - 28}, 9, 80, 8, director, projection, Color.black, buffer );
            drawMotor(new int[]{xc - 150, yc - 15, zc - 28}, 9, 80, 8, director, projection, new Color(0, 1, 0), buffer );
        }

        drawSurface(cabinBackUp, director, projection, 1, false, Color.gray, new Color(131, 131, 130), buffer);
        drawPolyhedronFaces(cabinBackLevels, new int[] {0, 1}, 1, director, projection, Color.gray, new Color(130, 130, 130), buffer);

        if(!povUp) {
            drawSurface(cabinBackFace, director, projection, 1, false, Color.gray, new Color(120, 120, 120), buffer);
            drawMotor(new int[]{xc - 150, yc + 15, zc - 28}, 9, 80, 8, director, projection, Color.black, buffer );
            drawMotor(new int[]{xc - 150, yc - 15, zc - 28}, 9, 80, 8, director, projection, new Color(0, 1, 0), buffer );

        }
    }

    public void drawArturito() {

    }

    private void drawMotor(int[] p0, double radius, int length, int numSides, int[] director, String projection, Color color, BufferedImage buffer) {
        int x0 = p0[0];

        int[] zPoints = new int[numSides];
        int[] yPoints = new int[numSides];
        double angleStep = 2 * Math.PI / numSides; // Paso del ángulo en radianes

        for (int i = 0; i < numSides; i++) {
            double angle = i * angleStep;
            zPoints[i] = (int) (p0[2] + radius * Math.cos(angle));
            yPoints[i] = (int) (p0[1] + radius * Math.sin(angle));
        }

        int[] zPoints2 = new int[numSides];
        int[] yPoints2 = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            double angle = i * angleStep;
            zPoints2[i] = (int) (p0[2] + (radius - 4) * Math.cos(angle));
            yPoints2[i] = (int) (p0[1] + (radius - 4) * Math.sin(angle));
        }

        int[] x0Points = new int[numSides];
        int[] xfPoints = new int[numSides];
        int[] xf2Points = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            x0Points[i] = x0;
            xfPoints[i] = x0 - length;
            xf2Points[i] = x0 - length - propulsor;
        }

        int[][] face1 = new int[][]{x0Points, yPoints, zPoints};
        int[][] face2 = new int[][]{xfPoints, yPoints, zPoints};
        int[][] face3 = new int[][]{xfPoints, yPoints2, zPoints2};
        int[][] face4 = new int[][]{xf2Points, yPoints2, zPoints2};

        int[][] transformedFace1 = transform3D(face1, center, scale, angles, null, null);
        int[][] transformedFace2 = transform3D(face2, center, scale, angles, null, null);
        int[][] transformedFace3 = transform3D(face3, center, scale, angles, null, null);
        int[][] transformedFace4 = transform3D(face4, center, scale, angles, null, null);

        int[][][] faces = new int[][][]{transformedFace1, transformedFace2};
        int[][][] faces2 = new int[][][]{transformedFace3, transformedFace4};

        drawPolyhedronFaces(faces, new int[]{0}, 1, director, projection, new Color(60, 60, 60), color, buffer);

        drawPolyhedronFaces(faces2, new int[]{0}, 1, director, projection, null, new Color(50, 128, 230), buffer);


        //drawSurface(transformedFace3, director, projection, 1, false, new Color(60, 60, 60), new Color(50, 128, 230), buffer);
        //Draw.drawPolygon(zPoints, yPoints, Color.green, buffer);
    }


    public void drawPlanes(int[] director, String projection, BufferedImage buffer) {
        drawSurface(rotatedXYPlane, director, projection, 0, false, Color.blue, null, buffer);
        drawSurface(rotatedXZPlane, director, projection, 0, false, Color.green, null, buffer);
        drawSurface(rotatedYZPlane, director, projection, 0, false, Color.red, null, buffer);

    }

    private void drawAxis(int[] director, String projection, boolean drawOriginal, BufferedImage buffer) {
        int[][] projectedCenter = projection(new int[][]{new int[]{xc, 1, 1, 1}, new int[]{yc, 1, 1, 1}, new int[]{zc, 1, 1, 1}}, director, projection);

        if(drawOriginal) {
            int[][] originalEnds = new int[][]{
                    new int[]{ends[0][0], ends[1][0], ends[2][0], 1},
                    new int[]{ends[0][1], ends[1][1], ends[2][1], 1},
                    new int[]{ends[0][2], ends[1][2], ends[2][2], 1},
            };

            int[][] projectedOriginalEnds = projection(originalEnds, director, projection);

            drawLine(projectedCenter[0][0], projectedCenter[1][0], projectedOriginalEnds[0][0], projectedOriginalEnds[1][0], Color.pink, buffer);
            drawLine(projectedCenter[0][0], projectedCenter[1][0], projectedOriginalEnds[0][1], projectedOriginalEnds[1][1], Color.orange, buffer);
            drawLine(projectedCenter[0][0], projectedCenter[1][0], projectedOriginalEnds[0][2], projectedOriginalEnds[1][2], Color.cyan, buffer);
        }

        int[][] finalEnds = new int[][] {
                new int[]{ends2[0][0], ends2[1][0], ends2[2][0], 1} ,
                new int[]{ends2[0][1], ends2[1][1], ends2[2][1], 1}  ,
                new int[]{ends2[0][2], ends2[1][2], ends2[2][2], 1},
        };
        int[][] projectedFinalEnds = projection(finalEnds, director, projection);

        drawLine(projectedCenter[0][0], projectedCenter[1][0], projectedFinalEnds[0][0], projectedFinalEnds[1][0], Color.red, buffer);
        drawLine(projectedCenter[0][0], projectedCenter[1][0], projectedFinalEnds[0][1], projectedFinalEnds[1][1], Color.green, buffer);
        drawLine(projectedCenter[0][0], projectedCenter[1][0], projectedFinalEnds[0][2], projectedFinalEnds[1][2], Color.blue, buffer);


    }



}
