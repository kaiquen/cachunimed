package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Agenda;
import model.Funcionario;


public class AgendaDAO {
    Connection connection;
    public AgendaDAO(Connection  connection){
        this.connection = connection;
    }
    
    public void createAgenda(Agenda agenda) throws SQLException{
        String sql = "insert into agenda (idmedico, idpaciente, idhours, date) values (?, ?, ?, ?)"; 
        PreparedStatement ps = connection.prepareStatement(sql);
      
        ps.setInt(1, agenda.getIdMedico());
        ps.setInt(2, agenda.getIdPaciente());
        ps.setInt(3, agenda.getIdHours());
        ps.setDate(4, agenda.getDate());
        ps.execute();
    }
    public List<Agenda> selectHours() throws SQLException{
        String sql = "select * from hours"; 
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet  rs = ps.executeQuery();

        List<Agenda> agendas = new ArrayList<>();

        while(rs.next()){
            Agenda agenda = new Agenda();
            agenda.setTime(rs.getTime("hour")); 
            agenda.setId(rs.getInt("id"));
            agendas.add(agenda);
        }
        return agendas;
    }

    public List<Agenda> selectAgendas(LocalDate newValue) throws SQLException{
        String sql = "select p.name, date, h.hour from agenda as a inner join paciente as p on p.id=a.idpaciente inner join hours as h on h.id=a.idhours where a.idmedico=? and a.date=?"; 
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, Funcionario.idLogin);
        Date dateSQL = Date.valueOf(newValue);
        ps.setDate(2, dateSQL);
        System.out.println(Funcionario.idLogin);
        ResultSet  rs = ps.executeQuery();

        List<Agenda> agendas = new ArrayList<>();
        while(rs.next()){
            Agenda agenda = new Agenda();
            agenda.setNamePaciente(rs.getString("name"));
            agenda.setDate(rs.getDate("date"));
            agenda.setTime(rs.getTime("hour")); 
            agendas.add(agenda);
        }
        return agendas;
    }

    
    public Integer selectIdHours(Agenda agenda) throws SQLException{
        String sql = "select id from hours where hour=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setTime(1, agenda.getTime());
     
        ResultSet rs = ps.executeQuery();
        rs.next();
        
        return rs.getInt("id");
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
