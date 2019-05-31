package iMat.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class iMatThirdCheckout extends AnchorPane {

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

    @FXML
    private void endPurchaseButton(){
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

}
