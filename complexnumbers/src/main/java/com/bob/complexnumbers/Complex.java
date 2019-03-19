package com.bob.complexnumbers;

public class Complex {
    private double realPart;
    private double imagPart;

    private Complex() {

    }
    public Complex(double real, double imagnary)
    {
        this.realPart = real;
        this.imagPart = imagnary;
    }

    public Complex(Complex c) {
        this.realPart = c.realPart;
        this.imagPart = c.imagPart;
    }

    public double magnitude() {
        return Math.sqrt(this.realPart * this.realPart + this.imagPart * this.imagPart);
    }

    public double real() {
        return this.realPart;
    }
    public double imag() {
        return this.imagPart;
    }

    public Complex add(Complex c) {
        Complex r = new Complex(c);
        r.realPart += this.realPart;
        r.imagPart += this.imagPart;
        return r;
    }

    public Complex mult(double d) {
        Complex r = new Complex(this);
        r.realPart *= d;
        r.imagPart *= d;
        return r;

    }

    public Complex mult(Complex c) {
        Complex r = new Complex();
        r.realPart = this.realPart*c.realPart - this.imagPart*c.imagPart;
        r.imagPart = this.realPart * c.imagPart + this.imagPart * c.realPart;
        return r;
    }

    public Complex sub(Complex c) {
        Complex r = new Complex(this);
        r.realPart -= c.realPart;
        r.imagPart -= c.imagPart;
        return r;
    }

    public Complex conjugate() {
        Complex r = new Complex();
        r.realPart = this.realPart;
        r.imagPart = -1 * this.imagPart;
        return r;
    }

    public Complex divide(Complex denom) {
        Complex numerator = this.mult(denom.conjugate());
        double  denominator = 1d/(denom.imagPart * denom.imagPart + denom.realPart * denom.realPart);
        return numerator.mult(denominator);
    }

    public boolean equals(Complex compareto) {
        return ((compareto.realPart == this.realPart) && (compareto.imagPart == this.imagPart));
    }

}
