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
		
		Delivery delivery = new Delivery(); //empty object to map to the form submission
		model.addAttribute("delivery", delivery); //binds empty object to name specified in th:object 
		
		List<Item> items = itemRepository.findAll();
		List<Item> itemsWithNull = items.stream().filter(i -> i.getDelivery() == null).collect(Collectors.toList());
		//^Only return items that aren't already in a delivery. Without this, the new delivery will just overwrite the first
		model.addAttribute("allItems", itemsWithNull); //carries over the list of items from db for the html when selecting items dropdown
		
		return "deliveries/new-delivery"; 
		//name of html page(in template) for reroute on url /items/new, .html not required
	}
	
	@PostMapping("/save") // method is POST in the template
	public String createDelivery(Delivery delivery, @RequestParam List<Long> items, Model model) {
		//RequestParam name(items) needs to match the request param (object(entity) property name)
		
		deliveryRepository.save(delivery);
		
		//Put all items that were selected when creating a delivery into a list
		Iterable<Item> chosenItems = itemRepository.findAllById(items);
		
		//For each item, update its delivery field (so the foreign key is not null) and save into the db
		//It is necessary to update the items from DeliveryController as delivery is assigned to items table when delivery is created
		for (Item item : chosenItems) {
			item.setDelivery(delivery);
			itemRepository.save(item);
		}
		
		//prevent duplicate submissions(spamming submit) via redirect to any page
		return "redirect:/delivery/new";
	}
	
	@GetMapping("/update")
	public String deliveryUpdate(@RequestParam("id") long deliveryId, Model model) {
		
		Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId);
		model.addAttribute("delivery", delivery);
		
		List<Item> items = itemRepository.findAll();
		List<Item> itemsWithNull = items.stream().filter(i -> i.getDelivery() == null).collect(Collectors.toList());
		//^Only return items that aren't already in a delivery. Without this, the new delivery will just overwrite the first
		model.addAttribute("allItems", itemsWithNull); //carries over the list of items from db for the html when selecting items dropdown
		
		return "deliveries/new-delivery";
	}
	
	@GetMapping("/delete")
	public String deliveryDelete(@RequestParam("id") long deliveryId, Model model) {
		
		Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId);
		deliveryRepository.delete(delivery);
		
		return "redirect:/";
	}

}
