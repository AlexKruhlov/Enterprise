package com.company.semafor;

/**
 * Created by Alexandr Kruhlov on 24.03.2016.
 */
public interface Semaphore {

    // Запрашивает разрешение. Если есть свободное захватывает его.
    // Если нет - приостанавливает поток до тех пор пока не появится свободное разрешение.
    public void acquire() throws InterruptedException;

    // Запрашивает переданое количество разрешений. Если есть переданое количество свободных разрешений
    // захватывает их.

    // Если нет - приостанавливает поток до тех пор пока не появится переданое колтчество свободных
    // разрешений.

    public void acquire(int permits) throws InterruptedException;

    // Отпускает разрешение возвращая его семафору.

    public void release();

    // Отпускает переданое количество разрешений возварщая их семафору.

    public void release(int permits);

    // Возвращает количество свободных разрешений на данный момент.

    public int getAvailablePermits();

}
