public class Cos extends Node{

    Node arg;

    Cos(Node n){
        arg = n;
    }

    Cos(double d){
        arg = new Constant(d);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if(sign<0)b.append("-");
        b.append("cos("+arg.toString()+")");
        return b.toString();
    }

    @Override
    double evaluate() {
        return Math.cos(arg.evaluate());
    }

    @Override
    Node diff(Variable var) {
        Node argdiff = arg.diff(var);
        if(argdiff.isZero())
            return new Constant(0);
        Prod result = new Prod(new Sin(arg).minus(), argdiff);
        return result;
    }

    @Override
    boolean isZero() {
        return false;
    }

}

