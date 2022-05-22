package model;

public class Calculator {
    public Integer[][] calculateDistancematrix(Matrix matrix) {
        Integer[][] adjacencyMatrix = duplicateMatrix(matrix.getMatrix());
        Integer[][] distanceMatrix = duplicateMatrix(adjacencyMatrix); //muss dupliziert werden ansonsten ist es eine referenz auf die selbe matrix
        int matrixSize = matrix.getMatrix().length;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (adjacencyMatrix[i][j] == 0) {
                    distanceMatrix[i][j] = null; //null = ∞
                }
            }
        }

        //Hauptdiagonale der Distanzmatrix auf 0 setzen.
        for (int i = 0; i < matrixSize; i++) {
            distanceMatrix[i][i] = 0;
        }

        for (int i = 2; i < matrixSize; i++){
            //A^x
            adjacencyMatrix = multiplyMatrix(adjacencyMatrix, matrix.getMatrix(), matrixSize);

            //D^x
            for (int j = 0; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    if (adjacencyMatrix[j][k] != 0 && distanceMatrix[j][k] == null) {
                        distanceMatrix[j][k] = i;
                    }
                }
            }
        }

        return distanceMatrix;
    }

    private Integer[][] duplicateMatrix(Integer[][] matrix) {
        Integer[][] duplicatedMatrix = new Integer[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, duplicatedMatrix[i], 0, matrix.length);
        }

        return duplicatedMatrix;
    }

    private Integer[][] multiplyMatrix(Integer[][] m1, Integer[][] m2, int size) {
        Integer[][] m3 = new Integer[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                m3[i][j] = 0;
                for (int k = 0; k < size; k++) {
                    m3[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return m3;
    }

    public void printMatrix(Integer[][] matrix) {
        int size = matrix.length;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == null)
                    str.append("\u221e" + "|");
                else
                    str.append(matrix[i][j]).append("|");
            }
            str.append(i == size - 1 ? "" : "\n");
        }
        System.out.println(str);
    }
}
