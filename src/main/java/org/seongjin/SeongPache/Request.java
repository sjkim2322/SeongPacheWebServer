package org.seongjin.SeongPache;

import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Request {
	
	private static Logger logger = LoggerFactory.getLogger(Request.class);
	
	public static final String HTTPVERSION = "HTTP/1.1";
	private Map<String,String> headers;
	private String requestTarget;
	private String method;
	private Response response;
	private final Socket clientSocket;

	public Request(Socket clientSocket) {
		this.headers = new HashMap<String,String>();
		this.clientSocket = clientSocket;
	}
	public void addHeader(String key,String value) {
//		logger.info("key :"+key + "  value :" + value);
		headers.put(key, value);
	}


	public Socket getClientSocket() {
		return clientSocket;
	}


	public String getHeader(String key) {
		
		return headers.get(key);
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRequestTarget() {
		return requestTarget;
	}
	public void setRequestTarget(String requestTarget) {
		this.requestTarget = requestTarget;
	}
	@Override
	public String toString() {
		return "Request [headers=" + headers + ", requestTarget=" + requestTarget + ", method=" + method + "]";
	}


	
	
	
}
