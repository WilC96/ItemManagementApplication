package com.wil.item.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity // Entity hibernate module can pick this up as a pojo to be saved into the db
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long itemId;
	
	private String name;
	private String description;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, 
			fetch = FetchType.LAZY)
	
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	public Item() {
		
	}
	
	public Item(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public long getItemId() {
		return itemId;
	}
	
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
