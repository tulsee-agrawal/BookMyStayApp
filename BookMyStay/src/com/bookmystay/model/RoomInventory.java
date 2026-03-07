package com.bookmystay.model;
import java.util.*;
import com.bookmystay.exception.*;
public class RoomInventory {
Map<String,Integer> RoomCountMap;
Map<String,Double> RoomPriceMap;
Map<String, List<String>> amenities;
public RoomInventory() {
	RoomCountMap = new HashMap<>();
	RoomPriceMap  = new HashMap<>();
	amenities =new HashMap<>();
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
	amenities.put(type, new ArrayList<>());
	
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

public void setAmenities(String type,List<String> amenity) {
	if(type== null || type.trim().isEmpty() ) {
        throw new InvalidInputException("Room type cannot be null or empty.");
     }

     List<String> safe = (amenity == null) ? new ArrayList<>() : new ArrayList<>(amenity);
     amenities.put(type, safe);

}
public int getCount(String Type) {
	return RoomCountMap.getOrDefault(Type,0);
}
public double getPrice(String Type) {
	return RoomPriceMap.getOrDefault(Type,0.0);
}

public List<String> getAmenities(String type) {
        List<String> list = amenities.get(type);
        return (list == null) ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

public Map<String, Integer> getRoomCountMap() {
	return new HashMap<>(RoomCountMap);
}
public Map<String, Double> getRoomPriceMap() {
	return new HashMap<>(RoomPriceMap);
}

public Map<String, List<String>> getAllAmenities() {


Map<String, List<String>> copy = new HashMap<>();
    for (Map.Entry<String, List<String>> e : amenities.entrySet()) {
        copy.put(e.getKey(), new ArrayList<>(e.getValue())); // new list per entry
    }
    return copy; 
    }

}
