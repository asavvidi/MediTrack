package com.savvidis.spring_boot_meditrack.service;


import com.savvidis.spring_boot_meditrack.exception.PatientNotFoundException;
import com.savvidis.spring_boot_meditrack.model.Patient;
import com.savvidis.spring_boot_meditrack.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;


    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient getPatientById(int patientId) {
        return patientRepo.findById(patientId).orElseThrow(()-> new PatientNotFoundException("Patient not found"));
    }

    public Patient addPatient(Patient patient) throws IOException {
        return patientRepo.save(patient);
    }

    public Patient updatePatient(int patientId,Patient patient) throws IOException{
        Patient existing = patientRepo.findById(patientId).orElseThrow(()->new PatientNotFoundException("Patient not found"));
        if(existing!=null){
            existing.setAddress(patient.getAddress());
            existing.setEmail(patient.getEmail());
            existing.setFirstName(patient.getFirstName());
            existing.setLastName(patient.getLastName());
            existing.setPhoneNumber(patient.getPhoneNumber());
            existing.setDateOfBirth(patient.getDateOfBirth());

            return patientRepo.save(existing);
        }
        return null;
    }

    public void deletePatient(int patientId) {
        patientRepo.deleteById(patientId);
    }


//    public List<Patient> searchPatients(String keyword) {
//        return patientRepo.searchPatients(keyword);
//    }
}
