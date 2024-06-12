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

    public double[] angles;
    public double scale;

    public Venator(int xc, int yc, int zc, double scale, double[] angles) {
        this.xc = xc;
        this.yc = yc;
        this.zc = zc;

        this.x0 = xc + 500;
        this.y0 = yc + 500;
        this.z0 = zc + 500;

        this.angles = angles;
        this.scale = scale;
    }


    public void draw(int[] director, String projection, BufferedImage buffer, double ang) {
        // -------------------- Top --------------------
        int[][] topYAxis = new int[][]{new int[]{xc - 430, yc + 17, zc + 10}, new int[]{xc - 430, yc - 17, zc + 10}};

        // ----- Right -----
        int[][] topRight = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 129, xc + 243, xc + 278, xc + 250,},
                new int[]{yc + 17, yc + 32, yc + 139, yc + 93, yc + 108, yc + 210, yc + 211, yc + 52,},
                new int[]{zc + 10, zc + 10, zc + 10, zc + 10, zc + 10, zc + 10, zc + 10, zc + 10,},
        };

        int[][] topRightRot1 = rotateAroundLine(topRight[0], topRight[1], topRight[2], topYAxis[0], topYAxis[1], 0.03);

        int[][] topXRightAxis = new int[][]{
                new int[]{topRightRot1[0][0], topRightRot1[1][0], topRightRot1[2][0]},
                new int[]{topRightRot1[0][topRightRot1[0].length - 1], topRightRot1[1][topRightRot1[0].length - 1], topRightRot1[2][topRightRot1[0].length - 1]}
        };

        int[][] topRightRot2 = rotateAroundLine(topRightRot1[0], topRightRot1[1], topRightRot1[2], topXRightAxis[0], topXRightAxis[1], -0.2);

        // Transformations
        int[][] rotatedTopRight = rotate(topRightRot2, angles);
        int[][] scaledTopRight = scale3D(rotatedTopRight[0], rotatedTopRight[1], rotatedTopRight[2], xc, yc, zc, false, scale, scale, scale);

        // ----- Left -----
        int[][] topLeft = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 9, xc + 54, xc + 129, xc + 243, xc + 278, xc + 250,},
                new int[]{yc - 17, yc - 32, yc - 139, yc - 93, yc - 108, yc - 210, yc - 211, yc - 52,},
                new int[]{zc + 10, zc + 10, zc + 10, zc + 10, zc + 10, zc + 10, zc + 10, zc + 10,},
        };

        int[][] topLeftRot1 = rotateAroundLine(topLeft[0], topLeft[1], topLeft[2], topYAxis[0], topYAxis[1], 0.03);

        int[][] topXLeftAxis = new int[][]{
                new int[]{topLeftRot1[0][0], topLeftRot1[1][0], topLeftRot1[2][0]},
                new int[]{topLeftRot1[0][topLeftRot1[0].length - 1], topLeftRot1[1][topLeftRot1[0].length - 1], topLeftRot1[2][topLeftRot1[0].length - 1]}
        };

        int[][] topLeftRot2 = rotateAroundLine(topLeftRot1[0], topLeftRot1[1], topLeftRot1[2], topXLeftAxis[0], topXLeftAxis[1], 0.2);

        // Transformations
        int[][] rotatedTopLeft = rotate(topLeftRot2, angles);
        int[][] scaledTopLeft = scale3D(rotatedTopLeft[0], rotatedTopLeft[1], rotatedTopLeft[2], xc, yc, zc, false, scale, scale, scale);

        // ----- Center -----
        int[][] topYAxisCenter = new int[][]{new int[]{xc - 430, yc + 17, zc + 13}, new int[]{xc - 430, yc - 17, zc + 13}};

        int[][] topRed = new int[][]{
                new int[]{xc - 430, xc - 10, xc, xc - 10, xc - 430},
                new int[]{yc + 17, yc + 39, yc + 0, yc - 39, yc - 17},
                new int[]{zc + 13, zc + 13, zc + 13, zc + 13, zc + 13},
        };

        int[][] topRedRot1 = rotateAroundLine(topRed[0], topRed[1], topRed[2], topYAxisCenter[0], topYAxisCenter[1], 0.034);

        int[][] rotatedTopRed = rotate(topRedRot1, angles);
        int[][] scaledTopRed = scale3D(rotatedTopRed[0], rotatedTopRed[1], rotatedTopRed[2], xc, yc, zc, false, scale, scale, scale);

        int[][] topRedBase = new int[][]{
                new int[]{xc - 430, xc - 10, xc, xc - 10, xc - 430},
                new int[]{yc + 17, yc + 39, yc + 0, yc - 39, yc - 17},
                new int[]{zc + 10, zc + 10, zc + 10, zc + 10, zc + 10},
        };

        int[][] topRedBaseRot1 = rotateAroundLine(topRedBase[0], topRedBase[1], topRedBase[2], topYAxis[0], topYAxis[1], 0.03);

        int[][] rotatedTopRedBase = rotate(topRedBaseRot1, angles);
        int[][] scaledTopRedBase = scale3D(rotatedTopRedBase[0], rotatedTopRedBase[1], rotatedTopRedBase[2], xc, yc, zc, false, scale, scale, scale);

        int[][] topRedLeft = new int[][]{
                new int[]{scaledTopRed[0][3], scaledTopRed[0][4], scaledTopRedBase[0][4], scaledTopRedBase[0][3]},
                new int[]{scaledTopRed[1][3], scaledTopRed[1][4], scaledTopRedBase[1][4], scaledTopRedBase[1][3]},
                new int[]{scaledTopRed[2][3], scaledTopRed[2][4], scaledTopRedBase[2][4], scaledTopRedBase[2][3]},
        };

        int[][] topRedRight = new int[][]{
                new int[]{scaledTopRed[0][0], scaledTopRed[0][1], scaledTopRedBase[0][1], scaledTopRedBase[0][0]},
                new int[]{scaledTopRed[1][0], scaledTopRed[1][1], scaledTopRedBase[1][1], scaledTopRedBase[1][0]},
                new int[]{scaledTopRed[2][0], scaledTopRed[2][1], scaledTopRedBase[2][1], scaledTopRedBase[2][0]},
        };

        int[][] topRedFront = new int[][]{
                new int[]{scaledTopRed[0][4], scaledTopRed[0][0], scaledTopRedBase[0][0], scaledTopRedBase[0][4]},
                new int[]{scaledTopRed[1][4], scaledTopRed[1][0], scaledTopRedBase[1][0], scaledTopRedBase[1][4]},
                new int[]{scaledTopRed[2][4], scaledTopRed[2][0], scaledTopRedBase[2][0], scaledTopRedBase[2][4]},
        };




        int[][] topCenter = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc + 75, xc + 75, xc + 35, xc + 35, xc + 75, xc + 75, xc + 115, xc + 115, xc - 10, xc},
                new int[]{yc + 39, yc + 45, yc + 25, yc + 25, yc + 12, yc + 12, yc - 12, yc - 12, yc - 25, yc - 25, yc - 45, yc - 39, yc - 0},
                new int[]{zc + 28, zc + 32, zc + 32, zc + 31, zc + 31, zc + 29, zc + 29, zc + 31, zc + 31, zc + 32, zc + 32, zc + 28, zc + 28},
        };

        int[][] rotatedTopCenter = rotate(topCenter, angles);
        int[][] scaledTopCenter = scale3D(rotatedTopCenter[0], rotatedTopCenter[1], rotatedTopCenter[2], xc, yc, zc, false, scale, scale, scale);

        // -------------------- Bottom --------------------
        int[][] bottomYAxis = new int[][]{new int[]{xc - 430, yc + 17, zc - 10}, new int[]{xc - 430, yc - 17, zc - 10}};

        // ----- Right -----
        int[][] bottomRight = new int[][]{
                new int[]{xc - 430, xc - 432, xc - 9, xc + 54, xc + 129, xc + 244, xc + 289, xc + 356,},
                new int[]{yc, yc + 32, yc + 138, yc + 92, yc + 107, yc + 208, yc + 209, yc,},
                new int[]{zc - 10, zc - 10, zc - 10, zc - 10, zc - 10, zc - 10, zc - 10, zc - 10,},
        };

        int[][] bottomRightRot1 = rotateAroundLine(bottomRight[0], bottomRight[1], bottomRight[2], bottomYAxis[0], bottomYAxis[1], -0.03);

        int[][] bottomXAxis = new int[][]{
                new int[]{bottomRightRot1[0][0], bottomRightRot1[1][0], bottomRightRot1[2][0]},
                new int[]{bottomRightRot1[0][7], bottomRightRot1[1][7], bottomRightRot1[2][7]},
        };

        int[][] bottomRightRot2 = rotateAroundLine(bottomRightRot1[0], bottomRightRot1[1], bottomRightRot1[2], bottomXAxis[0], bottomXAxis[1], 0.13);

        // Transformations
        int[][] rotatedBottomRight = rotate(bottomRightRot2, angles);
        int[][] scaledBottomRight = scale3D(rotatedBottomRight[0], rotatedBottomRight[1], rotatedBottomRight[2], xc, yc, zc, false, scale, scale, scale);

        // ----- Left -----
        int[][] bottomLeft = new int[][]{
                new int[]{xc - 430, xc - 432, xc - 9, xc + 54, xc + 129, xc + 244, xc + 289, xc + 356,},
                new int[]{yc, yc - 32, yc - 138, yc - 92, yc - 107, yc - 208, yc - 209, yc,},
                new int[]{zc - 10, zc - 10, zc - 10, zc - 10, zc - 10, zc - 10, zc - 10, zc - 10,},
        };

        int[][] bottomLeftRot1 = rotateAroundLine(bottomLeft[0], bottomLeft[1], bottomLeft[2], bottomYAxis[0], bottomYAxis[1], -0.03);
        int[][] bottomLeftRot2 = rotateAroundLine(bottomLeftRot1[0], bottomLeftRot1[1], bottomLeftRot1[2], bottomXAxis[0], bottomXAxis[1], -0.13);


        // Transformations
        int[][] rotatedBottomLeft = rotate(bottomLeftRot2, angles);
        int[][] scaledBottomLeft = scale3D(rotatedBottomLeft[0], rotatedBottomLeft[1], rotatedBottomLeft[2], xc, yc, zc, false, scale, scale, scale);

        // -------------------- Sides --------------------
        int[][] left1 = new int[][]{
                new int[]{scaledTopLeft[0][1], scaledTopLeft[0][2], scaledBottomLeft[0][2], scaledBottomLeft[0][1]},
                new int[]{scaledTopLeft[1][1], scaledTopLeft[1][2], scaledBottomLeft[1][2], scaledBottomLeft[1][1]},
                new int[]{scaledTopLeft[2][1], scaledTopLeft[2][2], scaledBottomLeft[2][2], scaledBottomLeft[2][1]},
        };
        int[][] left2 = new int[][]{
                new int[]{scaledTopLeft[0][2], scaledTopLeft[0][3], scaledBottomLeft[0][3], scaledBottomLeft[0][2]},
                new int[]{scaledTopLeft[1][2], scaledTopLeft[1][3], scaledBottomLeft[1][3], scaledBottomLeft[1][2]},
                new int[]{scaledTopLeft[2][2], scaledTopLeft[2][3], scaledBottomLeft[2][3], scaledBottomLeft[2][2]},
        };

        int[][] left3 = new int[][]{
                new int[]{scaledTopLeft[0][3], scaledTopLeft[0][4], scaledBottomLeft[0][4], scaledBottomLeft[0][3]},
                new int[]{scaledTopLeft[1][3], scaledTopLeft[1][4], scaledBottomLeft[1][4], scaledBottomLeft[1][3]},
                new int[]{scaledTopLeft[2][3], scaledTopLeft[2][4], scaledBottomLeft[2][4], scaledBottomLeft[2][3]},
        };

        int[][] left4 = new int[][]{
                new int[]{scaledTopLeft[0][4], scaledTopLeft[0][5], scaledBottomLeft[0][5], scaledBottomLeft[0][4]},
                new int[]{scaledTopLeft[1][4], scaledTopLeft[1][5], scaledBottomLeft[1][5], scaledBottomLeft[1][4]},
                new int[]{scaledTopLeft[2][4], scaledTopLeft[2][5], scaledBottomLeft[2][5], scaledBottomLeft[2][4]},
        };

        int[][] left5 = new int[][]{
                new int[]{scaledTopLeft[0][5], scaledTopLeft[0][6], scaledBottomLeft[0][6], scaledBottomLeft[0][5]},
                new int[]{scaledTopLeft[1][5], scaledTopLeft[1][6], scaledBottomLeft[1][6], scaledBottomLeft[1][5]},
                new int[]{scaledTopLeft[2][5], scaledTopLeft[2][6], scaledBottomLeft[2][6], scaledBottomLeft[2][5]},
        };

        int[][] right1 = new int[][]{
                new int[]{scaledTopRight[0][1], scaledTopRight[0][2], scaledBottomRight[0][2], scaledBottomRight[0][1]},
                new int[]{scaledTopRight[1][1], scaledTopRight[1][2], scaledBottomRight[1][2], scaledBottomRight[1][1]},
                new int[]{scaledTopRight[2][1], scaledTopRight[2][2], scaledBottomRight[2][2], scaledBottomRight[2][1]},
        };
        int[][] right2 = new int[][]{
                new int[]{scaledTopRight[0][2], scaledTopRight[0][3], scaledBottomRight[0][3], scaledBottomRight[0][2]},
                new int[]{scaledTopRight[1][2], scaledTopRight[1][3], scaledBottomRight[1][3], scaledBottomRight[1][2]},
                new int[]{scaledTopRight[2][2], scaledTopRight[2][3], scaledBottomRight[2][3], scaledBottomRight[2][2]},
        };

        int[][] right3 = new int[][]{
                new int[]{scaledTopRight[0][3], scaledTopRight[0][4], scaledBottomRight[0][4], scaledBottomRight[0][3]},
                new int[]{scaledTopRight[1][3], scaledTopRight[1][4], scaledBottomRight[1][4], scaledBottomRight[1][3]},
                new int[]{scaledTopRight[2][3], scaledTopRight[2][4], scaledBottomRight[2][4], scaledBottomRight[2][3]},
        };

        int[][] right4 = new int[][]{
                new int[]{scaledTopRight[0][4], scaledTopRight[0][5], scaledBottomRight[0][5], scaledBottomRight[0][4]},
                new int[]{scaledTopRight[1][4], scaledTopRight[1][5], scaledBottomRight[1][5], scaledBottomRight[1][4]},
                new int[]{scaledTopRight[2][4], scaledTopRight[2][5], scaledBottomRight[2][5], scaledBottomRight[2][4]},
        };

        int[][] right5 = new int[][]{
                new int[]{scaledTopRight[0][5], scaledTopRight[0][6], scaledBottomRight[0][6], scaledBottomRight[0][5]},
                new int[]{scaledTopRight[1][5], scaledTopRight[1][6], scaledBottomRight[1][6], scaledBottomRight[1][5]},
                new int[]{scaledTopRight[2][5], scaledTopRight[2][6], scaledBottomRight[2][6], scaledBottomRight[2][5]},
        };

        int[][] front = new int[][]{
                new int[]{scaledTopLeft[0][1], scaledTopLeft[0][0], scaledTopRight[0][0], scaledTopRight[0][1], scaledBottomRight[0][1], scaledBottomRight[0][0], scaledBottomLeft[0][1]},
                new int[]{scaledTopLeft[1][1], scaledTopLeft[1][0], scaledTopRight[1][0], scaledTopRight[1][1], scaledBottomRight[1][1], scaledBottomRight[1][0], scaledBottomLeft[1][1]},
                new int[]{scaledTopLeft[2][1], scaledTopLeft[2][0], scaledTopRight[2][0], scaledTopRight[2][1], scaledBottomRight[2][1], scaledBottomRight[2][0], scaledBottomLeft[2][1]},
        };





        /*System.out.println("----------");
        for(int i = 0; i < bottomRightRot2.length; i++) {
            for(int j = 0; j < bottomRightRot2[0].length; j++) {
                System.out.print(bottomRightRot2[i][j] + ", ");

            }
            System.out.println("");
        }*/

        // X angle -0.13


        /*System.out.print("new int[]{");
        for (int i = 0; i < ro[0].length; i++) {
            System.out.print("xc + " + ro[0][i] + ", ");
        }
        System.out.print("},");
        System.out.println();
        System.out.print("new int[]{");
        for (int i = 0; i < ro[0].length; i++) {
            System.out.print("yc + " + ro[1][i] + ", ");
        }
        System.out.print("},");
        System.out.println();
        System.out.print("new int[]{");
        for (int i = 0; i < ro[0].length; i++) {
            System.out.print("zc + " + ro[2][i] + ", ");
        }
        System.out.print("},");*/

        // Top angle 0.19
        // Bottom angle 0.14

        /*for(int i = 0; i < bottomLeft.length; i++) {
            for(int j = 0; j < bottomLeft[0].length; j++) {
                System.out.print(rotated4[i][j] + ", ");

            }
            System.out.println("");
        }*/

        surface(topRedRight, director, projection, 1, false, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
        surface(topRedLeft, director, projection, 1, false, new Color(120, 80, 80), new Color(116, 53, 60), buffer);
        surface(topRedFront, director, projection, 1, false, new Color(120, 80, 80), new Color(116, 53, 60), buffer);

        if(angles[2] > Math.PI && angles[2] < 2 * Math.PI) {
            surface(left5, director, projection, -1, false, Color.gray, new Color(100, 100, 100 ), buffer);
            surface(left4, director, projection, -1, false, Color.gray, new Color(101, 100, 100 ), buffer);
            surface(left3, director, projection, -1, false, Color.gray, new Color(100, 101, 100 ), buffer);
            surface(left2, director, projection, -1, false, Color.gray, new Color(100, 100, 101 ), buffer);
            surface(left1, director, projection, -1, false, Color.gray, new Color(101, 101, 100 ), buffer);

            surface(right5, director, projection, 1, false, Color.gray, new Color(100, 100, 100 ), buffer);
            surface(right4, director, projection, 1, false, Color.gray, new Color(101, 100, 100 ), buffer);
            surface(right3, director, projection, 1, false, Color.gray, new Color(100, 101, 100 ), buffer);
            surface(right2, director, projection, 1, false, Color.gray, new Color(100, 100, 101 ), buffer);
            surface(right1, director, projection, 1, false, Color.gray, new Color(101, 101, 100 ), buffer);

            surface(front, director, projection, 1, false, Color.gray, new Color(101, 101, 101 ), buffer);
        }
        else {
            surface(front, director, projection, 1, false, Color.gray, new Color(101, 101, 101 ), buffer);

            surface(left1, director, projection, -1, false, Color.gray, new Color(100, 100, 100 ), buffer);
            surface(left2, director, projection, -1, false, Color.gray, new Color(101, 100, 100 ), buffer);
            surface(left3, director, projection, -1, false, Color.gray, new Color(100, 101, 100 ), buffer);
            surface(left4, director, projection, -1, false, Color.gray, new Color(100, 100, 101 ), buffer);
            surface(left5, director, projection, -1, false, Color.gray, new Color(101, 101, 100 ), buffer);

            surface(right1, director, projection, 1, false, Color.gray, new Color(100, 100, 100 ), buffer);
            surface(right2, director, projection, 1, false, Color.gray, new Color(101, 100, 100 ), buffer);
            surface(right3, director, projection, 1, false, Color.gray, new Color(100, 101, 100 ), buffer);
            surface(right4, director, projection, 1, false, Color.gray, new Color(100, 100, 101 ), buffer);
            surface(right5, director, projection, 1, false, Color.gray, new Color(101, 101, 100 ), buffer);
        }

        surface(scaledTopLeft, director, projection, 1, false, Color.gray, new Color(180, 180, 180), buffer);
        surface(scaledTopRight, director, projection, -1, false, Color.gray, new Color(180, 180, 180), buffer);
        //surface(scaledTopRight2 director, projection, -1, false, Color.gray, Color.blue, buffer);

        //prism(scaledTopCenter, 4, director, 1, null, projection, -1, Color.gray, buffer);
        //prism(scaledTopRed, 4, director, 1, null, projection, -1, new Color(143, 67, 76, buffer);

        surface(scaledTopRed, director, projection, -1, false, new Color(120, 80, 80), new Color(143, 67, 76), buffer);

        surface(scaledBottomLeft, director, projection, -1, false, Color.gray, new Color(180, 180, 180), buffer);
        surface(scaledBottomRight, director, projection, 1, false, Color.gray, new Color(180, 180, 180), buffer);

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
}
