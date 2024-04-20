package com.example.client.api.services.helpers;

import org.json.JSONObject;

public class JSONMethods {

  public JSONObject createResponse(String type, String message, int code){
    return new JSONObject()
    .put("type", type)
    .put("message", message)
    .put("code", code);
  }

  public JSONObject checkFileJSON(
    String encyptedEmail, String encyptedFileName, String encrAddress
  ){
    return new JSONObject()
    .put("email", encyptedEmail)
    .put("fileName", encyptedFileName)
    .put("address", encrAddress);
  }
}