module com.example.curriculumvitae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires docx4j;
    requires java.xml.bind;

    exports com.example.curriculumvitae;
    opens com.example.curriculumvitae to javafx.fxml;
    exports com.example.curriculumvitae.helper;
    opens com.example.curriculumvitae.helper to javafx.fxml;
}