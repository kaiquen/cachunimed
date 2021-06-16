package controller.diretor;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Funcionario;
import model.dao.FuncionarioDAO;
import model.database.Factory;
import model.database.Idatabase;

public class ListRecepcionistaController implements Initializable {
    @FXML private TableView<Funcionario> tableView;
    @FXML private TableColumn<Funcionario, Integer> tableColumnId;
    @FXML private TableColumn<Funcionario, String> tableColumnName;
    @FXML private Label labelId;
    @FXML private Label labelName;
    @FXML private Label labelCpf;
    @FXML private Label labelPassword;
    
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
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.setItems(lista());
    }

    private ObservableList<Funcionario> lista() throws SQLException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        FuncionarioDAO funcionario = new FuncionarioDAO(connection);
        
        return FXCollections.observableArrayList(
            funcionario.selectRecepcionista()
        );
    }
}
