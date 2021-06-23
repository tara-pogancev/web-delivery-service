package model;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class IdGenerator {

	private static IdGenerator instance = new IdGenerator();
	
	private String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static IdGenerator getInstance() {
		return instance;
	}
	
	public String generateRandomKey(int numOfChars) {
		
		String key = "";
		
		int len = validChars.length();
		
		 for(int i =0; i<numOfChars; i++){
		      int randomInteger = ThreadLocalRandom.current().nextInt(0, len);
		      key += validChars.charAt(randomInteger);
		 }
		
		return key;		
		
	}	

	public String getValidChars() {
		return validChars;
	}

	public void setValidChars(String validChars) {
		this.validChars = validChars;
	}
	
	public boolean checkIfValidKey(Set<String> keys, String key) {		
		return (!keys.contains(key));		
	}
	
	public String generateId(Set<String> keys, int numOfChars) {
		
		String key = "";
		
		do {			
			key = generateRandomKey(numOfChars);			
		} while (!checkIfValidKey(keys, key));
		
		return key;
		
	}
	
	
}
