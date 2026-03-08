package com.bookmystay.app;

/**
 * Use Case 5: Add-On Service Selection
 *
 * Data Structures:
 * Map<Reservation, List<Service>> – reservation → attached services
 *
 * Application:
 *  - Attach optional services (breakfast, spa, airport pickup) to a reservation
 *  - Allow multiple services per booking
 *  - Calculate total additional cost for billing
 *
 * Flow:
 * - Select service
 * - Add to List
 * - Map to Reservation
 *
 * @author Tulsee Agrawal
 * @version 5.0
 */

import java.util.*;
import com.bookmystay.service.*;
import com.bookmystay.model.*;
public class App {
	    public static void main(String[] args) {

	        InventoryService inv = new InventoryService();
	        inv.initializeTypes();

	      
	        AllocationService alloc = new AllocationService();

	     
	        Reservation r1 = new Reservation("A", "Double", 2);
	        Reservation r2 = new Reservation("B", "Suite", 1);

	   
	        System.out.println("Allocating rooms...");
	        List<String> rooms1 = alloc.confirm(r1, inv); // updates inventory + assigns IDs
	        List<String> rooms2 = alloc.confirm(r2, inv);
	        System.out.println("A rooms: " + rooms1);
	        System.out.println("B rooms: " + rooms2);

	        // UC-5: Add-on services
	        ServiceManagement sm = new ServiceManagement();

	       
	        sm.addService(r1, new Service("Breakfast", 500));
	        sm.addService(r1, new Service("Airport Pickup", 1200));

	        sm.addServices(r2, Arrays.asList(
	                new Service("Spa", 2000),
	                new Service("Breakfast", 500)
	        ));

	        sm.printServices(r1);
	        sm.printServices(r2);

	        sm.removeService(r1, "Breakfast");
	        System.out.println("\nAfter removing Breakfast for Aarav:");
	        sm.printServices(r1);

	        inv.showInventory();
	    }
}


