package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.*;

public class ProductRepository extends GenericRepository<Product, ProductRepository> {

	@Override
	protected String getFileName() {
		return "productData.json";
	}

	@Override
	protected String getKey(Product e) {
		return e.getId();
	}
	
	public ArrayList<Product> getAll() {
		Map<String, Product> map = getMap();
		ArrayList<Product> list = new ArrayList<>();

		for (Map.Entry<String, Product> entry : map.entrySet()) {
			if(!entry.getValue().isDeleted())
				list.add(((Product) entry.getValue()));
		}

		return list;
	}
	
	public Map<String, Product> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Product>>() {
		}.getType();

		Map<String, Product> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}

}
