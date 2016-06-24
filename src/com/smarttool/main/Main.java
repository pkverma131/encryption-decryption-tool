package com.smarttool.main;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;
//import java.util.regex.Pattern;
import com.smarttool.utils.IOUtils;
import com.smarttool.utils.RC4WithASCII;

public class Main {
	public static final String KEY="linksmart123";
	public static void main(String args[]){
		String filepath =args[0];
		String action=args[1];
		String SECRETE_KEY =args[2];
		if (action.equals("encrypt")){
			actionEncrypt(filepath, SECRETE_KEY);
		}
		else if(action.equals("decrypt")){
			actionDecrypt(filepath, SECRETE_KEY);
		}
		else{
			System.out.println("Unidentified action!");
		}
		
	}
	public static void actionEncrypt(String toEncrypt,String SECRETE_KEY){
		File file =new File(toEncrypt);
		String file_path=file.getParentFile()+File.separator;
		String file_name=FilenameUtils.removeExtension(file.getName());
		String file_ext=FilenameUtils.getExtension(file.getName());		
		String toDecrypt=file_path+file_name+"_cipher."+file_ext;
		RC4WithASCII rc4=new RC4WithASCII();
		try {
			String plainText=IOUtils.readFileString(toEncrypt);			
			String encrypted=rc4.encrypt(plainText, SECRETE_KEY );
			System.out.println("\n************* Output *************\n\n"
						+encrypted);
			IOUtils.writeToFile(toDecrypt, encrypted);
			System.out.println("\n************* Output saved to: "
					+file_path+file_name+"_cipher."+file_ext
					+" *************");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void actionDecrypt(String toDecrypt,String SECRETE_KEY){
		File file =new File(toDecrypt);
		String file_path=file.getParentFile()+File.separator;
		String file_name=FilenameUtils.removeExtension(file.getName());
		String file_ext=FilenameUtils.getExtension(file.getName());
		String recovered=file_path+file_name+"_recovered."+file_ext;
		RC4WithASCII rc4=new RC4WithASCII();
		try {

			String cipherText=IOUtils.readFileString(toDecrypt);
			String recoveredText =rc4.decrypt(cipherText, SECRETE_KEY);
//			System.out.println("\n************* Output *************\n\n"
//						+recoveredText);
			IOUtils.writeToFile(recovered, recoveredText);
			System.out.println("\n************* Output saved to:"
					+file_path+file_name+"_recovered."+file_ext
					+" *************");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void sampleAction(){
		System.out.println("Enter the file path without space without and enter "
		+ "(e.g.- C:/Users/user/Documents/localhost.linksmartdna.com/dep_list.json");
		Scanner scanner=new Scanner(System.in);
		String toEncrypt=scanner.nextLine();
		scanner.close();
		File file =new File(toEncrypt);
		String file_path=file.getParentFile()+File.separator;
		String file_name=FilenameUtils.removeExtension(file.getName());
		String file_ext=FilenameUtils.getExtension(file.getName());
		
		System.out.println("Output written to:"
				+file_path+file_name+"_cipher."+file_ext);
		System.out.println("Output recovered verify result and written to:"
				+file_path+file_name+"_recovered."+file_ext);

		String toDecrypt=file_path+file_name+"_cipher."+file_ext;
		String recovered=file_path+file_name+"_recovered."+file_ext;
		RC4WithASCII rc4=new RC4WithASCII();
		try {
			String plainText=IOUtils.readFileString(toEncrypt);
			//System.out.println(plainText);
			
			String encrypted=rc4.encrypt(plainText, KEY);
			System.out.println("Output:\n"+encrypted);
			IOUtils.writeToFile(toDecrypt, encrypted);
			
			String cipherText=IOUtils.readFileString(toDecrypt);
			//System.out.println(cipherText);
			String recoveredText =rc4.decrypt(cipherText, KEY);
			//System.out.println(recoveredText);
			IOUtils.writeToFile(recovered, recoveredText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
