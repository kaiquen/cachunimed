package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Funcionario;
import model.dao.FuncionarioDAO;
import model.database.Factory;
import model.database.Idatabase;

public class LoginController implements Initializable {

    @FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private AnchorPane login;

    @FXML
    private void Enter() throws SQLException, IOException {
        final Idatabase database = Factory.getDatabase("postgres");
        final Connection connection = database.connect();
        final FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);

        Image image = new Image("view/images/64x64.png");
        Funcionario funcionario = new Funcionario(textField.getText(), passwordField.getText());
      
        switch (funcionarioDAO.login(funcionario)) {
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
                try {
                    login.getScene().getWindow().hide();
                    Stage stage = new Stage();
                    BorderPane root = new BorderPane();
                  
                    Scene scene = new Scene(root, 500, 500);
                    stage.setTitle("Médico");
                    stage.getIcons().add(image);
                   

                    DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
                 
                    Node calendar = datePickerSkin.getPopupContent();
                    
                    root.setCenter(calendar);
            
                    stage.setScene(scene);
                    stage.show(); 
                } 
              
                catch (Exception e) {
                    System.out.println(e);
                }
             
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

            default:
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
