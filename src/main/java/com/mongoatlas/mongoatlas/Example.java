package com.mongoatlas.mongoatlas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example
{
    public static void main(String[] args) {
    int processors = Runtime.getRuntime().availableProcessors();
    System.out.println("Number of processors: " + processors);
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cores: " + cores);

    for (int i = 1; i <= processors * 2; i++) {
        try {
            Thread thread = new Thread(new Task());
            thread.start();
            thread.join();
            System.out.println("Started thread " + i);
        } catch (OutOfMemoryError | ThreadDeath e) {
            System.out.println("Unable to start thread " + i);
            break;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);  // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
