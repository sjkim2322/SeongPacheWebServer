package org.seongjin.SeongPache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Request {
	
	private static Logger logger = LoggerFactory.getLogger(Request.class);
	
	public static final String httpVersion = "HTTP/1.1";
	private Map<String,String> headers;
		

	public Request() {
		this.headers = new HashMap<String,String>();
	}
	public void addHeader(String key,String value) {
//		logger.info("key :"+key + "  value :" + value);
		headers.put(key, value);
	}
	
	public String getHeader(String key) {
		
		return headers.get(key);
	}
	

	
	@Override
	public String toString() {
		return "Request [headers=" + headers + "]";
	}

	private String toString(Collection<?> m ,int i) {
		return "";
	}
	
	
}
