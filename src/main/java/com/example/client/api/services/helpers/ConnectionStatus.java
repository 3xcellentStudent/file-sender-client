package com.example.client.api.services.helpers;

import java.net.HttpURLConnection;

public class ConnectionStatus {

  private HttpURLConnection connection;
  private String error;

  public ConnectionStatus(HttpURLConnection connection, String error){
    this.connection = connection;
    this.error = error;
  }

  public HttpURLConnection getConnection(){return connection;}

  public String getConnectionError(){return error;}
}