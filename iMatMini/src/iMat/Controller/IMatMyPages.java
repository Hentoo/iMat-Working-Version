package iMat.Controller;

import iMat.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IMatMyPages extends AnchorPane{

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    iMatMainController controller;

    IMatMyPages(iMatMainController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatMyPages.fxml"));
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
    public AnchorPane favoritesAnchorPane;

    @FXML
    private AnchorPane mainAnchorPane;
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
    @FXML
    private FlowPane favoritesFlowPane;
    @FXML
    private AnchorPane MainMyPagesFlowPAne;
    @FXML
    private AnchorPane helpMyPages;
    @FXML
    private AnchorPane paymentAnchorPane;
    @FXML
    private AnchorPane personUppgifter;
    @FXML
    private TextField cardNumberTextField;
    @FXML
    private TextField nameCardHolder;
    @FXML
    private TextField cvcField;
    @FXML
    private TextField firstNameContainer;
    @FXML
    private TextField lastNameContainer;
    @FXML
    private TextField phonenumberContainer;
    @FXML
    private TextField mailContainer;
    @FXML
    private TextField adressContainer;
    @FXML
    private TextField postcodeContainer;
    @FXML
    private ComboBox cardTypeComboBox;
    @FXML
    private ComboBox monthComboBox;
    @FXML
    private ComboBox yearComboBox;

    String[] years = {"2019", "2020", "2021", "2022", "2023", "2024", "2025"};
    String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};


    @FXML
    private Button categoryButton6; //TODO 6 HJÄLP
    @FXML
    private Button categoryButton4; //TODO 4 Favoriter
    @FXML
    private Button categoryButton3; //TODO 3 KöpHistorik
    @FXML
    private Button categoryButton2; //TODO 2 Personliga uppgifter -
    @FXML
    private Button categoryButton1; //TODO 1 Betalningssätt -



    List<Object> purchases; //TODO Fixa denna så den innehåller alla tidigare köps rutor.

    List<iMatProduct> favorites = new ArrayList<>();

    Model model = Model.getInstance();

    int hasBeenDone = 0;
    int hasBeenDone2 = 0;


    @FXML
    private void pressedOnLogoButton(){
        //TODO

        controller.mainScreen.toFront();
        mainAnchorPane.toBack();

      /*  infoViewPane.toBack();

        productsFlowPane.getChildren().clear();
        updateProductList(offers);*/
    }

    /**
     * Lägger fram en sida där användaren kan redigera kortinfo
     * denna knapp ska även uppdatera det som står i rutorna med information från backend så de slipper fylla i det varje gång
     */
    @FXML
    private void pressedOnCategory1(){
        paymentAnchorPane.toFront();

        fillCardInfoTextFields();
        //TODO Lägger fram en sida där användaren kan redigera kortinfo

    }

    /**
     * Lägger fram en sida där användaren kan redigera Personuppgifter och leveransadress
     * denna knapp ska även kalla på en metod som ska uppdatera det som står i rutorna med information från backend så de slipper fylla i det varje gång
     */
    @FXML
    private void pressedOnCategory2(){
        personUppgifter.toFront();

        fillPersonalInfoTextFields();
        //TODO Lägger fram en anchorPane där användaren akn skriva in sina personuppgifter

    }

    @FXML
    private void pressedOnCategory3(){
        MainMyPagesFlowPAne.toFront();
        updateHistoryList();
        //TODO lägger fram hela mainsidan som ska visa de tidigare köpen samt uppdaterar listan med eventuell ny information

    }

    /**
     * läggerFavorit-sidan överst samt laddar de favoritmarkerade articklarna till den
     */
    @FXML
    private void pressedOnCategory4(){
        favorites = controller.favourites;
        updateFavoriteProductList();
        favoritesAnchorPane.toFront();
    }

    @FXML
    private void pressedOnCategory6(){

        helpMyPages.toFront();
        //TODO visar "startsida" för denna vy, med andra ord hjälpen med vad vyn är till för

    }


    /**
     * När man klickar på spara knappen inom kort-info sidan ska denna spara det i backenden.
     */
    @FXML
    private void saveCardInfo(){
        updateCreditCard();
    }

    /**
     * När man klickar på spara knappen på person-info sidan ska denna spara det i backenden.
     */
    @FXML
    private void savePersonalInfo(){
        updatePersonalInfo();
    }

    /**
     * saves the users data to the backend
     */
    private void updatePersonalInfo() {
        Customer customer = model.getCustomer();

        customer.setAddress(adressContainer.getText());
        customer.setEmail(mailContainer.getText());
        customer.setFirstName(firstNameContainer.getText());
        customer.setLastName(lastNameContainer.getText());
        customer.setMobilePhoneNumber(phonenumberContainer.getText());
        customer.setPostAddress(adressContainer.getText());
        customer.setPostCode(postcodeContainer.getText());
    }


    private void updateHistoryList() {
        productsFlowPane.getChildren().clear();

        for (iMatProduct product : favorites) {
            favoritesFlowPane.getChildren().add(product);
        }
    }

    private void updateFavoriteProductList() {
        favoritesFlowPane.getChildren().clear();

        for (iMatProduct product : favorites) {
            favoritesFlowPane.getChildren().add(product);
        }
    }

    /**
     * Fyller i rutorna med personlig information och leveransinformation.
     */
    private void fillPersonalInfoTextFields() {
        Customer customer = model.getCustomer();

        if (hasBeenDone2 == 0){
            setTeextfieldToIntOnly(phonenumberContainer);
            setTeextfieldToIntOnly(postcodeContainer);

            hasBeenDone2++;
        }

        phonenumberContainer.clear();
        phonenumberContainer.appendText(customer.getMobilePhoneNumber());
        postcodeContainer.clear();
        postcodeContainer.appendText(customer.getPostCode());

        firstNameContainer.clear();
        firstNameContainer.appendText(customer.getFirstName());
        lastNameContainer.clear();
        lastNameContainer.appendText(customer.getLastName());
        mailContainer.clear();
        mailContainer.appendText(customer.getEmail());
        adressContainer.clear();
        adressContainer.appendText(customer.getEmail());
    }

    /**
     * fyller i rutorna för kreditkorten så at man kan se det som tidigare har skrivigts och man slipper skriva i det varje gång
     */
    private void fillCardInfoTextFields() {
        CreditCard card = model.getCreditCard();



        if (hasBeenDone == 0){
            setTeextfieldToIntOnly(cardNumberTextField);
            setTeextfieldToIntOnly(cvcField);
            hasBeenDone++;
        }

        cardNumberTextField.clear();
        cardNumberTextField.appendText(card.getCardNumber());
        nameCardHolder.clear();
        nameCardHolder.appendText(card.getHoldersName());

        cardTypeComboBox.getItems().clear();
        cardTypeComboBox.getItems().addAll("Mastercard", "Maestro", "Visa");  //oklart om dessa rader kommer att fungera
        cardTypeComboBox.getSelectionModel().select(card.getCardType());
        monthComboBox.getItems().clear();
        monthComboBox.getItems().addAll(months);
        monthComboBox.getSelectionModel().select(card.getValidMonth());
        yearComboBox.getItems().clear();
        yearComboBox.getItems().addAll(years);
        yearComboBox.getSelectionModel().select(card.getValidYear());

        int cvc = card.getVerificationCode();
        cvcField.clear();
        cvcField.appendText(Integer.toString(cvc));
    }

    /**
     * Supposedly sets the TextField to be an int only textField
     * @param textField the text field to set as an int only TextField
     */
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

    /**
     * Uppdaterar backend med kortinformationen
     */
    private void updateCreditCard() {
        CreditCard card = model.getCreditCard();

        card.setCardNumber(cardNumberTextField.getText());
        card.setHoldersName(nameCardHolder.getText());

        String selectedValue = (String) cardTypeComboBox.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);

        selectedValue = (String) monthComboBox.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));

        selectedValue = (String) yearComboBox.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));

        card.setVerificationCode(Integer.parseInt(cvcField.getText()));
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

    /**
     * sets the view to start with favorites
     */
    public void setFavorites() {
        favoritesFlowPane.toFront();
        updateFavoriteProductList();
    }

    /**
     * set the view to start with
     */
    public void setStart() {
        MainMyPagesFlowPAne.toFront();
        helpMyPages.toFront();
    }
}


