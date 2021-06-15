package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UsersDAO;
import model.database.Factory;
import model.database.Idatabase;

public class LoginController implements Initializable {

    @FXML private TextField textField;
    @FXML private PasswordField passwordField;

    @FXML private void Enter() throws SQLException{
        final Idatabase database = Factory.getDatabase("postgres");
        final Connection connection = database.connect();
        final UsersDAO users = new UsersDAO(connection);
       
        Integer type = users.select(textField.getText(), passwordField.getText());
        //type 1 = Diretor
        //type 2 = Medico
        //type 3 = Recepcionista

        switch(type) {
            case 1:
                System.out.println("1");
                break;

            case 2:
                System.out.println("2");
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
