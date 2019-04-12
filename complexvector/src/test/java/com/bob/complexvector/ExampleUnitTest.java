package com.bob.complexvector;

import com.bob.complexnumbers.Complex;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test_Constructor() {
        ComplexVector v = new ComplexVector(new Complex(1), new Complex(2), new Complex(3));
        assertNotNull(v);
    }
    @Test
    public void test_scalarMultiplication() {
        ComplexVector v = new ComplexVector(new Complex(1), new Complex(2), new Complex(3));
        float mag1 = v.mag();
        ComplexVector v2 = v.mult(new Complex(2));
        float mag2 = v2.mag();
        assertEquals( mag2, 2f*mag1, 0);
    }
    @Test
    public void test_Unity(){
        ComplexVector v = new ComplexVector(new Complex(1,1), new Complex(2,1), new Complex(3,1));
        float mag1 = v.unit().mag();
        ComplexVector v2 = v.mult(new Complex(2));
        float mag2 = v2.unit().mag();
        assertEquals( mag1, 1f, .000001);
        assertEquals( mag2, 1f, .000001);
    }
    @Test
    public void test_CrossMultiplywithreverseZValue() {
        ComplexVector a = new ComplexVector(new Complex(1), new Complex(2), new Complex(3));
        ComplexVector b = new ComplexVector(new Complex(4), new Complex(5), new Complex(6));
        ComplexVector acb = a.cross(b);
        ComplexVector bca = b.cross(a);
        assertEquals(acb.getZ().add(bca.getZ()).magnitude(), 0 , .000001);
    }
}