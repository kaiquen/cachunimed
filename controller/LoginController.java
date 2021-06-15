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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.UsersDAO;
import model.database.Factory;
import model.database.Idatabase;

public class LoginController implements Initializable {

    @FXML private TextField textField;
    @FXML private PasswordField passwordField;
    @FXML private AnchorPane login;

    @FXML private void Enter() throws SQLException, IOException{
        final Idatabase database = Factory.getDatabase("postgres");
        final Connection connection = database.connect();
        final UsersDAO users = new UsersDAO(connection);
        final Integer type = users.select(textField.getText(), passwordField.getText());
        //type 1 = Diretor
        //type 2 = Medico
        //type 3 = Recepcionista

        switch(type) {
            case 1:
                login.getScene().getWindow().hide();
                
                Parent mainDiretor = FXMLLoader.load(getClass().getResource("/view/diretor/MainDiretor.fxml"));
                Scene sceneDiretor = new Scene(mainDiretor);

                Stage stageDiretor = new Stage();
                stageDiretor.setTitle("Diretor");
                stageDiretor.setScene(sceneDiretor);
                stageDiretor.show();
                break;

            case 2:
                login.getScene().getWindow().hide();
                    
                Parent mainMedico = FXMLLoader.load(getClass().getResource("/view/medico/MainMedico.fxml"));
                Scene sceneMedico = new Scene(mainMedico);

                Stage stageMedico = new Stage();
                stageMedico.setTitle("Médico");
                stageMedico.setScene(sceneMedico);
                stageMedico.show();
                break;
              
            case 3:
                System.out.println("3");
                break;

            default: 
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Conection Fail");
                alert.setHeaderText("Credencias inválidas");
                alert.setContentText("Tente novamente!");
                alert.show();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {  
    }
}
