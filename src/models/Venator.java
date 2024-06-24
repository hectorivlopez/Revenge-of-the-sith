package models;

import graphics.*;

import static graphics.Draw.*;
import static graphics.Draw3d.*;
import static graphics.Transformations.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Venator {
    public int xc;
    public int yc;
    public int zc;

    public int[] center;

    public int[] x0;
    public int[] y0;
    public int[] z0;

    public int[][] ends;
    public int[][] ends2;

    // ---------- Pov ----------
    public boolean povUp;
    public boolean povLeft;
    public boolean povFront;

    public boolean povUpBack;
    public boolean povLeftBack;
    public boolean povFrontBack;

    // ---------- Transformations ----------
    public double[] angles;
    public double scale;
    public double ang;

    // ---------- Inclination ----------
    public int height;
    public double yInclination;
    public double xTopInclination;
    public double xBottomInclination;

    // ---------- Top Axis ----------
    int[][] topYAxis;
    int[][] topXLeftAxis;
    int[][] topXRightAxis;
    int[][] topYElevatedAxis;
    int[][] bottomYAxis;
    int[][] bottomXAxis;

    // -------------------- Faces --------------------
    // ---------- Sides ----------
    public int[][] front;
    public int[][] back;
    public int[][] backUp;
    public int[][] left1;
    public int[][] left2;
    public int[][] left3;
    public int[][] left4;
    public int[][] left5;
    public int[][] right1;
    public int[][] right2;
    public int[][] right3;
    public int[][] right4;
    public int[][] right5;

    // ---------- Top Sides ----------
    int[][] topLeft;
    int[][] scaledTopLeft;
    int[][] topLeft1;
    int[][] scaledTopLeft1;
    int[][] topLeft2;
    int[][] scaledTopLeft2;

    int[][] topRight;
    int[][] scaledTopRight;
    int[][] topRight1;
    int[][] scaledTopRight1;
    int[][] topRight2;
    int[][] scaledTopRight2;

    // Decoration
    int[][] topLeftDecoration;
    int[][] scaledTopLeftDecoration;

    int[][] topRightDecoration;
    int[][] scaledTopRightDecoration;

    int[][] redLeft1;
    int[][] scaledRedLeft1;
    int[][] redLeft2;
    int[][] scaledRedLeft2;
    int[][] redLeft3;
    int[][] scaledRedLeft3;
    int[][] redLeft4;
    int[][] scaledRedLeft4;
    int[][] redLeft5;
    int[][] scaledRedLeft5;

    int[][] redRight1;
    int[][] scaledRedRight1;
    int[][] redRight2;
    int[][] scaledRedRight2;
    int[][] redRight3;
    int[][] scaledRedRight3;
    int[][] redRight4;
    int[][] scaledRedRight4;
    int[][] redRight5;
    int[][] scaledRedRight5;


    // Flaps
    int[][] topLeftFlap;
    int[][] scaledTopLeftFlap;

    int[][] topRightFlap;
    int[][] scaledTopRightFlap;

    // ---------- Top Center ----------
    // Top red
    int[][] topRedBase;
    int[][] scaledTopRedBase;

    int[][] topRed;
    int[][] scaledTopRed;

    int[][] topRedLeft;
    int[][] topRedRight;
    int[][] topRedFront;

    // Top red
    int[][] topWhiteBase;
    int[][] scaledTopWhiteBase;

    int[][] topWhite;
    int[][] scaledTopWhite;

    int[][] topWhiteLeft;
    int[][] topWhiteRight;

    // Top center
    int[][] topCenterBase;
    int[][] scaledTopCenterBase;

    int[][] topCenter;
    int[][] scaledTopCenter;

    int[][] topCenterCenter;
    int[][] scaledTopCenterCenter;

    int[][] topCenterLeft1;
    int[][] scaledTopCenterLeft1;
    int[][] topCenterLeft2;
    int[][] scaledTopCenterLeft2;

    int[][] topCenterRight1;
    int[][] scaledTopCenterRight1;
    int[][] topCenterRight2;
    int[][] scaledTopCenterRight2;

    int[][] bridgeBase;
    int[][] scaledBridgeBase;

    // ---------- Bottom ----------
    int[][] bottomLeft;
    int[][] scaledBottomLeft;

    int[][] bottomLeft1;
    int[][] scaledBottomLeft1;

    int[][] bottomLeft2;
    int[][] scaledBottomLeft2;

    int[][] bottomRight;
    int[][] scaledBottomRight;

    int[][] bottomRight1;
    int[][] scaledBottomRight1;

    int[][] bottomRight2;
    int[][] scaledBottomRight2;

    int[][] bottomLeftUp;
    int[][] scaledBottomLeftUp;

    int[][] bottomRightUp;
    int[][] scaledBottomRightUp;

    int[][] bottomRedLeft;
    int[][] scaledBottomRedLeft;

    int[][] bottomRedRight;
    int[][] scaledBottomRedRight;

    int[][] bottomHangarLeft;
    int[][] scaledBottomHangarLeft;

    int[][] bottomHangarRight;
    int[][] scaledBottomHangarRight;

    // ---------- Bridge ----------
    // Towers
    int[][] bridgeBase1Front;
    int[][] bridgeBase1Top;
    int[][] scaledBridgeBase1Top;

    int[][] bridgeBase2Top;
    int[][] scaledBridgeBase2Top;

    int[][] bridgeBase2Front;
    int[][] bridgeBase3Front;

    int[][] bridgeBase1LeftFront;
    int[][] bridgeBase1LeftBack;
    int[][] scaledBridgeBase1LeftBack;

    int[][] bridgeBase1RightFront;
    int[][] bridgeBase1RightBack;
    int[][] scaledBridgeBase1RightBack;

    int[][] bridgeBase2Left;
    int[][] bridgeBase2Right;

    int[][] bridgeBase3Left;
    int[][] bridgeBase3Right;

    int[][] bridgeBase1Back;
    int[][] scaledBridgeBase1Back;
    int[][] bridgeBase3Back;

    int[][] bridgeTowerLeftBase1;
    int[][] scaledBridgeTowerLeftBase1;

    int[][] bridgeTowerRightBase1;
    int[][] scaledBridgeTowerRightBase1;

    int[][] bridgeTowerLeftBase2;
    int[][] scaledBridgeTowerLeftBase2;

    int[][] bridgeTowerRightBase2;
    int[][] scaledBridgeTowerRightBase2;

    int[][] bridgeLeft1;
    int[][] scaledBridgeLeft1;
    int[][] bridgeLeft2;
    int[][] scaledBridgeLeft2;
    int[][] bridgeLeft3;
    int[][] scaledBridgeLeft3;

    int[][] bridgeRight1;
    int[][] scaledBridgeRight1;
    int[][] bridgeRight2;
    int[][] scaledBridgeRight2;
    int[][] bridgeRight3;
    int[][] scaledBridgeRight3;

    // Back
    int[][] backCenter1;
    int[][] scaledBackCenter1;

    int[][] backCenter2;
    int[][] scaledBackCenter2;

    int[][] backCenterTop;

    Color borderGray;

    int[][] rotatedXYPlane;
    int[][] rotatedXZPlane;
    int[][] rotatedYZPlane;

    int[][] rotatedXYPlaneBack;
    int[][] rotatedXZPlaneBack;
    int[][] rotatedYZPlaneBack;

    int[] director;
    String projection;
    BufferedImage buffer;

    public Venator(int xc, int yc, int zc, int[] director, String projection, BufferedImage buffer) {
        this.xc = xc;
        this.yc = yc;
        this.zc = zc;
        this.center = new int[]{xc, yc, zc};

        this.x0 = new int[]{xc + 500, yc, zc};
        this.y0 = new int[]{xc, yc + 500, zc};
        this.z0 = new int[]{xc, yc, zc + 500};

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

        this.height = 8;
        this.yInclination = 0.04;
        this.xTopInclination = 0.2;
        this.xBottomInclination = 0.13;

        this.borderGray = new Color(145, 145, 145);

        this.director = director;
        this.projection = projection;
        this.buffer = buffer;

        update();
    }


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


    public synchronized void update() {
        calBack();
        calTopSides();
        calTopCenter();
        calBridge();
        calBottom();
        calSides();

        boolean[] povCenter = calPovCenter(center, director, projection);
        povUp = povCenter[0];
        povLeft = povCenter[1];
        povFront = povCenter[2];

        boolean[] povBack =  calPovBack(center, director, projection);
        povUpBack = povBack[0];
        povLeftBack = povBack[1];
        povFrontBack = povBack[2];
    }

    public synchronized void draw(int[] director, String projection, boolean develop, BufferedImage buffer, double ang) {
        this.ang = ang;

        if (povUp) {
            if (povFrontBack) drawBack(director, projection, buffer);

            drawSides(director, projection, true, buffer);

            drawTopSides(director, projection, true, buffer);

            drawTopCenter(director, projection, true, buffer);

            drawBridge(director, projection, true, buffer);

            drawElevatedSides(director, projection, buffer);

            if (!povFrontBack) drawBack(director, projection, buffer);

            drawBottom(director, projection, true, buffer);
        } else {
            if (povFrontBack) drawBack(director, projection, buffer);

            drawBridge(director, projection, true, buffer);
            drawTopCenter(director, projection, true, buffer);

            drawElevatedSides(director, projection, buffer);

            drawSides(director, projection, true, buffer);

            drawTopSides(director, projection, true, buffer);

            if (!povFrontBack) drawBack(director, projection, buffer);

            drawBottom(director, projection, true, buffer);
        }

        if(develop) {
            drawPlanes(director, projection, buffer);
            drawAxis(director, projection, true, buffer);
        }
    }

    // ---------- Calculations ----------
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

    private boolean[] calPovBack(int[] povCenter, int[] director, String projection) {
        int xc = povCenter[0];
        int yc = povCenter[1];
        int zc = povCenter[2];

        int[][] xyPlaneBack = new int[][]{
                new int[]{xc - 100, xc - 100, xc + 100, xc + 100},
                new int[]{yc + 100, yc - 100, yc - 100, yc + 100},
                new int[]{zc, zc, zc, zc},
        };
        rotatedXYPlaneBack = rotateRespectEuler(xyPlaneBack, center, angles);

        int[][] xzPlaneBack = new int[][]{
                new int[]{xc - 100, xc - 100, xc + 100, xc + 100},
                new int[]{yc, yc, yc, yc},
                new int[]{zc + 100, zc - 100, zc - 100, zc + 100},
        };
        rotatedXZPlaneBack = rotateRespectEuler(xzPlaneBack, center, angles);

        int[][] yzPlaneBack = new int[][]{
                new int[]{xc, xc, xc, xc},
                new int[]{yc + 100, yc + 100, yc - 100, yc - 100},
                new int[]{zc + 100, zc - 100, zc - 100, zc + 100},
        };
        rotatedYZPlaneBack = rotateRespectEuler(yzPlaneBack, center, angles);

        boolean povUp = backFaceCulling(rotatedXYPlaneBack, null, director, projection, 1);
        boolean povLeft = backFaceCulling(rotatedXZPlaneBack, null, director, projection, 1);
        boolean povFront = backFaceCulling(rotatedYZPlaneBack, null, director, projection, 1);

        return new boolean[]{povUp, povLeft, povFront};
    }

    private void calTopSides() {
        topYAxis = new int[][]{new int[]{xc - 430, yc + 17, zc + height}, new int[]{xc - 430, yc - 17, zc + height}};

        // ----- Left -----
        topLeft = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 129, xc + 243, xc + 278, xc + 250,},
                new int[]{yc - 17, yc - 32, yc - 139, yc - 93, yc - 108, yc - 210, yc - 211, yc - 52,},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height,},
        };

        int[][] topLeftRot1 = rotateAroundLine(topLeft[0], topLeft[1], topLeft[2], topYAxis[0], topYAxis[1], yInclination);

        topXLeftAxis = new int[][]{
                new int[]{topLeftRot1[0][0], topLeftRot1[1][0], topLeftRot1[2][0]},
                new int[]{topLeftRot1[0][topLeftRot1[0].length - 1], topLeftRot1[1][topLeftRot1[0].length - 1], topLeftRot1[2][topLeftRot1[0].length - 1]}
        };

        scaledTopLeft = transform3D(topLeftRot1, center, scale, angles, new double[]{xTopInclination}, new int[][][]{topXLeftAxis});

        // Faces to paint
        topLeft1 = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 54},
                new int[]{yc - 17, yc - 32, yc - 139, yc - 93, yc - 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopLeft1 = transform3D(topLeft1, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

        topLeft2 = new int[][]{
                new int[]{xc + 54, xc + 129, xc + 243, xc + 278, xc + 250, xc + 54},
                new int[]{yc - 93, yc - 108, yc - 210, yc - 211, yc - 52, yc - 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopLeft2 = transform3D(topLeft2, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

        // ----- Right -----
        topRight = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 129, xc + 243, xc + 278, xc + 250,},
                new int[]{yc + 17, yc + 32, yc + 139, yc + 93, yc + 108, yc + 210, yc + 211, yc + 52,},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height,},
        };

        int[][] topRightRot1 = rotateAroundLine(topRight[0], topRight[1], topRight[2], topYAxis[0], topYAxis[1], yInclination);

        topXRightAxis = new int[][]{
                new int[]{topRightRot1[0][0], topRightRot1[1][0], topRightRot1[2][0]},
                new int[]{topRightRot1[0][topRightRot1[0].length - 1], topRightRot1[1][topRightRot1[0].length - 1], topRightRot1[2][topRightRot1[0].length - 1]}
        };

        scaledTopRight = transform3D(topRightRot1, center, scale, angles, new double[]{-xTopInclination}, new int[][][]{topXRightAxis});

        // Faces to paint
        topRight1 = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 54},
                new int[]{yc + 17, yc + 32, yc + 139, yc + 93, yc + 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopRight1 = transform3D(topRight1, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        topRight2 = new int[][]{
                new int[]{xc + 54, xc + 129, xc + 243, xc + 278, xc + 250, xc + 54},
                new int[]{yc + 93, yc + 108, yc + 210, yc + 211, yc + 52, yc + 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopRight2 = transform3D(topRight2, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        // Decoration
        topLeftDecoration = new int[][]{
                new int[]{xc - 430, xc - 12, xc + 50, xc + 136, xc + 250, xc + 276, xc + 250,},
                new int[]{yc - 17, yc - 122, yc - 76, yc - 93, yc - 197, yc - 197, yc - 52,},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height,},
        };
        scaledTopLeftDecoration = transform3D(topLeftDecoration, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});


        topRightDecoration = new int[][]{
                new int[]{xc - 430, xc - 12, xc + 50, xc + 136, xc + 250, xc + 276, xc + 250,},
                new int[]{yc + 17, yc + 122, yc + 76, yc + 93, yc + 197, yc + 197, yc + 52,},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height,},
        };
        scaledTopRightDecoration = transform3D(topRightDecoration, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        redLeft1 = new int[][]{
                new int[]{xc + 180, xc + 254, xc + 252, xc + 168},
                new int[]{yc - 132, yc - 132, yc - 122, yc - 122},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedLeft1 = transform3D(redLeft1, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

        redLeft2 = new int[][]{
                new int[]{xc + 198, xc + 257, xc + 255, xc + 186},
                new int[]{yc - 148, yc - 148, yc - 138, yc - 138},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedLeft2 = transform3D(redLeft2, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

        redLeft3 = new int[][]{
                new int[]{xc + 214, xc + 260, xc + 258, xc + 203},
                new int[]{yc - 164, yc - 164, yc - 154, yc - 154},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedLeft3 = transform3D(redLeft3, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

        redLeft4 = new int[][]{
                new int[]{xc + 232, xc + 263, xc + 260, xc + 220},
                new int[]{yc - 180, yc - 180, yc - 170, yc - 170},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedLeft4 = transform3D(redLeft4, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

        redLeft5 = new int[][]{
                new int[]{xc + 250, xc + 266, xc + 263, xc + 238},
                new int[]{yc - 196, yc - 196, yc - 186, yc - 186},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedLeft5 = transform3D(redLeft5, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

        redRight1 = new int[][]{
                new int[]{xc + 180, xc + 254, xc + 252, xc + 168},
                new int[]{yc + 132, yc + 132, yc + 122, yc + 122},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedRight1 = transform3D(redRight1, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        redRight2 = new int[][]{
                new int[]{xc + 198, xc + 257, xc + 255, xc + 186},
                new int[]{yc + 148, yc + 148, yc + 138, yc + 138},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedRight2 = transform3D(redRight2, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        redRight3 = new int[][]{
                new int[]{xc + 214, xc + 260, xc + 258, xc + 203},
                new int[]{yc + 164, yc + 164, yc + 154, yc + 154},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedRight3 = transform3D(redRight3, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        redRight4 = new int[][]{
                new int[]{xc + 232, xc + 263, xc + 260, xc + 220},
                new int[]{yc + 180, yc + 180, yc + 170, yc + 170},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedRight4 = transform3D(redRight4, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        redRight5 = new int[][]{
                new int[]{xc + 250, xc + 266, xc + 263, xc + 238},
                new int[]{yc + 196, yc + 196, yc + 186, yc + 186},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledRedRight5 = transform3D(redRight5, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});


        // Flaps
        int[][] leftFlapAxis = new int[][]{
                new int[]{scaledTopLeft2[0][3], scaledTopLeft2[1][3], scaledTopLeft2[2][3]},
                new int[]{scaledTopLeft2[0][4], scaledTopLeft2[1][4], scaledTopLeft2[2][4]},
        };

        topLeftFlap = new int[][]{
                new int[]{xc + 278, xc + 289, xc + 291, xc + 250},
                new int[]{yc - 211, yc - 211, yc - 55, yc - 52},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopLeftFlap = transform3D(topLeftFlap, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});
        scaledTopLeftFlap = rotateAroundLine(scaledTopLeftFlap[0], scaledTopLeftFlap[1], scaledTopLeftFlap[2], leftFlapAxis[0], leftFlapAxis[1], 0.2);

        int[][] rightFlapAxis = new int[][]{
                new int[]{scaledTopRight2[0][3], scaledTopRight2[1][3], scaledTopRight2[2][3]},
                new int[]{scaledTopRight2[0][4], scaledTopRight2[1][4], scaledTopRight2[2][4]},
        };

        topRightFlap = new int[][]{
                new int[]{xc + 278, xc + 289, xc + 291, xc + 250},
                new int[]{yc + 211, yc + 211, yc + 55, yc + 52},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopRightFlap = transform3D(topRightFlap, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});
        scaledTopRightFlap = rotateAroundLine(scaledTopRightFlap[0], scaledTopRightFlap[1], scaledTopRightFlap[2], rightFlapAxis[0], rightFlapAxis[1], -0.2);
    }

    private void calTopCenter() {
        topYElevatedAxis = new int[][]{new int[]{xc - 430, yc + 17, zc + height + 3}, new int[]{xc - 430, yc - 17, zc + height + 3}};

        // ----- Red -----
        topRedBase = new int[][]{
                new int[]{xc - 430, xc - 10, xc, xc - 10, xc - 430},
                new int[]{yc + 17, yc + 39, yc + 0, yc - 39, yc - 17},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopRedBase = transform3D(topRedBase, center, scale, angles, new double[]{yInclination}, new int[][][]{topYAxis});

        topRed = new int[][]{
                new int[]{xc - 430, xc - 10, xc, xc - 10, xc - 430},
                new int[]{yc + 17, yc + 39, yc + 0, yc - 39, yc - 17},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3},
        };
        scaledTopRed = transform3D(topRed, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topRedLeft = new int[][]{
                new int[]{scaledTopRed[0][3], scaledTopRed[0][4], scaledTopRedBase[0][4], scaledTopRedBase[0][3]},
                new int[]{scaledTopRed[1][3], scaledTopRed[1][4], scaledTopRedBase[1][4], scaledTopRedBase[1][3]},
                new int[]{scaledTopRed[2][3], scaledTopRed[2][4], scaledTopRedBase[2][4], scaledTopRedBase[2][3]},
        };

        topRedRight = new int[][]{
                new int[]{scaledTopRed[0][0], scaledTopRed[0][1], scaledTopRedBase[0][1], scaledTopRedBase[0][0]},
                new int[]{scaledTopRed[1][0], scaledTopRed[1][1], scaledTopRedBase[1][1], scaledTopRedBase[1][0]},
                new int[]{scaledTopRed[2][0], scaledTopRed[2][1], scaledTopRedBase[2][1], scaledTopRedBase[2][0]},
        };

        topRedFront = new int[][]{
                new int[]{scaledTopRed[0][4], scaledTopRed[0][0], scaledTopRedBase[0][0], scaledTopRedBase[0][4]},
                new int[]{scaledTopRed[1][4], scaledTopRed[1][0], scaledTopRedBase[1][0], scaledTopRedBase[1][4]},
                new int[]{scaledTopRed[2][4], scaledTopRed[2][0], scaledTopRedBase[2][0], scaledTopRedBase[2][4]},
        };

        // ----- Center -----
        topCenterBase = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc + 75, xc + 75, xc + 35, xc + 35, xc + 75, xc + 75, xc + 115, xc + 115, xc - 10, xc},
                new int[]{yc + 39, yc + 45, yc + 25, yc + 25, yc + 12, yc + 12, yc - 12, yc - 12, yc - 25, yc - 25, yc - 45, yc - 39, yc - 0},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height, zc + height,},
        };
        scaledTopCenterBase = transform3D(topCenterBase, center, scale, angles, new double[]{yInclination}, new int[][][]{topYAxis});

        topCenter = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc + 75, xc + 75, xc + 35, xc + 35, xc + 75, xc + 75, xc + 115, xc + 115, xc - 10, xc},
                new int[]{yc + 39, yc + 45, yc + 25, yc + 25, yc + 12, yc + 12, yc - 12, yc - 12, yc - 25, yc - 25, yc - 45, yc - 39, yc - 0},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenter = transform3D(topCenter, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterLeft1 = new int[][]{
                new int[]{xc - 6, xc + 75, xc + 75, xc - 3},
                new int[]{yc - 25, yc - 25, yc - 12, yc - 12},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterLeft1 = transform3D(topCenterLeft1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterLeft2 = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc - 6},
                new int[]{yc - 39, yc - 45, yc - 25, yc - 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterLeft2 = transform3D(topCenterLeft2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterRight1 = new int[][]{
                new int[]{xc - 6, xc + 75, xc + 75, xc - 3},
                new int[]{yc + 25, yc + 25, yc + 12, yc + 12},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterRight1 = transform3D(topCenterRight1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterRight2 = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc - 6},
                new int[]{yc + 39, yc + 45, yc + 25, yc + 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterRight2 = transform3D(topCenterRight2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterCenter = new int[][]{
                new int[]{xc - 3, xc + 35, xc + 35, xc - 3, xc},
                new int[]{yc - 12, yc - 12, yc + 12, yc + 12, yc},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3},
        };
        scaledTopCenterCenter = transform3D(topCenterCenter, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topWhite = new int[][]{
                new int[]{xc - 10, xc + 289, xc + 289, xc - 10, xc},
                new int[]{yc + 39, yc + 54, yc - 54, yc - 39, yc},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3},
        };
        scaledTopWhite = transform3D(topWhite, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topWhiteRight = new int[][]{
                new int[]{scaledTopRed[0][1], scaledTopWhite[0][1], scaledTopRightFlap[0][2], /*scaledTopRightFlap[0][3],*/ scaledTopRedBase[0][1]},
                new int[]{scaledTopRed[1][1], scaledTopWhite[1][1], scaledTopRightFlap[1][2], /*scaledTopRightFlap[1][3],*/ scaledTopRedBase[1][1]},
                new int[]{scaledTopRed[2][1], scaledTopWhite[2][1], scaledTopRightFlap[2][2], /*scaledTopRightFlap[2][3],*/ scaledTopRedBase[2][1]},
        };

        topWhiteLeft = new int[][]{
                new int[]{scaledTopRed[0][3], scaledTopWhite[0][2], scaledTopLeftFlap[0][2], /*scaledTopLeftFlap[0][3],*/ scaledTopRedBase[0][3]},
                new int[]{scaledTopRed[1][3], scaledTopWhite[1][2], scaledTopLeftFlap[1][2], /*scaledTopLeftFlap[1][3],*/ scaledTopRedBase[1][3]},
                new int[]{scaledTopRed[2][3], scaledTopWhite[2][2], scaledTopLeftFlap[2][2], /*scaledTopLeftFlap[2][3],*/ scaledTopRedBase[2][3]},
        };

        bridgeBase = new int[][]{
                new int[]{xc + 75, xc + 115, xc + 115, xc + 75,},
                new int[]{yc + 43, yc + 45, yc + 25, yc + 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledBridgeBase = transform3D(bridgeBase, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});
    }

    private void calBridge() {
        // Top
        bridgeBase1Top = new int[][]{
                new int[]{xc + 150, xc + 167, xc + 167, xc + 150},
                new int[]{yc + 12, yc + 12, yc - 12, yc - 12},
                new int[]{zc + 87, zc + 90, zc + 90, zc + 87},
        };
        scaledBridgeBase1Top = transform3D(bridgeBase1Top, center, scale, angles, null, null);

        bridgeBase1Front = new int[][]{
                new int[]{scaledTopCenterCenter[0][1], scaledBridgeBase1Top[0][3], scaledBridgeBase1Top[0][0], scaledTopCenterCenter[0][2]},
                new int[]{scaledTopCenterCenter[1][1], scaledBridgeBase1Top[1][3], scaledBridgeBase1Top[1][0], scaledTopCenterCenter[1][2]},
                new int[]{scaledTopCenterCenter[2][1], scaledBridgeBase1Top[2][3], scaledBridgeBase1Top[2][0], scaledTopCenterCenter[2][2]},
        };

        bridgeBase2Top = new int[][]{
                new int[]{xc + 167, xc + 250, xc + 250, xc + 167},
                new int[]{yc + 16, yc + 16, yc - 16, yc - 16},
                new int[]{zc + 90, zc + 105, zc + 105, zc + 90},
        };
        scaledBridgeBase2Top = transform3D(bridgeBase2Top, center, scale, angles, null, null);

        // Towers
        bridgeTowerLeftBase1 = new int[][]{
                new int[]{xc + 170, xc + 245, xc + 245, xc + 170},
                new int[]{yc - 16, yc - 16, yc - 30, yc - 30},
                new int[]{zc + height + 3 + 53, zc + height + 3 + 53, zc + height + 3 + 53, zc + height + 3 + 53},
        };
        scaledBridgeTowerLeftBase1 = transform3D(bridgeTowerLeftBase1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeTowerLeftBase2 = new int[][]{
                new int[]{xc + 180, xc + 235, xc + 235, xc + 180},
                new int[]{yc - 16, yc - 16, yc - 25, yc - 25},
                new int[]{zc + height + 3 + 90, zc + height + 3 + 90, zc + height + 3 + 90, zc + height + 3 + 90},
        };
        scaledBridgeTowerLeftBase2 = transform3D(bridgeTowerLeftBase2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeTowerRightBase1 = new int[][]{
                new int[]{xc + 170, xc + 245, xc + 245, xc + 170},
                new int[]{yc + 16, yc + 16, yc + 30, yc + 30},
                new int[]{zc + height + 3 + 53, zc + height + 3 + 53, zc + height + 3 + 53, zc + height + 3 + 53},
        };
        scaledBridgeTowerRightBase1 = transform3D(bridgeTowerRightBase1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeTowerRightBase2 = new int[][]{
                new int[]{xc + 180, xc + 235, xc + 235, xc + 180},
                new int[]{yc + 16, yc + 16, yc + 25, yc + 25},
                new int[]{zc + height + 3 + 90, zc + height + 3 + 90, zc + height + 3 + 90, zc + height + 3 + 90},
        };
        scaledBridgeTowerRightBase2 = transform3D(bridgeTowerRightBase2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        // Bridge
        bridgeLeft1 = new int[][]{
                new int[]{xc + 145, xc + 255, xc + 255, xc + 145},
                new int[]{yc - 10, yc - 10, yc - 32, yc - 32},
                new int[]{zc + height + 3 + 90, zc + height + 3 + 90, zc + height + 3 + 90, zc + height + 3 + 90},
        };
        scaledBridgeLeft1 = transform3D(bridgeLeft1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeLeft2 = new int[][]{
                new int[]{xc + 145, xc + 255, xc + 255, xc + 145},
                new int[]{yc - 10, yc - 10, yc - 32, yc - 32},
                new int[]{zc + height + 3 + 95, zc + height + 3 + 95, zc + height + 3 + 95, zc + height + 3 + 95},
        };
        scaledBridgeLeft2 = transform3D(bridgeLeft2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeLeft3 = new int[][]{
                new int[]{xc + 155, xc + 255, xc + 255, xc + 155},
                new int[]{yc - 13, yc - 13, yc - 29, yc - 29},
                new int[]{zc + height + 3 + 100, zc + height + 3 + 100, zc + height + 3 + 100, zc + height + 3 + 100},
        };
        scaledBridgeLeft3 = transform3D(bridgeLeft3, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});


        bridgeRight1 = new int[][]{
                new int[]{xc + 145, xc + 255, xc + 255, xc + 145},
                new int[]{yc + 10, yc + 10, yc + 32, yc + 32},
                new int[]{zc + height + 3 + 90, zc + height + 3 + 90, zc + height + 3 + 90, zc + height + 3 + 90},
        };
        scaledBridgeRight1 = transform3D(bridgeRight1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeRight2 = new int[][]{
                new int[]{xc + 145, xc + 255, xc + 255, xc + 145},
                new int[]{yc + 10, yc + 10, yc + 32, yc + 32},
                new int[]{zc + height + 3 + 95, zc + height + 3 + 95, zc + height + 3 + 95, zc + height + 3 + 95},
        };
        scaledBridgeRight2 = transform3D(bridgeRight2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeRight3 = new int[][]{
                new int[]{xc + 155, xc + 255, xc + 255, xc + 155},
                new int[]{yc + 13, yc + 13, yc + 29, yc + 29},
                new int[]{zc + height + 3 + 100, zc + height + 3 + 100, zc + height + 3 + 100, zc + height + 3 + 100},
        };
        scaledBridgeRight3 = transform3D(bridgeRight3, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        // Front
        bridgeBase2Front = new int[][]{
                new int[]{scaledTopCenterLeft1[0][1], scaledBridgeBase2Top[0][3], scaledBridgeBase2Top[0][0], scaledTopCenterRight1[0][1]},
                new int[]{scaledTopCenterLeft1[1][1], scaledBridgeBase2Top[1][3], scaledBridgeBase2Top[1][0], scaledTopCenterRight1[1][1]},
                new int[]{scaledTopCenterLeft1[2][1], scaledBridgeBase2Top[2][3], scaledBridgeBase2Top[2][0], scaledTopCenterRight1[2][1]},
        };

        bridgeBase3Front = new int[][]{
                new int[]{scaledTopCenterLeft2[0][1], scaledBridgeTowerLeftBase1[0][3], scaledBridgeTowerRightBase1[0][3], scaledTopCenterRight2[0][1]},
                new int[]{scaledTopCenterLeft2[1][1], scaledBridgeTowerLeftBase1[1][3], scaledBridgeTowerRightBase1[1][3], scaledTopCenterRight2[1][1]},
                new int[]{scaledTopCenterLeft2[2][1], scaledBridgeTowerLeftBase1[2][3], scaledBridgeTowerRightBase1[2][3], scaledTopCenterRight2[2][1]},
        };

        // Back
        bridgeBase3Back = new int[][]{
                new int[]{scaledTopWhite[0][2], scaledBridgeTowerLeftBase1[0][2], scaledBridgeTowerRightBase1[0][2], scaledTopWhite[0][1]},
                new int[]{scaledTopWhite[1][2], scaledBridgeTowerLeftBase1[1][2], scaledBridgeTowerRightBase1[1][2], scaledTopWhite[1][1]},
                new int[]{scaledTopWhite[2][2], scaledBridgeTowerLeftBase1[2][2], scaledBridgeTowerRightBase1[2][2], scaledTopWhite[2][1]},
        };

        bridgeBase1Back = new int[][]{
                new int[]{bridgeBase2Top[0][1], bridgeBase2Top[0][2], bridgeBase2Top[0][2] + 78, bridgeBase2Top[0][1] + 78},
                new int[]{bridgeBase2Top[1][1], bridgeBase2Top[1][2], bridgeBase2Top[1][2], bridgeBase2Top[1][1]},
                new int[]{bridgeBase2Top[2][1], bridgeBase2Top[2][2], bridgeBase2Top[2][2] - 81, bridgeBase2Top[2][1] - 81},
        };
        scaledBridgeBase1Back = transform3D(bridgeBase1Back, center, scale, angles, null, null);

        backCenterTop = new int[][]{
                new int[]{scaledBridgeBase1Back[0][3], scaledBridgeBase1Back[0][2], scaledBackCenter2[0][0], scaledBackCenter2[0][1]},
                new int[]{scaledBridgeBase1Back[1][3], scaledBridgeBase1Back[1][2], scaledBackCenter2[1][0], scaledBackCenter2[1][1]},
                new int[]{scaledBridgeBase1Back[2][3], scaledBridgeBase1Back[2][2], scaledBackCenter2[2][0], scaledBackCenter2[2][1]},
        };

        // Sides
        bridgeBase1LeftFront = new int[][]{
                new int[]{scaledTopCenter[0][6], scaledBridgeBase1Top[0][3], scaledBridgeBase1Top[0][2], scaledTopCenter[0][7]},
                new int[]{scaledTopCenter[1][6], scaledBridgeBase1Top[1][3], scaledBridgeBase1Top[1][2], scaledTopCenter[1][7]},
                new int[]{scaledTopCenter[2][6], scaledBridgeBase1Top[2][3], scaledBridgeBase1Top[2][2], scaledTopCenter[2][7]},
        };

        bridgeBase1RightFront = new int[][]{
                new int[]{scaledTopCenter[0][5], scaledBridgeBase1Top[0][0], scaledBridgeBase1Top[0][1], scaledTopCenter[0][4]},
                new int[]{scaledTopCenter[1][5], scaledBridgeBase1Top[1][0], scaledBridgeBase1Top[1][1], scaledTopCenter[1][4]},
                new int[]{scaledTopCenter[2][5], scaledBridgeBase1Top[2][0], scaledBridgeBase1Top[2][1], scaledTopCenter[2][4]},
        };

        int[][] bridgeTowerHeightMid = new int[][]{
                new int[]{xc + 239, xc + 250, xc + 250, xc + 239},
                new int[]{yc + 16, yc + 16, yc - 16, yc - 16},
                new int[]{zc + 103, zc + 105, zc + 105, zc + 103},
        };

        bridgeBase1LeftBack = new int[][]{
                new int[]{bridgeTowerHeightMid[0][3], bridgeBase1Back[0][1], bridgeBase1Back[0][2], backCenter1[0][0], backCenter1[0][0], bridgeTowerHeightMid[0][3] + 3},
                new int[]{bridgeTowerHeightMid[1][3], bridgeBase1Back[1][1], bridgeBase1Back[1][2], backCenter1[1][0], backCenter1[1][0], bridgeTowerHeightMid[1][3]},
                new int[]{bridgeTowerHeightMid[2][3], bridgeBase1Back[2][1], bridgeBase1Back[2][2], backCenter1[2][0], backCenter1[2][0] + 14, bridgeTowerHeightMid[2][3] - 10},
        };
        scaledBridgeBase1LeftBack = transform3D(bridgeBase1LeftBack, center, scale, angles, null, null);

        bridgeBase1RightBack = new int[][]{
                new int[]{bridgeTowerHeightMid[0][0], bridgeBase1Back[0][0], bridgeBase1Back[0][3], backCenter1[0][1], backCenter1[0][1], bridgeTowerHeightMid[0][0] + 3},
                new int[]{bridgeTowerHeightMid[1][0], bridgeBase1Back[1][0], bridgeBase1Back[1][3], backCenter1[1][1], backCenter1[1][1], bridgeTowerHeightMid[1][0]},
                new int[]{bridgeTowerHeightMid[2][0], bridgeBase1Back[2][0], bridgeBase1Back[2][3], backCenter1[2][1], backCenter1[2][1] + 14, bridgeTowerHeightMid[2][0] - 10},
        };
        scaledBridgeBase1RightBack = transform3D(bridgeBase1RightBack, center, scale, angles, null, null);

        bridgeBase2Left = new int[][]{
                new int[]{scaledTopCenter[0][8], scaledBridgeBase2Top[0][3], scaledTopCenter[0][9]},
                new int[]{scaledTopCenter[1][8], scaledBridgeBase2Top[1][3], scaledTopCenter[1][9]},
                new int[]{scaledTopCenter[2][8], scaledBridgeBase2Top[2][3], scaledTopCenter[2][9]},
        };

        bridgeBase2Right = new int[][]{
                new int[]{scaledTopCenter[0][3], scaledBridgeBase2Top[0][0], scaledTopCenter[0][2]},
                new int[]{scaledTopCenter[1][3], scaledBridgeBase2Top[1][0], scaledTopCenter[1][2]},
                new int[]{scaledTopCenter[2][3], scaledBridgeBase2Top[2][0], scaledTopCenter[2][2]},
        };

        bridgeBase3Left = new int[][]{
                new int[]{bridgeBase3Front[0][0], scaledBridgeTowerLeftBase1[0][3], scaledBridgeTowerLeftBase1[0][2], scaledTopWhite[0][2]},
                new int[]{bridgeBase3Front[1][0], scaledBridgeTowerLeftBase1[1][3], scaledBridgeTowerLeftBase1[1][2], scaledTopWhite[1][2]},
                new int[]{bridgeBase3Front[2][0], scaledBridgeTowerLeftBase1[2][3], scaledBridgeTowerLeftBase1[2][2], scaledTopWhite[2][2]},
        };

        bridgeBase3Right = new int[][]{
                new int[]{bridgeBase3Front[0][3], scaledBridgeTowerRightBase1[0][3], scaledBridgeTowerRightBase1[0][2], scaledTopWhite[0][1]},
                new int[]{bridgeBase3Front[1][3], scaledBridgeTowerRightBase1[1][3], scaledBridgeTowerRightBase1[1][2], scaledTopWhite[1][1]},
                new int[]{bridgeBase3Front[2][3], scaledBridgeTowerRightBase1[2][3], scaledBridgeTowerRightBase1[2][2], scaledTopWhite[2][1]},
        };
    }

    private void calBottom() {
        bottomYAxis = new int[][]{new int[]{xc - 430, yc + 17, zc - height}, new int[]{xc - 430, yc - 17, zc - height}};

        // ----- Left -----
        bottomLeft = new int[][]{
                new int[]{xc - 430, xc - 432, xc - 9, xc + 54, xc + 129, xc + 244, xc + 289, xc + 356,},
                new int[]{yc, yc - 32, yc - 138, yc - 92, yc - 107, yc - 208, yc - 209, yc,},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height,},
        };

        int[][] bottomLeftRot1 = rotateAroundLine(bottomLeft[0], bottomLeft[1], bottomLeft[2], bottomYAxis[0], bottomYAxis[1], -yInclination);

        bottomXAxis = new int[][]{
                new int[]{bottomLeftRot1[0][0], bottomLeftRot1[1][0], bottomLeftRot1[2][0]},
                new int[]{bottomLeftRot1[0][7], bottomLeftRot1[1][7], bottomLeftRot1[2][7]},
        };
        scaledBottomLeft = transform3D(bottomLeftRot1, center, scale, angles, new double[]{-xBottomInclination}, new int[][][]{bottomXAxis});

        bottomLeft1 = new int[][]{
                new int[]{xc - 430, xc - 432, xc - 9, xc + 54, xc + 54},
                new int[]{yc, yc - 32, yc - 138, yc - 92, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height},
        };
        scaledBottomLeft1 = transform3D(bottomLeft1, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomLeft2 = new int[][]{
                new int[]{xc + 54, xc + 129, xc + 244, xc + 289, xc + 356, xc + 54},
                new int[]{yc - 92, yc - 107, yc - 208, yc - 209, yc, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomLeft2 = transform3D(bottomLeft2, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        // ----- Right -----
        bottomRight = new int[][]{
                new int[]{xc - 430, xc - 432, xc - 9, xc + 54, xc + 129, xc + 244, xc + 289, xc + 356,},
                new int[]{yc, yc + 32, yc + 138, yc + 92, yc + 107, yc + 208, yc + 209, yc,},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomRight = transform3D(bottomRight, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomRight1 = new int[][]{
                new int[]{xc - 430, xc - 432, xc - 9, xc + 54, xc + 54},
                new int[]{yc, yc + 32, yc + 138, yc + 92, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height},
        };
        scaledBottomRight1 = transform3D(bottomRight1, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomRight2 = new int[][]{
                new int[]{xc + 54, xc + 129, xc + 244, xc + 289, xc + 356, xc + 54},
                new int[]{yc + 92, yc + 107, yc + 208, yc + 209, yc, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomRight2 = transform3D(bottomRight2, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        // ----- Center -----
        bottomRedLeft = new int[][]{
                new int[]{xc - 430, xc - 431, xc - 310, xc - 300, xc - 270, xc - 260, xc - 160, xc - 150, xc - 90, xc - 80, xc + 353, xc + 356},
                new int[]{yc, yc - 10, yc - 10, yc - 17, yc - 17, yc - 10, yc - 10, yc - 17, yc - 17, yc - 10, yc - 10, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height},
        };
        scaledBottomRedLeft = transform3D(bottomRedLeft, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomRedRight = new int[][]{
                new int[]{xc - 430, xc - 431, xc - 310, xc - 300, xc - 270, xc - 260, xc - 160, xc - 150, xc - 90, xc - 80, xc + 353, xc + 356},
                new int[]{yc, yc + 10, yc + 10, yc + 17, yc + 17, yc + 10, yc + 10, yc + 17, yc + 17, yc + 10, yc + 10, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height},
        };
        scaledBottomRedRight = transform3D(bottomRedRight, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        // ----- Hangar -----
        bottomHangarLeft = new int[][]{
                new int[]{xc - 7, xc - 8, xc + 81, xc + 81},
                new int[]{yc, yc - 25, yc - 25, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomHangarLeft = transform3D(bottomHangarLeft, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomHangarRight = new int[][]{
                new int[]{xc - 7, xc - 8, xc + 81, xc + 81},
                new int[]{yc, yc + 25, yc + 25, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomHangarRight = transform3D(bottomHangarRight, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomLeftUp = new int[][]{
                new int[]{bottomLeft[0][6], bottomLeft[0][6], xc + 356},
                new int[]{yc, yc - 209, yc},
                new int[]{zc - height + 2, zc - height, zc - height, zc - height},
        };
        scaledBottomLeftUp = transform3D(bottomLeftUp, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomRightUp = new int[][]{
                new int[]{bottomRight[0][6], bottomRight[0][6], xc + 356},
                new int[]{yc, yc + 209, yc},
                new int[]{zc - height + 2, zc - height, zc - height, zc - height},
        };
        scaledBottomRightUp = transform3D(bottomRightUp, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

    }

    private void calSides() {
        left1 = new int[][]{
                new int[]{scaledTopLeft[0][1], scaledTopLeft[0][2], scaledBottomLeft[0][2], scaledBottomLeft[0][1]},
                new int[]{scaledTopLeft[1][1], scaledTopLeft[1][2], scaledBottomLeft[1][2], scaledBottomLeft[1][1]},
                new int[]{scaledTopLeft[2][1], scaledTopLeft[2][2], scaledBottomLeft[2][2], scaledBottomLeft[2][1]},
        };
        left2 = new int[][]{
                new int[]{scaledTopLeft[0][2], scaledTopLeft[0][3], scaledBottomLeft[0][3], scaledBottomLeft[0][2]},
                new int[]{scaledTopLeft[1][2], scaledTopLeft[1][3], scaledBottomLeft[1][3], scaledBottomLeft[1][2]},
                new int[]{scaledTopLeft[2][2], scaledTopLeft[2][3], scaledBottomLeft[2][3], scaledBottomLeft[2][2]},
        };

        left3 = new int[][]{
                new int[]{scaledTopLeft[0][3], scaledTopLeft[0][4], scaledBottomLeft[0][4], scaledBottomLeft[0][3]},
                new int[]{scaledTopLeft[1][3], scaledTopLeft[1][4], scaledBottomLeft[1][4], scaledBottomLeft[1][3]},
                new int[]{scaledTopLeft[2][3], scaledTopLeft[2][4], scaledBottomLeft[2][4], scaledBottomLeft[2][3]},
        };

        left4 = new int[][]{
                new int[]{scaledTopLeft[0][4], scaledTopLeft[0][5], scaledBottomLeft[0][5], scaledBottomLeft[0][4]},
                new int[]{scaledTopLeft[1][4], scaledTopLeft[1][5], scaledBottomLeft[1][5], scaledBottomLeft[1][4]},
                new int[]{scaledTopLeft[2][4], scaledTopLeft[2][5], scaledBottomLeft[2][5], scaledBottomLeft[2][4]},
        };

        left5 = new int[][]{
                new int[]{scaledTopLeft[0][5], scaledTopLeftFlap[0][0], scaledTopLeftFlap[0][1], scaledBottomLeft[0][6], scaledBottomLeft[0][5]},
                new int[]{scaledTopLeft[1][5], scaledTopLeftFlap[1][0], scaledTopLeftFlap[1][1], scaledBottomLeft[1][6], scaledBottomLeft[1][5]},
                new int[]{scaledTopLeft[2][5], scaledTopLeftFlap[2][0], scaledTopLeftFlap[2][1], scaledBottomLeft[2][6], scaledBottomLeft[2][5]},
        };

        right1 = new int[][]{
                new int[]{scaledTopRight[0][1], scaledTopRight[0][2], scaledBottomRight[0][2], scaledBottomRight[0][1]},
                new int[]{scaledTopRight[1][1], scaledTopRight[1][2], scaledBottomRight[1][2], scaledBottomRight[1][1]},
                new int[]{scaledTopRight[2][1], scaledTopRight[2][2], scaledBottomRight[2][2], scaledBottomRight[2][1]},
        };
        right2 = new int[][]{
                new int[]{scaledTopRight[0][2], scaledTopRight[0][3], scaledBottomRight[0][3], scaledBottomRight[0][2]},
                new int[]{scaledTopRight[1][2], scaledTopRight[1][3], scaledBottomRight[1][3], scaledBottomRight[1][2]},
                new int[]{scaledTopRight[2][2], scaledTopRight[2][3], scaledBottomRight[2][3], scaledBottomRight[2][2]},
        };

        right3 = new int[][]{
                new int[]{scaledTopRight[0][3], scaledTopRight[0][4], scaledBottomRight[0][4], scaledBottomRight[0][3]},
                new int[]{scaledTopRight[1][3], scaledTopRight[1][4], scaledBottomRight[1][4], scaledBottomRight[1][3]},
                new int[]{scaledTopRight[2][3], scaledTopRight[2][4], scaledBottomRight[2][4], scaledBottomRight[2][3]},
        };

        right4 = new int[][]{
                new int[]{scaledTopRight[0][4], scaledTopRight[0][5], scaledBottomRight[0][5], scaledBottomRight[0][4]},
                new int[]{scaledTopRight[1][4], scaledTopRight[1][5], scaledBottomRight[1][5], scaledBottomRight[1][4]},
                new int[]{scaledTopRight[2][4], scaledTopRight[2][5], scaledBottomRight[2][5], scaledBottomRight[2][4]},
        };

        right5 = new int[][]{
                new int[]{scaledTopRight[0][5], scaledTopRightFlap[0][0], scaledTopRightFlap[0][1], scaledBottomRight[0][6], scaledBottomRight[0][5]},
                new int[]{scaledTopRight[1][5], scaledTopRightFlap[1][0], scaledTopRightFlap[1][1], scaledBottomRight[1][6], scaledBottomRight[1][5]},
                new int[]{scaledTopRight[2][5], scaledTopRightFlap[2][0], scaledTopRightFlap[2][1], scaledBottomRight[2][6], scaledBottomRight[2][5]},
        };

        front = new int[][]{
                new int[]{scaledTopLeft[0][1], scaledTopLeft[0][0], scaledTopRight[0][0], scaledTopRight[0][1], scaledBottomRight[0][1], scaledBottomRight[0][0], scaledBottomLeft[0][1]},
                new int[]{scaledTopLeft[1][1], scaledTopLeft[1][0], scaledTopRight[1][0], scaledTopRight[1][1], scaledBottomRight[1][1], scaledBottomRight[1][0], scaledBottomLeft[1][1]},
                new int[]{scaledTopLeft[2][1], scaledTopLeft[2][0], scaledTopRight[2][0], scaledTopRight[2][1], scaledBottomRight[2][1], scaledBottomRight[2][0], scaledBottomLeft[2][1]},
        };

        back = new int[][]{
                new int[]{scaledTopWhite[0][1], scaledTopWhite[0][2], scaledTopLeftFlap[0][2], scaledTopLeftFlap[0][1], scaledBottomLeft[0][6], scaledBottomRightUp[0][0], scaledBottomRight[0][6], scaledTopRightFlap[0][1], scaledTopRightFlap[0][2]},
                new int[]{scaledTopWhite[1][1], scaledTopWhite[1][2], scaledTopLeftFlap[1][2], scaledTopLeftFlap[1][1], scaledBottomLeft[1][6], scaledBottomRightUp[1][0], scaledBottomRight[1][6], scaledTopRightFlap[1][1], scaledTopRightFlap[1][2]},
                new int[]{scaledTopWhite[2][1], scaledTopWhite[2][2], scaledTopLeftFlap[2][2], scaledTopLeftFlap[2][1], scaledBottomLeft[2][6], scaledBottomRightUp[2][0], scaledBottomRight[2][6], scaledTopRightFlap[2][1], scaledTopRightFlap[2][2]},
        };

        backUp = new int[][]{
                new int[]{back[0][0], back[0][1], back[0][2], back[0][8]},
                new int[]{back[1][0], back[1][1], back[1][2], back[1][8]},
                new int[]{back[2][0], back[2][1], back[2][2], back[2][8]},
        };
    }

    private void calBack() {
        backCenter1 = new int[][]{
                new int[]{xc + 288, xc + 288, xc + 288, xc + 288},
                new int[]{yc - 16, yc + 16, yc + 20, yc - 20},
                new int[]{zc + 28, zc + 28, zc - 10, zc - 10},
        };
        scaledBackCenter1 = transform3D(backCenter1, center, scale, angles, null, null);

        backCenter2 = new int[][]{
                new int[]{xc + 288 + 145, xc + 288 + 145, xc + 288 + 170, xc + 288 + 170},
                new int[]{yc - 16, yc + 16, yc + 20, yc - 20},
                new int[]{zc + 15, zc + 15, zc - 10, zc - 10},
        };
        scaledBackCenter2 = transform3D(backCenter2, center, scale, angles, null, null);
    }


    public void drawPlanes(int[] director, String projection, BufferedImage buffer) {
        drawSurface(rotatedXYPlane, director, projection, 0, false, Color.blue, null, buffer);
        drawSurface(rotatedXZPlane, director, projection, 0, false, Color.green, null, buffer);
        drawSurface(rotatedYZPlane, director, projection, 0, false, Color.red, null, buffer);

    }

    // -------------------- Faces --------------------
    // ---------- Draw sections ----------
    private void drawTopSides(int[] director, String projection, boolean fill, BufferedImage buffer) {
        drawSurface(scaledTopLeft1, director, projection, 1, false, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        drawSurface(scaledTopLeft2, director, projection, -1, false, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        drawSurface(scaledTopLeftFlap, director, projection, 1, false, borderGray, fill ? new Color(162, 162, 162) : null, buffer);

        drawSurface(scaledRedLeft1, director, projection, 1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledRedLeft2, director, projection, 1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledRedLeft3, director, projection, 1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledRedLeft4, director, projection, 1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledRedLeft5, director, projection, 1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledTopLeftDecoration, director, projection, 1, false, Color.gray, null, buffer);

        drawSurface(scaledTopRight1, director, projection, -1, false, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        drawSurface(scaledTopRight2, director, projection, 1, false, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        drawSurface(scaledTopRightFlap, director, projection, -1, false, borderGray, fill ? new Color(162, 162, 162) : null, buffer);

        drawSurface(scaledRedRight1, director, projection, -1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledRedRight2, director, projection, -1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledRedRight3, director, projection, -1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledRedRight4, director, projection, -1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledRedRight5, director, projection, -1, false, null, new Color(143, 67, 76), buffer);
        drawSurface(scaledTopRightDecoration, director, projection, -1, false, Color.gray, null, buffer);

        drawSurface(backUp, director, projection, 1, false, borderGray, fill ? new Color(162, 159, 162) : null, buffer);
    }

    private void drawTopCenter(int[] director, String projection, boolean fill, BufferedImage buffer) {
        drawSurface(scaledTopRed, director, projection, -1, false, new Color(120, 80, 80), fill ? new Color(143, 67, 76) : null, buffer);
        drawSurface(scaledTopWhite, director, projection, -1, false, borderGray, fill ? new Color(170, 170, 170) : null, buffer);
    }

    private void drawElevatedSides(int[] director, String projection, BufferedImage buffer) {
        drawSurface(topRedRight, director, projection, 1, false, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
        drawSurface(topRedLeft, director, projection, 1, false, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
        drawSurface(topRedFront, director, projection, 1, false, new Color(120, 80, 80), new Color(116, 53, 60), buffer);

        drawSurface(topWhiteRight, director, projection, 1, false, borderGray, new Color(160, 160, 160), buffer);
        drawSurface(topWhiteLeft, director, projection, -1, false, borderGray, new Color(160, 160, 160), buffer);
    }

    private void drawBridge(int[] director, String projection, boolean fill, BufferedImage buffer) {
        if (!povUp) drawBridgeTowers(director, projection, fill, buffer);
        // Base
        ArrayList<Surface> surfaces = new ArrayList<>();

        surfaces.add(new Surface(bridgeBase3Front, 1, borderGray, fill ? new Color(180, 183, 178) : null));
        surfaces.add(new Surface(bridgeBase2Front, 1, borderGray, fill ? new Color(178, 183, 181) : null));
        surfaces.add(new Surface(bridgeBase1Front, 1, borderGray, fill ? new Color(170, 172, 171) : null));

        surfaces.add(new Surface(bridgeBase1LeftFront, -1, borderGray, fill ? new Color(160, 161, 159) : null));
        surfaces.add(new Surface(bridgeBase2Left, -1, borderGray, fill ? new Color(162, 161, 159) : null));
        surfaces.add(new Surface(bridgeBase3Left, -1, borderGray, fill ? new Color(163, 161, 159) : null));

        surfaces.add(new Surface(bridgeBase1RightFront, 1, borderGray, fill ? new Color(158, 161, 159) : null));
        surfaces.add(new Surface(bridgeBase2Right, 1, borderGray, fill ? new Color(162, 161, 159) : null));
        surfaces.add(new Surface(bridgeBase3Right, 1, borderGray, fill ? new Color(163, 161, 159) : null));

        surfaces.add(new Surface(bridgeBase3Back, -1, borderGray, fill ? new Color(180, 182, 183) : null));

        surfaces.add(new Surface(scaledBridgeBase1RightBack, 1, borderGray, fill ? new Color(159, 161, 159) : null));
        surfaces.add(new Surface(scaledBridgeBase1LeftBack, -1, borderGray, fill ? new Color(159, 161, 158) : null));

        drawSortedSurfaces(surfaces, director, projection, buffer);

        if (!povUp)
            drawSurface(scaledBridgeBase1Back, director, projection, 1, false, borderGray, fill ? new Color(178, 182, 183) : null, buffer);

        if (povUp) drawBridgeTowers(director, projection, fill, buffer);


    }

    private void drawBottom(int[] director, String projection, boolean fill, BufferedImage buffer) {
        //drawSurface(scaledBottomLeft, director, projection, -1, false, borderGray, fill ? new Color(180, 180, 180) : null, buffer);
        drawSurface(scaledBottomLeft1, director, projection, -1, false, null, fill ? new Color(180, 180, 180) : null, buffer);
        drawSurface(scaledBottomLeft2, director, projection, 1, false, null, fill ? new Color(180, 180, 180) : null, buffer);

        //drawSurface(scaledBottomRight, director, projection, 1, false, borderGray, fill ? new Color(180, 180, 180) : null, buffer);
        drawSurface(scaledBottomRight1, director, projection, 1, false, null, fill ? new Color(180, 180, 180) : null, buffer);
        drawSurface(scaledBottomRight2, director, projection, -1, false, null, fill ? new Color(180, 180, 180) : null, buffer);


        drawSurface(scaledBottomRedLeft, director, projection, -1, false, new Color(116, 53, 60), fill ? new Color(116, 53, 60) : null, buffer);
        drawSurface(scaledBottomRedRight, director, projection, 1, false, new Color(116, 53, 60), fill ? new Color(116, 53, 60) : null, buffer);

        if (!povUp) {
            drawSurface(scaledBottomHangarLeft, director, projection, -1, false, Color.black, Color.black, buffer);
            drawSurface(scaledBottomHangarRight, director, projection, 1, false, Color.black, Color.black, buffer);
        }
    }

    private void drawSides(int[] director, String projection, boolean fill, BufferedImage buffer) {
        ArrayList<Surface> surfaces = new ArrayList<>();

        surfaces.add(new Surface(left1, -1, borderGray, fill ? new Color(100, 100, 100) : null));
        surfaces.add(new Surface(left2, -1, borderGray, fill ? new Color(101, 100, 100) : null));
        surfaces.add(new Surface(left3, -1, borderGray, fill ? new Color(100, 101, 100) : null));
        surfaces.add(new Surface(left4, -1, borderGray, fill ? new Color(100, 100, 101) : null));
        surfaces.add(new Surface(left5, -1, borderGray, fill ? new Color(101, 101, 100) : null));

        surfaces.add(new Surface(right1, 1, borderGray, fill ? new Color(100, 100, 100) : null));
        surfaces.add(new Surface(right2, 1, borderGray, fill ? new Color(101, 100, 100) : null));
        surfaces.add(new Surface(right3, 1, borderGray, fill ? new Color(100, 101, 100) : null));
        surfaces.add(new Surface(right4, 1, borderGray, fill ? new Color(100, 100, 101) : null));
        surfaces.add(new Surface(right5, 1, borderGray, fill ? new Color(101, 101, 100) : null));

        surfaces.add(new Surface(front, 1, borderGray, fill ? new Color(101, 101, 100) : null));

        surfaces.add(new Surface(back, 1, borderGray, fill ? new Color(91, 91, 90) : null));

        drawSortedSurfaces(surfaces, director, projection, buffer);
    }

    private void drawBack(int[] director, String projection, BufferedImage buffer) {
        Color motor1Color = new Color(48, 47, 47);
        Color motor2Color = new Color(48, 48, 47);
        Color motor3Color = new Color(48, 47, 48);
        Color motor4Color = new Color(47, 47, 47);
        Color motor5Color = new Color(47, 48, 47);
        Color motor6Color = new Color(47, 47, 48);

        if (!povUpBack) {
            drawSurface(scaledBridgeBase1Back, director, projection, 1, false, borderGray, new Color(178, 183, 183), buffer);

            drawSurface(scaledBridgeBase1LeftBack, director, projection, -1, false, borderGray, new Color(159, 161, 157), buffer);
            drawSurface(scaledBridgeBase1RightBack, director, projection, 1, false, borderGray, new Color(159, 162, 157), buffer);
        } else {
            drawSurface(scaledBottomLeftUp, director, projection, 1, false, borderGray, new Color(100, 99, 100), buffer);
            drawSurface(scaledBottomRightUp, director, projection, -1, false, borderGray, new Color(100, 99, 98), buffer);
        }
        if (povLeftBack) {
            drawMotor(new int[]{xc + 288, yc + 80, zc - 5}, 18, 50, 18, director, projection, motor1Color, buffer);
            drawMotor(new int[]{xc + 288, yc + 40, zc - 12}, 17, 140, 18, director, projection, motor2Color, buffer);
            drawMotor(new int[]{xc + 288, yc + 35, zc + 15}, 10, 50, 10, director, projection, motor3Color, buffer);

        } else {
            drawMotor(new int[]{xc + 288, yc - 80, zc - 5}, 18, 50, 18, director, projection, motor4Color, buffer);
            drawMotor(new int[]{xc + 288, yc - 40, zc - 12}, 17, 140, 18, director, projection, motor5Color, buffer);
            drawMotor(new int[]{xc + 288, yc - 35, zc + 15}, 10, 50, 10, director, projection, motor6Color, buffer);
        }

        int[][][] backCenter = new int[][][]{scaledBackCenter1, scaledBackCenter2};
        drawPolyhedronFaces(backCenter, new int[]{0, 2}, -1, director, projection, Color.gray, new Color(87, 88, 88), buffer);
        drawSurface(backCenterTop, director, projection, 1, false, Color.gray, new Color(88, 88, 88), buffer);

        if (!povLeftBack) {
            drawMotor(new int[]{xc + 288, yc + 35, zc + 15}, 10, 50, 10, director, projection, motor3Color, buffer);
            drawMotor(new int[]{xc + 288, yc + 40, zc - 12}, 17, 140, 18, director, projection, motor2Color, buffer);
            drawMotor(new int[]{xc + 288, yc + 80, zc - 5}, 18, 50, 18, director, projection, motor1Color, buffer);
            if (povUpBack)
                drawMotor(new int[]{xc + 288, yc + 35, zc + 15}, 10, 50, 10, director, projection, motor3Color, buffer);

        } else {
            drawMotor(new int[]{xc + 288, yc - 35, zc + 15}, 10, 50, 10, director, projection, motor6Color, buffer);
            drawMotor(new int[]{xc + 288, yc - 40, zc - 12}, 17, 140, 18, director, projection, motor5Color, buffer);
            drawMotor(new int[]{xc + 288, yc - 80, zc - 5}, 18, 50, 18, director, projection, motor4Color, buffer);
            if (povUpBack)
                drawMotor(new int[]{xc + 288, yc - 35, zc + 15}, 10, 50, 10, director, projection, motor3Color, buffer);

        }

        if (povUpBack) {
            drawSurface(scaledBridgeBase1Back, director, projection, 1, false, borderGray, new Color(178, 183, 183), buffer);

            drawSurface(scaledBridgeBase1LeftBack, director, projection, -1, false, borderGray, new Color(159, 161, 157), buffer);
            drawSurface(scaledBridgeBase1RightBack, director, projection, 1, false, borderGray, new Color(159, 162, 157), buffer);
        } else {
            drawSurface(scaledBottomLeftUp, director, projection, 1, false, borderGray, new Color(100, 99, 100), buffer);
            drawSurface(scaledBottomRightUp, director, projection, -1, false, borderGray, new Color(100, 99, 98), buffer);

        }
    }

    // ---------- Draw components ----------
    private void drawBridgeTowers(int[] director, String projection, boolean fill, BufferedImage buffer) {
        if (povLeft) drawBridgeTower("right", director, projection, fill, buffer);
        else drawBridgeTower("left", director, projection, fill, buffer);

        drawSurface(scaledBridgeBase1Top, director, projection, -1, false, null, fill ? new Color(170, 172, 171) : null, buffer);
        drawSurface(scaledBridgeBase2Top, director, projection, -1, false, borderGray, fill ? new Color(170, 172, 173) : null, buffer);

        if (povUp)
            drawSurface(scaledBridgeBase1Back, director, projection, 1, false, borderGray, fill ? new Color(178, 182, 183) : null, buffer);

        if (!povLeft) drawBridgeTower("right", director, projection, fill, buffer);
        else drawBridgeTower("left", director, projection, fill, buffer);
    }

    private void drawBridgeTower(String side, int[] director, String projection, boolean fill, BufferedImage buffer) {
        if (side.equals("left")) {
            int[][][] leftBridge = new int[][][]{scaledBridgeLeft1, scaledBridgeLeft2, scaledBridgeLeft3};

            if (!povUp)
                drawPolyhedronFaces(leftBridge, null, -1, director, projection, borderGray, fill ? new Color(171, 169, 170) : null, buffer);

            int[][][] leftTowerBase = new int[][][]{scaledBridgeTowerLeftBase1, scaledBridgeTowerLeftBase2};
            drawPolyhedronFaces(leftTowerBase, new int[]{0, 1}, -1, director, projection, borderGray, fill ? new Color(183, 179, 183) : null, buffer);

            if (povUp)
                drawPolyhedronFaces(leftBridge, null, -1, director, projection, borderGray, fill ? new Color(171, 169, 170) : null, buffer);
        }
        if (side.equals("right")) {
            int[][][] rightBridge = new int[][][]{scaledBridgeRight1, scaledBridgeRight2, scaledBridgeRight3};

            if (!povUp)
                drawPolyhedronFaces(rightBridge, null, 1, director, projection, borderGray, fill ? new Color(171, 169, 172) : null, buffer);

            int[][][] rightTowerBase = new int[][][]{scaledBridgeTowerRightBase1, scaledBridgeTowerRightBase2};
            drawPolyhedronFaces(rightTowerBase, new int[]{0, 1}, 1, director, projection, borderGray, fill ? new Color(181, 182, 179) : null, buffer);

            if (povUp)
                drawPolyhedronFaces(rightBridge, null, 1, director, projection, borderGray, fill ? new Color(171, 169, 172) : null, buffer);

        }
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
            zPoints2[i] = (int) (p0[2] + (radius - 3) * Math.cos(angle));
            yPoints2[i] = (int) (p0[1] + (radius - 3) * Math.sin(angle));
        }

        int[] x0Points = new int[numSides];
        int[] xfPoints = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            x0Points[i] = x0;
            xfPoints[i] = x0 + length;
        }

        int[][] face1 = new int[][]{x0Points, yPoints, zPoints};
        int[][] face2 = new int[][]{xfPoints, yPoints, zPoints};
        int[][] face3 = new int[][]{xfPoints, yPoints2, zPoints2};

        int[][] transformedFace1 = transform3D(face1, center, scale, angles, null, null);
        int[][] transformedFace2 = transform3D(face2, center, scale, angles, null, null);
        int[][] transformedFace3 = transform3D(face3, center, scale, angles, null, null);

        int[][][] faces = new int[][][]{transformedFace1, transformedFace2};

        drawPolyhedronFaces(faces, new int[]{0}, -1, director, projection, new Color(60, 60, 60), color, buffer);

        drawSurface(transformedFace3, director, projection, -1, false, new Color(60, 60, 60), new Color(50, 128, 230), buffer);
        //Draw.drawPolygon(zPoints, yPoints, Color.green, buffer);
    }


    // -------------------- Utils --------------------
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
