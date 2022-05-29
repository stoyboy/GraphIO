package model;

import java.util.ArrayList;

public class Components {
    private ArrayList<String> components = new ArrayList<>();

    public Components(PathMatrix pathMatrix) throws GraphIOException {
        if (pathMatrix != null)
            calculate(pathMatrix);
        else
            throw new GraphIOException("pathMatrix ist null");
    }

    public ArrayList<String> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<String> components) {
        this.components = components;
    }

    public void calculate(PathMatrix pathMatrix) {
        String[] rows = new String[pathMatrix.getMatrix().length];
        ArrayList<String> components = new ArrayList<>();

        for (int i = 0; i < pathMatrix.getMatrix().length; i++) {
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < pathMatrix.getMatrix().length; j++) {
                tmp.append(pathMatrix.getMatrix()[i][j]);
            }
            rows[i] = tmp.toString();
        }

        //sortiert doppelte zeilen aus
        for (String row : rows) {
            if (!components.contains(row))
                components.add(row);
        }

        for (String uniqueRow : components) {
            StringBuilder str = new StringBuilder();
            str.append('{');
            for (int i = 0; i < uniqueRow.length(); i++) {
                if (uniqueRow.charAt(i) == '1')
                    str.append(Utils.convertIntToLetter(i)).append(uniqueRow.lastIndexOf('1') == i ? "" : ',');
            }
            str.append('}');
            components.set(components.indexOf(uniqueRow), str.toString());
        }

        setComponents(components);
    }

    public void print() {
        for (int i = 0; i < this.components.size(); i++) {
            String str = "K" +
                    Utils.convertToSubscript(i + 1) +
                    " = (K(" +
                    (i + 1) +
                    "), " +
                    this.components.get(i) +
                    ")";
            System.out.println(str);
        }
    }
}
