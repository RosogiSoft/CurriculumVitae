package com.example.curriculumvitae.databaseModel;

import com.example.curriculumvitae.helper.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

public class DataBaseConnect {

    private static String url;
    private static String user;
    private static String password;
    private static final Logger logger = Logger.getLogger(DataBaseConnect.class.getName());


    public static void getConnectionData(){
        FileInputStream fileInputStream;
        Properties properties = new Properties();

        try {
            fileInputStream = new FileInputStream("src/main/resources/com/example/curriculumvitae/connection.properties");
            properties.load(fileInputStream);

            url = properties.getProperty("db.host");
            user = properties.getProperty("db.login");
            password = properties.getProperty("db.password");

            logger.info("Host: " + url);
            logger.info("Login: " + user);
            logger.info("Password: " + password);
        } catch (FileNotFoundException e) {
            logger.info("Файл конфигурации отсутсвует!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.info("Ошибка при загрузке конфигурационного файла!");
            throw new RuntimeException(e);
        }
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void addData(Person person) {
        String sqlQ =
                "INSERT INTO Student" +
                        "(STUDENTNAME, DATEOFBIRTH, GROUPNUMBER, SPECIALTYCODE, " +
                        "TELEPHONENUMBER, EMAILADDRESS)"
                        + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect()) {
            PreparedStatement statementOne = conn.prepareStatement(sqlQ);
            statementOne.setString(1, person.getName());
            statementOne.setString(2, person.getDateOfBirth());
            statementOne.setString(3, person.getGroupNumber());
            statementOne.setInt(4, person.getSpecialityCode());
            statementOne.setString(5, person.getPhoneNumber());
            statementOne.setString(6, person.getMailAddress());
            statementOne.addBatch();
            statementOne.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDataPic(Person personPic) {
        try (Connection conn = connect()) {
            String sqlQ = "INSERT INTO Student_Photo(PHOTO) VALUES (?)";
            PreparedStatement statementTwo = conn.prepareStatement(sqlQ);
            statementTwo.setBinaryStream(1, personPic.getImageFileStream(),
                    (int) personPic.getImageFile().length());
            statementTwo.addBatch();
            statementTwo.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getSpecializationData() {
        ArrayList<String> namesArrayList = new ArrayList<>();
        String sqlQ = "SELECT SPECIALTYNAME FROM Specialty_Code ORDER  BY SPECIALTYNAME ASC";
        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQ);
            while (rs.next()) {
                namesArrayList.add(rs.getString("SPECIALTYNAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return namesArrayList;
    }

    public static ArrayList<String> getSpecializationCheckBox(int specCode) {
        ArrayList<String> data = new ArrayList<>();
        String sqlQ = String.format(
                "SELECT COMPETENCE1, COMPETENCE2, COMPETENCE3, COMPETENCE4, COMPETENCE5, COMPETENCE6, " +
                        "COMPETENCE7, COMPETENCE8, COMPETENCE9, COMPETENCE10, COMPETENCE11, COMPETENCE12, " +
                        "COMPETENCE13, " +
                        "COMPETENCE14, COMPETENCE15 FROM Decoding_Competency_Full WHERE SPECIALTYCODE = %d", specCode
        );
        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQ);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                    data.add(rs.getString(String.format("COMPETENCE%s", i)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static int setSpecialityCodeFromDb(String getSpecialityName) {
        String sqlQ = String.format(
                "SELECT SPECIALTYCODE FROM Specialty_Code WHERE SPECIALTYNAME= '%s'", getSpecialityName
        );
        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQ);
            if (rs.next()) {
                return rs.getInt("SPECIALTYCODE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static byte[] getUserPicFromDataBase(int id) {
        String sqlQ = String.format(
                "SELECT PHOTO FROM Student_Photo WHERE id = %d", id
        );
        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQ);
            if (rs.next()) {
                return rs.getBytes("PHOTO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static int getUserLastId(){
        String sqlQ = "SELECT ID FROM RESUME.Student ORDER BY ID DESC LIMIT 1";
        try(Connection conn = connect()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQ);
            if (rs.next()){
                return rs.getInt("ID") - 1;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static ArrayList<String> getSoftSkills() {
        ArrayList<String> softSkills = new ArrayList<>();
        String sqlQ = "SELECT SOFTSKILLS FROM RESUME.Soft_Skills";
        try(Connection conn = connect()){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQ);
            while (rs.next()){
                softSkills.add(rs.getString("SOFTSKILLS"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return softSkills;
    }
}
