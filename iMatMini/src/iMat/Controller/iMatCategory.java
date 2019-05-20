package iMat.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.Locale;

public class iMatCategory {

    @FXML
    ImageView categoryPicture;
    @FXML
    Label categoryLabel;


    ProductCategory productCategory;

    public iMatCategory(ProductCategory productCategory) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatCategory.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.productCategory = productCategory;
        categoryLabel.setText(productCategory.name());

    }
}
