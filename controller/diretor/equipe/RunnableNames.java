package controller.diretor.equipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import controller.diretor.equipe.*;

public class RunnableNames implements Runnable {
    private int posi;
    
    private List<String> pessoas = new ArrayList<String>();
    
    private Label label;

    public RunnableNames(Label label) {
        this.label = label;
    }

    @Override
    public void run() {

        try {
            Equipe grupoList = new Equipe("34.125.46.96", 12345, 7);
            this.pessoas = grupoList.grupoList;
            System.out.println(this.pessoas);
            System.out.println(grupoList.grupoList.size());
            for (this.posi = 0; this.posi < grupoList.grupoList.size(); this.posi++) {
                System.out.println(this.posi);
                
                Platform.runLater(() -> this.label.setText(this.pessoas.get(this.posi)));
                
                Thread.sleep(2000);
            }
            Thread.interrupted();

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
