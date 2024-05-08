package com.example.employeeattendance.Controller;

import com.example.employeeattendance.getData;
import com.example.employeeattendance.models.Attendance;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DashboardController extends MainController implements Initializable {

    public TableColumn colId;
    public TableColumn colCheckin;
    public TableColumn colCheckout;
    public TableColumn colDate;
    public TableColumn colOvertime;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    public TableView<Attendance> tbData;
    @FXML
    private ComboBox<String> comboBox;
    private ObservableList<Attendance> data = FXCollections.observableArrayList();

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

        try {
            showOnTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Attendance> getAttendanceByMonth(String month) throws SQLException {
        ObservableList<Attendance> result = FXCollections.observableArrayList();

        String sql = String.format("select * from attendance%s WHERE id = ", month) + getData.userid;

        Connection connection = connectDb();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        while(resultSet.next()){
            Attendance attendance = new Attendance();
            attendance.setId(resultSet.getInt(1));
            attendance.setDate(resultSet.getDate(2));

            Time checkinTimestamp = resultSet.getTime(3);
            String checkinTime = sdf.format(checkinTimestamp);
            attendance.setCheckin(checkinTime);

            Time checkoutTimestamp = resultSet.getTime(4);
            String checkoutTime = sdf.format(checkoutTimestamp);
            attendance.setCheckout(checkoutTime);

            Time overtime = resultSet.getTime(5);
            String overtimeTime = sdf.format(overtime);
            attendance.setOvertime(overtimeTime);

            result.add(attendance);
        }

        return result;
    }

    private void showOnTable() throws SQLException {

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        colOvertime.setCellValueFactory(new PropertyValueFactory<>("overtime"));

        data = getAttendanceByMonth("jan");
        tbData.setItems(data);
    }

    public void onSelected(ActionEvent actionEvent) {
        if(comboBox.getValue().equals("January")){
            data = FXCollections.observableArrayList();
            try {
                data = getAttendanceByMonth("jan");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tbData.setItems(data);
        } else if(comboBox.getValue().equals("February")){
            data = FXCollections.observableArrayList();
            try {
                data = getAttendanceByMonth("feb");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            tbData.setItems(data);
        }
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
}