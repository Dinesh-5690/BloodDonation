package com.blood.util;

public class AppointmentConflictException extends Exception{
    @Override
    public String toString(){
        return "Appointment Invalid:Already someone appointment at same time";
    }
}
