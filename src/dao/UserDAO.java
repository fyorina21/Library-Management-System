package dao;

import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String FileName = "users.dat";


    public void saveUser(User user){
        List<User> users = loadAllUsers();
        users.add(user);
        try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(FileName))) {
            save.writeObject(users);
        } catch (IOException e){
            System.out.println("Error: " +e.getMessage());
        }
    }

    public List<User> loadAllusers(){
        File file = new File(FileName);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream display = new ObjectInputStream(new FileInputStream(FileName)));{  
            return (List<User>) display.readObject();
        }catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
    public User findUserbyUsername(String username){
        for (User user : loadAllUsers()) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }
}
