package dao;

import abstracts.User;
import model.Librarian;
import model.Member;
import utill.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void saveUser(User user){
        String sql = "INSERT INTO users (name, username, email, role, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public List<User> loadAllUsers(){
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String role = rs.getString("role").toLowerCase();
                User user;

                if (role.equals("librarian")) {
                    user = new Librarian(
                            rs.getString("name"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                } else {
                    user = new Member(
                            rs.getString("name"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }

                user.setId(rs.getInt("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }

        return users;
    }


    public User findUserByUsername(String username){
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role").toLowerCase();
                User user;

                if (role.equals("librarian")) {
                    user = new Librarian(
                            rs.getString("name"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                } else {
                    user = new Member(
                            rs.getString("name"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }

                user.setId(rs.getInt("id")); // critical for borrow/return tracking
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Error finding user: " + e.getMessage());
        }

        return null;
    }

    public boolean usernameExists(String username) {
        return findUserByUsername(username) != null;
    }
    
    public void viewAllMembers() {
        String sql = "SELECT * FROM users WHERE role = 'member'";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== All Members ===");
            while (rs.next()) {
                System.out.println("[" + rs.getInt("id") + "] " +
                        rs.getString("name") + " - " +
                        rs.getString("username") + " - " +
                        rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving members: " + e.getMessage());
        }
    }
}

