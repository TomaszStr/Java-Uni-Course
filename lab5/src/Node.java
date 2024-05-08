import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;

abstract public class Node {
    int sign=1;

    static double precision = 0.00001;
    Node minus(){
        sign *= -1;
        return this;
    }
    Node plus(){
        sign = 1;
        return this;
    }
    int getSign(){return sign;}

    /**
     * Oblicza wartość wyrażenia dla danych wartości zmiennych
     * występujących w wyrażeniu
     */
    abstract double evaluate();

    /**
     *
     * zwraca tekstową reprezentację wyrażenia
     */
    public String toString(){return "";}

    /**
     *
     * Zwraca liczbę argumentów węzła
     */
    int getArgumentsCount(){return 0;}

    //zadanie domowe - pochodna
    abstract Node diff(Variable var);

    abstract boolean isZero();

    Node simplify(){
        return this;
    }
}