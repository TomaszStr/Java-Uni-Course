import java.util.Locale;

import static java.lang.Math.abs;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static void buildAndPrint(){
    Variable x = new Variable("x");
    Node exp = new Sum()
            .add(2.1,new Power(x,3))
            .add(new Power(x,2))
            .add(-2,x)
            .add(7);
    System.out.println(exp.toString());

    }
    static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }

        bisection(exp, x, -5, 5);
    }
    static void bisection(Node exp, Variable x, double a, double b){
        x.setValue((a+b)/2);
        while(abs(exp.evaluate()) > Node.precision){
            x.setValue((a+b)/2);
            if(exp.evaluate()*a < 0)
                b = (a+b)/2;
            else a = (a+b)/2;
        }
        System.out.printf("Miejsce zerowe w x = %f \n",x.value);
        System.out.printf("f(x) = %f \n",exp.evaluate());
    }
    static void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());

        double xv = 100*(Math.random()-.5);
        double yv = 100*(Math.random()-.5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s\n",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));
    }
    static void find_points(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString()+"\nPunkty wewnatrz");
        int cnt = 0, total=0;
        while(cnt < 100){
            // losuje punkty x,y z <-10, 10>
            double xv = 20*(Math.random()-.5);
            double yv = 20*(Math.random()-.5);
            x.setValue(xv);
            y.setValue(yv);
            double fv = circle.evaluate();
            if(fv<0){
                cnt++;
                System.out.printf("Punkt: (%f, %f)\n", xv, yv);
            }
            total++;
        }
        System.out.printf("Znaleziono po %d iteracjach",total);
    }

    static void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());

    }

    static void diffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());

    }

    public static void main(String[] args) {
      //buildAndPrint();
      //buildAndEvaluate();
      //defineCircle();
      //find_points();
      diffPoly();
      //diffCircle();

      /*Variable x = new Variable("x"),
                y = new Variable("y");
      Prod p = new Prod(8,new Sum(y,x).add(x)).mul(new Power(new Sum(y,x),2)).mul(25);
      System.out.println(new Power(p,100).toString());
      x.setValue(1);
      y.setValue(1);
      System.out.println(p.evaluate());*/

      Variable x = new Variable("x");
      Variable y = new Variable("y");
      Exp e = new Exp(new Sum(new Constant(5),new Prod(x,y)));
      System.out.println(e.diff(x).toString());

      Log log = new Log(x,5);
      Sin sin = new Sin(x);
      Cos cos = new Cos(x);
      System.out.println(log.diff(x).toString());
      System.out.println(sin.diff(x).toString());
      System.out.println(cos.diff(x).toString());


       Prod pr = new Prod(-3,x).mul(3).mul(5);
       pr.mul(new Prod(4,x).mul(3).mul(4).mul(new Prod(2,y)));
       System.out.println(pr.simplify().toString());
    }

}