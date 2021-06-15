package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersDAO {
    Connection connection;
    public UsersDAO(Connection connection){
        this.connection = connection;
    }
    public Integer select(String cpf, String password) throws SQLException {
        String sql = "select * from users where cpf=? and password=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, cpf);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
      
        if(rs.next()){
            return rs.getInt("type");
        }

        return 4;
    }

    public void create(String type, String cpf, String name,String password) throws SQLException{
        String sql = "insert into users (cpf, type, name, password) values (?,?,?,?)";
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
