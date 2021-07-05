package model;

import javafx.scene.control.ComboBox;

public class Funcionario {
    private Integer cod;
    private String cpf;
    private String nome;
    private String senha;
    private String cargo;
    private Integer id_cargo;

    private ComboBox<Types> comboBoxCargo;
    static public Integer cod_medico;

    public Funcionario() {
    }

    public Funcionario(Integer cod) {
        this.cod = cod;
    }

    public Funcionario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Funcionario(Integer id, String name, String senha) {
        setCod(id);
        boolean verifyName = !name.isEmpty() && name.length() <= 50
                && name.toLowerCase().matches("^[a-zA-Z\u00C0-\u017F´]+\s+[a-zA-Z\u00C0-\u017F´]{0,}$");
        boolean verifyPasword = !senha.isEmpty();
        String err = "";
        if (verifyName)
            setNome(name);
        else
            err += "Preencha o campo nome!\n";
        if (verifyPasword)
            setSenha(senha);
        else
            err += "Preencha o campo senha!";
        if (!verifyName || !verifyPasword)
            throw new RuntimeException(err);
    }

    public Funcionario(ComboBox<Types> comboBoxCargo, String cpf, String name, String senha) {
        boolean verifyCPF = (!cpf.isEmpty() && cpf.length() == 11 && cpf.matches("[0-9]+"));
        boolean verifyComboBox = !(comboBoxCargo.getSelectionModel().isEmpty());
        System.out.println(name.length());
        boolean verifyName = !name.isEmpty() && name.length() <= 50
                && name.toLowerCase().matches("^[a-zA-Z\u00C0-\u017F´]+\s+[a-zA-Z\u00C0-\u017F´]{0,}$");
        boolean verifyPasword = !senha.isEmpty();
        String err = "";
        if (verifyComboBox)
            setCargo(comboBoxCargo.getValue().toString());
        else
            err += "Selecione uma opção!\n";
        if (verifyCPF)
            setCpf(cpf);
        else
            err = "CPF inválido!\n";
        if (verifyName)
            setNome(name);
        else
            err += "Preencha o campo nome!\n";
        if (verifyPasword)
            setSenha(senha);
        else
            err += "Preencha o campo senha!";
        if (!verifyCPF || !verifyComboBox || !verifyName || !verifyPasword)
            throw new RuntimeException(err);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(Integer id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setComboBox(ComboBox<Types> comboBoxCargo) {
        this.comboBoxCargo = comboBoxCargo;
    }

    public ComboBox<Types> getComboBox() {
        return comboBoxCargo;
    }

    @Override
    public String toString() {
        return nome;
    }
}
