package top.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.frame.Dao;
import top.mapper.ChainMapper;
import top.vo.ChainVO;

@Repository("chaindao")
public class ChainDao implements Dao<String, ChainVO> {
	@Autowired
	ChainMapper chainmapper;

	@Override
	public ChainVO select(String chainID) {
		return chainmapper.select(chainID);
	}

	@Override
	public ArrayList<ChainVO> selectall() {
		return chainmapper.selectall();
	}
	
	public ArrayList<ChainVO> selectchainname(String hqID){
		return chainmapper.selectchainname(hqID);
		
	}

	@Override
	public void insert(ChainVO model) {
		chainmapper.insert(model);

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ChainVO model) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<ChainVO> selectname(String hqID) {
		return chainmapper.selectchainname(hqID);
	}

}