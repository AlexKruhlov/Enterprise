package com.company.modul_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sigmund69 on 18.03.2016.
 */


interface Executor {

    // Добавить таск на выполнение. Результат таска будет доступен через метод getValidResults().
    // Бросает Эксепшн если уже был вызван метод execute()
    void addTask(Task task);

    // Добавить таск на выполнение и валидатор результата. Результат таска будет записан в ValidResults если validator.isValid вернет true для этого результата
    // Результат таска будет записан в InvalidResults если validator.isValid вернет false для этого результата
    // Бросает Эксепшн если уже был вызван метод execute()
    void addTask(Task task, Validator validator);

    // Выполнить все добавленые таски
    void execute();

    // Получить валидные результаты. Бросает Эксепшн если не был вызван метод execute()
    List getValidResults();

    // Получить невалидные результаты. Бросает Эксепшн если не был вызван метод execute()
    List getInvalidResults();
}

class ImplementExecutor implements Executor {
    boolean isExecuteIsRun;
    private List<Task> validResults = new ArrayList<>();
    private List<Task> invalidResults = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        try {
            if (isExecuteIsRun) {
                throw new Exception();
            } else {
                validResults.add(task);
            }
        } catch (Exception e) {
            System.out.println("[Error]: This Task has been executed!");
        }
    }

    @Override
    public void addTask(Task task, Validator validator) {
        checkIsExecuteIsRun();
        if (validator.isValid(task)) {
            validResults.add(task);
        } else {
            invalidResults.add(task);
        }
    }

    @Override
    public void execute() {
        execution(validResults);
        execution(invalidResults);
        isExecuteIsRun = true;
    }

    @Override
    public List getValidResults() {
        checkIsExecuteIsNotRun();
        return validResults;
    }

    @Override
    public List getInvalidResults() {
        checkIsExecuteIsNotRun();
        return invalidResults;
    }

    public void execution(List<Task> task) {
        for (int i = 0; i < task.size(); i++) {
            task.get(i).execute();
        }
    }

    public void checkIsExecuteIsRun() {
        try {
            if (isExecuteIsRun) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("[Error]: This Task has been executed!");
        }
    }

    public void checkIsExecuteIsNotRun() {
        try {
            if (! isExecuteIsRun) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("[Error]: This Task hasn't been executed!");
        }
    }

}

interface Task<T> {

    // Метода запускает таск на выполнение
    void execute();

    // Возвращает результат выполнения
    T getResult();

}

class ImplementTask implements Task {
    private String name;
    private String surname;
    private boolean executeIsRun = false;

    ImplementTask (String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    @Override
    public void execute() {
        System.out.println("Information saved!");
        executeIsRun = true;
    }

    @Override
    public Object getResult() {
        return this;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isExecuteIsRun() {
        return executeIsRun;
    }

    @Override
    public String toString() {
        return "ImplementTask{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", executeIsRun=" + executeIsRun +
                '}';
    }
}

interface Validator<T> {
    // Валидирует переданое значение
    boolean isValid(T result);
}

class ImplementValidator implements Validator<ImplementTask> {

    public boolean isValid(ImplementTask result) {
        if ((result.getName() != null && result.getName() != "" && result.getName().charAt(0) != ' ') &&
                result.getSurname() != null && result.getSurname() != "" && result.getSurname().charAt(0) != ' ') {
            return true;
        } else {
            return false;
        }
    }

}

































