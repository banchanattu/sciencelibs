package com.bob.interval;

public class Interval {
    private float a;
    private float b;

    public Interval(float x, float y) {
        this.a = x;
        this.b = y;
    }
    public float length() {
        return (b-a);
    }

    public boolean ismember(float x) {
        return ( a <= x) && (x <=b);
    }

    public Interval intersection(Interval i) {
        return new Interval(Math.max(a, i.a), Math.min(b, i.b));
    }

    public boolean doesintersect(Interval i) {
        return (Math.min(b, i.b) >=  Math.max(a, i.a));
    }

    public boolean equal(Interval i) {
        return (i.a == a) && (i.b == b);
    }

    public boolean after(Interval i) {
        return (a<= i.a);
    }

    public boolean before(Interval i) {
        return (b >= i.a);
    }

    public boolean contains(Interval i) {
        return this.intersection(i).equal(i);
    }
}
