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
import java.util.ListIterator;

public class iMatProduct extends AnchorPane {

    @FXML
    private ImageView imageField;
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

    private iMatInfoViewController infoView;

    @FXML
    private AnchorPane infoViewAnchorPane;

    private iMatMainController controller;


    private Product product;

    int chosenAmount = 1;


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

        controller.shoppingCartArea.clear();
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
            IMatDataHandler.getInstance().getShoppingCart().getItems().add(new ShoppingItem(checkCopy.getProduct(), checkCopy.getAmount()+chosenAmount));
            IMatDataHandler.getInstance().getShoppingCart().getItems().remove(i);
        }


        for(ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            controller.shoppingCartArea.appendText(shoppingItem.getProduct().getName() + "           " + (int)shoppingItem.getAmount() + " st." + "\n");
        }

        for(ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            totalPrice = totalPrice + (shoppingItem.getProduct().getPrice() * shoppingItem.getAmount());
        }
        controller.totalPriceLabel.setText("TOTALPRIS: " + Integer.toString((int)totalPrice) + " SEK");

        chosenAmount = 1;
        updateAmountBar();


      /*  for (ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            index++;
            if (shoppingItem.getProduct().getName().equals(product.getName())){
                i++;
                checkCopy = shoppingItem;
            }

        }

        if (i == 0){
            IMatDataHandler.getInstance().getShoppingCart().getItems().add(new ShoppingItem(product, 1));
        }

        if (i !=0){
            IMatDataHandler.getInstance().getShoppingCart().getItems().add(new ShoppingItem(product, checkCopy.getAmount() + 1));
            IMatDataHandler.getInstance().getShoppingCart().getItems().remove(index-1);

        }

        for(ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            controller.shoppingCartArea.appendText(shoppingItem.getProduct().getName() + " " + shoppingItem.getAmount() + "\n");
        }

       */



    /*    controller.list = IMatDataHandler.getInstance().getShoppingCart().getItems().listIterator();
        int i = 0;
        ShoppingItem checkCopy = new ShoppingItem(product, 0);


        while (controller.list.hasNext()){
            ShoppingItem check = controller.list.next();
            if (check.getProduct().getName() == product.getName()){
                checkCopy = check;
                controller.list.remove();
                i++;
            }

        }

        if (i == 0){
            controller.list.add(new ShoppingItem(product, 1));
            System.out.println("item addade i är 0");
        }
        if (i == 1){
            controller.list.add(new ShoppingItem(product, checkCopy.getAmount() + 1));
            System.out.println("item addade i är 1");
        }

       /* IMatDataHandler.getInstance().getShoppingCart().getItems().clear();

        while(controller.list.hasNext()){
            IMatDataHandler.getInstance().getShoppingCart().getItems().add(controller.list.next());
        }*/


     /*  while(controller.list.hasNext()){
           System.out.println(controller.list.next().getProduct().getName());
        }

        while (controller.list.hasNext()){
            controller.shoppingCartArea.appendText(controller.list.next().getProduct().getName() + " " + (int) controller.list.next().getAmount() + " \n");
        } */

    }

   /* @FXML
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

     /*        if(products.isEmpty()){
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




    } */

    private boolean listContainingItem(List<ShoppingItem> products, String string){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getProduct().getName().equals(string)){
                return true;
            }
        }
        return false;
    }


}

