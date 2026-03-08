# UC‑3: Booking Request (First‑Come‑First‑Served)
## Overview
- Handles booking requests using a simple FIFO queue to ensure fair, ordered processing during high‑traffic periods.
## Data Structures

- Queue<Reservation> → stores booking requests in arrival order

## Features

- Accept booking requests
- Maintain strict first‑come‑first‑served processing
- Ensure fairness during peak demand
- Prevent request‑level race conditions

## Flow

- Receive booking request
- Add to FIFO queue
- Process next request
- Confirm or reject based on availability
