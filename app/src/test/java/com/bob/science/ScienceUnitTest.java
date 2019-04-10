package com.bob.science;

import com.bob.math.matrices.Matrix;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ScienceUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testSolve2X2Lineer() {
        double[][] values = {{1,2},{3,4}};
        double[][] rightvalues = {{5},{6}};
        Matrix A = new Matrix(values);
        Matrix B = new Matrix(rightvalues);
        Matrix AP = A.inverse();
        Matrix I = AP.mult(A);
        assertEquals(1.0, Matrix.det(I),.00001);
        Matrix R = AP.mult(B);

        assertNotNull(R);

    }
}