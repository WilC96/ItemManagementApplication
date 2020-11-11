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
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Annotation from hibernate -> a module within spring. 
	//Maps java objects to db tables and increments the id
	private long itemId;
	
	private String name;
	private String description;
	
	// define relationship (this is the child as its many to one)
	// If anything happens to the parent (Delivery), cascade rules can define what we want the child to be programmed to do
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, 
			fetch = FetchType.LAZY)
	
	// In Object Relational Mapping (ORM, ie. Hibernate), fetching/loading data is determined by 2 types.
	// LAZY - If Delivery is loaded, associated Items will not be initialised and loaded into memory of app until an explicit call
	// EAGER - If Delivery is loaded, also load up all associated items to store in memory. Can slow down the app
	
	// current chosen cascade types are typical for industry standards, we don't use remove as we want the flexibility to pick and choose
	// Detach - 
	// Merge - If Delivery is merged into another project, so do the associated items
	// Persist - If Delivery is saved into the db, so are the items associated
	// Refresh - If Delivery gets refreshed (re-query a parent in db), also refreshes the items associated
	// Remove - If Delivery is deleted, items associated will also be deleted
	
	// *Selecting an item that's already in another delivery will just overwrite the deliveryId in the item table*
	
	@JoinColumn(name = "delivery_id") // new column name
	private Delivery delivery; // the relationship object it'll be assigned to
	
	public Item() {
		
	}
	
	public Item(String name, String description) {
		super();
		this.name = name; //name should be same as th var... 
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
