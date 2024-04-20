package com.example.client.fx.services.outputText;

import org.json.JSONObject;

import javafx.scene.text.Text;

public class OutputText {
  
  public void printResponse(Text textResponse, JSONObject response){
    String message = response.getString("message");
    int code = response.getInt("code");
    if(code == 400 || code >= 500){textResponse.setStyle("-fx-fill: red;");}
    else if(code == 409){textResponse.setStyle("-fx-fill: yellow;");} 
    else if(code >= 200){textResponse.setStyle("-fx-fill: green;");}
    textResponse.setText(message);
    textResponse.setWrappingWidth(200);
    return;
  }
}