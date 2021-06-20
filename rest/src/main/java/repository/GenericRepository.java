package repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class GenericRepository<Entity, StorageType extends GenericRepository<Entity, StorageType>>
	implements IGenericRepository<Entity>
{
	//Abstraktna klasa sa CRUD operacijama
	
	protected abstract String getFileName();	
	protected abstract String getKey(Entity e);
	
	private String getPath() {
		return "C:\\Users\\Tara\\Desktop\\FTN\\Veb programiranje\\&PROJEKAT\\web-delivery-service\\rest\\data\\" + getFileName();
	}

    private void writeFile(Map<String, Entity> entities)
    {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try {
    		File file = new File(getPath());
    		
    		//String path = file.getPath();
            //System.out.println(path);		
    		
			mapper.writeValue(file, entities);            
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

	public void create (Entity entity) {		
		Map<String, Entity> map = getMap();		
		map.put(getKey(entity), entity);
		writeFile(map);
	}
	
	public Entity read (String id) {
		Map<String, Entity> map = getMap();		
		return map.get(id);		
	}
	
	public void update (Entity entity) {
		Map<String, Entity> map = getMap();		
		map.put(getKey(entity), entity);
		writeFile(map);
	}
	
	public void delete (Entity entity) {
		Map<String, Entity> map = getMap();		
		map.remove(getKey(entity));		
		writeFile(map);
		
	}
	
	public void delete (String id) {
		Map<String, Entity> map = getMap();		
		map.remove(id);		
		writeFile(map);
	}
	
	public ArrayList<Entity> getAll() {
		return (ArrayList<Entity>) getMap().values();
	}
	
	public Map<String, Entity> getMap() {
		ObjectMapper mapper = new ObjectMapper();
		
		File file = new File(getPath());
		
		Map<String, Entity> map;
		try {
			map = mapper.readValue(file, new TypeReference<Map<String, Entity>>(){});
			
			System.out.println("Mapa uspesno procitana sa : " + map.size() + " entiteta.");
			
			return map;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
}
