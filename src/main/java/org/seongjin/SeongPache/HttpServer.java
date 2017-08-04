package org.seongjin.SeongPache;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {

	private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
	private int portNumber;
	private RequestHandler requestHandler;

	public HttpServer(int portNumber) {
		this.portNumber = portNumber;
		this.requestHandler = new RequestHandler();
	}

	public void run() {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(this.portNumber);
			logger.info("Initialized");
		} catch (IOException e) {
			logger.error("Failed to create ServerSocket");
		}

		try {
			logger.info("Waiting Client`s Request");
			requestHandler.handle(serverSocket.accept());
			logger.info("Request accept !");
		} catch (IOException e) {
			logger.error("Failed To Accept Request");
		}

	}
}
