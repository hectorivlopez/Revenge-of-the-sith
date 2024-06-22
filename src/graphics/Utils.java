package graphics;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Utils {
    public static int[][] multiplyMatrices(double[][] scaleMatrix, int[][] initialMatrix) {
        int n = scaleMatrix.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double newValue = 0;
                for (int k = 0; k < n; k++) {
                    newValue += scaleMatrix[i][k] * (double) initialMatrix[k][j];
                }
                result[i][j] = (int) newValue;
            }
        }

        return result;
    }

    public static int[] calVector(int length, double angle, String angUnit) {
        if (!angUnit.equals("R")) angle = Math.toRadians(angle);
        while (angle < 0) angle += (2 * Math.PI);

        double m = Math.tan(angle);
        int dx, dy;

        if (Math.abs(m) < 1) {
            int xDirection = angle > (Math.PI / 2) && angle < (3 * Math.PI / 2) ? -1 : 1;

            dx = xDirection * (int) Math.round(length / Math.sqrt(1 + m * m));
            dy = (int) Math.round(m * dx);
        } else {
            int yDirection = angle > 0 && angle < Math.PI ? 1 : -1;

            dy = yDirection * (int) Math.round(length / Math.sqrt(1 + 1 / (m * m)));
            dx = (int) Math.round(dy / m);
        }

        return new int[]{dx, dy};
    }

    public static int[] calVectorOrigin(int x0, int y0, int length, double angle, String angUnit) {
        // Calculate the direction vectors for the lines
        int[] vector = calVector(length, angle, angUnit);


        return new int[]{x0 + vector[0], y0 + vector[1]};
    }

    public static double[] calculatePerpendicularVector(int[] xPoints, int[] yPoints, int[] zPoints, int magnitude, double direction) {
        // Vectors u and v
        int[] A = new int[]{xPoints[0], yPoints[0], zPoints[0]};
        int[] B = new int[]{xPoints[1], yPoints[1], zPoints[1]};
        int[] C = new int[]{xPoints[2], yPoints[2], zPoints[2]};

        double[] u = new double[]{B[0] - A[0], B[1] - A[1], B[2] - A[2]};
        double[] v = new double[]{C[0] - A[0], C[1] - A[1], C[2] - A[2]};

        // Cross product u x v
        double[] normal = new double[]{
                u[1] * v[2] - u[2] * v[1],
                u[2] * v[0] - u[0] * v[2],
                u[0] * v[1] - u[1] * v[0]
        };

        // Normal vector
        double normalMagnitude = Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);
        if (normalMagnitude == 0) return new double[]{0, 0, 0};

        // Normalize the normal vector
        double[] normalized = new double[]{
                normal[0] / normalMagnitude,
                normal[1] / normalMagnitude,
                normal[2] / normalMagnitude
        };

        // Scale the normal vector
        double[] scaled = new double[]{
                /*(int)*/ (normalized[0] * magnitude * direction),
                /*(int)*/ (normalized[1] * magnitude * direction),
                /*(int)*/ (normalized[2] * magnitude * direction)
        };

        return scaled;
    }


    public static double calculateDotProduct(double[] a, double[] b) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
    }

    public static boolean isPointInPolygon(int x, int y, int[] polyX, int[] polyY) {
        boolean inside = false;
        int nPoints = polyX.length;
        for (int i = 0, j = nPoints - 1; i < nPoints; j = i++) {
            if ((polyY[i] > y) != (polyY[j] > y) &&
                    (x < (polyX[j] - polyX[i]) * (y - polyY[i]) / (polyY[j] - polyY[i]) + polyX[i])) {
                inside = !inside;
            }
        }
        return inside;
    }

    public static double circunf(String unit) {
        return unit.equals("R") ? 2 * Math.PI : 360;
    }

    public static int[] calCentroid(int[][] points) {
        int n = points.length;
        int m = points[0].length;
        int[] sums = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sums[i] += points[i][j];
            }
        }

        int[] centroid = new int[n];
        for (int i = 0; i < n; i++) {
            centroid[i] = (int) ((double) sums[i] / ((double) m));
        }
        return centroid;
    }

    public static int[] calProjectedCenter(int[][] points, int[] center, int[] director, String projection) {
        if (center == null) {
            center = calCentroid(points);
        }
        int[][] projectedCenter = Draw3d.projection(new int[][]{new int[]{center[0], 1, 1, 1}, new int[]{center[1], 1, 1, 1}, new int[]{center[2], 1, 1, 1}}, director, projection);
        return new int[]{projectedCenter[0][0], projectedCenter[1][0]};
    }

    public static int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Drawing
    public static int[] adjustStartFillPoint(int[] xPoints, int[] yPoints, int startX, int startY, BufferedImage buffer) {
        if (startX < 0) {
            // Find the rightmost line
            int maxIndex = 0;
            int max2Index = 0;

            int x1 = xPoints[0];
            int y1 = yPoints[0];

            for (int i = 1; i < xPoints.length; i++) {
                if (xPoints[i] > x1) {
                    x1 = xPoints[i];
                    y1 = yPoints[i];
                    maxIndex = i;
                }
            }

            if (maxIndex < xPoints.length - 1) max2Index = maxIndex + 1;

            int x2 = xPoints[max2Index];
            int y2 = yPoints[max2Index];

            if (maxIndex == 0) {
                if (xPoints[xPoints.length - 1] > x2) max2Index = xPoints.length - 1;
            }
            else if(maxIndex == xPoints.length - 1){
                if(xPoints[xPoints.length - 2] > x2) max2Index = xPoints.length - 2;
            }
            else if(xPoints[maxIndex - 1] > x2) max2Index = maxIndex - 1;

            x2 = xPoints[max2Index];
            y2 = yPoints[max2Index];

            // Calculate the center of the rightmost line
            startX = ((x1 + x2) / 2) - 2;
            startY = (y1 + y2) / 2;

            for(int i = 0; i < 10; i++){
                if(!isPointInPolygon(startX, startY, xPoints, yPoints)) startY++;
                else return new int[]{startX, startY};
            }

            startY = (y1 + y2) / 2;

            for(int i = 0; i < 10; i++){
                if(!isPointInPolygon(startX, startY, xPoints, yPoints)) startY--;
                else return new int[]{startX, startY};
            }
        }

        if (startX >= buffer.getWidth()) {

            // Find the rightmost line
            int minIndex = 0;
            int min2Index = 0;

            int x1 = xPoints[0];
            int y1 = yPoints[0];

            for (int i = 1; i < xPoints.length; i++) {
                if (xPoints[i] < x1) {
                    x1 = xPoints[i];
                    y1 = yPoints[i];
                    minIndex = i;
                }
            }

            if (minIndex < xPoints.length - 1) min2Index = minIndex + 1;

            int x2 = xPoints[min2Index];
            int y2 = yPoints[min2Index];

            if (minIndex == 0) {
                if (xPoints[xPoints.length - 1] < x2) min2Index = xPoints.length - 1;
            }
            else if(minIndex == xPoints.length - 1){
                if(xPoints[xPoints.length - 2] < x2) min2Index = xPoints.length - 2;
            }
            else if(xPoints[minIndex - 1] < x2) min2Index = minIndex - 1;

            x2 = xPoints[min2Index];
            y2 = yPoints[min2Index];

            // Calculate the center of the rightmost line
            startX = ((x1 + x2) / 2) + 5;
            startY = (y1 + y2) / 2;

            for(int i = 0; i < 10; i++){
                if(!isPointInPolygon(startX, startY, xPoints, yPoints)) startY++;
                else return new int[]{startX, startY};
            }

            startY = (y1 + y2) / 2;

            for(int i = 0; i < 10; i++){
                if(!isPointInPolygon(startX, startY, xPoints, yPoints)) startY--;
                else return new int[]{startX, startY};
            }
        }

        if (startY < 0) {
            // Find the rightmost line
            int maxIndex = 0;
            int max2Index = 0;

            int x1 = xPoints[0];
            int y1 = yPoints[0];

            for (int i = 1; i < xPoints.length; i++) {
                if (xPoints[i] > y1) {
                    x1 = xPoints[i];
                    y1 = yPoints[i];
                    maxIndex = i;
                }
            }

            if (maxIndex < xPoints.length - 1) max2Index = maxIndex + 1;

            int x2 = xPoints[max2Index];
            int y2 = yPoints[max2Index];

            if (maxIndex == 0) {
                if (xPoints[xPoints.length - 1] > y2) max2Index = xPoints.length - 1;
            }
            else if(maxIndex == xPoints.length - 1){
                if(xPoints[xPoints.length - 2] > y2) max2Index = xPoints.length - 2;
            }
            else if(xPoints[maxIndex - 1] > y2) max2Index = maxIndex - 1;

            x2 = xPoints[max2Index];
            y2 = yPoints[max2Index];

            // Calculate the center of the rightmost line
            startX = (y1 + y2) / 2;
            startY = ((x1 + x2) / 2) - 2;

            for(int i = 0; i < 10; i++){
                if(!isPointInPolygon(startX, startY, xPoints, yPoints)) startX++;
                else return new int[]{startX, startY};
            }

            startX = (y1 + y2) / 2;

            for(int i = 0; i < 10; i++){
                if(!isPointInPolygon(startX, startY, xPoints, yPoints)) startX--;
                else return new int[]{startX, startY};
            }
        }

        if (startY >= buffer.getHeight()) {
            // Find the rightmost line
            int maxIndex = 0;
            int max2Index = 0;

            int x1 = xPoints[0];
            int y1 = yPoints[0];

            for (int i = 1; i < xPoints.length; i++) {
                if (xPoints[i] < y1) {
                    x1 = xPoints[i];
                    y1 = yPoints[i];
                    maxIndex = i;
                }
            }

            if (maxIndex < xPoints.length - 1) max2Index = maxIndex + 1;

            int x2 = xPoints[max2Index];
            int y2 = yPoints[max2Index];

            if (maxIndex == 0) {
                if (xPoints[xPoints.length - 1] < y2) max2Index = xPoints.length - 1;
            }
            else if(maxIndex == xPoints.length - 1){
                if(xPoints[xPoints.length - 2] < y2) max2Index = xPoints.length - 2;
            }
            else if(xPoints[maxIndex - 1] < y2) max2Index = maxIndex - 1;

            x2 = xPoints[max2Index];
            y2 = yPoints[max2Index];

            // Calculate the center of the rightmost line
            startX = (y1 + y2) / 2;
            startY = ((x1 + x2) / 2) + 2;

            for(int i = 0; i < 10; i++){
                if(!isPointInPolygon(startX, startY, xPoints, yPoints)) startX++;
                else return new int[]{startX, startY};
            }

            startX = (y1 + y2) / 2;

            for(int i = 0; i < 10; i++){
                if(!isPointInPolygon(startX, startY, xPoints, yPoints)) startX--;
                else return new int[]{startX, startY};
            }
        }
        return new int[]{startX, startY};
    }

