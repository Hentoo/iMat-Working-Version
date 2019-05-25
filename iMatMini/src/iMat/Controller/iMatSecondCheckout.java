package iMat.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class iMatSecondCheckout extends AnchorPane implements Initializable {

    iMatMainController controller;

    @FXML
    private AnchorPane firstNameError;
    @FXML
    private AnchorPane surnameError;
    @FXML
    private TextField firstNameContainer;
    @FXML
    private TextField lastNameContainer;
    @FXML
    private Text phoneError;
    @FXML
    private TextField phonenumberContainer;
    @FXML
    private TextField mailContainer;
    @FXML
    private Text mailError;
    @FXML
    private Text adressError;
    @FXML
    private TextField adressContainer;
    @FXML
    private TextField cityContainer;
    @FXML
    private Text cityError;
    @FXML
    private TextField postcodeContainer;
    @FXML
    private Text postcodeError;
    @FXML
    Spinner daySpinner;
    @FXML Spinner monthSpinner;

    @FXML RadioButton radioButton1;
    @FXML RadioButton radioButton2;
    @FXML RadioButton radioButton3;
    @FXML RadioButton radioButton4;
    @FXML RadioButton radioButton5;


    private ToggleGroup timeToggleGroup;

    public iMatSecondCheckout(iMatMainController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatSecondCheckout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;


    }

    @FXML
    private void backToFirst(){
        controller.activateFirstCheckout();
        postcodeError.toBack();
        cityError.toBack();
        adressError.toBack();
        phoneError.toBack();
        surnameError.toBack();
        firstNameError.toBack();
        mailError.toBack();
    }

    @FXML
    private void toThirdCheckout(){

        int fieldChecker = 7;

        if (firstNameContainer.getText().isEmpty()){
            firstNameError.toFront();
        }
        else {
            firstNameError.toBack();
            fieldChecker--;
        }
        if (lastNameContainer.getText().isEmpty()){
            surnameError.toFront();
        }
        else {
            surnameError.toBack();
            fieldChecker--;
        }
        if(phonenumberContainer.getText().isEmpty()){
            phoneError.toFront();
        }
        else{
            phoneError.toBack();
            fieldChecker--;
        }
        if (mailContainer.getText().isEmpty()){
            mailError.toFront();
        }
        else{
            mailError.toBack();
            fieldChecker--;
        }
        if (adressContainer.getText().isEmpty()){
            adressError.toFront();
        }
        else{
            adressError.toBack();
            fieldChecker--;
        }
        if (cityContainer.getText().isEmpty()){
            cityError.toFront();
        }
        else{
            cityError.toBack();
            fieldChecker--;
        }
        if(postcodeContainer.getText().isEmpty()){
            postcodeError.toFront();
        }
        else{
            postcodeError.toBack();
            fieldChecker--;
        }


        if (fieldChecker == 0){
            controller.activateThirdCheckout();
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1, 1);
        daySpinner.setValueFactory(valueFactory);

        daySpinner.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

                if (daySpinner.getValueFactory() != null) {
                    int selected = valueFactory.getValue();
                    controller.setDays(selected);
                }
            }
        });

        daySpinner.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {
                    //focusgained - do nothing
                } else {
                    Integer value = Integer.valueOf(daySpinner.getEditor().getText());
                    controller.setDays(value);
                }

            }
        });

        SpinnerValueFactory<Integer> valueFactoryMonth = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1, 1);
        monthSpinner.setValueFactory(valueFactoryMonth);

        monthSpinner.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

                if (monthSpinner.getValueFactory() != null) {
                    int selected = valueFactory.getValue();
                    controller.setDays(selected);
                }
            }
        });

        monthSpinner.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {
                    //focusgained - do nothing
                } else {
                    Integer value = Integer.valueOf(monthSpinner.getEditor().getText());
                    controller.setDays(value);
                }

            }
        });

        timeToggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(timeToggleGroup);
        radioButton2.setToggleGroup(timeToggleGroup);
        radioButton3.setToggleGroup(timeToggleGroup);
        radioButton4.setToggleGroup(timeToggleGroup);
        radioButton5.setToggleGroup(timeToggleGroup);

        radioButton1.setSelected(true);

        timeToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (timeToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) timeToggleGroup.getSelectedToggle();
                    controller.setDifficulty(selected.getText());
                }
            }
        });

    }


}
