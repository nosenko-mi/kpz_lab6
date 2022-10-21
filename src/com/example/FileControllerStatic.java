package com.example;

import java.io.*;
import java.util.ArrayList;

public class FileControllerStatic {

    private FileControllerStatic(){};

    public static void saveString(String fileName, String result){
        writeToFile(fileName, result, false);
    }

    public static void saveResult(String fileName, int n, int m, double result){
        String str = String.format("%d %d \n%.3f\n", n, m, result);
        writeToFile(fileName, str, false);
    }

    public static Matrix loadMatrix(String fileName){
        ArrayList<ArrayList<Integer>> auxMatrix = new ArrayList<>();
        File file = new File(fileName);
        // read data
        int actualSize = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text;
            int i = 0;
            text = reader.readLine();
            while ((text = reader.readLine()) != null) {
//                split "1.1 0.1"
                String[] parts = text.split(" ");

                auxMatrix.add(new ArrayList<>());
                for (String part : parts) {
                    auxMatrix.get(i).add(Integer.parseInt(part));
                    actualSize++;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.printf("File %s not found ", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // check input size
        int m = auxMatrix.size();
        int n = auxMatrix.get(0).size();
        if (m * n != actualSize) return null;

        Integer[][] matrix;
        matrix = auxMatrix.stream().map(x->x.toArray(new Integer[0])).toArray(Integer[][]::new);

        return new Matrix(matrix);
    }

    private static void writeToFile(String fileName, String str, boolean a){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, a))) {
            writer.write(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.printf("File %s not found ", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occurred while writing to a file");
        }
    }
}
