package controller.diretor.equipe;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Equipe {
    public List<String> grupoList = new ArrayList<>();

    public  Equipe(String ip, int port, int numeroGrupo) throws IOException, ClassNotFoundException {
        this.grupoList = busca(ip, port, numeroGrupo);
    }

    public  List<String> busca(String ip, int port, int numeroGrupo) throws IOException, ClassNotFoundException {

        List<String> grupoList;
        try (Socket socket = new Socket(ip, port)) {
            ObjectInputStream entrada;
            try (DataOutputStream saida = new DataOutputStream(socket.getOutputStream())) {
                saida.writeInt(numeroGrupo);
                entrada = new ObjectInputStream(socket.getInputStream());
                grupoList = (List<String>) entrada.readObject();
            }
            entrada.close();
        }

        return grupoList;
    }
}
