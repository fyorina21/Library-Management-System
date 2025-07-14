package model;

import abstracts.User;
import exception.BookNotFoundException;

import java.util.ArrayList;

// all fields should be private with getters and setters
// should extend user and implement both interfaces
public class Member extends User {
    private int maxBooks;
    ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Member(String userId, String name, String email, String role,String password) {
        super(userId, name, email, role, password);
    }
    public ArrayList<Member> members = new ArrayList<>();

    public ArrayList<Member> getMembers(){
        return members;
    }
    public void setMembers(ArrayList<Member> members){
        this.members = members;
    }

    public ArrayList<Book> gerBorrowedBooks(){
        return borrowedBooks;
    }
    public void setBorrowedBooks(ArrayList<Book> borrowedBooks){
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public void displayInfo() {
        System.out.println("User info: " + super.toString());
        System.out.println("Books Borrowed: " + borrowedBooks);
    }

    @Override
    public String toString(){
        return super.toString() +
                ",Max Books: " + maxBooks +
                ", Currently Borrowed:" + borrowedBooks.size();
    }
    @Override
    public String getUserId() {
        return super.getUserId();
    }

    public void borrowBook(Book b)throws BookNotFoundException {
        if(b ==null || !(borrowedBooks.contains(b))){
            throw new BookNotFoundException("null or Book not found");
        }else if(b.isAvailable()){
            borrowedBooks.add(b);
            b.setIsAvailable(false);
            System.out.println("you have successfully borrowed:" + b);
        }else{
            System.out.println("Book not available");
        }
    }
    public void returnBook(Book b) throws BookNotFoundException {
        if (b == null || borrowedBooks.contains(b)) {
            throw new BookNotFoundException("invalid");
        } else if (!(b.isAvailable())) {
            borrowedBooks.remove(b);
            b.setIsAvailable(true);
            System.out.println("you have successfully returned:" + b);
        } else {
            System.out.println("Try again");
        }
    }


        public void getBorrowedBookCount() {
            int count = borrowedBooks.size();
            System.out.println("Number of borrowed books: " + count);
        }
}
