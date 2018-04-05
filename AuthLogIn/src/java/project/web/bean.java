package project.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.util.*;
import java.sql.PreparedStatement;
 
@ManagedBean(name="bean")
@RequestScoped
public class bean {
    private String username;
    private String password;
    private String databaseUsername;
    private String databasePassword;
    Connection connect;
    PreparedStatement prepareStatement;
    Statement preStatement;
    ResultSet resultSet;
    String SQL_Str;
    int i=0;
    private String databaseLevel;
 
    public void databaseData(String User)
    {
        
        SQL_Str="select * from USER_DETAIL where USERNAME = ?";
        
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connect = DriverManager.getConnection("jdbc:derby://localhost:1527/policeDatabase","root","root");
            /* Vulnerable Code to SQLi
            SQL_Str="Select * from USER_DETAIL where USERNAME ='" + User +"'";
            preStatement = connect.createStatement();
            resultSet=preStatement.executeQuery(SQL_Str);
            resultSet.next();
            databaseUsername=resultSet.getString(1).toString();
            databasePassword=resultSet.getString(2).toString();
            databaseLevel=resultSet.getString(3).toString();*/
            
            //Fixed SQLi Vuln
            prepareStatement = connect.prepareStatement(SQL_Str);
            prepareStatement.setString(1, User);
            resultSet = prepareStatement.executeQuery();
            resultSet.next();
            databaseUsername=resultSet.getString(1);
            databasePassword=resultSet.getString(2);
            databaseLevel=resultSet.getString(3);
            resultSet.close();
            connect.close();
            
            
            
            
        }
        
        
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception" + ex);
        } 
    
        
    }
    public void databaseDataInsertAdmin()
    {
        //Fixed Inputted scripts into username, causing script execution on the index pages. Without this it allows a user to input a script.
        if(username.contains("<script>") || (username.contains("</script>"))){
            System.out.println("False");
        }else{
        SQL_Str="INSERT INTO USER_DETAIL(USERNAME, PASSWORD, user_level) VALUES(?,?,?)";
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connect = DriverManager.getConnection("jdbc:derby://localhost:1527/policeDatabase","root","root");
        
            /*SQL_Str="INSERT INTO USER_DETAIL (USERNAME, PASSWORD) VALUES (?,?);";
            preStatement = connect.prepareStatement(SQL_Str);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            i = prepareStatement.executeUpdate();*/
            
            
            
            prepareStatement = connect.prepareStatement(SQL_Str);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            prepareStatement.setString(3, "admin");
            prepareStatement.executeUpdate();
            System.out.println("Success");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception" + ex);
        }
        }
        }
    
    public void databaseDataInsertUser()
    {
        //Fixed Inputted scripts into username, causing script execution on the index pages. Without this it allows a user to input a script and/or html code.
        if(username.contains("<script>") || (username.contains("</script>") || (username.contains("<") || (username.contains(">"))))){
            System.out.println("False");
        }else{
        SQL_Str="INSERT INTO USER_DETAIL(USERNAME, PASSWORD, user_level) VALUES(?,?,?)";
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connect = DriverManager.getConnection("jdbc:derby://localhost:1527/policeDatabase","root","root");
            
            /*preStatement = connect.createStatement();
            SQL_Str="INSERT INTO USER_DETAIL(USERNAME, PASSWORD, user_level) VALUES('" + username +"','" + password +"','user')";
            preStatement.executeUpdate(SQL_Str);
            System.out.println("Success");*/
            
            prepareStatement = connect.prepareStatement(SQL_Str);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            prepareStatement.setString(3, "user");
            prepareStatement.executeUpdate();
            System.out.println("Success");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception" + ex);
        }
        }
        }
    
    
    public void databaseDataRemove()
    {
        //Fixed Inputted scripts into username, causing script execution on the index pages. Without this it allows a user to input a script.
       if(username.contains("<script>") || (username.contains("</script>"))){
            System.out.println("False");
        }else{
        SQL_Str="DELETE FROM USER_DETAIL where USERNAME = ?";
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connect = DriverManager.getConnection("jdbc:derby://localhost:1527/policeDatabase","root","root");
        
            /*SQL_Str="INSERT INTO USER_DETAIL (USERNAME, PASSWORD) VALUES (?,?);";
            preStatement = connect.prepareStatement(SQL_Str);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            i = prepareStatement.executeUpdate();*/
            
            /*preStatement = connect.createStatement();
            SQL_Str="DELETE FROM USER_DETAIL WHERE USERNAME =('" + username +"')";
            
            preStatement.executeUpdate(SQL_Str);
            System.out.println("Success");*/
            
               
            prepareStatement = connect.prepareStatement(SQL_Str);
            prepareStatement.setString(1, username);
            prepareStatement.executeUpdate();
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception" + ex);
        }
       }
        }
    
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
    public String getdatabaseLevel() {
        return databaseLevel;
    }
 
    public void setdatabaseLevel(String databaseLevel) {
        this.databaseLevel = databaseLevel;
    }
    public String checkForValidUser()
    {
        databaseData(username);
 
        if(databaseLevel.equals("user"))
        {
 
            if(password.equals(databasePassword))
                return "1";
            
            else
            {
                return "0";
            }
        }
        else if(databaseLevel.equals("admin"))
        {
 
            if(password.equals(databasePassword))
                return "2";
            
            else
            {
                return "0";
            }
        }
        else
        {
            return "0";
        }
    }
    
    
}