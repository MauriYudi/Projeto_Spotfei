/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author unifmkuniyoshi
 */
public class Conexao {
    public Connection getConnection() throws SQLException{
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://"
                + "aws-0-sa-east-1.pooler.supabase.com:6543/postgres",
                "postgres.wrviautrpliotezcdmbf","Yud1nho04_YVSXPAP");
        return conexao;
    }
}
