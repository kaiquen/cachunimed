package controller.medico;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.BorderPane;
import model.Agenda;
import model.dao.AgendaDAO;
import model.database.Factory;
import model.database.Idatabase;


public class MainMedicoController implements Initializable {
    @FXML ListView<Agenda> listView;
    @FXML BorderPane borderPane;
    DatePicker datePicker = new DatePicker(LocalDate.now());
    DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
    Node calendar = datePickerSkin.getPopupContent();
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { 
        borderPane.setLeft(calendar);
       
       
        try {
            atualizaListView(LocalDate.now());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                atualizaListView(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    
    private void atualizaListView(LocalDate newValue) throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        AgendaDAO agendaDAO = new AgendaDAO(connection);
       
        ObservableList<Agenda> items = FXCollections.observableArrayList (
            agendaDAO.selectAgendas(newValue));

        listView.setItems(items);
    }


  
}
