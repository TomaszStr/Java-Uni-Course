public class Log extends Node{
    double base = Math.E;
    Node arg;
    Log(Node n){
        arg = n;
    }
    Log(Node n, double b){
        if(n.isZero() || n.sign < 0 || b < precision)
            System.out.println("incorrect data provided");
        else{
        arg = n;
        base = b;}
    }

    Log(double val){
        arg = new Constant(val);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if(sign<0)
            b.append("-");
        if(base == Math.E)
            b.append("ln(");
        else b.append("log("+base+")(");
        b.append(arg.toString()+")");
        return b.toString();

    }

    @Override
    double evaluate() {
        return Math.log(arg.evaluate())/Math.log(base);
    }

    @Override
    Node diff(Variable var) {
        Prod result = new Prod();
        Node argdiff = arg.diff(var);
        if(argdiff.isZero())
            return new Constant(0);
        result.args.add(new Log(new Constant(Math.E),base));
        result.args.add(new Power(arg,-1));
        result.args.add(argdiff);
        return result;
    }

    @Override
    boolean isZero() {
        return false;
    }


}
