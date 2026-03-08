package com.bookmystay.service;

import com.bookmystay.model.Reservation;

import java.util.*;

/**
 * Use Case 6: Booking History & Reporting
 *
 * Stores confirmed and canceled reservations in simple ordered lists.
 * Provides basic reporting: totals, per-type counts, and recent activity.
 */
public class HistoryService {

    private final List<Reservation> confirmed = new ArrayList<>();
    private final List<Reservation> canceled  = new ArrayList<>();

    public void recordConfirmation(Reservation reservation) {
        if (reservation != null) {
            confirmed.add(reservation);
        }
    }
    public void recordCancellation(Reservation reservation) {
        if (reservation != null) {
            canceled.add(reservation);
        }
    }
    public List<Reservation> getAllConfirmed() {
        return new ArrayList<>(confirmed);
    }

    public List<Reservation> getAllCanceled() {
        return new ArrayList<>(canceled);
    }

    private Map<String, Integer> countByType(List<Reservation> list) {
        Map<String, Integer> map = new HashMap<>();
        for (Reservation r : list) {
            String type = r.getRoomType();
            map.put(type, map.getOrDefault(type, 0) + 1);
        }
        return map;
    }

  
    public void printReport() {
        System.out.println("\n=== BOOKING HISTORY REPORT ===");

        System.out.println("\n-- Totals --");
        System.out.println("Confirmed: " + confirmed.size());
        System.out.println("Canceled : " + canceled.size());

        System.out.println("\n-- Confirmed by Room Type --");
        Map<String, Integer> confByType = countByType(confirmed);
        if (confByType.isEmpty()) {
            System.out.println("(none)");
        } else {
            for (Map.Entry<String, Integer> e : confByType.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
        }

        System.out.println("\n-- Canceled by Room Type --");
        Map<String, Integer> cancByType = countByType(canceled);
        if (cancByType.isEmpty()) {
            System.out.println("(none)");
        } else {
            for (Map.Entry<String, Integer> e : cancByType.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
        }
    }

  

}