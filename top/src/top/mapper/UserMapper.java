package top.mapper;

import top.model.User;

public interface UserMapper {
	

	public User select(String u_id);
	
	public User insert(User user);

}
