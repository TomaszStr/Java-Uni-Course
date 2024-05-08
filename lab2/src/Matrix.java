import java.util.Arrays;
import java.util.Random;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

public class Matrix {
    double[] data;
    int rows;
    int cols;

    static double precision = 0.00001;

    Matrix(int rows, int cols) {
        if(rows < 1 || cols < 1)
            throw new RuntimeException("Numbers of rows and columns must be a positive integer");
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows * cols];
    }

    //2.1
    Matrix(double[][] d) {
        int max = 0;
        for (double[] row : d) {
            if (row.length > max)
                max = row.length;
        }
        this.rows = d.length;
        this.cols = max;
        this.data = new double[this.rows * this.cols];
        // initial val of double is 0.0, also:
        // Arrays.fill(this.data, 0.0);

        for (int i = 0; i < d.length; i++) {
            int j = i * this.cols;
            // copy of range
            for (int k = 0; k < d[i].length; k++)
                this.data[j + k] = d[i][k];
        }
    }

    //2.2
    double[][] asArray() {
        double[][] Arr = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            Arr[i] = Arrays.copyOfRange(this.data, i * this.cols, (i + 1) * this.cols);
        }
        return Arr;
    }

    //2.3
    double get(int r, int c) {
        if (c > cols-1 || c < 0)
            throw new RuntimeException(String.format("There is no such column:%d",c));
        if (r > rows-1 || r < 0)
            throw new RuntimeException(String.format("There is no such row:%d",r));
        return this.data[r * this.cols + c];
    }

    void set(int r, int c, double value) {
        if (c > cols-1 || c < 0)
            throw new RuntimeException(String.format("There is no such column:%d",c));
        if (r > rows-1 || r < 0)
            throw new RuntimeException(String.format("There is no such row:%d",r));
        this.data[r * this.cols + c] = value;
    }

    //2.4

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append('[');
        for (int i = 0; i < rows; i++) {
            buf.append('[');
            for (int j = 0; j < cols - 1; j++) {
                buf.append(data[i * cols + j] + ", ");
            }
            buf.append(data[i * cols + cols - 1]);
            buf.append(']');
            if(i<rows-1) buf.append("\n");
        }
        buf.append(']');
        return buf.toString();
    }

    //2.5

    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        cols = newCols;
        rows = newRows;
    }

    //2.6

    int[] shape() {
        int[] tab = new int[2];
        tab[0] = rows;
        tab[1] = cols;
        return tab;
    }

    //2.7

    Matrix add(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new RuntimeException(String.format("Matrices have different shapes: %d x %d != %d x %d",rows, cols, m.rows, m.cols));
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = data[i] + m.data[i];
        }
        return result;
    }

    //2.8

    Matrix sub(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new RuntimeException(String.format("Matrices have different shapes: %d x %d != %d x %d",rows, cols, m.rows, m.cols));
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = data[i] - m.data[i];
        }
        return result;
    }

    Matrix mul(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new RuntimeException(String.format("Matrices have different shapes: %d x %d != %d x %d",rows, cols, m.rows, m.cols));
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = data[i] * m.data[i];
        }
        return result;
    }

    Matrix div(Matrix m) {
        if (rows != m.rows || cols != m.cols)
            throw new RuntimeException(String.format("Matrices have different shapes: %d x %d != %d x %d",rows, cols, m.rows, m.cols));
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = data[i] / m.data[i];
        }
        return result;
    }

    Matrix add(double d) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = data[i] + d;
        }
        return result;
    }

    Matrix sub(double d) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = data[i] -   d;
        }
        return result;
    }

    Matrix mul(double d) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = data[i] * d;
        }
        return result;
    }

    Matrix div(double d) {
        if (abs(d) < precision)
            throw new RuntimeException("Can't divide by zero");
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows * cols; i++) {
            result.data[i] = data[i] / d;
        }
        return result;
    }

    //2.9

    Matrix dot(Matrix m){
        if(cols != m.rows)
            throw new RuntimeException(String.format("Multiplying these matrices isn't possible: %d != %d", cols, m.rows));
        Matrix result = new Matrix(rows,m.cols);
        double val=0;
        for(int r=0; r<rows; r++){
            for(int c=0; c<m.cols; c++){
                val=0;
                for(int i=0; i<cols; i++)
                    val += get(r,i) * m.get(i,c);
                result.set(r,c,val);
            }
        }
        return result;
    }

    //2.10

    double frobenius(){
        double frob=0;
        for(int i=0; i<rows*cols; i++)
            frob += data[i]*data[i];
        return sqrt(frob);
    }

    //2.11

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        for(int i=0; i<rows*cols; i++) //???
            m.data[i]=r.nextDouble();
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for(int i=0; i<n; i++)
            m.set(i,i,1);
        return m;
    }

    //2.12
    double det(){
        if(rows != cols)
            throw new RuntimeException("The matrix isn't square");
        double result=1;
        double[][] copy = asArray();
        //Gauss on copy
        for(int i=0; i<rows-1; i++) {
            if (abs(copy[i][i]) > precision) {
                for (int k = i; k < rows - 1; k++){
                    double a = copy[(k + 1)][i] / copy[i][i];
                    for (int j = 0; j < cols; j++)
                        copy[k + 1][j] -= a * copy[i][j];
                }
            }
        }
        //multiplying elemnts on the diagonal
        for(int i=0; i<rows; i++)
            result *= copy[i][i];
        return result;
    }


    //2.13
    // gauss jordan
    void inv(){
        if(abs(det()) > precision){
            Matrix I = Matrix.eye(rows);
            //Gauss
            for(int i=0; i<rows-1; i++) {
                if (abs(get(i, i)) > precision) {
                    for (int k = i; k < rows - 1; k++) {
                        double a = data[(k + 1) * cols + i] / data[i * cols + i];
                        for (int j = 0; j < cols; j++) {
                            data[(k + 1) * cols + j] -= a * data[i * cols + j];
                            I.data[(k + 1) * cols + j] -= a * I.data[i * cols + j];
                        }
                    }
                }
            }
            //"reversed" Gauss
            for(int i=rows-1; i>0; i--){
                for(int k=i-1; k > -1; k--){
                    double a = data[k * cols + i] / data[i * cols + i];
                    for(int j=cols-1; j>-1; j--){
                        data[k * cols + j] -= a * data[i * cols + j];
                        I.data[k * cols + j] -= a * I.data[i * cols + j];

                    }
                }
            }
            // multiply certain rows
            for(int i=0; i<rows; i++){
                double a = get(i,i);
                for(int j=0; j<cols; j++){
                    I.data[i*cols +j] /=a;
                }
            }
            data = I.data;
        }
        else throw new RuntimeException("This matrix is not invertible(det is zero)");
    }


    //2.14

    double[] solve_eq(double[] b){
        if(abs(det()) < precision || rows != cols || b.length != rows)
            throw new RuntimeException("Can't solve this equation");
        double[] result = new double[b.length];
        //Gauss
        double[][] copy = asArray();
        for(int i=0; i<rows-1; i++) {
            if (abs(copy[i][i]) > precision) {
                for (int k = i; k < rows - 1; k++) {
                    double a = copy[(k + 1)][i] / copy[i][i];
                    for (int j = 0; j < cols; j++)
                        copy[k + 1][j] -= a * copy[i][j];
                    b[k+1] -= a*b[i];
                }
            }
        }
        //solving
        for(int i =b.length-1; i>=0; i--){
            for(int j=b.length-1; j >i; j--)
                b[i] -= copy[i][j]*result[j];
            result[i] = b[i]/copy[i][i];
        }
        return result;
    }
}