package controller.diretor;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Paciente;
import model.dao.PacienteDAO;
import model.database.Factory;
import model.database.Idatabase;

public class ListPacienteController implements Initializable {
    @FXML TableView<Paciente> tableView;
    @FXML TableColumn<Paciente, Integer> tableColumnId;
    @FXML TableColumn<Paciente, String> tableColumnName;
    @FXML TextField textFieldNameCreate;
    @FXML TextField textFieldCpfCreate;
    @FXML TextField textFieldFoneCreate;
    @FXML TextField textFieldAddressCreate;
    @FXML TextField textFieldNameUpdate;
    @FXML TextField textFieldFoneUpdate;
    @FXML TextField textFieldAddressUpdate;
    @FXML Label labelIdList;
    @FXML Label labelNameList;
    @FXML Label labelCpfList;
    @FXML Label labelFoneList;
    @FXML Label labelAddressList;
    @FXML AnchorPane anchorPaneUpdate;
    @FXML AnchorPane anchorPaneCreate;
    @FXML AnchorPane anchorPaneList;

    @FXML private void updateList(){ 
        if(tableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Fail");
            alert.setHeaderText("Selecione o Paciente");
            alert.setContentText("Tente novamente!");
            alert.show();
        } else {
            textFieldNameUpdate.setText(labelNameList.getText());
            textFieldFoneUpdate.setText(labelFoneList.getText());
            textFieldAddressUpdate.setText(labelAddressList.getText());
            anchorPaneList.setVisible(false);
            anchorPaneUpdate.setVisible(true); 
        }
    }

    @FXML private void createList() {
        anchorPaneList.setVisible(false);
        anchorPaneCreate.setVisible(true);  
    }

    @FXML private void create() throws SQLException{
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        PacienteDAO pacienteDAO = new PacienteDAO(connection);
        try {
            Paciente paciente = new Paciente(textFieldCpfCreate.getText(), textFieldNameCreate.getText(), textFieldFoneCreate.getText(), textFieldAddressCreate.getText());
            pacienteDAO.createPaciente(paciente);
            anchorPaneCreate.setVisible(false);
            atulizarTableViews();
            anchorPaneList.setVisible(true);   
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Fail");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText(e.getMessage());
            alert.show();
        }  
    } 
    
    @FXML private void clearCreate(){
        anchorPaneCreate.setVisible(false);
        anchorPaneList.setVisible(true);
    }

    @FXML private void delete() throws NumberFormatException, SQLException {
        if(tableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Fail");
            alert.setHeaderText("Selecione o campo");
            alert.setContentText("Tente novamente!");
            alert.show();
        }else {
            Idatabase database = Factory.getDatabase("postgres");
            Connection connection = database.connect();
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            Paciente funcionario = new Paciente(Integer.valueOf(labelIdList.getText()));
            pacienteDAO.delete(funcionario);
            atulizarTableViews();   
        }
    }

    @FXML private void update() throws SQLException{
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        PacienteDAO pacienteDAO = new PacienteDAO(connection);
        try {
            Paciente paciente = new Paciente(Integer.valueOf(labelIdList.getText()), textFieldNameUpdate.getText(), textFieldFoneUpdate.getText(), textFieldAddressUpdate.getText());
            pacienteDAO.update(paciente);
            anchorPaneUpdate.setVisible(false);
            atulizarTableViews();
            anchorPaneList.setVisible(true);  
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Fail");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML private void clearUpdate(){
        anchorPaneUpdate.setVisible(false);
        anchorPaneList.setVisible(true);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            atulizarTableViews();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableView.getSelectionModel().selectedItemProperty().addListener(
            (obeservable, oldValue, newValue) -> selectItem(newValue)
        );
    }

    private void atulizarTableViews() throws SQLException{
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("cod"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableView.setItems(lista());
    }

    private ObservableList<Paciente> lista() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        PacienteDAO pacienteDAO = new PacienteDAO(connection);
        return FXCollections.observableArrayList(
            pacienteDAO.selectPacientes()
        );
    }
    private void selectItem(Paciente paciente){
        if(paciente != null){
            String cpf = paciente.getCpf().substring(0,3) + "." + paciente.getCpf().substring(3,6) + "." + paciente.getCpf().substring(6,9) + "-" + paciente.getCpf().substring(9,11);
            String telefone = "("+ paciente.getTelefone().substring(0,2) + ") "  + paciente.getTelefone().substring(2,7) + "-" + paciente.getTelefone().substring(7,11);
            labelIdList.setText(String.valueOf(paciente.getCod()));
            labelNameList.setText(paciente.getNome());
            labelCpfList.setText(cpf);
            labelFoneList.setText(telefone);
            labelAddressList.setText(paciente.getEndereco());
        }else{
            labelIdList.setText("");
            labelNameList.setText("");
            labelCpfList.setText("");
            labelFoneList.setText("");
            labelAddressList.setText("");   
        }
    }
}
