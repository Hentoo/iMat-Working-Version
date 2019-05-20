package iMat.Controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;


import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;


public class iMatMainController implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();



    @FXML
    private FlowPane productsFlowPane;
    @FXML private Button categoryButton1;
    @FXML
    private ImageView imageField;
    @FXML
    Text productText;
    @FXML
    private TextField searchField;















    Product product = dataHandler.getProduct(45);
    Product test = dataHandler.getProduct(30);


    List<Product> dairyProducts = new ArrayList<>();


    void populateFlowPane(Product product){
        productText.setText(product.getName());


    }

    @FXML
  private void pressedOnCategory1(){

        productsFlowPane.getChildren().clear();

      productsFlowPane.getChildren().add(new iMatProduct(test));

  }
/*

*/
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



/*
        categoryButton1.cancelButtonProperty().addListener(new ChangeListener<Button>() {

            @Override
            public void changed(ObservableValue<? extends Button> observable, Button oldValue, Button newValue) {
                populateFlowPane(test);
             //   updateRecipeList();
            }
        });
        */

        updateProductList(dataHandler.getProducts());





    }


    @FXML
    private void handleSearchAction(ActionEvent event) {

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


