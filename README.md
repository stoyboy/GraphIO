<h1 align="center">Welcome to GraphIO 👋</h1>
<p>
  <a href="https://github.com/stoyboy/GraphIO/blob/master/LICENSE" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>
</p>

> GraphIO is a program developed in Java 17 for the calculation and representation of subareas of graph theory. It was developed as part of a programming assignment in the subject POS (graph theory).
> 
> GraphIO ist ein in Java 17 entwickeltes Programm zur Berechnung und Darstellung von Teilbereichen der Graphentheorie. Es wurde im Rahmen einer Programmieraufgabe im Fach POS (Graphentheorie) entwickelt.


## Current features
* [Importing matrices from CSV files as a adjacency matrix](#importing-matrices-from-csv-files-as-adjacency-matrix)
* [Calculating distance matrices](#calculating-distance-matrices)
* [Calculating eccentricities](#calculating-eccentricities)
* [Calculating radius](#calculating-radius)
* [Calculating diameter](#calculating-diameter)
* [Calculating center](#calculating-center)
* [Calculating path matrices](#calculating-path-matrices)
* [Calculating components](#calculating-components)
* [Calculating articulations](#calculating-articulations)
* [Calculating bridges](#calculating-bridges)

## Examples
### Importing matrices from CSV files as adjacency matrix
[Heading]: #importing-matrices-from-csv-files-as-a-adjacency-matrix
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
    }
}
````

### Calculating distance matrices
[Heading]: #calculating-distance-matrices
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        DistanceMatrix distanceMatrix = new DistanceMatrix(adjacencyMatrix);
        
        distanceMatrix.printMatrix();
    }
}
````
#### Output
````text
0|1|2|2|3|4|4|4|4|5|5|5|6|6|7|7|6|8|8|7|7|8|9|9|
1|0|1|1|2|3|3|3|3|4|4|4|5|5|6|6|5|7|7|6|6|7|8|8|
2|1|0|1|1|2|2|2|2|3|3|3|4|4|5|5|4|6|6|5|5|6|7|7|
2|1|1|0|1|2|2|2|2|3|3|3|4|4|5|5|4|6|6|5|5|6|7|7|
3|2|1|1|0|1|1|1|1|2|2|2|3|3|4|4|3|5|5|4|4|5|6|6|
4|3|2|2|1|0|1|2|2|3|3|2|3|4|5|5|4|6|6|5|5|6|7|7|
4|3|2|2|1|1|0|2|2|3|3|1|2|4|5|5|4|6|6|5|5|6|7|7|
4|3|2|2|1|2|2|0|1|1|1|3|4|2|3|3|2|4|4|3|3|4|5|5|
4|3|2|2|1|2|2|1|0|2|2|3|4|3|4|4|3|5|5|4|4|5|6|6|
5|4|3|3|2|3|3|1|2|0|1|4|5|2|3|3|2|4|4|3|3|4|5|5|
5|4|3|3|2|3|3|1|2|1|0|4|5|1|2|2|1|3|3|2|2|3|4|4|
5|4|3|3|2|2|1|3|3|4|4|0|1|5|6|6|5|7|7|6|6|7|8|8|
6|5|4|4|3|3|2|4|4|5|5|1|0|6|7|7|6|8|8|7|7|8|9|9|
6|5|4|4|3|4|4|2|3|2|1|5|6|0|1|2|2|2|2|3|3|4|5|5|
7|6|5|5|4|5|5|3|4|3|2|6|7|1|0|1|2|1|1|3|3|4|5|5|
7|6|5|5|4|5|5|3|4|3|2|6|7|2|1|0|1|2|2|2|2|3|4|4|
6|5|4|4|3|4|4|2|3|2|1|5|6|2|2|1|0|3|3|1|1|2|3|3|
8|7|6|6|5|6|6|4|5|4|3|7|8|2|1|2|3|0|1|4|4|5|6|6|
8|7|6|6|5|6|6|4|5|4|3|7|8|2|1|2|3|1|0|4|4|5|6|6|
7|6|5|5|4|5|5|3|4|3|2|6|7|3|3|2|1|4|4|0|1|2|3|3|
7|6|5|5|4|5|5|3|4|3|2|6|7|3|3|2|1|4|4|1|0|1|2|2|
8|7|6|6|5|6|6|4|5|4|3|7|8|4|4|3|2|5|5|2|1|0|1|1|
9|8|7|7|6|7|7|5|6|5|4|8|9|5|5|4|3|6|6|3|2|1|0|1|
9|8|7|7|6|7|7|5|6|5|4|8|9|5|5|4|3|6|6|3|2|1|1|0|
````

### Calculating eccentricities
[Heading]: #calculating-eccentricities
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        DistanceMatrix distanceMatrix = new DistanceMatrix(adjacencyMatrix);
        Eccentricity eccentricity = new Eccentricity(distanceMatrix);
        
        eccentricity.printEccentricity();
    }
}
````
#### Output
````text
{A=9, B=8, C=7, D=7, E=6, F=7, G=7, H=5, I=6, J=5, K=5, L=8, M=9, N=6, O=7, P=7, Q=6, R=8, S=8, T=7, U=7, V=8, W=9, X=9}
````

### Calculating radius
[Heading]: #calculating-radius
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        DistanceMatrix distanceMatrix = new DistanceMatrix(adjacencyMatrix);
        Eccentricity eccentricity = new Eccentricity(distanceMatrix);

        eccentricity.printRadius();
    }
}
````
#### Output
````text
rad(G) = 5
````

### Calculating diameter
[Heading]: #calculating-diameter
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        DistanceMatrix distanceMatrix = new DistanceMatrix(adjacencyMatrix);
        Eccentricity eccentricity = new Eccentricity(distanceMatrix);

        eccentricity.printDiameter();
    }
}
````
#### Output
````text
dm(G) = 9
````

