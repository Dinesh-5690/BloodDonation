package com.blood.app;
import com.blood.bean.Donor;
import com.blood.service.BloodService;
import com.blood.util.AppointmentConflictException;
import com.blood.util.ValidationException;

public class BloodMain{
    private static BloodService service=new BloodService();
    public static void main(String[] args){
        java.util.Scanner sc=new java.util.Scanner(System.in);
        System.out.println("--- Blood Donation Camp Console ---\n");
        try{
            Donor d=new Donor();
            d.setDonorID("DN2015");
            d.setFullName("Dinesh S");
            d.setGender("MALE");
            d.setAge(20);
            d.setBloodGroup("O+");
            d.setLastDonationDate(null);
            d.setEligibleFlag("Y");
            d.setMobile("6374964500");
            d.setEmail("Dinesh@example.com");
            d.setCity("Salem");
            d.setStatus("ACTIVE");
            boolean ok=service.registerNewDonor(d);
            System.out.println(ok?"DONOR REGISTERED":"DONOR REGISTRATION FAILED");
        }catch(ValidationException e){
            System.out.println("Validation Error: "+e.toString());
        }catch(Exception e){
            System.out.println("System Error: "+e.getMessage());
        }
        try{
            java.sql.Date today=new java.sql.Date(System.currentTimeMillis());
            java.sql.Date apptDate=new java.sql.Date(System.currentTimeMillis()+7L*24*60*60*1000);
            boolean ok=service.scheduleAppointment("DN2015","City Hospital Camp","City Hospital, Main Hall",apptDate,"09:00-10:00",today,1);
            System.out.println(ok?"APPOINTMENT SCHEDULED":"APPOINTMENT SCHEDULING FAILED");
        }catch(AppointmentConflictException e){
            System.out.println("Appointment Conflict: "+e.toString());
        }catch(ValidationException e){
            System.out.println("Validation Error: "+e.toString());
        }catch(Exception e){
            System.out.println("System Error: "+e.getMessage());
        }
        sc.close();
    }
}