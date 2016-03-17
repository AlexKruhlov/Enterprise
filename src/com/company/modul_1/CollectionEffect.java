package com.company.modul_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by sigmund69 on 14.03.2016.
 */
public class CollectionEffect {

    public static int[] getRandomArray(int size, int interval) {
        Random random = new Random();
        int[] indexes = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = random.nextInt(size);
        }
        return indexes;
    }

    public static void tableResult(ArrayList<ListTesting> inputCollection) {

        String[][] table = new String[5][8];
        table[0][0] = " ";
        table[0][1] = "add";
        table[0][2] = "get";
        table[0][3] = "remove";
        table[0][4] = "contains";
        table[0][5] = "populate";
        table[0][6] = "iterator.add";
        table[0][7] = "iterator.remove";
        table[1][0] = "ArrayList";
        table[2][0] = "LinkedList";
        table[3][0] = "HashSet";
        table[4][0] = "TreeSet";

        for (int i = 0; i < inputCollection.size(); i++) {
            for (int k = 1; k < 8; k++) {
                if (inputCollection.get(i).getMethodName() == table[0][k]) {
                    for (int l = 1; l < 5; l++) {
                        if (inputCollection.get(i).getClassName() == table[l][0]) {
                            table[l][k] = Integer.toString(inputCollection.get(i).getTestResult());
                            break;
                        }
                    }
                }
            }
        }
        String tempStringToFile;
        try (FileWriter writer = new FileWriter("D:\\table.txt", true)) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    if (table[i][j] == null) {
                        tempStringToFile = String.format("%1$16s", "-");
                    } else {
                        tempStringToFile = String.format("%1$16s", table[i][j]);
                    }
                    System.out.print(tempStringToFile);
                    writer.write(tempStringToFile);
                }
                System.out.println();
                writer.write("\r\n");
            }
            writer.write("\r\n\r\n");
            writer.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }



    public static void main(String[] args) {
        int[] testIntervalVersion = {10_000, 100_000, 1_000_000};


        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        TreeSet treeSet = new TreeSet();
        ArrayList<ListTesting> testResultCollection = new ArrayList();
        ListTesting listTesting = null;

        for (int n = 0; n < testIntervalVersion.length; n++) {

            int[] indexes = getRandomArray(100, testIntervalVersion[n]);
            for (int i = 1; i <= 4; i++) {
                switch (i) {
                    case 1:
                        listTesting = new ListTesting(arrayList, testIntervalVersion[n]);
                        break;
                    case 2:
                        listTesting = new ListTesting(linkedList, testIntervalVersion[n]);
                        break;
                    case 3:
                        listTesting = new ListTesting(hashSet, testIntervalVersion[n]);
                        break;
                    case 4:
                        listTesting = new ListTesting(treeSet, testIntervalVersion[n]);
                }
                for (int j = 1; j <= 7; j++) {

                    if (i > 2 && j > 4) break;

                    ListTesting currentListTesting = new ListTesting(listTesting);

                    switch (j) {
                        case 1:
                            currentListTesting.add(indexes);
                            break;
                        case 2:
                            currentListTesting.populate(testIntervalVersion[n]);
                            break;
                        case 3:
                            currentListTesting.remove(indexes);
                            break;
                        case 4:
                            currentListTesting.contains(indexes);
                            break;
                        case 5:
                            currentListTesting.get(indexes);
                            break;
                        case 6:
                            currentListTesting.iteratorAdd();
                            break;
                        case 7:
                            currentListTesting.iteratorRemove();
                    }
                    testResultCollection.add(currentListTesting);
                }
            }
            tableResult(testResultCollection);
        }
        System.out.println("\n\n");
    }
}


















