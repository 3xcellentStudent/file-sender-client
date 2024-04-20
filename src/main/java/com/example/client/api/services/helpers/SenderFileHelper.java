package com.example.client.api.services.helpers;

import java.io.PrintWriter;

public class SenderFileHelper {
  public final String fileDisposition = "Content-Disposition: form-data; name=\"file\"";
  public final String emailDisposition = "Content-Disposition: form-data; name=\"email\"";
  public final String fileNameDisposition = "Content-Disposition: form-data; name=\"fileName\"";
  public final String fileLastModified = "Content-Disposition: form-data; name=\"lastModified\"";
  public final String docIdDisposition = "Content-Disposition: form-data; name=\"docId\"";

  public PrintWriter createPartContent(
    PrintWriter writer, String boundary, String disposition, String content
  ){
    writer.println("--" + boundary);
    writer.println(disposition);
    writer.println();
    writer.println(content);
    return writer;
  }
}