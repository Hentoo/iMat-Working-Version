package iMat.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;


import java.awt.event.ActionEvent;
import java.io.IOException;

public class iMatHistory extends AnchorPane {

    iMatMainController controller;
    Order order;

    @FXML private Label orderNumberLabel;
    @FXML private Label dateLabel;
    @FXML private FlowPane itemsFlowPane;


    public iMatHistory(Order order, iMatMainController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatHistory.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = controller;
        this.order = order;
        orderNumberLabel.setText(Integer.toString(order.getOrderNumber()));
        dateLabel.setText(order.getDate().toString());
        fillHistory();
        itemsFlowPane.toFront();

    }

    private void fillHistory(){
        itemsFlowPane.getChildren().clear();
        for(ShoppingItem shoppingItem : order.getItems()){
            itemsFlowPane.getChildren().add(new OrderHistoryProductView(shoppingItem, controller));
        }

    }




}
