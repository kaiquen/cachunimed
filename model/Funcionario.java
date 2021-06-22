package model;


import javafx.scene.control.ComboBox;

public class Funcionario {
    private  Integer id;
    private  String cpf;
    private  String name;
    private  String password;
    private  Integer type; 
    private  String cargo; // Tem que alterar ... cargo == combobox
    private ComboBox<Types> comboBox;
    static public Integer idLogin;
    public Funcionario(){};
   
    public Funcionario(Integer id){
        this.id= id;
        
    }
    public Funcionario(String cpf, String password){
        this.cpf = cpf; 
        this.password = password;
    };
    

    public Funcionario(Integer id, String name, String password){
        setId(id);
        boolean verifyName= !name.isEmpty();
        boolean verifyPasword =  !password.isEmpty();

        String err= "";
        if(verifyName) setName(name);
            else err += "Preencha o campo nome!\n";
        if(verifyPasword) setPassword(password);
            else err += "Preencha o campo senha";

        if (!verifyName || !verifyPasword)throw new RuntimeException(err);
    };
    
    public Funcionario(ComboBox<Types> comboBox, String cpf, String name, String password){
        boolean verifyCPF = (!cpf.isEmpty() && cpf.length() == 11 && cpf.matches("[0-9]+"));
        boolean verifyComboBox = !(comboBox.getSelectionModel().isEmpty());
        boolean verifyName= !name.isEmpty();
        boolean verifyPasword =  !password.isEmpty();

        String err= "";

        if(verifyComboBox) setCargo(comboBox.getValue().toString());
            else err += "Selecione o tipo!\n";
        if(verifyCPF) setCpf(cpf);
            else err="CPF invalido!\n";
        if(verifyName) setName(name);
            else err += "Preencha o campo nome!\n";
        if(verifyPasword) setPassword(password);
            else err += "Preencha o campo senha";

        if(!verifyCPF || !verifyComboBox || !verifyName || !verifyPasword)throw new RuntimeException(err);
    }
  
    public void setId(Integer id) {
        this.id = id;
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

    public void setCargo(String cargo){
        this.cargo = cargo;
    }
    public void setComboBox(ComboBox<Types> comboBox) {
        this.comboBox = comboBox;
    }
    public ComboBox<Types> getComboBox() {
        return comboBox;
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

    public String getCargo(){
        return cargo;
    }

    @Override
    public String toString() {
        return name;
    }
}


