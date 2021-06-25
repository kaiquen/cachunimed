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
        String sql = "select * from paciente";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Paciente> pacientes = new ArrayList<>(); 
        while(rs.next()){
            Paciente paciente = new Paciente();
            paciente.setCod(rs.getInt("cod_paciente"));
            paciente.setNome(rs.getString("nome_paciente"));
            paciente.setCpf(rs.getString("cpf_paciente"));
            paciente.setTelefone(rs.getString("telefone_paciente"));
            paciente.setEndereco(rs.getString("endereco_paciente"));
            
            pacientes.add(paciente);
        }
        return pacientes;
    }

    public List<Paciente> selectPaciente(Paciente paciente) throws SQLException{
        String sql = "select * from paciente where nome_paciente like '%" + paciente.getNome() + "%'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Paciente> pacientes = new ArrayList<>();
        while(rs.next()){
            Paciente pacienteAux = new Paciente();
            pacienteAux.setCod(rs.getInt("cod_paciente"));
            pacienteAux.setNome(rs.getString("nome_paciente"));
            pacienteAux.setCpf(rs.getString("cpf_paciente"));
            pacienteAux.setTelefone(rs.getString("telefone_paciente"));
            pacienteAux.setEndereco(rs.getString("endereco_paciente"));
            pacientes.add(pacienteAux);
        }
        return pacientes;
    }

    public void createPaciente(Paciente paciente) throws SQLException{
        String sql = "insert into paciente (cpf_paciente, nome_paciente, telefone_paciente, endereco_paciente) values (?,?,?,?)"; 
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, paciente.getCpf());
        ps.setString(2, paciente.getNome()); 
        ps.setString(3, paciente.getTelefone());
        ps.setString(4, paciente.getEndereco());
        ps.execute();
    }

    public void delete(Paciente paciente) throws SQLException{
        String sql = "delete from paciente where cod_paciente=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, paciente.getCod());
        ps.execute();
    }
    
    public void update(Paciente paciente) throws SQLException{
        String sql = "update paciente set nome_paciente=?, telefone_paciente=?, endereco_paciente=? where cod_paciente=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, paciente.getNome());
        ps.setString(2, paciente.getTelefone());
        ps.setString(3, paciente.getEndereco());
        ps.setInt(4, paciente.getCod());
        ps.execute();
    }
}
