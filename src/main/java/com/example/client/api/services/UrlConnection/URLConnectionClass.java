package com.example.client.api.services.UrlConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.example.client.api.services.helpers.ConnectionStatus;

public class URLConnectionClass {
  
  public ConnectionStatus connect(String urlString, String boundary){
    try {
      URL url = new URI(urlString).toURL();
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setReadTimeout(10000);
      connection.setConnectTimeout(30000);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
      return fulfilled(connection, null);
    } catch(SocketTimeoutException error){
      return error(error, "Timeout reading file !");
    } catch(MalformedURLException error){
      return error(error, "Wrong URL address !");
    } catch(URISyntaxException error){
      return error(error, "Wrong URI adress !");
    } catch(ProtocolException error){
      return error(error, "Connection error or wrong protocol, please check your internet connection or try later !");
    } catch(IOException error){
      return error(error, "Server connection error, please check your internet connection or try later!");
    }
  }

  private ConnectionStatus fulfilled(HttpURLConnection connection, String error){
    System.out.println("connection has been established...");
    return new ConnectionStatus(connection, null);
  }

  private ConnectionStatus error(Exception error, String errorMessage){
    System.err.println(error.getMessage());
    error.printStackTrace();
    return new ConnectionStatus(null, errorMessage);
  }
}