package uk.ac.ljmu.cs.dvdWebApp;


public class User {
    
    String name;
    int maxDvds;

    // Constructor
    public User() {
        name = "Unknown Name";
        maxDvds = 5;
    }

    // Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxDvds() {
        return maxDvds;
    }

    public void setMaxDvds(int maxDvds) {
        this.maxDvds = maxDvds;
    }
}
