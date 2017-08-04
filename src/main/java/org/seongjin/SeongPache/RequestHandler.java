package org.seongjin.SeongPache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler {

	private static Logger logger = LoggerFactory.getLogger(HttpServer.class);

	private Request request;
	private InputStream bodyInput;
	private String requestTarget;
	private InputStream inputStream;
	private BufferedReader rawRequest;

	public RequestHandler() {
		this.request = new Request();
	}

	public Request handle(Socket clientSocket) throws IOException {
		logger.info("Request Handling....");
		request = new Request();
		String line;
		getRawRequest(clientSocket);
		request.addHeader("Method", getMethod());
		try {
			while((line = rawRequest.readLine()) != null) {
				parseRequestLine(line);
			}
			logger.info("Header Start");
		} catch (IOException e) {
			logger.error("Failed to Parsing Request Header");
		}

		return null;
	}

	private void getRawRequest(Socket clientSocket) {
		try {
			rawRequest = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			logger.info("Raw Request Generated");
			return;
		} catch (IOException e) {
			logger.error("Failed to Generate Raw Request");
		}
		rawRequest = null;
	}

	private String getMethod() {
		String method = null;
		try {
			method = rawRequest.readLine();
		} catch (IOException e) {
			logger.error("Invalid Request");
		}
		return method.split("/")[0].trim();
	}

	public boolean parseRequestLine(String line) {
		if ("".equals(line)) {
			logger.info("Header End");
			return false;
		} else {
			String[] parsedLine;
			logger.info(line);
			parsedLine = line.split(":", 2);
			request.addHeader(parsedLine[0], parsedLine[1]);
			return true;

		}
	}
}
