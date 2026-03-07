package com.bookmystay.app;
/**
 * Use Case 2: Room Search & Availability Check
 * 
 * Data Structures:
 * HashMap<String, Integer> – Room type → available count
 * HashMap<String, Double>  – Room type → price per night
 * HashMap<String, List<String>> – Room type → amenities
 * 
 * Application:
 *  - Displays available room types
 *  - Shows prices and amenities for each room
 *  - Provides real-time availability (read-only)
 *  - Prevents booking of unavailable room types
 *  
 * Flow:
 * - Receive search request
 * - Lookup data from HashMaps
 * - Filter rooms with availability > 0
 * - Display availability, price, and amenities
 * 
 * Goal:
 *  - Allow guests to search rooms without modifying inventory
 *  - Ensure accurate, real-time availability results
 * 
 * @author Tulsee Agrawal
 * @version 2.0
 */
import com.bookmystay.service.*;

public class App {
public static void main(String[] args) {
	InventoryService inv = new InventoryService();
	inv.initializeTypes();

	 // UC-2: read-only search
	 SearchService search = new SearchService(inv.getInventory());

	 // Before any booking
	 search.showAvailable();
	 
	 inv.reserve("Double", 3);

	 System.out.println("\n--- After booking 3 Doubles ---");
        search.showAvailable();

}
}
