package org.seongjin.SeongPache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
	private int portNumber;
	
	
	
	public HttpServer(int port) {
		this.portNumber = port;
	}
	
	public void run() {
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(this.portNumber);
			logger.info("Initialized");
		} catch (IOException e) {
			logger.error("Failed to create ServerSocket");
		}
	
		
		try {
			logger.info("Waiting Client`s Request");
			clientSocket = serverSocket.accept();
			logger.info("Request accept !");
		} catch (IOException e) {
			logger.error("Failed To Accept Request");
		}
		
	}
}
