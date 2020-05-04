package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.IngredientVO;

@Service("ingbiz")
public class IngredientBiz implements Biz<String, IngredientVO> {

	@Resource(name = "ingdao")
	Dao<String, IngredientVO> dao;

	@Override
	public void register(IngredientVO model) throws Exception {
		dao.insert(model);
	}

	@Override
	public IngredientVO get(String ingID) {
		return dao.select(ingID);
	}

	@Override
	public ArrayList<IngredientVO> get() {
		return dao.selectall();
	}

	@Override
	public void remove(String ingID) throws Exception {
		dao.delete(ingID);

	}

	@Override
	@Transactional
	public void modify(IngredientVO model) throws Exception {
		dao.update(model);

	}
	@Override
	@Transactional
	public void modify2(IngredientVO model) throws Exception {
		dao.update2(model);
		
	}
	@Override
	public ArrayList<IngredientVO> getUserSpecific(String hqID) {
		return dao.selectUserSpecificOneIng(hqID);
	}

}