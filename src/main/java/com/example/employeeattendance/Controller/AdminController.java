package com.example.employeeattendance.Controller;

import com.example.employeeattendance.models.AttendRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController extends MainController implements Initializable {
    @FXML
    private PieChart pieChart;
    @FXML
    private ComboBox<String> ButtonMonth;
    @FXML
    private TableView<AttendRecord> tbData;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colInTime;
    @FXML
    private TableColumn colLate;
    @FXML
    private TableColumn colAbsent;
    @FXML
    private TableColumn colApprove;
    @FXML
    private TableColumn colStatus;
    @FXML
    private TableColumn colOvertime;

    private ObservableList<AttendRecord> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            showOnTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ButtonMonth.getItems().addAll("January", "February");
        ButtonMonth.getSelectionModel().selectFirst();
        ButtonMonth.setValue("January");
    }

    private ObservableList<AttendRecord> getDataByMonth(String month) throws SQLException {
        ObservableList<AttendRecord> result = FXCollections.observableArrayList();

        String sql = String.format("select * from attend_record_%s", month);

        Connection connection = connectDb();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            result.add(new AttendRecord(
                    resultSet.getInt("ID"),
                    resultSet.getInt("in_time"),
                    resultSet.getInt("late"),
                    resultSet.getInt("total_absent"),
                    resultSet.getInt("approval"),
                    resultSet.getString("status"),
                    resultSet.getInt("overtime"))
            );
        }

        return result;
    }

    private void showOnTable() throws SQLException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colInTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
        colLate.setCellValueFactory(new PropertyValueFactory<>("late"));
        colAbsent.setCellValueFactory(new PropertyValueFactory<>("totalAbsent"));
        colApprove.setCellValueFactory(new PropertyValueFactory<>("approved"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOvertime.setCellValueFactory(new PropertyValueFactory<>("overTime"));

        data = getDataByMonth("jan");
        tbData.setItems(data);
    }
    public void onSelected(ActionEvent actionEvent) {
        if(ButtonMonth.getValue().equals("January")){
            try {
                data = getDataByMonth("jan");
                tbData.setItems(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(ButtonMonth.getValue().equals("February")){
            try {
                data = getDataByMonth("feb");
                tbData.setItems(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
