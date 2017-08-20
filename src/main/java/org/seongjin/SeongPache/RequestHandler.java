package org.seongjin.SeongPache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler {

	private static Logger logger = LoggerFactory.getLogger(RequestHandler.class);
	private Request request;
	private BufferedReader rawRequest;

	public Request handle(Socket clientSocket) throws IOException {
		logger.info("Request accept !");
		logger.info("Request Handling....");
		request = new Request(clientSocket);
		String line;
		getRawRequest(clientSocket);
		if(!handlingGeneral().equals(Request.HTTPVERSION)) {
			logger.error("This Protocol is not HTTP/1.1");
			return null;
		}
		try {
			logger.info("Header Start");
			while((line = rawRequest.readLine()) != null) {
				if(!parseRequestLine(line)) {
					break;
				}
			}
			logger.info("Header End");
			logger.info("Request Body Handling..");
			logger.info("Request Body Handling Complete!");
			logger.info("Initialize Response");
			request.setResponse(new ResponseHandler().handle(request));
			logger.info(request.toString());
		} catch (IOException e) {
			logger.error("Failed to Parsing Request Header");
		}

		return request;
	}

	private void getRawRequest(Socket clientSocket) {
		try {
			rawRequest = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			logger.info("Raw Request Generated");
			logger.debug(String.valueOf(rawRequest));
			clientSocket.getInputStream();
			return;
		} catch (IOException e) {
			logger.error("Failed to Generate Raw Request");
		}
		rawRequest = null;
	}

	private String handlingGeneral() {
		logger.debug("handlingGeneral");
		String method = null;
		try {
			method = rawRequest.readLine();
		} catch (IOException e) {
			logger.error("Invalid Request");
		}
		if(method == null) {
			return "ERROR";
		}
		logger.debug(method);
		String[] general = method.split(" ");
		request.setMethod(general[0]);
		request.setRequestTarget(general[1]);
		return general[2];
	}

	private boolean parseRequestLine(String line) {
		if ("".equals(line)) {
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
