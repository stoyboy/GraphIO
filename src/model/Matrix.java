package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Matrix {
    private ArrayList<Integer> matrix;
    private String filepath;

    public Matrix(String filepath) throws GraphIOException {
        setFilepath(filepath);
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void setFilepath(String filepath) throws GraphIOException {
        if (filepath != null) {
            this.filepath = filepath;
            return;
        }
        else {
            throw new GraphIOException("Dateipfad ist null");
        }
    }

    public void readMatrix() {
        try (
                BufferedReader br = new BufferedReader(new FileReader(getFilepath()));
                )
        {
            String line = br.readLine();
            while (line != null) {
                String[] items = line.split(";");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
