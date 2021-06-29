package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Agenda;
import model.Funcionario;


public class AgendaDAO {
    Connection connection;
    public AgendaDAO(Connection  connection){
        this.connection = connection;
    }
    
    public void createAgenda(Agenda agenda) throws SQLException{
        String sql = "insert into agenda_medico (cod_funcionario_medico, cod_paciente, horario_consulta) values (?, ?, ?)"; 
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, agenda.getCod_funcionario_medico());
        ps.setInt(2, agenda.getCod_paciente());
        ps.setTimestamp(3, agenda.getHorario_consulta());
        ps.execute();
    }

    public List<Agenda> selectAgendas(LocalDate newValue) throws SQLException{
        String sql = "select p.nome_paciente, a.* from agenda_medico as a inner join paciente as p on p.cod_paciente=a.cod_paciente where a.cod_funcionario_medico=? and (cast(a.horario_consulta as date)=?)"; 
        PreparedStatement ps = connection.prepareStatement(sql);
        Date dateSQL = Date.valueOf(newValue);
        ps.setInt(1, Funcionario.cod_medico);
        ps.setDate(2, dateSQL);
        System.out.println(Funcionario.cod_medico + "\n" + dateSQL);
        ResultSet  rs = ps.executeQuery();
        List<Agenda> agendas = new ArrayList<>();
        while(rs.next()){
            Agenda agenda = new Agenda();
            agenda.setCod_paciente(rs.getInt("cod_paciente"));
            agenda.setNome_paciente(rs.getString("nome_paciente"));
            agenda.setCod_funcionario_medico(rs.getInt("cod_funcionario_medico"));
            agenda.setHorario_consulta(rs.getTimestamp("horario_consulta"));
          
            agendas.add(agenda);
        }
        return agendas;
    }

    public void deleteConsulta(Agenda agenda) throws SQLException{
        String sql = "delete from agenda_medico where cod_funcionario_medico=? and cod_paciente=? and horario_consulta=?";
       
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,  Funcionario.cod_medico);
        ps.setInt(2, agenda.getCod_paciente());
        ps.setTimestamp(3, agenda.getHorario_consulta());
        ps.execute();
    }

    public Integer selectIdMedico(Agenda agenda) throws SQLException{
        String sql = "select cod_funcionario from funcionario where nome_funcionario=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, agenda.getNome_funcionario_medico());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("cod_funcionario");
    }
    
    public ArrayList selectYear() throws SQLException{
        String sql = "select distinct extract(year from horario_consulta) as ano from agenda_medico";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList agendas = new ArrayList<>();
        while(rs.next()){
            agendas.add(rs.getInt("ano"));
        }
        return agendas;
    }
    public Map<Integer, ArrayList> graphic(Integer date) throws SQLException{
        String sql = "select count(cod_agenda_medico), extract(year from horario_consulta)as ano, extract(month from horario_consulta) as mes from agenda_medico where extract(year from horario_consulta)=? group by ano,mes order by ano,mes";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,date);
        
        Map<Integer, ArrayList> graphic = new HashMap<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            ArrayList rows = new ArrayList<>();
            if(!graphic.containsKey(rs.getInt("mes"))){
                rows.add(rs.getInt("mes"));
                rows.add(rs.getInt("count"));
                graphic.put(rs.getInt("mes"), rows);
            }else {
                ArrayList newRows = graphic.get(rs.getInt("ano"));
                newRows.add(rs.getInt("mes"));
                newRows.add(rs.getInt("count"));
            }
        }
        return graphic;
    }
    
    public List<Agenda> selectRelatorios() throws SQLException{
        String sql = "select a.cod_agenda_medico,  m.nome_funcionario as medico, p.nome_paciente as paciente, a.horario_consulta as horario_consulta from agenda_medico as a inner join funcionario as m on m.cod_funcionario=a.cod_funcionario_medico inner join paciente as p on p.cod_paciente=a.cod_paciente order by horario_consulta"; 
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet  rs = ps.executeQuery();
        
        List<Agenda> agendas = new ArrayList<>();
        while(rs.next()){
            Agenda agenda = new Agenda();
            agenda.setCod(rs.getInt("cod_agenda_medico"));
            agenda.setNome_funcionario_medico(rs.getString("medico"));
            agenda.setNome_paciente(rs.getString("paciente"));
            agenda.setHorario_consulta(rs.getTimestamp("horario_consulta"));
            agendas.add(agenda);
        }
        return agendas;
    }
}
