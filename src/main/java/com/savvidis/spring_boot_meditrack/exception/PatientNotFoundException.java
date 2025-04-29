package com.savvidis.spring_boot_meditrack.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String message){
        super(message);
    }
}
