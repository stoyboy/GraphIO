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
* [Importing matrices from CSV files](#importing-matrices-from-csv-files)
* [Calculating distance matrices](#calculating-distance-matrices)
* [Calculating eccentricities](#calculating-eccentricities)
* [Calculating radius](#calculating-radius)
* [Calculating diameter](#calculating-diameter)
* [Calculating center](#calculating-center)

## Examples
### Importing matrices from CSV files
[Heading]: #importing-matrices-from-csv-files
````java
class Test {
    public static void main(String[] args) {
        Matrix m = new Matrix("PATH-TO-YOUR-CSV-FILE");
    }
}
````

### Calculating distance matrices
[Heading]: #calculating-distance-matrices
````java
class Test {
    public static void main(String[] args) {
        Matrix m = new Matrix("PATH-TO-YOUR-CSV-FILE");
        
        System.out.println("Distanzmatrix");
        System.out.println("================================================");
        Calculator.printMatrix(m.getMatrix());
        System.out.println("================================================");
    }
}
````
#### Output
````test
Distanzmatrix
================================================
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
================================================
````

### Calculating eccentricities
[Heading]: #calculating-eccentricities
````java
class Test {
    public static void main(String[] args) {
        Matrix m = new Matrix("PATH-TO-YOUR-CSV-FILE");
        Integer[][] distanceMatrix = Calculator.calculateDistancematrix(m);

        System.out.println("Exzentrizität");
        System.out.println("================================" +
                "===========================================" +
                "=============================================");
        HashMap<String, Integer> eccentricity = Calculator.calculateEccentricity(distanceMatrix);
        System.out.println(eccentricity);
        System.out.println("================================" +
                "===========================================" +
                "=============================================");
    }
}
````
#### Output
````text
Exzentrizität
========================================================================================================================
{A=9, B=8, C=7, D=7, E=6, F=7, G=7, H=5, I=6, J=5, K=5, L=8, M=9, N=6, O=7, P=7, Q=6, R=8, S=8, T=7, U=7, V=8, W=9, X=9}
========================================================================================================================
````

### Calculating radius
[Heading]: #calculating-radius
````java
class Test {
    public static void main(String[] args) {
        Matrix m = new Matrix("PATH-TO-YOUR-CSV-FILE");
        Integer[][] distanceMatrix = Calculator.calculateDistancematrix(m);
        HashMap<String, Integer> eccentricity = Calculator.calculateEccentricity(distanceMatrix);
        
        System.out.println("Radius");
        System.out.println("================================");
        System.out.println("rad(G) = " + Calculator.calculateRadius(eccentricity));
        System.out.println("================================");
    }
}
````
#### Output
````text
Radius
================================================
rad(G) = 5
================================================
````

### Calculating diameter
[Heading]: #calculating-diameter
````java
class Test {
    public static void main(String[] args) {
        Matrix m = new Matrix("PATH-TO-YOUR-CSV-FILE");
        Integer[][] distanceMatrix = Calculator.calculateDistancematrix(m);
        HashMap<String, Integer> eccentricity = Calculator.calculateEccentricity(distanceMatrix);
        
        System.out.println("Durchmesser");
        System.out.println("================================");
        System.out.println("dm(G) = " + Calculator.calculateDiameter(eccentricity));
        System.out.println("================================");
    }
}
````
#### Output
````text
Durchmesser
================================================
dm(G) = 9
================================================
````

### Calculating center
[Heading]: #calculating-center
````java
class Test {
    public static void main(String[] args) {
        Matrix m = new Matrix("PATH-TO-YOUR-CSV-FILE");
        Integer[][] distanceMatrix = Calculator.calculateDistancematrix(m);
        HashMap<String, Integer> eccentricity = Calculator.calculateEccentricity(distanceMatrix);
        
        System.out.println("Zentrum");
        System.out.println("================================");
        System.out.println("Z(G) = " + Calculator.calculateCenter(eccentricity));
        System.out.println("================================");
    }
}
````
#### Output
````text
Zentrum
================================================
Z(G) = {H,J,K}
================================================
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