package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("To Do List Manager");
        primaryStage.setScene(new Scene(root, 1024, 512));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void stop() throws Exception{
        try{
            DataManager.getInstance().save();

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void init() throws Exception {
        try {
            DataManager.getInstance().load();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
