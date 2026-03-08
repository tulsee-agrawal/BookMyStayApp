# UC‑2: Room Search & Availability Check
## Overview
Provides read‑only search features to view available rooms, pricing, and amenities without modifying inventory.
## Data Structures

HashMap<String, Integer> → room type → available count
HashMap<String, Double> → room type → price per night
HashMap<String, List<String>> → room type → amenities

## Features

- Show available room types
- Display prices and amenities
- Validate if a room type can be booked
- Keep search operations read‑only

## Flow

- Receive search request
- Read data from HashMaps
- Filter types with availability > 0
- Display availability, price, and amenities
