package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Agenda;


public class AgendaDAO {
    Connection connection;
    public AgendaDAO(Connection  connection){
        this.connection = connection;
    }
    
    public void createAgenda(Agenda agenda) throws SQLException{
        String sql = "insert into agenda (idmedico, idpaciente, date) values (?,?,?)"; 
        PreparedStatement ps = connection.prepareStatement(sql);
      
        ps.setInt(1, agenda.getIdMedico());
        ps.setInt(2, agenda.getIdPaciente());
        ps.setDate(3, agenda.getDate());
        ps.execute();
    }
    public Integer selectIdMedico(Agenda agenda) throws SQLException{
        String sql = "select id from funcionario where name=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agenda.getNameMedico());
     
        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getInt("id");
    }
}
