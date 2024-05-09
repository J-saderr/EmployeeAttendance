package com.example.employeeattendance.Controller;

import com.example.employeeattendance.getData;
import com.example.employeeattendance.UserInfo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InfoController extends MainController implements Initializable {
    private Button dashboard;

    @FXML
    private Button dashboard1;

    @FXML
    private Button dashboard11;

    @FXML
    private TextField dateOfBirth;

    @FXML
    private TextField department;

    @FXML
    private TextField firstname;

    @FXML
    private Label fullname;
    @FXML
    private Label position;
    @FXML
    private TextField id;
    @FXML
    private Pane inner_pane;

    @FXML
    private Pane inner_pane1;

    @FXML
    private TextField full_name;

    @FXML
    private TextField positions;
    @FXML
    private TextField bioTextField;
    @FXML
    public Button saveButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update();
        refreshBio();
    }
    public UserInfo showInfo(){
        UserInfo info1 = null;
        try {

            String sql = "SELECT * FROM information WHERE id = " + getData.userid;
          
            Connection connection = connectDb();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                info1 = new UserInfo(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Position"),
                        resultSet.getString("Department"),
                        resultSet.getDate("Date_of_Birth")
                );
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info1;
    }
    public void update() {
        UserInfo userInfo = showInfo();
        if (userInfo != null ) {
            Platform.runLater(() -> {
                fullname.setText(userInfo.getName());
                position.setText(userInfo.getPosition());
                positions.setText(userInfo.getPosition());
                department.setText(userInfo.getDepartment());
                dateOfBirth.setText(String.valueOf(userInfo.getDate()));
                id.setText(String.valueOf(userInfo.getId()));
                full_name.setText((userInfo.getName()));
            });
        }
    }
    @FXML
    private void saveButton(ActionEvent event) {
        String bioText = bioTextField.getText();
        saveBio(bioText);
    }

    private void saveBio(String bio) {
        String updateSql = "UPDATE information SET Bio = ? WHERE id = " + getData.userid;
        Connection connect = connectDb();
        PreparedStatement updatePrepare = null;
        try {
            updatePrepare = connect.prepareStatement(updateSql);
            updatePrepare.setString(1, bio);
            updatePrepare.executeUpdate();
            int affectedRows = updatePrepare.executeUpdate();
            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cập nhật thành công");
                alert.setHeaderText(null);
                alert.setContentText("Thông tin bio đã được cập nhật.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cập nhật không thành công");
                alert.setHeaderText(null);
                alert.setContentText("Không có hàng nào được cập nhật.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshBio() {
        String selectSql = "SELECT Bio FROM information WHERE id = " + getData.userid;
        Connection connect = connectDb();
        PreparedStatement selectPrepare = null;
        ResultSet resultSet = null;
        try {
            selectPrepare = connect.prepareStatement(selectSql);
            resultSet = selectPrepare.executeQuery();
            if (resultSet.next()) {
                String bio = resultSet.getString("Bio");
                Platform.runLater(() -> {
                    bioTextField.setText(bio);
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
