package controller.diretor;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Agenda;
import model.dao.AgendaDAO;
import model.database.Factory;
import model.database.Idatabase;

public class RelatoriosController implements Initializable{
     @FXML TableView<Agenda> tableView;
     @FXML TableColumn<Agenda, Integer> tableColumnId;
     @FXML TableColumn<Agenda, String> tableColoumnMedico;
     @FXML TableColumn<Agenda, String> tableColumnPaciente;
     @FXML TableColumn<Agenda, Timestamp> tablecolumnDateTime;

    
     @FXML void imprimir(){
         
     }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            atulizarTableViews();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    private void atulizarTableViews() throws SQLException{
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColoumnMedico.setCellValueFactory(new PropertyValueFactory<>("nameMedico"));
        tableColumnPaciente.setCellValueFactory(new PropertyValueFactory<>("namePaciente"));
        tablecolumnDateTime.setCellValueFactory(new PropertyValueFactory<>("DateTime"));

        tableView.setItems(lista());
    }
    private ObservableList<Agenda> lista() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        AgendaDAO agendaDAO = new AgendaDAO(connection);
        
        return FXCollections.observableArrayList(
            agendaDAO.selectRelatorios()
        );
    }
}
