package com.company.squaresun;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by sigmund69 on 01.04.2016.
 */
public class ArrayPhaser implements SquareSun {
    // private Phaser phaser = new Phaser(1);
    //private

    @Override
    public long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException {
        long sum = 0;
        Phaser phaser = new Phaser(1);

        int elementsOfThread;
        if (values.length < numberOfThreads) {
            elementsOfThread = values.length;
            numberOfThreads = 1;
        } else {
            elementsOfThread = values.length / numberOfThreads;
        }

        ExecutorService executor = null;
        int currentArrayIndex = 0;

        for (int i = 0; i < numberOfThreads; i++) {
            phaser.register();
            executor = Executors.newSingleThreadExecutor();
            final int tempCurrentArrayIndex = currentArrayIndex;
            Future<Long> f = executor.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    long tempSum = 0;
                    int numberOfIterations = 0;
                    for (int j = tempCurrentArrayIndex; j < values.length && numberOfIterations < elementsOfThread; j++) {
                        tempSum += (long) values[j] * values[j];
                        numberOfIterations++;
                    }
                    return tempSum;
                }
            });
            currentArrayIndex += elementsOfThread;
            sum += (long) f.get();
            System.out.println(sum);
            System.out.println("Фаза " + phaser.getPhase() + " для потока " + executor + " завершена.");
            phaser.arriveAndDeregister();
            executor.shutdown();
        }
        return sum;
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        int[] values = { 3, 4, 5, 7, 2, 5 };
        ArrayPhaser arrayPhaser = new ArrayPhaser();

        long expected = 128;

        long actual = arrayPhaser.getSquareSum(values, 3);
        Assert.assertEquals(expected, actual);
        System.out.println("\nТест 1 завершен успешно.\n");

        actual = arrayPhaser.getSquareSum(values,7);
        Assert.assertEquals(expected,actual);
        System.out.println("\nТест 2 завершен успешно.");
    }
}




















