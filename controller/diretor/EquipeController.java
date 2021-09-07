package controller.diretor;



import java.net.URL;

import java.util.ResourceBundle;
import controller.diretor.equipe.RunnableNames;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class EquipeController implements Initializable{
    
    @FXML public Label equipe;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        RunnableNames names = new RunnableNames(equipe);
        Thread thread = new Thread(names);
        thread.start();
    }
}
