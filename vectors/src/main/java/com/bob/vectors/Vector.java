package com.bob.vectors;


import com.bob.math.matrices.Matrix;

public class Vector {
    private float x;
    private float y;
    private float z;

    public Vector(int i, int j, int k) {
        x = i;
        y = j;
        z = k;
    }

    public Vector(Vector v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Vector sum(Vector v) {
        Vector result = new Vector(v);
        result.x += x;
        result.y += y;
        result.z += z;

        return result;
    }



    public Vector sub(Vector v) {
        Vector result = new Vector(v);
        result.x -= x;
        result.y -= y;
        result.z -= z;

        return result;
    }

    public Vector mult(float c) {
        Vector result = new Vector(this);
        result.x *= c;
        result.y *= c;
        result.z *= c;
        return result;
    }

    public float dot(Vector v) {
        return x * v.x + y * v.y + z * v.y;
    }

    public Vector unit() {
        Vector result = new Vector(this);
        float div = (float) Math.sqrt(x * x + y * y + z * z);
        result.x = x/div;
        result.y = y/div;
        result.z = z/div;

        return result;
    }

    public Vector cross(Vector v) {
        Vector result = new Vector(0,0,0);

        result.x = y * v.z - z * v.y;
        result.y = z * v.x - x * v.z;
        result.z = x * v.y - y * v.x;
        return result;
    }

    public Matrix vectToMat() {
        double[][] value = {{(double)x}, {(double)y}, {(double)z}};
        Matrix result = new Matrix(value);
        return result;

    }

    public float mag() {
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

}
