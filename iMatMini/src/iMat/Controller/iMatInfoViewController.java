package iMat.Controller;

import iMat.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class iMatInfoViewController extends AnchorPane {

    @FXML
    private AnchorPane infoViewAnchorPane;
    @FXML
    private ImageView infoViewPicture;
    @FXML
    private Label infoViewProductName;

    private Product product;

    private Model model = Model.getInstance();


    public iMatInfoViewController(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatInfoView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.infoViewPicture.setImage(model.getImage(product));
        this.infoViewProductName.setText(product.getName());



    }

}
