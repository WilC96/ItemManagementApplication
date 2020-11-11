package com.wil.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wil.item.dao.IDeliveryRepository;
import com.wil.item.dao.IItemRepository;
import com.wil.item.dto.ChartData;
import com.wil.item.dto.DeliveryItem;
import com.wil.item.entities.Item;


@Controller
public class HomeController {
	
	@Autowired
	IItemRepository itemRepository;
	
	@Autowired
	IDeliveryRepository deliveryRepository;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		//Items
		List<Item> items = itemRepository.findAll(); // finds all in the db and puts into a list
		model.addAttribute("itemsList", items);
		
		//Deliveries
		List<DeliveryItem> deliveriesItems = deliveryRepository.deliveryItems();
		model.addAttribute("deliveryItemCount", deliveriesItems);
		
		//Bring in a List of records from the sql query
		List<ChartData> deliveryDistance = deliveryRepository.deliveryDistance();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(deliveryDistance);
		//Data structure mapped to String format [["LOCAL", 1], ["LONG", 3], ["OVERSEAS", 2]]
		
		model.addAttribute("deliveryDistance", jsonString);
		
		return "main/home";	
	}

}

