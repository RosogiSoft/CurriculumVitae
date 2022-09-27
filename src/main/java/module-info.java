module com.example.curriculumvitae {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.example.curriculumvitae;
    opens com.example.curriculumvitae to javafx.fxml;
}