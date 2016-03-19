package com.company.modul_2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sigmund69 on 20.03.2016.
 */
public class ImplementExecutorTest {
    @Test
    public void mainTest() {
        Executor executorActual = new ImplementExecutor();
        Validator validator = new ImplementValidator();
        Task<ImplementTask> task1 = new ImplementTask("Alex", "Krug");
        Task<ImplementTask> task2 = new ImplementTask(" ", "Vag");
        executorActual.addTask(task1, validator);
        executorActual.addTask(task2, validator);
        executorActual.execute();

        List<Task> validExpectedList = new ArrayList<>();
        Task<ImplementTask> taskExpVal = new ImplementTask("Alex", "Krug");
        taskExpVal.execute();
        validExpectedList.add(taskExpVal);

        List<Task> invalidExpectedList = new ArrayList<>();
        Task<ImplementTask> taskExpInval = new ImplementTask(" ", "Vag");
        taskExpInval.execute();
        invalidExpectedList.add(taskExpInval);

        assertEquals(validExpectedList.toString(), executorActual.getValidResults().toString());
        assertEquals(invalidExpectedList.toString(), executorActual.getInvalidResults().toString());
    }
}


































