package org.example;

import java.util.Random;

public class Matrix {
    private Integer disponibleShips;
    private int[][] matrix;

    public Matrix() {
        disponibleShips = 20;
        matrix = new int[11][11];
    }

    public int getElement(int row, int col) {
        if (row >= 0 && row < 10 && col >= 0 && col < 10) {
            return matrix[row][col];
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds. Must be between 0 and 9.");
        }
    }

    public void setElement(int row, int col, int value) {
        if (row >= 0 && row < 10 && col >= 0 && col < 10) {
            matrix[row][col] = value;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds. Must be between 0 and 9.");
        }
    }

    public int setFromTo(int colStart, int rowStart, int colFinish, int rowFinish) {
        int count = 0;

        if (rowStart == rowFinish) {
            for (int i = colStart; i <= colFinish; i++) {
                if (disponibleShips == 0){
                    return -1;
                }
                if (matrix[rowStart][i] == 0) {
                    matrix[rowStart][i] = 1;
                    count++;
                    disponibleShips--;
                }
            }
            for (int i = colFinish; i <= colStart; i++) {
                if (disponibleShips == 0){
                    return -1;
                }
                if (matrix[rowStart][i] == 0) {
                    matrix[rowStart][i] = 1;
                    count++;
                    disponibleShips--;
                }
            }
        } else {
            for (int i = rowStart; i <= rowFinish; i++) {
                if (disponibleShips == 0){
                    return -1;
                }
                if (matrix[i][colStart] == 0) {
                    matrix[i][colStart] = 1;
                    count++;
                    disponibleShips--;
                }
            }
            for (int i = rowFinish; i <= rowStart; i++) {
                if (disponibleShips == 0){
                    return -1;
                }
                if (matrix[i][colStart] == 0) {
                    matrix[i][colStart] = 1;
                    count++;
                    disponibleShips--;
                }
            }
        }

        if (disponibleShips == 0){
            return -1;
        }
        return count;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Integer getDisponibleShips() {
        return disponibleShips;
    }

    // Method to set the entire matrix
    public void setMatrix(int[][] newMatrix) {
        if (newMatrix.length == 10 && newMatrix[0].length == 10) {
            matrix = newMatrix;
        } else {
            throw new IllegalArgumentException("Matrix must be 10x10.");
        }
    }


    public StringBuilder printMatrix() {
        StringBuilder sb = new StringBuilder();

        sb.append("   ");
        for (int i = 0; i < 10; i++) {
            char to = (char) ('A' + i);
            sb.append(to).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < 10; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < 10; j++) {
                if (matrix[i][j] == 0) {
                    sb.append("| ");
                } else if (matrix[i][j] == 1) {
                    sb.append("|X");
                } else {
                    sb.append("| "); // Handle other values if necessary
                }
            }
            sb.append("|\n");
        }

        return sb;
    }
}
