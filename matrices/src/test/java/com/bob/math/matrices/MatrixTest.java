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

    /**
     * Test Unit matrix creation
     */
    @Test
    public void testUnitMatCreation()
    {
        Matrix m  = Matrix.UnitMatrix(3);
        /** Assert multiplication of two two dimensional Unit matrix is unit matrix.
         */
        assertTrue(m.mult(m).equals(m));
        /** Assert a Matrix with two dimensional multiplied with Unit matrix is the original matrix
 \        */
         double[][] val = {{1,2,3},{4,5,6},{7,8,9}};
         Matrix M = new Matrix(val);
         assertTrue(M.mult(Matrix.UnitMatrix(M.getOrder().getRow())).equals(M));

        /** Again check a unit matrix with a Matrix is the original matrix
         *
         */
        assertTrue(Matrix.UnitMatrix(M.getOrder().getRow()).mult(M).equals(M));
    }

    /**
     * Test the determinant operator
     */

    @Test
    public void testDeterminant(){
        Matrix u = Matrix.UnitMatrix(3);
        assertTrue(1.0 == Matrix.det(u));
        double[][] val = {{1,2,3},{4,5,6},{7,8,9}};
        Matrix M = new Matrix(val);
        double det = Matrix.det(M);
        assertTrue(0  == det);
        double[][] val1 = {{4,5,6},{7,8,9},{10,11,11}};
        Matrix M1 = new Matrix(val1);
        double det1 = Matrix.det(M1);
        assertTrue(3  == det1);
    }

    @Test
    public void testInverse() {
        Matrix u = Matrix.UnitMatrix(3);
//        assertTrue(u.equals(u.inverse()));
        double[][] val = {{1,2,3},{4,5,6},{7,8,10}};
        Matrix M = new Matrix(val);
        assertTrue(u.equals(M.mult(M.inverse())));
    }
}