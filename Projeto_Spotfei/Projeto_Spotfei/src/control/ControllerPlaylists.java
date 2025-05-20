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
import javax.swing.JOptionPane;
import model.Usuario;
import model.Playlist;
import view.Playlists_Criar;
/**
 *
 * @author unifmkuniyoshi
 */
public class ControllerPlaylists {
    private Playlists_Criar view;
    
    Usuario u = UsuarioLogin.getUl().getUser();

    public ControllerPlaylists(Playlists_Criar view) {
        this.view = view;
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
}
