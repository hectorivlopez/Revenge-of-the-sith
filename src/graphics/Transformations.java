package graphics;

import java.util.Arrays;

public class Transformations {
    // -------------------- 2D --------------------
    public static int[][] translate(int[] xPoints, int[] yPoints, int xMove, int yMove) {
        int[][] initialMatrix = new int[xPoints.length][xPoints.length];
        initialMatrix[0] = xPoints;
        initialMatrix[1] = yPoints;

        for(int i = 2; i < xPoints.length; i++) {
            for(int j = 0; j < xPoints.length; j++) {
                initialMatrix[i][j] = 1;
            }
        }

        double[][] translateMatrix = new double[xPoints.length][xPoints.length];
        for(int i = 0; i < xPoints.length; i++) {
            for(int j = 0; j < xPoints.length; j++) {
                if(i == j) {
                    translateMatrix[i][j] = 1;
                }
                else {
                    translateMatrix[i][j] = 0;
                }
            }
        }

        translateMatrix[0][xPoints.length - 1] = xMove;
        translateMatrix[1][xPoints.length - 1] = yMove;

        int[][] resultMatrix = Utils.multiplyMatrices(translateMatrix, initialMatrix);

        return resultMatrix;
    }

    public static int[][] scale(int[] xPoints, int[] yPoints, int xc, int yc, boolean center, double xScale, double yScale) {
        int xCenter = 0 + xc;
        int yCenter = 0 + yc;

        // Calculate the center
        if (center) {
            int sumX = 0;
            int sumY = 0;

            for (int i = 0; i < xPoints.length; i++) {
                sumX += xPoints[i];
                sumY += yPoints[i];
            }

            xCenter = sumX / xPoints.length;
            yCenter = sumY / yPoints.length;
        }

        // Create initial matrix
        int[][] initialMatrix = new int[xPoints.length][xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            initialMatrix[0][i] = xPoints[i];
            initialMatrix[1][i] = yPoints[i];
        }


        // Setting center to [0, 0]
        for (int i = 0; i < xPoints.length; i++) {
            initialMatrix[0][i] -= xCenter;
            initialMatrix[1][i] -= yCenter;
        }

        for (int i = 2; i < xPoints.length; i++) {
            for (int j = 0; j < xPoints.length; j++) {
                initialMatrix[i][j] = 1;
            }
        }

        // Create resizing matrix
        double[][] scalelMatrix = new double[xPoints.length][xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            for (int j = 0; j < xPoints.length; j++) {
                if (i == j) {
                    scalelMatrix[i][j] = 1;
                } else {
                    scalelMatrix[i][j] = 0;
                }
            }
        }

        scalelMatrix[0][0] = xScale;
        scalelMatrix[1][1] = yScale;

        int[][] resultMatrix = Utils.multiplyMatrices(scalelMatrix, initialMatrix);

        // Translation to original center
        for (int i = 0; i < xPoints.length; i++) {
            resultMatrix[0][i] += xCenter;
            resultMatrix[1][i] += yCenter;
        }

        return resultMatrix;
    }

    public static int[][] rotate(int[] xPoints, int[] yPoints, int xc, int yc, boolean center, double angle) {
        int xCenter = 0 + xc;
        int yCenter = 0 + yc;

        // Calculate the center
        if (center) {
            int sumX = 0;
            int sumY = 0;

            for (int i = 0; i < xPoints.length; i++) {
                sumX += xPoints[i];
                sumY += yPoints[i];
            }

            // Posible problema
            xCenter = sumX / xPoints.length;
            yCenter = sumY / yPoints.length;
        }

        // Create initial matrix
        int[][] initialMatrix = new int[xPoints.length][xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            initialMatrix[0][i] = xPoints[i];
            initialMatrix[1][i] = yPoints[i];
        }

        // Setting center to [0, 0]
        for (int i = 0; i < xPoints.length; i++) {
            initialMatrix[0][i] -= xCenter;
            initialMatrix[1][i] -= yCenter;
        }

        for (int i = 2; i < xPoints.length; i++) {
            for (int j = 0; j < xPoints.length; j++) {
                initialMatrix[i][j] = 1;
            }
        }

        // Create rotator matrix
        double[][] rotatelMatrix = new double[xPoints.length][xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            for (int j = 0; j < xPoints.length; j++) {
                if (i == j) {
                    rotatelMatrix[i][j] = 1;
                } else {
                    rotatelMatrix[i][j] = 0;
                }
            }
        }

        rotatelMatrix[0][0] = Math.cos(angle);
        rotatelMatrix[0][1] = -1 * Math.sin(angle);
        rotatelMatrix[1][0] = Math.sin(angle);
        rotatelMatrix[1][1] = Math.cos(angle);

        int[][] resultMatrix = Utils.multiplyMatrices(rotatelMatrix, initialMatrix);

        // Translation to original center
        for (int i = 0; i < xPoints.length; i++) {
            resultMatrix[0][i] += xCenter;
            resultMatrix[1][i] += yCenter;
        }

        return resultMatrix;
    }

