package models;

import graphics.Transformations;

import static graphics.Draw.*;
import static graphics.Draw3d.*;
import static graphics.Transformations.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Venator {
    public int xc;
    public int yc;
    public int zc;

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

        /*surface(topRedRight, director, projection, 1, false, null, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
        surface(topRedLeft, director, projection, 1, false, null, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
        surface(topRedFront, director, projection, 1, false, null, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
*/
        //drawSides(director, projection, buffer);

        drawTopSides(director, projection, false, buffer);

        drawTopCenter(director, projection, false, buffer);

        /*drawBridge(director, projection, true, buffer);

        drawBottom(director, projection, true, buffer);*/

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

        int[][] topLeftRot2 = rotateAroundLine(topLeftRot1[0], topLeftRot1[1], topLeftRot1[2], topXLeftAxis[0], topXLeftAxis[1], xTopInclination);

        // Transformations
        int[][] rotatedTopLeft = rotate(topLeftRot2, angles);
        scaledTopLeft = scale3D(rotatedTopLeft[0], rotatedTopLeft[1], rotatedTopLeft[2], xc, yc, zc, false, scale, scale, scale);

        // Faces to paint
        topLeft1 = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 54},
                new int[]{yc - 17, yc - 32, yc - 139, yc - 93, yc - 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height},
        };

        int[][] topLeft1Rot1 = rotateAroundLine(topLeft1[0], topLeft1[1], topLeft1[2], topYAxis[0], topYAxis[1], yInclination);
        int[][] topLeft1Rot2 = rotateAroundLine(topLeft1Rot1[0], topLeft1Rot1[1], topLeft1Rot1[2], topXLeftAxis[0], topXLeftAxis[1], xTopInclination);

        // Transformations
        int[][] rotatedTopLeft1 = rotate(topLeft1Rot2, angles);
        scaledTopLeft1 = scale3D(rotatedTopLeft1[0], rotatedTopLeft1[1], rotatedTopLeft1[2], xc, yc, zc, false, scale, scale, scale);

        topLeft2 = new int[][]{
                new int[]{xc + 54, xc + 129, xc + 243, xc + 278, xc + 250, xc + 54},
                new int[]{yc - 93, yc - 108, yc - 210, yc - 211, yc - 52, yc - 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height},
        };

        int[][] topLeft2Rot1 = rotateAroundLine(topLeft2[0], topLeft2[1], topLeft2[2], topYAxis[0], topYAxis[1], yInclination);
        int[][] topLeft2Rot2 = rotateAroundLine(topLeft2Rot1[0], topLeft2Rot1[1], topLeft2Rot1[2], topXLeftAxis[0], topXLeftAxis[1], xTopInclination);

        // Transformations
        int[][] rotatedTopLeft2 = rotate(topLeft2Rot2, angles);
        scaledTopLeft2 = scale3D(rotatedTopLeft2[0], rotatedTopLeft2[1], rotatedTopLeft2[2], xc, yc, zc, false, scale, scale, scale);

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

        int[][] topRightRot2 = rotateAroundLine(topRightRot1[0], topRightRot1[1], topRightRot1[2], topXRightAxis[0], topXRightAxis[1], -xTopInclination);

        // Transformations
        int[][] rotatedTopRight = rotate(topRightRot2, angles);
        scaledTopRight = scale3D(rotatedTopRight[0], rotatedTopRight[1], rotatedTopRight[2], xc, yc, zc, false, scale, scale, scale);

        // Faces to paint
        topRight1 = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 54},
                new int[]{yc + 17, yc + 32, yc + 139, yc + 93, yc + 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height},
        };

        int[][] topRight1Rot1 = rotateAroundLine(topRight1[0], topRight1[1], topRight1[2], topYAxis[0], topYAxis[1], yInclination);
        int[][] topRight1Rot2 = rotateAroundLine(topRight1Rot1[0], topRight1Rot1[1], topRight1Rot1[2], topXRightAxis[0], topXRightAxis[1], -xTopInclination);

        // Transformations
        int[][] rotatedTopRight1 = rotate(topRight1Rot2, angles);
        scaledTopRight1 = scale3D(rotatedTopRight1[0], rotatedTopRight1[1], rotatedTopRight1[2], xc, yc, zc, false, scale, scale, scale);

        topRight2 = new int[][]{
                new int[]{xc + 54, xc + 129, xc + 243, xc + 278, xc + 250, xc + 54},
                new int[]{yc + 93, yc + 108, yc + 210, yc + 211, yc + 52, yc + 42},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height, zc + height},
        };

        int[][] topRight2Rot1 = rotateAroundLine(topRight2[0], topRight2[1], topRight2[2], topYAxis[0], topYAxis[1], yInclination);
        int[][] topRight2Rot2 = rotateAroundLine(topRight2Rot1[0], topRight2Rot1[1], topRight2Rot1[2], topXRightAxis[0], topXRightAxis[1], -xTopInclination);

        // Transformations
        int[][] rotatedTopRight2 = rotate(topRight2Rot2, angles);
        scaledTopRight2 = scale3D(rotatedTopRight2[0], rotatedTopRight2[1], rotatedTopRight2[2], xc, yc, zc, false, scale, scale, scale);


    }

    private void calTopCenter() {
        topYElevatedAxis = new int[][]{new int[]{xc - 430, yc + 17, zc + height + 3}, new int[]{xc - 430, yc - 17, zc + height + 3}};

        // ----- Red -----
        topRed = new int[][]{
                new int[]{xc - 430, xc - 10, xc, xc - 10, xc - 430},
                new int[]{yc + 17, yc + 39, yc + 0, yc - 39, yc - 17},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3},
        };

        int[][] topRedRot1 = rotateAroundLine(topRed[0], topRed[1], topRed[2], topYElevatedAxis[0], topYElevatedAxis[1], yInclination + 0.004);

        int[][] rotatedTopRed = rotate(topRedRot1, angles);
        scaledTopRed = scale3D(rotatedTopRed[0], rotatedTopRed[1], rotatedTopRed[2], xc, yc, zc, false, scale, scale, scale);

        topRedBase = new int[][]{
                new int[]{xc - 430, xc - 10, xc, xc - 10, xc - 430},
                new int[]{yc + 17, yc + 39, yc + 0, yc - 39, yc - 17},
                new int[]{zc + height, zc + height, zc + height, zc + height, zc + height},
        };

        int[][] topRedBaseRot1 = rotateAroundLine(topRedBase[0], topRedBase[1], topRedBase[2], topYAxis[0], topYAxis[1], yInclination);

        int[][] rotatedTopRedBase = rotate(topRedBaseRot1, angles);
        scaledTopRedBase = scale3D(rotatedTopRedBase[0], rotatedTopRedBase[1], rotatedTopRedBase[2], xc, yc, zc, false, scale, scale, scale);

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

        int[][] topCenterBaseRot1 = rotateAroundLine(topCenterBase[0], topCenterBase[1], topCenterBase[2], topYAxis[0], topYAxis[1], yInclination);

        int[][] rotatedTopCenterBase = rotate(topCenterBaseRot1, angles);
        scaledTopCenterBase = scale3D(rotatedTopCenterBase[0], rotatedTopCenterBase[1], rotatedTopCenterBase[2], xc, yc, zc, false, scale, scale, scale);

        topCenter = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc + 75, xc + 75, xc + 35, xc + 35, xc + 75, xc + 75, xc + 115, xc + 115, xc - 10, xc},
                new int[]{yc + 39, yc + 45, yc + 25, yc + 25, yc + 12, yc + 12, yc - 12, yc - 12, yc - 25, yc - 25, yc - 45, yc - 39, yc - 0},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };

        int[][] topCenterRot1 = rotateAroundLine(topCenter[0], topCenter[1], topCenter[2], topYElevatedAxis[0], topYElevatedAxis[1], yInclination + 0.004);

        int[][] rotatedTopCenter = rotate(topCenterRot1, angles);
        scaledTopCenter = scale3D(rotatedTopCenter[0], rotatedTopCenter[1], rotatedTopCenter[2], xc, yc, zc, false, scale, scale, scale);

        topCenterLeft1 = new int[][]{
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

        bridgeBase = new int[][]{
                new int[]{xc + 75, xc + 115, xc + 115, xc + 75,},
                new int[]{yc + 43, yc + 45, yc + 25, yc + 25},
                new int[]{zc + height + 3, zc + height + 3, zc + height + 3, zc + height + 3,},
        };

        int[][] bridgeBaseRot1 = rotateAroundLine(bridgeBase[0], bridgeBase[1], bridgeBase[2], topYElevatedAxis[0], topYElevatedAxis[1], yInclination + 0.004);

        int[][] rotatedBridgeBase = rotate(bridgeBaseRot1, angles);
        scaledBridgeBase = scale3D(rotatedBridgeBase[0], rotatedBridgeBase[1], rotatedBridgeBase[2], xc, yc, zc, false, scale, scale, scale);

    }

    private void calBridge() {
        bridgeTopCenter2 = new int[][]{
                new int[]{xc + 150, xc + 270, xc + 270, xc + 150},
                new int[]{yc + 12, yc + 12, yc - 12, yc - 12},
                new int[]{zc + 85, zc + 100, zc + 100, zc + 85},
        };

        int[][] rotatedBridgeTopCenter2 = rotate(bridgeTopCenter2, angles);
        scaledBridgeTopCenter2 = scale3D(rotatedBridgeTopCenter2[0], rotatedBridgeTopCenter2[1], rotatedBridgeTopCenter2[2], xc, yc, zc, false, scale, scale, scale);

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

        int[][] rotatedBridgeTowerBaseLeft = rotate(bridgeTowerBaseLeft, angles);
        scaledBridgeTowerBaseLeft = scale3D(rotatedBridgeTowerBaseLeft[0], rotatedBridgeTowerBaseLeft[1], rotatedBridgeTowerBaseLeft[2], xc, yc, zc, false, scale, scale, scale);

        bridgeTowerBaseRight = new int[][]{
                new int[]{xc + 170, xc + 245, xc + 245, xc + 170},
                new int[]{yc + 12, yc + 12, yc + 30, yc + 30},
                new int[]{zc + 90, zc + 90, zc + 90, zc + 90},
        };

        int[][] rotatedBridgeTowerBaseRight = rotate(bridgeTowerBaseRight, angles);
        scaledBridgeTowerBaseRight = scale3D(rotatedBridgeTowerBaseRight[0], rotatedBridgeTowerBaseRight[1], rotatedBridgeTowerBaseRight[2], xc, yc, zc, false, scale, scale, scale);


        int[][] bridgeTowersBaseFrontMid = new int[][]{
                new int[]{xc + 170, xc + 170, 1, 1},
                new int[]{yc - 18, yc + 18, 1, 1},
                new int[]{zc + 90, zc + 90, 1, 1},
        };

        int[][] rotatedBridgeTowersBaseFrontMid = rotate(bridgeTowersBaseFrontMid, angles);
        int[][] scaledBridgeTowersBaseFrontMid = scale3D(rotatedBridgeTowersBaseFrontMid[0], rotatedBridgeTowersBaseFrontMid[1], rotatedBridgeTowersBaseFrontMid[2], xc, yc, zc, false, scale, scale, scale);

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

        int[][] bottomLeftRot2 = rotateAroundLine(bottomLeftRot1[0], bottomLeftRot1[1], bottomLeftRot1[2], bottomXAxis[0], bottomXAxis[1], -xBottomInclination);

        // Transformations
        int[][] rotatedBottomLeft = rotate(bottomLeftRot2, angles);
        scaledBottomLeft = scale3D(rotatedBottomLeft[0], rotatedBottomLeft[1], rotatedBottomLeft[2], xc, yc, zc, false, scale, scale, scale);

        // ----- Right -----
        bottomRight = new int[][]{
                new int[]{xc - 430, xc - 432, xc - 9, xc + 54, xc + 129, xc + 244, xc + 289, xc + 356,},
                new int[]{yc, yc + 32, yc + 138, yc + 92, yc + 107, yc + 208, yc + 209, yc,},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height,},
        };

        int[][] bottomRightRot1 = rotateAroundLine(bottomRight[0], bottomRight[1], bottomRight[2], bottomYAxis[0], bottomYAxis[1], -yInclination);
        int[][] bottomRightRot2 = rotateAroundLine(bottomRightRot1[0], bottomRightRot1[1], bottomRightRot1[2], bottomXAxis[0], bottomXAxis[1], xBottomInclination);

        // Transformations
        int[][] rotatedBottomRight = rotate(bottomRightRot2, angles);
        scaledBottomRight = scale3D(rotatedBottomRight[0], rotatedBottomRight[1], rotatedBottomRight[2], xc, yc, zc, false, scale, scale, scale);

        // ----- Center -----
        bottomRedLeft = new int[][]{
                new int[]{xc - 430, xc - 431, xc - 310, xc - 300, xc - 270, xc - 260, xc - 160, xc - 150, xc - 90, xc - 80, xc + 353, xc + 356},
                new int[]{yc, yc - 10, yc - 10, yc - 17, yc - 17, yc - 10, yc - 10, yc - 17, yc - 17, yc - 10, yc - 10, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height},
        };

        int[][] bottomRedLeftRot1 = rotateAroundLine(bottomRedLeft[0], bottomRedLeft[1], bottomRedLeft[2], bottomYAxis[0], bottomYAxis[1], -yInclination);
        int[][] bottomRedLeftRot2 = rotateAroundLine(bottomRedLeftRot1[0], bottomRedLeftRot1[1], bottomRedLeftRot1[2], bottomXAxis[0], bottomXAxis[1], -xBottomInclination);

        // Transformations
        int[][] rotatedBottomRedLeft = rotate(bottomRedLeftRot2, angles);
        scaledBottomRedLeft = scale3D(rotatedBottomRedLeft[0], rotatedBottomRedLeft[1], rotatedBottomRedLeft[2], xc, yc, zc, false, scale, scale, scale);

        bottomRedRight = new int[][]{
                new int[]{xc - 430, xc - 431, xc - 310, xc - 300, xc - 270, xc - 260, xc - 160, xc - 150, xc - 90, xc - 80, xc + 353, xc + 356},
                new int[]{yc, yc + 10, yc + 10, yc + 17, yc + 17, yc + 10, yc + 10, yc + 17, yc + 17, yc + 10, yc + 10, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height, zc - height},
        };

        int[][] bottomRedRightRot1 = rotateAroundLine(bottomRedRight[0], bottomRedRight[1], bottomRedRight[2], bottomYAxis[0], bottomYAxis[1], -yInclination);
        int[][] bottomRedRightRot2 = rotateAroundLine(bottomRedRightRot1[0], bottomRedRightRot1[1], bottomRedRightRot1[2], bottomXAxis[0], bottomXAxis[1], xBottomInclination);

        // Transformations
        int[][] rotatedBottomRedRight = rotate(bottomRedRightRot2, angles);
        scaledBottomRedRight = scale3D(rotatedBottomRedRight[0], rotatedBottomRedRight[1], rotatedBottomRedRight[2], xc, yc, zc, false, scale, scale, scale);

        // ----- Hangar -----
        bottomHangarLeft = new int[][]{
                new int[]{xc - 9, xc - 8, xc + 81, xc + 81},
                new int[]{yc, yc - 25, yc - 25, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height,},
        };

        int[][] bottomHangarLeftRot1 = rotateAroundLine(bottomHangarLeft[0], bottomHangarLeft[1], bottomHangarLeft[2], bottomYAxis[0], bottomYAxis[1], -yInclination);
        int[][] bottomHangarLeftRot2 = rotateAroundLine(bottomHangarLeftRot1[0], bottomHangarLeftRot1[1], bottomHangarLeftRot1[2], bottomXAxis[0], bottomXAxis[1], -xBottomInclination);

        // Transformations
        int[][] rotatedBottomHangarLeft = rotate(bottomHangarLeftRot2, angles);
        scaledBottomHangarLeft = scale3D(rotatedBottomHangarLeft[0], rotatedBottomHangarLeft[1], rotatedBottomHangarLeft[2], xc, yc, zc, false, scale, scale, scale);

        bottomHangarRight = new int[][]{
                new int[]{xc - 9, xc - 8, xc + 81, xc + 81},
                new int[]{yc, yc + 25, yc + 25, yc},
                new int[]{zc - height, zc - height, zc - height, zc - height,},
        };

        int[][] bottomHangarRightRot1 = rotateAroundLine(bottomHangarRight[0], bottomHangarRight[1], bottomHangarRight[2], bottomYAxis[0], bottomYAxis[1], -yInclination);
        int[][] bottomHangarRightRot2 = rotateAroundLine(bottomHangarRightRot1[0], bottomHangarRightRot1[1], bottomHangarRightRot1[2], bottomXAxis[0], bottomXAxis[1], xBottomInclination);

        // Transformations
        int[][] rotatedBottomHangarRight = rotate(bottomHangarRightRot2, angles);
        scaledBottomHangarRight = scale3D(rotatedBottomHangarRight[0], rotatedBottomHangarRight[1], rotatedBottomHangarRight[2], xc, yc, zc, false, scale, scale, scale);
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
}
