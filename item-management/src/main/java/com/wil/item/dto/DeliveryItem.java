package com.wil.item.dto;

public interface DeliveryItem {
	
	//Have get before each property name
	//So Spring Data knows that this data transfer object is used to populate the table with the data coming from the query
	public String getDeliveryId();
	public String getCompanyName();
	public int getItemCount();

}
