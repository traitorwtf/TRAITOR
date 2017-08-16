package training;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by traitorwtf on 16.08.2017.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Поток " + Thread.currentThread().getName());
            }
        });
        executorService.shutdown();
        while(!executorService.awaitTermination(24L, TimeUnit.DAYS)) {
            System.out.println("not yet");
        }
        System.out.println(Thread.currentThread().getName());
    }
}
