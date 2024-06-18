package models;

import graphics.Surface;
import graphics.Transformations;
import graphics.Utils;

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

    public int x0;
    public int y0;
    public int z0;

    public double ang;


    // ---------- Sides ----------
    public int[][] front;
    public int[][] back;
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


    public int height;
    public double yInclination;
    public double xTopInclination;
    public double xBottomInclination;

    public double[] angles;
    public double scale;

    public ArrayList<Surface> surfaces;

    // ---------- Top Axis ----------
    int[][] topYAxis;
    int[][] topXLeftAxis;
    int[][] topXRightAxis;
    int[][] topYElevatedAxis;
    int[][] bottomYAxis;
    int[][] bottomXAxis;

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


    int[][] topCentera;
    int[][] scaledTopCentera;

    int[][] topCenterCentera;
    int[][] scaledTopCenterCentera;

    int[][] topCenterLeft1a;
    int[][] scaledTopCenterLeft1a;
    int[][] topCenterLeft2a;
    int[][] scaledTopCenterLeft2a;

    int[][] topCenterRight1a;
    int[][] scaledTopCenterRight1a;
    int[][] topCenterRight2a;
    int[][] scaledTopCenterRight2a;


    int[][] bridgeBase;
    int[][] scaledBridgeBase;

    // ---------- Bottom ----------
    int[][] bottomLeft;
    int[][] scaledBottomLeft;

    int[][] bottomRight;
    int[][] scaledBottomRight;

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

    int[][] bridgeTopLeft1;
    int[][] scaledBridgeTopLeft1;
    int[][] bridgeTopLeft2;
    int[][] scaledBridgeTopLeft2;

    int[][] bridgeTopRight1;
    int[][] scaledBridgeTopRight1;
    int[][] bridgeTopRight2;
    int[][] scaledBridgeTopRight2;

    int[][] bridgeBase2Front;
    int[][] scaledBridgeFront2;

    int[][] bridgeBase3Front;
    int[][] scaledBridgeFront3;

    int[][] bridgeBase1LeftFront;
    int[][] bridgeBase1LeftBack;
    int[][] bridgeBase1RightFront;
    int[][] bridgeBase1RightBack;

    int[][] bridgeBase2Left;
    int[][] bridgeBase2Right;

    int[][] bridgeBase3Left;
    int[][] bridgeBase3Right;

    int[][] bridgeBase1Back;
    int[][] scaledBridgeBase1Back;
    int[][] bridgeBase3Back;

    int[][] bridgeTowerLeftBase;
    int[][] scaledBridgeTowerLeftBase;

    int[][] bridgeTowerRightBase;
    int[][] scaledBridgeTowerRightBase;

    public Venator(int xc, int yc, int zc, double scale, double[] angles) {
        this.xc = xc;
        this.yc = yc;
        this.zc = zc;
        this.center = new int[]{xc, yc, zc};

        this.x0 = xc + 500;
        this.y0 = yc + 500;
        this.z0 = zc + 500;

        this.surfaces = new ArrayList();

        this.height = 8;
        this.yInclination = 0.04;
        this.xTopInclination = 0.2;
        this.xBottomInclination = 0.13;

        this.angles = angles;
        this.scale = scale;
        this.ang = 0;
    }


    public void draw(int[] director, String projection, BufferedImage buffer, double ang) {
        this.ang = ang;
        this.surfaces = new ArrayList();

        calTopSides();
        calTopCenter();
        calBridge();
        calBottom();
        calSides();


        /*System.out.println("----------");
        for(int i = 0; i < bottomRightRot2.length; i++) {
            for(int j = 0; j < bottomRightRot2[0].length; j++) {
                System.out.print(bottomRightRot2[i][j] + ", ");

            }
            System.out.println("");
        }*/

        surface(topRedRight, director, projection, 1, false, null, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
        surface(topRedLeft, director, projection, 1, false, null, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
        surface(topRedFront, director, projection, 1, false, null, new Color(120, 80, 80), new Color(116, 53, 60), buffer);

        surface(topWhiteRight, director, projection, 1, false, null, new Color(160, 160, 160), new Color(160, 160, 160), buffer);
        surface(topWhiteLeft, director, projection, -1, false, null, new Color(160, 160, 160), new Color(160, 160, 160), buffer);
        drawSides(director, projection, true, buffer);

        drawTopSides(director, projection, true, buffer);

        /*if (!(angles[2] > Math.PI && angles[2] < 2 * Math.PI)) {
            surface(back, director, projection, 1, false, null, Color.gray, new Color(90, 90, 90), buffer);
        }*/

        drawTopCenter(director, projection, true, buffer);

        drawBridge(director, projection, true, buffer);

        drawBottom(director, projection, true, buffer);




        //drawAxis(director, projection, buffer);
    }



    private void drawAxis(int[] director, String projection, BufferedImage buffer) {
        int[] xAxis1 = new int[]{xc, yc, zc};
        int[] xAxis2 = new int[]{x0, yc, zc};

        int[][] axis = rotateAroundLine(new int[]{xc, x0, xc, xc}, new int[]{yc, yc, y0, yc}, new int[]{zc, zc, zc, z0}, xAxis1, xAxis2, angles[0]);

        int[] yAxis1 = new int[]{xc, yc, zc};
        int[] yAxis2 = new int[]{axis[0][2], axis[1][2], axis[2][2]};

        axis = rotateAroundLine(axis[0], axis[1], axis[2], yAxis1, yAxis2, angles[1]);

        int[] zAxis1 = new int[]{xc, yc, zc};
        int[] zAxis2 = new int[]{axis[0][3], axis[1][3], axis[2][3]};

        axis = rotateAroundLine(axis[0], axis[1], axis[2], zAxis1, zAxis2, angles[2]);

        int[][] projectedAxis = projection(axis, director, projection);
        fillCircle(projectedAxis[0][0], projectedAxis[1][0], 3, Color.red, buffer);
        for (int i = 1; i < 4; i++) {
            drawLine(projectedAxis[0][0], projectedAxis[1][0], projectedAxis[0][i], projectedAxis[1][i], null, buffer);
        }
    }


    // Calc
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

        scaledTopLeft = transform(topLeftRot1, center, scale, angles, new double[]{xTopInclination}, new int[][][]{topXLeftAxis});

        // Faces to paint
        topLeft1 = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 54},
                new int[]{yc - 17, yc - 32, yc - 139, yc - 93, yc - 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopLeft1 = transform(topLeft1, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

        topLeft2 = new int[][]{
                new int[]{xc + 54, xc + 129, xc + 243, xc + 278, xc + 250, xc + 54},
                new int[]{yc - 93, yc - 108, yc - 210, yc - 211, yc - 52, yc - 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopLeft2 = transform(topLeft2, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});

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

        scaledTopRight = transform(topRightRot1, center, scale, angles, new double[]{-xTopInclination}, new int[][][]{topXRightAxis});

        // Faces to paint
        topRight1 = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 54},
                new int[]{yc + 17, yc + 32, yc + 139, yc + 93, yc + 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopRight1 = transform(topRight1, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        topRight2 = new int[][]{
                new int[]{xc + 54, xc + 129, xc + 243, xc + 278, xc + 250, xc + 54},
                new int[]{yc + 93, yc + 108, yc + 210, yc + 211, yc + 52, yc + 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopRight2 = transform(topRight2, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});

        int[][] leftFlapAxis = new int[][]{
                new int[]{scaledTopLeft2[0][3], scaledTopLeft2[1][3], scaledTopLeft2[2][3]},
                new int[]{scaledTopLeft2[0][4], scaledTopLeft2[1][4], scaledTopLeft2[2][4]},
        };

        topLeftFlap = new int[][]{
                new int[]{xc + 278, xc + 289, xc + 291, xc + 250},
                new int[]{yc - 211, yc - 211, yc - 55, yc - 52},
                new int[]{zc + height, zc + height, zc + height, zc + height},
        };
        scaledTopLeftFlap = transform(topLeftFlap, center, scale, angles, new double[]{yInclination, xTopInclination}, new int[][][]{topYAxis, topXLeftAxis});
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
        scaledTopRightFlap = transform(topRightFlap, center, scale, angles, new double[]{yInclination, -xTopInclination}, new int[][][]{topYAxis, topXRightAxis});
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
        scaledTopRedBase = transform(topRedBase, center, scale, angles, new double[]{yInclination}, new int[][][]{topYAxis});

        topRed = new int[][]{
                new int[]{xc - 430, xc - 10, xc, xc - 10, xc - 430},
                new int[]{yc + 17, yc + 39, yc + 0, yc - 39, yc - 17},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3},
        };
        scaledTopRed = transform(topRed, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

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
        scaledTopCenterBase = transform(topCenterBase, center, scale, angles, new double[]{yInclination}, new int[][][]{topYAxis});

        topCenter = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc + 75, xc + 75, xc + 35, xc + 35, xc + 75, xc + 75, xc + 115, xc + 115, xc - 10, xc},
                new int[]{yc + 39, yc + 45, yc + 25, yc + 25, yc + 12, yc + 12, yc - 12, yc - 12, yc - 25, yc - 25, yc - 45, yc - 39, yc - 0},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenter = transform(topCenter, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterLeft1 = new int[][]{
                new int[]{xc - 6, xc + 75, xc + 75, xc - 3},
                new int[]{yc - 25, yc - 25, yc - 12, yc - 12},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterLeft1 = transform(topCenterLeft1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterLeft2 = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc - 6},
                new int[]{yc - 39, yc - 45, yc - 25, yc - 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterLeft2 = transform(topCenterLeft2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterRight1 = new int[][]{
                new int[]{xc - 6, xc + 75, xc + 75, xc - 3},
                new int[]{yc + 25, yc + 25, yc + 12, yc + 12},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterRight1 = transform(topCenterRight1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterRight2 = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc - 6},
                new int[]{yc + 39, yc + 45, yc + 25, yc + 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterRight2 = transform(topCenterRight2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterCenter = new int[][]{
                new int[]{xc - 3, xc + 35, xc + 35, xc - 3, xc},
                new int[]{yc - 12, yc - 12, yc + 12, yc + 12, yc},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3},
        };
        scaledTopCenterCenter = transform(topCenterCenter, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});


        topWhite = new int[][]{
                new int[]{xc - 10, xc + 289, xc + 289, xc - 10, xc},
                new int[]{yc + 39, yc + 54, yc - 54, yc - 39, yc},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3},
        };
        scaledTopWhite = transform(topWhite, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

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
        scaledBridgeBase = transform(bridgeBase, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

    }

    private void calBridge() {
        // Top
        bridgeBase1Top = new int[][]{
                new int[]{xc + 150, xc + 260, xc + 260, xc + 150},
                new int[]{yc + 12, yc + 12, yc - 12, yc - 12},
                new int[]{zc + 85, zc + 105, zc + 105, zc + 85},
        };
        scaledBridgeBase1Top = transform(bridgeBase1Top, center, scale, angles, null, null);

        bridgeBase1Front = new int[][]{
                new int[]{scaledTopCenterCenter[0][1], scaledBridgeBase1Top[0][3], scaledBridgeBase1Top[0][0], scaledTopCenterCenter[0][2]},
                new int[]{scaledTopCenterCenter[1][1], scaledBridgeBase1Top[1][3], scaledBridgeBase1Top[1][0], scaledTopCenterCenter[1][2]},
                new int[]{scaledTopCenterCenter[2][1], scaledBridgeBase1Top[2][3], scaledBridgeBase1Top[2][0], scaledTopCenterCenter[2][2]},
        };

        bridgeTowerLeftBase = new int[][]{
                new int[]{xc + 170, xc + 245, xc + 245, xc + 170},
                new int[]{yc - 12, yc - 12, yc - 30, yc - 30},
                new int[]{zc + height + 3 + 53, zc + height + 3 + 53, zc + height + 3 + 53, zc + height + 3 + 53},
        };
        //scaledBridgeTowerLeftBase = transform(bridgeTowerLeftBase, center, scale, angles, null, null);
        scaledBridgeTowerLeftBase = transform(bridgeTowerLeftBase, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeTowerRightBase = new int[][]{
                new int[]{xc + 170, xc + 245, xc + 245, xc + 170},
                new int[]{yc + 12, yc + 12, yc + 30, yc + 30},
                new int[]{zc + height + 3 + 53, zc + height + 3 + 53, zc + height + 3 + 53, zc + height + 3 + 52},
        };
        //scaledBridgeTowerRightBase = transform(bridgeTowerRightBase, center, scale, angles, null, null);
        scaledBridgeTowerRightBase = transform(bridgeTowerRightBase, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});


        int[][] bridgeTowersBaseFrontMid = new int[][]{
                new int[]{xc + 170, xc + 170, 1, 1},
                new int[]{yc - 18, yc + 18, 1, 1},
                new int[]{zc + height + 3 + 53, zc + height + 3 + 53, 1, 1},
        };
        int[][] scaledBridgeTowersBaseFrontMid = transform(bridgeTowersBaseFrontMid, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        /*bridgeTopLeft1 = new int[][]{
                new int[]{scaledTopCenterLeft2a[0][3], scaledBridgeTowersBaseFrontMid[0][0], scaledBridgeTowerLeftBase[0][0], scaledTopCenterLeft1a[0][2]},
                new int[]{scaledTopCenterLeft2a[1][3], scaledBridgeTowersBaseFrontMid[1][0], scaledBridgeTowerLeftBase[1][0], scaledTopCenterLeft1a[1][2]},
                new int[]{scaledTopCenterLeft2a[2][3], scaledBridgeTowersBaseFrontMid[2][0], scaledBridgeTowerLeftBase[2][0], scaledTopCenterLeft1a[2][2]},
        };

        bridgeTopRight1 = new int[][]{
                new int[]{scaledTopCenterRight2a[0][3], scaledBridgeTowersBaseFrontMid[0][1], scaledBridgeTowerRightBase[0][0], scaledTopCenterRight1a[0][2]},
                new int[]{scaledTopCenterRight2a[1][3], scaledBridgeTowersBaseFrontMid[1][1], scaledBridgeTowerRightBase[1][0], scaledTopCenterRight1a[1][2]},
                new int[]{scaledTopCenterRight2a[2][3], scaledBridgeTowersBaseFrontMid[2][1], scaledBridgeTowerRightBase[2][0], scaledTopCenterRight1a[2][2]},
        };



        bridgeTopLeft2 = new int[][]{
                new int[]{scaledTopCenterLeft2a[0][1], scaledBridgeTowerLeftBase[0][3], scaledBridgeTowersBaseFrontMid[0][0], scaledTopCenterLeft2a[0][2]},
                new int[]{scaledTopCenterLeft2a[1][1], scaledBridgeTowerLeftBase[1][3], scaledBridgeTowersBaseFrontMid[1][0], scaledTopCenterLeft2a[1][2]},
                new int[]{scaledTopCenterLeft2a[2][1], scaledBridgeTowerLeftBase[2][3], scaledBridgeTowersBaseFrontMid[2][0], scaledTopCenterLeft2a[2][2]},
        };

        bridgeTopRight2 = new int[][]{
                new int[]{scaledTopCenterRight2a[0][1], scaledBridgeTowerRightBase[0][3], scaledBridgeTowersBaseFrontMid[0][1], scaledTopCenterRight2a[0][2]},
                new int[]{scaledTopCenterRight2a[1][1], scaledBridgeTowerRightBase[1][3], scaledBridgeTowersBaseFrontMid[1][1], scaledTopCenterRight2a[1][2]},
                new int[]{scaledTopCenterRight2a[2][1], scaledBridgeTowerRightBase[2][3], scaledBridgeTowersBaseFrontMid[2][1], scaledTopCenterRight2a[2][2]},
        };*/


        bridgeBase2Front = new int[][]{
                new int[]{scaledTopCenterLeft1[0][1], scaledBridgeTowersBaseFrontMid[0][0], scaledBridgeTowersBaseFrontMid[0][1], scaledTopCenterRight1[0][1]},
                new int[]{scaledTopCenterLeft1[1][1], scaledBridgeTowersBaseFrontMid[1][0], scaledBridgeTowersBaseFrontMid[1][1], scaledTopCenterRight1[1][1]},
                new int[]{scaledTopCenterLeft1[2][1], scaledBridgeTowersBaseFrontMid[2][0], scaledBridgeTowersBaseFrontMid[2][1], scaledTopCenterRight1[2][1]},
        };

        bridgeBase3Front = new int[][]{
                new int[]{scaledTopCenterLeft2[0][1], scaledBridgeTowerLeftBase[0][3], scaledBridgeTowerRightBase[0][3], scaledTopCenterRight2[0][1]},
                new int[]{scaledTopCenterLeft2[1][1], scaledBridgeTowerLeftBase[1][3], scaledBridgeTowerRightBase[1][3], scaledTopCenterRight2[1][1]},
                new int[]{scaledTopCenterLeft2[2][1], scaledBridgeTowerLeftBase[2][3], scaledBridgeTowerRightBase[2][3], scaledTopCenterRight2[2][1]},
        };

        // Back
        bridgeBase3Back = new int[][]{
                new int[]{scaledTopWhite[0][2], scaledBridgeTowerLeftBase[0][2], scaledBridgeTowerRightBase[0][2], scaledTopWhite[0][1]},
                new int[]{scaledTopWhite[1][2], scaledBridgeTowerLeftBase[1][2], scaledBridgeTowerRightBase[1][2], scaledTopWhite[1][1]},
                new int[]{scaledTopWhite[2][2], scaledBridgeTowerLeftBase[2][2], scaledBridgeTowerRightBase[2][2], scaledTopWhite[2][1]},
        };

        bridgeBase1Back = new int[][]{
                new int[]{bridgeBase1Top[0][1], bridgeBase1Top[0][2], bridgeBase1Top[0][2] + 50, bridgeBase1Top[0][1] + 50},
                new int[]{bridgeBase1Top[1][1], bridgeBase1Top[1][2], bridgeBase1Top[1][2], bridgeBase1Top[1][1]},
                new int[]{bridgeBase1Top[2][1], bridgeBase1Top[2][2], bridgeBase1Top[2][2] - 53, bridgeBase1Top[2][1] - 53},
        };

        scaledBridgeBase1Back = transform(bridgeBase1Back, center, scale, angles, null, null);


        // Sides
        bridgeBase1LeftFront = new int[][]{
                new int[]{scaledTopCenter[0][6], scaledBridgeBase1Top[0][3], scaledBridgeTowerLeftBase[0][0], scaledTopCenter[0][7]},
                new int[]{scaledTopCenter[1][6], scaledBridgeBase1Top[1][3], scaledBridgeTowerLeftBase[1][0], scaledTopCenter[1][7]},
                new int[]{scaledTopCenter[2][6], scaledBridgeBase1Top[2][3], scaledBridgeTowerLeftBase[2][0], scaledTopCenter[2][7]},
        };

        bridgeBase1RightFront = new int[][]{
                new int[]{scaledTopCenter[0][5], scaledBridgeBase1Top[0][0], scaledBridgeTowerRightBase[0][0], scaledTopCenter[0][4]},
                new int[]{scaledTopCenter[1][5], scaledBridgeBase1Top[1][0], scaledBridgeTowerRightBase[1][0], scaledTopCenter[1][4]},
                new int[]{scaledTopCenter[2][5], scaledBridgeBase1Top[2][0], scaledBridgeTowerRightBase[2][0], scaledTopCenter[2][4]},
        };

        bridgeBase2Left = new int[][]{
                new int[]{scaledTopCenter[0][8], scaledBridgeTowersBaseFrontMid[0][0], scaledTopCenter[0][9]},
                new int[]{scaledTopCenter[1][8], scaledBridgeTowersBaseFrontMid[1][0], scaledTopCenter[1][9]},
                new int[]{scaledTopCenter[2][8], scaledBridgeTowersBaseFrontMid[2][0], scaledTopCenter[2][9]},
        };

        bridgeBase2Right = new int[][]{
                new int[]{scaledTopCenter[0][3], scaledBridgeTowersBaseFrontMid[0][1], scaledTopCenter[0][2]},
                new int[]{scaledTopCenter[1][3], scaledBridgeTowersBaseFrontMid[1][1], scaledTopCenter[1][2]},
                new int[]{scaledTopCenter[2][3], scaledBridgeTowersBaseFrontMid[2][1], scaledTopCenter[2][2]},
        };

        bridgeBase3Left = new int[][]{
                new int[]{bridgeBase3Front[0][0], scaledBridgeTowerLeftBase[0][3], scaledBridgeTowerLeftBase[0][2], scaledTopWhite[0][2]},
                new int[]{bridgeBase3Front[1][0], scaledBridgeTowerLeftBase[1][3], scaledBridgeTowerLeftBase[1][2], scaledTopWhite[1][2]},
                new int[]{bridgeBase3Front[2][0], scaledBridgeTowerLeftBase[2][3], scaledBridgeTowerLeftBase[2][2], scaledTopWhite[2][2]},
        };

        bridgeBase3Right = new int[][]{
                new int[]{bridgeBase3Front[0][3], scaledBridgeTowerRightBase[0][3], scaledBridgeTowerRightBase[0][2], scaledTopWhite[0][1]},
                new int[]{bridgeBase3Front[1][3], scaledBridgeTowerRightBase[1][3], scaledBridgeTowerRightBase[1][2], scaledTopWhite[1][1]},
                new int[]{bridgeBase3Front[2][3], scaledBridgeTowerRightBase[2][3], scaledBridgeTowerRightBase[2][2], scaledTopWhite[2][1]},
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
        scaledBottomLeft = transform(bottomLeftRot1, center, scale, angles, new double[]{-xBottomInclination}, new int[][][]{bottomXAxis});

        // ----- Right -----
        bottomRight = new int[][]{
                new int[]{xc - 430, xc - 432, xc - 9, xc + 54, xc + 129, xc + 244, xc + 289, xc + 356,},
                new int[]{yc, yc + 32, yc + 138, yc + 92, yc + 107, yc + 208, yc + 209, yc,},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomRight = transform(bottomRight, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        // ----- Center -----
        bottomRedLeft = new int[][]{
                new int[]{xc - 430, xc - 431, xc - 310, xc - 300, xc - 270, xc - 260, xc - 160, xc - 150, xc - 90, xc - 80, xc + 353, xc + 356},
                new int[]{yc, yc - 10, yc - 10, yc - 17, yc - 17, yc - 10, yc - 10, yc - 17, yc - 17, yc - 10, yc - 10, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height},
        };
        scaledBottomRedLeft = transform(bottomRedLeft, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomRedRight = new int[][]{
                new int[]{xc - 430, xc - 431, xc - 310, xc - 300, xc - 270, xc - 260, xc - 160, xc - 150, xc - 90, xc - 80, xc + 353, xc + 356},
                new int[]{yc, yc + 10, yc + 10, yc + 17, yc + 17, yc + 10, yc + 10, yc + 17, yc + 17, yc + 10, yc + 10, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height},
        };
        scaledBottomRedRight = transform(bottomRedRight, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        // ----- Hangar -----
        bottomHangarLeft = new int[][]{
                new int[]{xc - 7, xc - 8, xc + 81, xc + 81},
                new int[]{yc, yc - 25, yc - 25, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomHangarLeft = transform(bottomHangarLeft, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomHangarRight = new int[][]{
                new int[]{xc - 7, xc - 8, xc + 81, xc + 81},
                new int[]{yc, yc + 25, yc + 25, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomHangarRight = transform(bottomHangarRight, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomLeftUp = new int[][]{
                new int[]{bottomLeft[0][6], bottomLeft[0][6], xc + 356},
                new int[]{yc, yc - 209, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height},
        };
        scaledBottomLeftUp = transform(bottomLeftUp, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomRightUp = new int[][]{
                new int[]{bottomRight[0][6], bottomRight[0][6], xc + 356},
                new int[]{yc, yc + 209, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height},
        };
        scaledBottomRightUp = transform(bottomRightUp, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

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
    }


    // Draw
    private void drawTopSides(int[] director, String projection, boolean fill, BufferedImage buffer) {
        surface(scaledTopLeft1, director, projection, 1, false, null, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        surface(scaledTopLeft2, director, projection, -1, false, null, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        surface(scaledTopLeftFlap, director, projection, 1, false, null, Color.gray, fill ? new Color(162, 162, 162) : null, buffer);

        surface(scaledTopRight1, director, projection, -1, false, null, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        surface(scaledTopRight2, director, projection, 1, false, null, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        surface(scaledTopRightFlap, director, projection, -1, false, null, Color.gray, fill ? new Color(162, 162, 162) : null, buffer);

    }

    private void drawTopCenter(int[] director, String projection, boolean fill, BufferedImage buffer) {
        surface(scaledTopRed, director, projection, -1, false, null, new Color(120, 80, 80), fill ? new Color(143, 67, 76) : null, buffer);

        /*surface(scaledTopCenterLeft1, director, projection, 1, false, null, new Color(181, 181, 181), fill ? new Color(181, 181, 181) : null, buffer);
        surface(scaledTopCenterLeft2, director, projection, 1, false, null, new Color(181, 181, 181), fill ? new Color(181, 181, 181) : null, buffer);

        surface(scaledTopCenterRight1, director, projection, -1, false, null, new Color(181, 181, 181), fill ? new Color(181, 181, 181) : null, buffer);
        surface(scaledTopCenterRight2, director, projection, -1, false, null, new Color(181, 181, 181), fill ? new Color(181, 181, 181) : null, buffer);

*/

       /* surface(scaledTopCenterLeft1, director, projection, 1, false, null, Color.green, fill ? new Color(181, 181, 181) : null, buffer);
        surface(scaledTopCenterLeft2, director, projection, 1, false, null, Color.red, fill ? new Color(181, 181, 181) : null, buffer);

        surface(scaledTopCenterRight1, director, projection, -1, false, null, Color.green, fill ? new Color(181, 181, 181) : null, buffer);
        surface(scaledTopCenterRight2, director, projection, -1, false, null, Color.red, fill ? new Color(181, 181, 181) : null, buffer);

        surface(scaledTopCenterCenter, director, projection, 1, false, null, Color.blue, null, buffer);
*/
        surface(scaledTopWhite, director, projection, -1, false, null, Color.gray, fill ? new Color(170, 170, 170) : null, buffer);
        //surface(scaledTopCenter, director, projection, -1, false, null, Color.gray, null, buffer);

    }

    private void drawBridge(int[] director, String projection, boolean fill, BufferedImage buffer) {
        ArrayList<Surface> surfaces = new ArrayList<>();
        surfaces.add(new Surface(bridgeBase3Front, 1, Color.gray, fill ? new Color(180, 183, 178) : null));
        surfaces.add(new Surface(bridgeBase2Front, 1, Color.gray, fill ? new Color(178, 183, 181) : null));
        surfaces.add(new Surface(bridgeBase1Front, 1, Color.gray, fill ? new Color(180, 182, 181) : null));

        surfaces.add(new Surface(bridgeBase1LeftFront, -1, Color.gray, fill ? new Color(160, 161, 159) : null));
        surfaces.add(new Surface(bridgeBase2Left, -1, Color.gray, fill ? new Color(162, 161, 159) : null));
        surfaces.add(new Surface(bridgeBase3Left, -1, Color.gray, fill ? new Color(163, 161, 159) : null));

        surfaces.add(new Surface(bridgeBase1RightFront, 1, Color.gray, fill ? new Color(158, 161, 159) : null));
        surfaces.add(new Surface(bridgeBase2Right, 1, Color.gray, fill ? new Color(162, 161, 159) : null));
        surfaces.add(new Surface(bridgeBase3Right, 1, Color.gray, fill ? new Color(163, 161, 159) : null));

        surfaces.add(new Surface(bridgeBase3Back, -1, Color.gray, fill ? new Color(180, 182, 183) : null));
        surfaces.add(new Surface(scaledBridgeBase1Back, 1, Color.gray, fill ? new Color(178, 182, 183) : null));

        surfaces.add(new Surface(scaledBridgeBase1Top, -1, Color.gray, fill ? new Color(180, 182, 181) : null));
        surfaces.add(new Surface(scaledBridgeBase1Top, -1, Color.gray, fill ? new Color(180, 182, 181) : null));

        sortSurfaces(surfaces, director, projection, buffer);
       /* surface(bridgeBase3Front, director, projection, 1, false, null, Color.gray, fill ? Color.black : null, buffer);
        surface(bridgeBase2Front, director, projection, 1, false, null, Color.gray, fill ? Color.red : null, buffer);

        // Sides
        surface(bridgeBase1LeftFront, director, projection, -1, false, null, Color.gray, fill ? new Color(160, 161, 159) : null, buffer);
        surface(bridgeBase2Left, director, projection, -1, false, null, Color.gray, fill ? new Color(162, 161, 159) : null, buffer);
        surface(bridgeBase3Left, director, projection, -1, false, null, Color.gray, fill ? new Color(162, 161, 158) : null, buffer);

        surface(bridgeBase1RightFront, director, projection, 1, false, null, Color.gray, fill ? new Color(160, 161, 161) : null, buffer);
        surface(bridgeBase2Right, director, projection, 1, false, null, Color.gray, fill ? new Color(162, 161, 161) : null, buffer);
        surface(bridgeBase3Right, director, projection, 1, false, null, Color.gray, fill ? new Color(162, 161, 159) : null, buffer);

        // Center
        surface(bridgeBase1Front, director, projection, 1, false, null, Color.gray, fill ? new Color(178, 182, 179) : null, buffer);

        surface(bridgeBase3Back, director, projection, -1, false, null, Color.gray, fill ? new Color(180, 182, 183) : null, buffer);
        surface(scaledBridgeBase1Back, director, projection, 1, false, null, Color.gray, fill ? new Color(180, 177, 183) : null, buffer);
        surface(scaledBridgeBase1Top, director, projection, -1, false, null, Color.gray, fill ? new Color(178, 182, 177) : null, buffer);
*/
        // Towers
        surface(scaledBridgeTowerLeftBase, director, projection, -1, false, null, Color.red, null, buffer);
        surface(scaledBridgeTowerRightBase, director, projection, 1, false, null, Color.red, null, buffer);
    }

    private void drawBottom(int[] director, String projection, boolean fill, BufferedImage buffer) {
        surface(scaledBottomLeft, director, projection, -1, false, null, Color.gray, fill ? new Color(180, 180, 180) : null, buffer);
        surface(scaledBottomRight, director, projection, 1, false, null, Color.gray, fill ? new Color(180, 180, 180) : null, buffer);


        surface(scaledBottomRedLeft, director, projection, -1, false, null, new Color(116, 53, 60), fill ? new Color(116, 53, 60) : null, buffer);
        surface(scaledBottomRedRight, director, projection, 1, false, null, new Color(116, 53, 60), fill ? new Color(116, 53, 60) : null, buffer);

        surface(scaledBottomHangarLeft, director, projection, -1, false, null, Color.black, Color.black, buffer);
        surface(scaledBottomHangarRight, director, projection, 1, false, null, Color.black, Color.black, buffer);

    }

    private void drawSides(int[] director, String projection, boolean fill, BufferedImage buffer) {
        ArrayList<Surface> surfaces = new ArrayList<>();

        surfaces.add(new Surface(left1, -1, Color.gray, fill ? new Color(100, 100, 100) : null));
        surfaces.add(new Surface(left2, -1, Color.gray, fill ? new Color(101, 100, 100) : null));
        surfaces.add(new Surface(left3, -1, Color.gray, fill ? new Color(100, 101, 100) : null));
        surfaces.add(new Surface(left4, -1, Color.gray, fill ? new Color(100, 100, 101) : null));
        surfaces.add(new Surface(left5, -1, Color.gray, fill ? new Color(101, 101, 100) : null));

        surfaces.add(new Surface(right1, 1, Color.gray, fill ? new Color(100, 100, 100) : null));
        surfaces.add(new Surface(right2, 1, Color.gray, fill ? new Color(101, 100, 100) : null));
        surfaces.add(new Surface(right3, 1, Color.gray, fill ? new Color(100, 101, 100) : null));
        surfaces.add(new Surface(right4, 1, Color.gray, fill ? new Color(100, 100, 101) : null));
        surfaces.add(new Surface(right5, 1, Color.gray, fill ? new Color(101, 101, 100) : null));

        surfaces.add(new Surface(front, 1, Color.gray, fill ? new Color(101, 101, 100) : null));

        surfaces.add(new Surface(back, 1, Color.gray, fill ? new Color(91, 91, 90) : null));

        //surfaces.add(new Surface(scaledBottomLeftUp, 1, Color.gray, fill ? new Color(90, 91, 90) : null));
        //surfaces.add(new Surface(scaledBottomRightUp, -1, Color.gray, fill ? new Color(90, 91, 90) : null));

        sortSurfaces(surfaces, director, projection, buffer);
    }


    // Utils
    private int[][] rotate(int[][] face, double[] angles) {
        int[] xAxis1 = new int[]{xc, yc, zc};
        int[] xAxis2 = new int[]{x0, yc, zc};

        int[][] rotatedXFace = rotateAroundLine(face[0], face[1], face[2], xAxis1, xAxis2, angles[0]);

        int[][] p0 = rotateAroundLine(new int[]{x0, xc, xc}, new int[]{yc, y0, yc}, new int[]{zc, zc, z0}, xAxis1, xAxis2, angles[0]);

        int[] yAxis1 = new int[]{xc, yc, zc};
        int[] yAxis2 = new int[]{p0[0][1], p0[1][1], p0[2][1]};

        int[][] rotatedYFace = rotateAroundLine(rotatedXFace[0], rotatedXFace[1], rotatedXFace[2], yAxis1, yAxis2, angles[1]);

        p0 = rotateAroundLine(p0[0], p0[1], p0[2], xAxis1, xAxis2, angles[1]);

        int[] zAxis1 = new int[]{xc, yc, zc};
        int[] zAxis2 = new int[]{p0[0][2], p0[1][2], p0[2][2]};

        int[][] rotatedZFace = rotateAroundLine(rotatedYFace[0], rotatedYFace[1], rotatedYFace[2], zAxis1, zAxis2, angles[2]);

        return rotatedZFace;

    }

    public int[][] transform(int[][] points, int[] center, double scale, double[] angles, double[] anglesAx, int[][][] axis) {
        int[][] pointsRot = new int[points.length][points[0].length];

        for (int i = 0; i < points.length; i++) {
            pointsRot[i] = Arrays.copyOf(points[i], points[i].length);
        }

        if (anglesAx != null) {
            for (int i = 0; i < anglesAx.length; i++) {
                pointsRot = rotateAroundLine(pointsRot[0], pointsRot[1], pointsRot[2], axis[i][0], axis[i][1], anglesAx[i]);
            }
        }

        int[][] rotatedPoints = rotate(pointsRot, angles);
        int[][] scaledPoints = scale3D(rotatedPoints[0], rotatedPoints[1], rotatedPoints[2], center[0], center[1], center[2], false, scale, scale, scale);

        return scaledPoints;
    }

    public void sortSurfaces(ArrayList<Surface> surfaces, int[] director, String projection, BufferedImage buffer) {
        int[][] centroids = new int[surfaces.size()][2];

        for(int i = 0; i < surfaces.size(); i++) {
            //int[] centroid = Utils.calCentroid(surfaces.get(i).points);
            //centroids[i] = new int[]{i, centroid[2]};
            centroids[i] = new int[]{i, Utils.findMin(surfaces.get(i).points[2])};
        }

       /* System.out.println(centroids[0][1]);
        System.out.println(centroids[3][1]);*/
       /* Arrays.sort(centroids, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });*/

        //Arrays.sort(centroids, Comparator.comparingInt(a -> a[1]));

        //System.out.println("------------");
        for(int i = 0; i < surfaces.size(); i++) {
            int index = centroids[i][0];
            //System.out.println(index);
            //System.out.println(centroids[i][1]);
            surfaces.get(index).draw(director, projection, buffer);
        }

    }


}
