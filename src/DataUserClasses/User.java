package DataUserClasses;

public class User{
    private String name,password;

     //Constructor
     public User(String name  , String password) {
         this.name = name;
         this.password = password;

     }
    
    // Getters and setters
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}