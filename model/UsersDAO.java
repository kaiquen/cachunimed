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
    public Integer select(String name, String password) throws SQLException {
        String sql = "select * from users where name=? and password=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setString(1, name);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
      
        if(rs.next()){
            return rs.getInt("type");
        }

        return 4;
    }
}
