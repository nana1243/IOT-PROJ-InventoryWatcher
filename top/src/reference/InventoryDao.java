package reference;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import top.frame.Dao;

@Repository("inventorydao")
public class InventoryDao implements Dao<String, InventoryVO> {
	@Autowired
	InventoryMapper inventorymapper;

	@Override
	public InventoryVO select(String inventoryID) {
		return inventorymapper.select(inventoryID);
	}

	@Override
	public ArrayList<InventoryVO> selectall() {
		return inventorymapper.selectall();
	}

	@Override
	public void insert(InventoryVO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(InventoryVO model) {
		// TODO Auto-generated method stub
		
	}

}