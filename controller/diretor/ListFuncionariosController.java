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
    @FXML private Label labelId;
    @FXML private Label labelName;
    @FXML private Label labelCpf;
    @FXML private Label labelPassword;
    @FXML private Label labelType;

    @FXML private AnchorPane anchorPaneCadastro;
    @FXML private AnchorPane anchorPaneDetalhes;

    @FXML private ComboBox<Types> comboBox;
    @FXML private TextField textFieldCpf;
    @FXML private TextField textFieldName;
    @FXML private PasswordField passwordField;
    @FXML private AnchorPane anchorPane;

    @FXML private void createCadastro() throws SQLException{
        if(verifyField()){
            Idatabase database = Factory.getDatabase("postgres");
            Connection connection = database.connect();
            FuncionarioDAO funcionario = new FuncionarioDAO(connection);
           
            funcionario.create(comboBox.getValue().toString(), textFieldCpf.getText(), textFieldName.getText(), passwordField.getText());
            
            anchorPaneCadastro.setVisible(false);
            atulizarTableViews();
            anchorPaneDetalhes.setVisible(true);   
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert Fail");
            alert.setHeaderText("Preencha todos os campo");
            alert.setContentText("Tente novamente!");
            alert.show();
        }
    } 

    @FXML private void createDetalhes() throws IOException, SQLException{
        anchorPaneDetalhes.setVisible(false);
        anchorPaneCadastro.setVisible(true);  
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

    private void selectItem(Funcionario funcionario){
        labelId.setText(String.valueOf(funcionario.getId()));
        labelName.setText(funcionario.getName());
        labelCpf.setText(funcionario.getCpf());
        labelPassword.setText(funcionario.getPassword());
        labelType.setText(funcionario.getCargo());
    }

    private boolean verifyField(){
        String name =  textFieldName.getText();
        String password = passwordField.getText();
        boolean verifyField = (!name.isEmpty() && !password.isEmpty() && verifyCpf() && verifyComboBox()) ? true : false;
        return verifyField;
    }

    private Boolean verifyCpf(){
        String cpf = textFieldCpf.getText();
        boolean verifyCPF = (!cpf.isEmpty() && cpf.length() == 11 && cpf.matches("[0-9]+")) ? true : false;
        return verifyCPF;
    }

    private Boolean verifyComboBox(){
        boolean verifyComboBox = !(comboBox.getValue().toString().isEmpty()) ? true : false;
        return verifyComboBox;
    }

    private void printTypes() throws SQLException{       
        comboBox.setItems(type());
    }

    private ObservableList<Types> type() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        FuncionarioDAO funcionario = new FuncionarioDAO(connection);
        return FXCollections.observableArrayList(
            funcionario.selectCargos()
        );
    }
}
