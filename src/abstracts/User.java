package abstracts;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String userId;
    protected String name;
    protected String username;
    protected String email;
    protected String role;
    protected String password;

    public User (String name, String username,String email, String role, String password){
        this.userId = generateUserId();
        this.name = name;
        this.username = username;
        this.email= email;
        this.role = role;
        this.password= password;
    }

    public String generateUserId() {
        return java.util.UUID.randomUUID().toString();
    }

    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId= userId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name ;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public abstract void displayInfo();

    @Override
    public String toString() {
        return "User{" +
                "User ID: '" + userId + '\'' +
                ", Name: '" + name + '\'' +
                ", Email: '" + email + '\'' +
                ", Role: '" + role + '\'' +
                '}';
    }


}


