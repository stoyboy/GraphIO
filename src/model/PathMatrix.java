package model;

import java.util.ArrayList;

public class PathMatrix extends Matrix {
    public PathMatrix(AdjacencyMatrix adjacencyMatrix) throws GraphIOException {
        if (adjacencyMatrix != null)
            calculate(adjacencyMatrix);
        else
            throw new GraphIOException("adjacencyMatrix ist null");
    }

    public void calculate(AdjacencyMatrix a) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(Utils.cloneMatrix(a.getMatrix()));
        AdjacencyMatrix pathMatrix = new AdjacencyMatrix(Utils.cloneMatrix(a.getMatrix()));
        int matrixSize = a.getMatrix().length;

        //Hauptdiagonale der Distanzmatrix auf 1 setzen.
        for (int i = 0; i < matrixSize; i++) {
            pathMatrix.getMatrix()[i][i] = 1;
        }

        Integer[][] potencyMatrix = Utils.multiplyMatrix(adjacencyMatrix.getMatrix(), a.getMatrix(), matrixSize);
        for (int i = 0; i < matrixSize; i++){
            potencyMatrix = Utils.multiplyMatrix(potencyMatrix, a.getMatrix(), matrixSize);

            for (int j = 0; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    if (pathMatrix.getMatrix()[j][k] != 1 && potencyMatrix[j][k] != 0) {
                        pathMatrix.getMatrix()[j][k] = 1;
                    }
                }
            }
        }

        setMatrix(pathMatrix.getMatrix());
    }
}
