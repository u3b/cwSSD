package uk.ac.ljmu.cs.dvdWebApp;

public class DVD {
    // Property fields
    private String title;
    private String rating;
    User loaner;

    // Constructor
    public DVD() {
        title = "Unkown Title";
        rating = "Unknown Rating";
    }

   public String getTitle() {
        return title;
    }

   public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public User getUser() {
        return loaner;
    }

    public void setUser(User user) {
        this.loaner = user;
    }

    // Methods
    public String isAvailable() {
        String available;
        if (this.getUser() == null) {
            available = "Available";
        } else {
            available = "Borrowed by " + this.getUser().getName();
        }
        return available;
    }
    
    
}
