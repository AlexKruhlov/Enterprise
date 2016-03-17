package com.company.modul_1;

import java.util.*;

/**
 * Created by sigmund69 on 14.03.2016.
 */
public class ListTesting {
    private String methodName;
    private String className;
    private int testResult;
    private AbstractCollection testCollection;

    public ListTesting(ListTesting listTesting) {
        methodName = listTesting.getMethodName();
        className = listTesting.getClassName();
        testResult = listTesting.getTestResult();
        testCollection = listTesting.getTestCollection();
    }

    public ListTesting(ArrayList list, int size) {
        testCollection = new ArrayList(list);
        filling(size);
        className = "ArrayList";
    }

    public ListTesting(LinkedList list, int size) {
        testCollection = new LinkedList(list);
        filling(size);
        className = "LinkedList";
    }

    public ListTesting(HashSet hashSet, int size) {
        testCollection = new HashSet(hashSet);
        filling(size);
        className = "HashSet";
    }

    public ListTesting(TreeSet treeSet, int size) {
        testCollection = new TreeSet(treeSet);
        filling(size);
        className = "TreeSet";
    }

    public void filling(int size) {
        Random random = new Random();
        for (int i = 0; i < 100_000; i++) {
            testCollection.add(random.nextInt());
        }
    }

    public int getTestResult() {
        return testResult;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }

    public AbstractCollection getTestCollection() {
        return testCollection;
    }

    public void populate(int size) {
        long timeTest = 0;
        for (int i = 0; i < 100; i++) {
            if (!testCollection.isEmpty()) {
                testCollection.clear();
            }
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < size; j++) {
                testCollection.add(j);
            }
            timeTest += System.currentTimeMillis() - startTime;
        }
        methodName = "populate";
        testResult = (int) timeTest / 100;
    }


    public void add(int[] indexes) {
        long timeTest = 0;
        long startTime = 0;
        int size = testCollection.size();
        AbstractCollection newTestCollection;

        for (int i = 0; i < 100; i++) {
            switch (className) {
                case "ArrayList":
                    newTestCollection = new ArrayList(testCollection);
                    startTime = System.currentTimeMillis();
                    ((ArrayList) newTestCollection).add(indexes[i], i);
                    break;

                case "LinkedList":
                    newTestCollection = new LinkedList(testCollection);
                    startTime = System.currentTimeMillis();
                    ((LinkedList) newTestCollection).add(indexes[i], i);
                    break;

                case "HashSet":
                    newTestCollection = new HashSet(testCollection);
                    startTime = System.currentTimeMillis();
                    ((HashSet) newTestCollection).add(i);
                    break;

                case "TreeSet":
                    newTestCollection = new TreeSet(testCollection);
                    startTime = System.currentTimeMillis();
                    ((TreeSet) newTestCollection).add(i);
            }
            timeTest += System.currentTimeMillis() - startTime;
        }
        methodName = "add";
        testResult = (int) timeTest / 100;
    }

    public void get(int[] indexes) {
        long timeTest = 0;
        long startTime = 0;
        int size = testCollection.size();

        for (int i = 0; i < 100; i++) {
            switch (className) {
                case "ArrayList":
                    startTime = System.currentTimeMillis();
                    ((ArrayList) testCollection).get(indexes[i]);
                    break;

                case "LinkedList":
                    startTime = System.currentTimeMillis();
                    ((LinkedList) testCollection).get(indexes[i]);
                    break;
            }
            timeTest += System.currentTimeMillis() - startTime;
        }
        methodName = "get";
        testResult = (int) timeTest / 100;
    }

    public void remove(int[] indexes) {
        long timeTest = 0;
        long startTime = 0;
        int size = testCollection.size();
        AbstractCollection newTestCollection;

        for (int i = 0; i < 100; i++) {
            switch (className) {
                case "ArrayList":
                    newTestCollection = new ArrayList(testCollection);
                    startTime = System.currentTimeMillis();
                    ((ArrayList) newTestCollection).remove(indexes[i]);
                    break;

                case "LinkedList":
                    newTestCollection = new LinkedList(testCollection);
                    startTime = System.currentTimeMillis();
                    ((LinkedList) newTestCollection).remove(indexes[i]);
                    break;

                case "HashSet":
                    newTestCollection = new HashSet(testCollection);
                    startTime = System.currentTimeMillis();
                    ((HashSet) newTestCollection).remove(indexes[i]);
                    break;

                case "TreeSet":
                    newTestCollection = new TreeSet(testCollection);
                    startTime = System.currentTimeMillis();
                    ((TreeSet) newTestCollection).remove(indexes[i]);
            }
            timeTest += System.currentTimeMillis() - startTime;
        }
        methodName = "remove";
        testResult = (int) timeTest / 100;
    }

    public void contains(int[] indexes) {
        long timeTest = 0;
        long startTime = 0;
        int size = testCollection.size();
        AbstractCollection newTestCollection;

        for (int i = 0; i < 100; i++) {
            switch (className) {
                case "ArrayList":
                    startTime = System.currentTimeMillis();
                    ((ArrayList) testCollection).contains(indexes[i]);
                    break;

                case "LinkedList":
                    startTime = System.currentTimeMillis();
                    ((LinkedList) testCollection).contains(indexes[i]);
                    break;

                case "HashSet":
                    startTime = System.currentTimeMillis();
                    ((HashSet) testCollection).contains(indexes[i]);
                    break;

                case "TreeSet":
                    startTime = System.currentTimeMillis();
                    ((TreeSet) testCollection).contains(indexes[i]);
            }
            timeTest += System.currentTimeMillis() - startTime;
        }
        methodName = "contains";
        testResult = (int) timeTest / 100;
    }

    public void iteratorAdd() {
        long timeTest = 0;
        long startTime = 0;
        int size = testCollection.size();
        AbstractCollection newTestCollection;
        ListIterator listIterator;

        for (int i = 0; i < 100; i++) {
            switch (className) {
                case "ArrayList":
                    newTestCollection = new ArrayList(testCollection);
                    listIterator = ((ArrayList) newTestCollection).listIterator();
                    startTime = System.currentTimeMillis();
                    listIterator.add(1);
                    break;

                case "LinkedList":
                    newTestCollection = new LinkedList(testCollection);
                    listIterator = (ListIterator) newTestCollection.iterator();
                    startTime = System.currentTimeMillis();
                    listIterator.add(1);
                    break;
            }
            timeTest += System.currentTimeMillis() - startTime;
        }
        methodName = "iterator.add";
        testResult = (int) timeTest / 100;
    }

    public void iteratorRemove() {
        long timeTest = 0;
        long startTime = 0;
        int size = testCollection.size();
        AbstractCollection newTestCollection;
        ListIterator listIterator;
        Iterator iterator;

        for (int i = 0; i < 100; i++) {
            switch (className) {
                case "ArrayList":
                    newTestCollection = new ArrayList(testCollection);
                    iterator = newTestCollection.iterator();
                    iterator.next();
                    startTime = System.currentTimeMillis();
                    iterator.remove();
                    timeTest += System.currentTimeMillis() - startTime;
                    break;

                case "LinkedList":
                    newTestCollection = new LinkedList (testCollection);
                    iterator = newTestCollection.iterator();
                    iterator.next();
                    startTime = System.currentTimeMillis();
                    iterator.remove();
                    timeTest += System.currentTimeMillis() - startTime;
                    break;
            }
        }
        methodName = "iterator.remove";
        testResult = (int) timeTest / 100;
    }
}















































