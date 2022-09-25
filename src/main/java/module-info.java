module com.example.curriculumvitae {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.curriculumvitae to javafx.fxml;
    exports com.example.curriculumvitae;
    exports com.example.curriculumvitae.controllers;
    opens com.example.curriculumvitae.controllers to javafx.fxml;
}