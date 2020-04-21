package top.frame;

import java.util.ArrayList;



public interface Dao<Id, Model> {

	
	public Model select(Id id);
	
	public ArrayList<Model> selectall();
	
	
	public Model selectname(Id id);
	
	public void delete(Id id);

	public void update(Model model);

	public void insert(Model model);

	public void applycnt(Model model);
	
	
	// 해당하는
	public ArrayList<Model> selectnotifi(String hqid);
	
	
	//refresh_change
	default public void updatestate(String chainid) {
		
	}
	


}
