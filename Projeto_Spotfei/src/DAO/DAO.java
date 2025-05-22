/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import javax.swing.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import model.Usuario;
import model.Playlist;
import model.Musica;
import model.Artista;
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
        String sql = "insert into \"user\" (nome, usuario, senha) "
                + "values (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getNome());
        statement.setString(2, user.getUsuario());
        statement.setString(3, user.getSenha());
        statement.execute();
        conn.close();
    }
    
    public void inserirPlaylist(Playlist play) throws SQLException{
        String sql = "insert into playlist (nome, usuario) values (?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, play.getPlayNome());
        statement.setString(2, play.getUser().getUsuario());
        statement.execute();
        conn.close();
    }
    
    public ArrayList<String> listaMusicas (JTextField t) throws SQLException {
        ArrayList<String> musicas = new ArrayList<>();
        String sql = "SELECT nome FROM musica WHERE nome = ? OR genero = ? "
                + "OR artista = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, t.getText());
        statement.setString(2, t.getText());
        statement.setString(3, t.getText());

        ResultSet resultado = statement.executeQuery();
        ResultSetMetaData metadata = resultado.getMetaData();
        int Cont = metadata.getColumnCount();

        while (resultado.next()) {
            StringBuilder linha = new StringBuilder();
            for (int i = 1; i <= Cont; i++) {
                String valor = resultado.getString(i); 
                linha.append(valor);                
            }
        musicas.add(linha.toString());
        }
        return musicas;
    }
    
    
    public ArrayList<String> nomeMusica(JList<String> j) throws SQLException{
        ArrayList<String> musica = new ArrayList<>();
        String sql = "SELECT * FROM musica WHERE nome = ?";        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, j.getSelectedValue());
        
        ResultSet resultado = statement.executeQuery();
        
        if(resultado.next()){
            String nome = resultado.getString("nome");
            String artista = resultado.getString("artista");
            String genero = resultado.getString("genero");

            musica.add(nome);
            musica.add(artista);
            musica.add(genero);
        }
        
        return musica;
    }
    
    public ArrayList<String> listaPlaylists (Usuario user) throws SQLException{
        ArrayList<String> playlists = new ArrayList<>();
        String sql = "SELECT nome FROM playlist WHERE usuario = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUsuario());
        
        ResultSet resultado = statement.executeQuery();
        ResultSetMetaData metadata = resultado.getMetaData();
        int Cont = metadata.getColumnCount();

        while (resultado.next()) {
            StringBuilder linha = new StringBuilder();
            for (int i = 1; i <= Cont; i++) {
                String valor = resultado.getString(i); 
                linha.append(valor);
            }
        playlists.add(linha.toString());
        }
        return playlists;
    }
    
    public void inserirMusica(Musica m, Playlist p, Usuario u) 
            throws SQLException {
    String sql = "INSERT INTO musica_playlist (nome, playlist, usuario) "
            + "VALUES (?, ?, ?)";
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, m.getMusicaNome());
    statement.setString(2, p.getPlayNome());
    statement.setString(3, u.getUsuario());
    statement.executeUpdate();
}
    
    public void deletarPlaylist(Playlist p, Usuario u)throws SQLException{
        String sql = "DELETE FROM playlist WHERE nome = ? AND usuario = ?";
        String sql1 = "DELETE FROM musica_playlist WHERE playlist = ? "
                + "AND usuario = ?";
        PreparedStatement statement1 = conn.prepareStatement(sql);
        statement1.setString(1, p.getPlayNome());
        statement1.setString(2, u.getUsuario());
        statement1.executeUpdate();
        
        PreparedStatement statement2 = conn.prepareStatement(sql1);
        statement2.setString(1, p.getPlayNome());
        statement2.setString(2, u.getUsuario());
        statement2.execute();
        conn.close();
    }
    
    public ArrayList<String> musicasPlaylist(JList<String> j, Usuario u) 
            throws SQLException{
        ArrayList<String> musicasPlaylist = new ArrayList<>();
        String sql = "SELECT nome FROM musica_playlist where playlist = ? AND "
                + "usuario = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, j.getSelectedValue());
        statement.setString(2, u.getUsuario());
        
        ResultSet resultado = statement.executeQuery();
        ResultSetMetaData metadata = resultado.getMetaData();
        int Cont = metadata.getColumnCount();
        
        while (resultado.next()) {
            StringBuilder linha = new StringBuilder();
            for (int i = 1; i <= Cont; i++) {
                String valor = resultado.getString(i); 
                linha.append(valor);
            }
        musicasPlaylist.add(linha.toString());
        }
        return musicasPlaylist;
    }
    
    public void deletarMusica(JList<String> l) throws SQLException{
        String sql = "DELETE FROM musica_playlist WHERE nome = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, l.getSelectedValue());
        statement.executeUpdate();
        conn.close();
    }
    
    public void like(Usuario u, Musica m) throws SQLException{
        String sql = "insert into user_musica (usuario, musica, up) values "
                + "(?, ?, 'sim')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, u.getUsuario());
        statement.setString(2, m.getMusicaNome());
        statement.execute();
        
        String sql1 = "DELETE FROM user_musica WHERE usuario = ? AND musica = ?"
                + " AND down = 'sim'";
        PreparedStatement statement1 = conn.prepareStatement(sql1);
        statement1.setString(1, u.getUsuario());
        statement1.setString(2, m.getMusicaNome());
        statement1.executeUpdate();
        
        conn.close();
    }
    
    public void dislike(Usuario u, Musica m) throws SQLException{
        String sql = "insert into user_musica (usuario, musica, down) values "
                + "(?, ?, 'sim')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, u.getUsuario());
        statement.setString(2, m.getMusicaNome());
        statement.execute();
        
        String sql1 = "DELETE FROM user_musica WHERE usuario = ? AND musica = ?"
                + " AND up = 'sim'";
        PreparedStatement statement1 = conn.prepareStatement(sql1);
        statement1.setString(1, u.getUsuario());
        statement1.setString(2, m.getMusicaNome());
        statement1.executeUpdate();
        
        conn.close();
    }
    
    public void inserirHistorico(JList<String> l, Usuario u) 
            throws SQLException{
        String sql = "insert into historico (musica, usuario, data)"
                + " values (?, ?, CURRENT_TIMESTAMP)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, l.getSelectedValue());
        statement.setString(2, u.getUsuario());
        statement.execute();
        
        String sql1 = "DELETE FROM historico WHERE id IN (SELECT id FROM("
                + "SELECT id FROM historico WHERE usuario = ? "
                + "ORDER BY data DESC "
                + "OFFSET 10)AS antigos)";
        PreparedStatement statement1 = conn.prepareStatement(sql1);
        statement1.setString(1, u.getUsuario());
        statement1.executeUpdate();
        
        conn.close();
    }
    
    public ArrayList<String>listaHistorico(Usuario u) throws SQLException{
        ArrayList<String> historico = new ArrayList<>();
        String sql = "SELECT musica FROM historico WHERE usuario = ?";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, u.getUsuario());
        
        ResultSet resultado = statement.executeQuery();
        ResultSetMetaData metadata = resultado.getMetaData();
        int Cont = metadata.getColumnCount();
        
        while (resultado.next()) {
            StringBuilder linha = new StringBuilder();
            for (int i = 1; i <= Cont; i++) {
                String valor = resultado.getString(i); 
                linha.append(valor);
            }
        historico.add(linha.toString());
        }
        return historico;
    }
    
    public ArrayList<String>verLikes(Usuario u) throws SQLException{
        ArrayList<String> likes = new ArrayList<>();
        String sql = "SELECT musica FROM user_musica where usuario = ? AND "
                + "up = 'sim'";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, u.getUsuario());
        
        ResultSet resultado = statement.executeQuery();
        ResultSetMetaData metadata = resultado.getMetaData();
        int Cont = metadata.getColumnCount();
        
        while (resultado.next()) {
            StringBuilder linha = new StringBuilder();
            for (int i = 1; i <= Cont; i++) {
                String valor = resultado.getString(i); 
                linha.append(valor);
            }
        likes.add(linha.toString());
        }
        return likes;
    }
    
    public ArrayList<String>verDisikes(Usuario u) throws SQLException{
        ArrayList<String> dislikes = new ArrayList<>();
        String sql = "SELECT musica FROM user_musica where usuario = ? AND "
                + "down = 'sim'";
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, u.getUsuario());
        
        ResultSet resultado = statement.executeQuery();
        ResultSetMetaData metadata = resultado.getMetaData();
        int Cont = metadata.getColumnCount();
        
        while (resultado.next()) {
            StringBuilder linha = new StringBuilder();
            for (int i = 1; i <= Cont; i++) {
                String valor = resultado.getString(i); 
                linha.append(valor);
            }
        dislikes.add(linha.toString());
        }
        return dislikes;
    }
}
