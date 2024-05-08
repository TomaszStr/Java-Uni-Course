import org.junit.Assert;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test(expected = RuntimeException.class)
    public void Matrix_def(){
        Matrix m1 = new Matrix(3,4);
        Matrix m2 = new Matrix(2,5);
        assertEquals(3, m1.rows);
        assertEquals(4, m1.cols);
        assertEquals(2, m2.rows);
        assertEquals(5, m2.cols);
        assertEquals(0.00001, Matrix.precision, 0.000000001);
        Matrix m3 = new Matrix(-2,-5); // should throw exception
    }

    @org.junit.Test
    public void Matrix_arr(){
        double[][] t1 = new double[][]{{1,2},{3.5,1},{1,1,0}};
        Matrix m1 = new Matrix(t1);
        assertEquals(3, m1.rows);
        assertEquals(3, m1.cols);
        assertEquals(2, m1.get(0,1),Matrix.precision);
        assertEquals(0, m1.get(1,2),Matrix.precision);
    }

    @org.junit.Test
    public void asArray() {
        double[][] t1 = new double[][]{{1,2},{3.5,1},{1,1,0}};
        double[][] t2 = new double[][]{{1,2,3,4,5}};
        Matrix m1 = new Matrix(t1);
        Matrix m2 = new Matrix(t2);

        assertFalse(Arrays.deepEquals(t1,m1.asArray()));
        assertTrue(Arrays.deepEquals(new double[][]{{1,2,0},{3.5,1,0},{1,1,0}},m1.asArray()));
        assertTrue(Arrays.deepEquals(t2,m2.asArray()));
    }

    @org.junit.Test
    public void get() {
        Matrix m = new Matrix(new double[][]{{1,2},{3.5}});
        assertEquals(1,m.get(0,0),Matrix.precision);
        assertEquals(2,m.get(0,1),Matrix.precision);
        assertEquals(3.5,m.get(1,0),Matrix.precision);
        assertEquals(0,m.get(1,1),Matrix.precision);
    }

    @org.junit.Test
    public void set() {
        Matrix m = new Matrix(5,2);
        m.set(0,0,1);
        m.set(4,1,2);
        m.set(3,0,3);
        assertEquals(1,m.get(0,0),Matrix.precision);
        assertEquals(2,m.get(4,1),Matrix.precision);
        assertEquals(3,m.get(3,0),Matrix.precision);
        assertEquals(0,m.get(1,1),Matrix.precision);
    }

    @org.junit.Test
    public void testToString() {
        Matrix m1 = new Matrix(new double[][]{{1,2},{3.5},{1,1,0}});
        String s1 = new String("[[1.0, 2.0, 0.0]\n" + "[3.5, 0.0, 0.0]\n" + "[1.0, 1.0, 0.0]]");
        Matrix m2 = new Matrix(new double[][]{{1,2,3,4,5}});
        String s2 = new String("[[1.0, 2.0, 3.0, 4.0, 5.0]]");
        assertEquals(m1.toString(),s1);
        assertEquals(m2.toString(),s2);
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void reshape_except() {
        Matrix m1 = new Matrix(new double[][]{{1,2},{3.5},{1,1,0,1}});
        Matrix m2 = new Matrix(1,5);
        m2.reshape(5,1);
        m1.reshape(4,5); // should throw exception

    }

    @org.junit.Test
    public void reshape() {
        Matrix m1 = new Matrix(new double[][]{{1,2},{3.5},{1,1,0,1}});
        Matrix m2 = new Matrix(1,5);
        m1.reshape(2,6);
        m2.reshape(5,1);
        assertEquals(m1.rows, 2);
        assertEquals(m1.cols, 6);
        assertEquals(m2.rows, 5);
        assertEquals(m2.cols, 1);
    }

    @org.junit.Test
    public void shape() {
        Matrix m1 = new Matrix(new double[][]{{1,2},{3.5},{1,1,0,1}});
        Matrix m2 = new Matrix(1,5);
        assertTrue(Arrays.equals(m1.shape(), new int[]{3, 4}));
        assertTrue(Arrays.equals(m2.shape(),new int[]{1, 5}));
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void add() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3},{3,2,1},{1,1,0}});
        Matrix m2 = new Matrix(3,3);
        Matrix m3 = m1.add(m2);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertEquals(m1.get(i,j), m3.get(i,j),Matrix.precision);
        Matrix m4 = new Matrix(1,3);
        m4.add(m1); // should throw exception
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void sub() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3},{3,2,1},{1,1,0}});
        Matrix m2 = m1.sub(m1);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertEquals(0, m2.get(i,j), Matrix.precision);
        Matrix m4 = new Matrix(1,3);
        m4.sub(m1); // should throw exception
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void mul() {
        Matrix m1 = new Matrix(3,3);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                m1.set(i,j,i*m1.cols+j);
        Matrix m2 = m1.mul(m1);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertEquals((i*m1.cols+j)*(i*m1.cols+j), m2.get(i,j), Matrix.precision);
        Matrix m4 = new Matrix(1,3);
        m4.mul(m1); // should throw exception
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void div() {
        Matrix m1 = new Matrix(3,3);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                m1.set(i,j,i*m1.cols+j+1);
        Matrix m2 = m1.div(m1);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertEquals(1, m2.get(i,j), Matrix.precision);
        Matrix m4 = new Matrix(1,3);
        m4.div(m1); // should throw exception
    }
    //methods with double
    @org.junit.Test
    public void testAdd() {
        Matrix m1 = new Matrix(3,3).add(55.5);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertEquals(55.5, m1.get(i,j), Matrix.precision);
    }

    @org.junit.Test
    public void testSub() {
        Matrix m1 = new Matrix(3,3).sub(77.7);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertEquals(-77.7, m1.get(i,j), Matrix.precision);
    }

    @org.junit.Test
    public void testMul() {
        Matrix m1 = new Matrix(3,3);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                m1.set(i,j,i*m1.cols+j);
        m1 = m1.mul(44.4);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertEquals(44.4*(i*m1.cols+j), m1.get(i,j), Matrix.precision);
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void testDiv() {
        Matrix m1 = new Matrix(3,3);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                m1.set(i,j,i*m1.cols+j+1);
        m1 = m1.div(100);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertEquals(i*m1.cols+j+1, m1.get(i,j)*100, Matrix.precision);
        m1.div(0.000001); // smaller than precision should throw exception
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void dot() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix m2 = m1.dot(m1);
        double[] tab1 = new double[]{30,36,42,66,81,96,102,126,150};
        Matrix m3 = new Matrix(new double[][]{{1,2,3}});
        Matrix m4 = new Matrix(new double[][]{{1},{2},{3}});
        Matrix m5 = m4.dot(m3);
        double[][] tab2 = {{1,2,3},{2,4,6},{3,6,9}};
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++){
                assertEquals(tab1[i*m1.rows+j],m2.get(i,j),Matrix.precision);
                assertEquals(tab2[i][j],m5.get(i,j),Matrix.precision);
            }
        //check matrices with incorrect shapes
        m1.dot(m3); // should throw exception
    }

    @org.junit.Test
    public void frobenius() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        assertEquals(m1.frobenius(), Math.sqrt(285),Matrix.precision);
        m1 = m1.div(m1);
        assertEquals(3, m1.frobenius(),Matrix.precision);
    }

    @org.junit.Test
    public void random() {
        Matrix m1 = Matrix.random(3,3);
        assertEquals(3, m1.rows);
        assertEquals(3, m1.cols);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                assertTrue(m1.get(i,j)<=1 && m1.get(i,j)>=0);
    }

    @org.junit.Test
    public void eye() {
        Matrix m1 = Matrix.eye(3);
        for(int i=0; i<m1.rows; i++)
            for(int j=0; j<m1.cols; j++)
                if(i == j)
                    assertEquals(1, m1.get(i,j), Matrix.precision);
                else
                    assertEquals(0, m1.get(i,j), Matrix.precision);
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void det() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}}); // det is 0
        assertEquals(0,m1.det(),Matrix.precision);
        Matrix m2 = new Matrix(2,3);
        Matrix m3 = new Matrix(new double[][]{{1,1,2},{0,2,3},{1,3,1}});
        assertEquals(-8,m3.det(),Matrix.precision);
        m2.det(); // should throw exception
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void inv() {

        Matrix m1 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix m2 = new Matrix(new double[][]{{1,1,2},{1,1,1},{1,2,1}});
        m2.inv();
        Matrix m3 = m2.dot(new Matrix(new double[][]{{1,1,2},{1,1,1},{1,2,1}}));
        assertTrue(Arrays.deepEquals(m2.asArray(),new double[][]{{-1,3,-1},{0,-1,1},{1,-1,0}}));
        assertTrue(Arrays.deepEquals(m3.asArray(),new double[][]{{1,0,0},{0,1,0},{0,0,1}}));
        m1.inv(); // should throw exception
    }

    @org.junit.Test
    public void solve_eq() {
        Matrix m1 = new Matrix(new double[][]{{1,1,3},{2,5,1},{3,8,6}});
        Matrix m2 = new Matrix(new double[][]{{2,5,1},{7,3,6},{1,1,5}});
        double[] tab1 = new double[]{1,2,3};
        double[] tab2 = new double[]{1.5,4,5};
        double[] sol1 = m1.solve_eq(new double[]{12,15,37});
        double[] sol2 = m2.solve_eq(new double[]{28,52.5,30.5});
        for(int i=0; i < 3; i++){
            assertEquals(tab1[i],sol1[i],Matrix.precision);
            assertEquals(tab2[i],sol2[i],Matrix.precision);
        }
    }
}