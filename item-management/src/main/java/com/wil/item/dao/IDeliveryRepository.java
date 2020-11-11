package com.wil.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wil.item.dto.ChartData;
import com.wil.item.dto.DeliveryItem;
import com.wil.item.entities.Delivery;

public interface IDeliveryRepository extends CrudRepository<Delivery, Long>{
	
	// Change from iterator to List
	@Override
	public List<Delivery> findAll();
		
	// CrudRepo handling the logic based on method name
	public Delivery findByDeliveryId(long id);
	
	//Query to return an object which can hold the record. Select returns 3 columns, dto(interface) has 3 properties
	@Query(nativeQuery=true, value="SELECT d.delivery_id as deliveryID, d.company_name as companyName, COUNT(i.delivery_id) as itemCount " + 
			"FROM delivery d LEFT JOIN item i ON d.delivery_id=i.delivery_id " + 
			"GROUP BY d.company_name ORDER BY 3 DESC")
	public List<DeliveryItem> deliveryItems();
	
	@Query(nativeQuery=true, value="SELECT distance as label, COUNT(*) as value " + 
			"FROM delivery " + 
			"GROUP BY distance")
	public List<ChartData> deliveryDistance();

}
