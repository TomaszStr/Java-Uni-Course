import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Tree implements XmasShape{

    Trunk trunk;
    ArrayList<Branch> branches = new ArrayList<>();

    int x;
    int y;

    double scale;

    Tree(){
        x=400;
        y=350;
        scale = 2;
        for(int i=0; i<5; i++)
            branches.add(new Branch(0,0-i*35,scale*(1-0.1*i)));
        trunk = new Trunk(x,y,scale);
    }

    Tree(int nx, int ny, double nscale){
        x=nx;
        y=ny;
        scale = nscale;
        for(int i=0; i<5; i++)
            branches.add(new Branch(0,-i*35,scale*(1-0.1*i)));
        trunk = new Trunk(0,0,scale);
    }


    @Override
    public void render(Graphics2D g2d) {
        AffineTransform mat = g2d.getTransform();
        for(var e: branches)
            e.render(g2d);
        trunk.render(g2d);

        new Star(0,-140,3,false).render(g2d);

        g2d.setTransform(mat);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
