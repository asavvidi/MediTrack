package com.savvidis.spring_boot_meditrack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patient;
    private String diagnosis;
    private String treatment;
    private String doctorName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date visitDate;
}
