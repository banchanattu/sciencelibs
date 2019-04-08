package com.bob.vectors;

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
        Vector v = new Vector(1,2,3);
        assertNotNull(v);
    }
    @Test
    public void test_scalarMultiplication() {
        Vector v = new Vector(1,2,3);
        float mag1 = v.mag();
        Vector v2 = v.mult(2);
        float mag2 = v2.mag();
        assertEquals( mag2, 2f*mag1, 0);
    }
    @Test
    public void test_Unity(){
        Vector v = new Vector(1,2,3);
        float mag1 = v.unit().mag();
        Vector v2 = v.mult(2);
        float mag2 = v2.unit().mag();
        assertEquals( mag1, 1f, .000001);
        assertEquals( mag2, 1f, .000001);
    }
    @Test
    public void test_CrossMultiplywithreverseZValue() {
        Vector a = new Vector(1,2,3);
        Vector b = new Vector(4,5,6);
        Vector acb = a.cross(b);
        Vector bca = b.cross(a);
        assertEquals(acb.getZ() + bca.getZ(), 0 , .000001);
    }
}