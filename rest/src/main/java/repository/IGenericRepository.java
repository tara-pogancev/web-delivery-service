package repository;

import java.util.ArrayList;
import java.util.Map;

public interface IGenericRepository <Entity> {

	void create (Entity entity);
	Entity read (String id);
	void update (Entity entity);
	void delete (Entity entity);
	void delete (String id);
	ArrayList<Entity> getAll();
	Map<String, Entity> getMap();
		
}
