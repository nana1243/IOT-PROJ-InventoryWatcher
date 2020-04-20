package top.mapper;

import java.util.ArrayList;

import top.vo.ChainVO;

public interface ChainMapper {

	public ChainVO select(String chainID);

	public ArrayList<ChainVO> selectall();
	
	
	
	// hq가 맡은 chain을 모두 불러오기 위해서
	public ArrayList<ChainVO> selectnotifi(String hqid);
	
	
	public void insert(ChainVO chainvo);
	
	public ChainVO selectchainname(String chainid);
	
	public void update(ChainVO chainvo);
	
	public void applycnt(ChainVO chainvo);
	
	

}