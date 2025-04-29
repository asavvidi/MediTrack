package com.savvidis.spring_boot_meditrack.controller;


import com.savvidis.spring_boot_meditrack.exception.PatientNotFoundException;
import com.savvidis.spring_boot_meditrack.model.Patient;
import com.savvidis.spring_boot_meditrack.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class PatientController {

    @Autowired
    private PatientService patientService;

    //GET all the patients
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getPatients(){
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    //GET a patient with the id
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getPatientById(@PathVariable("patientId") int patientId){
        Patient patient=null;
        try{
            patient=patientService.getPatientById(patientId);
            return new ResponseEntity<>(patient,HttpStatus.OK);
        }catch(PatientNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }


    }

    //CREATE a new patient
    @PostMapping("/patient")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient){
        Patient savedPatient=null;
        try{
            savedPatient = patientService.addPatient(patient);
            return new ResponseEntity<>(savedPatient,HttpStatus.CREATED);
        }catch(IOException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //UPDATE a patient
    @PutMapping("/patient/{patientId}")
    public ResponseEntity<?> updatePatient(@PathVariable("patientId") int patientId,@RequestBody Patient patient){
        Patient updatedPatient =null;
        try{
            updatedPatient=patientService.updatePatient(patientId,patient);
            return new ResponseEntity<>("Patient updated",HttpStatus.OK);
        }catch(IOException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //DELETE a patient
    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable("patientId") int patientId){
        Patient patient = null;
        try{
            patient=patientService.getPatientById(patientId);
            patientService.deletePatient(patientId);
            return new ResponseEntity<>("Patient deleted",HttpStatus.OK);
        }catch (PatientNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    //Search for a patient through a keyword. That keyword can match multiple patient records
//    @GetMapping("/patient/search")
//    public ResponseEntity<List<Patient>> searchPatient(@RequestParam String keyword){
//        List<Patient> patients = patientService.searchPatients(keyword);
//        return new ResponseEntity<>(patients,HttpStatus.OK);
//    }


}
