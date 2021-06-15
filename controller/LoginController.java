package controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import model.database.Factory;
import model.database.Idatabase;

public class LoginController implements Initializable {

    public final Idatabase database = Factory.getDatabase("postgres");
    public final Connection connection = database.connect();
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
}
