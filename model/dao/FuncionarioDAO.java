package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;
import model.Types;

public class FuncionarioDAO {
    Connection connection;

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer login(Funcionario funcionario) throws SQLException {
        String sql = "select * from funcionario where cpf=? and password=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, funcionario.getCpf());
        ps.setString(2, funcionario.getPassword());

        ResultSet rs = ps.executeQuery();

        if (rs.next())
            return rs.getInt("type");

        return 4;
    }

    public List<Funcionario> selectFuncionarios() throws SQLException{
        String sql = "select f.*, c.cargo from funcionario as f inner join cargo as c on f.type=c.id;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Funcionario> funcionarios = new ArrayList<>();

        while (rs.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getInt("id"));
            funcionario.setName(rs.getString("name"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setPassword(rs.getString("password"));
            funcionario.setType(rs.getInt("type"));
            funcionario.setCargo(rs.getString("cargo"));
            funcionarios.add(funcionario);
        }

        return funcionarios;
    } 
    
    public List<Types> selectCargos() throws SQLException{
        String sql = "select cargo from cargo where cargo!='Diretor'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Types> types = new ArrayList<>();

        while (rs.next()) {
            Types type = new Types();
            type.setTypes(rs.getString("cargo"));

            types.add(type);
        }
        return types;
    }
    //******************************************** Alterar SQL e SelecIdCargo
    public void create(Funcionario funcionario) throws SQLException{
        String sql = "insert into funcionario (cpf, type, name, password) values (?,?,?,?)"; //Tem q alterar
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, funcionario.getCpf());
        ps.setInt(2, selectIdCargo(funcionario)); //Tem que alterar 
        ps.setString(3, funcionario.getName());
        ps.setString(4, funcionario.getPassword());
        ps.execute();
    }
    //*******************************************
    public void delete(Funcionario funcionario) throws SQLException{
        String sql = "delete from funcionario where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, funcionario.getId());
        ps.execute();

        /*
         * Alert alert = new Alert(Alert.AlertType.WARNING);
         * alert.setTitle("Confirmação de Remoção");
         * alert.setHeaderText("Tem certeza que deseja excluir?");
         * alert.setContentText(""); alert.show();
         * 
         */
    }

    public void update(Funcionario funcionario) throws SQLException{
        String sql = "update funcionario set name=?, password=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, funcionario.getName());
        ps.setString(2, funcionario.getPassword());
        ps.setInt(3, funcionario.getId());
        ps.execute();

    }



    //Excluir 
    public Integer selectIdCargo(Funcionario funcionario) throws SQLException{
        String sql = "select id from cargo where cargo=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, funcionario.getCargo());

        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getInt("id");
    }
}
