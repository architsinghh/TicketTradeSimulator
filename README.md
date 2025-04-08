# TicketTradeSimulator

## Project Overview

A Java-based simulation of ticket trading operations across multiple exhibits and agents, designed to model real-time scheduling, concurrency, and data-driven decision-making.

This project demonstrates proficiency in:

- Multithreading and concurrency
- File I/O (CSV and JSON parsing)
- Custom class design and object modeling
- Event-driven simulation logic
- Java collections and data structure management

---

## Key Components

### 🧠 Exhibit & Task Management

- Representation of real-world exhibit trading operations
- Task scheduling and assignment based on CSV input
- Dynamic allocation of agents to exhibits

### 🔄 Concurrency Control

- **AgentSemaphore** class for controlling agent access using semaphores
- Thread-safe trade execution logic
- Simultaneous processing of multiple trade tasks

### 📂 Data Parsing & Integration

- **CSV parsing** using `opencsv`
- **JSON parsing** with `Gson` for exhibit data
- Automatic ingestion of exhibit and schedule files

---

## Technical Highlights

### Libraries & Tools

- [`Gson`](https://github.com/google/gson) — for JSON parsing
- [`OpenCSV`](http://opencsv.sourceforge.net/) — for CSV file parsing
- [`Apache Commons Lang/Text`](https://commons.apache.org/) — for utility support

### Multithreading

- Executors for managing thread pools
- Trade operations executed in parallel
- Race condition prevention with agent locks
