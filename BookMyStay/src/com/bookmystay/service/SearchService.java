package com.bookmystay.service;

import com.bookmystay.model.RoomInventory;


import java.util.*;

/**
 * UC-2: Room Search & Availability Check
 * - Read-only access
 * - Defensive reads (uses copies from RoomInventory)
 * - Availability validation without altering inventory
 */
public class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public List<String> listAvailableRoomTypes() {
        Map<String, Integer> counts = inventory.getRoomCountMap(); // copy
        List<String> available = new ArrayList<>();

        for (String type : counts.keySet()) {
            int qty = counts.get(type);
            if (qty > 0) {
                available.add(type);
            }
        }

        // sort alphabetically
        Collections.sort(available);

        return available;
    }


    public boolean canBook(String type, int qty) {
        return qty > 0 && inventory.getCount(type) >= qty;
    }

    public void showAvailable() {
        System.out.println("\n=== SEARCH RESULTS (Read-only) ===");
        List<String> types = listAvailableRoomTypes();
        if (types.isEmpty()) {
            System.out.println("(No rooms available at the moment)");
            return;
        }
        for (String t : types) {
            System.out.println(
                t + "Available: " + inventory.getCount(t) +
                "Price: ₹" + inventory.getPrice(t) +
                "Amenities: " + String.join(", ", inventory.getAmenities(t))
            );
        }
    }
}