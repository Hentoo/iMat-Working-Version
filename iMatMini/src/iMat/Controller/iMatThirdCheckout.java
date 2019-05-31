package iMat.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CreditCard;
import javafx.scene.shape.Line;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.Date;

public class iMatThirdCheckout extends AnchorPane {

    int hasBeenDone = 0;

    iMatMainController controller;
    @FXML
    private AnchorPane buyDoneAnchor;
    @FXML
    private AnchorPane summaryAnchorPane;
    @FXML
    private Button backToStart;
    @FXML
    private Button closeProgram;
    @FXML
    private Button finalizeButton;
    @FXML
    private TextField cardNumberText;
    @FXML
    private TextField cardNameText;
    @FXML
    private ComboBox monthCardText;
    @FXML
    private ComboBox yearCardText;
    @FXML
    private ComboBox cardTypeText;
    @FXML
    private TextField cvcText;
    @FXML
    public FlowPane summaryFlowPane;

    String[] years = {"2019", "2020", "2021", "2022", "2023", "2024", "2025"};
    String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    @FXML
    private AnchorPane orderPane;
    @FXML
    private AnchorPane sequencePane;
    @FXML
    private Line lineLine;

    public iMatThirdCheckout(iMatMainController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatThirdCheckout.fxml"));
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
    private void goBackToPayment(){
        summaryAnchorPane.toBack();
        sequencePane.toFront();
        orderPane.toFront();
        lineLine.toFront();
    }

    @FXML
    private void goBackToSecondCheckout(){
        controller.activateSecondCheckout();
    }

    @FXML
    private void finalizeButtonAction(){
        summaryAnchorPane.toFront();
        lineLine.toFront();
    }

    private void fillOrderList(Order order){

        for(ShoppingItem item : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            order.getItems().add(item);
        }

    }

    @FXML
    private void endPurchaseButton(){
        controller.currentOrder = new Order();
        controller.currentOrder.setOrderNumber(controller.currentOrderNumber);
        controller.currentOrderNumber++;
        fillOrderList(controller.currentOrder);
        controller.orders.add(controller.currentOrder);
        controller.currentOrder.setDate(new Date(2019, 06, 03));
        buyDoneAnchor.toFront();
    }

    @FXML
    private void backToStart(){
        buyDoneAnchor.toBack();
        controller.mainToFront();
        IMatDataHandler.getInstance().getShoppingCart().clear();

    }

    @FXML
    private void closeProgramAction(){
        System.exit(0);
    }

    private void setTeextfieldToIntOnly(TextField textField){   //TODO Testa så detta fungerar, ska egentligen fixa så att man bara kan skriva integers =========
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void fillCardInfoTextFields() {
        CreditCard card = IMatDataHandler.getInstance().getCreditCard();



        if (hasBeenDone == 0){
            setTeextfieldToIntOnly(cardNumberText);
            setTeextfieldToIntOnly(cvcText);
            hasBeenDone++;
        }

        cardNumberText.clear();
        cardNumberText.appendText(card.getCardNumber());
        cardNameText.clear();
        cardNameText.appendText(card.getHoldersName());

        cardTypeText.getItems().clear();
        cardTypeText.getItems().addAll("Mastercard", "Maestro", "Visa");  //oklart om dessa rader kommer att fungera
        cardTypeText.getSelectionModel().select(card.getCardType());
        monthCardText.getItems().clear();
        monthCardText.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        monthCardText.getSelectionModel().select(card.getValidMonth());
        yearCardText.getItems().clear();
        yearCardText.getItems().addAll(2019, 2020, 2021, 2022, 2023, 2024, 2025);
        yearCardText.getSelectionModel().select(card.getValidYear());

        int cvc = card.getVerificationCode();
        cvcText.clear();
        cvcText.appendText(Integer.toString(cvc));
    }

    private void updateCreditCard() {
        CreditCard card = IMatDataHandler.getInstance().getCreditCard();

        card.setCardNumber(cardNumberText.getText());
        card.setHoldersName(cardNameText.getText());

        String selectedValue = (String) cardTypeText.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);


        int selectedValue1 = (int) monthCardText.getSelectionModel().getSelectedItem();
        card.setValidMonth(selectedValue1);


        selectedValue1 = (int) yearCardText.getSelectionModel().getSelectedItem();
        card.setValidYear(selectedValue1);


        card.setVerificationCode(Integer.parseInt(cvcText.getText()));
    }

}
