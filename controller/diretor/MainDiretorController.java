package controller.diretor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class MainDiretorController implements Initializable{

    @FXML private AnchorPane anchorPane;

    @FXML private void listFuncionarios() throws IOException{
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/ListFuncionarios.fxml"));   
        anchorPane.getChildren().setAll(pane);
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
