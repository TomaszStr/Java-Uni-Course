import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class DrawPanel extends JPanel {
    Image img = Toolkit.getDefaultToolkit().getImage("/resources/santa.jpg");

    ArrayList<XmasShape> shapes = new ArrayList<XmasShape>();
    DrawPanel(){
        setBackground(new Color(0,10,50));
        setOpaque(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        /*try
        {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResource("resources/santa.jpg")));
            g.drawImage(img,0,0,getWidth(),getHeight(),this);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        //g.drawImage(img,0,0,getWidth(),getHeight(),this);
        //System.out.println(img);

        int w = this.getWidth();
        int h = this.getHeight();


        for(int i=0; i < 40 ; i++)
        {
            shapes.add(new Star((i+1)*(w/40), (int)((Math.random()-0.5)*(h/3))+(h/6) , Math.random(),false));
        }

        GeneralPath shape = new GeneralPath();
        shape.moveTo(0, h/1.5);
        shape.curveTo(w, h, w/2, h/8, w, h/2);
        shape.lineTo(w, h);
        shape.lineTo(0,h);
        shape.closePath();
        ((Graphics2D)g).setPaint(new GradientPaint(0,h/2,new Color(100,100,100),w,h,new Color(255,255,255)));
        ((Graphics2D)g).fill(shape);
        ((Graphics2D)g).draw(shape);

        shapes.add(new Star(w/4,h/16,2,true));
        shapes.add(new Moon(50,50,1.5,this.getBackground()));
        shapes.add(new Tree(w/2, (int) (h/1.75),1.5));

        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }
}