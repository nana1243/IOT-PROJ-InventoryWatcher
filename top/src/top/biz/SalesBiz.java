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

	// 언니 스타트
	public ArrayList<SalesVO> getYear(String year) {
		return dao.selectYear(year);
	}

	public ArrayList<SalesVO> getMonth(String year, String chainID) {
		return dao.selectMonth(year, chainID);
	}

	public ArrayList<SalesVO> getYearly(String salesID) {
		return dao.selectYearly(salesID);
	}

	public ArrayList<SalesVO> getMonthly(String salesID) {
		return dao.selectMonthly(salesID);
	}
	
	// 동현 poscontroller.top start
	public SalesVO getbychain(String chainID) {
		return dao.selectbychain(chainID);
	}
	
	
	
	
	@Override
	public void register(SalesVO model) throws Exception {
		dao.insert(model);

	}

	@Override
	public void modify(SalesVO model) throws Exception {
		dao.update(model);

	}

	@Override
	public void remove(String salesID) throws Exception {
		dao.delete(salesID);

	}

}