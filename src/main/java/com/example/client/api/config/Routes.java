package com.example.client.api.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {
  private String serverURL = "https://file-sender-project.nn.r.appspot.com";
  // private String checkURL = "http://localhost:8080/users/check-name";
  // private String writeURL = "http://localhost:8080/files/write-file";
  private String checkURL = "/users/check-name";
  private String writeURL = "/files/write-file";

  public String getCheckURL(){return serverURL + checkURL;}

  public String getWriteURL(){return serverURL + writeURL;}
}