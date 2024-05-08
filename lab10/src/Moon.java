import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;

public class Moon implements XmasShape{
    int x;
    int y;
    double scale;

    Color background;

    Moon(int nx, int ny, double nscale, Color nbackground){
        x=nx;
        y=ny;
        scale=nscale;
        background = nbackground;
    }
    @Override
    public void render(Graphics2D g2d) {
        AffineTransform mat = g2d.getTransform();
        g2d.setColor(new Color(100,100,100));
        g2d.fillOval(0,0,100,100);
        g2d.setColor(background);
        g2d.fillOval(20,20,90,90);
        g2d.setTransform(mat);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}