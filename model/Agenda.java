package model;

import java.sql.Timestamp;

public class Agenda {
    private Integer id;

    private Integer idHours;
    private Integer idMedico;
    private Integer idPaciente;
    private String nameMedico;
    private String namePaciente;
    private Timestamp dateTime;
   
    public Agenda(){}
    public Timestamp getDateTime() {
        return dateTime;
    }
    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
    public Agenda(Integer id){
        setIdMedico(id);
    }
    public Integer getIdHours() {
        return idHours;
    }
    public void setIdHours(Integer idHours) {
        this.idHours = idHours;
    }
    public Agenda(String nameMedico, Integer idPaciente, Timestamp dateTime){
        setNameMedico(nameMedico);;
        setIdPaciente(idPaciente);
        setDateTime(dateTime);
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
    public String getNameMedico() {
        return nameMedico;
    }
    public void setNameMedico(String nameMedico) {
        this.nameMedico = nameMedico;
    }
    public void setNamePaciente(String namePaciente) {
        this.namePaciente = namePaciente;
    }
   
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return  dateTime.toString().substring(10,16) + " " + namePaciente;
    }
    // tem que formatar hora
    
}
