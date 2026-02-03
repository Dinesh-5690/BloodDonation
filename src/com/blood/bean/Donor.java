package com.blood.bean;
import java.sql.Date;

public class Donor {
    private String donorID;
    private String fullName;
    private String gender;
    private int age;
    private String bloodGroup;
    private Date lastDonationDate;
    private String eligibleFlag;
    private String mobile;
    private String email;
    private String city;
    private String status;
    public Donor(){
    }
    public Donor(String donorID,String fullName,String gender,int age,String bloodGroup,Date lastDonationDate,String eligibleFlag,String mobile,
                 String email,String city,String status){
        this.donorID=donorID;
        this.fullName=fullName;
        this.gender=gender;
        this.age=age;
        this.bloodGroup=bloodGroup;
        this.lastDonationDate=lastDonationDate;
        this.eligibleFlag=eligibleFlag;
        this.mobile=mobile;
        this.email=email;
        this.city=city;
        this.status=status;
    }
    
    public String getDonorID(){
        return donorID;
    }
    
    public void setDonorID(String donorID){
        this.donorID=donorID;
    }
    
    public String getFullName(){
        return fullName;
    }
    
    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    
    public String getGender(){
        return gender;
    }
    
    public void setGender(String gender){
        this.gender=gender;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setAge(int age){
        this.age=age;
    }
    
    public String getBloodGroup(){
        return bloodGroup;
    }
    
    public void setBloodGroup(String bloodGroup){
        this.bloodGroup=bloodGroup;
    }
    
    public Date getLastDonationDate(){
        return lastDonationDate;
    }
    
    public void setLastDonationDate(Date lastDonationDate){
        this.lastDonationDate=lastDonationDate;
    }
    
    public String getEligibleFlag(){
        return eligibleFlag;
    }
    
    public void setEligibleFlag(String eligibleFlag){
        this.eligibleFlag=eligibleFlag;
    }
    
    public String getMobile(){
        return mobile;
    }
    
    public void setMobile(String mobile){
        this.mobile=mobile;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getCity(){
        return city;
    }
    
    public void setCity(String city){
        this.city=city;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status=status;
    }
}
