package top.frame;

import java.util.ArrayList;

import top.vo.ChainVO;



public interface Dao<Id, Model> {

	
	public Model select(Id id);
	
	public ArrayList<Model> selectall();
	
	
	public void delete(Id id);

	public void update(Model model);

	public void insert(Model model);

//	default public void applycnt(Model model) {
//		
//	};
	
	
	//read.top에서 쓰임 + adminpage.top
	default public ArrayList<ChainVO> selectnotifi(String id){
		return null;
	};
	
	
	//refresh_change
	default public void updatestate(String chainid) {
		
	}
	
	// user apply.top 에서 used!
	default public ChainVO selectname(String id) {
		return null;
	}
	

	


}
