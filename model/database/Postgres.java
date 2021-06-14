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
            this.connection = DriverManager.getConnection("url", "user", "password");
            return this.connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
        return null;
    }

    @Override
    public void desconnect(Connection Connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(Postgres.class.getName()).log(Level.SEVERE,null,e);
        }
        
    }
    
}
