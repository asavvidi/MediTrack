package com.savvidis.spring_boot_meditrack.controller;

import com.savvidis.spring_boot_meditrack.exception.MedicalRecordNotFoundException;
import com.savvidis.spring_boot_meditrack.exception.PatientNotFoundException;
import com.savvidis.spring_boot_meditrack.model.MedicalRecord;
import com.savvidis.spring_boot_meditrack.model.Patient;
import com.savvidis.spring_boot_meditrack.service.MedicalRecordService;
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
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private PatientService patientService;

    //Get all medical records
    @GetMapping("/medical-records")
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords(){
        return new ResponseEntity<>(medicalRecordService.getAllRecords(), HttpStatus.OK);
    }

    //Get a medical record based on id
    @GetMapping("medical-records/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable("id") int id){
       MedicalRecord medicalRecord=null;
       try{
           medicalRecord=medicalRecordService.getRecordById(id);
           return new ResponseEntity<>(medicalRecord,HttpStatus.OK);
       }catch (MedicalRecordNotFoundException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    //Get all the medical records for a patient
    @GetMapping("/medical-records/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getAllRecordsByPatientId(@PathVariable("patientId")int patientId){
        return new ResponseEntity<>(medicalRecordService.getAllRecordsByPatientId(patientId),HttpStatus.OK);
    }

    //Create a new medical record for a patient
    @PostMapping("/medical-records")
    public ResponseEntity<?> addRecord(@RequestBody MedicalRecord medicalRecord){
        Patient patient=null;
        try{
            patient =patientService.getPatientById(medicalRecord.getPatient().getId());
        }catch (PatientNotFoundException e){
            return new ResponseEntity<>("Patient not found",HttpStatus.NOT_FOUND);
        }
        medicalRecord.setPatient(patient);
        MedicalRecord saved =medicalRecordService.addRecord(medicalRecord);
        return new ResponseEntity<>(saved,HttpStatus.OK);
    }

    @PutMapping("/medical-records/{id}")
    public ResponseEntity<?> updateRecord(@PathVariable("id")int id, @RequestBody MedicalRecord medicalRecord){
        MedicalRecord updatedRecord = null;
        try{
            updatedRecord = medicalRecordService.updateRecord(id,medicalRecord);
            return new ResponseEntity<>(updatedRecord,HttpStatus.OK);
        }catch(MedicalRecordNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/medical-records/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable("id") int id){
        MedicalRecord record =null;
        try{
            record=medicalRecordService.getRecordById(id);
            medicalRecordService.deleteById(id);
            return new ResponseEntity<>("Medical record deleted",HttpStatus.OK);
        }catch (MedicalRecordNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }




}
