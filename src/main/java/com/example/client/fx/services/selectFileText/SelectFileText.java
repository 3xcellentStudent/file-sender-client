package com.example.client.fx.services.selectFileText;

import java.io.File;

import javafx.scene.text.Text;

public class SelectFileText {
  public File printResponse(File selectedFile, Text selectFile){
    if(selectedFile != null && selectedFile.exists()){
      selectFile.setText("Selected file: " + selectedFile.getName());
      selectFile.setStyle("-fx-fill: #58D68D;");
      return selectedFile;
    }
    else {
      selectFile.setText("Popup was closed or file was not selected");
      selectFile.setStyle("-fx-fill: yellow;");
      return null;
    }
  }
}
