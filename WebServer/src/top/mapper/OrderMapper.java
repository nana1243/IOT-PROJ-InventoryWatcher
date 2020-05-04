package top.mapper;

import java.util.ArrayList;

import top.vo.OrderVO;

public interface OrderMapper {

	public void insert(OrderVO order);
	public OrderVO select(String orderID);
	public ArrayList<OrderVO> selectall();

}