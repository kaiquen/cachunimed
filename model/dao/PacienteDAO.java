package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Paciente;

public class PacienteDAO {
    Connection connection;
    public PacienteDAO(Connection connection){
        this.connection = connection;
    }

    public List<Paciente> selectPacientes() throws SQLException{
        String sql = "select * from Paciente";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Paciente> pacientes = new ArrayList<>(); 
        while(rs.next()){
            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("id"));
            paciente.setName(rs.getString("name"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setFone(rs.getString("fone"));
            paciente.setAddress(rs.getString("address"));
            
            pacientes.add(paciente);
        }

        return pacientes;
    }

    public List<Paciente> searchPaciente(Paciente paciente) throws SQLException{
        String sql = "select * from paciente where name like '%" + paciente.getName() + "%'";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        //ps.setString(1, paciente.getName());
        ResultSet rs = ps.executeQuery();
        List<Paciente> pacientes = new ArrayList<>();
        while(rs.next()){
            Paciente pacienteAux = new Paciente();
            pacienteAux.setId(rs.getInt("id"));
            pacienteAux.setName(rs.getString("name"));
            pacienteAux.setCpf(rs.getString("cpf"));
            pacienteAux.setFone(rs.getString("fone"));
            pacienteAux.setAddress(rs.getString("address"));
            pacientes.add(pacienteAux);
        }
       
        return pacientes;
    }

    public void create(Paciente paciente) throws SQLException{
        String sql = "insert into Paciente (cpf, name, fone, address) values (?,?,?,?)"; 
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, paciente.getCpf());
        ps.setString(2, paciente.getName()); 
        ps.setString(3, paciente.getFone());
        ps.setString(4, paciente.getAddress());
        ps.execute();
    }
    public void delete(Paciente paciente) throws SQLException{
        String sql = "delete from paciente where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, paciente.getId());
        ps.execute();
    }
    public void update(Paciente paciente) throws SQLException{
        String sql = "update paciente set name=?, fone=?, address=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, paciente.getName());
        ps.setString(2, paciente.getFone());
        ps.setString(3, paciente.getAddress());
        ps.setInt(4, paciente.getId());
        ps.execute();
    }
}
