package com.savvidis.spring_boot_meditrack.exception;

public class MedicalRecordNotFoundException extends RuntimeException{
    public MedicalRecordNotFoundException(String message){
        super(message);
    }
}
