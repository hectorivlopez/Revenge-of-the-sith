package models;

import graphics.Transformations;
import static graphics.Draw.*;
import static graphics.Draw3d.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Venator {

    public static void draw(int x0, int y0, int z0, double[] angles, double scale, int[] director, String projection, BufferedImage buffer) {
        // Rotation axis
        int xc = x0 + 300;
        int yc = y0;
        int zc = z0;

        // Faces
        int[][] topLeft = new int[][]{
                new int[]{x0 + 0, x0 + 0, x0 + 420, x0 + 485, x0 + 560, x0 + 675, x0 + 710, x0 + 680},
                new int[]{y0 + 17, y0 + 32, y0 + 137, y0 + 92, y0 + 107, y0 + 217, y0 + 217, y0 + 52},
                /*new int[]{100, 100, 100, 100, 100, 100, 100, 100},*/
                /*new int[]{100, 104, 116, 99, 101, 127, 126, 79},*/
                new int[]{z0 - 15, z0 - 8, z0 - 8, z0 - 23, z0 - 23, z0 - 5, z0 - 5, z0 - 36},
        };

        int[][] rotatedTopLeft = rotate(topLeft, angles, x0, y0, z0);
        int[][] scaledTopLeft = Transformations.scale3D(rotatedTopLeft[0], rotatedTopLeft[1], rotatedTopLeft[2], xc, yc, zc, false, scale, scale, scale);

        int[][] topRight = new int[][]{
                new int[]{x0 + 0, x0 + 0, x0 + 420, x0 + 485, x0 + 560, x0 + 675, x0 + 710, x0 + 680},
                new int[]{y0 - 17, y0 - 32, y0 - 137, y0 - 92, y0 - 107, y0 - 217, y0 - 217, y0 - 52},
                /*new int[]{100, 100, 87, 85, 83, 79, 78, 79},*/
                new int[]{z0 - 15, z0 - 8, z0 - 8, z0 - 23, z0 - 23, z0 - 5, z0 - 5, z0 - 36},

        };

        int[][] rotatedTopRight = rotate(topRight, angles, x0, y0, z0);
        int[][] scaledTopRight = Transformations.scale3D(rotatedTopRight[0], rotatedTopRight[1], rotatedTopRight[2], xc, yc, zc, false, scale, scale, scale);

        int[][] topRed = new int[][]{
                new int[]{x0 + 0, x0 + 420, x0 + 430, x0 + 420, x0 + 0},
                new int[]{y0 + 17, y0 + 39, y0 + 0, y0 - 39, y0 - 17},
                new int[]{z0 - 15, z0 - 28, z0 - 28, z0 - 28, z0 - 15},
        };

        int[][] rotatedTopRed = rotate(topRed, angles, x0, y0, z0);
        int[][] scaledTopRed = Transformations.scale3D(rotatedTopRed[0], rotatedTopRed[1], rotatedTopRed[2], xc, yc, zc, false, scale, scale, scale);

        int[][] topCenter = new int[][]{
                new int[]{x0 + 420, x0 + 545, x0 + 545, x0 + 505, x0 + 505, x0 + 465, x0 + 465, x0 + 505, x0 + 505, x0 + 545, x0 + 545, x0 + 420, x0 + 430},
                new int[]{y0 + 39, y0 + 45, y0 + 25, y0 + 25, y0 + 12, y0 + 12, y0 - 12, y0 - 12, y0 -  25, y0 - 25, y0 - 45, y0 - 39, y0 - 0},
                new int[]{z0 - 28, z0 - 32, z0 - 32, z0 - 31, z0 - 31, z0 - 29, z0 - 29, z0 - 31, z0 - 31, z0 - 32, z0 - 32, z0 - 28, z0 - 28},
        };

        int[][] rotatedTopCenter = rotate(topCenter, angles, x0, y0, z0);
        int[][] scaledTopCenter = Transformations.scale3D(rotatedTopCenter[0], rotatedTopCenter[1], rotatedTopCenter[2], xc, yc, zc, false, scale, scale, scale);

        int[][] bottomLeft = new int[][]{
                new int[]{x0 + 0, x0 + 0, x0 + 420, x0 + 485, x0 + 560, x0 + 675, x0 + 720, x0 + 785},
                new int[]{y0, y0 + 32, y0 + 137, y0 + 92, y0 + 107, y0 + 217, y0 + 217, y0},
                /*new int[]{130, 130, 130, 130, 130, 130, 130, 130},*/
                new int[]{z0 + 15, z0 + 8, z0 + 8, z0 + 23, z0 + 23, z0 + 5, z0 + 5, z0 + 38},
        };

        int[][] rotatedBottomLeft = rotate(bottomLeft, angles, x0, y0, z0);
        int[][] scaledBottomLeft = Transformations.scale3D(rotatedBottomLeft[0], rotatedBottomLeft[1], rotatedBottomLeft[2], xc, yc, zc, false, scale, scale, scale);

        int[][] bottomRight = new int[][]{
                new int[]{x0 + 0, x0 + 0, x0 + 420, x0 + 485, x0 + 560, x0 + 675, x0 + 720, x0 + 785},
                new int[]{y0, y0 - 32, y0 - 137, y0 - 92, y0 - 107, y0 - 217, y0 - 217, y0},
                /*new int[]{130, 130, 130, 130, 130, 130, 130, 130},*/
                new int[]{z0 + 15, z0 + 8, z0 + 8, z0 + 23, z0 + 23, z0 + 5, z0 + 5, z0 + 38},

        };

        int[][] rotatedBottomRight = rotate(bottomRight, angles, x0, y0, z0);
        int[][] scaledBottomRight = Transformations.scale3D(rotatedBottomRight[0], rotatedBottomRight[1], rotatedBottomRight[2], xc, yc, zc, false, scale, scale, scale);



        int[][] right1 = new int[][]{
                new int[]{x0, x0 + 420, x0 + 420, x0},
                new int[]{y0 - 32, y0 - 137, y0 - 137, y0 -32},
                new int[]{z0 - 8, z0 - 8, z0 + 8, z0 + 8},
        };

        int[][] rotatedRight1 = rotate(right1, angles, x0, y0, z0);
        int[][] scaledRight1 = Transformations.scale3D(rotatedRight1[0], rotatedRight1[1], rotatedRight1[2], xc, yc, zc, false, scale, scale, scale);

        int[][] right2 = new int[][]{
                new int[]{x0 + 420, x0 + 485, x0 + 485, x0 + 420},
                new int[]{y0 - 137, y0 - 92, y0 - 92, y0 - 137},
                new int[]{z0 - 8, z0 - 23, z0 + 23, z0 + 8},
        };

        int[][] rotatedRight2 = rotate(right2, angles, x0, y0, z0);
        int[][] scaledRight2 = Transformations.scale3D(rotatedRight2[0], rotatedRight2[1], rotatedRight2[2], xc, yc, zc, false, scale, scale, scale);

        int[][] right3 = new int[][]{
                new int[]{x0 + 485, x0 + 560, x0 + 560, x0 + 485},
                new int[]{y0 - 92, y0 - 107, y0 - 107, y0 - 92},
                new int[]{z0 - 23, z0 - 23, z0 + 23, z0 + 23},
        };

        int[][] rotatedRight3 = rotate(right3, angles, x0, y0, z0);
        int[][] scaledRight3 = Transformations.scale3D(rotatedRight3[0], rotatedRight3[1], rotatedRight3[2], xc, yc, zc, false, scale, scale, scale);

        int[][] right4 = new int[][]{
                new int[]{x0 + 560, x0 + 675, x0 + 675, x0 + 560},
                new int[]{y0 - 107, y0 - 217, y0 - 217, y0 - 107},
                new int[]{z0 - 23, z0 - 5, z0 + 5, z0 + 23},
        };

        int[][] rotatedRight4 = rotate(right4, angles, x0, y0, z0);
        int[][] scaledRight4 = Transformations.scale3D(rotatedRight4[0], rotatedRight4[1], rotatedRight4[2], xc, yc, zc, false, scale, scale, scale);

        int[][] right5 = new int[][]{
                new int[]{x0 + 675, x0 + 710, x0 + 710, x0 + 675},
                new int[]{y0 - 217, y0 - 217, y0 - 217, y0 - 217},
                new int[]{z0 - 5, z0 - 5, z0 + 5, z0 + 5},
        };

        int[][] rotatedRight5 = rotate(right5, angles, x0, y0, z0);
        int[][] scaledRight5 = Transformations.scale3D(rotatedRight5[0], rotatedRight5[1], rotatedRight5[2], xc, yc, zc, false, scale, scale, scale);


        int[][] left1 = new int[][]{
                new int[]{x0, x0 + 420, x0 + 420, x0},
                new int[]{y0 + 32, y0 + 137, y0 + 137, y0 + 32},
                new int[]{z0 - 8, z0 - 8, z0 + 8, z0 + 8},
        };

        int[][] rotatedLeft1 = rotate(left1, angles, x0, y0, z0);
        int[][] scaledLeft1 = Transformations.scale3D(rotatedLeft1[0], rotatedLeft1[1], rotatedLeft1[2], xc, yc, zc, false, scale, scale, scale);

        int[][] left2 = new int[][]{
                new int[]{x0 + 420, x0 + 485, x0 + 485, x0 + 420},
                new int[]{y0 + 137, y0 + 92, y0 + 92, y0 + 137},
                new int[]{z0 - 8, z0 - 23, z0 + 23, z0 + 8},
        };

        int[][] rotatedLeft2 = rotate(left2, angles, x0, y0, z0);
        int[][] scaledLeft2 = Transformations.scale3D(rotatedLeft2[0], rotatedLeft2[1], rotatedLeft2[2], xc, yc, zc, false, scale, scale, scale);

        int[][] left3 = new int[][]{
                new int[]{x0 + 485, x0 + 560, x0 + 560, x0 + 485},
                new int[]{y0 + 92, y0 + 107, y0 + 107, y0 + 92},
                new int[]{z0 - 23, z0 - 23, z0 + 23, z0 + 23},
        };

        int[][] rotatedLeft3 = rotate(left3, angles, x0, y0, z0);
        int[][] scaledLeft3 = Transformations.scale3D(rotatedLeft3[0], rotatedLeft3[1], rotatedLeft3[2], xc, yc, zc, false, scale, scale, scale);

        int[][] left4 = new int[][]{
                new int[]{x0 + 560, x0 + 675, x0 + 675, x0 + 560},
                new int[]{y0 + 107, y0 + 217, y0 + 217, y0 + 107},
                new int[]{z0 - 23, z0 - 5, z0 + 5, z0 + 23},
        };

        int[][] rotatedLeft4 = rotate(left4, angles, x0, y0, z0);
        int[][] scaledLeft4 = Transformations.scale3D(rotatedLeft4[0], rotatedLeft4[1], rotatedLeft4[2], xc, yc, zc, false, scale, scale, scale);

        int[][] left5 = new int[][]{
                new int[]{x0 + 675, x0 + 710, x0 + 710, x0 + 675},
                new int[]{y0 + 217, y0 + 217, y0 + 217, y0 + 217},
                new int[]{z0 - 5, z0 - 5, z0 + 5, z0 + 5},
        };

        int[][] rotatedLeft5 = rotate(left5, angles, x0, y0, z0);
        int[][] scaledLeft5 = Transformations.scale3D(rotatedLeft5[0], rotatedLeft5[1], rotatedLeft5[2], xc, yc, zc, false, scale, scale, scale);




        int[] p1 = new int[]{topRed[0][0], topRed[1][2], topRed[2][0]};
        int[] p2 = new int[]{topRed[0][2], topRed[1][2], topRed[2][0]};







        /*for(int i = 0; i < bottomLeft.length; i++) {
            for(int j = 0; j < bottomLeft[0].length; j++) {
                System.out.print(rotated4[i][j] + ", ");

            }
            System.out.println("");
        }*/
        surface(scaledTopLeft, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledTopRight, 0, p1, p2, director, projection, -1, Color.white, buffer);

        prism(scaledTopCenter, 4, director, 1, null, projection, 1, Color.white, buffer);
        prism(scaledTopRed, 4, director, 1, null, projection, 1, Color.red, buffer);
        //surface(scaledTopRed, 0, p1, p2, director, projection, 1, Color.red, buffer);

        surface(scaledBottomLeft, 0, p1, p2, director, projection, -1, Color.white, buffer);
        surface(scaledBottomRight, 0, p1, p2, director, projection, 1, Color.white, buffer);

        surface(scaledRight1, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledRight2, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledRight3, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledRight4, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledRight5, 0, p1, p2, director, projection, 1, Color.white, buffer);

        surface(scaledLeft1, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledLeft2, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledLeft3, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledLeft4, 0, p1, p2, director, projection, 1, Color.white, buffer);
        surface(scaledLeft5, 0, p1, p2, director, projection, 1, Color.white, buffer);
    }

    private static int[][] rotate(int[][] face, double[] angles, int x0, int y0, int z0) {
        int xc = x0 + 300;
        int yc = y0;
        int zc = z0;

        int[] xAxis1 = new int[]{x0, y0, z0};
        int[] xAxis2 = new int[]{x0 + 100, y0, z0};

        int[] yAxis1 = new int[]{x0 + xc, y0 - 10, z0};
        int[] yAxis2 = new int[]{x0 + xc, y0 + 10, z0};

        int[] zAxis1 = new int[]{x0 + xc, y0, z0 - 10};
        int[] zAxis2 = new int[]{x0 + xc, y0, z0 + 10};

        int[][] rotatedXFace = Transformations.rotateAroundLine(face[0], face[1], face[2], xAxis1, xAxis2, angles[0]);
        int[][] rotatedYFace = Transformations.rotateAroundLine(rotatedXFace[0], rotatedXFace[1], rotatedXFace[2], yAxis1, yAxis2, angles[1]);
        int[][] rotatedZFace = Transformations.rotateAroundLine(rotatedYFace[0], rotatedYFace[1], rotatedYFace[2], zAxis1, zAxis2, angles[2]);

        return rotatedZFace;
    }
}
