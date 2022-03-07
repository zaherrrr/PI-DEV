package edu.esprit.entities;

public class Users {
    private int id;
    private String name;
    private String afterName;
    private String email;
    private String password;
    private String role;
    private String profilePicture;



    public Users() {
    }

    public Users(int id, String name) {
        this.id = id;
        this.name = name;
    }
    

    public Users(String name, String afterName, String email, String password, String role, String profilePicture) {
        this.name = name;
        this.afterName = afterName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profilePicture = profilePicture;
    }

    public Users(int id, String name, String afterName, String email, String password, String role, String profilePicture) {
        this.id = id;
        this.name = name;
        this.afterName = afterName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profilePicture = profilePicture;
    }

    public Users(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAfterName() {
        return afterName;
    }

    public void setAfterName(String afterName) {
        this.afterName = afterName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", afterName='" + afterName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
