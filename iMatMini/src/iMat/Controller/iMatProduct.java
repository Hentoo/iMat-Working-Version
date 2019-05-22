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

    private iMatMainController controller;


    private Product product;


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
    private void handleAddAction() {


        model.addToShoppingCart(product);


        controller.shoppingCartArea.clear();
        System.out.println("Add " + product.getName());



        for(int i = 0; i < products.size(); i++) {

            /*
            products.add(IMatDataHandler.getInstance().getShoppingCart().getItems().get(i));
            controller.shoppingCartArea.appendText(products.get(i).getProduct().getName() + "\n");

            System.out.println(products.size());
            */

             if(products.isEmpty()){
                products.add(IMatDataHandler.getInstance().getShoppingCart().getItems().get(i));
                System.out.println("hejhej");
            } else if(listContainingItem(products, product.getName())){
                 System.out.println(products.get(i) + "fler");
                products.get(i).setAmount(products.get(i).getAmount() + 1);
                System.out.println(products.get(i).getAmount());
            } else if(!listContainingItem(products, product.getName())){
                 System.out.println(products.get(i).getProduct().getName() + "tillagd");
                 products.add(new ShoppingItem(product));
             }


        }



        for(int i = 0; i < products.size(); i++){
          //  controller.shoppingCartArea.appendText(IMatDataHandler.getInstance().getShoppingCart().getItems().get(i).getProduct().getName() +  " " + (int) IMatDataHandler.getInstance().getShoppingCart().getItems().get(i).getAmount() + " st" + "\n");
            controller.shoppingCartArea.appendText(products.get(i).getProduct().getName() + " " + (int) products.get(i).getAmount() + " st" + "\n");
        }




    }

    private boolean listContainingItem(List<ShoppingItem> products, String string){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getProduct().getName().equals(string)){
                return true;
            }
        }
        return false;
    }


}

