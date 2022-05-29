package test;

import model.*;

public class TestMatrix {
    public static void main(String[] args) {
        try {
            AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("D:\\User\\Downloads\\test.csv");
            int matrixSize = adjacencyMatrix.getMatrix().length;


            System.out.println("Adjazenzmatrix");
            System.out.println("==".repeat(matrixSize));
            adjacencyMatrix.printMatrix();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");

            System.out.println("Distanzmatrix");
            System.out.println("==".repeat(matrixSize));
            DistanceMatrix distanceMatrix = new DistanceMatrix(adjacencyMatrix);
            distanceMatrix.printMatrix();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");

            System.out.println("Exzentrizität");
            System.out.println("=====".repeat(matrixSize));
            Eccentricity eccentricity = new Eccentricity(distanceMatrix);
            eccentricity.printEccentricity();
            System.out.println("=====".repeat(matrixSize)+"\n\n\n");

            System.out.println("Radius");
            System.out.println("==".repeat(matrixSize));
            eccentricity.printRadius();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");

            System.out.println("Durchmesser");
            System.out.println("==".repeat(matrixSize));
            eccentricity.printDiameter();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");

            System.out.println("Zentrum");
            System.out.println("==".repeat(matrixSize));
            eccentricity.printCenter();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");

            System.out.println("Wegmatrix");
            System.out.println("==".repeat(matrixSize));
            PathMatrix pathMatrix = new PathMatrix(adjacencyMatrix);
            pathMatrix.printMatrix();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");

            System.out.println("Komponenten");
            System.out.println("==".repeat(matrixSize));
            Components components = new Components(pathMatrix);
            System.out.println("c(G) = " + components.getComponents().size());
            components.print();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");

            System.out.println("Artikulationen");
            System.out.println("==".repeat(matrixSize));
            Articulation articulation = new Articulation(adjacencyMatrix, components.getComponents().size());
            articulation.print();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");

            System.out.println("Brücken");
            System.out.println("==".repeat(matrixSize));
            Bridges bridges = new Bridges(adjacencyMatrix, components.getComponents().size());
            bridges.print();
            System.out.println("==".repeat(matrixSize)+"\n\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
