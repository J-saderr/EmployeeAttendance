package com.example.employeeattendance.Login;

import com.example.employeeattendance.getData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.employeeattendance.Controller.MainController.connectDb;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_userid;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private Button ButtonLogin;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void login() {

        alertMessage alert = new alertMessage();

        if (login_userid.getText().isEmpty() || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {
            String selectData1 = "SELECT * FROM logininfo WHERE "

                    + "User_id = ? and Password = ? and Position = 'User'"; //Account user
            String selectData2 = "SELECT * FROM logininfo WHERE "
                    + "User_id = ? and Password = ? and Position = 'Admin'"; //Account admin

            connect = connectDb();

            if(login_selectShowPassword.isSelected()){
                login_password.setText(login_showPassword.getText());
            }else{
                login_showPassword.setText(login_password.getText());
            }

            try {
                prepare = connect.prepareStatement(selectData1);
                prepare.setString(1, login_userid.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {

                    getData.userid = login_userid.getText();

                    alert.successMessage("Successfully Login!");
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/employeeattendance/home-view.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();

                    ButtonLogin.getScene().getWindow().hide();
                }
                else {
                    prepare = connect.prepareStatement(selectData2);
                    prepare.setString(1, login_userid.getText());
                    prepare.setString(2, login_password.getText());
                    result = prepare.executeQuery();

                    if (result.next()) {
                        getData.userid = login_userid.getText();
                        alert.successMessage("Successfully Login!");
                        Parent root = FXMLLoader.load(getClass().getResource("/com/example/employeeattendance/info-view.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();

                        ButtonLogin.getScene().getWindow().hide();
                    } else {
                        alert.errorMessage("Incorrect Username/Password");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showPassword() {

        if (login_selectShowPassword.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
