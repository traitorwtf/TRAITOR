package deadlock;

/**
 * Created by traitorwtf on 29.05.2017.
 */
public class DeadLockTest {
    ResourceA resourceA = new ResourceA();
    ResourceB resourceB = new ResourceB();

    public static void main(String[] args) {
        new DeadLockTest().startProgramm();
    }

    public void startProgramm(){
        Thread my1 = new Thread(new MyThread1());
        Thread my2 = new Thread(new MyThread2());
        my1.start();
        my2.start();
    }

    public class MyThread1 implements Runnable{
        @Override
        public void run() {
            resourceA.sumI();
            resourceA.getK();
            System.out.println(resourceA.getI());
        }
    }

    public class MyThread2 implements Runnable{
        @Override
        public void run() {
            resourceB.sumK();
            resourceB.getI();
            System.out.println(resourceA.getK());
        }
    }

    public class ResourceA{
        int i = 0;

        public synchronized void sumI(){
            for (int j = 0; j < 5; j++) {
                i+=j;
            }
        }

        public synchronized int getI(){
            return i;
        }

        public synchronized int getK(){
            return resourceB.getK();
        }
    }

    public class ResourceB{
        int k = 0;

        public synchronized void sumK(){
            for (int j = 0; j < 10; j++) {
                k+=j;
            }
        }

        public synchronized int getK(){
            return k;
        }

        public synchronized int getI(){
            return resourceA.getI();
        }
    }


}


