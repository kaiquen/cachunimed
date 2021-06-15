import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        Scene scene = new Scene(login);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(scene);
        
        //**********
        Image image = new Image("view/image/logo.jpg");
        stage.getIcons().add(image);
        
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}