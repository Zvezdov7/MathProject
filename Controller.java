package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    MathModel model = new MathModel();

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


    public void solver(){
        model.recalculate();
        calculationArea.appendText("Model solution for U1, U2 and U3: \n");
        calculationArea.appendText(Arrays.toString(model.solveModel().getColumnPackedCopy()));
        calculationArea.appendText("\n");

    }

    public void showData(){
        clearDataText();
        dataArea.appendText("Y0 = " + model.getY0() + "\nY1 = " + model.getY1() + "\nY2 = " + model.getY2() +
                "\nC1 = " + model.getC1() + "\n" +
                "C2 = " + model.getC2() + "\nL = " + model.getL() + "\nU = " + model.getU() + "\np = " + model.getP());

        dataArea.appendText("\nMatrix C\n");
        dataArea.appendText(Arrays.toString(model.getMatrC().getColumnPackedCopy()));
        dataArea.appendText("\n");
        dataArea.appendText("Matrix G\n");
        dataArea.appendText(Arrays.toString(model.getMatrG().getColumnPackedCopy()));
        dataArea.appendText("\n");
        dataArea.appendText("Matrix L\n");
        dataArea.appendText(Arrays.toString(model.getMatrL().getColumnPackedCopy()));
        dataArea.appendText("\n");
        dataArea.appendText("Left-side Matrix: \n");
        dataArea.appendText(Arrays.toString(model.getLeftPartMatrix().getColumnPackedCopy()));
        dataArea.appendText("\n");
        dataArea.appendText("Right-side Matrix: \n");
        dataArea.appendText(Arrays.toString(model.getRightPartMatrix().getColumnPackedCopy()));
        dataArea.appendText("\n");
    }
    public void clearDataText(){
        dataArea.clear();
    }
    public void clearCalculation(){calculationArea.clear();}

    private boolean isDouble(TextField input, String message){
        try {
            double value = Double.parseDouble(input.getText());
            input.setStyle("-fx-border-color: null;-fx-border-width: 0px;");
            return true;

        }catch (NumberFormatException e){
            System.out.println("Wrong number");
            input.setStyle("-fx-border-color: #ff0309;-fx-border-width: 2px;");
            return false;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       dataArea.setWrapText(true);
        calculationArea.setWrapText(true);
//        showData();

        textField1.textProperty().addListener((observable, oldValue, newValue) -> {
            if(isDouble(textField1, textField1.getText())) {
                model.setY0(Double.parseDouble(textField1.getText()));
                model.recalculate();
                showData();
            }
        });
        textField2.textProperty().addListener((observable, oldValue, newValue) -> {
            if(isDouble(textField2, textField2.getText())) {
                model.setY1(Double.parseDouble(textField2.getText()));
                model.recalculate();
                showData();
            }
        });
        textField3.textProperty().addListener((observable, oldValue, newValue) -> {
            if(isDouble(textField3, textField3.getText())) {
                model.setY2(Double.parseDouble(textField3.getText()));
                model.recalculate();
                showData();
            }
        });
        textField4.textProperty().addListener((observable, oldValue, newValue) -> {
            if(isDouble(textField4, textField4.getText())) {
                model.setC1(Double.parseDouble(textField4.getText()));
                model.recalculate();
                showData();
            }
        });
        textField5.textProperty().addListener((observable, oldValue, newValue) -> {
            if(isDouble(textField5, textField5.getText())) {
                model.setC2(Double.parseDouble(textField5.getText()));
                model.recalculate();
                showData();
            }
        });
        textField6.textProperty().addListener((observable, oldValue, newValue) -> {
            if(isDouble(textField6, textField6.getText())) {
                model.setL(Double.parseDouble(textField6.getText()));
                model.recalculate();
                showData();
            }
        });
        textField7.textProperty().addListener((observable, oldValue, newValue) -> {
            if(isDouble(textField7, textField7.getText())) {
                model.setU(Double.parseDouble(textField7.getText()));
                model.recalculate();
                showData();
            }
        });
        textField8.textProperty().addListener((observable, oldValue, newValue) -> {
            if(isDouble(textField8, textField8.getText())) {
                model.setP(Double.parseDouble(textField8.getText()));
                model.recalculate();
                showData();
            }
        });


    }

}
