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
        // Faces
        int[][] topRight = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 10, xc + 55, xc + 130, xc + 245, xc + 280, xc + 250},
                new int[]{yc + 17, yc + 32, yc + 137, yc + 92, yc + 107, yc + 217, yc + 217, yc + 52},
                /*new int[]{100, 100, 100, 100, 100, 100, 100, 100},*/
                /*new int[]{100, 104, 116, 99, 101, 127, 126, 79},*/
                new int[]{zc + 15, zc + 11, zc + 8, zc + 19, zc + 19, zc + 5, zc + 5, zc + 36},
        };

        int[][] rotatedTopRight = rotate(topRight, angles);
        int[][] scaledTopRight = scale3D(rotatedTopRight[0], rotatedTopRight[1], rotatedTopRight[2], xc, yc, zc, false, scale, scale, scale);

        int[][] topLeft = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 10, xc + 55, xc + 130, xc + 245, xc + 280, xc + 250},
                new int[]{yc - 17, yc - 32, yc - 137, yc - 92, yc - 107, yc - 217, yc - 217, yc - 52},
                /*new int[]{100, 100, 87, 85, 83, 79, 78, 79},*/
                /*new int[]{zc + 15, zc + 8, zc + 8, zc + 23, zc + 23, zc + 5, zc + 5, zc + 36},*/
                new int[]{zc + 15, zc + 11, zc + 8, zc + 19, zc + 19, zc + 5, zc + 5, zc + 36},

        };

        int[][] rotatedTopLeft = rotate(topLeft, angles);
        int[][] scaledTopLeft = scale3D(rotatedTopLeft[0], rotatedTopLeft[1], rotatedTopLeft[2], xc, yc, zc, false, scale, scale, scale);

        int[][] topRed = new int[][]{
                new int[]{xc - 430, xc - 10, xc, xc - 10, xc - 430},
                new int[]{yc + 17, yc + 39, yc + 0, yc - 39, yc - 17},
                new int[]{zc + 15, zc + 28, zc + 28, zc + 28, zc + 15},
        };

        int[][] rotatedTopRed = rotate(topRed, angles);
        int[][] scaledTopRed = scale3D(rotatedTopRed[0], rotatedTopRed[1], rotatedTopRed[2], xc, yc, zc, false, scale, scale, scale);

        int[][] topCenter = new int[][]{
                new int[]{xc - 10, xc + 115, xc + 115, xc + 75, xc + 75, xc + 35, xc + 35, xc + 75, xc + 75, xc + 115, xc + 115, xc -10, xc},
                new int[]{yc + 39, yc + 45, yc + 25, yc + 25, yc + 12, yc + 12, yc - 12, yc - 12, yc -  25, yc - 25, yc - 45, yc - 39, yc - 0},
                new int[]{zc + 28, zc + 32, zc + 32, zc + 31, zc + 31, zc + 29, zc + 29, zc + 31, zc + 31, zc + 32, zc + 32, zc + 28, zc + 28},
        };

        int[][] rotatedTopCenter = rotate(topCenter, angles);
        int[][] scaledTopCenter = scale3D(rotatedTopCenter[0], rotatedTopCenter[1], rotatedTopCenter[2], xc, yc, zc, false, scale, scale, scale);

        int[][] bottomRight = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 10, xc + 55, xc + 130, xc + 245, xc + 290, xc + 355},
                new int[]{yc, yc + 32, yc + 137, yc + 92, yc + 107, yc + 217, yc + 217, yc},
                /*new int[]{130, 130, 130, 130, 130, 130, 130, 130},*/
                /*new int[]{zc - 15, zc - 8, zc - 8, zc - 23, zc - 23, zc - 5, zc - 5, zc - 38},*/
                new int[]{zc - 15, zc - 11, zc - 8, zc - 19, zc - 19, zc - 5, zc - 5, zc - 38},
        };

        int[][] rotatedBottomRight = rotate(bottomRight, angles);
        int[][] scaledBottomRight = scale3D(rotatedBottomRight[0], rotatedBottomRight[1], rotatedBottomRight[2], xc, yc, zc, false, scale, scale, scale);

        int[][] bottomLeft = new int[][]{
                new int[]{xc - 430, xc - 430, xc - 10, xc + 55, xc + 130, xc + 245, xc + 290, xc + 355},
                new int[]{yc, yc - 32, yc - 137, yc - 92, yc - 107, yc - 217, yc - 217, yc},
                /*new int[]{130, 130, 130, 130, 130, 130, 130, 130},*/
                /*new int[]{zc - 15, zc - 8, zc - 8, zc - 23, zc - 23, zc - 5, zc - 5, zc - 38},*/
                new int[]{zc - 15, zc - 11, zc - 8, zc - 19, zc - 19, zc - 5, zc - 5, zc - 38},

        };

        int[][] rotatedBottomLeft = rotate(bottomLeft, angles);
        int[][] scaledBottomLeft = scale3D(rotatedBottomLeft[0], rotatedBottomLeft[1], rotatedBottomLeft[2], xc, yc, zc, false, scale, scale, scale);



        int[][] left1 = new int[][]{
                new int[]{xc - 430, xc - 10, xc - 10, xc - 430},
                new int[]{yc - 32, yc - 137, yc - 137, yc -32},
                new int[]{zc + 11, zc + 8, zc - 8, zc - 11},
        };

        int[][] rotatedLeft1 = rotate(left1, angles);
        int[][] scaledLeft1 = scale3D(rotatedLeft1[0], rotatedLeft1[1], rotatedLeft1[2], xc, yc, zc, false, scale, scale, scale);

        int[][] left2 = new int[][]{
                new int[]{xc - 10, xc + 55, xc + 55, xc - 10},
                new int[]{yc - 137, yc - 92, yc - 92, yc - 137},
                new int[]{zc + 8, zc + 19, zc - 19, zc - 8},
        };

        int[][] rotatedLeft2 = rotate(left2, angles);
        int[][] scaledLeft2 = scale3D(rotatedLeft2[0], rotatedLeft2[1], rotatedLeft2[2], xc, yc, zc, false, scale, scale, scale);

        int[][] left3 = new int[][]{
                new int[]{xc + 55, xc + 130, xc + 130, xc + 55},
                new int[]{yc - 92, yc - 107, yc - 107, yc - 92},
                new int[]{zc + 19, zc + 19, zc - 19, zc - 19},
        };

        int[][] rotatedLeft3 = rotate(left3, angles);
        int[][] scaledLeft3 = scale3D(rotatedLeft3[0], rotatedLeft3[1], rotatedLeft3[2], xc, yc, zc, false, scale, scale, scale);

        int[][] left4 = new int[][]{
                new int[]{xc + 130, xc + 245, xc + 245, xc + 130},
                new int[]{yc - 107, yc - 217, yc - 217, yc - 107},
                new int[]{zc + 19, zc + 5, zc - 5, zc - 19},
        };

        int[][] rotatedLeft4 = rotate(left4, angles);
        int[][] scaledLeft4 = scale3D(rotatedLeft4[0], rotatedLeft4[1], rotatedLeft4[2], xc, yc, zc, false, scale, scale, scale);

        int[][] left5 = new int[][]{
                new int[]{xc + 245, xc + 280, xc + 280, xc + 245},
                new int[]{yc - 217, yc - 217, yc - 217, yc - 217},
                new int[]{zc + 5, zc + 5, zc - 5, zc - 5},
        };

        int[][] rotatedLeft5 = rotate(left5, angles);
        int[][] scaledLeft5 = scale3D(rotatedLeft5[0], rotatedLeft5[1], rotatedLeft5[2], xc, yc, zc, false, scale, scale, scale);


        int[][] right1 = new int[][]{
                new int[]{xc - 430, xc - 10, xc - 10, xc - 430},
                new int[]{yc + 32, yc + 137, yc + 137, yc + 32},
                new int[]{zc + 11, zc + 8, zc - 8, zc - 11},
        };

        int[][] rotatedRight1 = rotate(right1, angles);
        int[][] scaledRight1 = scale3D(rotatedRight1[0], rotatedRight1[1], rotatedRight1[2], xc, yc, zc, false, scale, scale, scale);

        int[][] right2 = new int[][]{
                new int[]{xc - 10, xc + 55, xc + 55, xc - 10},
                new int[]{yc + 137, yc + 92, yc + 92, yc + 137},
                new int[]{zc + 8, zc + 19, zc - 19, zc - 8},
        };

        int[][] rotatedRight2 = rotate(right2, angles);
        int[][] scaledRight2 = scale3D(rotatedRight2[0], rotatedRight2[1], rotatedRight2[2], xc, yc, zc, false, scale, scale, scale);

        int[][] right3 = new int[][]{
                new int[]{xc + 55, xc + 130, xc + 130, xc + 55},
                new int[]{yc + 92, yc + 107, yc + 107, yc + 92},
                new int[]{zc + 19, zc + 19, zc - 19, zc - 19},
        };

        int[][] rotatedRight3 = rotate(right3, angles);
        int[][] scaledRight3 = scale3D(rotatedRight3[0], rotatedRight3[1], rotatedRight3[2], xc, yc, zc, false, scale, scale, scale);

        int[][] right4 = new int[][]{
                new int[]{xc + 130, xc + 245, xc + 245, xc + 130},
                new int[]{yc + 107, yc + 217, yc + 217, yc + 107},
                new int[]{zc + 19, zc + 5, zc - 5, zc - 19},
        };

        int[][] rotatedRight4 = rotate(right4, angles);
        int[][] scaledRight4 = scale3D(rotatedRight4[0], rotatedRight4[1], rotatedRight4[2], xc, yc, zc, false, scale, scale, scale);

        int[][] right5 = new int[][]{
                new int[]{xc + 245, xc + 280, xc + 280, xc + 245},
                new int[]{yc + 217, yc + 217, yc + 217, yc + 217},
                new int[]{zc + 5, zc + 5, zc - 5, zc - 5},
        };

        int[][] rotatedRight5 = rotate(right5, angles);
        int[][] scaledRight5 = scale3D(rotatedRight5[0], rotatedRight5[1], rotatedRight5[2], xc, yc, zc, false, scale, scale, scale);




        int[] p1 = new int[]{topRed[0][0], topRed[1][2], topRed[2][0]};
        int[] p2 = new int[]{topRed[0][2], topRed[1][2], topRed[2][0]};


        int[] a = new int[]{scaledTopRight[0][0], scaledTopRight[1][0], scaledTopRight[2][0]};
        int[] b = new int[]{scaledTopRight[0][7], scaledTopRight[1][7], scaledTopRight[2][7]};

        int[] c = new int[]{scaledBottomRight[0][0], scaledBottomRight[1][0], scaledBottomRight[2][0]};
        int[] d = new int[]{scaledBottomRight[0][7], scaledBottomRight[1][7], scaledBottomRight[2][7]};


        // Top angle 0.19
        // Bottom angle 0.14

        /*for(int i = 0; i < bottomLeft.length; i++) {
            for(int j = 0; j < bottomLeft[0].length; j++) {
                System.out.print(rotated4[i][j] + ", ");

            }
            System.out.println("");
        }*/

        surface(scaledLeft1, 0, p1, p2, director, projection, -1, Color.white, Color.black, buffer);
        surface(scaledLeft2, 0, p1, p2, director, projection, -1, Color.white, Color.black, buffer);
        surface(scaledLeft3, 0, p1, p2, director, projection, -1, Color.white, Color.black, buffer);
        surface(scaledLeft4, 0, p1, p2, director, projection, -1, Color.white, Color.black, buffer);
        surface(scaledLeft5, 0, p1, p2, director, projection, -1, Color.white, Color.black, buffer);

        surface(scaledRight1, 0, p1, p2, director, projection, 1, Color.white, Color.black, buffer);
        surface(scaledRight2, 0, p1, p2, director, projection, 1, Color.white, Color.black, buffer);
        surface(scaledRight3, 0, p1, p2, director, projection, 1, Color.white, Color.black, buffer);
        surface(scaledRight4, 0, p1, p2, director, projection, 1, Color.white, Color.black, buffer);
        surface(scaledRight5, 0, p1, p2, director, projection, 1, Color.white, Color.black, buffer);


        surface(scaledTopLeft, 0, p1, p2, director, projection, 1, Color.white, Color.gray, buffer);
        surface(scaledTopRight, 0, a, b, director, projection, -1, Color.white, Color.gray, buffer);

        prism(scaledTopCenter, 4, director, 1, null, projection, -1, Color.white, buffer);
        prism(scaledTopRed, 4, director, 1, null, projection, -1, Color.red, buffer);
        //surface(scaledTopRed, 0, p1, p2, director, projection, 1, Color.red, buffer);

        surface(scaledBottomLeft, 0, p1, p2, director, projection, -1, Color.white, Color.gray, buffer);
        surface(scaledBottomRight, 0, c, d, director, projection, 1, Color.white, Color.gray, buffer);


        drawAxis(director, projection, buffer);
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
        for(int i = 1; i < 4; i++) {
            drawLine(projectedAxis[0][0], projectedAxis[1][0], projectedAxis[0][i], projectedAxis[1][i], null, buffer);
        }
    }
}
