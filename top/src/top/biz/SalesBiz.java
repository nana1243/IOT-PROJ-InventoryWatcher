package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.SalesVO;

@Service("salesbiz")
public class SalesBiz implements Biz<String, SalesVO> {

	@Resource(name = "salesdao")
	Dao<String, SalesVO> dao;

	@Override
	public SalesVO get(String salesID) {
		return dao.select(salesID);
	}

	@Override
	public ArrayList<SalesVO> get() {
		return dao.selectall();
	}
	
	@Override
	public void register(SalesVO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(SalesVO model) {
		// TODO Auto-generated method stub
		
	}

}