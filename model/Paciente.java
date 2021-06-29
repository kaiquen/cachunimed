package model;

public class Paciente {
    private Integer cod;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco; // talvez tenha que criar uma tabela endereço

    public Paciente() {
    }

    public Paciente(Integer cod) {
        this.cod = cod;
    }

    public Paciente(String nome) {
        this.nome = nome;
    }

    public Paciente(String nome, String telefone, String endereco) {
        boolean verifynome = !nome.isEmpty()
                && nome.toLowerCase().matches("^[a-zA-Z\u00C0-\u017F´]+\s+[a-zA-Z\u00C0-\u017F´]{0,}$");
        boolean verifytelefone = !telefone.isEmpty() && telefone.matches("[0-9]+");
        boolean verifyendereco = !endereco.isEmpty();
        String err = "";
        if (verifynome)
            setNome(nome);
        else
            err += "nome obidatório!\n";
        if (verifytelefone)
            setTelefone(telefone);
        else
            err += "Telefone obrigatorio!\n";
        if (verifyendereco)
            setEndereco(endereco);
        else
            err += "endereco obrigatorio!\n";
        if (!verifytelefone || !verifynome || !verifyendereco)
            throw new RuntimeException(err);
    }

    public Paciente(Integer cod, String nome, String telefone, String endereco) {
        this.cod = cod;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Paciente(String cpf, String nome, String telefone, String endereco) {
        boolean verifyCPF = (!cpf.isEmpty() && cpf.length() == 11 && cpf.matches("[0-9]+"));
        boolean verifynome = !nome.isEmpty() && nome.length() < 50
                && nome.toLowerCase().matches("^[a-zA-Z\u00C0-\u017F´]+\s+[a-zA-Z\u00C0-\u017F´]{0,}$");
        boolean verifytelefone = !telefone.isEmpty() && telefone.matches("[0-9]+");
        boolean verifyendereco = !endereco.isEmpty();
        String err = "";
        if (verifyCPF)
            setCpf(cpf);
        else
            err += "CPF inválido!\n";
        if (verifynome)
            setNome(nome);
        else
            err += "Name obidatório!\n";
        if (verifytelefone)
            setTelefone(telefone);
        else
            err += "Telefone obrigatorio!\n";
        if (verifyendereco)
            setEndereco(endereco);
        else
            err += "endereco obrigatorio!\n";
        if (!verifyCPF || !verifytelefone || !verifynome || !verifyendereco)
            throw new RuntimeException(err);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return nome;
    }
}
