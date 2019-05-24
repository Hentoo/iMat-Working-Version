package iMat.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class iMatFirstCheckout extends AnchorPane {



    @FXML
    FlowPane firstCheckoutFlowPane;
    @FXML
    AnchorPane firstCheckout;
    @FXML
    Button backToMainButton;
    @FXML
    Label totalPrice;
    @FXML Button firstCheckoutNextButton;

    iMatMainController controller;

    public iMatFirstCheckout(iMatMainController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatFirstCheckout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
        fillCheckoutPane();
        fillTotalPrice();

    }

    public void fillCheckoutPane(){
        for(ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            firstCheckoutFlowPane.getChildren().add(new iMatCartItemController(shoppingItem, controller));
        }
    }

    public void fillTotalPrice(){
        double totalPrices = 0;

        for (ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            totalPrices = totalPrices + (shoppingItem.getProduct().getPrice() * shoppingItem.getAmount());
        }

        totalPrice.setText(Integer.toString((int) totalPrices) + " SEK");
    }

    @FXML
    private void firstCheckoutNextButtonAction(){
        controller.activateSecondCheckout();
    }


    @FXML
    private void backToMainButtonAction() {
        controller.mainToFront();
    }
}
