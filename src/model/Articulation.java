package model;

import java.util.ArrayList;

public class Articulation {
    private ArrayList<Integer> articulations;

    public Articulation(AdjacencyMatrix adjacencyMatrix, int componentsSize) {
        calculate(adjacencyMatrix, componentsSize);
    }

    public ArrayList<Integer> getArticulations() {
        return articulations;
    }

    public void setArticulations(ArrayList<Integer> articulations) {
        this.articulations = articulations;
    }

    public void calculate(AdjacencyMatrix a, int componentCount) {
        ArrayList<Integer> articulationNodes = new ArrayList<>();
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(Utils.cloneMatrix(a.getMatrix()));
        AdjacencyMatrix tmpAdjacencyMatrix = new AdjacencyMatrix(Utils.cloneMatrix(a.getMatrix()));
        int matrixSize = a.getMatrix().length;
        int componentsCount;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < tmpAdjacencyMatrix.getMatrix().length; j++) {
                tmpAdjacencyMatrix.getMatrix()[i][j] = 0;
                tmpAdjacencyMatrix.getMatrix()[j][i] = 0;
            }

            PathMatrix tmpPathMatrix = new PathMatrix(tmpAdjacencyMatrix);
            Components tmpComponents = new Components(tmpPathMatrix);
            componentsCount = tmpComponents.getComponents().size();
            if (componentsCount > componentCount+1)
                articulationNodes.add(i);

            tmpAdjacencyMatrix.setMatrix(Utils.cloneMatrix(adjacencyMatrix.getMatrix()));
        }

        setArticulations(articulationNodes);
    }

    public void print() { //TODO null exception
        StringBuilder str = new StringBuilder();

        for (Integer i : this.articulations) {
            str.append(Utils.convertIntToLetter(i)).append(" ");
        }

        System.out.println(str.toString());
    }
}
