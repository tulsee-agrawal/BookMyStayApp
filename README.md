# UC‑4: Reservation Confirmation & Room Allocation
## Overview
Assigns unique room IDs during confirmation and updates inventory to guarantee zero double‑booking.
## Data Structures

- Set<String> → global list of assigned room IDs
- HashMap<String, Integer> → room type → next room number
- HashMap<String, Set<String>> → room type → assigned room IDs

## Features

- Confirm reservations
- Generate unique room IDs (e.g., S‑001, D‑002)
- Prevent reuse of allocated IDs
- Update inventory immediately after confirmation

## Flow

- Receive confirmed reservation
- Reserve rooms from inventory
- Generate unique room ID(s)
- Store assigned IDs
- Return assigned IDs to caller
