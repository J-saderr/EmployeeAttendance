module com.example.employeeattendance {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.employeeattendance to javafx.fxml;
    exports com.example.employeeattendance;
}