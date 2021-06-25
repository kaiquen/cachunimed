package controller.recepcionista;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Agenda;
import model.Funcionario;
import model.Paciente;
import model.dao.AgendaDAO;
import model.dao.FuncionarioDAO;
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
    @FXML TextInputControl textFieldFoneUpdate;
    @FXML TextInputControl textFieldNameUpdate;
    @FXML TextInputControl textFieldAddressUpdate;
    @FXML ComboBox<Funcionario> comboBoxMedicos;
    @FXML AnchorPane anchorPaneCreate;
    @FXML AnchorPane anchorPaneUpdate;
    @FXML AnchorPane anchorPaneConsulta;
    @FXML BorderPane borderPane;
    @FXML Group group;
    @FXML DatePickerSkin calendar;
    @FXML Label labelId;
    @FXML Label labelName;
    @FXML Label labelCpf;
    @FXML Label labelFone;
    @FXML Label labelAddress;
    @FXML TextField textFieldDate;
    @FXML ComboBox<String> comboBoxhour;
    
    String textDate;
    DatePicker datePicker = new DatePicker(LocalDate.now());
    DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
    LocalDate date;
    Funcionario funcionario;

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
            pacienteDAO.createPaciente(paciente);
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
            System.out.println(listView.getSelectionModel().getSelectedItem().getCod());
            Idatabase database = Factory.getDatabase("postgres");
            Connection connection = database.connect();
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            try {
                System.out.println(listView.getSelectionModel().getSelectedItem().getCod());
                Paciente paciente = new Paciente(Integer.valueOf(listView.getSelectionModel().getSelectedItem().getCod()), textFieldNameUpdate.getText(), textFieldFoneUpdate.getText(), textFieldAddressUpdate.getText());
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
        if(listView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Fail");
            alert.setHeaderText("Selecione o Paciente");
            alert.setContentText("Tente novamente!");
            alert.show();
        } else {
        group.setVisible(false);
        anchorPaneUpdate.setVisible(true);

        textFieldNameUpdate.setText(labelName.getText());
        textFieldFoneUpdate.setText(labelFone.getText());
        textFieldAddressUpdate.setText(labelAddress.getText());
        }
    }

    @FXML private void createConsulta(){
        if(listView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Fail");
            alert.setHeaderText("Selecione o Paciente");
            alert.setContentText("Tente novamente!");
            alert.show();
        } else {
            group.setVisible(false);
            anchorPaneConsulta.setVisible(true);
            Node calendar = datePickerSkin.getPopupContent();
            date = LocalDate.now();
            textDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
            textFieldDate.setText( textDate);
            borderPane.setCenter(calendar);
            datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                date =  newValue;
                textDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
                textFieldDate.setText(textDate);
            });
        }
    }

    @FXML private void agendar() throws SQLException{
        if(comboBoxMedicos.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Fail");
            alert.setHeaderText("Selecione o Médico");
            alert.setContentText("Tente novamente!");
            alert.show();
        } else {
            Idatabase database = Factory.getDatabase("postgres");
            Connection connection = database.connect();
            AgendaDAO agendaDAO = new AgendaDAO(connection);
            String datetime = date + " "+ comboBoxhour.getValue().toString();
            Timestamp datetimeSQL = Timestamp.valueOf(datetime);
            Agenda agenda = new Agenda(comboBoxMedicos.getValue().toString(), Integer.parseInt(labelId.getText()), datetimeSQL);
            agenda.setCod_funcionario_medico(agendaDAO.selectIdMedico(agenda));
            agendaDAO.createAgenda(agenda);
            cancelar();
        }
    }

    @FXML private void cancelar(){
         group.setVisible(true);
        anchorPaneConsulta.setVisible(false);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {   
        try {
            printMedicos();
            hours();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listView.getSelectionModel().selectedItemProperty()
                .addListener((obeservable, oldValue, newValue) -> selectItem(newValue));
    }

    private void atualizaListView() throws SQLException{
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        PacienteDAO pacienteDAO = new PacienteDAO(connection);
        Paciente paciente = new Paciente(textField.getText());
        System.out.println(paciente.getNome());

        ObservableList<Paciente> items = FXCollections.observableArrayList (
            pacienteDAO.selectPaciente(paciente));
        listView.setItems(items);
    }

    private ObservableList<Funcionario> medicos() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
        return FXCollections.observableArrayList(
            funcionarioDAO.selectMedicos()
        );
    }

    private void hours() throws SQLException {
        ObservableList<String> items = FXCollections.observableArrayList(
            "08:00:00", "09:00:00", "10:00:00","11:00:00","13:00:00", "14:00:00", "15:00:00" ,"16:00:00","17:00:00");
        comboBoxhour.setItems(items);
    }

    private void printMedicos() throws SQLException{ 
        comboBoxMedicos.setItems(medicos());
    }
  
    private void selectItem(Paciente paciente) {
        if (paciente != null) {
            labelId.setText(String.valueOf(paciente.getCod()));
            labelName.setText(paciente.getNome());
            labelCpf.setText(paciente.getCpf());
            labelFone.setText(paciente.getTelefone());
            labelAddress.setText(paciente.getEndereco());
        } else {
            labelId.setText("");
            labelName.setText("");
            labelCpf.setText("");
            labelFone.setText("");
            labelAddress.setText("");
        }
    }
}

