package iMat.Controller;


import iMat.IMat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;


import java.net.URL;
import java.util.*;


public class iMatMainController implements Initializable, ShoppingCartListener {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    ListIterator<ShoppingItem> list;
    IMatMyPages mypages = new IMatMyPages(this);
    public List<iMatProduct> favourites = new ArrayList<>();

    private iMatSecondCheckout secondCheckout = new iMatSecondCheckout(this);
    public iMatThirdCheckout thirdCheckout = new iMatThirdCheckout(this);
    public int currentOrderNumber = 1;
    public List<Order> orders = new ArrayList<>();



    private String difficulty;
    @FXML
    private FlowPane productsFlowPane;
    @FXML FlowPane categoryFlowPane;
    @FXML
    public AnchorPane mainAnchorPane;


    @FXML
    private ImageView imageField;
    @FXML
    Text productText;
    @FXML
    private TextField searchField;
    @FXML
    FlowPane shoppingCartArea;
    @FXML AnchorPane mainScreen;
    @FXML
    private Button logoButton;
    @FXML
    private Label backtoStart;
    @FXML AnchorPane fruitCategory;
    @FXML AnchorPane greenCategory;
    @FXML AnchorPane pastaCategory;
    @FXML AnchorPane drinkCategory;
    @FXML FlowPane infoViewPane;
    @FXML AnchorPane infoViewAnchor;
    @FXML Label totalPriceLabel;

    @FXML FlowPane checkoutFlowPane;
    @FXML AnchorPane firstCheckoutAnchor;
    @FXML FlowPane firstCheckoutPane;
    @FXML AnchorPane secondCheckoutAnchor;
    @FXML FlowPane secondCheckoutFlowp;
    @FXML AnchorPane thirdCheckoutAnchor;
    @FXML FlowPane thirdCheckoutFlowPane;
    @FXML AnchorPane startingPage;
    @FXML private AnchorPane myPagesAnchorPane;
    @FXML private FlowPane myPagesFlowPane;

    @FXML private Button categoryButton1;
    @FXML private Button categoryButton2;
    @FXML private Button categoryButton3;
    @FXML private Button categoryButton4;
    @FXML private Button categoryButton5;
    @FXML private Button categoryButton6;
    @FXML private Button categoryButton7;
    @FXML private Button categoryButton8;
    @FXML private Button categoryButton9;



    @FXML private Button pastaButton;
    @FXML private Button podButton;
    @FXML private Button vegetableButton;
    @FXML private Button rootButton;
    @FXML private Button seedButton;
    @FXML private Button exoticButton;
    @FXML private Button citrusButton;
    @FXML private Button berryButton;
    @FXML private Button flourButton;
    @FXML private Button potatoButton;
    @FXML private Button coldDrinks;
    @FXML private Button hotDrinks;

    @FXML private Button checkoutButton;

    private int categoryActive=0;




    @FXML
    Line lineLine;


    IMatMyPages iMatMyPages;


    iMatFirstCheckout firstCheckout;

    private List<String> difficultyList = Arrays.asList("14:00", "14:30", "15:00","15:30","16:00");

    Order currentOrder = new Order();










    Button currentUnderButton;

    Button currentButton;





    List<Product> allItems = IMatDataHandler.getInstance().getProducts();

    List<Product> offers = IMatDataHandler.getInstance().getProducts(ProductCategory.DAIRIES);


    private void keepBackgroundDarkUnder(Button button){

        if(currentUnderButton != null){
            StringBuilder normal = new StringBuilder();
            normal.append("}\n");
            normal.append("Button:hover{\n" +
                    "    -fx-background-color: #7a94a2;\n" +
                    "    -fx-border-bottom: 2px;\n" +
                    "    -fx-border-color: #929292;\n" +
                    "}\n" +
                    "\n" +
                    "Button:pressed{\n" +
                    "    -fx-background-color: GREY;\n" +
                    "    -fx-border-bottom: 2px;\n" +
                    "    -fx-border-color: #929292;\n" +
                    "}");
            currentUnderButton.setStyle(normal.toString());
        }
        button.setStyle("-fx-background-color: #7a94a2");
        currentUnderButton = button;
    }

