package com.example.employeeattendance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
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
    private Pane inner_pane211;

    @FXML
    private HBox root;
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
