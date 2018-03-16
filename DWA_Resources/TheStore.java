/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.ljmu.cs.dvdWebApp;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author cmpnshon
 */
@Named(value = "theStoreBean")
@SessionScoped

public class TheStore implements Serializable {
    private String tempTitle;
    private String tempRating;
    private String tempName;
    private String tempLoanTitle;
    private String tempLoanUser;
    private int tempMax;
    private final ArrayList<DVD> dvds = new ArrayList<>();
    private final ArrayList<User> users = new ArrayList<>();
    
    
    //Methods    
    public void addDvd() throws IOException {
        DVD dvd = new DVD();
        dvd.setTitle(getTempTitle());
        dvd.setRating(getTempRating());
        this.dvds.add(dvd);
        FacesContext.getCurrentInstance().getExternalContext().redirect("listDVDs.xhtml");
    }

   public void addUser() throws IOException {
        User user = new User();
        user.setName(getTempName());
        user.setMaxDvds(getTempMax());
        this.users.add(user);
        tempName = "";
        tempMax = 5;
        FacesContext.getCurrentInstance().getExternalContext().redirect("listUsers.xhtml");
    }
    
    public void loanDVD() throws IOException {
        
        User loanUser = new User();
        loanUser.setName(getTempLoanUser());
        
        //Loop through DVD listarray and set loaner
        for (DVD aDvd : this.getDvds()) {
           if(aDvd.getTitle().equalsIgnoreCase(tempLoanTitle)){
                aDvd.setUser(loanUser);
           }
	}
    }
    
    public boolean validateUser(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        String uname = ((String) value).toUpperCase();
        if(uname.length() > 50){
            return false;
        }
        return true;
    }
    
    public boolean validateMax(){
        if(getTempMax() > dvds.size()-1){
                return false;
        }
        return true;
    }
        
    public ArrayList<User> getUsers(){
	return users;
    }
    
    public ArrayList<DVD> getDvds(){
	return dvds;
    }
    
    //Getters & Setters
    public String getTempTitle() {
        return tempTitle;
    }

    public void setTempTitle(String tempTitle) {
        this.tempTitle = tempTitle;
    }

    public String getTempRating() {
        return tempRating;
    }

    public void setTempRating(String tempRating) {
        this.tempRating = tempRating;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getTempLoanTitle() {
        return tempLoanTitle;
    }

    public void setTempLoanTitle(String tempLoanTitle) {
        this.tempLoanTitle = tempLoanTitle;
    }

    public String getTempLoanUser() {
        return tempLoanUser;
    }

    public void setTempLoanUser(String tempLoanUser) {
        this.tempLoanUser = tempLoanUser;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }
}
