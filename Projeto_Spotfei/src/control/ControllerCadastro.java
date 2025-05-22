/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import DAO.DAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import view.Cadastro;
/**
 *
 * @author unifmkuniyoshi
 */
public class ControllerCadastro {
    private Cadastro view;
    
    public ControllerCadastro(Cadastro view){
        this.view = view;
    }
    
    public int salvarUser(){
        String nome = view.getTxt_nome_cadastro().getText();
        String usuario = view.getTxt_user_cadastro().getText();
        String senha = view.getTxt_senha_cadastro().getText();
        
        int erro = 0;
        
        if(nome.trim().isEmpty() || usuario.trim().isEmpty()
                || senha.trim().isEmpty()){
            JOptionPane.showMessageDialog(view, "Todos os campos precisam estar"
                    + " preenchidos!","Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
            erro = 1;
            return erro;
        }
        
        else{
            Usuario user = new Usuario(nome, usuario,senha);

            Conexao conexao = new Conexao();
            try {
                Connection conn = conexao.getConnection();
                DAO dao = new DAO(conn);
                dao.inserir(user);
                JOptionPane.showMessageDialog(view, "Usuario Cadastrado!",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Usuário não cadastrado!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return erro;
    }
}
