/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import model.UsuarioLogin;
import DAO.DAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import model.Usuario;
import model.Playlist;
import view.Playlists_Criar;
import view.Playlists;
import view.Playlists_Editar;
import view.Playlists_Deletar;
import view.Playlists_Ver;
/**
 *
 * @author unifmkuniyoshi
 */
public class ControllerPlaylists {
    private Playlists_Criar view;
    private Playlists view1;
    private Playlists_Editar view2;
    private Playlists_Deletar view3;
    private Playlists_Ver view4;
    
    Usuario u = UsuarioLogin.getUl().getUser();

    public ControllerPlaylists(Playlists_Criar view, Playlists view1, 
            Playlists_Editar view2, Playlists_Deletar view3,
            Playlists_Ver view4) {
        this.view = view;
        this.view1 = view1;
        this.view2 = view2;
        this.view3 = view3;
        this.view4 = view4;
    }
    
    public void salvarPlaylist(){
        String nome = view.getTxt_playlist_nome().getText();
        Usuario user = new Usuario(null, u.getUsuario(), u.getSenha());
        Playlist play = new Playlist(nome, user, null);
        
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            dao.inserirPlaylist(play);
            JOptionPane.showMessageDialog(view, "Playlist Criada!","Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro ao criar a playlist!!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
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
    
    public void excluirPlaylist(JList<String> l){
        Usuario usuario = new Usuario(null, u.getUsuario(), null);
        Playlist playlist = new Playlist(l.getSelectedValue(), usuario, null);
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            dao.deletarPlaylist(playlist, usuario);
            JOptionPane.showMessageDialog(view, "Playlist excluída!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Erro ao acessar o banco de dados!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int opcoes(){
        Object[] options = { "Adicionar Músicas", "Remover Músicas" };
        int escolha = JOptionPane.showOptionDialog(view2, 
                "Escolha o que deseja.", "Aviso!",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[0]);
        
        return escolha;
    }
    
    public int mostarMusicasPlaylist(JList<String> l){
        int vazia = 0;
        Conexao conexao = new Conexao();
        
        Usuario usuario = new Usuario(null, u.getUsuario(), null);
                
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
        
            ArrayList<String> resultados = dao.musicasPlaylist(
                view4.getLista_playlists(),
                usuario);
        
            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(view, 
                        "A Playlist está vazia!",
                        "", JOptionPane.INFORMATION_MESSAGE);
                vazia = 1;
                return vazia;
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
        return vazia;
    }
}
