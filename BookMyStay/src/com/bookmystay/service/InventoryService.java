package com.bookmystay.service;
import java.util.Map;

import com.bookmystay.model.*;
public class InventoryService{
RoomInventory inventory = new RoomInventory();
public void initializeTypes() {
		inventory.addRoomType("Single", 10, 2500.0);
        inventory.addRoomType("Double", 15, 3500.0);
        inventory.addRoomType("Suite",  5, 8000.0);
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
                               "\n");

	}
}
}
