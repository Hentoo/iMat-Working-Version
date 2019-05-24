package iMat.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class iMatSecondCheckout extends AnchorPane {

    iMatMainController controller;

    public iMatSecondCheckout(iMatMainController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatSecondCheckout.fxml"));
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
    private void backToFirst(){
        controller.activateFirstCheckout();
    }

    @FXML
    private void toThirdCheckout(){
        controller.activateThirdCheckout();
    }
}
