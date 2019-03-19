package com.bob.complexnumbers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.bob.complexnumbers.test", appContext.getPackageName());
    }
    @Test
    public void testConstructors() {
        Complex c = new Complex(1,2);
        assertNotNull(c);
    }
    @Test
    public void testAddandSub() {
        Complex c1 = new Complex(1,2);
        Complex c2 = new Complex(3,4);
        Complex sum = c1.add(c2);
        assertNotNull(sum);
        Complex subctracted = sum.sub(c2);
        assertNotNull(subctracted);
        assertTrue(subctracted.equals(c1));
    }

    @Test
    public  void testCongugate() {
        Complex c1 = new Complex(1,2);
        Complex c2 = c1.conjugate();
        Complex product = c1.mult(c2);
        assertEquals(0l, (long)product.imag());
    }

    @Test
    public void testMultiply() {
        Complex c1 = new Complex(1,2);
        Complex c2 = new Complex(3, 4);
        Complex dividec = c1.divide(c2);
        Complex multiplied = dividec.mult(c2);
        assertTrue(multiplied.equals(c1));
    }
}
