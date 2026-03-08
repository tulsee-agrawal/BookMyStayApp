# UC‑6: Booking History & Reporting
## Overview
Maintains a complete history of confirmed and canceled reservations for audits, customer support, and reporting.
## Data Structures

- List<Reservation> → stores confirmed reservations
- List<Reservation> → stores canceled reservations

# Features

- Record confirmed bookings
- Store canceled reservations
- Retrieve full booking history
- Generate simple activity reports

## Flow

- Confirm reservation
- Add to history list
- (Optional) Cancel reservation
- Generate reports when required
