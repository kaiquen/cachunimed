package controller.recepcionista;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import model.Paciente;
import model.dao.PacienteDAO;
import model.database.Factory;
import model.database.Idatabase;

public class MainRecepcionistaController implements Initializable{
    @FXML TextField textField;
    @FXML ListView<Paciente> listView;
    @FXML TextField textFieldCpfCreate;
    @FXML TextField textFieldNameCreate;
    @FXML TextField textFieldFoneCreate;
    @FXML TextField textFieldAddressCreate;
    @FXML TextInputControl labelIdList;

    @FXML TextInputControl textFieldFoneUpdate;
    @FXML TextInputControl textFieldNameUpdate;
    @FXML TextInputControl textFieldAddressUpdate;

    @FXML AnchorPane anchorPaneCreate;
    @FXML AnchorPane anchorPaneUpdate;
    @FXML Group group;

    @FXML private void searchPaciente() throws SQLException{
        
        if(textField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Fail");
            alert.setHeaderText("Preencha o campo ");
            alert.setContentText("Tente novamente!");
            alert.show();
        }else{
            atualizaListView();
        }
    }
    @FXML private void create(){
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        PacienteDAO pacienteDAO = new PacienteDAO(connection);
        try {
         
            Paciente paciente = new Paciente(textFieldCpfCreate.getText(), textFieldNameCreate.getText(), textFieldFoneCreate.getText(), textFieldAddressCreate.getText());
            
            pacienteDAO.create(paciente);

            anchorPaneCreate.setVisible(false);
            group.setVisible(true);   
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Fail");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText(e.getMessage());
            alert.show();
        }  
    }
    @FXML private void update(){
        System.out.println(listView.getSelectionModel().getSelectedItem().getId());
        if(listView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Fail");
            alert.setHeaderText("Selecione o Paciente");
            alert.setContentText("Tente novamente!");
            alert.show();
        } else {
            Idatabase database = Factory.getDatabase("postgres");
            Connection connection = database.connect();
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            try {
                System.out.println(listView.getSelectionModel().getSelectedItem().getId());
                Paciente paciente = new Paciente(Integer.valueOf(listView.getSelectionModel().getSelectedItem().getId()), textFieldNameUpdate.getText(), textFieldFoneUpdate.getText(), textFieldAddressUpdate.getText());
                pacienteDAO.update(paciente);
                
                anchorPaneUpdate.setVisible(false);
                group.setVisible(true);  
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Insert Fail");
                alert.setHeaderText("Preencha todos os campos");
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }
    @FXML private void clearUpdate(){
        anchorPaneUpdate.setVisible(false);
        group.setVisible(true);
        
    }
    @FXML private void clearCreate(){
        anchorPaneCreate.setVisible(false);
        group.setVisible(true);
    }
    @FXML private void createPaciente(){
        group.setVisible(false);
        anchorPaneCreate.setVisible(true);
    }

    @FXML private void updatePaciente(){
        group.setVisible(false);
        anchorPaneUpdate.setVisible(true);
    }
    @FXML private void createConsulta(){
        System.out.println("ok");
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {   
    }

    private void atualizaListView() throws SQLException{
        Idatabase database = Factory.getDatabase("postgres");
            Connection connection = database.connect();
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            Paciente paciente = new Paciente(textField.getText());
            
            ObservableList<Paciente> items =FXCollections.observableArrayList (
                pacienteDAO.searchPaciente(paciente));
            listView.setItems(items);
    }
    
}
