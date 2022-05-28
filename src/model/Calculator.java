package model;

import java.util.*;

/*
TODO: end loop in distancematrix when already done
TODO: check in calculateEccentricity if Integer is null- "graph nicht zusammenhängend"
 */


public class Calculator {
    public static Integer[][] calculateDistancematrix(Matrix matrix) {
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

    public static HashMap<String, Integer> calculateEccentricity(Integer[][] matrix) {
        HashMap<String, Integer> eccentricity = new HashMap<>();
        int letterCounter = 0;

        for (Integer[] integers : matrix) {
            int counter = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (integers[j] != null && integers[j] > counter) {
                    counter = integers[j];
                }
            }
            String letter = convertIntToLetter(letterCounter);
            eccentricity.put(letter, counter);
            letterCounter++;
        }

        return eccentricity;
    }

    public static int calculateRadius(HashMap<String, Integer> eccentricity) {
        return Collections.min(eccentricity.values());
    }

    public static int calculateDiameter(HashMap<String, Integer> eccentricity) {
        return Collections.max(eccentricity.values());
    }

    public static String calculateCenter(HashMap<String, Integer> eccentricity) {
        StringBuilder center = new StringBuilder();
        int radius = calculateRadius(eccentricity);

        center.append('{');
        for (Map.Entry<String, Integer> entry : eccentricity.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if(value == radius)
                center.append(key).append(',');
        }
        center.replace(center.lastIndexOf(","), center.lastIndexOf(",")+1, "}");

        return center.toString();
    }

    public static Integer[][] calculatePathmatrix(Matrix matrix) {
        Integer[][] adjacencyMatrix = duplicateMatrix(matrix.getMatrix());
        Integer[][] pathMatrix = duplicateMatrix(adjacencyMatrix);
        int matrixSize = matrix.getMatrix().length;

        //Potenzmatrix generieren
        Integer[][] potencyMatrix = multiplyMatrix(adjacencyMatrix, matrix.getMatrix(), matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (pathMatrix[i][j] != 1 && potencyMatrix[i][j] != 0) {
                    pathMatrix[i][j] = 1;
                }
            }
        }

        //Hauptdiagonale der Distanzmatrix auf 1 setzen.
        for (int i = 0; i < matrixSize; i++) {
            pathMatrix[i][i] = 1;
        }

        for (int i = 0; i < matrixSize; i++){
            potencyMatrix = multiplyMatrix(potencyMatrix, matrix.getMatrix(), matrixSize);

            for (int j = 0; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    if (pathMatrix[j][k] != 1 && potencyMatrix[j][k] != 0) {
                        pathMatrix[j][k] = 1;
                    }
                }
            }
        }

        return pathMatrix;
    }

    public static int calculateComponents(Integer[][] pathMatrix) {
        ArrayList<String> rows = new ArrayList<>();
        ArrayList<String> components = new ArrayList<>();

        for (Integer[] integers : pathMatrix) {
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < pathMatrix.length; j++) {
                tmp.append(integers[j]);
            }
            rows.add(tmp.toString());
        }

        for (String row : rows) {
            if (!components.contains(row))
                components.add(row);
        }

        return components.size();
    }

    private static String convertIntToLetter(int i) {
        StringBuilder letters = new StringBuilder();
        int quot = i / 26;
        int rem = i % 26;
        letters.append((char) (65 + rem)); //65 ist der erste Buchstabe in der ASCII-Tabelle

        if( quot == 0 ) {
            return letters.toString();
        } else {
            return convertIntToLetter(quot-1) + letters;
        }
    }

    private static Integer[][] duplicateMatrix(Integer[][] matrix) {
        Integer[][] duplicatedMatrix = new Integer[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, duplicatedMatrix[i], 0, matrix.length);
        }

        return duplicatedMatrix;
    }

    private static Integer[][] multiplyMatrix(Integer[][] m1, Integer[][] m2, int size) {
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

    public static void printMatrix(Integer[][] matrix) {
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
