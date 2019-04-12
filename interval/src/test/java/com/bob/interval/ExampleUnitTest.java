package com.bob.interval;

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
    public void testConstructor() {
        Interval I = new Interval(1,10);
        assertNotNull(I);
    }
    @Test
    public void testContains() {
        Interval I = new Interval(1,10);
        assertTrue(I.contains(new Interval(1,3)));
        assertFalse(I.contains(new Interval(5,15)));
    }

    @Test
    public void intercectTest() {
        Interval I = new Interval(10,20);
        assertTrue(I.doesintersect(new Interval(3,15)));
        assertTrue(I.doesintersect(new Interval(16,30)));
        assertTrue(I.doesintersect(new Interval( 12, 16)));
        assertFalse(I.doesintersect(new Interval(1,5)));
        assertTrue(I.doesintersect(new Interval(19, 21)));
        assertTrue(I.doesintersect(new Interval(1,10)));
        assertTrue(I.doesintersect(new Interval(20, 25)));
    }
}