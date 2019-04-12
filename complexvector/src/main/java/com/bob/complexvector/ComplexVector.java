package com.bob.complexvector;

import com.bob.complexmatrices.ComplexMatrix;
import com.bob.complexnumbers.Complex;
public class ComplexVector {

        private Complex x;
        private Complex y;
        private Complex z;

        public ComplexVector(Complex i, Complex j, Complex k) {
            x = i;
            y = j;
            z = k;
        }

        public ComplexVector(ComplexVector v) {
            x = v.x;
            y = v.y;
            z = v.z;
        }

        public Complex getX() {
            return x;
        }

        public Complex getY() {
            return y;
        }

        public Complex getZ() {
            return z;
        }

        public ComplexVector sum(ComplexVector v) {
            ComplexVector result = new ComplexVector(v);
            result.x = result.x.add(x);
            result.y = result.y.add(y);
            result.z = result.z.add(z);

            return result;
        }



        public ComplexVector sub(ComplexVector v) {
            ComplexVector result = new ComplexVector(v);
            result.x = result.x.sub(x);
            result.y = result.y.sub(y);
            result.z =  result.z.sub(z);

            return result;
        }

        public ComplexVector mult(Complex c) {
            ComplexVector result = new ComplexVector(this);
            result.x = result.x.mult(c);
            result.y = result.y.mult(c);
            result.z = result.z.mult(c);
            return result;
        }

        public Complex dot(ComplexVector v) {
            return x.mult(v.x).add(y.mult(v.y)).add(z.mult(v.y));
        }

        public ComplexVector unit() {
            ComplexVector result = new ComplexVector(this);
            float div = (float) Math.sqrt(x.mult(x.conjugate()).add(y.mult(y.conjugate())).add(z.mult(z.conjugate())).magnitude());
            result.x = x.mult(1/div);
            result.y = y.mult(1/div);
            result.z = z.mult(1/div);

            return result;
        }

        public ComplexVector cross(ComplexVector v) {
            ComplexVector result = new ComplexVector(new Complex(0), new Complex(0), new Complex(0));

            result.x = y.mult(v.z).sub(z.mult(v.y));
            result.y = z.mult(v.x).sub(x.mult(v.z));
            result.z = x.mult(v.y).sub(y.mult(v.x));
            return result;
        }

        public ComplexMatrix vectToMat() {
            Complex[][] value = {{x}, {y}, {z}};
            ComplexMatrix result = new ComplexMatrix(value);
            return result;

        }

        public float mag() {
            return (float) Math.sqrt(x.mult(x.conjugate()).add(y.mult(y.conjugate())).add(z.mult(z.conjugate())).magnitude());
        }


}
