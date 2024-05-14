package Admin;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane background;
    @FXML
    private Button HomeButton;
    @FXML
    private ChoiceBox<?> MonthButton;

    //Data for PieChart
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

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> Absent;

    @FXML
    private TableColumn<?, ?> Approve;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> Late;

    @FXML
    private TableColumn<?, ?> Overtime;

    @FXML
    private TableColumn<?, ?> Status;

}
