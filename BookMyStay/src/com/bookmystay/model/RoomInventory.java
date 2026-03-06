package com.bookmystay.model;
import java.util.*;
import com.bookmystay.exception.*;
public class RoomInventory {
Map<String,Integer> RoomCountMap;
Map<String,Double> RoomPriceMap;

public RoomInventory() {
	RoomCountMap = new HashMap<>();
	RoomPriceMap  = new HashMap<>();
	
}
public void  addRoomType(String type, Integer count,Double price) {

	if(type== null || type.trim().isEmpty()) {
            throw new InvalidInputException("Room type cannot be null or empty.");
    }

	if(count<0) {
            throw new InvalidInputException("Count cannot be negative for type: " + type);
    }
    if(price<0) {
            throw new InvalidInputException("Price cannot be negative for type: " + type);
    }


	RoomCountMap.put(type, count);
	RoomPriceMap.put(type, price);
	
}
public boolean takeRoom(String type,int qty) {
	if(type== null || type.trim().isEmpty() ) {
        throw new InvalidInputException("Room type cannot be null or empty.");
     }
	if(qty<=0) {
		throw new InvalidInputException("Quantity cannot be 0");
	}
	if (!RoomCountMap.containsKey(type) || !RoomPriceMap.containsKey(type)) {
            throw new RoomTypeException(type);
    }
	
	int available=RoomCountMap.getOrDefault(type,0);
	if(available<qty) {
		throw new InsufficientRoomException(type,qty,available);
	};
	RoomCountMap.put(type,available-qty);
	return true;
}
public void releaseRoom(String type,int qty) {
	if(type== null || type.trim().isEmpty() ) {
        throw new InvalidInputException("Room type cannot be null or empty.");
     }
	if(qty<=0) {
		throw new InvalidInputException("Quantity cannot be 0");
	}
	if (!RoomCountMap.containsKey(type) || !RoomPriceMap.containsKey(type)) {
            throw new RoomTypeException(type);
    }
	int available=RoomCountMap.getOrDefault(type,0);
	RoomCountMap.put(type,available+qty);
	
}
public int getCount(String Type) {
	return RoomCountMap.getOrDefault(Type,0);
}
public double getPrice(String Type) {
	return RoomPriceMap.getOrDefault(Type,0.0);
}
public Map<String, Integer> getRoomCountMap() {
	return RoomCountMap;
}
public Map<String, Double> getRoomPriceMap() {
	return RoomPriceMap;
}
}
