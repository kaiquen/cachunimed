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

    public Integer select(String cpf, String password) throws SQLException {
        String sql = "select * from funcionario where cpf=? and password=?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, cpf);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next())
            return rs.getInt("type");

        return 4;
    }

    public List<Funcionario> selectFuncionarios() throws SQLException {
        String sql = "select * from funcionario inner join cargo on funcionario.type=cargo.id";
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

    public List<Types> selectCargos() throws SQLException {
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

    public void create(String type, String cpf, String name, String password) throws SQLException {
        String sql = "insert into funcionario (cpf, type, name, password) values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, cpf);
        ps.setInt(2, selectIdCargo(type));
        ps.setString(3, name);
        ps.setString(4, password);
        ps.execute();
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from funcionario where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();

        /*
         * Alert alert = new Alert(Alert.AlertType.WARNING);
         * alert.setTitle("Confirmação de Remoção");
         * alert.setHeaderText("Tem certeza que deseja excluir?");
         * alert.setContentText(""); alert.show();
         * 
         */
    }

    public void update(Integer id, String name, String password) throws SQLException {
        String sql = "update funcionario set name=?, password=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, password);
        ps.setInt(3, id);
        ps.execute();

    }

    public Integer selectIdCargo(String cargo) throws SQLException {
        String sql = "select id from cargo where cargo=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, cargo);

        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getInt("id");
    }
}