    // -------------------- 3D --------------------
    public static int[][] translate3d(int[] xPoints, int[] yPoints, int[] zPoints, int dx, int dy, int dz) {
        int[][] initialMatrix = new int[xPoints.length][xPoints.length];
        initialMatrix[0] = xPoints;
        initialMatrix[1] = yPoints;
        initialMatrix[2] = zPoints;

        for(int i = 3; i < xPoints.length; i++) {
            for(int j = 0; j < xPoints.length; j++) {
                initialMatrix[i][j] = 1;
            }
        }

        double[][] translateMatrix = new double[xPoints.length][xPoints.length];
        for(int i = 0; i < xPoints.length; i++) {
            for(int j = 0; j < xPoints.length; j++) {
                if(i == j) {
                    translateMatrix[i][j] = 1;
                }
                else {
                    translateMatrix[i][j] = 0;
                }
            }
        }

        translateMatrix[0][xPoints.length - 1] = dx;
        translateMatrix[1][xPoints.length - 1] = dy;
        translateMatrix[2][xPoints.length - 1] = dz;

        int[][] resultMatrix = Utils.multiplyMatrices(translateMatrix, initialMatrix);

        return resultMatrix;
    }

    public static int[][] scale3D(int[] xPoints, int[] yPoints, int[] zPoints, int xc, int yc, int zc, boolean center, double xScale, double yScale, double zScale) {
        int xCenter = 0 + xc;
        int yCenter = 0 + yc;
        int zCenter = 0 + zc;

        // Calculate the center
        if (center) {
            int sumX = 0;
            int sumY = 0;
            int sumZ = 0;

            for (int i = 0; i < xPoints.length; i++) {
                sumX += xPoints[i];
                sumY += yPoints[i];
                sumZ += zPoints[i];
            }

            xCenter = sumX / xPoints.length;
            yCenter = sumY / yPoints.length;
            zCenter = sumZ / zPoints.length;
        }

        // Create initial matrix
        int[][] initialMatrix = new int[xPoints.length][xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            initialMatrix[0][i] = 0 + xPoints[i];
            initialMatrix[1][i] = 0 + yPoints[i];
            initialMatrix[2][i] = 0 + zPoints[i];
        }

        // Setting center to [0, 0]
        for (int i = 0; i < xPoints.length; i++) {
            initialMatrix[0][i] -= xCenter;
            initialMatrix[1][i] -= yCenter;
            initialMatrix[2][i] -= zCenter;
        }

        for (int i = 3; i < xPoints.length; i++) {
            for (int j = 0; j < xPoints.length; j++) {
                initialMatrix[i][j] = 1;
            }
        }

        // Create resizing matrix
        double[][] scaleMatrix = new double[xPoints.length][xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            for (int j = 0; j < xPoints.length; j++) {
                if (i == j) {
                    scaleMatrix[i][j] = 1;
                } else {
                    scaleMatrix[i][j] = 0;
                }
            }
        }

        scaleMatrix[0][0] = xScale;
        scaleMatrix[1][1] = yScale;
        scaleMatrix[2][2] = zScale;

        int[][] resultMatrix = Utils.multiplyMatrices(scaleMatrix, initialMatrix);

        // Translation to origin al center
        for (int i = 0; i < xPoints.length; i++) {
            resultMatrix[0][i] += xCenter;
            resultMatrix[1][i] += yCenter;
            resultMatrix[2][i] += zCenter;
        }
            /*for(int i = 0; i < initialMatrix.length; i++) {
                System.out.print("[ ");
                for(int j = 0; j < initialMatrix[0].length; j++) {
                    System.out.print(initialMatrix[i][j] + ", ");
                }
                System.out.println("]");
            }*/

        return resultMatrix;
    }

