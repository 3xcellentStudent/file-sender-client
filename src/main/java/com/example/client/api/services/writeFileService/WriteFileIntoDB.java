package com.example.client.api.services.writeFileService;

import java.io.File;
import java.net.HttpURLConnection;

import org.json.JSONObject;

import com.example.client.api.config.Routes;
import com.example.client.api.services.UrlConnection.SenderFile;
import com.example.client.api.services.UrlConnection.URLConnectionClass;
import com.example.client.api.services.helpers.ConnectionStatus;
import com.example.client.api.services.helpers.JSONMethods;


public class WriteFileIntoDB {
  private SenderFile senderFile = new SenderFile();
  private JSONMethods jsonMethods = new JSONMethods();

  public JSONObject write(File file, String docId, String email){
    String writeURL = new Routes().getWriteURL();
    String boundary = Long.toHexString(System.currentTimeMillis());
    ConnectionStatus connectionStatus = new URLConnectionClass().connect(writeURL, boundary);
    HttpURLConnection connection = connectionStatus.getConnection();
    String connectionError = connectionStatus.getConnectionError();
    if(connectionError != null){
      System.err.println(connectionError);
      return jsonMethods.createResponse("output", "Connection failed !", 400);
    }
    else {
      JSONObject response = senderFile.send(connection, boundary, file, docId, email);
      return response;
    }
  }
}