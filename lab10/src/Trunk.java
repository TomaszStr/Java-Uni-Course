import java.awt.*;
import java.awt.geom.AffineTransform;

public class Trunk implements XmasShape{
    int x;
    int y;
    double scale;

    Trunk(){
        x=300;
        y=300;
        scale = 1;
    }

    Trunk(int nx,int ny,double nscale){
        x=nx;
        y=ny;
        scale=nscale;
    }

    @Override
    public void render(Graphics2D g2d) {
        System.out.println("Trunk");
        AffineTransform mat = g2d.getTransform();

        this.transform(g2d);
        GradientPaint grad = new GradientPaint(0,50,new Color(70,20,0),25,80,new Color(30,20,0));
        g2d.setPaint(grad);
        int xs[]={0,25,25,0};
        int ys[]={50,50,80,80};
        g2d.fillPolygon(xs,ys,xs.length);
        g2d.scale(-1,1);
        g2d.fillPolygon(xs,ys,xs.length);
        g2d.setTransform(mat);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
