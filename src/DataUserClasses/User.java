package DataUserClasses;

public class User{
    private String name,password , email;

    /**
     * Constructs a user object with the specified name, password and email.
     * @param name the name of the user.
     * @param password the password of the user.
     * @param email the email of the user.
     */
    public User(String name  , String password , String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    /**
     * Constructs an empty user object.
     */
    public User() {}
    /**
     * Returns the name of the user.
     * @return the name of the user.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the user.
     * @param name the new name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the user.
     * @param password the new password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Returns the email of the user.
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email of the user.
     * @param email the new email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

}