    public static int[][] rotate3D(int[] xPoints, int[] yPoints, int[] zPoints, int xc, int yc, int zc, boolean center, double angleX, double angleY, double angleZ) {
        int[][] rotatedPoints = new int[][] {
                xPoints,
                yPoints,
                zPoints
        };

        if(angleX != 0) {
            int[][] rotated = rotate(rotatedPoints[1], rotatedPoints[2], yc, zc, center, angleX);
            rotatedPoints = new int[][] {rotatedPoints[0], rotated[0], rotated[1]};
        }
        if(angleY != 0) {
            int[][] rotated = rotate(rotatedPoints[0], rotatedPoints[2], xc, zc, center, angleY);
            rotatedPoints = new int[][] {rotated[0], rotatedPoints[1], rotated[1]};
        }
        if(angleZ != 0) {
            int[][] rotated = rotate(rotatedPoints[0], rotatedPoints[1], xc, yc, center, angleZ);
            rotatedPoints = new int[][] {rotated[0], rotated[1], rotatedPoints[2]};
        }

        return rotatedPoints;
    }

    public static int[][] rotateAroundLine(int[] xPoints, int[] yPoints, int[] zPoints, int[] pointA, int[] pointB, double angle) {
        // Make pointA the origin
        double x0 = pointA[0];
        double y0 = pointA[1];
        double z0 = pointA[2];

        // Vector along the line (pointB - pointA)
        double ux = pointB[0] - x0;
        double uy = pointB[1] - y0;
        double uz = pointB[2] - z0;
        double norm = Math.sqrt(ux * ux + uy * uy + uz * uz);
        ux /= norm;
        uy /= norm;
        uz /= norm;

        double cosTheta = Math.cos(angle);
        double sinTheta = Math.sin(angle);

        double[][] resultMatrix = new double[3][xPoints.length];

        // Rotation
        for (int i = 0; i < xPoints.length; i++) {
            // Translate point to origin
            double x = xPoints[i] - x0;
            double y = yPoints[i] - y0;
            double z = zPoints[i] - z0;

            // Rotation matrix components
            double dotProduct = x * ux + y * uy + z * uz;
            double crossProductX = uy * z - uz * y;
            double crossProductY = uz * x - ux * z;
            double crossProductZ = ux * y - uy * x;

            double rotatedX = x * cosTheta + crossProductX * sinTheta + ux * dotProduct * (1 - cosTheta);
            double rotatedY = y * cosTheta + crossProductY * sinTheta + uy * dotProduct * (1 - cosTheta);
            double rotatedZ = z * cosTheta + crossProductZ * sinTheta + uz * dotProduct * (1 - cosTheta);

            // Back to original position
            resultMatrix[0][i] = rotatedX + x0;
            resultMatrix[1][i] = rotatedY + y0;
            resultMatrix[2][i] = rotatedZ + z0;
        }

        int[][] intResultMatrix = new int[3][xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            intResultMatrix[0][i] = (int) Math.round(resultMatrix[0][i]);
            intResultMatrix[1][i] = (int) Math.round(resultMatrix[1][i]);
            intResultMatrix[2][i] = (int) Math.round(resultMatrix[2][i]);
        }

        return intResultMatrix;
   }

    public static int[][] rotateRespectAxis(int[][] face, int[] center, double[] angles) {
        int xc = center[0];
        int yc = center[1];
        int zc = center[2];

        int x0 = xc + 100;
        int y0 = yc + 100;
        int z0 = zc + 100;

        int[] xAxis1 = new int[]{xc, yc, zc};
        int[] xAxis2 = new int[]{x0, yc, zc};

        int[][] rotatedXFace = rotateAroundLine(face[0], face[1], face[2], xAxis1, xAxis2, angles[0]);

        int[][] p0 = rotateAroundLine(new int[]{x0, xc, xc}, new int[]{yc, y0, yc}, new int[]{zc, zc, z0}, xAxis1, xAxis2, angles[0]);

        int[] yAxis1 = new int[]{xc, yc, zc};
        int[] yAxis2 = new int[]{p0[0][1], p0[1][1], p0[2][1]};

        int[][] rotatedYFace = rotateAroundLine(rotatedXFace[0], rotatedXFace[1], rotatedXFace[2], yAxis1, yAxis2, angles[1]);

        p0 = rotateAroundLine(p0[0], p0[1], p0[2], yAxis1, yAxis2, angles[1]);

        int[] zAxis1 = new int[]{xc, yc, zc};
        int[] zAxis2 = new int[]{p0[0][2], p0[1][2], p0[2][2]};

        int[][] rotatedZFace = rotateAroundLine(rotatedYFace[0], rotatedYFace[1], rotatedYFace[2], zAxis1, zAxis2, angles[2]);

        return rotatedZFace;

    }

