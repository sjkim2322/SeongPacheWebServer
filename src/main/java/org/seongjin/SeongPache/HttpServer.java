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
	
	private static Logger logger = LoggerFactory.getLogger(HttpServer.class);
	private int port;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private String line = null;
	private BufferedReader in;
	private PrintWriter out;
	
	public HttpServer(int port) {
		this.port = port;
		try {
			serverSocket = new ServerSocket(this.port);
			logger.info("Initialized");
		} catch (IOException e) {
			logger.error("Failed to create ServerSocket");
		}

		
	}
	
	public void run() {
		System.out.println("Waiting Client`s Request");
		try {
			clientSocket = serverSocket.accept();
			logger.info("Request accept !");
		} catch (IOException e) {
			logger.error("Failed To Accept Request");
		}
		
	}
}
