package com.bookmystay.service;
import java.util.*;


import com.bookmystay.model.*;
public class InventoryService{
RoomInventory inventory = new RoomInventory();
public void initializeTypes() {
		inventory.addRoomType("Single", 10, 2500.0);
		inventory.setAmenities("Single", Arrays.asList("Free Wi-Fi", "AC", "TV"));
        inventory.addRoomType("Double", 15, 3500.0);
        inventory.setAmenities("Double", Arrays.asList("Free Wi-Fi", "AC", "TV", "City View"));
        inventory.addRoomType("Suite",  5, 8000.0);
        inventory.setAmenities("Suite", Arrays.asList("Free Wi-Fi", "AC", "Mini Bar", "Bathtub"));

}
public void reserve (String type,int count) {
	boolean res=inventory.takeRoom(type, count);
	if(res) {
		System.out.println("\nReserved "+count+" "+type+ " Rooms.");
	}
	else {
		System.out.println("\nNo rooms available for "+type+ " type.");
	}
}
public void release(String type,int count) {
	inventory.releaseRoom(type, count);
	System.out.println("\nReleased "+count+" "+type+ " Rooms.");
}
public void showInventory() {
	System.out.println("----------INVENTORY DETAILS--------");
	for(Map.Entry<String, Integer> e: inventory.getRoomCountMap().entrySet()) {

			String type = e.getKey();
            System.out.println("\n Type      :  "+type +
                               "\n Available :  " + e.getValue() +
                               "\n Price      : ₹" + inventory.getPrice(type)+
                               " | Amenities: " + String.join(", ", inventory.getAmenities(type))+
                               "\n");

	}
}
public RoomInventory getInventory() { return inventory; }
}
