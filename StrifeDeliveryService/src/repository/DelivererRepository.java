package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Deliverer;

public class DelivererRepository extends GenericRepository<Deliverer, DelivererRepository> {

	@Override
	protected String getFileName() {
		return "delivererData.json";
	}

	@Override
	protected String getKey(Deliverer e) {
		return e.getId();
	}
	
	public ArrayList<Deliverer> getAll() {
		Map<String, Deliverer> map = getMap();
		ArrayList<Deliverer> list = new ArrayList<>();

		for (Map.Entry<String, Deliverer> entry : map.entrySet()) {
			if(!entry.getValue().isDeleted())
				list.add(((Deliverer) entry.getValue()));
		}

		return list;
	}
	
	public Map<String, Deliverer> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Deliverer>>() {
		}.getType();

		Map<String, Deliverer> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}
	
	public void delete(String id) {
		Map<String, Deliverer> map = getMap();
		map.get(id).setDeleted(true);;
		writeFile(map);
	}

}
