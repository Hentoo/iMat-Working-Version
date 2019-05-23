package iMat.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class iMatCartItemController extends AnchorPane {

    @FXML
    private Label cartItemName;
    @FXML
    private Label cartItemPrice;
    @FXML
    private Button cartButtonLess;
    @FXML
    private Button cartButtonMore;
    @FXML
    private TextField cartAmount;
    @FXML
    private TextField cartTotalPrice;
    @FXML
    private Button cartRemoveItem;

    private iMatMainController controller;
    private ShoppingItem shoppingItem;



    public iMatCartItemController(ShoppingItem shoppingItem, iMatMainController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = controller;
        this.shoppingItem = shoppingItem;
        setCartItemPrice();
        setCartTotalPrice();
        setCartAmount();
        setCartItemName();
    }


    private void setCartItemPrice(){

        cartItemPrice.setText(Integer.toString((int) shoppingItem.getProduct().getPrice()) + "kr");
    }

    private void setCartTotalPrice(){
        cartTotalPrice.setText("Total: " + Integer.toString((int) shoppingItem.getProduct().getPrice() * (int) shoppingItem.getAmount()) + "kr");
    }

    private void setCartAmount(){
        cartAmount.setText(Integer.toString((int) shoppingItem.getAmount()) + "x");
    }

    private void setCartItemName(){
        cartItemName.setText(shoppingItem.getProduct().getName());
    }

    @FXML
    private void removeItemAction(){
        int index = 0;

        for (int i = 0; i < IMatDataHandler.getInstance().getShoppingCart().getItems().size(); i++){

            if (shoppingItem.getProduct().getName() == IMatDataHandler.getInstance().getShoppingCart().getItems().get(i).getProduct().getName()){
                index = i;
            }

        }

        controller.shoppingCartArea.getChildren().remove(index);
        IMatDataHandler.getInstance().getShoppingCart().getItems().remove(index);
        controller.updateTotalPrice();

    }

    @FXML
    private void lessCartButtonAction(){
        int index = 0;

        for (int i = 0; i < IMatDataHandler.getInstance().getShoppingCart().getItems().size(); i++){

            if (shoppingItem.getProduct().getName() == IMatDataHandler.getInstance().getShoppingCart().getItems().get(i).getProduct().getName()){
                index = i;
            }

        }

        if (IMatDataHandler.getInstance().getShoppingCart().getItems().get(index).getAmount() > 1){

            IMatDataHandler.getInstance().getShoppingCart().getItems().get(index).setAmount(IMatDataHandler.getInstance().getShoppingCart().getItems().get(index).getAmount()-1);

        }

        controller.updateTotalPrice();
        setCartAmount();
        setCartTotalPrice();



    }


    @FXML
    private void moreCartButtonAction(){
        int index = 0;

        for (int i = 0; i < IMatDataHandler.getInstance().getShoppingCart().getItems().size(); i++){

            if (shoppingItem.getProduct().getName() == IMatDataHandler.getInstance().getShoppingCart().getItems().get(i).getProduct().getName()){
                index = i;
            }

        }

        IMatDataHandler.getInstance().getShoppingCart().getItems().get(index).setAmount(IMatDataHandler.getInstance().getShoppingCart().getItems().get(index).getAmount()+1);


        controller.updateTotalPrice();
        setCartAmount();
        setCartTotalPrice();



    }

}
