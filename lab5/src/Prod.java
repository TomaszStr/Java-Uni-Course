import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        //wywołaj konstruktor jednoargumentowy przekazując new Constant(c)
        Constant con = new Constant(c);
        args.add(con);
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
        simplify();
    }
    Prod(double c, Node n){
        //wywołaj konstruktor dwuargumentowy
        Constant con = new Constant(c);
        args.add(con);
        args.add(n);
        simplify();
    }

    Prod mul(Prod p){
        for(Node n:p.args)
            mul(n);
        return this;
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        Constant con = new Constant(c);
        args.add(con);
        return this;
    }


    @Override
    double evaluate() {
        double result =1;
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        for(Node val : args){
            result *= val.evaluate();
        }
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}


    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        // ...
        //zaimplementuj

        for(int i=0; i<args.size();i++){
            Node n = args.get(i);
            if(n instanceof Sum || n instanceof Power || n instanceof Exp || n instanceof Prod)
                    b.append("("+n.toString()+")");
            else
                b.append(n.toString());
            if(i<args.size()-1)
                b.append("*");
        }
        return b.toString();
    }

    Node diffVanilla(Variable var) {
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            r.add(m);
        }
        return r;
    }

    @Override
    Node simplify(){
        double hlp = 1;
        ArrayList<Node> lst = new ArrayList<>();
        for(Iterator<Node> it = args.iterator();it.hasNext();)
        {
            Node n = it.next();
            if(n instanceof Constant) {
                hlp *= n.evaluate();
                it.remove();
            }
            else if(n instanceof Prod){
                it.remove();
                n.simplify();
                lst.add(n);
            }
        }

        for(Node n: lst)
            for(Node h: ((Prod)n).args)
                mul(h);

        for(Iterator<Node> it = args.iterator();it.hasNext();){
            Node n = it.next();
            if(n instanceof Constant){
                ((Constant) n).value *= hlp;
                return this;
            }
        }
        if(hlp != 1 || args.size()==0)
            mul(new Constant(hlp));
        return this;
    }
    Node diff(Variable var){
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m= new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            if(!m.isZero())
                r.add(m);
        }
        if(r.args.size() == 0)
            return new Constant(0);
        return r;
    }

    boolean isZero(){
        for(Node n: args)
            if(n.isZero())
                return true;
        return false;
    }

}