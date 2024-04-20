package com.example.client.api.controller;

import java.io.File;
import java.util.Map;

import org.json.JSONObject;

import com.example.client.api.services.checkFileService.CheckFileIntoDB;
import com.example.client.api.services.macService.GetNetworkAdderss;
import com.example.client.api.services.writeFileService.WriteFileIntoDB;

public class Controller {
  private WriteFileIntoDB writeFile = new WriteFileIntoDB();
  private CheckFileIntoDB checkFile = new CheckFileIntoDB();
  
  public JSONObject sendData(File file, String email){
    String fileName = file.getName();
    
    Map<String, String> address = new GetNetworkAdderss().getAddress();
    
    String isFoundRes = checkFile.check(email, fileName, address);
    
    System.out.println("isFoundResisFoundResisFoundResisFoundResisFoundRes" + isFoundRes);
    JSONObject jsonIsFoundRes = new JSONObject(isFoundRes);
    Object code = jsonIsFoundRes.get("code");
    System.out.println("-------------------------------------------------------------");
    System.out.println(jsonIsFoundRes);
    System.out.println("-------------------------------------------------------------");
    
    if(code.equals(200) || code.equals(404)){
      String docIdRes = jsonIsFoundRes.getJSONObject("message").getString("docId");
      String emailRes = jsonIsFoundRes.getJSONObject("message").getString("email");
      JSONObject response = writeFile.write(file, docIdRes, emailRes);
      return response;
    }
    else return jsonIsFoundRes;
  }
}