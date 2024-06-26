package com.example.employeeattendance.Controller;

import com.example.employeeattendance.getData;
import com.example.employeeattendance.UserInfo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController extends MainController implements Initializable{
    @FXML
    private Button dashboard;

    @FXML
    private Button dashboard1;

    @FXML
    private Button dashboard11;

    @FXML
    private Pane inner_pane;

    @FXML
    private Pane inner_pane1;

    @FXML
    private Pane inner_pane2;

    @FXML
    private Pane inner_pane21;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label labelDisplay;

    @FXML
    private Pane inner_pane211;

    @FXML
    private Label absent;

    @FXML
    private Label base_salary;

    @FXML
    private Label income;

    @FXML
    private Label bonus;

    @FXML
    private Label checking;

    @FXML
    private Label name;

    @FXML
    private Label position;

    @FXML
    private Label department;

    @FXML
    private HBox root;

    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            comboBox.setItems(FXCollections.observableArrayList("January", "February"));
            comboBox.setValue("January");
            updateLabels();
            updatePieChart();
            comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                updateLabels();
                updatePieChart();
            });
        });
    }

    public UserInfo showInfo1(){
        UserInfo info1 = null;
        try {
            String sql = "SELECT * FROM information WHERE Employee_ID = " + getData.userid;
            Connection connection = connectDb();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                info1 = new UserInfo(
                        resultSet.getInt("Employee_ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Position"),
                        resultSet.getString("Department"),
                        resultSet.getInt("Base_Salary")
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

    public UserInfo showInfo2(){
        UserInfo info2 = null;
        String selectedValue = comboBox.getValue();

        if ("January".equals(selectedValue)) {
            try {
                String sql = "SELECT j.*, s.Jan_MonthlyIncome\n" +
                        "FROM employee.sal_perform_jan j\n" +
                        "INNER JOIN employee.jan_totalsalary s ON j.JanSal_ID = s.JanSal_ID\n" +
                        "WHERE s.Employee_ID = " + getData.userid;
                Connection connection = connectDb();

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    info2 = new UserInfo(
                            resultSet.getInt("JanSal_ID"),
                            resultSet.getInt("Jan_Absent"),
                            resultSet.getInt("Jan_Bonus"),
                            resultSet.getInt("Jan_MonthlyIncome"),
                            resultSet.getInt("Jan_Penalty")
                    );
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if ("February".equals(selectedValue)) {
            try {
                String sql = "SELECT f.*, s.Feb_MonthlyIncome\n" +
                        "FROM employee.sal_perform_feb f\n" +
                        "INNER JOIN employee.feb_totalsalary s ON f.FebSal_ID = s.FebSal_ID\n" +
                        "WHERE s.Employee_ID = " + getData.userid;
                Connection connection = connectDb();

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    info2 = new UserInfo(
                            resultSet.getInt("FebSal_ID"),
                            resultSet.getInt("Feb_Absent"),
                            resultSet.getInt("Feb_Bonus"),
                            resultSet.getInt("Feb_MonthlyIncome"),
                            resultSet.getInt("Feb_Penalty")

                    );
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return info2;
    }

    public void updateLabels() {
        UserInfo userInfo1 = showInfo1();
        UserInfo userInfo2 = showInfo2();
        if (userInfo1 != null ) {
            Platform.runLater(() -> {
                name.setText(userInfo1.getName());
                position.setText(userInfo1.getPosition());
                department.setText(userInfo1.getDepartment());
                base_salary.setText(String.valueOf(userInfo1.getBaseSalary()));
                absent.setText(String.valueOf(userInfo2.getAbsent()));
                income.setText(String.valueOf(userInfo2.getIncome()));
                bonus.setText(String.valueOf(userInfo2.getBonus()));
                checking.setText(String.valueOf(userInfo2.getChecking()));
            });
        }
    }
    public void updatePieChart() {
        Connection connection = connectDb();
        String selectedValue = comboBox.getValue();

        if ("January".equals(selectedValue)) {
            String sql = "SELECT * FROM attend_record_jan WHERE JanPerform_ID =" + getData.userid;
            try {

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int onTimeCount = resultSet.getInt("Jan_TotalIntime");
                    int lateCount = resultSet.getInt("Jan_TotalLate");
                    int absentCount = resultSet.getInt("Jan_TotalAbsent");

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("On time (" + onTimeCount + ")", onTimeCount),
                            new PieChart.Data("Late (" + lateCount + ")", lateCount),
                            new PieChart.Data("Absent (" + absentCount + ")", absentCount)
                    );

                    pieChart.setData(pieChartData);
                    pieChart.setVisible(true);
                }

                resultSet.close();
                preparedStatement.close();
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
        else if ("February".equals(selectedValue)) {
            String sql = "SELECT * FROM attend_record_feb WHERE FebPerform_ID = " + getData.userid;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int onTimeCount = resultSet.getInt("Feb_TotalIntime");
                    int lateCount = resultSet.getInt("Feb_TotalLate");
                    int absentCount = resultSet.getInt("Feb_Totalabsent");

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("On time (" + onTimeCount + ")", onTimeCount),
                            new PieChart.Data("Late (" + lateCount + ")", lateCount),
                            new PieChart.Data("Absent (" + absentCount + ")", absentCount)
                    );
                    pieChart.setData(pieChartData);
                }

                resultSet.close();
                preparedStatement.close();
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