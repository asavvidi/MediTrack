# MediTrack 🏥

MediTrack is a medical record management system that allows users to manage patient profiles and their corresponding medical history. It's designed as a RESTful API using Spring Boot and can be used with frontend applications or testing tools like Postman.

## Overview

MediTrack lets users perform CRUD operations on patients and medical records, associate records with specific patients, and handle data with meaningful error messages. It is built with:

- **Spring Boot** for the backend
- **Spring Data JPA** for ORM and database interaction
- **Postgresql** as the database 


## Features

- **Add & Manage Patients:** Create, update, fetch, or delete patient profiles.
- **Medical Records Management:** Assign and maintain medical records for patients.
- **Relational Mapping:** Each medical record is linked to a patient using JPA.
- **Exception Handling:** Custom exceptions like `PatientNotFoundException` and `MedicalRecordNotFoundException` for better error feedback.

## Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **Postgresql**

## Endpoints Summary

### 🧑‍⚕️ Patient Endpoints

| Method | Endpoint                 | Description                 |
|--------|--------------------------|-----------------------------|
| GET    | `/api/patients`          | Retrieve all patients       |
| GET    | `/api/patient/{id}`      | Get a specific patient by ID|
| POST   | `/api/patient`           | Create a new patient        |
| PUT    | `/api/patient/{id}`      | Update patient information  |
| DELETE | `/api/patient/{id}`      | Delete a patient            |

### 📋 Medical Record Endpoints

| Method | Endpoint                                      | Description                            |
|--------|-----------------------------------------------|----------------------------------------|
| GET    | `/api/medical-records`                        | Retrieve all medical records           |
| GET    | `/api/medical-records/{id}`                   | Get a medical record by ID             |
| GET    | `/api/medical-records/patient/{patientId}`    | Get all medical records for a patient  |
| POST   | `/api/medical-records`                        | Add a new medical record               |
| PUT    | `/api/medical-records/{id}`                   | Update an existing medical record      |
| DELETE | `/api/medical-records/{id}`                   | Delete a medical record      