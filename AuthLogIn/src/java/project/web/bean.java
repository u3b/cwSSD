/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.web;

import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @author cmpkmcdo
 */
@Named(value = "bean")
public class bean implements Serializable {

    private String name;
    private String pass;
    public boolean isLogged;
    
    
    /**
     * Creates a new instance of bean
     */
    public bean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String action() {
        isLogged = true;
        return "forum.xhtml?faces-redirect=true";
    }
}
