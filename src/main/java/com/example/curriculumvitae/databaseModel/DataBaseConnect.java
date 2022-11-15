package com.example.curriculumvitae.databaseModel;

import com.example.curriculumvitae.Person;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnect {

    private static final String url = "jdbc:mysql://45.10.43.194:3306/RESUME";
    private static final String user = "igorvasiltsev";
    private static final String password = "45034691";


    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void addData(Person person){
        String sqlQ =
                "INSERT INTO Student" +
                        "(STUDENTNAME, DATEOFBIRTH, GROUPNUMBER, SPECIALTYCODE, " +
                        "TELEPHONENUMBER, EMAILADDRESS)"
        + "VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection conn = connect()) {
            PreparedStatement statementOne = conn.prepareStatement(sqlQ);
            statementOne.setString(1, person.getName());
            statementOne.setString(2, person.getDateOfBirth());
            statementOne.setString(3, person.getGroupNumber());
            statementOne.setInt(4, person.getSpecialityCode());
            statementOne.setString(5, person.getPhoneNumber());
            statementOne.setString(6, person.getMailAddress());
            statementOne.addBatch();
            statementOne.executeBatch();
            //Test of image load in database
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void addDataPic(Person personPic){
        try(Connection conn = connect()){
            String sqlQuery = "INSERT INTO Student_Photo(PHOTO) VALUES (?)";
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

    public static ArrayList<String> getSpecializationData(){
        ArrayList<String> namesArrayList = new ArrayList<>();
        String sqlQuery = "SELECT SPECIALTYNAME FROM Specialty_Code";
        try(Connection conn = connect()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()){
                namesArrayList.add(rs.getString("SPECIALTYNAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return namesArrayList;
    }

    public static ArrayList<String> getSpecializationCheckBox(){
        ArrayList<String> data = null;
        String query = "";
        return data;
    }

    public int setSpecialityCodeFromDb(){
        return 0;
    }

    public static void connClose() throws SQLException {
        connect().close();
    }
}
