package com.bob.math.matrices;

public class Matrix {
    private double[][] mat;
    private Order order;

    /**
     * Constructs and empty Matrix with give order
     * @param r number of rows
     * @param c number of columns
     */
    public Matrix(int r, int c) {
        mat = new double[r][c];
        setOrder();
    }

    /**
     * Construct Matrix with given two dimensional value
     * @param values two dimensional array
     */
    public Matrix(double[][] values) {
        mat = values;
        setOrder();
    }


    /**
     * Get the Order of a matrix
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
     * @param m a second matrix to add
     * @return result matrix
     */

    public Matrix add(Matrix m) {

        assert(getOrder().getColumn()== m.getOrder().getColumn());
        assert (getOrder().getRow() == m.getOrder().getRow());
        Matrix result = new Matrix(getOrder().getRow(), getOrder().getColumn());
        for (int i=0; i< getOrder().getRow(); i++) {
            for (int j=0; j<getOrder().getColumn(); j++) {
                result.mat[i][j] = mat[i][j] + m.mat[i][j];
            }
        }

        return result;
    }

    /**
     * Compares the current matrix with another to check if each elements are equal
     * @param m a second matrix to compare with
     * @return true if they are equal else false
     */
    public boolean equals(Matrix m) {
        for (int i=0; i< getOrder().getRow(); i++) {
            for (int j=0; j<getOrder().getColumn(); j++) {
                if (mat[i][j] != m.mat[i][j]) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Scalar multiplication of a matrix.
     * @param c a scalar value to multiply
     * @return a scalar multiplied resultant matrix
     */
    public Matrix mult(double c) {
        Matrix result = new Matrix(getOrder().getRow(), getOrder().getColumn());
        for (int i=0; i< getOrder().getRow(); i++) {
            for (int j = 0; j < getOrder().getColumn(); j++) {
                result.mat[i][j] = c * mat[i][j];
            }
        }
        return  result;
    }

    /**
     * Multiply the matrix with another matrix. If the order is not comparable an assertion thrown
     * @param m a second Matrix to multiply.
     * @return result of the product matrix
     */

    public Matrix mult(Matrix m) {
        assert (getOrder().getColumn() == m.getOrder().getRow());
        Matrix result = new Matrix(getOrder().getRow(), m.getOrder().getColumn());

        double sum = 0.00;
        for (int i=0; i < getOrder().getRow(); i++){
            for (int j = 0; j < getOrder().getColumn(); j++) {
                result.mat[0][j] = sum + mat[0][j] * m.mat[j][0];
            }
        }
        return result;
    }


}
