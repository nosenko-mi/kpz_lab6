package com.example;

import java.util.Arrays;

public class Main {

//  21.	среднее арифметическое отрицательных элементов в таблице.
//  22.	среднее геометрическое положительных элементов в таблице.
//  23.	наименьшее положительное число в каждой строке.
    private static final String INPUT_TXT = "input.txt";
    private static final String OUTPUT1_TXT = "output_1.txt";
    private static final String OUTPUT2_TXT = "output_2.txt";
    private static final String OUTPUT3_TXT = "output_3.txt";

    public static void main(String[] args) {
        var inputFile = new FileController(INPUT_TXT);
        var out1File = new FileController(OUTPUT1_TXT);
        var out2File = new FileController(OUTPUT2_TXT);
        var out3File = new FileController(OUTPUT3_TXT);

        Matrix matrix = inputFile.loadMatrix();

        if (matrix == null) {
            String errorMessage = "Error occurred while reading file";
            out1File.saveString(errorMessage);
            out2File.saveString(errorMessage);
            out3File.saveString(errorMessage);
            return;
        }

        out1File.saveResult(matrix.getRowCount(), matrix.getColCount(), matrix.getArithmeticMean());
        out2File.saveResult(matrix.getRowCount(), matrix.getColCount(), matrix.getGeometricMean());

        String result3 = matrix.getRowCount() + " " + matrix.getColCount() + "\n" +
                Arrays.toString(matrix.getSmallestPositiveEachRow());
        out3File.saveString(result3);
    }

    public static void staticExample(){
        Matrix matrix = FileControllerStatic.loadMatrix(INPUT_TXT);
        if (matrix == null) {
            FileControllerStatic.saveString(OUTPUT1_TXT, "Error occurred while reading file");
            FileControllerStatic.saveString(OUTPUT2_TXT, "Error occurred while reading file");
            FileControllerStatic.saveString(OUTPUT3_TXT, "Error occurred while reading file");
            return;
        }

        FileControllerStatic.saveResult(OUTPUT1_TXT, matrix.getRowCount(), matrix.getColCount(), matrix.getArithmeticMean());
        FileControllerStatic.saveResult(OUTPUT2_TXT, matrix.getRowCount(), matrix.getColCount(), matrix.getGeometricMean());

        String result3 = matrix.getRowCount() + " " + matrix.getColCount() + "\n" +
                Arrays.toString(matrix.getSmallestPositiveEachRow());
        FileControllerStatic.saveString(OUTPUT3_TXT, result3);
    }
}
