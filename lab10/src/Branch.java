import com.sun.source.doctree.EscapeTree;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Branch implements XmasShape{

    int x;
    int y;
    double scale;

    ArrayList<Bubble> bubbles = new ArrayList<>();

    Branch(){
        x=300;
        y=300;
        scale = 1;
    }

    Branch(int nx,int ny,double nscale){
        x=nx;
        y=ny;
        scale=nscale;
        for(int i=0;i<5;i++)
        {
            int bx = (int)((Math.random()-0.5)*15);
            int by = (int)((Math.random()-0.5)*10);
            bubbles.add(new Bubble((i-2)*25+bx,35+by,scale*0.75));
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        System.out.println("branch");
        //zapisuje bazowe ustawienia
        AffineTransform mat = g2d.getTransform();
        //kolor

        this.transform(g2d);
        GradientPaint grad = new GradientPaint(0,0,new Color(0,155,50),100,50, new Color(120,100,150));
        g2d.setPaint(grad);
        //g2d.scale(1,scale);
        int xs[]={0,100,75,50,0};
        int ys[]={50,50,40,30,0};
        g2d.fillPolygon(xs,ys,xs.length);
        //symetria
        g2d.scale(-1,1);
        g2d.fillPolygon(xs,ys,xs.length);
        for(var e: bubbles)
            e.render(g2d);
        g2d.setTransform(mat);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
