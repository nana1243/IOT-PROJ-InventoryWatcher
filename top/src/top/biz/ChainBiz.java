package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.ChainVO;

@Service("chainbiz")
public class ChainBiz implements Biz<String, ChainVO> {

	@Resource(name = "chaindao")
	Dao<String, ChainVO> dao;

	@Override
	public ChainVO get(String chainID) {
		return dao.select(chainID);
	}
	
	

	@Override
	public ArrayList<ChainVO> get() {
		return dao.selectall();
	}
	
	
	// check
	@Override
	public void register(ChainVO model) {
		dao.insert(model);
		
	}


	@Override
	public void modify(ChainVO model) {
		
		
	}
	
	@Override
	public ChainVO getname(String hqid) {

		return dao.selectname(hqid);
	}

	@Override
	public ArrayList<ChainVO> getnotifi(String hqid) {
		return dao.selectnotifi(hqid);
		
	}



	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}



	





}