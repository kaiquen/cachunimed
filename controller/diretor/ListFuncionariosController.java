package controller.diretor;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Funcionario;
import model.Types;
import model.dao.FuncionarioDAO;
import model.database.Factory;
import model.database.Idatabase;

public class ListFuncionariosController implements Initializable {
    @FXML private TableView<Funcionario> tableView;
    @FXML private TableColumn<Funcionario, Integer> tableColumnId;
    @FXML private TableColumn<Funcionario, String> tableColumnName;

    @FXML private AnchorPane anchorPaneList;
    @FXML private Label labelIdList;
    @FXML private Label labelNameList;
    @FXML private Label labelCpfList;
    @FXML private Label labelPasswordList;
    @FXML private Label labelTypeList;

    @FXML private AnchorPane anchorPaneCreate;
    @FXML private TextField textFieldCpfCreate;
    @FXML private TextField textFieldNameCreate;
    @FXML private PasswordField passwordFieldCreate;
    @FXML private ComboBox<Types> comboBox;

    @FXML private AnchorPane anchorPaneUpdate;
    @FXML private TextField textFieldNameUpdate;
    @FXML private PasswordField passwordFieldUpdate;
    
    //*******************************************************
    @FXML private void updateList(){ 
        textFieldNameUpdate.setText(labelNameList.getText());
        passwordFieldUpdate.setText("");
        anchorPaneList.setVisible(false);
        anchorPaneUpdate.setVisible(true); 
    }
    @FXML private void createList() throws IOException, SQLException{
        textFieldNameCreate.setText("");
        textFieldCpfCreate.setText("");
        passwordFieldCreate.setText("");
        anchorPaneList.setVisible(false);
        anchorPaneCreate.setVisible(true);  
    }
    //*********************************************************

    @FXML private void create() throws SQLException{
        if(!validation()){
            String err = "";    
            if(!verifyCpf())err += "CPF inválido!\n";
            if(!verifyComboBox())err += "Selecione o tipo!\n";
            if(verifyCpf() && verifyComboBox() && !verifyName_Password(true))err = "Tente novamente!";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Fail");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText(err);
            alert.show();
        }else{
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
        //comboBox.getValue().toString(), textFieldCpfCreate.getText(), textFieldNameCreate.getText(), passwordFieldCreate.getText()
        Funcionario funcionario = new Funcionario();
        funcionarioDAO.create(funcionario);
        
        anchorPaneCreate.setVisible(false);
        atulizarTableViews();
        anchorPaneList.setVisible(true);   
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
        FuncionarioDAO funcionario = new FuncionarioDAO(connection);
      
            funcionario.delete(Integer.valueOf(labelIdList.getText()));
            atulizarTableViews();   
        }
    }

    @FXML private void update() throws SQLException{
        if(tableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Fail");
            alert.setHeaderText("Selecione o funcionário");
            alert.setContentText("Tente novamente!");
            alert.show();
        }else if(!verifyName_Password(false)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Fail");
            alert.setHeaderText("Preenchar todos os campos!");
            alert.setContentText("Tente novamente!");
            alert.show();
        }else {
            Idatabase database = Factory.getDatabase("postgres");
            Connection connection = database.connect();
            FuncionarioDAO funcionario = new FuncionarioDAO(connection);

           
            funcionario.update(Integer.valueOf(labelIdList.getText()), textFieldNameUpdate.getText(), passwordFieldUpdate.getText());

            anchorPaneUpdate.setVisible(false);
            atulizarTableViews();
            anchorPaneList.setVisible(true);  
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
            printTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.getSelectionModel().selectedItemProperty().addListener(
            (obeservable, oldValue, newValue) -> selectItem(newValue)
        );
    }  

    private void atulizarTableViews() throws SQLException{
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.setItems(lista());
    }

    private ObservableList<Funcionario> lista() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        FuncionarioDAO funcionario = new FuncionarioDAO(connection);
        
        return FXCollections.observableArrayList(
            funcionario.selectFuncionarios()
        );
    }
    private ObservableList<Types> type() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        FuncionarioDAO funcionario = new FuncionarioDAO(connection);
        return FXCollections.observableArrayList(
            funcionario.selectCargos()
        );
    }

    private void selectItem(Funcionario funcionario){
        if(funcionario != null){
            labelIdList.setText(String.valueOf(funcionario.getId()));
            labelNameList.setText(funcionario.getName());
            labelCpfList.setText(funcionario.getCpf());
            labelPasswordList.setText(funcionario.getPassword());
            labelTypeList.setText(funcionario.getCargo());
        }else{
            labelIdList.setText("");
            labelNameList.setText("");
            labelCpfList.setText("");
            labelPasswordList.setText("");
            labelTypeList.setText("");   
        }
    }


    private boolean validation(){
        Boolean validation = (verifyName_Password(true) && verifyCpf() && verifyComboBox()) ? true : false; 
        return validation; 
    } 
    //true = create | false = update
    private Boolean verifyName_Password(Boolean type){
        boolean verifyName_Password;
        if(type){
            verifyName_Password = (!textFieldNameCreate.getText().isEmpty() && !passwordFieldCreate.getText().isEmpty()) ? true : false;
        }else{
            verifyName_Password = (!textFieldNameUpdate.getText().isEmpty() && !passwordFieldUpdate.getText().isEmpty()) ? true : false;
        }
        return verifyName_Password;
    }
    private Boolean verifyCpf(){
        boolean verifyCPF = (!textFieldCpfCreate.getText().isEmpty() && textFieldCpfCreate.getText().length() == 11 && textFieldCpfCreate.getText().matches("[0-9]+")) ? true : false;
        return verifyCPF;
    }

    private Boolean verifyComboBox(){
        boolean verifyComboBox = !(comboBox.getSelectionModel().isEmpty()) ? true : false;
        return verifyComboBox;
    }

    private void printTypes() throws SQLException{       
        comboBox.setItems(type());
    }
}
