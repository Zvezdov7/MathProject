package sample;

import Jama.Matrix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Resistors
    private double y0 = 1., y1 = 1., y2 = 1.;

    //Capasitors
    private double c1 =1., c2 = 1.;

    //Inductivity
    private double l=5., u = 1., p = 1.;

    //U1-3
    private double u1 = 1.;
    private double u2 = 1.;

    public void setU(double u) {
        this.u = u;
    }

    public void setP(double p) {
        this.p = p;
    }

    private double u3 = 1.;

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

    @FXML
    public TextField textField1 = new TextField();

    @FXML
    public TextField textField2;
    @FXML
    public TextField textField3;
    @FXML
    public TextField textField4;
    @FXML
    public TextField textField5;
    @FXML
    public TextField textField6;
    @FXML
    public TextField textField7; //U
    @FXML
    public TextField textField8; //p


    @FXML
    public TextArea calculationArea;

    @FXML
    public TextArea dataArea;


    public void onButtonClick() {
        setY0(Double.parseDouble(textField1.getText()));
        setY1(Double.parseDouble(textField2.getText()));
        setY2(Double.parseDouble(textField3.getText()));
        setC1(Double.parseDouble(textField4.getText()));
        setC2(Double.parseDouble(textField5.getText()));
        setL(Double.parseDouble(textField6.getText()));
        setU(Double.parseDouble(textField7.getText()));
        setP(Double.parseDouble(textField8.getText()));

        showData();
    }

    public void solver(){

        double[][] arrayC = {{c1, 0., 0.}, {0., c2, -c2}, {0., -c2, c2}};
        double[][] arrayG = {{y0, 0., 0.}, {0., y1, 0.}, {0., 0., y2}};
        double[][] arrayL = {{1/l, -1/l, 0},{-1/l, 1/l, 0.}, {0., 0., 0.}};
        double[] B = {u * y0, 0., 0.};
        Matrix C = new Matrix(arrayC);
        C.times(p);
        Matrix G = new Matrix(arrayG);
        Matrix L = new Matrix(arrayL);
        L.times(1/p);

        Matrix M = C.plus(G.plus(L));
        Matrix b = new Matrix(B, 3);

        Matrix x = M.solve(b);

        calculationArea.setWrapText(true);
        calculationArea.appendText("Matrix C\n");
        calculationArea.appendText(Arrays.toString(C.getColumnPackedCopy()));
        calculationArea.appendText("\n");
        calculationArea.appendText("Matrix G\n");
        calculationArea.appendText(Arrays.toString(G.getColumnPackedCopy()));
        calculationArea.appendText("\n");
        calculationArea.appendText("Matrix L\n");
        calculationArea.appendText(Arrays.toString(L.getColumnPackedCopy()));
        calculationArea.appendText("\n");

        calculationArea.appendText("Maxtrix U\n");
        calculationArea.appendText(Arrays.toString(x.getColumnPackedCopy()));
        calculationArea.appendText("\n");

    }

    public void showData(){
        clearDataText();
        dataArea.appendText("Y0 = " + y0 + "\n Y1 = " + y1 + "\n Y2 = " + y2 + "\n C1 = " + c1 + "\n" +
                " C2 = " + c2 + "\n L = " + l + "\nU = " + u + "\n p = " + p);
    }
    public void clearDataText(){
        dataArea.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showData();
        textField1.textProperty().addListener((observable, oldValue, newValue) -> {
            setY0(Double.parseDouble(textField1.getText()));
            showData();
        });
        textField2.textProperty().addListener((observable, oldValue, newValue) -> {
            setY1(Double.parseDouble(textField2.getText()));
            showData();
        });
        textField3.textProperty().addListener((observable, oldValue, newValue) -> {
            setY2(Double.parseDouble(textField3.getText()));
            showData();
        });
        textField4.textProperty().addListener((observable, oldValue, newValue) -> {
            setC1(Double.parseDouble(textField4.getText()));
            showData();
        });
        textField5.textProperty().addListener((observable, oldValue, newValue) -> {
            setC2(Double.parseDouble(textField5.getText()));
            showData();
        });
        textField6.textProperty().addListener((observable, oldValue, newValue) -> {
            setL(Double.parseDouble(textField6.getText()));
            showData();
        });
        textField7.textProperty().addListener((observable, oldValue, newValue) -> {
            setU(Double.parseDouble(textField7.getText()));
            showData();
        });
        textField8.textProperty().addListener((observable, oldValue, newValue) -> {
            setP(Double.parseDouble(textField8.getText()));
            showData();
        });

    }
}
