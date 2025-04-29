package com.savvidis.spring_boot_meditrack.repo;

import com.savvidis.spring_boot_meditrack.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer> {
    //List<Patient> searchPatients(String keyword);
}
