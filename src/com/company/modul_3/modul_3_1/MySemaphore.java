package com.company.semafor;

/**
 * Created by Alexandr Kruhlov on 24.03.2016.
 */

public class MySemaphore implements Semaphore {
    private int permits = 0;
    private Object lock = new Object();

    // Запрашивает разрешение. Если есть свободное захватывает его.
    // Если нет - приостанавливает поток до тех пор пока не появится свободное разрешение.
    @Override
    public void acquire() throws InterruptedException {
        boolean isPermitsGeven = false;
        while (true) {
            synchronized (lock) {
                System.out.println("Предоставте мне, пожалуйста, одно разрешение!");
                if (getAvailablePermits() > 0) {
                    setPermits(getAvailablePermits() - 1);
                    System.out.println("Пожалуйста, вот Вам одно разрешение!");
                    isPermitsGeven = true;
                } else {
                    System.out.println("Извините, но совободных разрешений нет!");
                    lock.wait();
                    //setPermits(getAvailablePermits() - 1);
                }
            }
            if (isPermitsGeven) break;
        }
    }

    // Запрашивает переданое количество разрешений. Если есть переданое количество свободных разрешений
    // захватывает их.

    // Если нет - приостанавливает поток до тех пор пока не появится переданое колтчество свободных
    // разрешений.

    @Override
    public void acquire(int permits) throws InterruptedException {
        boolean isPermitsGeven = false;
        while (true) {
            synchronized (lock) {
                System.out.println("Предоставте мне, пожалуйста, " + permits + " разрешений!");
                if (getAvailablePermits() >= permits) {
                    System.out.println("Пожалуйста, вот Вам " + permits + " разрешений!");
                    setPermits(getAvailablePermits() - permits);
                    isPermitsGeven = true;
                } else {
                    System.out.println("Извините, но в наличии только " + getAvailablePermits() + " свободных разрешений!");
                    lock.wait();
                }
            }
            if (isPermitsGeven) break;
        }
    }

    // Отпускает разрешение возвращая его семафору.
    @Override
    public void release() {
        synchronized (lock) {
            setPermits(getAvailablePermits() + 1);
            System.out.println("Предоствалено 1 разрешение, всего разрешений: " + getAvailablePermits());
            lock.notify();
        }
    }

    // Отпускает переданое количество разрешений возварщая их семафору.

    @Override
    public void release(int permits) {
        synchronized (lock) {
            setPermits(getAvailablePermits() + permits);
            System.out.println("Предоствалено " + permits + " разрешений, всего разрешений: " + getAvailablePermits());
            lock.notifyAll();
        }
    }

    // Возвращает количество свободных разрешений на данный момент.
    @Override
    public int getAvailablePermits() {
        return permits;
    }

    public void setPermits(int permits) {
        this.permits = permits;
    }
}


