package sample;

import Jama.Matrix;

/**
 * Created by Zvezdov on 10.10.16.
 */
public class MathModel {
    //Resistors
    private double y0 = 1., y1 = 1., y2 = 1.;

    //Capasitors
    private double c1 =1., c2 = 1.;

    //Inductivity
    private double l=5., u = 1., p = 1.;

    //U1-3
    private double u1 = 1.;
    private double u2 = 1.;
    private double u3 = 1.;

    // Initialize matrices
    private Matrix matrC;
    private Matrix matrG;
    private Matrix matrL;
    private Matrix leftPartMatrix;
    private Matrix rightPartMatrix;


    public void recalculate(){
        // Initialize arrays for Matrices
        double[][] arrayC = {{c1, 0., 0.}, {0., c2, -c2}, {0., -c2, c2}};
        double[][] arrayG = {{y0, 0., 0.}, {0., y1, 0.}, {0., 0., y2}};
        double[][] arrayL = {{1/l, -1/l, 0},{-1/l, 1/l, 0.}, {0., 0., 0.}};
        double[] rightPartArray = {u * y0, 0., 0.};

        matrC = new Matrix(arrayC);
        matrG = new Matrix(arrayG);
        matrL = new Matrix(arrayL);
        matrC.times(p);
        matrL.times(1 / p);
        leftPartMatrix = matrC.plus(matrG.plus(matrL));
        rightPartMatrix = new Matrix(rightPartArray, 3);

    }


    public Matrix solveModel(){
        Matrix x = rightPartMatrix.solve(leftPartMatrix);
        return x;
    }

    public void setU(double u) {
        this.u = u;
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setC1(double c1) {
        this.c1 = c1;
    }

    public void setC2(double c2) {
        this.c2 = c2;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getC1() {
        return c1;
    }

    public double getC2() {
        return c2;
    }

    public double getL() {
        return l;
    }

    public double getU() {
        return u;
    }

    public double getP() {
        return p;
    }

    public double getY0() {

        return y0;
    }

    public Matrix getMatrC() {
        return matrC;
    }

    public Matrix getMatrG() {
        return matrG;
    }

    public Matrix getMatrL() {
        return matrL;
    }

    public Matrix getLeftPartMatrix() {
        return leftPartMatrix;
    }

    public Matrix getRightPartMatrix() {
        return rightPartMatrix;
    }
}
