# UC‑5: Add‑On Service Selection
## Overview
Allows guests to attach optional services (breakfast, spa, pickup) to their reservation using a simple one‑to‑many mapping.
## Data Structures

Map<Reservation, List<Service>> → reservation → attached services

## Features

- Add one or multiple services to a reservation
- Store service details (name + cost)
- Remove services if needed
- Calculate total add‑on cost

## Flow

- Select desired service
- Add to reservation’s service list
- Map reservation to services
- Retrieve and compute final add‑on charges
