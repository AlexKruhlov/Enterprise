package com.company.modul_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sigmund69 on 18.03.2016.
 */

interface Executor<T extends Task, V extends Validator> {

    // Добавить таск на выполнение. Результат таска будет доступен через метод getValidResults().
    // Бросает Эксепшн если уже был вызван метод execute()
    void addTask(T task);

    // Добавить таск на выполнение и валидатор результата. Результат таска будет записан в ValidResults если validator.isValid вернет true для этого результата
    // Результат таска будет записан в InvalidResults если validator.isValid вернет false для этого результата
    // Бросает Эксепшн если уже был вызван метод execute()
    void addTask(T task, V validator);

    // Выполнить все добавленые таски
    void execute();

    // Получить валидные результаты. Бросает Эксепшн если не был вызван метод execute()
    List getValidResults();

    // Получить невалидные результаты. Бросает Эксепшн если не был вызван метод execute()
    List getInvalidResults();
}

class ImplementExecutor implements Executor<ImplementTask, ImplementValidator> {
    boolean isExecuteIsRun;
    private List<ImplementTask> tasks = new ArrayList<>();
    private List<ResultNameSurname> validResults = new ArrayList<>();
    private List<ResultNameSurname> invalidResults = new ArrayList<>();

    @Override
    public void addTask(ImplementTask task) {
        try {
            if (isExecuteIsRun) {
                throw new Exception();
            } else {
                tasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("[Error]: This Task has been executed!");
        }
    }

    @Override
    public void addTask(ImplementTask task, ImplementValidator validator) {
        checkIsExecuteIsRun();
        if (validator.isValid(task)) {
            validResults.add(task.getResult());
        } else {
            invalidResults.add(task.getResult());
        }
    }

    @Override
    public void execute() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).execute();
            addTask(tasks.get(i), new ImplementValidator());
        }
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
    private ResultNameSurname result = new ResultNameSurname();
    private boolean executeIsRun = false;

    ImplementTask(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public void execute() {
        result.setName(name);
        result.setSurname(surname);
        System.out.println("Information saved!");
        executeIsRun = true;
    }

    @Override
    public ResultNameSurname getResult() {
        return result;
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

}

class ResultNameSurname {
    private String name;
    private String surname;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "ResultNameSurname{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
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

































