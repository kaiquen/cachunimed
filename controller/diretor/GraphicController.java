package controller.diretor;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import model.dao.AgendaDAO;
import model.database.Factory;
import model.database.Idatabase;

public class GraphicController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private Axis<Integer> numberAxis;
    @FXML
    private ComboBox comboBox;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        months();
        try {
            ano();
            alterGraphic();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void alterGraphic() throws SQLException {
        graphic();
    }

    private void graphic() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        AgendaDAO agendaDAO = new AgendaDAO(connection);

        Integer date = Integer.valueOf(comboBox.getSelectionModel().getSelectedItem().toString());
        System.out.println(date);
        Map<Integer, ArrayList> dados = agendaDAO.graphic(date);

       
        barChart.getData().clear();
        for (Map.Entry<Integer, ArrayList> dadosItem : dados.entrySet()) { 
            Series<String, Integer> series = new XYChart.Series<>();
            series.setName(dadosItem.getKey().toString());
            for (int i = 0; i < dadosItem.getValue().size(); i = i + 2) {
                String mes;
                Integer quantidade;
                mes = retornaNomeMes((Integer) dadosItem.getValue().get(i));
                quantidade = (Integer) dadosItem.getValue().get(i + 1);
                series.getData().add(new XYChart.Data<>(mes, quantidade));
            }
            barChart.getData().add(series);
        }
    }

    private String retornaNomeMes(int i) {
        switch (i) {
            case 1:
                return "Jan";
            case 2:
                return "Fev";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "Mai";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Set";
            case 10:
                return "Out";
            case 11:
                return "Nov";
            case 12:
                return "Dez";
        }
        return null;
    }

    private void months() {
        ObservableList<String> items = FXCollections.observableArrayList("Jan", "Fev", "Mar", "Abr", "Mai", "Jun",
                "Jul", "Ago", "Set", "Out", "Dez");
        categoryAxis.setCategories(items);
    }

    private void ano() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        AgendaDAO agendaDAO = new AgendaDAO(connection);

        ObservableList<String> items = FXCollections.observableArrayList(agendaDAO.selectYear());
        comboBox.setItems(items);
        comboBox.getSelectionModel().select(0);
    }
}