/*    public static int[] adjustStartFillPoint(int[] xPoints, int[] yPoints, int startX, int startY, BufferedImage buffer) {
        if (startX < 0) {
            // Find the rightmost line
            int maxIndex = 0;
            int max2Index = 0;
            int max3Index = 0;

            int x1 = xPoints[0];
            int y1 = yPoints[0];

            for (int i = 1; i < xPoints.length; i++) {
                if (xPoints[i] > x1) {
                    x1 = xPoints[i];
                    y1 = yPoints[i];
                    maxIndex = i;
                }
            }

            if (maxIndex < xPoints.length - 1) max2Index = maxIndex + 1;
            if (maxIndex > 0) max3Index = maxIndex - 1;

            if (maxIndex == 0) max3Index = xPoints.length - 1;
            else if (maxIndex == xPoints.length - 1) max2Index = 0;

            int x2 = xPoints[max2Index];
            int y2 = yPoints[max2Index];

            int x3 = xPoints[max3Index];
            int y3 = yPoints[max3Index];

            int[] cornerCentroid = calCentroid(new int[][]{new int[]{x1, x2, x3}, new int[]{y1, y2, y3}});
            int[] distanceToCorner = new int[] {
                    x1 - cornerCentroid[0], y1 - cornerCentroid[1]
            };
            double distance = Math.sqrt(Math.pow(distanceToCorner[0], 2) + Math.pow(distanceToCorner[1], 2));
            double[] normalVector = new double[]{(double) distanceToCorner[0] / distance, (double) distanceToCorner[1] / distance};

            // Calculate the center of the rightmost line
            startX = x1 - (int) (normalVector[0] * 20);
            startY = y1 - (int) (normalVector[1] * 20);
            Draw.fillCircle(x1, y1, 3, Color.blue, buffer);
            Draw.fillCircle(x2, y2, 3, Color.orange, buffer);
            Draw.fillCircle(x3, y3, 3, Color.red, buffer);

        }


        return new int[]{startX, startY};
    }*/


    public static void floodFill(int x, int y, Color targetColor, BufferedImage buffer) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            int originalColor = buffer.getRGB(x, y);

            if (originalColor == targetColor.getRGB() || x < 0 || x >= buffer.getWidth() - 1 || y < 0 || y >= buffer.getHeight() - 1) {
                return;
            }

            Deque<int[]> stack = new ArrayDeque<>();
            stack.push(new int[]{x, y});

            while (!stack.isEmpty()) {
                int[] currentPixel = stack.pop();
                int px = currentPixel[0];
                int py = currentPixel[1];

                if (buffer.getRGB(px, py) != targetColor.getRGB()) {
                    //buffer.setRGB(px, py, targetColor.getRGB());
                    Draw.draw(px, py, targetColor, buffer);

                    if (px + 1 >= 0 && px + 1 < buffer.getWidth() && py >= 0 && py < buffer.getHeight()) {
                        stack.push(new int[]{px + 1, py});
                    }
                    if (px - 1 >= 0 && px - 1 < buffer.getWidth() && py >= 0 && py < buffer.getHeight()) {
                        stack.push(new int[]{px - 1, py});
                    }
                    if (px >= 0 && px < buffer.getWidth() && py + 1 >= 0 && py + 1 < buffer.getHeight()) {
                        stack.push(new int[]{px, py + 1});
                    }
                    if (px >= 0 && px < buffer.getWidth() && py - 1 >= 0 && py - 1 < buffer.getHeight()) {
                        stack.push(new int[]{px, py - 1});
                    }

                }
            }

        }
    }

    public static void floodFillTarget(int x, int y, Color targetColor, Color fillColor, BufferedImage buffer) {
        int originalColor = buffer.getRGB(x, y);
        if (originalColor == fillColor.getRGB() || originalColor == targetColor.getRGB()) {
            return;
        }

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{x, y});

        while (!stack.isEmpty()) {
            int[] currentPixel = stack.pop();
            int px = currentPixel[0];
            int py = currentPixel[1];

            if (buffer.getRGB(px, py) != targetColor.getRGB() && buffer.getRGB(px, py) != fillColor.getRGB()) {
                buffer.setRGB(px, py, fillColor.getRGB());

                if (px + 1 >= 0 && px + 1 < buffer.getWidth() && py >= 0 && py < buffer.getHeight()) {
                    stack.push(new int[]{px + 1, py});
                }
                if (px - 1 >= 0 && px - 1 < buffer.getWidth() && py >= 0 && py < buffer.getHeight()) {
                    stack.push(new int[]{px - 1, py});
                }
                if (px >= 0 && px < buffer.getWidth() && py + 1 >= 0 && py + 1 < buffer.getHeight()) {
                    stack.push(new int[]{px, py + 1});
                }
                if (px >= 0 && px < buffer.getWidth() && py - 1 >= 0 && py - 1 < buffer.getHeight()) {
                    stack.push(new int[]{px, py - 1});
                }
            }
        }
    }

    public static void changeFill(int x, int y, Color targetColor, Color color, BufferedImage buffer) {
        int originalColor = buffer.getRGB(x, y);

        if (originalColor == color.getRGB()) {
            return;
        }

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{x, y});

        while (!stack.isEmpty()) {
            int[] currentPixel = stack.pop();
            int px = currentPixel[0];
            int py = currentPixel[1];

            if (buffer.getRGB(px, py) == targetColor.getRGB()) {
                //buffer.setRGB(px, py, targetColor.getRGB());
                Draw.draw(px, py, color, buffer);

                if (px + 1 >= 0 && px + 1 < buffer.getWidth() && py >= 0 && py < buffer.getHeight()) {
                    stack.push(new int[]{px + 1, py});
                }
                if (px - 1 >= 0 && px - 1 < buffer.getWidth() && py >= 0 && py < buffer.getHeight()) {
                    stack.push(new int[]{px - 1, py});
                }
                if (px >= 0 && px < buffer.getWidth() && py + 1 >= 0 && py + 1 < buffer.getHeight()) {
                    stack.push(new int[]{px, py + 1});
                }
                if (px >= 0 && px < buffer.getWidth() && py - 1 >= 0 && py - 1 < buffer.getHeight()) {
                    stack.push(new int[]{px, py - 1});
                }

            }
        }
    }

    public static void fillScanLine(int xc, int yc, Color targetColor, BufferedImage buffer) {
        int y = yc;
        while (y >= 0 && y < buffer.getHeight() && buffer.getRGB(xc, y) != targetColor.getRGB()) {
            int x = xc;
            while (x >= 0 && x < buffer.getWidth() && buffer.getRGB(x, y) != targetColor.getRGB()) {
                Draw.draw(x++, y, targetColor, buffer);
            }
            x = xc - 1;
            while (x >= 0 && x < buffer.getWidth() && buffer.getRGB(x, y) != targetColor.getRGB()) {
                Draw.draw(x--, y, targetColor, buffer);
            }
            y++;
        }
        y = yc - 1;
        while (y >= 0 && y < buffer.getHeight() && buffer.getRGB(xc, y) != targetColor.getRGB()) {
            int x = xc;
            while (x >= 0 && x < buffer.getWidth() && buffer.getRGB(x, y) != targetColor.getRGB()) {
                Draw.draw(x++, y, targetColor, buffer);
            }
            x = xc - 1;
            while (x >= 0 && x < buffer.getWidth() && buffer.getRGB(x, y) != targetColor.getRGB()) {
                Draw.draw(x--, y, targetColor, buffer);
            }
            y--;
        }

    }


    public static Color[] generateColorVariants(Color baseColor, int numberOfVariants) {
        Color[] colorVariants = new Color[numberOfVariants];
        int step = 10; // Define the step size for each color component adjustment
        int totalSteps = numberOfVariants / 3 + 1; // Calculate total steps needed for each component to vary enough

        for (int i = 0; i < numberOfVariants; i++) {
            int red = baseColor.getRed();
            int green = baseColor.getGreen();
            int blue = baseColor.getBlue();

            // Adjust each color component in sequence to ensure all colors are different
            if (i % 3 == 0) {
                red = clampColorValue(baseColor.getRed() + (i / 3) * step);
            } else if (i % 3 == 1) {
                green = clampColorValue(baseColor.getGreen() + (i / 3) * step);
            } else if (i % 3 == 2) {
                blue = clampColorValue(baseColor.getBlue() + (i / 3) * step);
            }

            colorVariants[i] = new Color(red, green, blue);
        }

        return colorVariants;
    }

    private static int clampColorValue(int value) {
        return Math.max(0, Math.min(255, value));
    }

    public static int calcBit(int number) {
        if (number <= 0) return 0;
        else return 1;
    }

    public static void calcEndsPos(int[][] endsPos, int xMin, int xMax, int yMin, int yMax, LineToCrop line) {
        for (int j = 0; j <= 1; j++) {
            endsPos[j][0] = calcBit(yMin - line.ends[j][1]);
            endsPos[j][1] = calcBit(line.ends[j][1] - yMax);
            endsPos[j][2] = calcBit(xMin - line.ends[j][0]);
            endsPos[j][3] = calcBit(line.ends[j][0] - xMax);
        }
    }
}
