package com.bob.complexmatrices;

import java.util.Arrays;
import com.bob.complexnumbers.Complex;
import com.bob.math.matrices.Matrix;
import com.bob.math.matrices.Order;


public class ComplexMatrix {


        private Complex[][] mat;
        private Order order;

        /**
         * Constructs and empty Matrix with give order
         *
         * @param r number of rows
         * @param c number of columns
         */
        public ComplexMatrix(int r, int c) {
            mat = new Complex[r][c];
            setOrder();
        }

        /**
         * Construct Matrix with given two dimensional value
         *
         * @param values two dimensional array
         */
        public ComplexMatrix(Complex[][] values) {
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
         * @param m a second ComplexMatrix to add
         * @return result ComplexMatrix
         */

        public ComplexMatrix add(ComplexMatrix m) {

            assert (getOrder().getColumn() == m.getOrder().getColumn());
            assert (getOrder().getRow() == m.getOrder().getRow());
            ComplexMatrix result = new ComplexMatrix(getOrder().getRow(), getOrder().getColumn());
            for (int i = 0; i < getOrder().getRow(); i++) {
                for (int j = 0; j < getOrder().getColumn(); j++) {
                    result.mat[i][j] = mat[i][j].add( m.mat[i][j] );
                }
            }

            return result;
        }

        /**
         * Compares the current ComplexMatrix with another to check if each elements are equal
         *
         * @param m a second ComplexMatrix to compare with
         * @return true if they are equal else false
         */
        public boolean equals(ComplexMatrix m) {
            for (int i = 0; i < getOrder().getRow(); i++) {
                for (int j = 0; j < getOrder().getColumn(); j++) {

                   // if ((Math.round((mat[i][j].sub(m.mat[i][j])).mult(1000000000)).mult(1/1000000000)) != 0) {
                   //     return false;
                   // }
                }
            }
            return true;

        }

        /**
         * Scalar multiplication of a ComplexMatrix.
         *
         * @param c a scalar value to multiply
         * @return a scalar multiplied resultant ComplexMatrix
         */
        public ComplexMatrix mult(double c) {
            ComplexMatrix result = new ComplexMatrix(getOrder().getRow(), getOrder().getColumn());
            for (int i = 0; i < getOrder().getRow(); i++) {
                for (int j = 0; j < getOrder().getColumn(); j++) {
                    result.mat[i][j] = mat[i][j].mult(c);
                }
            }
            return result;
        }

        /**
         * Multiply the ComplexMatrix with another ComplexMatrix. If the order is not comparable an assertion thrown
         *
         * @param m a second ComplexMatrix to multiply.
         * @return result of the product ComplexMatrix
         */

        public ComplexMatrix mult(ComplexMatrix m) {
            assert (getOrder().getColumn() == m.getOrder().getRow());
            ComplexMatrix result = new ComplexMatrix(getOrder().getRow(), m.getOrder().getColumn());
            for (int i = 0; i < getOrder().getRow(); i++) {
                for (int j = 0; j < m.getOrder().getColumn(); j++) {
                    result.mat[i][j] = new Complex(0.00d,0.00d);
                    for (int k = 0; k < getOrder().getRow(); k++)
                        result.mat[i][j] = result.mat[i][j].add(mat[i][k].mult(m.mat[k][j]));
                }
            }
            return result;
        }


        public ComplexMatrix tanspose() {
            ComplexMatrix result = new ComplexMatrix(getOrder().getColumn(), getOrder().getRow());
            for (int i = 0; i < getOrder().getRow(); i++) {
                for (int j = 0; j < getOrder().getColumn(); j++) {
                    result.mat[i][j] = mat[j][i];
                }
            }
            return result;
        }

        /**
         * Calculate the determinant of a ComplexMatrix
         *
         * @param m ComplexMatrix whose determinant has to be determined
         * @return determinant value
         */
        public static Complex det(ComplexMatrix m) {
            assert (m.order.getColumn() == m.order.getRow());
            Complex det = new Complex(0,0);
            if (m.order.getRow() == 2) {
                return (m.mat[0][0].mult(m.mat[1][1]).sub(m.mat[0][1].mult(m.mat[1][0])));
            } else {
                for (int i = 0; i < m.order.getRow(); i++) {
                    ComplexMatrix n = m.subMatrixOf(0, i);
                    det = det.add(m.mat[0][i].mult((det(n).mult(Math.pow(-1, i) ))));
                }
                return det;
            }

        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            //super.clone();
            ComplexMatrix result = new ComplexMatrix(order.getRow(), order.getColumn());
            for (int i = 0; i < order.getRow(); i++) {
                for (int j = 0; j < order.getColumn(); j++) {
                    result.mat[i][j] = mat[i][j];
                }
            }
            return result;
        }

        public ComplexMatrix inverse() {
            assert (order.getRow() == order.getColumn());
            assert (ComplexMatrix.det(this).equals(new Complex(0,0)) == false );
            ComplexMatrix result = new ComplexMatrix(order.getRow(), order.getColumn()*2);
            for (int i=0; i< order.getRow(); i++) {
                for (int j=0; j <order.getColumn(); j++) {
                    result.mat[i][j] = mat[i][j];
                }
            }
            /** Append the Adjugate Unit mtrix*/
            for (int i= 0; i<order.getRow(); i++) {
                for (int j=order.getColumn(); j< order.getColumn() * 2; j++) {
                    if ((j-order.getColumn()) == i)
                        result.mat[i][j] = new Complex(1, 0);
                    else
                        result.mat[i][j] = new Complex(0, 0);

                }
            }
            for (int i = 0; i < order.getRow(); i++) {
                divideRow(result, i, result.mat[i][i]);
                rowDeduce(result,i);
            }
            ComplexMatrix inverse = new ComplexMatrix(order.getRow(), order.getColumn());
            for (int i= 0; i<order.getRow(); i++) {
                for (int j=order.getColumn(); j< order.getColumn() * 2; j++) {
                    inverse.mat[i][j-order.getColumn()] = result.mat[i][j];
                }
            }

            return  inverse;

        }


        private static void divideRow(ComplexMatrix M, int row, Complex factor) {
            for (int i = 0; i < M.order.getColumn(); i++) {
                M.mat[row][i] = M.mat[row][i].divide(factor);
            }
        }

        private static void rowDeduce(ComplexMatrix M, int r) {
            for (int row = 0; row < M.order.getRow(); row++) {
                if  (row == r) continue;
                Complex factor = M.mat[row][r];
                for (int column = 0; column < M.order.getColumn(); column++) {

                    M.mat[row][column] = M.mat[row][column].sub(factor.mult(M.mat[r][column]));
                }
            }
        }






        /**
         * This is a private function. Will select a sub metric removing the rth row and cth column
         * @param r number of row to be removed
         * @param c number of column to be removed
         * @return gives a sum metrics
         */
        private ComplexMatrix subMatrixOf(int r, int c) {
            ComplexMatrix result = new ComplexMatrix(getOrder().getRow()-1, getOrder().getColumn()-1);
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
         * return a Unit ComplexMatrix
         * @param r
         * @return
         */
        public static ComplexMatrix UnitMatrix(int r) {

            Complex[][] mat = new Complex[r][r];

            for (int i=0; i< r; i++) {
                Arrays.fill(mat[i],new Complex(0.00d));
                mat[i][i] = new Complex(1, 0);
            }
            return new ComplexMatrix(mat);
        }




}
