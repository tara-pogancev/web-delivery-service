package repository;

import java.util.ArrayList;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import model.Customer;

public class CustomerRepo {

	public String fileStorageLocation;
	public Gson gs = new Gson();
	public Type classType;

	public Map<String, Customer> getAll() {
		
		String json = "";
		try {
			json = new String (Files.readAllBytes(Paths.get(fileStorageLocation)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Customer>>() {}.getType();

		Map<String, Customer> map = gs.fromJson(json, empMapType);
		
		System.out.println("Map with: " + map.size());
		

		
	    return map;		
		
	}
	
	public ArrayList<Customer> getList() {
		
		Map<String, Customer> map = getAll();
		ArrayList<Customer> list = new ArrayList<>();
				
	    for (Map.Entry<String, Customer> entry : map.entrySet()) {
	        list.add(((Customer)entry.getValue()));
	    }
	    
	    for (Customer c : list) {
	        System.out.println(c.getName() + " " + c.getLastName());
	    }
	    
	    return list;
		
	}
	
	public void create(Customer c) {
		
		Map<String, Customer> map = getAll();
		
		map.put(c.getId(), c);
		
		String jsonStr = gs.toJson(map);
		System.out.println(jsonStr);
	    
	    FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileStorageLocation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    byte[] strToBytes = jsonStr.getBytes();
	    try {
			outputStream.write(strToBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	
	

}
