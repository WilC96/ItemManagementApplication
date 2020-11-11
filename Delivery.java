package com.wil.item.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Delivery {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long deliveryId;
	
	private String companyName;
	private String description;
	private String distance; //LOCAL, LONG, OVERSEAS
	
	@OneToMany(mappedBy = "delivery") 
	private List<Item> items;

	public Delivery() {
		
	}
	
	public Delivery(String companyName, String description, String distance) {
		super();
		this.companyName = companyName;
		this.description = description;
		this.description = distance;
	}
	

	public long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
	

