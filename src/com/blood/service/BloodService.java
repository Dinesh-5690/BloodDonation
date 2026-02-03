package com.blood.service;
import java.sql.Connection;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.blood.bean.*;
import com.blood.dao.*;
import com.blood.util.*;

public class BloodService {
    private DonorDAO donorDAO=new DonorDAO();
    private DonationAppointmentDAO apptDAO=new DonationAppointmentDAO();
    public Donor viewDonorDetails(String donorID) throws Exception {
        if (donorID == null||donorID.trim().length()==0) {
            throw new ValidationException();
        }
        return donorDAO.findDonor(donorID);
    }
    public List<Donor> viewAllDonors() throws Exception {
        return donorDAO.viewAllDonors();
    }
    public boolean registerNewDonor(Donor donor) throws Exception {
        if (donor == null||isBlank(donor.getDonorID())||isBlank(donor.getFullName())||isBlank(donor.getGender())||isBlank(donor.getBloodGroup())||isBlank(donor.getMobile())) {
            throw new ValidationException();
        }
        if (donor.getAge() < 18 || donor.getAge() > 65) {
            throw new ValidationException();
        }
        if (!donor.getMobile().matches("\\d{8,15}")) {
            throw new ValidationException();
        }
        Set<String> groups=new HashSet<String>(Arrays.asList("A+","A-","B+","B-","O+","O-","AB+","AB-"));
        if (!groups.contains(donor.getBloodGroup().toUpperCase())) {
        	throw new ValidationException();
        }
        if (donorDAO.findDonor(donor.getDonorID()) != null) {
        	throw new ValidationException();
        }
        if (isBlank(donor.getStatus())) {
        	donor.setStatus("ACTIVE");
        }
        if (isBlank(donor.getEligibleFlag())) {
        	donor.setEligibleFlag("Y");
        }
        return donorDAO.insertDonor(donor);
    }

    public List<DonationAppointment> listAppointmentsByDonor(String donorID) throws Exception {
        return apptDAO.findAppointmentsByDonor(donorID);
    }

    public List<DonationAppointment> listAppointmentsByCampAndDate(
            String campName, Date appointmentDate) throws Exception {
        return apptDAO.findAppointmentsByCampAndDate(campName, appointmentDate);
    }

    public boolean scheduleAppointment(String donorID,String campName,String campLocation,Date appointmentDate,String timeSlot,Date registrationDate,int unitsPlanned)
            throws Exception {
        if (isBlank(donorID) || isBlank(campName) || isBlank(campLocation) ||isBlank(timeSlot) || appointmentDate == null ||registrationDate == null || unitsPlanned <= 0 ||registrationDate.after(appointmentDate))
            throw new ValidationException();
        Donor donor = donorDAO.findDonor(donorID);
        if (donor == null) {
        	return false;
        }
        if (!"ACTIVE".equalsIgnoreCase(donor.getStatus())||!"Y".equalsIgnoreCase(donor.getEligibleFlag())) {
        	return false;
        }
        if (donor.getLastDonationDate() != null) {
            long gap = ChronoUnit.DAYS.between(donor.getLastDonationDate().toLocalDate(),appointmentDate.toLocalDate());
            if (gap<90) {
            	throw new ValidationException();
            }
        }
        DonationAppointment conflict =apptDAO.findConflictingAppointment(donorID,appointmentDate,timeSlot);
        if (conflict != null)
            throw new AppointmentConflictException();

        Connection con=DBUtil.getDBConnection();
        try {
            con.setAutoCommit(false);
            int newID=apptDAO.generateAppointmentID();
            DonationAppointment appt=new DonationAppointment();
            appt.setAppointmentID(newID);
            appt.setDonorID(donorID);
            appt.setCampName(campName);
            appt.setCampLocation(campLocation);
            appt.setAppointmentDate(appointmentDate);
            appt.setTimeSlot(timeSlot);
            appt.setRegistrationDate(registrationDate);
            appt.setUnitsPlanned(unitsPlanned);
            appt.setUnitsCollected(0);
            appt.setAppointmentStatus("SCHEDULED");
            appt.setRemarks(null);
            boolean done=apptDAO.insertAppointment(appt);
            if (done) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (Exception e) {
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

    public boolean updateAppointmentStatus(int appointmentID,String newStatus,int unitsCollected,String remarks)throws Exception {
        if (appointmentID<=0||unitsCollected<0||isBlank(newStatus)||!(newStatus.equals("Scheduled")||newStatus.equals("Completed")||newStatus.equals("Canceled")||newStatus.equals("NO_SHOW"))) {
            throw new ValidationException();
        }
        DonationAppointment appt=apptDAO.findAppointment(appointmentID);
        if (appt==null) {
        	return false;
        }
        Connection con = DBUtil.getDBConnection();
        try {
            con.setAutoCommit(false);
            boolean cr=apptDAO.updateAppointmentStatusAndUnits(appointmentID,newStatus,unitsCollected,remarks);
            if (cr&&newStatus.equals("Completed")) {donorDAO.updateLastDonationDate(appt.getDonorID(),appt.getAppointmentDate());
            }
            if (cr) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (Exception e) {
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

    public boolean disableDonor(String donorID, Date referenceDate)throws Exception {
        if (isBlank(donorID)) {
        	throw new ValidationException();
        }
        List<DonationAppointment> list=apptDAO.findFutureScheduledAppointmentsForDonor(donorID,referenceDate);
        if (list != null && !list.isEmpty()) {
        	throw new ActiveAppointmentsExistException();
        }
        return donorDAO.updateDonorStatus(donorID,"Inactive");
    }
    private boolean isBlank(String s) {
        return s==null||s.trim().isEmpty();
    }
}
