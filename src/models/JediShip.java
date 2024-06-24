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


    public JediShip(int xc, int yc, int zc, int[] director, String projection, BufferedImage buffer) {
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

        this.cabinSides = 16;

        this.director = director;
        this.projection = projection;
        this.buffer = buffer;

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
    }

    public void calWingsSides() {
        leftWingSides = new ArrayList<Surface>();
        rightWingSides = new ArrayList<Surface>();

        calWingSides(leftWingSides, scaledLeftWingTop, scaledLeftWingBottom, 1, Color.black);
        calWingSides(rightWingSides, scaledRightWingTop, scaledRightWingBottom, -1, new Color(0, 1, 0));
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

        int[] z5Points = new int[numSides];
        int[] y5Points = new int[numSides];

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

            z5Points[i] = (int) (zc + 40 * Math.cos(angle)) + 10;
            y5Points[i] = (int) (yc + 40 * Math.sin(angle));
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
        int[][] face5 = new int[][]{x5Points, y5Points, z5Points};

        int[][] transformedFace0 = transform3D(face0, center, scale, angles, null, null);
        int[][] transformedFace1 = transform3D(face1, center, scale, angles, null, null);
        int[][] transformedFace2 = transform3D(face2, center, scale, angles, null, null);
        int[][] transformedFace3 = transform3D(face3, center, scale, angles, null, null);
        int[][] transformedFace4 = transform3D(face4, center, scale, angles, null, null);
        int[][] transformedFace5 = transform3D(face5, center, scale, angles, null, null);

        cabinFrontLevels = new int[][][]{transformedFace0, transformedFace1, transformedFace2, transformedFace3,};
        cabinBackLevels = new int[][][]{transformedFace3, transformedFace4, transformedFace5};
    }



    // ------------------------------ Drawing ------------------------------
    public void draw(boolean perspective, int[] director, String projection, BufferedImage buffer, double ang) {
        this.ang = ang;


        Color leftTopColor = Color.red;
        Color rightTopColor = Color.green;
        Color leftBottomColor = Color.pink;
        Color rightBottomColor = Color.orange;

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
                    } else {
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

                    } else {

                    }
                }

            } else {
                if (povFront) {
                    if (povLeft) {

                    } else {

                    }
                } else {
                    if (povLeft) {

                    } else {

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
        drawPolyhedronFaces(cabinFrontLevels, new int[]{1}, 1, director, projection, Color.white, Color.blue, buffer);
    }

    public void drawCabinBack(int[] director, String projection, boolean fill, BufferedImage buffer) {
        drawPolyhedronFaces(cabinBackLevels, null, 1, director, projection, Color.white, Color.cyan, buffer);
    }

    public void drawArturito() {

    }




}
