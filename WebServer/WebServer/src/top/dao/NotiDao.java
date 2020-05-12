package top.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.frame.Dao;
import top.mapper.NotiMapper;
import top.vo.NotiVO;

@Repository("notidao")
public class NotiDao implements Dao<String, NotiVO> {

	// 여기서는 INSERT로 USER_APPLY의 객체를 저장해준다

	@Autowired
	NotiMapper notimapper;

	@Override
	public NotiVO select(String id) {
		return notimapper.select(id);
	}

	@Override
	public void updatestate(String chainid) {
		notimapper.updaterefresh(chainid);
	}

	@Override
	public ArrayList<NotiVO> selectall() {
		return notimapper.selectall();
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(NotiVO model) {
		// TODO Auto-generated method stub

	}

	public void updatestatetrue(String chainId) {
		notimapper.updatestatetrue(chainId);

	}

	@Override
	public void insert(NotiVO model) {
		notimapper.insert(model);

	}

}
