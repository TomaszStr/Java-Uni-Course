import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static java.lang.Math.abs;

public class Exp extends Node{

    double base = Math.E; // domyslne liczba Eulera

    Node arg;

    Exp(Node n){ arg = n;}

    Exp(double b, Node n){
        base = b;
        arg = n;
    }

    @Override
    double evaluate() {
        return Math.pow(base, arg.evaluate());
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        DecimalFormat format = new DecimalFormat("0.#####",new DecimalFormatSymbols(Locale.US));
        if(sign<0)b.append("-");
        if(base == Math.E)
            b.append("e");
        else
            b.append(format.format(base));
        b.append("^");
        int argSign = arg.getSign();
        int cnt = arg.getArgumentsCount();
        boolean useBracket = false;
        if(argSign<0 || cnt>1)useBracket = true;
        String argString = arg.toString();
        if(useBracket)b.append("(");
        b.append(argString);
        if(useBracket)b.append(")");
        return b.toString();
    }

    @Override
    Node diff(Variable var) {
       Prod result = new Prod(this,new Log(base));
       Node argdiff = arg.diff(var);
       if(!argdiff.isZero())
           result.args.add(argdiff);
       return result;
    }

    @Override
    boolean isZero() {
        if(abs(base) < precision)
            return true;
        return false;
    }


}
