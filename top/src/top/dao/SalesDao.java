package top.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.frame.Dao;
import top.mapper.SalesMapper;
import top.vo.SalesVO;

@Repository("salesdao")
public class SalesDao implements Dao<String, SalesVO> {
	@Autowired
	SalesMapper salesmapper;

	@Override
	public SalesVO select(String salesID) {
		return salesmapper.select(salesID);
	}

	@Override
	public ArrayList<SalesVO> selectall() {
		return salesmapper.selectall();
	}
	@Override
	public void insert(SalesVO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SalesVO model) {
		// TODO Auto-generated method stub
		
	}

}