### Calculating center
[Heading]: #calculating-center
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        DistanceMatrix distanceMatrix = new DistanceMatrix(adjacencyMatrix);
        Eccentricity eccentricity = new Eccentricity(distanceMatrix);

        eccentricity.printCenter();
    }
}
````
#### Output
````text
Z(G) = {H,J,K}
````

### Calculating path matrices
[Heading]: #calculating-path-matrices
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        PathMatrix pathMatrix = new PathMatrix(adjacencyMatrix);
        
        pathMatrix.printMatrix();
    }
}
````
#### Output
````text
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|
````

### Calculating components
[Heading]: #calculating-components
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        PathMatrix pathMatrix = new PathMatrix(adjacencyMatrix);
        Components components = new Components(pathMatrix);

        components.print();
    }
}
````
#### Output
````text
c(G) = 1
K₁ = (K(1), {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X})
````

### Calculating articulations
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        PathMatrix pathMatrix = new PathMatrix(adjacencyMatrix);
        Components components = new Components(pathMatrix);
        Articulation articulation = new Articulation(adjacencyMatrix, components.getComponents().size());

        articulation.print();
    }
}
````
#### Output
`````text
B E G H K L O Q U V 
`````

### Calculating bridges
````java
class Test {
    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix("PATH-TO-YOUR-CSV-FILE");
        PathMatrix pathMatrix = new PathMatrix(adjacencyMatrix);
        Components components = new Components(pathMatrix);
        Bridges bridges = new Bridges(adjacencyMatrix, components.getComponents().size());

        bridges.print();
    }
}
````
#### Output
````text
[A,B]
[B,A]
[G,L]
[L,G]
[L,M]
[M,L]
[U,V]
[V,U]
````
## Author

👤 **Jovan Stojimirovic**

* GitHub: [@stoyboy](https://github.com/stoyboy)

## Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2022 [Jovan Stojimirovic](https://github.com/stoyboy).<br />
This project is [MIT](https://github.com/stoyboy/GraphIO/blob/master/LICENSE) licensed.

***