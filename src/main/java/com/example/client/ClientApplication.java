package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.client.fx.MainApp;

import javafx.application.Application;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
		Thread javafxThread = new Thread(() -> {
			Application.launch(MainApp.class);
		});
		javafxThread.start();
	}

}
