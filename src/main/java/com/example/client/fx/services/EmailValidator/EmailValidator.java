package com.example.client.fx.services.EmailValidator;

public class EmailValidator {
  public String validation(String email){
    if(email.length() >= 8 && email.contains("@") && email.contains(".")){return email;}
    else {return null;}
  }
}