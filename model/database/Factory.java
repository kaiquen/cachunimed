package model.database;

public class Factory {
    public static Idatabase getDatabase(String name) {
        if(name.equals("postgres")) {
            return new Postgres();
        }
        
        return null;
    }
}
