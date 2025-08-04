package model;

import abstracts.User;
import dao.BookDAO;
import dao.BorrowDAO;


public class Member extends User {
    // private int maxBooks;

    public Member(String name, String username, String email, String password) {
        super(name, username, email, "member", password);
    }

    public Member() {
        super();
    }


    public boolean borrowBook(int bookId) {
        BookDAO bookDAO = new BookDAO();
        BorrowDAO borrowDAO = new BorrowDAO();

        Book book = bookDAO.findBookById(bookId);
        if (book != null && book.isAvailable()) {
            borrowDAO.borrowBook(this.getId(), bookId);
            book.setIsAvailable(false);
            bookDAO.updateBook(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(int bookId) {
        BookDAO bookDAO = new BookDAO();
        BorrowDAO borrowDAO = new BorrowDAO();

        Book book = bookDAO.findBookById(bookId);
        if (book != null) {
            borrowDAO.returnBook(this.getId(), bookId);
            book.setIsAvailable(true);
            bookDAO.updateBook(book);
            return true;
        }

        return false;
    }


    @Override
    public void displayInfo() {
        System.out.println("User info: " + super.toString());
    }

    @Override
    public int getId() {
        return super.getId();
    }


    public void getBorrowedBookCount() {
        BorrowDAO borrowDAO = new BorrowDAO();
        int count = borrowDAO.getBorrowedBooks(this.getId()).size();
        System.out.println("Number of borrowed books: " + count);
    }
}
