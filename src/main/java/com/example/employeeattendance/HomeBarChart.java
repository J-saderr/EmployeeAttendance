package com.example.employeeattendance;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class HomeBarChart implements Initializable {

    @FXML
    private PieChart pieChart;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("On time",23),
                new PieChart.Data("Late",5),
                new PieChart.Data("Absent",2));
        pieChart.setData(pieChartData);

    }
}
