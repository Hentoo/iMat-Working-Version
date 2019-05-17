package iMat.Controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class iMatProductController extends AnchorPane {

    @FXML
    private ImageView imageField;
    @FXML
    private ImageView favoriteStar;
    @FXML
    private Button lesserButon;
    @FXML
    private Button moreButton;
    @FXML
    private Button infoKnapp;
    @FXML
    private Button addToCartButton;
    @FXML
    private TextField nrOfItems;
    @FXML
    private Text productText;

    private iMatMainController parentController;
    private Product product;

    public iMatProductController(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/iMatProduct.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;

    }
}

