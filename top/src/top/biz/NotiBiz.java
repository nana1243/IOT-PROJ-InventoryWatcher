package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.NotiVO;

@Service("notibiz")
public class NotiBiz implements Biz<String, NotiVO> {

	@Resource(name = "notidao")
	Dao<String, NotiVO> dao;

	@Override
	public NotiVO get(String id) {
		return dao.select(id);
	}

	@Override
	public ArrayList<NotiVO> get() {
		return dao.selectall();
	}
	
	


	@Override
	public void register(NotiVO model) throws Exception {
		dao.insert(model);

	}

	@Override
	public void refreshstate(String chainId) {
		dao.updatestate(chainId);
	}
	@Override
	public void refreshStateTrue(String chainId) {
		dao.updatestatetrue(chainId);
	}

	



	

	
}
