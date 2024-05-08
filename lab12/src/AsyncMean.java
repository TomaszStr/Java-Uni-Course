import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AsyncMean {
    static double[] array;

    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * size / (i + 1);
        }
    }

    static class MeanCalcSupplier implements Supplier<Double> {
        //...

        int start;
        int end;
        MeanCalcSupplier(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public Double get() {
            double mean=0;
            // oblicz średnią
            for(int i=start; i< end; i++)
                mean += array[i];
            mean /= (end-start);
            //System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
            return mean;
        }
    }

    public static void asyncMeanv1(int cnt) {
        /*int size = 100_000_000;
        initArray(size);*/
        ExecutorService executor = Executors.newFixedThreadPool(cnt);
        int n= array.length/cnt;//cnt?
        // Utwórz listę future
        List<CompletableFuture<Double>> partialResults = new ArrayList<>();

        double t1 = System.nanoTime()/1e6;

        for(int i=0;i<cnt;i++){
            CompletableFuture<Double> partialMean = CompletableFuture.supplyAsync(
                    new MeanCalcSupplier(i*n,(i+1)*n),executor);
            partialResults.add(partialMean);
        }

        double t2 = System.nanoTime()/1e6;

        // zagreguj wyniki
        double mean=0;
        for(var pr:partialResults){
            // wywołaj pr.join() aby odczytać wartość future;
            mean += pr.join();
            // join() zawiesza wątek wołający
        }

        double t3 = System.nanoTime()/1e6;

        mean/=cnt;

        //System.out.printf(Locale.US,"mean=%f\n",mean);
        System.out.printf(Locale.US,"ASYNC V1 size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
        executor.shutdown();
    }

    static void asyncMeanv2(int cnt) {
        /*int size = 100_000_000;
        initArray(size);*/
        ExecutorService executor = Executors.newFixedThreadPool(cnt);
        int n=16;
        int h = array.length/cnt;
        BlockingQueue<Double> queue = new ArrayBlockingQueue<>(n);

        double t1 = System.nanoTime()/1e6;

        for (int i = 0; i < cnt; i++) {
            CompletableFuture.supplyAsync(
                    new MeanCalcSupplier(i*h,(i+1)*h), executor)
            .thenApply(d -> queue.offer(d));
        }
        double t2 = System.nanoTime()/1e6;
        double mean=0;
        // w pętli obejmującej n iteracji wywołaj queue.take() i oblicz średnią
        for (int i = 0; i < cnt; i++){
            try {
                mean += queue.take();
            } catch (InterruptedException e) {throw new RuntimeException(e);}
        }

        double t3 = System.nanoTime()/1e6;

        mean /= cnt;
        //System.out.printf(Locale.US,"mean=%f\n", mean);
        System.out.printf(Locale.US,"ASYNC V1 size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
        executor.shutdown();
    }
}