package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
    private String[][] stringMatrix;
    private int[][] matrix;
    private String filepath;

    public Matrix(String filepath) throws GraphIOException {
        setFilepath(filepath);
        readMatrix();
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void setFilepath(String filepath) throws GraphIOException {
        if (filepath != null) {
            this.filepath = filepath;
        }
        else {
            throw new GraphIOException("Dateipfad ist null");
        }
    }

    public void readMatrix() {
        try (
                BufferedReader br = new BufferedReader(new FileReader(getFilepath()))
        )
        {
            String line = br.readLine();
            String separator = ";";
            int lineCount = 0;

            ArrayList<String> lines = new ArrayList<>();

            while (line != null) {
                lines.add(line);
                lineCount++;
                line = br.readLine();
            }

            if (lineCount > 0) {
                //Erstellt ein neues Array anhand der Anzahl der Zeilen der CSV-Datei.
                this.stringMatrix = new String[lineCount][lineCount];
                this.matrix = new int[lineCount][lineCount];

                for (int i = 0; i < lines.size(); i++) {
                    String[] items = lines.get(i).split(separator);
                    if (items.length >= 0)
                        System.arraycopy(items, 0, this.stringMatrix[i], 0, items.length);
                }

                parseMatrix();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseMatrix() throws NumberFormatException {
        for (int i = 0; i < this.stringMatrix.length; i++) {
            for (int j = 0; j < this.stringMatrix[i].length; j++) {
                this.matrix[i][j] = Integer.parseInt(this.stringMatrix[i][j]);
            }
        }
    }
}
