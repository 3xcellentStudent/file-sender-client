package com.example.client.fx.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.example.client.api.controller.Controller;
import com.example.client.fx.services.EmailValidator.EmailValidator;
import com.example.client.fx.services.outputText.OutputText;
import com.example.client.fx.services.selectFileText.SelectFileText;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerFX implements Initializable {
  private Stage primaryStage;
  private File file;
  private OutputText outputText = new OutputText();
  private SelectFileText selectFileText = new SelectFileText();
  private EmailValidator emailValidator = new EmailValidator();
  
  @FXML
  private TextField textField1;
  @FXML
  private Text selectFile;
  @FXML
  private Text textResponse;
  @FXML
  private Text emailError;
  @FXML
  private Text fileIsEmpty;

  @Override
  public void initialize(URL location, ResourceBundle resources){}

  public void setPrimaryStage(Stage primaryStage){
    this.primaryStage = primaryStage;
  }

  @FXML
  public void print(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File");
    File selectedFile = fileChooser.showOpenDialog(primaryStage);
    file = selectFileText.printResponse(selectedFile, selectFile);
  }

  public void submit(){
    String validatedEmail = emailValidator.validation(textField1.getText());

    if(validatedEmail == null){
      emailError.setText("You have entered an incorrect email !");
    } else if(file == null){
      fileIsEmpty.setText("You haven't selected a file !");
    } else {
      JSONObject response = new Controller().sendData(file, validatedEmail);
      outputText.printResponse(textResponse, response);
      emailError.setText("");
      fileIsEmpty.setText("");
    }
  }
}