module com.example.curriculumvitae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports com.example.curriculumvitae;
    opens com.example.curriculumvitae to javafx.fxml;
    exports com.example.curriculumvitae.helper;
    opens com.example.curriculumvitae.helper to javafx.fxml;
}