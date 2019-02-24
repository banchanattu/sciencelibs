package com.bob.math.matrices;

import java.util.Arrays;

public class Matrix {
    private double[][] mat;
    private Order order;

    /**
     * Constructs and empty Matrix with give order
     *
     * @param r number of rows
     * @param c number of columns
     */
    public Matrix(int r, int c) {
        mat = new double[r][c];
        setOrder();
    }

    /**
     * Construct Matrix with given two dimensional value
     *
     * @param values two dimensional array
     */
    public Matrix(double[][] values) {
        mat = values;
        setOrder();
    }


    /**
     * Get the Order of a matrix
     *
     * @return {@link Order} of the Matrix
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Private member not exposed and should not be exposed
     */
    private void setOrder() {
        int row = mat.length;
        int column = mat[0].length;
        order = new Order(row, column);
    }

    /**
     * Add two matrices. If the order is not matching for addition exception is thrown
     *
     * @param m a second matrix to add
     * @return result matrix
     */

    public Matrix add(Matrix m) {

        assert (getOrder().getColumn() == m.getOrder().getColumn());
        assert (getOrder().getRow() == m.getOrder().getRow());
        Matrix result = new Matrix(getOrder().getRow(), getOrder().getColumn());
        for (int i = 0; i < getOrder().getRow(); i++) {
            for (int j = 0; j < getOrder().getColumn(); j++) {
                result.mat[i][j] = mat[i][j] + m.mat[i][j];
            }
        }

        return result;
    }

    /**
     * Compares the current matrix with another to check if each elements are equal
     *
     * @param m a second matrix to compare with
     * @return true if they are equal else false
     */
    public boolean equals(Matrix m) {
        for (int i = 0; i < getOrder().getRow(); i++) {
            for (int j = 0; j < getOrder().getColumn(); j++) {
                if (mat[i][j] != m.mat[i][j]) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Scalar multiplication of a matrix.
     *
     * @param c a scalar value to multiply
     * @return a scalar multiplied resultant matrix
     */
    public Matrix mult(double c) {
        Matrix result = new Matrix(getOrder().getRow(), getOrder().getColumn());
        for (int i = 0; i < getOrder().getRow(); i++) {
            for (int j = 0; j < getOrder().getColumn(); j++) {
                result.mat[i][j] = c * mat[i][j];
            }
        }
        return result;
    }

    /**
     * Multiply the matrix with another matrix. If the order is not comparable an assertion thrown
     *
     * @param m a second Matrix to multiply.
     * @return result of the product matrix
     */

    public Matrix mult(Matrix m) {
        assert (getOrder().getColumn() == m.getOrder().getRow());
        Matrix result = new Matrix(getOrder().getRow(), m.getOrder().getColumn());
        for (int i = 0; i < getOrder().getRow(); i++) {
            for (int j = 0; j < getOrder().getColumn(); j++) {
                result.mat[i][j] = 0.00;
                for (int k = 0; k < getOrder().getRow(); k++)
                    result.mat[i][j] = result.mat[i][j] + mat[i][k] * m.mat[k][j];
            }
        }
        return result;
    }


    public Matrix tanspose() {
        Matrix result = new Matrix(getOrder().getColumn(), getOrder().getRow());
        for (int i = 0; i < getOrder().getRow(); i++) {
            for (int j = 0; j < getOrder().getColumn(); j++) {
                result.mat[i][j] = mat[j][i];
            }
        }
        return result;
    }

    /**
     * Calculate the determinant of a Matrix
     *
     * @param m matrix whose determinant has to be determined
     * @return determinant value
     */
    public static double det(Matrix m) {
        assert (m.order.getColumn() == m.order.getRow());
        double det = 0;
        if (m.order.getRow() == 2) {
            return (m.mat[0][0] * m.mat[1][1] - m.mat[0][1] * m.mat[1][0]);
        } else {
            for (int i = 0; i < m.order.getRow(); i++) {
                Matrix n = m.subMatrixOf(0, i);
                det = det + m.mat[0][i] * (Math.pow(-1, i) * det(n));
            }
            return det;
        }

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //super.clone();
        Matrix result = new Matrix(order.getRow(), order.getColumn());
        for (int i = 0; i < order.getRow(); i++) {
            for (int j = 0; j < order.getColumn(); j++) {
                result.mat[i][j] = mat[i][j];
            }
        }
        return result;
    }

    public Matrix inverse() {
        assert (order.getColumn() == order.getRow());
        Matrix result = Matrix.UnitMatrix(order.getRow());
        try {
            Matrix m = (Matrix) this.clone();
            for (int i = 0; i < m.order.getRow(); i++) {
                divideRow(m, i, m.mat[i][i]);
                divideRow(result, i, result.mat[i][i]);
                //for (int c=0; c<m.order.getRow(); c++) {
                    rowDeduce(m, i);
                    rowDeduce(result,i);
                //}

            }
        } catch (CloneNotSupportedException e) {
            assert (false);
        }
        return result;

    }

    private static void divideRow(Matrix M, int row, double factor) {
        for (int i = 0; i < M.order.getColumn(); i++) {
            M.mat[row][i] /= factor;
        }
    }

    private static void rowDeduce(Matrix M, int r) {
        for (int row = 0; row < M.order.getColumn(); row++) {
            if (row == r) continue;
            double factor = M.mat[row][r];
            for (int column = 0; column < M.order.getRow(); column++) {

                M.mat[row][column] = M.mat[row][column] - factor * M.mat[r][column];
            }
        }
    }






    /**
     * This is a private function. Will select a sub metric removing the rth row and cth column
     * @param r number of row to be removed
     * @param c number of column to be removed
     * @return gives a sum metrics
     */
    private Matrix subMatrixOf(int r, int c) {
        Matrix result = new Matrix(getOrder().getRow()-1, getOrder().getColumn()-1);
        int rr =0, rc =0;
        for (int i=0; i<getOrder().getRow(); i++) {
            if (i == r) continue;
            for (int j=0; j<getOrder().getColumn(); j++) {
                if (j== c) continue;
                result.mat[rr][rc] = mat[i][j];
                rc++;
            }
            rc = 0;
            rr++;
        }
        return result;
    }

    /**
     * return a Unit Matrix
     * @param r
     * @return
     */
    public static Matrix UnitMatrix(int r) {

         double[][] mat = new double[r][r];

         for (int i=0; i< r; i++) {
             Arrays.fill(mat[i],0.00d);
             mat[i][i] = 1;
         }
         return new Matrix(mat);
    }


}
