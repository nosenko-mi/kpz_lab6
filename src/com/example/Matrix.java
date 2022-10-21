package com.example;

import java.util.Arrays;
import java.util.Random;

public class Matrix {
    private Integer[][] matrix;

    public Matrix(){}

    public Matrix(Integer[][] matrix) {
        this.matrix = matrix;
    }

    //    21.	среднее арифметическое отрицательных элементов в таблице.
    public double getArithmeticMean(){
        int amount = 0;
        double result = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] < 0){
                    result += matrix[i][j];
                    amount++;
                }
            }
        }
        if (amount > 0){
            result = result/amount;
        }
        return result;
    }

    //22.	среднее геометрическое положительных элементов в таблице.
    public double getGeometricMean(){
        double root = 0;
        double result = -1;
        double product = 1;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] > 0){
                    product *= matrix[i][j];
                    root++;
                }
            }
        }
        if (root > 1){
            result = Math.pow(product, 1/root);
        }
        return result;
    }

    //23.	наименьшее положительное число в каждой строке.
    public int[] getSmallestPositiveEachRow(){
        int[] result = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++){
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] > 0 && matrix[i][j] < min){
                    min= matrix[i][j];
                }
            }
            if (min == Integer.MAX_VALUE){
                min = -1;
            }
            result[i] = min;
        }
        return result;
    }

    public int[] getSize(){
        return new int[]{matrix.length, matrix[0].length};
    }

    public int getRowCount(){
        return matrix.length;
    }

    public int getColCount(){
        return matrix[0].length;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix).replace("], ", "]\n");
    }
}
