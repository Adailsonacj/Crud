package CRUD;

import java.sql.*;

public class Conexao {
    
    public static Connection conexao() throws SQLException, ClassNotFoundException{
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/academica?useSSL=false", "root","root");
            return conexao;
        }
        
        catch(SQLException error){
         }
		return null;
    }
}
