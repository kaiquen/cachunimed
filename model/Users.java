package model;

public class Users {
    private  Integer id;
    private  String cpf;
    private  String name;
    private  String password;
    private  Integer type; 

    public Users(){};
   

    public Users(String cpf, String password, Integer type){
        this.cpf = cpf;
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
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
    
    public String getCpf() {
        return cpf;
    }
    
}


