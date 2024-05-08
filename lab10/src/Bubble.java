import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    Bubble(){
        x=0;
        y=0;
        scale=1;
        lineColor = new Color(100,0,0);
        fillColor = new Color(150,100,0);
    }

    Bubble(int nx,int ny,double nscale){
        x=nx;
        y=ny;
        scale=nscale;
        lineColor = new Color(100,0,0);
        fillColor = new Color(150,100,0);
    }
    @Override
    public void render(Graphics2D g2d) {
        AffineTransform mat = g2d.getTransform();
        this.transform(g2d);
        g2d.setColor(fillColor);
        g2d.fillOval(0,0, 10, 10);
        g2d.setColor(lineColor);
        g2d.drawOval(0,0, 10, 10);
        g2d.setColor(new Color(250,180,50));
        int[] xs  = {4,6,6,4};
        int[] ys = {1,1,-2,-2};
        g2d.fillPolygon(xs , ys, xs.length);
        g2d.setTransform(mat);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
