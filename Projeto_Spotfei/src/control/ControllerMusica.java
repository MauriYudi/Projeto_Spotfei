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
/**
 *
 * @author unifmkuniyoshi
 */
public class ControllerMusica {
    private Buscar view;
    
    public ControllerMusica(Buscar view){
        this.view = view;
    }
    
    public void buscarMusica(){
//        Musica music = new Musica(
//            view.getTxt_buscarM().getText(),
//            view.getTxt_buscarG().getText(),
//            new Artista(view.getTxt_buscarA().getText()));
        Conexao conexao = new Conexao();
        try {
        Connection conn = conexao.getConnection();
        DAO dao = new DAO(conn);

        ArrayList<String> resultados = dao.musicaToString(view.getTxt_buscarA());

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nenhum Resultado Encontrado!",
                "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String linha : resultados) {
            model.addElement(linha);
            //model.addElement(" ");
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
}
