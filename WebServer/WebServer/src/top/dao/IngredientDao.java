package top.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import top.frame.Dao;
import top.mapper.IngredientMapper;
import top.vo.IngredientVO;

@Repository("ingdao")
public class IngredientDao implements Dao<String, IngredientVO> {
	@Autowired
	IngredientMapper ingredientmapper;

	// Hyun min start

	@Override
	public IngredientVO select(String ingID) {
		return ingredientmapper.select(ingID);
	}

	@Override
	public ArrayList<IngredientVO> selectall() {
		return ingredientmapper.selectall();
	}

	// Hyun SU start

	@Override
	public void insert(IngredientVO model) {
		ingredientmapper.insert(model);

	}

	@Override
	@Transactional
	public void update(IngredientVO model) {
		ingredientmapper.update(model);

	}

	@Override
	@Transactional
	public void update2(IngredientVO model) {
		ingredientmapper.update2(model);

	}

	@Override
	public void delete(String id) {
		ingredientmapper.delete(id);
	}
	
	@Override
	public ArrayList<IngredientVO> selectUserSpecificOneIng(String id) {
		return ingredientmapper.selectUserSpecificOneIng(id);

	}
	
	
}