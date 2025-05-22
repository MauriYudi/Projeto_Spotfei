/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import model.UsuarioLogin;
import DAO.DAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.Login;
import view.Pagina_Usuario;
/**
 *
 * @author unifmkuniyoshi
 */
public class ControllerLogin {
    public Login view;

    public ControllerLogin(Login view) {
        this.view = view;
    }
    
    public void loginUser(){
        Usuario user = new Usuario(null, 
            view.getTxt_user_login().getText(),
            view.getTxt_senha_login().getText());
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            ResultSet res = dao.consultar(user);
            if(res.next()){
                UsuarioLogin.getUl().setUser(user);
                JOptionPane.showMessageDialog(view, 
                    "Login efetuado!", "Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
                Pagina_Usuario p = new Pagina_Usuario();
                p.getLbl_user().setText(view.getTxt_user_login().getText());
                p.setVisible(true);
                view.setVisible(false);
            } else{
                JOptionPane.showMessageDialog(view, 
                    "Login NÃO efetuado!", "Aviso",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException e){    
            JOptionPane.showMessageDialog(view, 
                "Erro de conexão!", "Aviso",
                JOptionPane.ERROR_MESSAGE);
        }

    }

}
