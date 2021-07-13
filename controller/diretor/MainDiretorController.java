package controller.diretor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
public class MainDiretorController implements Initializable {
    @FXML private AnchorPane anchorPane;

    @FXML private void listFuncionarios() throws IOException {
        AnchorPane paneFuncionario = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/ListFuncionarios.fxml"));
        anchorPane.getChildren().setAll(paneFuncionario);
    }

    @FXML private void listPaciente() throws IOException {
        AnchorPane panePaciente = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/ListPaciente.fxml"));
        anchorPane.getChildren().setAll(panePaciente);
    }

    @FXML private void listGraphic() throws IOException {     
        AnchorPane panePaciente = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/Graphic.fxml"));
        anchorPane.getChildren().setAll(panePaciente);
    }
    
    @FXML private void listRelatorios() throws IOException {     
        AnchorPane panePaciente = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/diretor/Relatorios.fxml"));
        anchorPane.getChildren().setAll(panePaciente);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
