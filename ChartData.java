package com.wil.item.dto;

public interface ChartData {
	
	//Have get before each property name
	//So Spring Data knows that this data transfer object is used to populate the table with the data coming from the query
	public String getLabel();
	public long getValue();

}
