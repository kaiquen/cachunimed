package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Agenda;

public class AgendaDAO {
    Connection connection;
    public AgendaDAO(Connection  connection){
        this.connection = connection;
    }
    
    public void createAgenda(Agenda agenda) throws SQLException{
        String sql = "insert into agenda (namemedico, namepaciente, date) values (?,?,?)"; 
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, agenda.getNameMedico());
        ps.setString(2, agenda.getNamePaciente());
        ps.setDate(3, (Date) agenda.getDate());
        ps.execute();
    }
}
