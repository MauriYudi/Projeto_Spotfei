/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Usuario;
import model.Playlist;
/**
 *
 * @author unifmkuniyoshi
 */
public class DAO {
    private Connection conn;

    public DAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Usuario user) throws SQLException{
        String sql = "select * from \"user\" where usuario = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUsuario());
        statement.setString(2, user.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public ResultSet nome(Usuario user) throws SQLException{
        String sql = "select * from \"user\" where senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        String u = resultado.getString("usuario");
        System.out.println(u);
        System.out.println(u);
        return resultado;
    }
    
    public void inserir(Usuario user) throws SQLException{
        String sql = "insert into \"user\" (nome, usuario, senha) values ('"
                      + user.getNome()    + "', '"
                      + user.getUsuario() + "', '"
                      + user.getSenha()   + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    
    public void inserirPlaylist(Playlist play) throws SQLException{
        String sql = "insert into playlist (nome, usuario) values ('"
                      + play.getNome()    + "', '"
                      + play.getUser().getUsuario()   + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
}
