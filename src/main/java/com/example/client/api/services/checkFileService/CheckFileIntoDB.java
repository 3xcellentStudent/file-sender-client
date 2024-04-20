package com.example.client.api.services.checkFileService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;
import java.util.Map;

import org.json.JSONObject;

import com.example.client.api.config.Routes;
import com.example.client.api.services.helpers.JSONMethods;
import com.example.client.encrypt.EncryptionUtil;

public class CheckFileIntoDB {
  private EncryptionUtil encryption = new EncryptionUtil();
  private JSONMethods jsonMethods = new JSONMethods();

  private HttpRequest makeRequest(JSONObject reqBody){
    HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create(new Routes().getCheckURL()))
    .timeout(Duration.ofSeconds(60))
    .header("Content-Type", "application/json")
    .POST(BodyPublishers.ofString(reqBody.toString()))
    .build();
    return request;
  }
  
  public String check(String email, String fileName, Map<String, String> address){
    String encrEmail = encryption.encryptString(email);
    String encrFileName = encryption.encryptString(fileName);
    String encrAddress = encryption.encryptString(address.toString());

    JSONObject reqBody = jsonMethods.checkFileJSON(encrEmail, encrFileName, encrAddress);
    HttpClient client = HttpClient.newHttpClient();
    try {
      HttpRequest request = makeRequest(reqBody);
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return response.body();
    } catch (Exception error) {
      System.out.println(error);
      return error.getMessage();
    }
  }  
}