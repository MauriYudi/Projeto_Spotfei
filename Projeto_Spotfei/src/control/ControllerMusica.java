/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.util.ArrayList;
import model.UsuarioLogin;
import DAO.DAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import model.Musica;
import model.Usuario;
import model.Playlist;
import model.Artista;
import view.Buscar;
import view.Musica_Info;
import view.Playlist_Info;
/**
 *
 * @author unifmkuniyoshi
 */
public class ControllerMusica {
    private Buscar view;
    private Musica_Info view1;
    private Playlist_Info view3;
    
    public ControllerMusica(Buscar view, Musica_Info view1, 
            Playlist_Info view3){
        this.view = view;
        this.view1 = view1;
        this.view3 = view3;
    }
    
    Usuario u = UsuarioLogin.getUl().getUser();
    
    public void buscarMusica(){
        Conexao conexao = new Conexao();
        try {
        Connection conn = conexao.getConnection();
        DAO dao = new DAO(conn);

        ArrayList<String> resultados = dao.listaMusicas(view.getTxt_buscarA());

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nenhum Resultado Encontrado!",
                "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String linha : resultados) {
            model.addElement(linha);
        }

        view.getLista_musicas().setModel(model);

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(view, "Erro ao acessar o banco de dados!",
                "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void apagaTexto(JTextField t, String s){
        if (t.getText().equalsIgnoreCase(s))
            t.setText("");
    }
    
    public void geraTexto(JTextField t, String s){
        if (t.getText().equalsIgnoreCase(""))
            t.setText(s);
    }
    
    public void exibeMusica(JLabel l1, JLabel l2, JLabel l3){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);

            ArrayList<String> music = dao.nomeMusica(view.getLista_musicas());

            if (music.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Erro1",
                    "", JOptionPane.INFORMATION_MESSAGE);
            }
            
            Musica m = new Musica(music.get(0), music.get(1),
                    new Artista(music.get(2)));
            
            l1.setText(m.getMusicaNome());
            l2.setText(m.getGenero());
            l3.setText(m.getAutor().getArtistaNome());
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro2",
                "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarPlaylists(JList l){
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
        
            ArrayList<String> resultados = dao.listaPlaylists(u);
        
            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(view, 
                        "Nenhuma Playlist Encontrada!",
                        "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String linha : resultados) {
                model.addElement(linha);
            }
        
            l.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Erro ao acessar o banco de dados!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void salvarMusica(Musica_Info mi){
        Musica musica = new Musica(mi.getLbl_musica_nome().getText(),
                null, null);
        Playlist playlist = new Playlist(
                mi.getLista_playlists().getSelectedValue(),
                null, null);
        Usuario usuario = new Usuario(null, u.getUsuario(), null);
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            dao.inserirMusica(musica, playlist, usuario);
            JOptionPane.showMessageDialog(view1, 
                    "Música adicionada à playlist!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro ao adicionar música!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void excluirMusica(JList<String> l){
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            dao.deletarMusica(l);
            JOptionPane.showMessageDialog(view3, "Música Removida!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Erro ao acessar o banco de dados!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void curtir(JLabel l){
        Musica musica = new Musica(l.getText(), null, null);
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            dao.like(u, musica);
            JOptionPane.showMessageDialog(view1, "Música Curtida!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Erro ao acessar o banco de dados!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void descurtir(JLabel l){
        Musica musica = new Musica(l.getText(), null, null);
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            dao.dislike(u, musica);
            JOptionPane.showMessageDialog(view1, "Música Descurtida!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Erro ao acessar o banco de dados!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void adicionarHistorico(){
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            dao.inserirHistorico(view.getLista_musicas(), u);
        } catch (SQLException ex) {}
    }
}
