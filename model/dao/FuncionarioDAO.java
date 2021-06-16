package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;


public class FuncionarioDAO {
    Connection connection;
    public FuncionarioDAO(Connection connection){
        this.connection = connection;
    }

    public Integer select(String cpf, String password) throws SQLException {
        String sql = "select * from funcionario where cpf=? and password=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, cpf);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
      
        if(rs.next()){
            return rs.getInt("type");
        }
        return 4;
    }

    public List<Funcionario> selectMedico() throws SQLException{
        String sql = "select * from funcionario where type=2";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Funcionario> funcionarios = new ArrayList<>();

        while(rs.next()){
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getInt("id"));
            funcionario.setName(rs.getString("name"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setPassword(rs.getString("password"));
            funcionarios.add(funcionario);
        }

        return funcionarios;
    } 
    public List<Funcionario> selectRecepcionista() throws SQLException{
        String sql = "select * from funcionario where type=3";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Funcionario> funcionarios = new ArrayList<>();

        while(rs.next()){
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getInt("id"));
            funcionario.setName(rs.getString("name"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setPassword(rs.getString("password"));
            funcionarios.add(funcionario);
        }

        return funcionarios;
    } 
    public void create(String type, String cpf, String name,String password) throws SQLException{
        String sql = "insert into funcionario (cpf, type, name, password) values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, cpf);
        ps.setInt(2, ConverterType(type));
        ps.setString(3, name);
        ps.setString(4, password);
        ps.execute();

        System.out.println("Criado!!!!!");
    }

    private Integer ConverterType(String type){
        if(type.equals("Diretor")){
            return 1;
        }else if(type.equals("Médico")){
            return 2;
        }else if(type.equals("Recepcionista")){
            return 3;
        }
        return null;
    }
}
