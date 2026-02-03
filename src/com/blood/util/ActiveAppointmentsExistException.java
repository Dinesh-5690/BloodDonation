package com.blood.util;

public class ActiveAppointmentsExistException extends Exception {
    @Override
    public String toString() {
        return "Cannot disable donor:already Scheduled Appointments Exist";
    }
}
