//https://home.agh.edu.pl/~pszwed/wiki/doku.php?id=pz1:watki-obliczenia-rownolegle

import java.util.Locale;
import java.util.concurrent.*;

public class Mean {
    static double[] array;
    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    public static void main(String[] args) {
        initArray(100000000);
    }

    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            // ??? liczymy średnią
                for (int i = start; i < end; i++)
                    mean += array[i];
                mean /= (end - start);

            //DLA V2 i V3
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMeanv1(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        int part_len = (int)(array.length/cnt);
        for(int i=0;i<cnt-1;i++)
            threads[i] = new MeanCalc(i*part_len,(i+1)*part_len);
        threads[cnt-1] = new MeanCalc((cnt-1)*part_len,array.length);

        results.clear();

        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for(MeanCalc mc:threads)
            mc.start();

        double t2 = System.nanoTime()/1e6;

        // czekaj na ich zakończenie używając metody ''join''
        for(MeanCalc mc:threads) {
            mc.join();
        }
        // oblicz średnią ze średnich
        double mean=0;
        for(MeanCalc mc:threads)
            mean += mc.mean;
        mean /= cnt;

        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"V1 size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    static void parallelMeanv2(int cnt) throws InterruptedException {

        MeanCalc threads[]=new MeanCalc[cnt];

        int part_len = (int)(array.length/cnt);
        for(int i=0;i<cnt-1;i++)
            threads[i] = new MeanCalc(i*part_len,(i+1)*part_len);
        threads[cnt-1] = new MeanCalc((cnt-1)*part_len,array.length);

        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for(MeanCalc mc:threads)
            mc.start();

        double t2 = System.nanoTime()/1e6;

        double mean = 0;
        for(MeanCalc mc:threads)
            mean += results.take();

        mean /= cnt;

        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    static void parallelMeanv3(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        int part_len = (int)(array.length/cnt);
        for(int i=0;i<cnt-1;i++)
            threads[i] = new MeanCalc(i*part_len,(i+1)*part_len);
        threads[cnt-1] = new MeanCalc((cnt-1)*part_len,array.length);

        double t1 = System.nanoTime()/1e6;

        ExecutorService executor = Executors.newFixedThreadPool(cnt);
        for(int i=0;i<cnt;i++) {
            executor.execute(threads[i]);
        }

        double t2 = System.nanoTime()/1e6;

        executor.shutdown();

        // oblicz średnią ze średnich
        double mean = 0;
        for(MeanCalc mc:threads)
            mean += results.take();

        mean /= cnt;

        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }
}

