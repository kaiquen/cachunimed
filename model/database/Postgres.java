package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Postgres implements Idatabase{
    private Connection connection;

    @Override
    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital", "postgres", "root");
           
            System.out.println("connected");
            return this.connection;
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void desconnect(Connection Connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
