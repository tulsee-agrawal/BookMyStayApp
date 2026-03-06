package com.bookmystay.app;
/**
 * Use Case 1: Room Inventory Setup & Management
 * 
 * Data Structures:
 * HashMap<String, Integer> – Room type → available count
 * HashMap<String, Double> – Room type → price per night
 * 
 * Application:
 *  - Initialized room types (Single, Double, Suite)
 *  - Stores room counts and prices
 *  - Support dynamic inventory updates
 *  - Provide real-time availability status
 *  
 * flow:
 * - Add room type
 * - Store in HashMap 
 * - Update count/price
 * - Confirm
 * 
 * @author Tulsee Agrawal
 * @version 1.0
 */
import com.bookmystay.model.*;
import com.bookmystay.service.*;

public class App {
public static void main(String[] args) {
	InventoryService inv = new InventoryService();
	inv.initializeTypes();
	inv.showInventory();
	inv.reserve("Single", 10);
	inv.reserve("Double", 1);
	inv.showInventory();
	inv.release("Single", 1);
	inv.showInventory();
}
}
