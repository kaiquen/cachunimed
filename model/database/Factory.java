package model.database;

public class Factory {
    public static Database getDatabase(String name) {
        if(name.equals("postgres")) {
            return new Postgres();
        }
        
        return null;
    }
}
