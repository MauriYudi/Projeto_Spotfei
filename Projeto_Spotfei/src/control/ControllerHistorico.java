/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import DAO.Conexao;
import DAO.DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioLogin;
import view.Pagina_Usuario;
/**
 *
 * @author Yudi
 */
public class ControllerHistorico {
    private Pagina_Usuario view;
    
    public ControllerHistorico(Pagina_Usuario view){
        this.view = view;
    }
    
    Usuario u = UsuarioLogin.getUl().getUser();
    
    public void exibirHistorico(JList l){
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            
            ArrayList<String> resultados = dao.listaHistorico(u);
            
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String linha : resultados) {
                model.addElement(linha);
            }
            
            l.setModel(model);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Erro ao acessar o banco de dados!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void exibirLikes(JList l){
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            
            ArrayList<String> resultados = dao.verLikes(u);
            
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String linha : resultados) {
                model.addElement(linha);
            }
            
            l.setModel(model);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Erro ao acessar o banco de dados!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void exibirDislikes(JList l){
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            DAO dao = new DAO(conn);
            
            ArrayList<String> resultados = dao.verDisikes(u);
            
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String linha : resultados) {
                model.addElement(linha);
            }
            
            l.setModel(model);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                    "Erro ao acessar o banco de dados!",
                    "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
