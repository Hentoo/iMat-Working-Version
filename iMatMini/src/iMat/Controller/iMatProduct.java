package iMat.Controller;

import iMat.IMat;
import iMat.Model;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class iMatProduct extends AnchorPane {

    @FXML
    public ImageView imageField;

    public ImageView getFavoriteStar() {
        return favoriteStar;
    }

    @FXML
    private ImageView favoriteStar;
    @FXML
    private Button lesserButton;
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

    @FXML
    AnchorPane backProduct;
    private iMatInfoViewController infoView;

    @FXML
    private AnchorPane infoViewAnchorPane;
    @FXML AnchorPane addedProduct;

    private iMatMainController controller;


    public Product getProduct() {
        return product;
    }

    private Product product;

    int chosenAmount = 1;

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    boolean isFavourite = false;


    List<ShoppingItem> products = new ArrayList<>();


    private Model model = Model.getInstance();

    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    public iMatProduct(Product product, iMatMainController controller){
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
        this.controller = controller;
    }

    @FXML
    private void onInfoClick(){
        controller.activateInfoView(product);
    }

    @FXML
    private void lesserButtonAction(){
        if(chosenAmount > 1){
            nrOfItems.clear();
            chosenAmount--;
            updateAmountBar();
        }

    }

    @FXML
    private void moreButtonAction(){
        nrOfItems.clear();
        chosenAmount++;
        updateAmountBar();
    }

    private void updateAmountBar(){
        nrOfItems.clear();
        nrOfItems.appendText(Integer.toString(chosenAmount));
    }

    @FXML
    private void handleAddAction(){

        controller.shoppingCartArea.getChildren().clear();
        imageField.toBack();
        backProduct.toBack();


        double totalPrice = 0;

        int i = 0;
        ShoppingItem checkCopy = null;

        for (int j = 0; j < IMatDataHandler.getInstance().getShoppingCart().getItems().size(); j++){
            if (IMatDataHandler.getInstance().getShoppingCart().getItems().get(j).getProduct().getName() == product.getName()){
                checkCopy = IMatDataHandler.getInstance().getShoppingCart().getItems().get(j);
                i = j;
            }
        }

        if(i == 0 && checkCopy == null){
            IMatDataHandler.getInstance().getShoppingCart().getItems().add(new ShoppingItem(product, chosenAmount));
        }

        if (checkCopy != null){
            IMatDataHandler.getInstance().getShoppingCart().getItems().add(i, new ShoppingItem(checkCopy.getProduct(), checkCopy.getAmount()+chosenAmount));
            IMatDataHandler.getInstance().getShoppingCart().getItems().remove(i+1);
        }


        for(ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            controller.shoppingCartArea.getChildren().add(new iMatCartItemController(shoppingItem, controller));
        }

        for(ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            totalPrice = totalPrice + (shoppingItem.getProduct().getPrice() * shoppingItem.getAmount());
        }
        controller.totalPriceLabel.setText("Total: " + Integer.toString((int)totalPrice) + " SEK");

        chosenAmount = 1;
        updateAmountBar();

    }


    private boolean listContainingItem(List<ShoppingItem> products, String string){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getProduct().getName().equals(string)){
                return true;
            }
        }
        return false;
    }

    @FXML
    private void addToFavourites() {


        String iconPath;
        iMatProduct pekarProduct = this;

        if (!isFavourite) {
            isFavourite = true;
            iconPath = "imatresources/images/favourites.png";
            controller.favourites.add(pekarProduct);
            favoriteStar.setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));
        }

        else {

            int i = 0;
            for (int j = 0; j < controller.favourites.size(); j++) {
                if (controller.favourites.get(j).product.getName() == this.product.getName()) {
                    i = j;
                }
            }

            controller.favourites.remove(i);
            isFavourite = false;
            iconPath = "imatresources/images/empty_star.png";
            favoriteStar.setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));

        }
    }

}

