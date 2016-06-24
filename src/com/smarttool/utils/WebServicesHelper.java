package com.smarttool.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class WebServicesHelper {
	public HttpClient httpclient;
	
	public WebServicesHelper() {
		HttpParams http_params = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(http_params, 50000);
		httpclient=new DefaultHttpClient(http_params);
	}
	private String httpRespose(HttpResponse response) throws HttpResponseException, IOException, NullPointerException {
		HttpEntity entity = response.getEntity();
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			final InputStream in = entity.getContent();
			BufferedReader bin = new BufferedReader(new InputStreamReader(in), 8192);
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = bin.readLine()) != null) {
				sb.append(line);
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("Exception during response parsing");
				}
			}
			return sb.toString();
		} else {
			File responseOutput = new File("/sdcard/Linksmart/responseOutput");
			OutputStream responseOutputStream = new FileOutputStream(responseOutput);
			entity.writeTo(responseOutputStream);
			entity.consumeContent();
			//For us, a status code different than 200 is an exception:
			HttpResponseException e = new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
			throw e;
		}
	}
	private byte[] httpResponse2Byte(HttpResponse response) throws IllegalStateException, IOException{
		HttpEntity entity = response.getEntity();	
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			InputStream ins = entity.getContent();
			return IOUtils.toByteArray(ins);
		}
		else{
			return null;
		}
	}
	public String fetchJsonFromUrl(String url){
		HttpPost post = new HttpPost(url);
		String strResponse=null;
	    try {
			HttpResponse response = httpclient.execute(post);
			strResponse= httpRespose(response);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        strResponse=null;
	    }
	    return strResponse;
	}
	public byte[] fetchBytesFromUrl(String url){
		HttpPost post = new HttpPost(url);
		byte[] byteResponse=null;
	    try {
			HttpResponse response = httpclient.execute(post);
			byteResponse= httpResponse2Byte(response);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        byteResponse=null;
	    }
	    return byteResponse;
	}
}