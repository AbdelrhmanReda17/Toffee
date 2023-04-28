package DataUserClasses;

public class User{
    private String name,phone,password,address ;

    // Constructor
    // public User(String name  , String password , String address , String phone) {
    //     this.name = name;
    //     this.password = password;
    //     this.phone = phone;
    //     this.address = address;
    // }
    
    // Getters and setters
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}