package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Admin;

public class AdminRepository extends GenericRepository<Admin, AdminRepository>  {

	@Override
	protected String getFileName() {
		return "adminData.json";
	}

	@Override
	protected String getKey(Admin e) {
		return e.getId();
	}
	
	public ArrayList<Admin> getAll() {
		Map<String, Admin> map = getMap();
		ArrayList<Admin> list = new ArrayList<>();

		for (Map.Entry<String, Admin> entry : map.entrySet()) {
			list.add(((Admin) entry.getValue()));
		}

		return list;
	}
	
	public Map<String, Admin> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Admin>>() {
		}.getType();

		Map<String, Admin> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}

}
