package model;

import java.util.ArrayList;

public class Bridges {
    private ArrayList<String[]> bridges = new ArrayList<>();

    public Bridges(AdjacencyMatrix adjacencyMatrix, int componentCount) throws GraphIOException {
        if (adjacencyMatrix != null) {
            if (componentCount >= 0) {
            calculateBridges(adjacencyMatrix, componentCount);
            }
            else
                throw new GraphIOException("componentsSize ist kleiner 0");
        }
        else
            throw new GraphIOException("adjacencyMatrix ist null");
    }

    public ArrayList<String[]> getBridges() {
        return bridges;
    }

    public void setBridges(ArrayList<String[]> bridges) {
        this.bridges = bridges;
    }

    public void calculateBridges(AdjacencyMatrix a, int componentCount) throws GraphIOException {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(Utils.cloneMatrix(a.getMatrix()));
        AdjacencyMatrix tmpAdjacencyMatrix = new AdjacencyMatrix(Utils.cloneMatrix(a.getMatrix()));
        int matrixSize = a.getMatrix().length;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (adjacencyMatrix.getMatrix()[i][j] == 1) {
                    tmpAdjacencyMatrix.getMatrix()[i][j] = 0;
                    PathMatrix tmpPathMatrix = new PathMatrix(tmpAdjacencyMatrix);
                    Components tmpComponents = new Components(tmpPathMatrix);
                    int tmpComponentCount = tmpComponents.getComponents().size();
                    if (tmpComponentCount > componentCount) {
                        bridges.add(new String[]{Utils.convertIntToLetter(i), Utils.convertIntToLetter(j)});
                    }
                    tmpAdjacencyMatrix.setMatrix(Utils.cloneMatrix(adjacencyMatrix.getMatrix()));
                }
            }
        }
        setBridges(bridges);
    }

    public void print() {
        for (String[] bridge: this.bridges) {
            System.out.println("["+bridge[0]+","+bridge[1]+"]");
        }
    }
}
