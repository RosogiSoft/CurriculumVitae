module com.example.curriculumvitae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.curriculumvitae to javafx.fxml;
    exports com.example.curriculumvitae;
}