package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.NotiVO;

<<<<<<< HEAD
@Service("notibiz")
public class NotiBiz implements Biz<String, NotiVO> {

=======


@Service("notibiz")
public class NotiBiz implements Biz<String , NotiVO>{
	
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
	@Resource(name = "notidao")
	Dao<String, NotiVO> dao;

	@Override
	public NotiVO get(String id) {
<<<<<<< HEAD
		return dao.select(id);
=======
		
		return dao.select(id);
	
	}

	@Override
	public NotiVO getname(String id) {
		// TODO Auto-generated method stub
		return null;
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
	}

	@Override
	public ArrayList<NotiVO> get() {
<<<<<<< HEAD
		return dao.selectall();
	}
	
	

=======
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// 쓰는 함수!-------------------------
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633

	@Override
	public void register(NotiVO model) {
		dao.insert(model);
<<<<<<< HEAD

	}

=======
		
	}
	
	
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
	@Override
	public void refreshstate(String chainId) {
		dao.updatestate(chainId);
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
<<<<<<< HEAD

=======
		
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
	}

	@Override
	public void modify(NotiVO model) {
		// TODO Auto-generated method stub
<<<<<<< HEAD

	}

	

	
=======
		
	}

	@Override
	public void modifycnt(NotiVO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<NotiVO> getnotifi(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
}
