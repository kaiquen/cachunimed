package controller.diretor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.Types;

public class CreateFuncionarioController implements Initializable{


    @FXML ComboBox<Types> comboBox;
    
 
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {  
        printTypes();

    }

    public void printTypes(){
        List<Types> type = new ArrayList<>();
        ObservableList<Types> types;
    
        Types type1 = new Types("Diretor");
        Types type2 = new Types("Médico");
        Types type3 = new Types("Recepcionista");

        type.add(type1);
        type.add(type2);
        type.add(type3);

        types = FXCollections.observableArrayList(type);
        comboBox.setItems(types);
    }
}
