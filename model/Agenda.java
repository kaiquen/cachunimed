package model;

import java.sql.Date;



public class Agenda {
    private Integer idMedico;
    private Integer idPaciente;
    private String nameMedico;
    private String namePaciente;
    private Date date;
    
    public Agenda(String nameMedico, Integer idPaciente, Date dateOk){
        setNameMedico(nameMedico);;
        setIdPaciente(idPaciente);
        setDate(dateOk);
    }
    
    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

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

    @Override
    public String toString() {
        return nameMedico ;
    }
}
