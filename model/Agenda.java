package model;

import java.sql.Date;
import java.sql.Time;

public class Agenda {
    private Integer id;

    private Integer idHours;
    private Integer idMedico;
    private Integer idPaciente;
    private String nameMedico;
    private String namePaciente;
    private Date date;
    private Time time;
    
   
    public Agenda(){}
    public Agenda(Integer id){
        setIdMedico(id);
    }
    public Integer getIdHours() {
        return idHours;
    }
    public void setIdHours(Integer idHours) {
        this.idHours = idHours;
    }
    public Agenda(String nameMedico, Integer idPaciente, Time time, Date dateOk){
        setNameMedico(nameMedico);;
        setIdPaciente(idPaciente);
        setTime(time);
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
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return  time + " " + namePaciente;
    }
    // tem que formatar hora
    
}
