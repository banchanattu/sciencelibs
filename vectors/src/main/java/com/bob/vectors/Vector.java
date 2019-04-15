package com.bob.vectors;


import com.bob.math.matrices.Matrix;

public class Vector {
    private float x;
    private float y;
    private float z;

    /**
     * Construct vector with three basis values
     * @param i x directional coeff
     * @param j y directional coeff
     * @param k z directional coeff
     */
    public Vector(int i, int j, int k) {
        x = i;
        y = j;
        z = k;
    }

    /**
     * Construct Vector from another vector
     * @param v vector
     */
    public Vector(Vector v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    /**
     * get x coeff
     * @return x coeff
     */
    public float getX() {
        return x;
    }

    /**
     * get Y coeff
     * @return y coeff
     */
    public float getY() {
        return y;
    }

    /**
     * get Z coeff
     * @return z coeff
     */
    public float getZ() {
        return z;
    }

    /**
     * Add two vectors
     * @param v the vector that should be added
     * @return summ of the vector and the argument vector
     */
    public Vector sum(Vector v) {
        Vector result = new Vector(v);
        result.x += x;
        result.y += y;
        result.z += z;

        return result;
    }



    /**
     * Substract  two vectors
     * @param v the vector that should be substracted
     * @return difference of the vector and the argument vector
     */
    public Vector sub(Vector v) {
        Vector result = new Vector(v);
        result.x -= x;
        result.y -= y;
        result.z -= z;

        return result;
    }

    /**
     * Scalar multiplication of Vector
     * @param c scalar quantities to multiply
     * @return returs the multiplied vector
     */
    public Vector mult(float c) {
        Vector result = new Vector(this);
        result.x *= c;
        result.y *= c;
        result.z *= c;
        return result;
    }

    /**
     * Dot product of two vector
     * @param v vector to dot multiple to
     * @return result of the dot product
     */
    public float dot(Vector v) {
        return x * v.x + y * v.y + z * v.y;
    }

    /**
     * Prepares a unit vector in the direction of the original vector
     * @return unit vector
     */
    public Vector unit() {
        Vector result = new Vector(this);
        float div = (float) Math.sqrt(x * x + y * y + z * z);
        result.x = x/div;
        result.y = y/div;
        result.z = z/div;

        return result;
    }

    /**
     * Calculate Vector product AKA cross product
     * @param v Vect to cross multiplied
     * @return result of the cross multiplication
     */
    public Vector cross(Vector v) {
        Vector result = new Vector(0,0,0);

        result.x = y * v.z - z * v.y;
        result.y = z * v.x - x * v.z;
        result.z = x * v.y - y * v.x;
        return result;
    }

    /**
     * Convert a vector to a column Matrix
     * @return a column Matrix for the vector
     */
    public Matrix vectToMat() {
        double[][] value = {{(double)x}, {(double)y}, {(double)z}};
        Matrix result = new Matrix(value);
        return result;

    }

    /**
     * Magnitude of a vector
     * @return magnitude of the vector
     */
    public float mag() {
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

}
