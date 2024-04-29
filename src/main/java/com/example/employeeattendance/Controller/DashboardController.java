package com.example.employeeattendance.Controller;

import com.example.employeeattendance.UserInfo;
import com.example.employeeattendance.getData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController extends MainController implements Initializable {

    @FXML
    public LineChart<String, Number> lineChart;
    @FXML
    private ComboBox<String> comboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            comboBox.setItems(FXCollections.observableArrayList("January", "February"));
            comboBox.setValue("January");
            updateLineChart();
            comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                updateLineChart();
            });
        });
    }
    public void updateLineChart(){
        String selectedValue = comboBox.getValue();

        if ("January".equals(selectedValue)) {
            try {
                String sql = "SELECT DATE_FORMAT(Checkin_DateTime, '%Y-%m-%d') as date, HOUR(TIME(Checkin_DateTime)) + MINUTE(TIME(Checkin_DateTime)) / 60.0 as hours FROM attendancetracking_jan WHERE ID = " + getData.userid;
                Connection connection = connectDb();

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    double hours = resultSet.getDouble("hours");
                    XYChart.Data<String, Number> data = new XYChart.Data<>(date, hours);
                    series.getData().add(data);
                }

                series.setName("Daily Attendance Tracking");
                lineChart.getData().add(series);

                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("February".equals(selectedValue)) {
            try {
                String sql = "SELECT DATE(Checkin_DateTime) as date, TIME(Checkin_DateTime) as hours FROM attendancetracking_feb WHERE ID = " + getData.userid;
                Connection connection = connectDb();

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    double hours = resultSet.getDouble("hours");
                    XYChart.Data<String, Number> data = new XYChart.Data<>(date, hours);
                    series.getData().add(data);
                }

                series.setName("Daily Attendance Tracking");
                lineChart.getData().add(series);

                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}