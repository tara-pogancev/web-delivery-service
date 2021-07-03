package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Cart;

public class CartRepository extends GenericRepository<Cart, CartRepository>  {

	@Override
	protected String getFileName() {
		return "cartData.json";
	}

	@Override
	protected String getKey(Cart e) {
		return e.getCartOwnerId();
	}

	public ArrayList<Cart> getAll() {
		Map<String, Cart> map = getMap();
		ArrayList<Cart> list = new ArrayList<>();

		for (Map.Entry<String, Cart> entry : map.entrySet()) {
			if(!entry.getValue().isDeleted())
				list.add(((Cart) entry.getValue()));
		}

		return list;
	}
	
	public Map<String, Cart> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Cart>>() {
		}.getType();

		Map<String, Cart> map = gs.fromJson(json, empMapType);

		//System.out.println("Map with: " + map.size());

		return map;

	}
	
}
