package model;

public class Users {
    private Integer id;
    private String name;
    private String password;
    private Integer type; 

    public Users(){};
    public Users(String name, String password, Integer type){
        this.name = name;
        this.password = password;
        this.type = type;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getType(){
        return type;
    }
}
