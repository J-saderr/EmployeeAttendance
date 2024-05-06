package com.example.employeeattendance.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane background;

    @FXML
    private Button HomeButton;

    @FXML
    private ChoiceBox<String> MonthButton;

    @FXML
    private TableView<DataItem> table;

    @FXML
    private TableColumn<DataItem, String> Absent;

    @FXML
    private TableColumn<DataItem, String> Approve;

    @FXML
    private TableColumn<DataItem, String> ID;

    @FXML
    private TableColumn<DataItem, String> Late;

    @FXML
    private TableColumn<DataItem, String> Overtime;

    @FXML
    private TableColumn<DataItem, String> Status;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize ChoiceBox with months
        MonthButton.getItems().addAll("January", "February");

        // Set up columns in the TableView
        Absent.setCellValueFactory(new PropertyValueFactory<>("absent"));
        Approve.setCellValueFactory(new PropertyValueFactory<>("approve"));
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Late.setCellValueFactory(new PropertyValueFactory<>("late"));
        Overtime.setCellValueFactory(new PropertyValueFactory<>("overtime"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Connect to the database
        connection = connectDb();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
        }
    }

    @FXML
    private void onMonthSelected() {
        String selectedMonth = MonthButton.getValue();
        fetchDataForMonth(selectedMonth);
    }

    private void fetchDataForMonth(String month) {
        if (connection != null) {
            try {
                String query = "SELECT * FROM your_table_name WHERE month = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, month);
                ResultSet resultSet = statement.executeQuery();

                // Clear previous data
                pieChart.getData().clear();
                table.getItems().clear();

                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String absent = resultSet.getString("absent");
                    String late = resultSet.getString("late");
                    String overtime = resultSet.getString("overtime");
                    String approve = resultSet.getString("approve");
                    String status = resultSet.getString("status");

                    // Add data to pie chart
                    PieChart.Data data = new PieChart.Data(status, Integer.parseInt(absent));
                    pieChart.getData().add(data);

                    // Add data to table
                    table.getItems().add(new DataItem(id, absent, late, overtime, approve, status));
                }

                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Database connection is not established.");
        }
    }

    private static class DataItem {
        private final String id;
        private final String absent;
        private final String late;
        private final String overtime;
        private final String approve;
        private final String status;

        public DataItem(String id, String absent, String late, String overtime, String approve, String status) {
            this.id = id;
            this.absent = absent;
            this.late = late;
            this.overtime = overtime;
            this.approve = approve;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getAbsent() {
            return absent;
        }

        public String getLate() {
            return late;
        }

        public String getOvertime() {
            return overtime;
        }

        public String getApprove() {
            return approve;
        }

        public String getStatus() {
            return status;
        }
    }
}

