// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Matrix n = new Matrix(new double[][]{{1,1,1},{1,2,2},{1,3,3}});
        //Matrix m = new Matrix(new double[][]{{1,1,1},{1,2},{1}});
        //System.out.printf("%s\n",m.toString());
        //double[][] tab = m.asArray();
        //System.out.println(Arrays.deepToString(m.asArray()));
        //m.set(2,1,20);
        //System.out.println(m.get(2,1));
        //m.reshape(1,9);
        //System.out.printf("%s\n",m.toString());
        Matrix n = new Matrix(new double[][]{{1,2},{3,4},{5,6}});
        Matrix m = new Matrix(new double[][]{{1,2},{3,1}});
        Matrix k = n.dot(m);
        //System.out.println(k.toString());
        //System.out.println(k.frobenius());
        Matrix h = new Matrix(new double[][]{{1,3,4},{1,6,3},{5,3,2}});
        System.out.println(h.det());
        System.out.println(h.toString()+"\n");
        System.out.println(Arrays.toString(h.solve_eq(new double[]{4,12,9})));
        h.inv();
        Matrix j = h.dot(new Matrix(new double[][]{{1,3,4},{1,6,3},{5,3,2}}));
        System.out.println(h.toString()+"\n");
        System.out.println(j.toString()+"\n");
    }
}