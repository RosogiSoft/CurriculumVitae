package com.example.curriculumvitae.databaseModel;

import com.example.curriculumvitae.Person;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnect {

    private static final String url = "jdbc:mysql://45.10.43.194:3306/resume_database";
    private static final String user = "igorvasiltsev";
    private static final String password = "45034691";


    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void addData(Person person){
        String sqlQ =
                "INSERT INTO student(studentName, dateOfBirth, telephoneNumber, emailAdress, groupNumber,speciality)"
        + "VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = connect()) {
            PreparedStatement statementOne = conn.prepareStatement(sqlQ);
            statementOne.setString(1, person.getName());
            statementOne.setString(2, person.getDateOfBirth());
            statementOne.setString(3, person.getPhoneNumber());
            statementOne.setString(4, person.getMailAddress());
            statementOne.setString(5, person.getGroupNumber());
            statementOne.setString(6, person.getSpeciality());
            statementOne.addBatch();
            statementOne.executeBatch();
            //Test of image load in database
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void addDataPic(Person personPic){
        try(Connection conn = connect()){
            String sqlQuery = "INSERT INTO students_image(image) VALUES (?)";
            PreparedStatement statementTwo = conn.prepareStatement(sqlQuery);
            statementTwo.setBinaryStream(1,personPic.getImageFileStream(),
                    (int) personPic.getImageFile().length());
            statementTwo.addBatch();
            statementTwo.executeBatch();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
