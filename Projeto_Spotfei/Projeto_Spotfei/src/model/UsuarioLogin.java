/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author unifmkuniyoshi
 */
public class UsuarioLogin {
    private static UsuarioLogin ul;
    private Usuario user;
    
    private UsuarioLogin(){}
    
    public static UsuarioLogin getUl(){
        if(ul == null)
            ul = new UsuarioLogin();
        return ul;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
