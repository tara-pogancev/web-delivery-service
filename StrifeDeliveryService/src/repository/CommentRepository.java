package repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import model.Comment;

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

	
	
}
