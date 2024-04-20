package com.example.client.fx;

import com.example.client.fx.controller.ControllerFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
  private ControllerFX controller = new ControllerFX();

  @Override
  public void start(Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("/templates/main.fxml"));
    Scene scene = new Scene(root, 510, 350);
    scene.getStylesheets().add(getClass().getResource("/static/style.css").toExternalForm());
    primaryStage.setTitle("File Sender");
    primaryStage.setScene(scene);
    primaryStage.show();
    controller.setPrimaryStage(primaryStage);
  }
}
