package com.wil.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.wil.item.dao.IDeliveryRepository;
import com.wil.item.dao.IItemRepository;

@SpringBootApplication
public class ItemManagementApplication {
	
	@Autowired
	IDeliveryRepository deliveryRepository;
	
	@Autowired
	IItemRepository itemRepository;

	public static void main(String[] args) {
		SpringApplication.run(ItemManagementApplication.class, args);
	}

}
