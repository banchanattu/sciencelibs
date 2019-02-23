package com.bob.math.matrices;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MatrixTest {
    @Test
    public void initializeMatrices()
    {
        Matrix M = new Matrix(10,12);
        Order O = M.getOrder();
        assertNotNull(O);
        assertEquals(10, O.getRow(),10);
        assertEquals(12, O.getColumn());
    }
    @Test
    public void initMatricesWithValue()
    {
        double[][] val = {{1,2,3},{4,5,6}};
        Matrix M = new Matrix(val);
        Order O = M.getOrder();
        assertNotNull(O);
        assertEquals(2, O.getRow());
        assertEquals(3, O.getColumn());

    }

    /**
     * This tests Addition of Matrices. This also indirectly test division
     */
    @Test
    public void testAdd()
    {
        double[][] val = {{1,2,3},{4,5,6}};
        Matrix M = new Matrix(val);
        Matrix sum = M.add(M);
        assertNotNull(sum);

        for (int i=0; i< M.getOrder().getRow(); i++) {
            for (int j = 0; j < M.getOrder().getColumn(); j++) {
               assertTrue(M.equals(sum.mult(.5)));
            }
        }
    }
}