package com.example;

import java.io.*;
import java.util.ArrayList;

public class FileController {

    private final String fileName;

    public FileController(String fileName) {
        this.fileName = fileName;

    }

    public ArrayList<Integer> loadSize(){
        ArrayList<Integer> arrayList = new ArrayList<>();

        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text;
            if((text = reader.readLine()) != null){
                String[] parts = text.split(" ");
                for (String part : parts) {
                    arrayList.add(Integer.parseInt(part));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.printf("File %s not found ", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (arrayList.size() != 2) return null;
        return arrayList;
    }

    public Matrix loadMatrix(){
        ArrayList<ArrayList<Integer>> auxMatrix = new ArrayList<>();
        File file = new File(fileName);
        // read data
        int actualSize = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text;
            int i = 0;
            text = reader.readLine(); // first line contains size of matrix
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

    public void saveString(String result){
        writeToFile(result, false);
    }

    public void saveResult(int n, int m, double result){
        String str = String.format("%d %d \n%.3f\n", n, m, result);
        writeToFile(str, false);
    }

    private void writeToFile(String str, boolean a){
        try (var writer = new BufferedWriter(new FileWriter(fileName, a))) {
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
