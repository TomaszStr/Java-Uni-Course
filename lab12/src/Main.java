public class Main {
    public static void main(String[] args) {
        /*Mean.initArray(128_000_000);
        try{
            for(int i=1;i<11;i++)
                Mean.parallelMeanv1(i*4);}
        catch (Exception e){
            e.printStackTrace();
        }*/

        //????????
        /*Mean.initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}) {
            try {
                Mean.parallelMeanv3(cnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

       /* Mean.initArray(128_000_000);
        try {
            Mean.parallelMeanv1(16);
            Mean.parallelMeanv2(16);
            Mean.parallelMeanv3(16);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*AsyncMean.initArray(128_000_000);
        AsyncMean.asyncMeanv1(16);
        AsyncMean.asyncMeanv2(16);*/



    }
}

/*
WYNIKI
DLA parallelMean() v1
size = 1000000 cnt=20 >  t2-t1=1.033800 t3-t1=30.746800 mean=8.202654
size = 1000000 cnt=1000 >  t2-t1=77.073700 t3-t1=110.338700 mean=6.696112
size = 1000000 cnt=1 >  t2-t1=0.165300 t3-t1=15.768400 mean=7.218687
size = 2000000 cnt=100 >  t2-t1=6.377900 t3-t1=48.639700 mean=8.181589

DLA parallelMean() v2
size = 2000000 cnt=100 >  t2-t1=3.830400 t3-t1=46.077100 mean=7.706878
size = 1000000 cnt=20 >  t2-t1=0.986900 t3-t1=20.711600 mean=7.132679
size = 1000000 cnt=1000 >  t2-t1=58.975000 t3-t1=74.052700 mean=7.542635
size = 1000000 cnt=1 >  t2-t1=0.142200 t3-t1=4.550500 mean=7.390022

*/