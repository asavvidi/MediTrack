package com.savvidis.spring_boot_meditrack.repo;

import com.savvidis.spring_boot_meditrack.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicalRecordRepo extends JpaRepository<MedicalRecord,Integer> {

    List<MedicalRecord> findByPatientId(int patientId);
}
