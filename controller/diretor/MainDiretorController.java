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

    @FXML private void listMedico() throws IOException{
        AnchorPane paneMedico = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/ListMedico.fxml"));   
        anchorPane.getChildren().setAll(paneMedico);
    }
    @FXML private void listRecepcionista() throws IOException{
        AnchorPane paneRecepcionista = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/ListRecepcionista.fxml"));   
        anchorPane.getChildren().setAll(paneRecepcionista);
    }
    @FXML private void createFuncionario() throws IOException{
        AnchorPane paneCadastro = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/CreateFuncionario.fxml"));   
        anchorPane.getChildren().setAll(paneCadastro);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
