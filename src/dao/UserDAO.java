package dao;

import abstracts.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
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

    public List<User> loadAllUsers(){
        File file = new File(FileName);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream display = new ObjectInputStream(new FileInputStream(FileName))){
            List<User> loaded = (List<User>) display.readObject();
            System.out.println("Loaded " + loaded.size() + "users from file.");
            return loaded;
        }catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
    public User findUserByUsername(String username){
        for (User user : loadAllUsers()) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }
}