    public static double[] getEulerAngles(double[] v1, double[] v2, double[] v3) {
        // Normalizar los vectores
        double[] n1 = normalize(v1);
        double[] n2 = normalize(v2);
        double[] n3 = normalize(v3);

        // Construir la matriz de rotación R
        double[][] R = {
                { n1[0], n2[0], n3[0] },
                { n1[1], n2[1], n3[1] },
                { n1[2], n2[2], n3[2] }
        };

        // Calcular los ángulos de Euler
        double[] angles = getEulerAngles(R);

        return angles;
    }

    private static double[] normalize(double[] v) {
        double norm = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        return new double[] { v[0] / norm, v[1] / norm, v[2] / norm };
    }

    public static double[] getEulerAngles(double[][] R) {
        double alpha, beta, gamma;

        // Asegúrate de que R es una matriz 3x3
        if (R.length != 3 || R[0].length != 3) {
            throw new IllegalArgumentException("La matriz de rotación debe ser 3x3");
        }

        // Calcular beta
        beta = Math.acos(R[2][2]);

        if (beta != 0 && beta != Math.PI) {
            // Calcular alpha
            alpha = Math.atan2(R[1][2] / Math.sin(beta), R[0][2] / Math.sin(beta));
            // Calcular gamma
            gamma = Math.atan2(R[2][1] / Math.sin(beta), -R[2][0] / Math.sin(beta));
        } else {
            // Caso especial cuando beta es 0 o pi
            alpha = 0;
            if (beta == 0) {
                gamma = Math.atan2(R[1][0], R[0][0]);
            } else {
                gamma = Math.atan2(-R[1][0], -R[0][0]);
            }
        }

        // Devuelve los ángulos de Euler en un array
        return new double[] { alpha, beta, gamma };
    }

    public static int[][] rotateRespectEuler(int[][] face, int[] center, double[] angles) {
        int xc = center[0];
        int yc = center[1];
        int zc = center[2];

        int x0 = xc + 100;
        int y0 = yc + 100;
        int z0 = zc + 100;

        int[] origin = new int[]{xc, yc, zc};

        // ---------- z rotation ----------
        int[] zEnd = new int[]{xc, yc, z0};

        int[][] rotatedFace = rotateAroundLine(face[0], face[1], face[2], origin, zEnd, angles[0]);

        int[][] ends = rotateAroundLine(new int[]{x0, xc, xc}, new int[]{yc, y0, yc}, new int[]{zc, zc, z0}, origin, zEnd, angles[0]);

        // ---------- y' rotation ----------
        int[] yEnd = new int[]{ends[0][1], ends[1][1], ends[2][1]};

        rotatedFace = rotateAroundLine(rotatedFace[0], rotatedFace[1], rotatedFace[2], origin, yEnd, angles[1]);

        ends = rotateAroundLine(ends[0], ends[1], ends[2], origin, yEnd, angles[1]);

        // ---------- z'' rotation ----------
        int[] z1End = new int[]{ends[0][2], ends[1][2], ends[2][2]};

        rotatedFace = rotateAroundLine(rotatedFace[0], rotatedFace[1], rotatedFace[2], origin, z1End, angles[2]);

        ends = rotateAroundLine(ends[0], ends[1], ends[2], origin, z1End, angles[2]);

        return rotatedFace;

    }

