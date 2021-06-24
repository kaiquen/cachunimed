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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class RelatoriosController implements Initializable{
     @FXML TableView<Agenda> tableView;
     @FXML TableColumn<Agenda, Integer> tableColumnId;
     @FXML TableColumn<Agenda, String> tableColoumnMedico;
     @FXML TableColumn<Agenda, String> tableColumnPaciente;
     @FXML TableColumn<Agenda, Timestamp> tablecolumnDateTime;

    
     @FXML void imprimir() throws JRException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
    
        URL url = getClass().getResource("/controller/diretor/relatorio/relatorioAgenda.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

        JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
        jasperViewer.setVisible(true);
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
