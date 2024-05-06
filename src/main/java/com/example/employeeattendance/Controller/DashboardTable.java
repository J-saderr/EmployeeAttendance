//package com.example.employeeattendance.Controller;
//
//import com.example.employeeattendance.AttendanceInfo;
//import com.example.employeeattendance.getData;
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.beans.property.SimpleDoubleProperty;
//
//
//import java.net.URL;
//import java.sql.Date;
//import java.sql.Time;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class DashboardTableController extends DashboardController {
//
//    @FXML
//    private TableView<AttendanceInfo> tableView;
//    @FXML
//    private TableColumn<AttendanceInfo, Integer> idColumn;
//    @FXML
//    private TableColumn<AttendanceInfo, String> nameColumn;
//    @FXML
//    private TableColumn<AttendanceInfo, Date> dateColumn;
//    @FXML
//    private TableColumn<AttendanceInfo, Time> checkInColumn;
//    @FXML
//    private TableColumn<AttendanceInfo, Time> checkOutColumn;
//    @FXML
//    private TableColumn<AttendanceInfo, Double> overtimeColumn;
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        Platform.runLater(() -> {
//            comboBox.setItems(FXCollections.observableArrayList("January", "February"));
//            comboBox.setValue("January");
//            updateLineChart();
//            comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//
//                updateLineChart();
//            });
//        });
//    }
//
////    private void initializeTable() {
////        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
////        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
////        dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
////        checkInColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckIn()));
////        checkOutColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckOut()));
////        overtimeColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getOvertime()).asObject());
////    }
////
////    @Override
////    public void updateLineChart() {
////        super.updateLineChart();
////        populateTable();
////    }
////
////    private void populateTable() {
////        String selectedValue = comboBox.getValue();
////        Connection connection = connectDb();
////        List<AttendanceInfo> attendanceInfoList = new ArrayList<>();
////
////        if (connection != null) {
////            String tableName = selectedValue.equals("January") ? "attendancetracking_jan" : "attendancetracking_feb";
////            String sql = "SELECT * FROM " + tableName + " WHERE ID = ?";
////
////            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
////                preparedStatement.setInt(1, Integer.parseInt(getData.userid));
////                try (ResultSet resultSet = preparedStatement.executeQuery()) {
////                    while (resultSet.next()) {
////                        int id = resultSet.getInt("ID");
////                        String name = resultSet.getString("Name");
////                        Date date = resultSet.getDate("Checkin_Date");
////                        Time checkIn = resultSet.getTime("Checkin_Time");
////                        Time checkOut = resultSet.getTime("Checkout_Time");
////                        double overtime = resultSet.getDouble("Overtime");
////
////                        AttendanceInfo attendanceInfo = new AttendanceInfo(id, name, date, checkIn, checkOut, overtime);
////                        attendanceInfoList.add(attendanceInfo);
////                    }
////                }
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        tableView.setItems(FXCollections.observableArrayList(attendanceInfoList));
////    }
////}
//
