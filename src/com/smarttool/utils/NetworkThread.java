package com.smarttool.utils;

public class NetworkThread implements Runnable {
	String url;
	public static String json;
	public NetworkThread(String url) {
		this.url=url;
	}
    @Override
    public void run() {
    	WebServicesHelper webserviceshelper=new WebServicesHelper();
    	json=webserviceshelper.fetchJsonFromUrl(url);
     }
}