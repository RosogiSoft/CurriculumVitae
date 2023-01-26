package com.example.curriculumvitae.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

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
    private String establishment;
    private String faculty;
    private String formOfStudy;
    private String yearOfEnding;
    private boolean[] competency = new boolean[15];
    private String specialytiInfo;
    private String[] softSkills;



    public Person() {

    }

    public void showInfo() {
        System.out.println("Итоговая инофрмация о человеке: ");
        System.out.println("Имя: " + this.name + ", Дата рождения: " + this.dateOfBirth);
        System.out.println("Номер телефона: " + this.phoneNumber + ", Номер телефона: " + this.mailAddress);
        System.out.println("Социальные сети: " + this.socialNetwork);
        System.out.println("Номер группы: " + this.groupNumber + "Специальность: " + this.speciality);
        System.out.println("Дополнительная информация: " + this.additionalInfo);
        System.out.println("Иностранный язык: " + this.foreignLanguage);
        System.out.println("Водительское удостоверение: " + this.driverLicense);
        System.out.println("Дополнительные навыки: " + this.additionalCompetencies);
        System.out.println("Социальные навыки: " + Arrays.toString(this.softSkills));


    }

    //Down bellow only setters and getters of Person fields

    public String getSpecialytiInfo() {
        return specialytiInfo;
    }

    public void setSpecialytiInfo(String specialytiInfo) {
        this.specialytiInfo = specialytiInfo;
    }
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

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFormOfStudy() {
        return formOfStudy;
    }

    public void setFormOfStudy(String formOfStudy) {
        this.formOfStudy = formOfStudy;
    }

    public String getYearOfEnding() {
        return yearOfEnding;
    }

    public void setYearOfEnding(String yearOfEnding) {
        this.yearOfEnding = yearOfEnding;
    }

    public boolean[] getCompetency() {
        return competency;
    }
    public void setCompetency(boolean[] competency) {
        this.competency = competency;
    }

    public String[] getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(String[] softSkills) {
        this.softSkills = softSkills;
    }


}
