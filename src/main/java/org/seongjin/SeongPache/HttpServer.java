package org.seongjin.SeongPache;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {

	private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
	private int portNumber;
	private ServerSocket serverSocket = null;

	public HttpServer(int portNumber) {
		this.portNumber = portNumber;
		try {
			serverSocket = new ServerSocket(this.portNumber);
			logger.info("Initialized");
		} catch (IOException e) {
			logger.error("Failed to create ServerSocket");
		}
	}

	public void run() {
		try {
			while(true) {
				logger.info("Waiting Client`s Request");
				HttpClientThread httpClientThread = new HttpClientThread(serverSocket.accept());
				httpClientThread.run();
			}
		} catch (IOException e) {
			logger.error("Failed To Handling Request");
		}

	}
}
