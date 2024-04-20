package com.example.client.api.services.macService;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;

public class GetNetworkAdderss {
  public Map<String, String> getAddress(){
    try {
      InetAddress ip = InetAddress.getLocalHost();
      
      NetworkInterface network = NetworkInterface.getByInetAddress(ip);
      byte[] mac = network.getHardwareAddress();
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < mac.length; i++) {
        builder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
      }
      
      String ipAddress = ip.getHostAddress();
      String macAddress = builder.toString();
      HashMap<String, String> hashMap = new HashMap<>();
      hashMap.put("ipAddress", ipAddress);
      hashMap.put("macAddress", macAddress);
      return hashMap;
    } catch (Exception error) {
        System.out.println("Get Network adderss error: " + error.getMessage());
        error.printStackTrace();
        return null;
    }
  }
}