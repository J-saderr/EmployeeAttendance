package com.example.employeeattendance;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends MainController implements Initializable {

    @FXML
    public LineChart<String, Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Data<String, Number> day1 = new XYChart.Data<>("day 1", 8.0);
        XYChart.Data<String, Number> day2 = new XYChart.Data<>("day 2", 7.45);
        XYChart.Data<String, Number> day3 = new XYChart.Data<>("day 3", 7.55);
        XYChart.Data<String, Number> day4 = new XYChart.Data<>("day 4", 8.15);
        XYChart.Data<String, Number> day5 = new XYChart.Data<>("day 5", 7.55);
        XYChart.Data<String, Number> day6 = new XYChart.Data<>("day 6", 7.43);
        XYChart.Data<String, Number> day7 = new XYChart.Data<>("day 7", 7.20);
        XYChart.Data<String, Number> day8 = new XYChart.Data<>("day 8", 8.0);
        XYChart.Data<String, Number> day9 = new XYChart.Data<>("day 9", 8.05);
        XYChart.Data<String, Number> day10 = new XYChart.Data<>("day 10", 7.44);
        XYChart.Data<String, Number> day11 = new XYChart.Data<>("day 11", 7.58);

        series.getData().addAll(day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,day11);
        series.setName("Daily Attendance Tracking");
        lineChart.getData().add(series);

    }
}
