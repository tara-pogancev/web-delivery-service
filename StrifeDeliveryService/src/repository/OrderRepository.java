package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import enumeration.OrderStatus;
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
	
	public Order getById(String id) {
		Order retVal = new Order();
		ArrayList<Order> list = getAll();
		for(Order o : list) {
			if(o.getId().equals(id))
			{
				retVal = o;
			}
		}
		
		return retVal;
	}

	public ArrayList<Order> getAllByCustomer(String id) {
		ArrayList<Order> list = new ArrayList<>();

		for (Order o : getAll()) {
			if (o.getCustomerId().equals(id))
				list.add(o);
		}

		return list;
	}

	public ArrayList<Order> getAllByRestaurant(String restaurantName) {
		ArrayList<Order> list = new ArrayList<>();

		for (Order o : getAll()) {
			if (o.getRestaurant().getName().equals(restaurantName))
				list.add(o);
		}

		return list;
	}

	public ArrayList<Order> getAllAvailable() {
		ArrayList<Order> list = new ArrayList<>();	

		for (Order o : getAll()) {
			if (o.getStatus() == (OrderStatus.AWAITING_DELIVERER))
				list.add(o);
		}
		

		return list;
	}
}
