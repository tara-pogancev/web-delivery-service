package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.*;

public class CustomerRepository extends GenericRepository<Customer, CustomerRepository> 
{

	@Override
	protected String getFileName() {		
		return "customerData.json";
	}

	@Override
	protected String getKey(Customer e) {
		return e.getId();
	}
	
	public ArrayList<Customer> getAll() {
		Map<String, Customer> map = getMap();
		ArrayList<Customer> list = new ArrayList<>();

		for (Map.Entry<String, Customer> entry : map.entrySet()) {
			if(!entry.getValue().isDeleted())
				list.add(((Customer) entry.getValue()));
		}

		return list;
	}
	
	public Map<String, Customer> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Customer>>() {
		}.getType();

		Map<String, Customer> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}
	
	public void delete(String id) {
		Map<String, Customer> map = getMap();
		map.get(id).setDeleted(true);;
		writeFile(map);
	}
	
	public void ban(String id) {
		Map<String, Customer> map = getMap();
		map.get(id).setBlocked(true);;
		writeFile(map);
	}
	
	public void unban(String id) {
		Map<String, Customer> map = getMap();
		map.get(id).setBlocked(false);;
		writeFile(map);
	}
	
}
