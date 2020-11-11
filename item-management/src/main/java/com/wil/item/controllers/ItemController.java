package com.wil.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wil.item.dao.IItemRepository;
import com.wil.item.entities.Item;

@Controller
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	IItemRepository itemRepository;
	
	@GetMapping 
	public String displayItemForm(Model model) {
		
		List<Item> itemList = itemRepository.findAll();
		
		model.addAttribute("itemsList", itemList);
		
		return "items/list-item"; 
	}
	
	@GetMapping("/new")
	public String createItemForm(Model model) {
		
		Item i = new Item();
		
		model.addAttribute("item", i);
		
		return "items/new-item"; 
	}
	
	@PostMapping("/save")
	public String createItem(Item item, Model model) {
		
		itemRepository.save(item);
		
		return "redirect:/items/new";
	}
	
	@GetMapping("/update")
	public String itemUpdate(@RequestParam("id") long itemId, Model model) {
		
		Item item = itemRepository.findByItemId(itemId);
		model.addAttribute("item", item);
		
		return "items/new-item";
	}
	
	@GetMapping("/delete")
	public String itemDelete(@RequestParam("id") long itemId, Model model) {
		
		Item item = itemRepository.findByItemId(itemId);
		itemRepository.delete(item);
		
		return "redirect:/";
	}

}
