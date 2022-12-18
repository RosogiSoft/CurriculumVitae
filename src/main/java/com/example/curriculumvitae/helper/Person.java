package com.example.curriculumvitae.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Person {
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String mailAddress;
    private String groupNumber;
    private String speciality;
    private File image;
    private int specialityCode;
    private String additionalInfo;
    private String foreignLanguage;
    private String driverLicense;
    private String additionalCompetencies;
    private String socialNetwork;



    public Person() {

    }

    public void printData() {
        System.out.println(this.name);
        System.out.println(this.dateOfBirth);
        System.out.println(this.phoneNumber);
        System.out.println(this.mailAddress);
        System.out.println(this.groupNumber);
        System.out.println(this.speciality);
        System.out.println(this.additionalInfo);
        System.out.println(this.foreignLanguage);
        System.out.println(this.driverLicense);
        System.out.println(this.additionalCompetencies);
        System.out.println(this.socialNetwork);

    }

    //Down bellow only setters and getters of Person fields
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

    public File getImageFile() {
        return image;
    }

    public FileInputStream getImageFileStream() throws FileNotFoundException {
        return new FileInputStream(this.image);
    }

    public void setImage(File image) {

        this.image = image;
    }

    public int getSpecialityCode() {
        return specialityCode;
    }

    public void setSpecialityCode(int specialityCode) {
        this.specialityCode = specialityCode;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getAdditionalCompetencies() {
        return additionalCompetencies;
    }

    public void setAdditionalCompetencies(String additionalCompetencies) {
        this.additionalCompetencies = additionalCompetencies;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

}
