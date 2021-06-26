package model;

import java.sql.Timestamp;
public class Agenda {
    private Integer cod;
    private Integer idHours;
    private Integer cod_funcionario_medico;
    private Integer cod_paciente;
    private String nome_funcionario_medico;
    private String nome_paciente;
    private Timestamp horario_consulta;
    private String ano;

    public Agenda(){}
    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }
    public Agenda(Integer id){
        setCod(id);
    }
    public Agenda(Timestamp horario_conulta){
        this.horario_consulta = horario_conulta;
    }
    public Agenda(String nameMedico, Integer idPaciente, Timestamp dateTime){
        setNome_funcionario_medico(nameMedico);;
        setCod_paciente(idPaciente);
        setHorario_consulta(dateTime);
    }

    public Integer getCod() {
        return cod;
    }
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Integer getCod_funcionario_medico() {
        return cod_funcionario_medico;
    }
    public void setCod_funcionario_medico(Integer cod_funcionario_medico) {
        this.cod_funcionario_medico = cod_funcionario_medico;
    }

    public Integer getCod_paciente() {
        return cod_paciente;
    }
    public void setCod_paciente(Integer cod_paciente) {
        this.cod_paciente = cod_paciente;
    }

    public String getNome_funcionario_medico() {
        return nome_funcionario_medico;
    }
    public void setNome_funcionario_medico(String nome_funcionario_medico) {
        this.nome_funcionario_medico = nome_funcionario_medico;
    }

    public String getNome_paciente() {
        return nome_paciente;
    }
    public void setNome_paciente(String nome_Paciente) {
        this.nome_paciente = nome_Paciente;
    }

    public Timestamp getHorario_consulta() {
        return horario_consulta;
    }
    public void setHorario_consulta(Timestamp horario_consulta) {
        this.horario_consulta = horario_consulta;
    }
    public Integer getIdHours() {
        return idHours;
    }
    public void setIdHours(Integer idHours) {
        this.idHours = idHours;
    }
   
    @Override
    public String toString() {
        return  horario_consulta.toString().substring(10,16) + " " + nome_paciente;
    }
    // tem que formatar hora
}
