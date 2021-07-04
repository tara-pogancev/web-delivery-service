package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.*;

public class OrderRepository extends GenericRepository<Order, OrderRepository> {

	@Override
	protected String getFileName() {
		return "orderData.json";
	}

	@Override
	protected String getKey(Order e) {
		return e.getId();
	}

	public ArrayList<Order> getAll() {
		Map<String, Order> map = getMap();
		ArrayList<Order> list = new ArrayList<>();

		for (Map.Entry<String, Order> entry : map.entrySet()) {
			list.add(((Order) entry.getValue()));
		}

		return list;
	}

	public Map<String, Order> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Order>>() {
		}.getType();

		Map<String, Order> map = gs.fromJson(json, empMapType);

		// System.out.println("Map with: " + map.size());

		return map;

	}
	
	public ArrayList<Order> getAllByCustomer(String id) {
		ArrayList<Order> list = new ArrayList<>();

		for (Order o : getAll()) {
			if (o.getCustomerId().equals(id))
				list.add(o);
		}

		return list;
	}

}
