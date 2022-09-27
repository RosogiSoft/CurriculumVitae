package com.example.curriculumvitae.databaseModel;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnect {

    private static final String url = "jdbc:mysql://45.10.43.194:3306/resume_database";
    private static final String user = "igorvasiltsev";
    private static final String password = "45034691";


    public static void testConnection(){
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            System.out.println("Connected to Database");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testConnection();
    }
}
