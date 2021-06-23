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
        String sql = "insert into agenda (idmedico, idpaciente, datetime) values (?, ?, ?)"; 
        PreparedStatement ps = connection.prepareStatement(sql);
      
        ps.setInt(1, agenda.getIdMedico());
        ps.setInt(2, agenda.getIdPaciente());
        ps.setTimestamp(3, agenda.getDateTime());
        ps.execute();
    }

    public List<Agenda> selectAgendas(LocalDate newValue) throws SQLException{
        String sql = "select p.name, a.datetime from agenda as a inner join paciente as p on p.id=a.idpaciente where a.idmedico=? and (cast(a.datetime as date)=?)"; 
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
            agenda.setDateTime(rs.getTimestamp("datetime"));
            agendas.add(agenda);
        }
        return agendas;
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
