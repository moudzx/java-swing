package canvas;

import java.awt.Color;
import java.awt.Graphics2D;


public class Line {
    private int x1, y1, x2, y2;
    private Color color;

    public Line(int x1, int y1, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1;
        this.y2 = y1;
        this.color = color;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }                
    
    public void draw(Graphics2D g){
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}
