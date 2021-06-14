package model.database;

import java.sql.Connection;

public interface Idatabase {
    public Connection connect();
    public void desconnect(Connection connection);
}
