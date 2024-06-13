package models;

import graphics.Transformations;

import static graphics.Draw.*;
import static graphics.Draw3d.*;
import static graphics.Transformations.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Venator {
    public int xc;
    public int yc;
    public int zc;

    public int[] center;

    public int x0;
    public int y0;
    public int z0;


    // ---------- Sides ----------
    public int[][] front;
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

    // Top center
    int[][] topCenterBase;
    int[][] scaledTopCenterBase;

    int[][] topCenter;
    int[][] scaledTopCenter;

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

    int[][] bottomRight;
    int[][] scaledBottomRight;

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
    int[][] bridgeTopCenter1;
    int[][] scaledBridgeTopCenter1;
    int[][] bridgeTopCenter2;
    int[][] scaledBridgeTopCenter2;

    int[][] bridgeTopLeft1;
    int[][] scaledBridgeTopLeft1;
    int[][] bridgeTopLeft2;
    int[][] scaledBridgeTopLeft2;

    int[][] bridgeTopRight1;
    int[][] scaledBridgeTopRight1;
    int[][] bridgeTopRight2;
    int[][] scaledBridgeTopRight2;

    int[][] bridgeTopCenterFrontLeft;
    int[][] bridgeTopCenterFrontRight;

    int[][] bridgeFrontLeft1Side;
    int[][] bridgeFrontLeft2Side;

    int[][] bridgeFrontRight1Side;
    int[][] bridgeFrontRight2Side;

    int[][] bridgeTowerBaseLeft;
    int[][] scaledBridgeTowerBaseLeft;

    int[][] bridgeTowerBaseRight;
    int[][] scaledBridgeTowerBaseRight;

    public Venator(int xc, int yc, int zc, double scale, double[] angles) {
        this.xc = xc;
        this.yc = yc;
        this.zc = zc;
        this.center = new int[]{xc, yc, zc};

        this.x0 = xc + 500;
        this.y0 = yc + 500;
        this.z0 = zc + 500;

        this.height = 10;
        this.yInclination = 0.03;
        this.xTopInclination = 0.2;
        this.xBottomInclination = 0.13;

        this.angles = angles;
        this.scale = scale;
    }


    public void draw(int[] director, String projection, BufferedImage buffer, double ang) {
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
        //drawSides(director, projection, buffer);

        drawTopSides(director, projection, false, buffer);

        drawTopCenter(director, projection, false, buffer);

        drawBridge(director, projection, true, buffer);

        drawBottom(director, projection, true, buffer);

        //drawAxis(director, projection, buffer);
    }

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

        /*topCenterLeft1 = new int[][]{
                new int[]{xc - 10, xc + 75, xc + 75, xc + 35, xc + 35, xc},
                new int[]{yc - 39, yc - 43, yc - 12, yc - 12, yc, yc},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };

        int[][] topCenterLeft1Rot1 = rotateAroundLine(topCenterLeft1[0], topCenterLeft1[1], topCenterLeft1[2], topYElevatedAxis[0], topYElevatedAxis[1], yInclination + 0.004);

        int[][] rotatedTopCenterLeft1 = rotate(topCenterLeft1Rot1, angles);
        scaledTopCenterLeft1 = scale3D(rotatedTopCenterLeft1[0], rotatedTopCenterLeft1[1], rotatedTopCenterLeft1[2], xc, yc, zc, false, scale, scale, scale);

        topCenterLeft2 = new int[][]{
                new int[]{xc + 75, xc + 115, xc + 115, xc + 75,},
                new int[]{yc - 43, yc - 45, yc - 25, yc - 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };

        int[][] topCenterLeft2Rot1 = rotateAroundLine(topCenterLeft2[0], topCenterLeft2[1], topCenterLeft2[2], topYElevatedAxis[0], topYElevatedAxis[1], yInclination + 0.004);

        int[][] rotatedTopCenterLeft2 = rotate(topCenterLeft2Rot1, angles);
        scaledTopCenterLeft2 = scale3D(rotatedTopCenterLeft2[0], rotatedTopCenterLeft2[1], rotatedTopCenterLeft2[2], xc, yc, zc, false, scale, scale, scale);


        topCenterRight1 = new int[][]{
                new int[]{xc - 10, xc + 75, xc + 75, xc + 35, xc + 35, xc},
                new int[]{yc + 39, yc + 43, yc + 12, yc + 12, yc, yc},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };

        int[][] topCenterRight1Rot1 = rotateAroundLine(topCenterRight1[0], topCenterRight1[1], topCenterRight1[2], topYElevatedAxis[0], topYElevatedAxis[1], yInclination + 0.004);

        int[][] rotatedTopCenterRight1 = rotate(topCenterRight1Rot1, angles);
        scaledTopCenterRight1 = scale3D(rotatedTopCenterRight1[0], rotatedTopCenterRight1[1], rotatedTopCenterRight1[2], xc, yc, zc, false, scale, scale, scale);

        topCenterRight2 = new int[][]{
                new int[]{xc + 75, xc + 115, xc + 115, xc + 75,},
                new int[]{yc + 43, yc + 45, yc + 25, yc + 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };

        int[][] topCenterRight2Rot1 = rotateAroundLine(topCenterRight2[0], topCenterRight2[1], topCenterRight2[2], topYElevatedAxis[0], topYElevatedAxis[1], yInclination + 0.004);

        int[][] rotatedTopCenterRight2 = rotate(topCenterRight2Rot1, angles);
        scaledTopCenterRight2 = scale3D(rotatedTopCenterRight2[0], rotatedTopCenterRight2[1], rotatedTopCenterRight2[2], xc, yc, zc, false, scale, scale, scale);
*/


        topCenterLeft1 = new int[][]{
                new int[]{xc - 10, xc + 75, xc + 75, xc + 35, xc + 35, xc},
                new int[]{yc - 39, yc - 43, yc - 12, yc - 12, yc, yc},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterLeft1 = transform(topCenterLeft1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterLeft2 = new int[][]{
                new int[]{xc + 75, xc + 115, xc + 115, xc + 75,},
                new int[]{yc - 43, yc - 45, yc - 25, yc - 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterLeft2 = transform(topCenterLeft2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterRight1 = new int[][]{
                new int[]{xc - 10, xc + 75, xc + 75, xc + 35, xc + 35, xc},
                new int[]{yc + 39, yc + 43, yc + 12, yc + 12, yc, yc},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterRight1 = transform(topCenterRight1, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        topCenterRight2 = new int[][]{
                new int[]{xc + 75, xc + 115, xc + 115, xc + 75,},
                new int[]{yc + 43, yc + 45, yc + 25, yc + 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledTopCenterRight2 = transform(topCenterRight2, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

        bridgeBase = new int[][]{
                new int[]{xc + 75, xc + 115, xc + 115, xc + 75,},
                new int[]{yc + 43, yc + 45, yc + 25, yc + 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };
        scaledBridgeBase = transform(bridgeBase, center, scale, angles, new double[]{yInclination + 0.004}, new int[][][]{topYElevatedAxis});

    }

    private void calBridge() {
        bridgeTopCenter2 = new int[][]{
                new int[]{xc + 150, xc + 270, xc + 270, xc + 150},
                new int[]{yc + 12, yc + 12, yc - 12, yc - 12},
                new int[]{zc + 85, zc + 100, zc + 100, zc + 85},
        };
        scaledBridgeTopCenter2 = transform(bridgeTopCenter2, center, scale, angles, null, null);

        bridgeTopCenter1 = new int[][]{
                new int[]{scaledTopCenterLeft1[0][3], scaledBridgeTopCenter2[0][3], scaledBridgeTopCenter2[0][0], scaledTopCenterRight1[0][3]},
                new int[]{scaledTopCenterLeft1[1][3], scaledBridgeTopCenter2[1][3], scaledBridgeTopCenter2[1][0], scaledTopCenterRight1[1][3]},
                new int[]{scaledTopCenterLeft1[2][3], scaledBridgeTopCenter2[2][3], scaledBridgeTopCenter2[2][0], scaledTopCenterRight1[2][3]},
        };

        bridgeTowerBaseLeft = new int[][]{
                new int[]{xc + 170, xc + 245, xc + 245, xc + 170},
                new int[]{yc - 12, yc - 12, yc - 30, yc - 30},
                new int[]{zc + 90, zc + 90, zc + 90, zc + 90},
        };
        scaledBridgeTowerBaseLeft = transform(bridgeTowerBaseLeft, center, scale, angles, null, null);

        bridgeTowerBaseRight = new int[][]{
                new int[]{xc + 170, xc + 245, xc + 245, xc + 170},
                new int[]{yc + 12, yc + 12, yc + 30, yc + 30},
                new int[]{zc + 90, zc + 90, zc + 90, zc + 90},
        };
        scaledBridgeTowerBaseRight = transform(bridgeTowerBaseRight, center, scale, angles, null, null);


        int[][] bridgeTowersBaseFrontMid = new int[][]{
                new int[]{xc + 170, xc + 170, 1, 1},
                new int[]{yc - 18, yc + 18, 1, 1},
                new int[]{zc + 90, zc + 90, 1, 1},
        };
        int[][] scaledBridgeTowersBaseFrontMid = transform(bridgeTowersBaseFrontMid, center, scale, angles, null, null);

        bridgeTopLeft1 = new int[][]{
                new int[]{scaledTopCenterLeft2[0][3], scaledBridgeTowersBaseFrontMid[0][0], scaledBridgeTowerBaseLeft[0][0], scaledTopCenterLeft1[0][2]},
                new int[]{scaledTopCenterLeft2[1][3], scaledBridgeTowersBaseFrontMid[1][0], scaledBridgeTowerBaseLeft[1][0], scaledTopCenterLeft1[1][2]},
                new int[]{scaledTopCenterLeft2[2][3], scaledBridgeTowersBaseFrontMid[2][0], scaledBridgeTowerBaseLeft[2][0], scaledTopCenterLeft1[2][2]},
        };

        bridgeTopLeft2 = new int[][]{
                new int[]{scaledTopCenterLeft2[0][1], scaledBridgeTowerBaseLeft[0][3], scaledBridgeTowersBaseFrontMid[0][0], scaledTopCenterLeft2[0][2]},
                new int[]{scaledTopCenterLeft2[1][1], scaledBridgeTowerBaseLeft[1][3], scaledBridgeTowersBaseFrontMid[1][0], scaledTopCenterLeft2[1][2]},
                new int[]{scaledTopCenterLeft2[2][1], scaledBridgeTowerBaseLeft[2][3], scaledBridgeTowersBaseFrontMid[2][0], scaledTopCenterLeft2[2][2]},
        };

        bridgeTopRight1 = new int[][]{
                new int[]{scaledTopCenterRight2[0][3], scaledBridgeTowersBaseFrontMid[0][1], scaledBridgeTowerBaseRight[0][0], scaledTopCenterRight1[0][2]},
                new int[]{scaledTopCenterRight2[1][3], scaledBridgeTowersBaseFrontMid[1][1], scaledBridgeTowerBaseRight[1][0], scaledTopCenterRight1[1][2]},
                new int[]{scaledTopCenterRight2[2][3], scaledBridgeTowersBaseFrontMid[2][1], scaledBridgeTowerBaseRight[2][0], scaledTopCenterRight1[2][2]},
        };

        bridgeTopRight2 = new int[][]{
                new int[]{scaledTopCenterRight2[0][1], scaledBridgeTowerBaseRight[0][3], scaledBridgeTowersBaseFrontMid[0][1], scaledTopCenterRight2[0][2]},
                new int[]{scaledTopCenterRight2[1][1], scaledBridgeTowerBaseRight[1][3], scaledBridgeTowersBaseFrontMid[1][1], scaledTopCenterRight2[1][2]},
                new int[]{scaledTopCenterRight2[2][1], scaledBridgeTowerBaseRight[2][3], scaledBridgeTowersBaseFrontMid[2][1], scaledTopCenterRight2[2][2]},
        };

        bridgeTopCenterFrontLeft = new int[][]{
                new int[]{scaledTopCenterLeft1[0][3], scaledBridgeTopCenter2[0][3], scaledBridgeTowerBaseLeft[0][0], scaledTopCenterLeft1[0][2]},
                new int[]{scaledTopCenterLeft1[1][3], scaledBridgeTopCenter2[1][3], scaledBridgeTowerBaseLeft[1][0], scaledTopCenterLeft1[1][2]},
                new int[]{scaledTopCenterLeft1[2][3], scaledBridgeTopCenter2[2][3], scaledBridgeTowerBaseLeft[2][0], scaledTopCenterLeft1[2][2]},
        };

        bridgeTopCenterFrontRight = new int[][]{
                new int[]{scaledTopCenterRight1[0][3], scaledBridgeTopCenter2[0][0], scaledBridgeTowerBaseRight[0][0], scaledTopCenterRight1[0][2]},
                new int[]{scaledTopCenterRight1[1][3], scaledBridgeTopCenter2[1][0], scaledBridgeTowerBaseRight[1][0], scaledTopCenterRight1[1][2]},
                new int[]{scaledTopCenterRight1[2][3], scaledBridgeTopCenter2[2][0], scaledBridgeTowerBaseRight[2][0], scaledTopCenterRight1[2][2]},
        };

        bridgeFrontLeft1Side = new int[][]{
                new int[]{scaledTopCenterLeft2[0][3], scaledBridgeTowersBaseFrontMid[0][0], scaledTopCenterLeft2[0][2]},
                new int[]{scaledTopCenterLeft2[1][3], scaledBridgeTowersBaseFrontMid[1][0], scaledTopCenterLeft2[1][2]},
                new int[]{scaledTopCenterLeft2[2][3], scaledBridgeTowersBaseFrontMid[2][0], scaledTopCenterLeft2[2][2]},
        };

        bridgeFrontRight1Side = new int[][]{
                new int[]{scaledTopCenterRight2[0][3], scaledBridgeTowersBaseFrontMid[0][1], scaledTopCenterRight2[0][2]},
                new int[]{scaledTopCenterRight2[1][3], scaledBridgeTowersBaseFrontMid[1][1], scaledTopCenterRight2[1][2]},
                new int[]{scaledTopCenterRight2[2][3], scaledBridgeTowersBaseFrontMid[2][1], scaledTopCenterRight2[2][2]},
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
                new int[]{xc - 9, xc - 8, xc + 81, xc + 81},
                new int[]{yc, yc - 25, yc - 25, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomHangarLeft = transform(bottomHangarLeft, center, scale, angles, new double[]{-yInclination, -xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});

        bottomHangarRight = new int[][]{
                new int[]{xc - 9, xc - 8, xc + 81, xc + 81},
                new int[]{yc, yc + 25, yc + 25, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height,},
        };
        scaledBottomHangarRight = transform(bottomHangarRight, center, scale, angles, new double[]{-yInclination, xBottomInclination}, new int[][][]{bottomYAxis, bottomXAxis});
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
                new int[]{scaledTopLeft[0][5], scaledTopLeft[0][6], scaledBottomLeft[0][6], scaledBottomLeft[0][5]},
                new int[]{scaledTopLeft[1][5], scaledTopLeft[1][6], scaledBottomLeft[1][6], scaledBottomLeft[1][5]},
                new int[]{scaledTopLeft[2][5], scaledTopLeft[2][6], scaledBottomLeft[2][6], scaledBottomLeft[2][5]},
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
                new int[]{scaledTopRight[0][5], scaledTopRight[0][6], scaledBottomRight[0][6], scaledBottomRight[0][5]},
                new int[]{scaledTopRight[1][5], scaledTopRight[1][6], scaledBottomRight[1][6], scaledBottomRight[1][5]},
                new int[]{scaledTopRight[2][5], scaledTopRight[2][6], scaledBottomRight[2][6], scaledBottomRight[2][5]},
        };

        front = new int[][]{
                new int[]{scaledTopLeft[0][1], scaledTopLeft[0][0], scaledTopRight[0][0], scaledTopRight[0][1], scaledBottomRight[0][1], scaledBottomRight[0][0], scaledBottomLeft[0][1]},
                new int[]{scaledTopLeft[1][1], scaledTopLeft[1][0], scaledTopRight[1][0], scaledTopRight[1][1], scaledBottomRight[1][1], scaledBottomRight[1][0], scaledBottomLeft[1][1]},
                new int[]{scaledTopLeft[2][1], scaledTopLeft[2][0], scaledTopRight[2][0], scaledTopRight[2][1], scaledBottomRight[2][1], scaledBottomRight[2][0], scaledBottomLeft[2][1]},
        };
    }


    // Draw
    private void drawTopSides(int[] director, String projection, boolean fill, BufferedImage buffer) {
        surface(scaledTopLeft1, director, projection, 1, false, null, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        surface(scaledTopLeft2, director, projection, -1, false, null, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);

        surface(scaledTopRight1, director, projection, -1, false, null, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);
        surface(scaledTopRight2, director, projection, 1, false, null, new Color(180, 180, 180), fill ? new Color(180, 180, 180) : null, buffer);

    }

    private void drawTopCenter(int[] director, String projection, boolean fill, BufferedImage buffer) {
        surface(scaledTopRed, director, projection, -1, false, null, new Color(120, 80, 80),  fill ? new Color(143, 67, 76) : null, buffer);

        surface(scaledTopCenterLeft1, director, projection, 1, false, null, new Color(181, 181, 181), fill ? new Color(181, 181, 181) : null, buffer);
        surface(scaledTopCenterLeft2, director, projection, 1, false, null, new Color(181, 181, 181), fill ? new Color(181, 181, 181) : null, buffer);

        surface(scaledTopCenterRight1, director, projection, -1, false, null, new Color(181, 181, 181), fill ? new Color(181, 181, 181) : null, buffer);
        surface(scaledTopCenterRight2, director, projection, -1, false, null, new Color(181, 181, 181), fill ? new Color(181, 181, 181) : null, buffer);

        surface(scaledTopCenter, director, projection, -1, false, null, Color.gray, null, buffer);

    }

    private void drawBridge(int[] director, String projection, boolean fill, BufferedImage buffer) {
        if (angles[2] > 0 && angles[2] < Math.PI / 2 || angles[2] > 3 * Math.PI / 2 && angles[2] < 2 * Math.PI) {
            surface(bridgeTopLeft2, director, projection, 1, false, null, Color.gray, fill ? new Color(181, 180, 181) : null, buffer);
            surface(bridgeTopLeft1, director, projection, 1, false, null, Color.gray, fill ? new Color(181, 179, 181) : null, buffer);

        } else {
            surface(bridgeTopRight2, director, projection, -1, false, null, Color.gray, fill ? new Color(181, 181, 180) : null, buffer);
            surface(bridgeTopRight1, director, projection, -1, false, null, Color.gray, fill ? new Color(179, 179, 181) : null, buffer);
        }

        // Sides
        surface(bridgeTopCenterFrontLeft, director, projection, -1, false, null, Color.gray, fill ? new Color(160, 161, 159) : null, buffer);
        surface(bridgeFrontLeft1Side, director, projection, -1, false, null, Color.gray, fill ? new Color(162, 161, 159) : null, buffer);

        surface(bridgeTopCenterFrontRight, director, projection, 1, false, null, Color.gray, fill ? new Color(160, 161, 161) : null, buffer);
        surface(bridgeFrontRight1Side, director, projection, 1, false, null, Color.gray, fill ? new Color(162, 161, 161) : null, buffer);

        surface(scaledBridgeTowerBaseLeft, director, projection, -1, false, null, Color.red, null, buffer);
        surface(scaledBridgeTowerBaseRight, director, projection, 1, false, null, Color.red, null, buffer);

        if (angles[2] > Math.PI / 2 && angles[2] < 3 * Math.PI / 2) {
            surface(bridgeTopLeft2, director, projection, 1, false, null, Color.gray, fill ? new Color(181, 180, 181) : null, buffer);
            surface(bridgeTopLeft1, director, projection, 1, false, null, Color.gray, fill ? new Color(181, 179, 181) : null, buffer);

        } else {
            surface(bridgeTopRight2, director, projection, -1, false, null, Color.gray, fill ? new Color(181, 181, 180) : null, buffer);
            surface(bridgeTopRight1, director, projection, -1, false, null, Color.gray, fill ? new Color(179, 179, 181) : null, buffer);
        }

        // Center
        surface(bridgeTopCenter1, director, projection, 1, false, null, Color.gray, fill ? new Color(180, 182, 181) : null, buffer);
        surface(scaledBridgeTopCenter2, director, projection, -1, false, null, Color.gray, fill ? new Color(180, 182, 181) : null, buffer);

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
        if (angles[2] > Math.PI && angles[2] < 2 * Math.PI) {
            surface(left5, director, projection, -1, false, null, Color.gray, fill ? new Color(100, 100, 100) : null, buffer);
            surface(left4, director, projection, -1, false, null, Color.gray, fill ? new Color(101, 100, 100) : null, buffer);
            surface(left3, director, projection, -1, false, null, Color.gray, fill ? new Color(100, 101, 100) : null, buffer);
            surface(left2, director, projection, -1, false, null, Color.gray, fill ? new Color(100, 100, 101) : null, buffer);
            surface(left1, director, projection, -1, false, null, Color.gray, fill ? new Color(101, 101, 100) : null, buffer);

            surface(right5, director, projection, 1, false, null, Color.gray, fill ? new Color(100, 100, 100) : null, buffer);
            surface(right4, director, projection, 1, false, null, Color.gray, fill ? new Color(101, 100, 100) : null, buffer);
            surface(right3, director, projection, 1, false, null, Color.gray, fill ? new Color(100, 101, 100) : null, buffer);
            surface(right2, director, projection, 1, false, null, Color.gray, fill ? new Color(100, 100, 101) : null, buffer);
            surface(right1, director, projection, 1, false, null, Color.gray, fill ? new Color(101, 101, 100) : null, buffer);

            surface(front, director, projection, 1, false, null, Color.gray, fill ? new Color(101, 101, 101) : null, buffer);
        } else {
            surface(front, director, projection, 1, false, null, Color.gray, fill ? new Color(101, 101, 101) : null, buffer);

            surface(left1, director, projection, -1, false, null, Color.gray, fill ? new Color(100, 100, 100) : null, buffer);
            surface(left2, director, projection, -1, false, null, Color.gray, fill ? new Color(101, 100, 100) : null, buffer);
            surface(left3, director, projection, -1, false, null, Color.gray, fill ? new Color(100, 101, 100) : null, buffer);
            surface(left4, director, projection, -1, false, null, Color.gray, fill ? new Color(100, 100, 101) : null, buffer);
            surface(left5, director, projection, -1, false, null, Color.gray, fill ? new Color(101, 101, 100) : null, buffer);

            surface(right1, director, projection, 1, false, null, Color.gray, fill ? new Color(100, 100, 100) : null, buffer);
            surface(right2, director, projection, 1, false, null, Color.gray, fill ? new Color(101, 100, 100) : null, buffer);
            surface(right3, director, projection, 1, false, null, Color.gray, fill ? new Color(100, 101, 100) : null, buffer);
            surface(right4, director, projection, 1, false, null, Color.gray, fill ? new Color(100, 100, 101) : null, buffer);
            surface(right5, director, projection, 1, false, null, Color.gray, fill ? new Color(101, 101, 100) : null, buffer);
        }
    }


    // Utils
    public int[][] transform(int[][] points, int[] center, double scale, double[] angles, double[] anglesAx, int[][][] axis) {
        int[][] pointsRot = new int[points.length][points[0].length];

        for(int i = 0; i < points.length; i++) {
            pointsRot[i] = Arrays.copyOf(points[i], points[i].length);
        }

        if(anglesAx != null) {
            for (int i = 0; i < anglesAx.length; i++) {
                pointsRot = rotateAroundLine(pointsRot[0], pointsRot[1], pointsRot[2], axis[i][0], axis[i][1], anglesAx[i]);
            }
        }

        int[][] rotatedPoints = rotate(pointsRot, angles);
        int[][] scaledPoints = scale3D(rotatedPoints[0], rotatedPoints[1], rotatedPoints[2], center[0], center[1], center[2], false, scale, scale, scale);

        return scaledPoints;
    }
}
