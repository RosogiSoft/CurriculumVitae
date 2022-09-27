package com.example.curriculumvitae;

public class Person {
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String mailAddress;
    private String groupNumber;
    private String speciality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Person(){

    }

    //Реализовать метод "Выплевывания" значения в JSON/XML/etc. для записи в БД в отдельном классе

    public void printData(){
        System.out.println(this.name);
        System.out.println(this.dateOfBirth);
        System.out.println(this.phoneNumber);
        System.out.println(this.mailAddress);
        System.out.println(this.groupNumber);
        System.out.println(this.speciality);
    }

    public String returnData(String name){
        return this.name;
    }

}
