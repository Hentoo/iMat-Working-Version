package iMat.Controller;

import iMat.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class OrderHistoryProductView {

    ShoppingItem product;

    private iMatMainController controller;

    Model model = Model.getInstance();
    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    @FXML
    private ImageView imageField;
    @FXML
    private TextField nrOfItems;
    @FXML
    private Label productName;
    @FXML
    private Label productPrize;

    public OrderHistoryProductView(ShoppingItem product, iMatMainController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatProduct.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        productName.setText(product.getProduct().getName());
        imageField.setImage(model.getImage(product.getProduct(), kImageWidth, kImageWidth*kImageRatio));
        this.controller = controller;
        nrOfItems.setText(product.getAmount() + "x" + product.getProduct().getPrice());
        productPrize.setText("Totalt: " + product.getAmount()*product.getProduct().getPrice());
    }

}
