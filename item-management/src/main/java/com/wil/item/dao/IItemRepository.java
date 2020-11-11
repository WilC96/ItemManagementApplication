package com.wil.item.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wil.item.entities.Item;

public interface IItemRepository extends CrudRepository<Item, Long>{
	
	// Change from iterator to List
	@Override
	public List<Item> findAll();
	
	// CrudRepo handling the logic based on method name
	public Item findByItemId(long id);

}
