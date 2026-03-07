package com.bookmystay.app;

/**
 * Use Case 4: Reservation Confirmation & Room Allocation
 *
 * Data Structures:
 * Set<String> – Global set of booked room IDs 
 * HashMap<String, Set<String>> – Room type → assigned room IDs
 * HashMap<String, Integer> – Room type → next room number 
 *
 * Application:
 *  - Confirms reservation requests
 *  - Assigns unique room IDs (e.g., S-001, D-002, SU-001)
 *  - Prevents reuse of room IDs via a Set
 *  - Updates inventory immediately upon confirmation
 *
 * Flow:
 * - Receive reservation request
 * - Validate request (type, quantity)
 * - Reserve from inventory (decrement availability)
 * - Generate unique room ID(s) for the type
 * - Add ID(s) to global Set and type-wise Map
 * - Return assigned room ID(s) as confirmation
 *
 * Goal:
 *  - Guarantee zero double-booking with unique allocation
 *  - Keep inventory in sync instantly on confirmation
 *
 * @author Tulsee Agrawal
 * @version 4.0
 */

import java.util.*;
import com.bookmystay.service.*;
import com.bookmystay.model.*;
public class App {
public static void main(String[] args) {
		InventoryService inv = new InventoryService();
		inv.initializeTypes();
		inv.showInventory();

AllocationService alloc = new AllocationService();

Reservation r1 = new Reservation("A", "Double", 2);
        List<String> ids1 = alloc.confirm(r1, inv);
        System.out.println("\nCONFIRMED for " + r1.getGuestName() + " -> " + ids1);


Reservation r2 = new Reservation("B", "Suite", 1);
        List<String> ids2 = alloc.confirm(r2, inv);
        System.out.println("CONFIRMED for " + r2.getGuestName() + " -> " + ids2);
	

try {
            Reservation r3 = new Reservation("C", "Suite", 10);
            alloc.confirm(r3, inv);
        } catch (Exception ex) {
            System.out.println("\nREJECTED (as expected) -> " + ex.getMessage());
        }

System.out.println("\n--- Inventory after confirmations ---");
       inv.showInventory();

       // Show per-type assigned list
       System.out.println("\nAssigned for Double: " + alloc.getAssignedForType("Double"));
       System.out.println("Assigned for Suite : " + alloc.getAssignedForType("Suite"));
       System.out.println("All booked IDs     : " + alloc.getAllBookedIds());
   }
}


