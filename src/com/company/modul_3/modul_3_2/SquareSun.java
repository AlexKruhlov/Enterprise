package com.company.squaresun;

import java.util.concurrent.ExecutionException;

/**
 * Created by sigmund69 on 01.04.2016.
 */
public interface SquareSun {
    long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException;
}
