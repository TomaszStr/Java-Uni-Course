import java.util.Scanner;
import java.util.Locale;

public class Problem115a {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x, longest, ans=0;
        int[] tab = new int[n];
        for(int i=0; i < n;i++) {
            tab[i] = scan.nextInt();
        }
        for(int i=0; i<n;i++){
            x = tab[i];
            longest = 1;
            while(x != -1){
                longest++;
                x = tab[x-1];
            }
            if(longest>ans)
                ans = longest;
        }
        System.out.println(ans);
    }
}
