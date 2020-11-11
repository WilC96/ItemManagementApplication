package com.wil.item.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wil.item.dao.IDeliveryRepository;
import com.wil.item.dao.IItemRepository;
import com.wil.item.entities.Delivery;
import com.wil.item.entities.Item;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	IDeliveryRepository deliveryRepository;
	
	@Autowired
	IItemRepository itemRepository;
	
	@GetMapping //endpoint will be just delivery, as in the RequestMapping
	public String displayItemForm(Model model) {
		
		List<Delivery> deliveryList = deliveryRepository.findAll();
		
		model.addAttribute("deliveryList", deliveryList);
		
		return "deliveries/list-delivery";
	}
	
	@GetMapping("/new")
	public String createItemForm(Model model) {
		
		Delivery delivery = new Delivery();
		model.addAttribute("delivery", delivery); 
		
		//only return items that aren't already in a delivery
		List<Item> items = itemRepository.findAll();
		List<Item> itemsWithNull = items.stream().filter(i -> i.getDelivery() == null).collect(Collectors.toList());
		
		model.addAttribute("allItems", itemsWithNull);
		
		return "deliveries/new-delivery"; 
	}
	
	@PostMapping("/save")
	public String createDelivery(Delivery delivery, @RequestParam List<Long> items, Model model) {
		
		deliveryRepository.save(delivery);
		
		//Put all items that were selected when creating a delivery into a list
		Iterable<Item> chosenItems = itemRepository.findAllById(items);
		
		//For each item, update its delivery field (so the foreign key is not null) and save into the db
		for (Item item : chosenItems) {
			item.setDelivery(delivery);
			itemRepository.save(item);
		}
		
		return "redirect:/delivery/new";
	}
	
	@GetMapping("/update")
	public String deliveryUpdate(@RequestParam("id") long deliveryId, Model model) {
		
		Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId);
		model.addAttribute("delivery", delivery);
		
		//only return items that aren't already in a delivery
		List<Item> items = itemRepository.findAll();
		List<Item> itemsWithNull = items.stream().filter(i -> i.getDelivery() == null).collect(Collectors.toList());
		
		model.addAttribute("allItems", itemsWithNull);
		
		return "deliveries/new-delivery";
	}
	
	@GetMapping("/delete")
	public String deliveryDelete(@RequestParam("id") long deliveryId, Model model) {
		
		Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId);
		deliveryRepository.delete(delivery);
		
		return "redirect:/";
	}

}
