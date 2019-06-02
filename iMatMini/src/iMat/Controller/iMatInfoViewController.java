package iMat.Controller;

import iMat.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class iMatInfoViewController extends AnchorPane {

    @FXML
    private AnchorPane infoViewAnchorPane;
    @FXML
    private ImageView infoViewPicture;
    @FXML
    private Label infoViewProductName;
    @FXML
    private Button closeInfoButton;
    @FXML
    private Button lessButton;
    @FXML
    private Button moreButton;
    @FXML
    private TextField amountField;
    @FXML
    private Button addToCart;
    @FXML private Button favoritesInfoView;
    @FXML private ImageView starImage;

    int chosenAmount = 1;

    boolean isFavourite;
    String iconPath;


    private Product product;

    private Model model = Model.getInstance();

    private iMatMainController controller;
    iMatProduct pekarProduct;


    public iMatInfoViewController(Product product, iMatMainController controller, iMatProduct iMatProduct){
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
        this.controller = controller;
        updateAmountBar();
        this.isFavourite = isFavourite;
        this.pekarProduct = iMatProduct;

        if(iMatProduct.isFavourite){
            iconPath = "imatresources/images/favourites.png";
            starImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));
        }
        else{
            iconPath = "imatresources/images/empty_star.png";
            starImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));
        }




    }

    @FXML
    private void closeInfoView(){
        controller.mainToFront();
    }

    @FXML
    private void lesserButtonAction(){
        if(chosenAmount > 1){
            amountField.clear();
            chosenAmount--;
            updateAmountBar();
        }

    }

    @FXML
    private void moreButtonAction(){
        amountField.clear();
        chosenAmount++;
        updateAmountBar();
    }

    private void updateAmountBar(){
        amountField.clear();
        amountField.appendText(Integer.toString(chosenAmount));
    }


    @FXML
    private void setAddToCartAction() {

       controller.shoppingCartArea.getChildren().clear();
        double totalPrice = 0;

        int i = 0;
        ShoppingItem checkCopy = null;

        for (int j = 0; j < IMatDataHandler.getInstance().getShoppingCart().getItems().size(); j++) {
            if (IMatDataHandler.getInstance().getShoppingCart().getItems().get(j).getProduct().getName() == product.getName()) {
                checkCopy = IMatDataHandler.getInstance().getShoppingCart().getItems().get(j);
                i = j;
            }
        }

        if (i == 0 && checkCopy == null) {
            IMatDataHandler.getInstance().getShoppingCart().getItems().add(new ShoppingItem(product, chosenAmount));
        }

        if (checkCopy != null) {
            IMatDataHandler.getInstance().getShoppingCart().getItems().add(new ShoppingItem(checkCopy.getProduct(), checkCopy.getAmount() + chosenAmount));
            IMatDataHandler.getInstance().getShoppingCart().getItems().remove(i);
        }


        for (ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()) {
            controller.shoppingCartArea.getChildren().add(new iMatCartItemController(shoppingItem, controller));
        }

        for (ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()) {
            totalPrice = totalPrice + (shoppingItem.getProduct().getPrice() * shoppingItem.getAmount());
        }
        controller.totalPriceLabel.setText("TOTALPRIS: " + Integer.toString((int) totalPrice) + " SEK");

        chosenAmount = 1;
        updateAmountBar();
        controller.mainToFront();

    }

    @FXML
    private void addToFavourites() {

        pekarProduct.addToFavourites();

        if(pekarProduct.isFavourite){
            iconPath = "imatresources/images/favourites.png";
            starImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));
        }
        else{
            iconPath = "imatresources/images/empty_star.png";
            starImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));
        }
    }


}
