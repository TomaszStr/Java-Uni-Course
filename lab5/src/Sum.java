import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        for(Node val : args){
            result += val.evaluate();
        }
        //oblicz sumę wartości zwróconych przez wywołanie evaluate skłądników sumy
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");

        //zaimplementuj
        for(int i=0; i<args.size();i++){
            b.append(args.get(i).toString());
            if(i<args.size()-1 )//&& args.get(i+1).sign>0)
                b.append(" + ");
            //else b.append(" ");
        }

        if(sign<0)b.append(")");
        return b.toString();
    }

    //wersja podstawowa do zrobienia wersja zoptmalizowana
    Node diffVanilla(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            r.add(n.diff(var));
        }
        return r;
    }


    Node simplify(){
        for(Node val : args)
            val.simplify();
        return this;
    }

    Node diff(Variable var){
        Sum result = new Sum();
        for(Node val : args){
            if(!val.isZero()){
                Node difval = val.diff(var);
                        if(!difval.isZero())
                            result.add(difval.simplify());
            }
        }
        if(result.args.size() == 0) // pusta suma
            return new Constant(0);
        else
            return result;
    }

    boolean isZero(){
        for(Node n: args)
            if(!n.isZero())
                return false;
        return true;
    }
}
