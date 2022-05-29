package model;

import java.util.Arrays;

public class Utils {
    public static String convertIntToLetter(int i) {
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

    public static String convertToSubscript(int i) {
        String num = Integer.toString(i);
        StringBuilder letters = new StringBuilder();

        for (int j = 0; j < num.length(); j++) {
            letters.append((char) (0x2080 + Integer.parseInt(Character.toString(num.charAt(j)))));
        }

        return letters.toString();
    }

    public static Integer[][] cloneMatrix(Integer[][] matrix) {
        Integer[][] duplicatedMatrix = new Integer[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, duplicatedMatrix[i], 0, matrix.length);
        }

        return duplicatedMatrix;
    }

    public static Integer[][] multiplyMatrix(Integer[][] m1, Integer[][] m2, int size) {
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

    public static boolean checkNull(Integer[][] matrix) {
        for (Integer[] i : matrix) {
            if (Arrays.asList(i).contains(null)) {
                return true;
            };
        }
        return false;
    }
}
