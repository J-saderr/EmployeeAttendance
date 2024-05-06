package com.example.employeeattendance.Controller;

import com.example.employeeattendance.AttendanceInfo;
import com.example.employeeattendance.UserInfo;
import com.example.employeeattendance.getData;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController extends MainController implements Initializable {

    @FXML
    public LineChart<String, Number> lineChart;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<AttendanceInfo> tableView;
    @FXML
    private TableColumn<AttendanceInfo, Integer> idColumn;
    @FXML
    private TableColumn<AttendanceInfo, String> nameColumn;
    @FXML
    private TableColumn<AttendanceInfo, Date> dateColumn;
    @FXML
    private TableColumn<AttendanceInfo, Time> checkInColumn;
    @FXML
    private TableColumn<AttendanceInfo, Time> checkOutColumn;
    @FXML
    private TableColumn<AttendanceInfo, Double> overtimeColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            comboBox.setItems(FXCollections.observableArrayList("January", "February"));
            comboBox.setValue("January");
            updateLineChart();
            comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                updateLineChart();
                populateTable();
                initializeTable();
            });
        });
    }

    public void updateLineChart(){
        String selectedValue = comboBox.getValue();
        Connection connection = connectDb();

        if ("January".equals(selectedValue)) {
            lineChart.getData().clear();
            String sql = "SELECT DATE_FORMAT(Checkin_DateTime, '%m-%d') as date, HOUR(TIME(Checkin_DateTime)) + MINUTE(TIME(Checkin_DateTime)) / 60.0 as hours FROM attendancetracking_jan WHERE ID = " + getData.userid;
            try {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    double hours = resultSet.getDouble("hours");


                    if (!resultSet.wasNull()) {
                        XYChart.Data<String, Number> data = new XYChart.Data<>(date, hours);
                        series.getData().add(data);
                    }

                    XYChart.Data<String, Number> data = new XYChart.Data<>(date, hours);
                    series.getData().add(data);
                }

                series.setName("Daily Attendance Tracking");
                lineChart.getData().add(series);
                lineChart.setVisible(true);
                lineChart.setAnimated(false);

                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if ("February".equals(selectedValue)) {
            lineChart.getData().clear();
            String sql = "SELECT DATE_FORMAT(Checkin_DateTime, '%m-%d') as date, HOUR(TIME(Checkin_DateTime)) + MINUTE(TIME(Checkin_DateTime)) / 60.0 as hours FROM attendancetracking_feb WHERE ID = " + getData.userid;
            try {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                XYChart.Series<String, Number> series = new XYChart.Series<>();

                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    double hours = resultSet.getDouble("hours");

                    if (!resultSet.wasNull()) {
                        XYChart.Data<String, Number> data = new XYChart.Data<>(date, hours);
                        series.getData().add(data);
                    }

                    XYChart.Data<String, Number> data = new XYChart.Data<>(date, hours);
                    series.getData().add(data);
                }

                series.setName("Daily Attendance Tracking");
                lineChart.getData().add(series);

                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initializeTable() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        checkInColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckIn()));
        checkOutColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckOut()));
        overtimeColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getOvertime()).asObject());
    }
    public void populateTable() {
        String selectedValue = comboBox.getValue();
        Connection connection = connectDb();
        List<AttendanceInfo> attendanceInfoList = new ArrayList<>();

        if (connection != null) {
            String tableName = selectedValue.equals("January") ? "attendancetracking_jan" : "attendancetracking_feb";
            String sql = "SELECT * FROM " + tableName + " WHERE ID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, Integer.parseInt(getData.userid));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String name = resultSet.getString("Name");
                        Date date = resultSet.getDate("Checkin_Date");
                        Time checkIn = resultSet.getTime("Checkin_Time");
                        Time checkOut = resultSet.getTime("Checkout_Time");
                        double overtime = resultSet.getDouble("Overtime");

                        AttendanceInfo attendanceInfo = new AttendanceInfo(id, name, date, checkIn, checkOut, overtime);
                        attendanceInfoList.add(attendanceInfo);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        tableView.setItems(FXCollections.observableArrayList(attendanceInfoList));
    }
}
