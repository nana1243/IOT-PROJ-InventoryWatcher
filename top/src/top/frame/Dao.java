package top.frame;

import java.util.ArrayList;



public interface Dao<Id, Model> {

	
	public Model select(Id id);
	
	public ArrayList<Model> selectall();
	
	public ArrayList<Model> selectname(Id id);
	
	public void delete(Id id);

	public void update(Model model);

	public void insert(Model model);



}
