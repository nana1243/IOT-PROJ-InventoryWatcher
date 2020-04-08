package top.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.frame.Dao;
import top.mapper.UserMapper;
import top.vo.UserVO;



@Repository("udao")
public class UserDao implements Dao<String, UserVO > {
	@Autowired
	UserMapper um;
	
	
	@Override
	public UserVO select(String u_id) {
		return um.select(u_id);
	}


	@Override
	public void insert(UserVO user) {
		um.insert(user);
	}


	@Override
	public ArrayList<UserVO> selectall() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}