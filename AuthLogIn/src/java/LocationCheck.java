import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="LocationCheck")
@RequestScoped
public class LocationCheck {
    
    private String local;
    private String locationDB;
    private String updates;
    private String information;
    Connection connect;
    PreparedStatement prepareStatement;
    ResultSet resultSet;
    String SQL_Str;
    

    public void LocationCheck(String Local) {
        
      
        
        SQL_Str="select * from LocationDB where LOCATION = ?";
        
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connect = DriverManager.getConnection("jdbc:derby://localhost:1527/policeDatabase","root","root");
            prepareStatement = connect.prepareStatement(SQL_Str);
            prepareStatement.setString(1, Local);
            resultSet = prepareStatement.executeQuery();
            resultSet.next();
            locationDB=resultSet.getString(1);
            updates=resultSet.getString(2);
            information=resultSet.getString(3);
            resultSet.close();
            connect.close();

        }

        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception" + ex);
        } 
    
        
    }
       public void databaseDataInsertLocation()
    {
        
        SQL_Str="INSERT INTO LOCATIONDB(LOCATION, STATUS, INFOMATION) VALUES(?,?,?)";
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connect = DriverManager.getConnection("jdbc:derby://localhost:1527/policeDatabase","root","root");
            prepareStatement = connect.prepareStatement(SQL_Str);
            prepareStatement.setString(1, local);
            prepareStatement.setString(2, updates);
            prepareStatement.setString(3, information);
            prepareStatement.executeUpdate();
            System.out.println("Success");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception" + ex);
        }
        
        }
    public String checkForLocation()
            
            
    {
        
        LocationCheck(local);
        if(locationDB.contains(local)){
        return "true";}
        else {
            return "false";
        }
        
    }
    
    public String getLocal() {
        return local;
    }
 
    public void setLocal(String local) {
        this.local = local;
    }
    
     public String getUpdates() {
        return updates;
    }
 
    public void setUpdates(String updates) {
        this.updates = updates;
    }
    
     public String getInformation() {
        return information;
    }
 
    public void setInformation(String information) {
        this.information = information;
    }
    
}
    
    
    
    

