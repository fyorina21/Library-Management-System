package abstracts;

public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String role;
    protected String password;

    public User (String userId,String name, String email,String role, String password){
        this.userId = userId;
        this.name = name;
        this.email= email;
        this.role = role;
        this.password= password;
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
    public String getEmail(){
        return name;
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
    public void setPassword(){
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


