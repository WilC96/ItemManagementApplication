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
	
	/*
	//seed some info to play with
	@Bean
	CommandLineRunner runner() {
	
		return args -> {
			
			Item item1 = new Item("PS4", "the superior console");
			Item item2 = new Item("Switch", "the weakest console");
			Item item3 = new Item("Smash Bros", "local or online?");
			
			Item item4 = new Item("One Punch Man", "Merchandise");
			Item item5 = new Item("Comic books", "use as fire fuel");
			Item item6 = new Item("Manga", "getting more popular these days");
			
			Item item7 = new Item("Anime blu rays", "don't stop buying these");
			Item item8 = new Item("Bakery goods", "butter flavour");
			Item item9 = new Item("A sandwich", "with crusts");
			
			
			Delivery delivery1 = new Delivery("Tesco", "Bakery order");
			Delivery delivery2 = new Delivery("GameStop", "Games and merch restock");
			Delivery delivery3 = new Delivery("Book Store", "Stock order");
			Delivery delivery4 = new Delivery("The Skip", "Unwanted trash here");
			
			
			delivery1.setItems(Arrays.asList(item8, item9));
			delivery2.setItems(Arrays.asList(item1, item2, item3, item7));
			delivery3.setItems(Arrays.asList(item4, item6));
			delivery4.setItems(Arrays.asList(item5));
			
			item1.setDelivery(delivery2);
			item2.setDelivery(delivery2);
			item3.setDelivery(delivery2);
			item4.setDelivery(delivery3);
			item5.setDelivery(delivery4);
			item6.setDelivery(delivery3);
			item7.setDelivery(delivery2);
			item8.setDelivery(delivery1);
			item9.setDelivery(delivery1);
			
			//save deliveries and items to database
			
			itemRepository.save(item1);
			itemRepository.save(item2);
			itemRepository.save(item3);
			itemRepository.save(item4);
			itemRepository.save(item5);
			itemRepository.save(item6);
			itemRepository.save(item7);
			itemRepository.save(item8);
			itemRepository.save(item9);
			
			deliveryRepository.save(delivery1);
			deliveryRepository.save(delivery2);
			deliveryRepository.save(delivery3);
			deliveryRepository.save(delivery4);
			
		};
	}
	*/

}
