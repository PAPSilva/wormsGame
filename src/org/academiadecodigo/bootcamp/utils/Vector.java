package org.academiadecodigo.bootcamp.utils;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class Vector {

    private double [] vec;

    // Constructors

    public Vector(int size) {
        vec = new double[size];
    }

    public Vector(Vector u) {
        this(u.length());
        for(int i=0; i < vec.length; i++) {
            vec[i] = u.get(i);
        }
    }

    // Behavior

    public void add(double a) {
        for(int i=0; i < vec.length; i++) {
            vec[i] += a;
        }
    }

    public void add(Vector u) {

        // Check if sizes are compatible
        checkForCompatibleLength(u);

        // Calculate
        for(int i=0; i < vec.length; i++) {
            vec[i] += u.get(i);
        }
    }

    public static Vector add(Vector2D v, Vector2D u) {
        Vector w = new Vector(v);
        w.add(u);
        return w;
    }

    public void subtract(double a) {
        for(int i=0; i < vec.length; i++) {
            vec[i] -= a;
        }
    }

    public void subtract(Vector u) {

        // Check if sizes are compatible
        checkForCompatibleLength(u);

        // Calculate
        for(int i=0; i < vec.length; i++) {
            vec[i] -= u.get(i);
        }
    }

    public void divide(double a) {
        for(int i=0; i < vec.length; i++) {
            vec[i] /= a;
        }
    }

    public void divide(Vector u) {

        // Check if sizes are compatible
        checkForCompatibleLength(u);

        // Calculate
        for(int i=0; i < vec.length; i++) {
            vec[i] /= u.get(i);
        }
    }

    public void multiply(double a) {
        for(int i=0; i < vec.length; i++) {
            vec[i] *= a;
        }
    }

    public void multiply(Vector u) {

        // Check if sizes are compatible
        checkForCompatibleLength(u);

        // Calculate
        for(int i=0; i < vec.length; i++) {
            vec[i] *= u.get(i);
        }
    }

    public static Vector multiply(Vector2D v, Vector2D u) {
        Vector w = new Vector(v);
        w.multiply(u);
        return w;
    }

    public double dot(Vector u) throws UnsupportedOperationException {

        // Check if sizes are compatible
        checkForCompatibleLength(u);

        // Calculate
        double product = 0.0;
        for(int i=0; i < vec.length; i++) {
            product += vec[i] * u.get(i);
        }
        return product;

    }

    private void checkForCompatibleLength(Vector u) throws UnsupportedOperationException {
        if(vec.length != u.length()) {
            throw new UnsupportedOperationException("Vectors have different size");
        }
    }

    // Getters and setters

    /**
     * @param i index
     * @return element at position i.
     */
    public double get(int i) {
        return vec[i];
    }

    /**
     * Sets element at position i as a.
     */
    public void set(double a, int i) {
        vec[i] = a;
    }

    public int length() {
        return vec.length;
    }

    public double norm() {
        double norm = 0.0;
        for(int i=0; i < vec.length; i++) {
            norm += vec[i] * vec[i];
        }
        return norm;
    }

    @Override
    public String toString() {
        String str = "[Vector (";
        for(int i=0; i < vec.length; i++) {
            str += vec[i];
            if(i<vec.length-1) {
                str += ",";
            }
        }
        str += ")]";
        return str;
    }

}
