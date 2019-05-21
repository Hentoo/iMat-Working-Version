package iMat.Controller;


import iMat.IMat;
import iMat.ProductPanel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;


import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;


public class iMatMainController implements Initializable, ShoppingCartListener {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();



    @FXML
    private FlowPane productsFlowPane;
    @FXML FlowPane categoryFlowPane;

    @FXML
    private Button categoryButton1;
    @FXML
    private ImageView imageField;
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



    Product test = dataHandler.getProduct(30);



    List<Product> allItems = IMatDataHandler.getInstance().getProducts();

    List<Product> offers = IMatDataHandler.getInstance().getProducts(ProductCategory.DAIRIES);



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
        infoViewPane.toBack();

        productsFlowPane.getChildren().clear();
        updateProductList(offers);
    }

    @FXML
  private void pressedOnCategory1(){
        productsFlowPane.getChildren().clear();

        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.FRUIT));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.BERRY));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.CITRUS_FRUIT));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.EXOTIC_FRUIT));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.MELONS));
  }
    @FXML
    private void pressedOnCategory2(){
        productsFlowPane.getChildren().clear();
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.CABBAGE));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.HERB));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.ROOT_VEGETABLE));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.POD));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.NUTS_AND_SEEDS));

    }
    @FXML
    private void pressedOnCategory3(){
        productsFlowPane.getChildren().clear();
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.BREAD));


    }
    @FXML
    private void pressedOnCategory4(){
        productsFlowPane.getChildren().clear();

        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.DAIRIES));

    }
    @FXML
    private void pressedOnCategory5(){
        productsFlowPane.getChildren().clear();

        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.MEAT));
    }
    @FXML
    private void pressedOnCategory6(){
        productsFlowPane.getChildren().clear();
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.FISH));

    }
    @FXML
    private void pressedOnCategory7(){
        productsFlowPane.getChildren().clear();
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.PASTA));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.POTATO_RICE));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.FLOUR_SUGAR_SALT));

    }
    @FXML
    private void pressedOnCategory8(){
        productsFlowPane.getChildren().clear();
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.HOT_DRINKS));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.COLD_DRINKS));


    }
    @FXML
    private void pressedOnCategory9(){
        productsFlowPane.getChildren().clear();
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.SWEET));
    }
    @FXML
    private void pressedOnCategory10(){
        productsFlowPane.getChildren().clear();
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.DAIRIES));
    }




    private void updateProductList(List<Product> products) {


        for (Product product : products) {
            productsFlowPane.getChildren().add(new iMatProduct(product));
        }

    }

//   private final iMatModel model = iMatModel.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateProductList(offers);


        updateProductList(dataHandler.getProducts());
        updateShoppingCart();





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


}


