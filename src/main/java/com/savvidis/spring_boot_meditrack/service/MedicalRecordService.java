package com.savvidis.spring_boot_meditrack.service;

import com.savvidis.spring_boot_meditrack.exception.MedicalRecordNotFoundException;
import com.savvidis.spring_boot_meditrack.model.MedicalRecord;
import com.savvidis.spring_boot_meditrack.repo.MedicalRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepo medicalRecordRepo;

    public List<MedicalRecord> getAllRecords(){
        return medicalRecordRepo.findAll();
    };

    public MedicalRecord getRecordById(int id) {
        return medicalRecordRepo.findById(id).orElseThrow(()->new MedicalRecordNotFoundException("Medical record not found"));
    }

    public List<MedicalRecord> getAllRecordsByPatientId(int patientId) {
        return medicalRecordRepo.findByPatientId(patientId);
    }

    public MedicalRecord addRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepo.save(medicalRecord);
    }

    public MedicalRecord updateRecord(int id, MedicalRecord medicalRecord)  {
        MedicalRecord existing = medicalRecordRepo.findById(id).orElseThrow(()->new MedicalRecordNotFoundException("Medical record not found"));
        if(existing!=null){
            existing.setDiagnosis(medicalRecord.getDiagnosis());
            existing.setDoctorName(medicalRecord.getDoctorName());
            existing.setTreatment(medicalRecord.getTreatment());
            existing.setVisitDate(medicalRecord.getVisitDate());
            return medicalRecordRepo.save(existing);
        }
        return null;
    }

    public void deleteById(int id) {
        medicalRecordRepo.deleteById(id);
    }
}
