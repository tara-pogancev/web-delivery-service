package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.*;

public class RestaurantRepository extends GenericRepository<Restaurant, RestaurantRepository> {

	@Override
	protected String getFileName() {
		return "restaurantData.json";
	}

	@Override
	protected String getKey(Restaurant e) {
		return e.getName();
	}
	

	public ArrayList<Restaurant> getAll() {
		Map<String, Restaurant> map = getMap();
		ArrayList<Restaurant> list = new ArrayList<>();

		for (Map.Entry<String, Restaurant> entry : map.entrySet()) {
			list.add(((Restaurant) entry.getValue()));
		}

		return list;
	}
	
	public Map<String, Restaurant> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Restaurant>>() {
		}.getType();

		Map<String, Restaurant> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}

}
