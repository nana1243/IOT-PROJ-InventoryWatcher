package top.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.model.User;

@Service("ubiz")
public class UserBiz implements Biz<String, User> {

	@Resource(name = "udao")
	Dao<String, User> dao;
	
	@Override
	public User get(String u_id) throws Exception {
		return dao.select(u_id);
	}
	
	@Override
	public void register(User v) throws Exception {
		dao.insert(v);
	}
	


}