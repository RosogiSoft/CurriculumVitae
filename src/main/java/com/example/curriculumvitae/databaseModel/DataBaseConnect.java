package com.example.curriculumvitae.databaseModel;

import com.example.curriculumvitae.Person;

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
            PreparedStatement statement = conn.prepareStatement(sqlQ);
            statement.setString(1, person.getName());
            statement.setString(2, person.getDateOfBirth());
            statement.setString(3, person.getPhoneNumber());
            statement.setString(4, person.getMailAddress());
            statement.setString(5, person.getGroupNumber());
            statement.setString(6, person.getSpeciality());
            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
