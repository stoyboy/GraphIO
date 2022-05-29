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

    public static Integer[][] calculatePathmatrix(Integer[][] matrix) {
        Integer[][] adjacencyMatrix = duplicateMatrix(matrix);
        Integer[][] pathMatrix = duplicateMatrix(adjacencyMatrix);
        int matrixSize = matrix.length;

        //Hauptdiagonale der Distanzmatrix auf 1 setzen.
        for (int i = 0; i < matrixSize; i++) {
            pathMatrix[i][i] = 1;
        }

        Integer[][] potencyMatrix = multiplyMatrix(adjacencyMatrix, matrix, matrixSize);
        for (int i = 0; i < matrixSize; i++){
            potencyMatrix = multiplyMatrix(potencyMatrix, matrix, matrixSize);

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

    public static ArrayList<String> calculateComponents(Integer[][] pathMatrix) {
        String[] rows = new String[pathMatrix.length];
        ArrayList<String> components = new ArrayList<>();

        for (int i = 0; i < pathMatrix.length; i++) {
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < pathMatrix.length; j++) {
                tmp.append(pathMatrix[i][j]);
            }
            rows[i] = tmp.toString();
        }

        //sortiert doppelte zeilen aus
        for (String row : rows) {
            if (!components.contains(row))
                components.add(row);
        }

        for (String uniqueRow : components) {
            StringBuilder str = new StringBuilder();
            str.append('{');
            for (int i = 0; i < uniqueRow.length(); i++) {
                if (uniqueRow.charAt(i) == '1')
                    str.append(convertIntToLetter(i)).append(uniqueRow.lastIndexOf('1') == i ? "" : ',');
            }
            str.append('}');
            components.set(components.indexOf(uniqueRow), str.toString());
        }

        return components;
    }

    public static void printComponents(ArrayList<String> components) {
        for (int i = 0; i < components.size(); i++) {
            String str = "K" +
                    convertToSubscript(i + 1) +
                    " = (K(" +
                    (i + 1) +
                    "), " +
                    components.get(i) +
                    ")";
            System.out.println(str);
        }
    }

    public static String calculateArticulations(Matrix matrix, int componentCount) {
        ArrayList<Integer> articulationNodes = new ArrayList<>();
        Integer[][] adjacencyMatrix = duplicateMatrix(matrix.getMatrix());
        Integer[][] tmpAdjacencyMatrix = duplicateMatrix(adjacencyMatrix);
        int matrixSize = matrix.getMatrix().length;
        int tmpComponentCount;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < tmpAdjacencyMatrix.length; j++) {
                tmpAdjacencyMatrix[i][j] = 0;
                tmpAdjacencyMatrix[j][i] = 0;
            }

            Integer[][] tmpPathMatrix = calculatePathmatrix(tmpAdjacencyMatrix);
            tmpComponentCount = calculateComponents(tmpPathMatrix).size();
            if (tmpComponentCount > componentCount)
                articulationNodes.add(i);

            tmpAdjacencyMatrix = duplicateMatrix(adjacencyMatrix);
        }

        return articulationsToString(articulationNodes);
    }

    private static String articulationsToString(ArrayList<Integer> articulationNodes) { //TODO null exception
        StringBuilder str = new StringBuilder();

        for (Integer i : articulationNodes) {
            str.append(convertIntToLetter(i)).append(" ");
        }

        return str.toString();
    }

    public static ArrayList<String[]> calculateBridges(Matrix m, int componentCount) {
        ArrayList<String[]> bridges = new ArrayList<>();
        Integer[][] adjacencyMatrix = duplicateMatrix(m.getMatrix());
        Integer[][] tmpAdjacencyMatrix = duplicateMatrix(adjacencyMatrix);
        int matrixSize = m.getMatrix().length;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    tmpAdjacencyMatrix[i][j] = 0;
                    Integer[][] tmpPathMatrix = calculatePathmatrix(tmpAdjacencyMatrix);
                    int tmpComponentCount = calculateComponents(tmpPathMatrix).size();
                    if (tmpComponentCount > componentCount) {
                        bridges.add(new String[]{convertIntToLetter(i), convertIntToLetter(j)});
                    }
                    tmpAdjacencyMatrix = duplicateMatrix(adjacencyMatrix);
                }
            }
        }
        return bridges;
    }

    public static void printBridges(ArrayList<String[]> bridges) {
        for (String[] bridge: bridges) {
            System.out.println("["+bridge[0]+","+bridge[1]+"]");
        }
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

    private static String convertToSubscript(int i) {
        String num = Integer.toString(i);
        StringBuilder letters = new StringBuilder();

        for (int j = 0; j < num.length(); j++) {
            letters.append((char) (0x2080 + Integer.parseInt(Character.toString(num.charAt(j)))));
        }

        return letters.toString();
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
