package top.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.frame.Dao;
import top.mapper.HeadquarterMapper;
import top.vo.HeadquarterVO;

@Repository("hqdao")
public class HeadquarterDao implements Dao<String, HeadquarterVO> {
	@Autowired
	HeadquarterMapper hqmapper;

	@Override
	public HeadquarterVO select(String hqID) {
		return hqmapper.select(hqID);
	}

	@Override
	public ArrayList<HeadquarterVO> selectall() {
		return hqmapper.selectall();
	}
	
	@Override
	public void insert(HeadquarterVO model) {
		hqmapper.insert(model);
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(HeadquarterVO model) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void applycnt(HeadquarterVO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<HeadquarterVO> selectnotifi(String hqid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeadquarterVO selectname(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}