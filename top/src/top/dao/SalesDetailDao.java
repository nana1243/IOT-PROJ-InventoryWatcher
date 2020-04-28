package top.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.frame.Dao;
import top.mapper.SalesDetailMapper;
import top.vo.SalesDetailVO;

@Repository("salesdetaildao")
public class SalesDetailDao implements Dao<String, SalesDetailVO> {
	@Autowired
	SalesDetailMapper salesdetailmapper;

	@Override
	public SalesDetailVO select(String salesDetailID) {
		return salesdetailmapper.select(salesDetailID);
	}

	@Override
	public ArrayList<SalesDetailVO> selectall() {
		return salesdetailmapper.selectall();
	}

	@Override
	public void insert(SalesDetailVO model) throws Exception {
		salesdetailmapper.insert(model);

		
	}

	@Override
	public void update(SalesDetailVO model) throws Exception {
		salesdetailmapper.update(model);
	}

	@Override
	public void delete(String salesDetailID) throws Exception {
		salesdetailmapper.delete(salesDetailID);
		
	}

}