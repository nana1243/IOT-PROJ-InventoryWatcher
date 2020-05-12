package top.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import top.vo.IngredientVO;

public interface IngredientMapper {

	public IngredientVO select(String ingID);

	public ArrayList<IngredientVO> selectall();

	// hyunsu start

	public void insert(IngredientVO obj);

	public void delete(String obj);

	public void update(IngredientVO ingredient);

	public void update2(IngredientVO ingredient);

	public ArrayList<IngredientVO> ingselect(String id);

	public ArrayList<IngredientVO> selectUserSpecific(@Param(value = "hqID") String hqID);

	public ArrayList<IngredientVO> selectUserSpecificOneIng(String hqID);

}