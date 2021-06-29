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

    public Funcionario selectFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "select * from funcionario where cpf_funcionario=? and senha_funcionario=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, funcionario.getCpf());
        ps.setString(2, funcionario.getSenha());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Funcionario.cod_medico = rs.getInt("cod_funcionario");
            funcionario.setId_cargo(rs.getInt("id_cargo_funcionario"));
        }
        return funcionario;
    }

    public List<Funcionario> selectFuncionarios() throws SQLException {
        String sql = "select f.*, c.nome_cargo from funcionario as f inner join cargo_funcionario as c on f.id_cargo_funcionario=c.id_cargo_funcionario;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setCod(rs.getInt("cod_funcionario"));
            funcionario.setNome(rs.getString("nome_funcionario"));
            funcionario.setCpf(rs.getString("cpf_funcionario"));
            funcionario.setSenha(rs.getString("senha_funcionario"));
            funcionario.setId_cargo(rs.getInt("id_cargo_funcionario"));
            funcionario.setCargo(rs.getString("nome_cargo"));
            funcionarios.add(funcionario);
        }
        return funcionarios;
    }

    public List<Types> selectCargos() throws SQLException {
        String sql = "select nome_cargo from cargo_funcionario where nome_cargo!='Diretor'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Types> types = new ArrayList<>();
        while (rs.next()) {
            Types type = new Types();
            type.setTypes(rs.getString("nome_cargo"));

            types.add(type);
        }
        return types;
    }

    public void createFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "insert into funcionario (id_cargo_funcionario,cpf_funcionario, nome_funcionario, senha_funcionario) values ((select id_cargo_funcionario from cargo_funcionario where nome_cargo=?),?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, funcionario.getCargo());
        ps.setString(2, funcionario.getCpf());
        ps.setString(3, funcionario.getNome());
        ps.setString(4, funcionario.getSenha());
        ps.execute();
    }

    public void delete(Funcionario funcionario) throws SQLException {
        String sql = "delete from funcionario where cod_funcionario=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, funcionario.getCod());
        ps.execute();
    }

    public void updateFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "update funcionario set nome_funcionario=?, senha_funcionario=? where cod_funcionario=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, funcionario.getNome());
        ps.setString(2, funcionario.getSenha());
        ps.setInt(3, funcionario.getCod());
        ps.execute();

    }

    public List<Funcionario> selectMedicos() throws SQLException {
        String sql = "select nome_funcionario, cod_funcionario from funcionario where id_cargo_funcionario=2";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(rs.getString("nome_funcionario"));
            funcionario.setCod(rs.getInt("cod_funcionario"));
            funcionarios.add(funcionario);
        }
        return funcionarios;
    }
}
