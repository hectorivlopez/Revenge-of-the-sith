package models;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    public boolean povRight;
    public boolean povFront;

    public double[] angles;
    public double scale;
    public double ang;

    private int[][] leftAxis;
    private int[][] rightAxis;
    private double xInclination;
    int thickness;

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

        this.thickness = 5;
        this.xInclination = 0.25;

        this.leftAxis = new int[][]{new int[]{xc + 240, yc + 30, zc + thickness}, new int[]{xc - 10, yc + 30, zc + thickness},};
        this.rightAxis = new int[][]{new int[]{xc + 240, yc - 30, zc + thickness}, new int[]{xc - 10, yc - 30, zc + thickness},};

    }

    public void draw(int[] director, String projection, BufferedImage buffer, double ang) {
        this.ang = ang;

        boolean[] povCenter = calPov(center, director, projection);
        povUp = povCenter[0];
        povRight = povCenter[1];
        povFront = povCenter[2];

        /*if (povRight) {
            drawLeftWing(director, projection, true, buffer);
            drawCabin(director, projection, true, buffer);
            drawRightWing(director, projection, true, buffer);
        }
        else {
            drawRightWing(director, projection, true, buffer);
            drawCabin(director, projection, true, buffer);
            drawLeftWing(director, projection, true, buffer);
        }*/

        drawLeftWing(director, projection, true, buffer);
    }

    public void drawCabin(int[] director, String projection, boolean fill, BufferedImage buffer) {
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

        int[] z4Points = new int[numSides];
        int[] y4Points = new int[numSides];

        int[] z5Points = new int[numSides];
        int[] y5Points = new int[numSides];

        for (int i = 0; i < numSides; i++) {
            double angle = i * angleStep + angleStep / 2;

            z0Points[i] = (int) (zc + 25 * Math.cos(angle));
            y0Points[i] = (int) (yc + 25 * Math.sin(angle));

            z1Points[i] = (int) (zc + 30 * Math.cos(angle));
            y1Points[i] = (int) (yc + 30 * Math.sin(angle));

            z2Points[i] = (int) (zc + 55 * Math.cos(angle));
            y2Points[i] = (int) (yc + 55 * Math.sin(angle));

            z3Points[i] = (int) (zc + 60 * Math.cos(angle));
            y3Points[i] = (int) (yc + 60 * Math.sin(angle));

            z4Points[i] = (int) (zc + 60 * Math.cos(angle));
            y4Points[i] = (int) (yc + 60 * Math.sin(angle));

            z5Points[i] = (int) (zc + 50 * Math.cos(angle));
            y5Points[i] = (int) (yc + 50 * Math.sin(angle));
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

       /* drawSurface(transformedFace0, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace1, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace2, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace3, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace4, director, projection, -1, false, Color.red, null, buffer);
        drawSurface(transformedFace5, director, projection, -1, false, Color.red, null, buffer);
*/
        int[][][] levels = new int[][][]{transformedFace0, transformedFace1, transformedFace2, transformedFace3, transformedFace4, transformedFace5};
        drawPolyhedronFaces(levels, null, 1, director, projection, Color.white, Color.blue, buffer);


    }

    public void drawLeftWing(int[] director, String projection, boolean fill, BufferedImage buffer) {
        int zTop = zc + thickness;
        int zBottom = zc - thickness;

        // Top
        int[][] leftWingTop = new int[][]{
                new int[]{xc + 240, xc + 250, xc + 100, xc + 85, xc + 50, xc + 50, xc - 10, xc - 115, xc - 210, xc - 200, xc - 150, xc - 100, xc - 70, xc - 10},
                new int[]{yc + 30, yc + 60, yc + 100, yc + 90, yc + 90, yc + 110, yc + 190, yc + 190, yc + 60, yc + 50, yc + 60, yc + 60, yc + 55, yc + 30},
                new int[]{zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop,}
        };
        int[][] scaledLeftWingTop = transform3D(leftWingTop, center, scale, angles, new double[]{xInclination}, new int[][][]{leftAxis});

        // Bottom
        int[][] leftWingBottom = new int[][]{
                new int[]{xc + 240, xc + 250, xc + 100, xc + 85, xc + 50, xc + 50, xc - 10, xc - 115, xc - 210, xc - 200, xc - 150, xc - 100, xc - 70, xc - 10},
                new int[]{yc + 30, yc + 60, yc + 100, yc + 90, yc + 90, yc + 110, yc + 190, yc + 190, yc + 60, yc + 50, yc + 60, yc + 60, yc + 55, yc + 30},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledLeftWingBottom = transform3D(leftWingBottom, center, scale, angles, new double[]{xInclination}, new int[][][]{leftAxis});

        int[][][] leftFaces = new int[][][]{scaledLeftWingTop, scaledLeftWingBottom};
        //drawPolyhedronFaces(leftFaces, null, -1, director, projection, Color.white, Color.red, buffer);


    }

    public void drawRightWing(int[] director, String projection, boolean fill, BufferedImage buffer) {
        int zTop = zc + thickness;
        int zBottom = zc - thickness;

        // Top
        int[][] rightWingTop = new int[][]{
                new int[]{xc + 240, xc + 250, xc + 100, xc + 85, xc + 50, xc + 50, xc - 10, xc - 115, xc - 210, xc - 200, xc - 150, xc - 100, xc - 70, xc - 10},
                new int[]{yc - 30, yc - 60, yc - 100, yc - 90, yc - 90, yc - 110, yc - 190, yc - 190, yc - 60, yc - 50, yc - 60, yc - 60, yc - 55, yc - 30},
                new int[]{zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop, zTop,}
        };
        int[][] scaledRightWingTop = transform3D(rightWingTop, center, scale, angles, new double[]{-xInclination}, new int[][][]{rightAxis});

        // Bottom
        int[][] rightWingBottom = new int[][]{
                new int[]{xc + 240, xc + 250, xc + 100, xc + 85, xc + 50, xc + 50, xc - 10, xc - 115, xc - 210, xc - 200, xc - 150, xc - 100, xc - 70, xc - 10},
                new int[]{yc - 30, yc - 60, yc - 100, yc - 90, yc - 90, yc - 110, yc - 190, yc - 190, yc - 60, yc - 50, yc - 60, yc - 60, yc - 55, yc - 30},
                new int[]{zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom, zBottom,}
        };
        int[][] scaledRightWingBottom = transform3D(rightWingBottom, center, scale, angles, new double[]{-xInclination}, new int[][][]{rightAxis});

        int[][][] rightFaces = new int[][][]{scaledRightWingTop, scaledRightWingBottom};
        drawPolyhedronFaces(rightFaces, null, 1, director, projection, Color.white, Color.green, buffer);

    }

    public void drawDroid() {

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
        boolean povRight = backFaceCulling(rotatedXZPlane, null, director, projection, 1);
        boolean povFront = backFaceCulling(rotatedYZPlane, null, director, projection, 1);

        return new boolean[]{povUp, povRight, povFront};
    }

}
