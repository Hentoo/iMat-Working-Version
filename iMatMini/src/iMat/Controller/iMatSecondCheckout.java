package iMat.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;

public class iMatSecondCheckout extends AnchorPane {

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


}
