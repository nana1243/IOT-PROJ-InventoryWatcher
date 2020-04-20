package top.frame;

import java.util.ArrayList;

import top.vo.ChainVO;



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
	


}
