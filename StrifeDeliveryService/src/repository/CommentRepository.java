package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Comment;
import model.Order;

public class CommentRepository extends GenericRepository<Comment, CommentRepository> {

	@Override
	protected String getFileName() {
		return "commentData.json";
	}

	@Override
	protected String getKey(Comment e) {
		return e.getId();
	}
	
	public ArrayList<Comment> getAll() {
		Map<String, Comment> map = getMap();
		ArrayList<Comment> list = new ArrayList<>();

		for (Map.Entry<String, Comment> entry : map.entrySet()) {
			list.add(((Comment) entry.getValue()));
		}

		return list;
	}
	
	public Map<String, Comment> getMap() {

		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Comment>>() {
		}.getType();

		Map<String, Comment> map = gs.fromJson(json, empMapType);

		System.out.println("Map with: " + map.size());

		return map;

	}
	
	public Comment getById(String id) {
		Comment retVal = new Comment();
		for(Comment c:getAll()) {
			if(c.getId().equals(id))
			{
				retVal = c;
			}
		}
		
		return retVal;
	}
	
	public ArrayList<Comment> getAllByCustomer(String id) {
		ArrayList<Comment> list = new ArrayList<>();

		for (Comment c : getAll()) {
			if (c.getAuthor().getId().equals(id))
				list.add(c);
		}

		return list;
	}

	public ArrayList<Comment> getAllByRestaurant(String restaurantName) {
		ArrayList<Comment> list = new ArrayList<>();

		for (Comment c : getAll()) {
			if (c.getRestaurant().getName().equals(restaurantName))
				list.add(c);
		}

		return list;
	}	
	
}
