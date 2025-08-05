package model;

import abstracts.User;

public class Librarian extends User {

    public Librarian(String name, String username, String email, String password) {
        super(name, username, email, "librarian", password);
    }

    @Override
    public void displayInfo() {
        System.out.println("Librarian info: " + getName() + " | Username: " + getUsername() + " | Email: " + getEmail());
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "Name='" + getName() + '\'' +
                ", Username='" + getUsername() + '\'' +
                ", Email='" + getEmail() + '\'' +
                '}';
    }


    //functions for adding, viewing and removing books are handled in BookDAO

}
