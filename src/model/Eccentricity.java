package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Eccentricity {
    private HashMap<String, Integer> eccentricity;
    private int radius;
    private int diameter;
    private String center;

    public Eccentricity(DistanceMatrix distanceMatrix) {
        calculateEccentricity(distanceMatrix);
        calculateRadius();
        calculateDiameter();
        calculateCenter();
    }

    public HashMap<String, Integer> getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(HashMap<String, Integer> eccentricity) {
        this.eccentricity = eccentricity;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public void calculateEccentricity(DistanceMatrix d) {
        Integer[][] distanceMatrix = d.getMatrix();
        HashMap<String, Integer> eccentricity = new HashMap<>();
        int letterCounter = 0;

        for (Integer[] integers : distanceMatrix) {
            int counter = 0;
            for (int j = 0; j < distanceMatrix.length; j++) {
                if (integers[j] != null && integers[j] > counter) {
                    counter = integers[j];
                }
            }
            String letter = Utils.convertIntToLetter(letterCounter);
            eccentricity.put(letter, counter);
            letterCounter++;
        }

        setEccentricity(eccentricity);
    }

    public void calculateRadius() {
        setRadius(Collections.min(this.eccentricity.values()));
    }

    public void calculateDiameter() {
        setDiameter(Collections.max(this.eccentricity.values()));
    }

    public void calculateCenter() {
        StringBuilder center = new StringBuilder();
        int radius = this.radius;

        center.append('{');
        for (Map.Entry<String, Integer> entry : this.eccentricity.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if(value == radius)
                center.append(key).append(',');
        }
        center.replace(center.lastIndexOf(","), center.lastIndexOf(",")+1, "}");

        setCenter(center.toString());
    }

    public void printEccentricity() {
        System.out.println(this.eccentricity.toString());
    }

    public void printRadius() {
        System.out.println("rad(G) = "+this.radius);
    }

    public void printDiameter() {
        System.out.println("dm(G) = "+this.diameter);
    }

    public void printCenter() {
        System.out.println("Z(G) = "+this.center);
    }
}
