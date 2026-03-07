package com.bookmystay.service;

import java.util.LinkedList;

import java.util.Queue;

import com.bookmystay.exception.InvalidInputException;
import com.bookmystay.model.Reservation;

public class BookingQueueService {

    private final Queue<Reservation> queue = new LinkedList<>();

    public void enqueue(String guestName, String roomType, int qty) {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidInputException("Guest name cannot be empty");
        }
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidInputException("Room type cannot be empty");
        }
        if (qty <= 0) {
            throw new InvalidInputException("Quantity must be > 0");
        }

        queue.add(new Reservation(guestName.trim(), roomType.trim(), qty));
    }
    public int size() {
    	return queue.size();
    }

   
    public boolean processNext(InventoryService inv) {

        Reservation r = queue.poll(); 

        if (r == null) {
            return false; // nothing to process
        }

        try {
            inv.reserve(r.getRoomType(), r.getQuantity());  // try booking
            System.out.println("CONFIRMED: " + r);
            return true;
        } catch (Exception ex) {
            System.out.println("REJECTED: " + r + " | Reason: " + ex.getMessage());
            return false;
        }
    }

    /** Print queue for debugging */
    public void printQueue() {
        System.out.println("\n=== BOOKING QUEUE ===");
        if (queue.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Reservation r : queue) {
            System.out.println(r);
        }
    }
}