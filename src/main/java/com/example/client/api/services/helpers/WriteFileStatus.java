package com.example.client.api.services.helpers;

import org.json.JSONObject;

public class WriteFileStatus {
  private int statusCode;
  private String stringResult;

  public WriteFileStatus(int statusCode, String stringResult){
    this.statusCode = statusCode;
    this.stringResult = stringResult;
  }

  public JSONObject getResponse(){
    return new JSONObject()
    .put("statusCode", statusCode)
    .put("stringResult", stringResult); 
  }
}