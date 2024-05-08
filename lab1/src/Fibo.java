import java.util.Scanner;
import java.util.Locale;

public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("hello");
        int i = scan.nextInt();
        if(i >45 || i<1)
            i = 10;
        int[] tab = new int[i];
        int fib1=1, fib2=1;
        for(int j=0; j<i;j++){
        tab[j] = fib1;
        int c=fib2;
        fib2=fib1+fib2;
        fib1 = c;
        }
        for(int j=0; j<i; j++)
            System.out.print(tab[j]+" ");

    }
}
