package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Funcionario;
import model.dao.FuncionarioDAO;
import model.database.Factory;
import model.database.Idatabase;

public class LoginController implements Initializable {
        Image image = new Image("view/images/64x64.png");
    @FXML private TextField textField;
    @FXML private PasswordField passwordField;
    @FXML private AnchorPane login;

    @FXML private void Enter() throws SQLException, IOException {
        Idatabase database = Factory.getDatabase("postgres");
        Connection connection = database.connect();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
        try {
            Funcionario funcionario = new Funcionario(textField.getText(), passwordField.getText());
            funcionario = (Funcionario) funcionarioDAO.selectFuncionario(funcionario);
            System.out.println(funcionario.getId_cargo());
            switch (funcionario.getId_cargo()) {
                case 1:
                    login.getScene().getWindow().hide();
                    Parent mainDiretor = FXMLLoader.load(getClass().getResource("/view/diretor/MainDiretor.fxml"));
                    Scene sceneDiretor = new Scene(mainDiretor);
                    sceneDiretor.getStylesheets().add("/view/style.css");
                    Stage stageDiretor = new Stage();
                    stageDiretor.setTitle("Diretor");
                    stageDiretor.setScene(sceneDiretor);
                    stageDiretor.getIcons().add(image);
                    stageDiretor.show();
                break;
    
                case 2:
                    login.getScene().getWindow().hide();
                    Parent mainMedico = FXMLLoader.load(getClass().getResource("/view/medico/MainMedico.fxml"));
                    Scene sceneMedico = new Scene(mainMedico);
                    sceneMedico.getStylesheets().add("/view/style.css");
                    Stage stageMedico = new Stage();
                    stageMedico.setTitle("Medico");
                    stageMedico.setScene(sceneMedico);
                    stageMedico.getIcons().add(image);
                    stageMedico.show();
                break;
    
                case 3:
                    login.getScene().getWindow().hide();
                    Parent mainRecepcionista = FXMLLoader.load(getClass().getResource("/view/Recepcionista/MainRecepcionista.fxml"));
                    Scene sceneRecepcionista = new Scene(mainRecepcionista); 
                    sceneRecepcionista.getStylesheets().add("/view/style.css");
                    Stage stageRecepcionista = new Stage();
                    stageRecepcionista.setTitle("Recepcionista");
                    stageRecepcionista.setScene(sceneRecepcionista);
                    stageRecepcionista.getIcons().add(image);
                    stageRecepcionista.show();
                break;    
            }
        } catch (Exception e) {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Connection Failed");
            alert.setHeaderText("Credenciais inválidas");
            alert.setContentText("Tente novamente!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
