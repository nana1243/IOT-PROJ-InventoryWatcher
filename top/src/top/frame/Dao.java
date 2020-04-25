package top.frame;

import java.util.ArrayList;

import top.vo.ChainVO;



public interface Dao<Id, Model> {

	// 기본적 CRUD  //
	
	public ArrayList<Model> selectall();
	
	public Model select(Id id);
	
	
	
	default public void insert(Model model) throws Exception {
	}

	default public void update(Model model) throws Exception {
	}

	default public void delete(Id id) throws Exception {
	}
	
	
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
	
	
	
	default public ArrayList<Model> selectYear(String year) {
		return null;
	}

	default public ArrayList<Model> selectMonth(String year, String id) {
		return null;
	}

	default public ArrayList<Model> selectYearly(String id) {
		return null;
	}

	default public ArrayList<Model> selectMonthly(String id) {
		return null;
	}

	// get container data for specific chainID
	default public ArrayList<Model> selectForChain(String id) {
		return null;
	}

	// get container data for specific hq=ID
	default public ArrayList<Model> selectbyhq(String id) {
		return null;
	}
	



}
