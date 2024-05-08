import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class Star implements XmasShape{

    int x;
    int y;
    double scale;

    boolean hasTail;

    Star(int x,int y,double scale,boolean tail){
        this.x = x;
        this.y = y;
        this.scale = scale;
        hasTail = tail;
    }
    @Override
    public void render(Graphics2D g2d) {
        AffineTransform mat = g2d.getTransform();
        transform(g2d);

        if(hasTail) {
            GeneralPath shape = new GeneralPath();
            shape.moveTo(0, 0);
            shape.curveTo(0, -5, 40, -10, 50, 10);
            shape.curveTo(50, 10, 30, 0, 35, 20);
            shape.curveTo(35, 20, 20, -5, 0, 0);
            shape.closePath();
            g2d.setPaint(new GradientPaint(0, 0, new Color(200, 150, 100), 50, 50, new Color(255, 255, 255)));
            g2d.fill(shape);
            g2d.draw(shape);
        }
        g2d.setColor(new Color(250,180,50));
        int[] xs  = {0,3,12,4,8,0,-8,-4,-12,-3};
        int[] ys = {-12,-5,-5,-1,6,1,6,-1,-5,-5};
        g2d.fillPolygon(xs , ys, xs.length);
        g2d.setTransform(mat);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
