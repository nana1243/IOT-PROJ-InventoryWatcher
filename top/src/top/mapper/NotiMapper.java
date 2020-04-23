package top.mapper;

<<<<<<< HEAD
import java.util.ArrayList;

=======
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
import top.vo.NotiVO;

public interface NotiMapper {

	public void insert(NotiVO notivo);
	
	
	// hq가 맡는 각각의 chainId에 해당하는 noti를 모두 불러온다
	public NotiVO  select(String chainId);
	
	
	//refresh 상태가 바뀌는 것!
	public void updaterefresh(String chainId);
	
<<<<<<< HEAD
	public ArrayList<NotiVO> selectall();
=======
	
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
	
	
}