    private void keepBackgroundDark(Button button){

        if(currentButton != null){
            StringBuilder normal = new StringBuilder();
            normal.append("}\n");
            normal.append("Button:hover{\n" +
                    "    -fx-background-color: DARKGREY;\n" +
                    "    -fx-border-bottom: 2px;\n" +
                    "    -fx-border-color: #929292;\n" +
                    "}\n" +
                    "\n" +
                    "Button:pressed{\n" +
                    "    -fx-background-color: GREY;\n" +
                    "    -fx-border-bottom: 2px;\n" +
                    "    -fx-border-color: #929292;\n" +
                    "}");
            currentButton.setStyle(normal.toString());
        }
        button.setStyle("-fx-background-color: grey");
        currentButton = button;
        if(currentUnderButton != null) {
            currentUnderButton.setStyle("-fx-background-color: lightblue");
        }

    }


    void populateFlowPane(Product product) {
        productText.setText(product.getName());

    }



    public void setDifficulty(String difficulty){
        for (String s: difficultyList) {
            if (difficulty.equals(s)) {
                this.difficulty = difficulty;
                break;
            }
            else {
                this.difficulty = null;
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainScreen.toFront();
        startingPage.toFront();
        lineLine.toFront();
        firstCheckout = new iMatFirstCheckout(this);

        IMatDataHandler.getInstance().getShoppingCart().getItems().clear();

        
        updateShoppingCart();


    }



    private void updateShoppingCart() {

        ShoppingCart shoppingCart = dataHandler.getShoppingCart();

       // shoppingCartArea.setText("Antal varor: " + shoppingCart.getItems().size());
       // costLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));

    }







    @FXML
    private void pressedOnCheckout(){
        categoryActive=0;
        activateFirstCheckout();
    }

    @Override
    public void shoppingCartChanged(CartEvent evt) {
        updateShoppingCart();
        }



    private void initItems(List<Product> products){
        allItems.clear();
        allItems = IMatDataHandler.getInstance().getProducts(ProductCategory.CABBAGE);
        updateProductList(allItems);
    }

    @FXML
    private void pressedOnLogoButton(){
        
        categoryActive=0;
        if(currentButton != null){
            currentButton.setStyle("-fx-background-color: white");
        }
        infoViewPane.toBack();
        mainScreen.toFront();
        startingPage.toFront();
        lineLine.toFront();
        productsFlowPane.getChildren().clear();
    }

    @FXML
    public void updateCurrentCategory(){
        if (categoryActive == 1){
            pressedOnCategory1();
        }
        if (categoryActive == 2){
            pressedOnCategory2();
        }
        if (categoryActive == 3){
            pressedOnCategory3();
        }
        if (categoryActive == 4){
            pressedOnCategory4();
        }
        if (categoryActive == 5){
            pressedOnCategory5();
        }
        if (categoryActive == 6){
            pressedOnCategory6();
        }
        if (categoryActive == 7){
            pressedOnCategory7();
        }
        if (categoryActive == 8){
            pressedOnCategory8();
        }
        if (categoryActive == 9) {
            pressedOnCategory9();
        }
    }

    @FXML
  private void pressedOnCategory1(){
        categoryActive=1;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        fruitCategory.toFront();
        lineLine.toFront();
        keepBackgroundDark(categoryButton1);
       // categoryButton1.setStyle("-fx-background-color: DARKGREY;");


        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.FRUIT));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.BERRY));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.CITRUS_FRUIT));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.EXOTIC_FRUIT));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.MELONS));
  }
    @FXML
    private void pressedOnCategory2(){
        categoryActive=2;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        greenCategory.toFront();
        lineLine.toFront();
        keepBackgroundDark(categoryButton2);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.CABBAGE));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.HERB));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.ROOT_VEGETABLE));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.POD));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.NUTS_AND_SEEDS));

    }
    @FXML
    private void pressedOnCategory3(){
        categoryActive=3;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        lineLine.toFront();
        keepBackgroundDark(categoryButton3);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.BREAD));


    }
    @FXML
    private void pressedOnCategory4(){
        categoryActive=4;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        lineLine.toFront();
        keepBackgroundDark(categoryButton4);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.DAIRIES));

    }
    @FXML
    private void pressedOnCategory5(){
        categoryActive=5;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        lineLine.toFront();
        keepBackgroundDark(categoryButton5);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.MEAT));
    }
    @FXML
    private void pressedOnCategory6(){
        categoryActive=6;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        lineLine.toFront();
        keepBackgroundDark(categoryButton6);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.FISH));

    }
    @FXML
    private void pressedOnCategory7(){
        categoryActive=7;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        pastaCategory.toFront();
        lineLine.toFront();
        keepBackgroundDark(categoryButton7);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.PASTA));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.POTATO_RICE));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.FLOUR_SUGAR_SALT));

    }
    @FXML
    private void pressedOnCategory8(){
        categoryActive=8;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        drinkCategory.toFront();
        lineLine.toFront();
        keepBackgroundDark(categoryButton8);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.HOT_DRINKS));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.COLD_DRINKS));


    }
    @FXML
    private void pressedOnCategory9(){
        categoryActive=9;
        productsFlowPane.getChildren().clear();
        mainScreen.toFront();
        startingPage.toBack();
        lineLine.toFront();
        keepBackgroundDark(categoryButton9);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.SWEET));
    }

    @FXML
    private void pressedOnPasta(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(pastaButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.PASTA));
    }
    @FXML
    private void pressedOnPotato(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(potatoButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.POTATO_RICE));
    }

    @FXML
    private void pressedOnFlour(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(flourButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.FLOUR_SUGAR_SALT));
    }

    @FXML
    private void pressedOnRoot(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(rootButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.ROOT_VEGETABLE));
    }

    @FXML
    private void pressedOnSeed(){
        productsFlowPane.getChildren().clear();
        keepBackgroundDarkUnder(pastaButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.NUTS_AND_SEEDS));
    }
    
    @FXML void pressedOnPod(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(podButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.POD));

    }

    @FXML
    private void pressedOnVegetable(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(vegetableButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.VEGETABLE_FRUIT));
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.HERB));
    }

    @FXML
    private void pressedOnHot(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(hotDrinks);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.HOT_DRINKS));
    }

    @FXML
    private void pressedOnCold(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(coldDrinks);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.COLD_DRINKS));
    }

    @FXML
    private void pressedOnExotic(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(exoticButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.EXOTIC_FRUIT));
    }
    @FXML
    private void pressedOnCitrus(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(citrusButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.CITRUS_FRUIT));
    }

    @FXML
    private void pressedOnBerry(){
        productsFlowPane.getChildren().clear();
        lineLine.toFront();
        keepBackgroundDarkUnder(berryButton);
        updateProductList(IMatDataHandler.getInstance().getProducts(ProductCategory.BERRY));
    }

    @FXML
    public void activateSecondCheckout(){
        secondCheckoutAnchor.toFront();
        secondCheckout.fillPersonalInfoTextFields();
        secondCheckoutFlowp.getChildren().clear();
        secondCheckoutFlowp.getChildren().add(secondCheckout);
    }

    @FXML
    private void gotoFavorites(){
        categoryActive=0;
        mypages.pressedOnCategory4();
        myPagesAnchorPane.toFront();
        myPagesFlowPane.getChildren().clear();
        myPagesFlowPane.getChildren().add(mypages);
        mypages.setFavorites();
        mypages.favoritesAnchorPane.toFront();
        lineLine.toFront();

    }

    @FXML
    private void gotomyPages(){
        categoryActive=0;

        myPagesAnchorPane.toFront();
        myPagesFlowPane.getChildren().clear();
        myPagesFlowPane.getChildren().add(mypages);

        mypages.setStart();
        lineLine.toFront();



    }

    private boolean checkIfInCart(Product product){
        for(ShoppingItem item : IMatDataHandler.getInstance().getShoppingCart().getItems()){
            if (item.getProduct().getName().equals(product.getName())){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfInFavourites(Product product){

        for(iMatProduct favorit : favourites){
            if (favorit.getProduct().getName().equals(product.getName())){
                return true;
            }

        }
        return false;
    }

    private void updateProductList(List<Product> products){
        String iconPath;


        for (Product product : products){
            if(IMatDataHandler.getInstance().isFavorite(product) && checkIfInCart(product)){
                iMatProduct productWithFavouriteAndCart = new iMatProduct(product, this);
                productWithFavouriteAndCart.setFavourite(true);
                iconPath = "imatresources/images/favourites.png";
                productWithFavouriteAndCart.getFavoriteStar().setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));
                productWithFavouriteAndCart.imageField.toBack();
                productWithFavouriteAndCart.backProduct.toBack();
                productsFlowPane.getChildren().add(productWithFavouriteAndCart);
            }
            else if (IMatDataHandler.getInstance().isFavorite(product)){
                iMatProduct productWithFavourite = new iMatProduct(product, this);
                productWithFavourite.setFavourite(true);
                iconPath = "imatresources/images/favourites.png";
                productWithFavourite.getFavoriteStar().setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));
                productsFlowPane.getChildren().add(productWithFavourite);

            }
            else if (checkIfInCart(product)){
                iMatProduct productInCart = new iMatProduct(product, this);
                productInCart.imageField.toBack();
                productInCart.backProduct.toBack();
                productsFlowPane.getChildren().add(productInCart);

            }
            else {
                productsFlowPane.getChildren().add(new iMatProduct(product, this));
            }
        }
    }




  /*  private void updateProductList(List<Product> products) {

         String iconPath;
        int checkIfFound = 0;

        for (Product product : products) {
            if (favourites.isEmpty()){
                productsFlowPane.getChildren().add(new iMatProduct(product,this));   //DETTA HAR PAJAT HELA VÅR KOD
            }
            for (iMatProduct favouriteCheck : favourites){
                if (favouriteCheck.getProduct().getName() == product.getName()){
                    iMatProduct productWithFavourite = new iMatProduct(product, this);
                    productWithFavourite.setFavourite(true);
                    iconPath = "imatresources/images/favourites.png";
                    productWithFavourite.getFavoriteStar().setImage(new Image(getClass().getClassLoader().getResourceAsStream(iconPath)));
                    productsFlowPane.getChildren().add(productWithFavourite);
                    checkIfFound = 1;
                }
            }
            if (checkIfFound == 0){
                productsFlowPane.getChildren().add(new iMatProduct(product, this));
            }

        }



    } */

    private int days;
