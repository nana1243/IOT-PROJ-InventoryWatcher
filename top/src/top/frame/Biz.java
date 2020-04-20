package top.frame;

import java.util.ArrayList;




public interface Biz<Id, Model> {
	
	public Model get(Id id);
	
	public Model getname(Id id);
	
	public ArrayList<Model> get();
	
	
	public void register(Model model);
	
	public void remove(Id id);
	
	public void modify(Model model);
	

	public void modifycnt(Model model);

	public ArrayList<Model> getnotifi(Id id);
	

}
