package com.bookmystay.app;

/**
 * Use Case 6: Booking History & Reporting
 *
 * Data Structures:
 * List<Reservation> – ordered storage of confirmed/canceled bookings
 *
 * Application:
 *  - Stores confirmed reservations
 *  - Supports cancellation and review
 *  - Generates simple activity reports
 *
 * Flow:
 * - Confirm booking
 * - Add to List
 * - Persist (in-memory for now)
 * - Retrieve when needed
 *
 * @author Tulsee Agrawal
 * @version 6.0
 */

import java.util.*;
import com.bookmystay.service.*;
import com.bookmystay.model.*;

public class App {
    public static void main(String[] args) {

      
        InventoryService inv = new InventoryService();
        inv.initializeTypes();

        
        AllocationService alloc = new AllocationService();

        
        HistoryService history = new HistoryService();

     
        Reservation r1 = new Reservation("A", "Double", 2);
        Reservation r2 = new Reservation("B", "Suite", 1);
        Reservation r3 = new Reservation("C",  "Single", 1);

        // Confirm r1, r2
        List<String> rooms1 = alloc.confirm(r1, inv);
        List<String> rooms2 = alloc.confirm(r2, inv);
        System.out.println("CONFIRMED r1 -> " + rooms1);
        System.out.println("CONFIRMED r2 -> " + rooms2);

        history.recordConfirmation(r1);
        history.recordConfirmation(r2);

      
        try {
            List<String> rooms3 = alloc.confirm(r3, inv);
            System.out.println("CONFIRMED r3 -> " + rooms3);
            history.recordConfirmation(r3);

           
            inv.release("Single", r3.getQuantity());
            history.recordCancellation(r3);
            System.out.println("CANCELED r3 and released inventory.");
        }
        catch (Exception ex) {
            System.out.println("r3 FAILED: " + ex.getMessage());
        }

        inv.showInventory();

        history.printReport();
    }
}


