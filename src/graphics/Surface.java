package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Surface {
    public int[][] points;
    public int direction;
    public boolean develop;
    public int[] fillCenter;
    public Color borderColor;
    public Color color;

    public Surface(int[][] points, int direction, Color borderColor, Color color) {
        this.points = points;
        this.direction = direction;
        this.borderColor = borderColor;
        this.color = color;
        this.develop = false;
        this.fillCenter = null;
    }

    public void draw(int[] director, String projection, BufferedImage buffer) {
        Draw3d.surface(points, director, projection, direction, develop, borderColor, color, buffer);
    }
}
