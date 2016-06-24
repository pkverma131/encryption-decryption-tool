package com.smarttool.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class IOUtils {
	public static void writeToFile(String filepath,String content){
		File file =new File(filepath);
		try (FileOutputStream fop = new FileOutputStream(file)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String readFileString(String filepath) throws IOException{
		Scanner scanner=new Scanner(new File(filepath));
		String plainText=scanner.useDelimiter("\\Z").next();
		scanner.close();
		return plainText;
	}

}
