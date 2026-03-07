package com.bookmystay.app;
/**
 * Use Case 3: Booking Request (First-Come-First-Served)
 *
 * Data Structures:
 * Queue<Reservation> – FIFO booking request queue
 *
 * Application:
 *  - Accepts booking requests from guests
 *  - Stores requests in a queue based on arrival order
 *  - Ensures fair processing of all requests
 *  - Avoids race conditions during peak traffic
 *
 * Flow:
 * - Booking request received
 * - Add to queue (FIFO)
 * - Await processing by the system
 *
 *@author Tulsee Agrawal
 *@version 3.0
 */
import com.bookmystay.service.*;

public class App {
public static void main(String[] args) {
		InventoryService inv = new InventoryService();
		inv.initializeTypes();
		inv.showInventory();

		BookingQueueService queue = new BookingQueueService();

        queue.enqueue("A", "Double", 2);
        queue.enqueue("B", "Suite", 2);
        queue.enqueue("C", "Double", 5);
        queue.enqueue("D", "Single", 1);

        queue.printQueue();

        System.out.println("\n=== PROCESSING FIFO ===");
        while (queue.size() > 0) {
            queue.processNext(inv);
        }

        System.out.println("\nFinal Inventory:");
        inv.showInventory();

}
}
