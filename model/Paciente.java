package model;

public class Paciente {
    private Integer id;
    private String name;
    private String cpf;
    private String fone;
    private String address; // talvez tenha que criar uma tabela endereço
    
    public Paciente(){}
    public Paciente(Integer id){
        this.id = id;
    }
    public Paciente(String name){
        this.name = name;
    }
    public Paciente(Integer id,String name, String fone, String address){
        this.id = id;
        this.name = name;
        this.fone = fone;
        this.address = address;
    }
  
    public Paciente(String cpf, String name, String fone, String address){
        boolean verifyCPF = (!cpf.isEmpty() && cpf.length() == 11 && cpf.matches("[0-9]+"));
        boolean verifyName= !name.isEmpty();
        boolean verifyFone =  !fone.isEmpty() && fone.matches("[0-9]+");
        //boolean verifyaddress = !address.isEmpty();

        String err = "";
        if(verifyCPF)
        setCpf(cpf);
            else err += "CPF inválido!\n";
        if(verifyName)setName(name);
            else err += "Name obidatório!\n";
        if(verifyFone)setFone(fone);
            else err += "Telefone obrigatorio!\n";

        setAddress(address);
        if(!verifyCPF || !verifyFone || !verifyName)throw new RuntimeException(err);

    }
    public Paciente(String name, String fone, String address) {
        boolean verifyName= !name.isEmpty();
        boolean verifyFone =  !fone.isEmpty() && fone.matches("[0-9]+");
        //boolean verifyaddress = !address.isEmpty();
        String err = "";

        if(verifyName)setName(name);
            else err += "Name obidatório!\n";
        if(verifyFone)setFone(fone);
            else err += "Telefone obrigatorio!\n";

        setAddress(address);

        if(!verifyFone || !verifyName)throw new RuntimeException(err);
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override

    public String toString() {
        return  name;
    }

}
