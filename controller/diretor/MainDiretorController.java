package controller.diretor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainDiretorController implements Initializable{


    @FXML private void CreateFuncionario() throws IOException{
        AnchorPane createFuncionario = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/CreateFuncionario.fxml"));   
        Scene scene = new Scene(createFuncionario);
        Stage stage = new Stage();
        stage.setTitle("Create Funcionario");
        stage.setScene(scene);
        stage.show();     
        System.out.println("ok");
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
