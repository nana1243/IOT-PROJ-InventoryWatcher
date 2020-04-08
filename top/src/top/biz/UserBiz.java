package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.UserVO;


@Service("ubiz")
public class UserBiz implements Biz<String, UserVO> {

	@Resource(name = "udao")
	Dao<String, UserVO> dao;
	
	@Override
	public UserVO get(String u_id) {
		return dao.select(u_id);
	}

	@Override
	public ArrayList<UserVO> get() {
		return dao.selectall();
	}

	@Override
	public void register(UserVO user) {
		dao.insert(user);
	}
	
	
	


}