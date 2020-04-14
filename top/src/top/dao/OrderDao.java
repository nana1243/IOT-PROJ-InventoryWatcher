package top.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.frame.Dao;
import top.mapper.OrderMapper;
import top.vo.OrderVO;

@Repository("orderdao")
public class OrderDao implements Dao<String, OrderVO> {
	@Autowired
	OrderMapper ordermapper;

	@Override
	public OrderVO select(String orderID) {
		return ordermapper.select(orderID);
	}

	@Override
	public ArrayList<OrderVO> selectall() {
		return ordermapper.selectall();
	}
	
	@Override
	public void insert(OrderVO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(OrderVO model) {
		// TODO Auto-generated method stub
		
	}
	


}