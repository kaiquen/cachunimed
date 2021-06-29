package model;

public class Types {
    private String type;

    public void setTypes(String type) {
        this.type = type;
    }

    public String getTypes() {
        return type;
    }

    @Override
    public String toString() {
        return getTypes();
    }
}