package iMat.Controller;

import iMat.IMat;
import iMat.Model;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class iMatProduct extends AnchorPane {

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
    private Label productName;
    @FXML
    private Label productPrize;

    private iMatInfoViewController infoView;

    @FXML
    private AnchorPane infoViewAnchorPane;


    private Product product;

    private Model model = Model.getInstance();

    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    public iMatProduct(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatProduct.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        productName.setText(product.getName());
        productPrize.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        imageField.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
    }
    @FXML
    private void createInfoView(){
     //   infoView = new iMatInfoViewController(product);
        infoViewAnchorPane.toFront();

    }
}

