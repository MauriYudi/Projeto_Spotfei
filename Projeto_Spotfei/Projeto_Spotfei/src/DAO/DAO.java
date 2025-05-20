/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
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
                      + play.getPlayNome() + "', '"
                      + play.getUser().getUsuario()   + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
    
    public ArrayList<String> musicaToString(Musica musica) throws SQLException {
    String sql = "SELECT nome FROM musica WHERE nome = ? OR genero = ? "
            + "OR artista = ?";
    ArrayList<String> musicas = new ArrayList<>();

    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setString(1, musica.getMusicaNome());
        statement.setString(2, musica.getGenero());
        statement.setString(3, musica.getAutor().getArtistaNome());

        try (ResultSet resultado = statement.executeQuery()) {
            ResultSetMetaData metaData = resultado.getMetaData();
            int Cont = metaData.getColumnCount();

            while (resultado.next()) {
                StringBuilder linha = new StringBuilder();
                for (int i = 1; i <= Cont; i++) {
                    String valor = resultado.getString(i); 
                    linha.append(valor);
                    if (i < Cont) {
                        linha.append(", ");
                        }
                    }
                    musicas.add(linha.toString());
                }
            }
        }

        return musicas;
    }
}
