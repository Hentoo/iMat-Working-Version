/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iMat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 *
 * @author oloft
 */
public class IMat extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMat/resources/iMat");
        System.out.println(getClass().getResource("Controller/iMat.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("Controller/iMat.fxml"), bundle);
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);

        stage.setTitle("iMat, the one and only");
        stage.show();
    }


    /*
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Controller/iMat.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Model.getInstance().shutDown();
        }
        }));

    }

    
}
