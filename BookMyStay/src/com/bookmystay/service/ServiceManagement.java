package com.bookmystay.service;

import com.bookmystay.exception.InvalidInputException;
import com.bookmystay.model.Reservation;
import com.bookmystay.model.Service;

import java.util.*;

/**
 * UC-5: Manage add-on services per Reservation.
 * - Map<Reservation, List<Service>>
 * - Add, remove, list, and compute total add-on cost
 */
public class ServiceManagement {

    private final Map<Reservation, List<Service>> reservationServices = new HashMap<>();

    public void addService(Reservation reservation, Service service) {
        validate(reservation, service);
        reservationServices
                .computeIfAbsent(reservation, k -> new ArrayList<>())
                .add(service);
    }


    public void addServices(Reservation reservation, List<Service> services) {
        if (services == null || services.isEmpty()) return;
        for (Service s : services) {
            addService(reservation, s);
        }
    }

    public boolean removeService(Reservation reservation, String serviceName) {
        if (reservation == null || serviceName == null) return false;
        List<Service> list = reservationServices.get(reservation);
        if (list == null) return false;

        return list.removeIf(s -> serviceName.equals(s.getName()));
    }
    
    public List<Service> getServices(Reservation reservation) {
        List<Service> list = reservationServices.get(reservation);
        return (list == null) ? new ArrayList<>() : new ArrayList<>(list);
    }

    public double getTotalAddOnCost(Reservation reservation) {
        List<Service> list = reservationServices.get(reservation);
        if (list == null) return 0.0;

        double sum = 0.0;
        for (Service s : list) {
            sum += s.getPrice();
        }
        return sum;
    }

    public void printServices(Reservation reservation) {
        List<Service> list = getServices(reservation);
        System.out.println("\n=== Services for Reservation ===");
        System.out.println(reservation);
        if (list.isEmpty()) {
            System.out.println("(No add-on services)");
        } else {
            for (Service s : list) {
                System.out.println("- " + s);
            }
            System.out.println("Add-on Total: ₹" + getTotalAddOnCost(reservation));
        }
    }

    private void validate(Reservation reservation, Service service) {
        if (reservation == null) {
            throw new InvalidInputException("Reservation cannot be null");
        }
        if (service == null) {
            throw new InvalidInputException("Service cannot be null");
        }
        if (service.getName() == null || service.getName().trim().isEmpty()) {
            throw new InvalidInputException("Service name cannot be empty");
        }
        if (service.getPrice() < 0) {
            throw new InvalidInputException("Service price cannot be negative");
        }
    }
}