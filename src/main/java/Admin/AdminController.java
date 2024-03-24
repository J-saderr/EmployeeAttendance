package Admin;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private PieChart pieChart;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Absent", 10),
                new PieChart.Data("Late", 25),
                new PieChart.Data("In-time", 63),
                new PieChart.Data("Absent appro", 2)
        );

        pieChartData.forEach(data -> data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), ": ", data.pieValueProperty()
                )));

        pieChart.getData().addAll(pieChartData);

        //making the legend invisible
        pieChart.setLegendVisible(false);
    }
}
