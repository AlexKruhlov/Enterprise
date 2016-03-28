package com.company.semafor;

/**
 * Created by Alexandr Kruhlov on 24.03.2016.
 */

public class Test {
    public static int numberObjects = 0;

    @org.junit.Test
    public void singleThread() throws InterruptedException {
        MySemaphore mySemaphore = new MySemaphore();

        int numberOfActivThreads = Thread.activeCount();
        new Thread(new SingleWorker(mySemaphore)).start();
        new Thread(new SingleWorker(mySemaphore)).start();

        while (true) {
            Thread.sleep(1000);
            mySemaphore.release();
            if (isExistAnyThread(numberOfActivThreads)) break;
        }
        mySemaphore.release();
        mySemaphore.release();
    }

    @org.junit.Test
    public void testMultyThread() throws InterruptedException {
        MySemaphore mySemaphore = new MySemaphore();
        int numberOfActivThreads = Thread.activeCount();

        new Thread(new MultyWorker(mySemaphore, 4)).start();
        while (true) {
            Thread.sleep(1000);
            mySemaphore.release(2);
            if (isExistAnyThread(numberOfActivThreads)) break;
        }
    }

    public static boolean isExistAnyThread(int numberOfActivThreads) {
        return Thread.activeCount() - numberOfActivThreads == 0;
    }
}

class SingleWorker implements Runnable {
    private MySemaphore mySemaphore;
    private int id;
    private int number = 0;

    SingleWorker(MySemaphore mySemaphore) {
        this.mySemaphore = mySemaphore;
        id = ++ Test.numberObjects;
    }

    @Override
    public void run() {
        int startValue = mySemaphore.getAvailablePermits();
        try {
            System.out.println("Запускаем o.acquire() потока" + id);
            mySemaphore.acquire();
            System.out.println("Вышли из метода o.acquire() потока" + id);
        } catch (InterruptedException e) {
            System.out.println("Сработало исключение метода o.acquire() потока" + id);
            e.printStackTrace();
        }

        System.out.println("Выполняем код после получения разрешения...");
    }
}

class MultyWorker implements Runnable {
    private MySemaphore mySemaphore;
    private int id;
    private int number = 0;
    private int permits = 0;

    MultyWorker(MySemaphore mySemaphore, int permits) {
        this.mySemaphore = mySemaphore;
        id = ++ Test.numberObjects;
        this.permits = permits;
    }

    @Override
    public void run() {
        int startValue = mySemaphore.getAvailablePermits();
        try {
            System.out.println("Запускаем o.acquire() потока" + id);
            mySemaphore.acquire(permits);
            System.out.println("Вышли из метода o.acquire() потока" + id);
        } catch (InterruptedException e) {
            System.out.println("Сработало исключение метода o.acquire() потока" + id);
            e.printStackTrace();
        }

        System.out.println("Выполняем код после получения разрешения...");
    }

    public void setPermits(int permits) {
        this.permits = permits;
    }
}
















