package com.emeraldElves.alcohollabelproject;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kylec on 4/15/2017.
 */
class CSVReader {

    private List<String> columns;
    private List<List<String>> data;


    public CSVReader(InputStream is) {
        columns = new ArrayList<>();
        data = new ArrayList<>();
        Scanner scanner = new Scanner(is);
        loadCSV(scanner);
    }

    public CSVReader(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            loadCSV(scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCSV(Scanner scanner) {
        if (scanner.hasNext()) {
            String[] cols = scanner.nextLine().split(",");
            Collections.addAll(columns, cols);
        }
        while (scanner.hasNext()) {
            String[] cols = scanner.nextLine().split(",");
            List<String> row = new ArrayList<>();
            Collections.addAll(row, cols);
            data.add(row);
        }
    }

    public int getNumRows() {
        return data.size();
    }

    public String getString(String column, int row) {
        int colIndex = columns.indexOf(column);
        return data.get(row).get(colIndex);
    }

    public int getInt(String column, int row) {
        int colIndex = columns.indexOf(column);
        return Integer.valueOf(data.get(row).get(colIndex));
    }

    public long getLong(String column, int row){
        int colIndex = columns.indexOf(column);
        return Long.valueOf(data.get(row).get(colIndex));
    }

    public double getDouble(String column, int row) {
        int colIndex = columns.indexOf(column);
        return Double.valueOf(data.get(row).get(colIndex));
    }

    public BigInteger getBigint(String column, int row){
        int colIndex = columns.indexOf(column);
        return  BigInteger.valueOf(Long.valueOf(data.get(row).get(colIndex)));
    }
}