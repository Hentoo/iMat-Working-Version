package iMat.Controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class iMatMainController implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();



    @FXML
    private FlowPane productsFlowPane;

    Product product = dataHandler.getProduct(45);

    private void updateProductList(List<Product> products) {

        productsFlowPane.getChildren().clear();

        productsFlowPane.getChildren().add(new iMatProduct(product));
/*
        for (Product product : products) {

            productsFlowPane.getChildren().add(new iMatProduct(product));
        }
*/
    }

//   private final iMatModel model = iMatModel.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateProductList(dataHandler.getProducts());

    }

    private void updateCreditCard() {

     //   CreditCard card = model.getCreditCard();

        /*card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());

        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);

        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));

        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));

        card.setVerificationCode(Integer.parseInt(cvcField.getText()));*/

    }


    private void setupAccountPane() {

    }

    private void updateBottomPanel() {
     //   ShoppingCart shoppingCart = model.getShoppingCart();

        //TODO - Fixa metoder
        //setNumberOfItems("Antal varor: " + shoppingCart.getItems().size());
        //setTotalCosttLabel("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));

    }


}


