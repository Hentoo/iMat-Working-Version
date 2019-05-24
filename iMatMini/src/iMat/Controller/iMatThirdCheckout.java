package iMat.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class iMatThirdCheckout extends AnchorPane {

    iMatMainController controller;
    @FXML
    private AnchorPane buyDoneAnchor;
    @FXML
    private Button backToStart;
    @FXML
    private Button closeProgram;
    @FXML
    private Button finalizeButton;

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
    private void goBackToSecondCheckout(){
        controller.activateSecondCheckout();
    }

    @FXML
    private void finalizeButtonAction(){
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
