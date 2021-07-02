package repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public abstract class GenericRepository<Entity, StorageType extends GenericRepository<Entity, StorageType>>
		implements IGenericRepository<Entity> {
	// Abstraktna klasa sa CRUD operacijama, koristi mapu

	protected abstract String getFileName();

	protected abstract String getKey(Entity e);

	protected Gson gs = new Gson();

	@SuppressWarnings("unused")
	private String basePath;

	public void setBasePath(String path) {
		this.basePath = path;
	}

	protected String getPath() {
		// return this.basePath + getFileName();
		
		//return "C:\\Users\\Tara\\Desktop\\FTN\\Veb programiranje\\&PROJEKAT\\web-delivery-service\\StrifeDeliveryService\\src\\data\\"+ getFileName();
		
		return "C:\\Users\\filip\\Documents\\GitHub\\web-delivery-service\\StrifeDeliveryService\\src\\data\\" + getFileName();

	}

	protected void writeFile(Map<String, Entity> entities) {
		String jsonStr = gs.toJson(entities);
		//System.out.println(jsonStr);

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(getPath());
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

	public void create(Entity entity) {
		Map<String, Entity> map = getMap();
		map.put(getKey(entity), entity);
		writeFile(map);
	}

	public Entity read(String id) {
		Map<String, Entity> map = getMap();
		return map.get(id);
	}

	public void update(Entity entity) {
		Map<String, Entity> map = getMap();
		map.put(getKey(entity), entity);
		writeFile(map);
	}

	public void delete(Entity entity) {
		Map<String, Entity> map = getMap();
		map.remove(getKey(entity));
		writeFile(map);
	}

	public void delete(String id) {
		Map<String, Entity> map = getMap();
		map.remove(id);
		writeFile(map);
	}

	public ArrayList<Entity> getAll() {
		Map<String, Entity> map = getMap();
		ArrayList<Entity> list = new ArrayList<>();

		for (Map.Entry<String, Entity> entry : map.entrySet()) {
			list.add(((Entity) entry.getValue()));
		}

		return list;
	}

	public Map<String, Entity> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Entity>>() {
		}.getType();

		Map<String, Entity> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}

	public Set<String> getKeySet() {
		return getMap().keySet();
	}

}