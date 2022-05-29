package model;

public class DistanceMatrix extends Matrix{
    public DistanceMatrix(AdjacencyMatrix adjacencyMatrix) {
        calculate(adjacencyMatrix);
    }

    public void calculate(AdjacencyMatrix a) {
        Integer[][] adjacencyMatrix = Utils.cloneMatrix(a.getMatrix());
        Integer[][] distanceMatrix = Utils.cloneMatrix(adjacencyMatrix); //muss dupliziert werden ansonsten ist es eine referenz auf die selbe matrix
        int matrixSize = a.getMatrix().length;

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
            adjacencyMatrix = Utils.multiplyMatrix(adjacencyMatrix, a.getMatrix(), matrixSize);

            //D^x
            for (int j = 0; j < matrixSize; j++) {
                for (int k = 0; k < matrixSize; k++) {
                    if (adjacencyMatrix[j][k] != 0 && distanceMatrix[j][k] == null) {
                        distanceMatrix[j][k] = i;
                    }
                }
            }
        }

        setMatrix(distanceMatrix);
    }
}
