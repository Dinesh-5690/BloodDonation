package com.blood.bean;
import java.sql.Date;

public class DonationAppointment {

    private int appointmentID;
    private String donorID;
    private String campName;
    private String campLocation;
    private Date appointmentDate;
    private String timeSlot;
    private Date registrationDate;
    private int unitsPlanned;
    private int unitsCollected;
    private String appointmentStatus;
    private String remarks;

    public DonationAppointment(){
    }
    public DonationAppointment(int appointmentID,String donorID,String campName,String campLocation,Date appointmentDate,String timeSlot,Date registrationDate,int unitsPlanned,int unitsCollected,String appointmentStatus,String remarks){
        this.appointmentID=appointmentID;
        this.donorID=donorID;
        this.campName=campName;
        this.campLocation=campLocation;
        this.appointmentDate=appointmentDate;
        this.timeSlot=timeSlot;
        this.registrationDate=registrationDate;
        this.unitsPlanned=unitsPlanned;
        this.unitsCollected=unitsCollected;
        this.appointmentStatus=appointmentStatus;
        this.remarks=remarks;
    }

    public int getAppointmentID(){
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID){
        this.appointmentID=appointmentID;
    }

    public String getDonorID(){
        return donorID;
    }

    public void setDonorID(String donorID){
        this.donorID=donorID;
    }

    public String getCampName(){
        return campName;
    }

    public void setCampName(String campName){
        this.campName=campName;
    }

    public String getCampLocation(){
        return campLocation;
    }

    public void setCampLocation(String campLocation){
        this.campLocation=campLocation;
    }

    public Date getAppointmentDate(){
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate){
        this.appointmentDate=appointmentDate;
    }

    public String getTimeSlot(){
        return timeSlot;
    }
    
    public void setTimeSlot(String timeSlot){
        this.timeSlot=timeSlot;
    }
    
    public Date getRegistrationDate(){
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate){
        this.registrationDate=registrationDate;
    }
    
    public int getUnitsPlanned(){
        return unitsPlanned;
    }
    
    public void setUnitsPlanned(int unitsPlanned){
        this.unitsPlanned=unitsPlanned;
    }
    
    public int getUnitsCollected(){
        return unitsCollected;
    }
    
    public void setUnitsCollected(int unitsCollected){
        this.unitsCollected=unitsCollected;
    }
    
    public String getAppointmentStatus(){
        return appointmentStatus;
    }
    
    public void setAppointmentStatus(String appointmentStatus){
        this.appointmentStatus=appointmentStatus;
    }
    
    public String getRemarks(){
        return remarks;
    }
    
    public void setRemarks(String remarks){
        this.remarks=remarks;
    }
}
