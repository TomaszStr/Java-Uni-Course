import java.util.Scanner;
import java.util.Locale;

public class Problem610a {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = 1,cnt=0;
        if(n%2 == 0)
        while(4*x < n){
            cnt++;
            x++;
        }
        System.out.println(cnt);
    }
}
