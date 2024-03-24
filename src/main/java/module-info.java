module com.example.employeeattendance {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.employeeattendance to javafx.fxml;
    exports com.example.employeeattendance;

    //Display & run file fxml: LoginScreen
    exports LoginScreen;
    opens LoginScreen to javafx.fxml;

    //Display & run file fxml: AdminScreen
    exports Admin;
    opens Admin to javafx.fxml;
}