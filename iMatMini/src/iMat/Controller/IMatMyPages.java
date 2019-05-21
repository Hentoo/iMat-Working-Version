package iMat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IMatMyPages extends AnchorPane{

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    iMatMainController main;

    @FXML
    private FlowPane productsFlowPane;
    @FXML
    Text productText;
    @FXML
    private TextField searchField;
    @FXML
    TextArea shoppingCartArea;
    @FXML
    private AnchorPane infoViewPane;
    @FXML
    private Button logoButton;
    @FXML
    private Label backtoStart;
    @FXML
    private Label infoViewProductName;

    List<Product> allItems = IMatDataHandler.getInstance().getProducts();

    List<Product> favorites = IMatDataHandler.getInstance().favorites();



    void populateFlowPane(Product product) {
        productText.setText(product.getName());

    }



    private void updateShoppingCart() {

        ShoppingCart shoppingCart = dataHandler.getShoppingCart();

        shoppingCartArea.setText("Antal varor: " + shoppingCart.getItems().size());
        // costLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));

    }

    @Override
    public void shoppingCartChanged(CartEvent evt) {
        updateShoppingCart();
    }



    private void initItems(List<Product> products){
        allItems.clear();
        allItems = IMatDataHandler.getInstance().getProducts(ProductCategory.CABBAGE);
        updateProductList(allItems);
    }

    @FXML
    private void pressedOnLogoButton(){
        //TODO
      /*  infoViewPane.toBack();

        productsFlowPane.getChildren().clear();
        updateProductList(offers);*/
    }

    @FXML
    private void pressedOnCategory1(){
        productsFlowPane.getChildren().clear();

        updateProductList(favorites);
    }
    @FXML
    private void pressedOnCategory2(){
        productsFlowPane.getChildren().clear();
        //TODO uppdatera med vad knappen ska gö

    }
    @FXML
    private void pressedOnCategory3(){
        productsFlowPane.getChildren().clear();
        //TODO uppdatera med vad knappen ska gö


    }
    @FXML
    private void pressedOnCategory4(){
        productsFlowPane.getChildren().clear();

        //TODO uppdatera med vad knappen ska gö            Om den ens ska göra något

    }
    @FXML
    private void pressedOnCategory5(){
        productsFlowPane.getChildren().clear();

        //TODO uppdatera med vad knappen ska gö       Om den ens ska göra något
    }
    @FXML
    private void pressedOnCategory6(){
        productsFlowPane.getChildren().clear();
        //TODO uppdatera med vad knappen ska gö         Om den ens ska göra något

    }





    private void updateProductList(List<Product> products) {


        for (Product product : products) {
            productsFlowPane.getChildren().add(new iMatProduct(product, product.getController));
        }

    }



    @FXML
    private void handleSearchAction(ActionEvent event) {
        productsFlowPane.getChildren().clear();
        List<Product> matches = dataHandler.findProducts(searchField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

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

    public void infoViewPaneToFront(){
        infoViewPane.toFront();
    }

    public void setProductName(Product product){
        infoViewProductName.setText(product.getName());
    }


}


