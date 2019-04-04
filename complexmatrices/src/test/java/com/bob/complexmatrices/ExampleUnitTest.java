package com.bob.complexmatrices;

import com.bob.complexmatrices.ComplexMatrix;
import com.bob.complexnumbers.Complex;
import com.bob.math.matrices.Order;

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
    public void initializeMatrices()
    {
        ComplexMatrix M = new ComplexMatrix(10,12);
        Order O = M.getOrder();
        assertNotNull(O);
        assertEquals(10, O.getRow(),10);
        assertEquals(12, O.getColumn());
    }
    @Test
    public void initMatricesWithValue()
    {
        Complex[][] val = {{new Complex(1), new Complex(2), new Complex(3)},
                            {new Complex(4),new Complex(5), new Complex(6)}};
        ComplexMatrix M = new ComplexMatrix(val);
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
        Complex[][] val = {{new Complex(1), new Complex(2), new Complex(3)},
                    {new Complex(4),new Complex(5), new Complex(6)}};
        ComplexMatrix M = new ComplexMatrix(val);
        ComplexMatrix sum = M.add(M);
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
        ComplexMatrix m  = ComplexMatrix.UnitMatrix(3);
        /** Assert multiplication of two two dimensional Unit matrix is unit matrix.
         */
        assertTrue(m.mult(m).equals(m));
        /** Assert a Matrix with two dimensional multiplied with Unit matrix is the original matrix
         \        */
        Complex[][] val = {{new Complex(1), new Complex(2), new Complex(3)},
                           {new Complex(4),new Complex(5), new Complex(6)},
                            {new Complex(7),new Complex(8),new Complex(9)}};
        ComplexMatrix M = new ComplexMatrix(val);
        assertTrue(M.mult(ComplexMatrix.UnitMatrix(M.getOrder().getRow())).equals(M));

        /** Again check a unit matrix with a Matrix is the original matrix
         *
         */
        assertTrue(ComplexMatrix.UnitMatrix(M.getOrder().getRow()).mult(M).equals(M));
    }

    /**
     * Test the determinant operator
     */

    @Test
    public void testDeterminant(){
        ComplexMatrix u = ComplexMatrix.UnitMatrix(3);
        assertTrue(new Complex(1.0).equals(ComplexMatrix.det(u)));
        Complex[][] val = {{new Complex(1),new Complex(2),new Complex(3)},{new Complex(4),new Complex(5),new Complex(6)},
                  {new Complex(7),new Complex(8), new Complex(9)}};
        ComplexMatrix M = new ComplexMatrix(val);
        Complex det = ComplexMatrix.det(M);
        assertTrue(new Complex(0).equals(det));
        Complex[][] val1 = {{new Complex(4),new Complex(5), new Complex(6)},
                        {new Complex(7),new Complex(8), new Complex(9)},
                       {new Complex(10), new Complex(11), new Complex(11)}};
        ComplexMatrix M1 = new ComplexMatrix(val1);
        Complex det1 = ComplexMatrix.det(M1);
        assertTrue(new Complex(3).equals(det1));
    }

    @Test
    public void testInverse() {
        ComplexMatrix u = ComplexMatrix.UnitMatrix(3);
        assertTrue(u.equals(u.inverse()));
        Complex[][] val = {{new Complex(1), new Complex(2), new Complex(3)},
                           {new Complex(4), new Complex(5), new Complex(6)},
                            {new Complex(7),new Complex(8),new Complex(10)}};
        ComplexMatrix M = new ComplexMatrix(val);
        ComplexMatrix I = M.inverse();
        assertTrue(u.equals(M.mult(I)));
    }
}