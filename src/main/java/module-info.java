module com.example.employeeattendance {
    requires javafx.controls;
    requires javafx.fxml;
<<<<<<< HEAD
    requires java.sql;
=======
>>>>>>> ddd1214 (Initial)

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.employeeattendance to javafx.fxml;
    exports com.example.employeeattendance;
    exports com.example.employeeattendance.Login;
    opens com.example.employeeattendance.Login to javafx.fxml;
    exports com.example.employeeattendance.Controller;
    opens com.example.employeeattendance.Controller to javafx.fxml;

    exports com.example.employeeattendance.models;
    opens com.example.employeeattendance.models to javafx.fxml;
}