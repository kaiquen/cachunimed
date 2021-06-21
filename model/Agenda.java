package model;

import java.util.Date;

public class Agenda {
    private String nameMedico;
    private String namePaciente;
    private Date date;
    
    public String getNamePaciente() {
        return namePaciente;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getNameMedico() {
        return nameMedico;
    }
    public void setNameMedico(String nameMedico) {
        this.nameMedico = nameMedico;
    }
    public void setNamePaciente(String namePaciente) {
        this.namePaciente = namePaciente;
    }
}