    public static int[][][] rotateAxis(int[][] face, int[] center, int[][] ends, double[] angles) {
        int xc = center[0];
        int yc = center[1];
        int zc = center[2];

        int[] x0 = ends[0];
        int[] y0 = ends[1];
        int[] z0 = ends[2];

        int[][] rotatedEnds = ends;
        int[][] rotatedFace = face;

        int[][] endsToRotate = new int[][]{
                new int[]{ends[0][0], ends[1][0], ends[2][0]},
                new int[]{ends[0][1], ends[1][1], ends[2][1]},
                new int[]{ends[0][2], ends[1][2], ends[2][2]},
        };

        // ---------- Rotate around x axis ----------
        if(angles[0] != 0) {
            int[] xAxis1 = new int[]{xc, yc, zc};
            int[] xAxis2 = new int[]{x0[0], x0[1], x0[2]};

            rotatedFace = rotateAroundLine(face[0], face[1], face[2], xAxis1, xAxis2, angles[0]);

            endsToRotate = rotateAroundLine(endsToRotate[0], endsToRotate[1], endsToRotate[2], xAxis1, xAxis2, angles[0]);
            rotatedEnds = new  int[][]{
                    new int[]{endsToRotate[0][0], endsToRotate[1][0], endsToRotate[2][0]},
                    new int[]{endsToRotate[0][1], endsToRotate[1][1], endsToRotate[2][1]},
                    new int[]{endsToRotate[0][2], endsToRotate[1][2], endsToRotate[2][2]},
            };
        }

        // ---------- Rotate around y axis ----------
        if(angles[1] != 0) {
            int[] yAxis1 = new int[]{xc, yc, zc};
            int[] yAxis2 = new int[]{rotatedEnds[1][0], rotatedEnds[1][1], rotatedEnds[1][2]};

            rotatedFace = rotateAroundLine(rotatedFace[0], rotatedFace[1], rotatedFace[2], yAxis1, yAxis2, angles[1]);

            endsToRotate = rotateAroundLine(endsToRotate[0], endsToRotate[1], endsToRotate[2], yAxis1, yAxis2, angles[1]);
            rotatedEnds = new  int[][]{
                    new int[]{endsToRotate[0][0], endsToRotate[1][0], endsToRotate[2][0]},
                    new int[]{endsToRotate[0][1], endsToRotate[1][1], endsToRotate[2][1]},
                    new int[]{endsToRotate[0][2], endsToRotate[1][2], endsToRotate[2][2]},
            };
        }


        // ---------- Rotate around z axis ----------
        if(angles[2] != 0) {
            int[] zAxis1 = new int[]{xc, yc, zc};
            int[] zAxis2 = new int[]{rotatedEnds[2][0], rotatedEnds[2][1], rotatedEnds[2][2]};

            rotatedFace = rotateAroundLine(rotatedFace[0], rotatedFace[1], rotatedFace[2], zAxis1, zAxis2, angles[2]);

            endsToRotate = rotateAroundLine(endsToRotate[0], endsToRotate[1], endsToRotate[2], zAxis1, zAxis2, angles[2]);
            rotatedEnds = new  int[][]{
                    new int[]{endsToRotate[0][0], endsToRotate[1][0], endsToRotate[2][0]},
                    new int[]{endsToRotate[0][1], endsToRotate[1][1], endsToRotate[2][1]},
                    new int[]{endsToRotate[0][2], endsToRotate[1][2], endsToRotate[2][2]},
            };
        }

       /* if(angles[1] == 0 && angles[2] == 0) rotatedEnds[0] = ends[0];
        if(angles[0] == 0 && angles[2] == 0) rotatedEnds[1] = ends[1];
        if(angles[0] == 0 && angles[1] == 0) rotatedEnds[2] = ends[2];*/

        return new int[][][]{rotatedFace, rotatedEnds};

    }

    public static int[][] transform3D(int[][] points, int[] center, double scale, double[] angles, double[] anglesAx, int[][][] axis) {
        int[][] pointsRot = new int[points.length][points[0].length];

        for (int i = 0; i < points.length; i++) {
            pointsRot[i] = Arrays.copyOf(points[i], points[i].length);
        }

        if (anglesAx != null) {
            for (int i = 0; i < anglesAx.length; i++) {
                pointsRot = rotateAroundLine(pointsRot[0], pointsRot[1], pointsRot[2], axis[i][0], axis[i][1], anglesAx[i]);
            }
        }

        int[][] rotatedPoints = rotateRespectEuler(pointsRot, center, angles);
        int[][] scaledPoints = scale3D(rotatedPoints[0], rotatedPoints[1], rotatedPoints[2], center[0], center[1], center[2], false, scale, scale, scale);

        return scaledPoints;
    }


    /*public static int[][] transform3D(int[][] points, int[] center, double scale, double[] angles, double[] anglesAx, int[][][] axis) {
        int[][] pointsRot = new int[points.length][points[0].length];

        for (int i = 0; i < points.length; i++) {
            pointsRot[i] = Arrays.copyOf(points[i], points[i].length);
        }

        if (anglesAx != null) {
            for (int i = 0; i < anglesAx.length; i++) {
                pointsRot = rotateAroundLine(pointsRot[0], pointsRot[1], pointsRot[2], axis[i][0], axis[i][1], anglesAx[i]);
            }
        }

        int[][] rotatedPoints = rotateRespectAxis(pointsRot, center, angles);
        int[][] scaledPoints = scale3D(rotatedPoints[0], rotatedPoints[1], rotatedPoints[2], center[0], center[1], center[2], false, scale, scale, scale);

        return scaledPoints;
    }
*/

}
