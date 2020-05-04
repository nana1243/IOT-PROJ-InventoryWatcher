package top.frame;

import java.util.ArrayList;

import top.vo.ChainVO;
import top.vo.IngredientVO;



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
	
	
	// hyun su ' ingredient START
	default public void update2(Model model) throws Exception {
	}
	
	
	default public ArrayList<Model> selectUserSpecificOneIng(Id id) {
		return null;

	}
	
	//read.top에서 쓰임 + adminpage.top(hennie)
	default public ArrayList<ChainVO> selectnotifi(String id){
		return null;
	};
	
	//refresh_change(hennie)
	default public void updatestate(String chainid) {
		
	}
	
	//refresh_ notification_state_change((hennie)
	default public void updatestatetrue(String chainid) {
		
	}
	
	// user apply.top 에서 used!
	default public ChainVO selectname(String id) {
		return null;
	}
	
	
	// start hyun min' function START
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
	
	// start hyun min' function END //

	
	// poscontroller - 동현
	
	default public Model selectbychain(Id id) {
		return null;
	}




}
