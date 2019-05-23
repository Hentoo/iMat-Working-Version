package iMat.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class iMatFirstCheckout extends AnchorPane {


    public iMatFirstCheckout() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatFirstCheckout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