//   private final iMatModel model = iMatModel.getInstance();
public void setDays(int days) {
    if (days > 0) {
        this.days = days;
    } else {
        this.days = 0;
    }

}




    @FXML
    private void handleSearchAction(ActionEvent event) {
        productsFlowPane.getChildren().clear();
        startingPage.toBack();
        lineLine.toFront();
        List<Product> matches = dataHandler.findProducts(searchField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }
    private void updateCreditCard() {

     //   CreditCard card = model.getCreditCard();

        /*card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());

        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);

        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));

        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));

        card.setVerificationCode(Integer.parseInt(cvcField.getText()));*/

    }


    private void setupAccountPane() {

    }

    private void updateBottomPanel() {
     //   ShoppingCart shoppingCart = model.getShoppingCart();

        //TODO - Fixa metoder
        //setNumberOfItems("Antal varor: " + shoppingCart.getItems().size());
        //setTotalCosttLabel("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));

    }



    public void activateFirstCheckout(){
        firstCheckoutAnchor.toFront();
        firstCheckoutPane.getChildren().clear();
        firstCheckoutPane.getChildren().add(firstCheckout = new iMatFirstCheckout(this));

    }

    private void fillSummaryPane(){

    }

    public void activateThirdCheckout(){
    thirdCheckoutAnchor.toFront();
    thirdCheckout.fillCardInfoTextFields();
    thirdCheckoutFlowPane.getChildren().add(thirdCheckout);
    }

    public void mainToFront(){

        mainScreen.toFront();
        lineLine.toFront();
    }


    public void updateTotalPrice(){
        double totalPrice = 0;

        for (ShoppingItem shoppingItem : IMatDataHandler.getInstance().getShoppingCart().getItems()){
           totalPrice = totalPrice + (shoppingItem.getProduct().getPrice() * shoppingItem.getAmount());
        }

        totalPriceLabel.setText("Total: " + Integer.toString((int) totalPrice) + " SEK");
    }


    public void goToFront() {
        mainAnchorPane.toFront();
    }
}


