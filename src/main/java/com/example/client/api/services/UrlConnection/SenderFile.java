package com.example.client.api.services.UrlConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import org.json.JSONObject;

import com.example.client.api.services.helpers.JSONMethods;
import com.example.client.api.services.helpers.SenderFileHelper;
import com.example.client.encrypt.EncryptionUtil;

public class SenderFile {
  private EncryptionUtil encryptionUtil = new EncryptionUtil();
  private SenderFileHelper helper = new SenderFileHelper();
  private JSONMethods jsonMethods = new JSONMethods();
  
  public JSONObject send(
    HttpURLConnection connection, String boundary, File file, String docId, String email
  ){
    String encrFile = encryptionUtil.encryptFile(file);
    String encrEmail = encryptionUtil.encryptString(email);
    String encrFileName = encryptionUtil.encryptString(file.getName());
    String encrLastModified = encryptionUtil.encryptString(Long.toString(file.lastModified()));
    try {
      connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

      OutputStream outputStream = connection.getOutputStream();
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

      helper.createPartContent(writer, boundary, helper.fileDisposition, encrFile);
      helper.createPartContent(writer, boundary, helper.emailDisposition, encrEmail);
      helper.createPartContent(writer, boundary, helper.fileNameDisposition, encrFileName);
      helper.createPartContent(writer, boundary, helper.fileLastModified, encrLastModified);
      helper.createPartContent(writer, boundary, helper.docIdDisposition, docId);

      writer.println("--" + boundary + "--");
      writer.close();

      InputStream resInputStream = connection.getInputStream();
      BufferedReader resReader = new BufferedReader(new InputStreamReader(resInputStream));
      String resLine;
      StringBuilder response = new StringBuilder();
      while((resLine = resReader.readLine()) != null) response.append(resLine);
      resReader.close();
      connection.disconnect();
      System.out.println("The connection was interrupted...");
      
      return new JSONObject(response.toString());
    } catch (IOException error) {
      System.err.println(error.getMessage());
      error.printStackTrace();
      return jsonMethods.createResponse(
        "output", "Somecting went wrong while connectiong to server !", 400
      );
    }
  }
}