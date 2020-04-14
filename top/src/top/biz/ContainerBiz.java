package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.ContainerVO;

@Service("conbiz")
public class ContainerBiz implements Biz<String, ContainerVO> {

	@Resource(name = "condao")
	Dao<String, ContainerVO> dao;

	@Override
	public ContainerVO get(String conID) {
		return dao.select(conID);
	}

	@Override
	public ArrayList<ContainerVO> get() {
		return dao.selectall();
	}
	@Override
	public void register(ContainerVO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(ContainerVO model) {
		// TODO Auto-generated method stub
		
	}

}