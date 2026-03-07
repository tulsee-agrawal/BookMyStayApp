package com.bookmystay.service;

import com.bookmystay.exception.InvalidInputException;
import com.bookmystay.model.Reservation;

import java.util.*;

public class AllocationService {

    private final Set<String> bookedRoomIds = new HashSet<>();

    private final Map<String, Set<String>> assignedByType = new HashMap<>();

    // incremental room IDs
    private final Map<String, Integer> typeCounters = new HashMap<>();

    // type prefix map
    private final Map<String, String> typePrefixes = new HashMap<>();

    public AllocationService() {
        typePrefixes.put("Single", "S");
        typePrefixes.put("Double", "D");
        typePrefixes.put("Suite",  "SU");
    }

  
    public List<String> confirm(Reservation r, InventoryService inv) {
        if (r == null) throw new InvalidInputException("Reservation cannot be null");

        String type = r.getRoomType();
        int qty = r.getQuantity();
        if (type == null || type.trim().isEmpty()) {
            throw new InvalidInputException("Room type cannot be empty");
        }
        if (qty <= 0) {
            throw new InvalidInputException("Quantity must be > 0");
        }

        inv.reserve(type, qty);
        List<String> assigned = new ArrayList<>();
        for (int i = 0; i < qty; i++) {
            String id = nextRoomId(type); //generate id
    
            bookedRoomIds.add(id);
            
            assignedByType.computeIfAbsent(type, k -> new HashSet<>()).add(id);
            assigned.add(id);
        }
        return assigned;
    }


    private String nextRoomId(String type) {
        String prefix = typePrefixes.get(type);
        int next = typeCounters.getOrDefault(type, 0) + 1;
        typeCounters.put(type, next);
        return String.format("%s-%03d", prefix, next);
    }

    public boolean isBookedId(String roomId) {
        return bookedRoomIds.contains(roomId);
    }

    public Set<String> getAssignedForType(String type) {
        Set<String> set = assignedByType.get(type);
        return (set == null) ? new HashSet<>() : new HashSet<>(set); // return a copy
    }

    public Set<String> getAllBookedIds() {
        return new HashSet<>(bookedRoomIds); // copy
    }
}