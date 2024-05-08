public class Sin extends Node {

    Node arg;

    Sin(Node n){
        arg = n;
    }

    Sin(double d){
        arg = new Constant(d);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if(sign<0)b.append("-");
        b.append("sin("+arg.toString()+")");
        return b.toString();
    }

    @Override
    double evaluate() {
        return Math.sin(arg.evaluate());
    }

    @Override
    Node diff(Variable var) {
        Node argdiff = arg.diff(var);
        if(argdiff.isZero())
            return new Constant(0);
        Prod result = new Prod(new Cos(arg), argdiff);
        return result;
    }

    @Override
    boolean isZero() {
        return false;
    }


}
