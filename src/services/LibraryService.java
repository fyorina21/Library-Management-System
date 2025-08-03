package services;

import dao.BookDAO;
import dao.BorrowDAO;
import dao.UserDAO;
import model.Book;
import model.Librarian;
import model.Member;

import java.util.List;

import abstracts.User;
import exception.LibraryException;

public class LibraryService {
   
    private final BorrowDAO borrowDAO;
    private final BookDAO bookDAO;
    private final UserDAO userDAO;


 public LibraryService(BorrowDAO borrowDAO, BookDAO bookDAO, UserDAO userDAO) {
        this.borrowDAO = borrowDAO;
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
    }

 public void addNewBook(Book book) throws LibraryException {
        try {
            if (book == null) {
                throw new LibraryException("Book cannot be null");
            }
            if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
                throw new LibraryException("Book title is required");
            }
            bookDAO.saveBook(book);
        } catch (Exception e) {
            throw new LibraryException("Failed to add book: " + e.getMessage(), e);
        }
    }

    public Book findBookById(int bookId) throws LibraryException {
        try {
            Book book = bookDAO.findBookById(bookId);
            if (book == null) {
                throw new LibraryException("Book not found");
            }
            return book;
        } catch (Exception e) {
            throw new LibraryException("Failed to find book: " + e.getMessage(), e);
        }
    }

    public List<Book> getAllBooks() throws LibraryException {
        try {
            return bookDAO.getAllBooks();
        } catch (Exception e) {
            throw new LibraryException("Failed to retrieve books: " + e.getMessage(), e);
        }
    }

     public void registerMember(Member member) throws LibraryException {
        try {
            if (member == null) {
                throw new LibraryException("Member cannot be null");
            }
            if (userDAO.usernameExists(member.getUsername())) {
                throw new LibraryException("Username already exists");
            }
            userDAO.saveUser(member);
        } catch (Exception e) {
            throw new LibraryException("Registration failed: " + e.getMessage(), e);
        }
    }

    public void registerLibrarian(Librarian librarian) throws LibraryException {
        try {
            if (librarian == null) {
                throw new LibraryException("Librarian cannot be null");
            }
            if (userDAO.usernameExists(librarian.getUsername())) {
                throw new LibraryException("Username already exists");
            }
            userDAO.saveUser(librarian);
        } catch (Exception e) {
            throw new LibraryException("Registration failed: " + e.getMessage(), e);
        }
    }

    public User authenticateUser(String username, String password) throws LibraryException {
        try {
            User user = userDAO.findUserByUsername(username);
            if (user == null) {
                throw new LibraryException("User not found");
            }
            if (!user.getPassword().equals(password)) {
                throw new LibraryException("Invalid password");
            }
            return user;
        } catch (Exception e) {
            throw new LibraryException("Authentication failed: " + e.getMessage(), e);
        }
    }

    public void borrowBook(int userId, int bookId) throws LibraryException {
        try {
            // 1. Verify user exists and can borrow
            User user = userDAO.loadAllUsers().stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
            if (user == null) {
                throw new LibraryException("User not found");
            }
            if (!(user instanceof Member)) {
                throw new LibraryException("Only members can borrow books");
            }

            // 2. Verify book exists and is available
            Book book = bookDAO.findBookById(bookId);
            if (book == null) {
                throw new LibraryException("Book not found");
            }
            if (!book.isAvailable()) {
                throw new LibraryException("Book is already borrowed");
            }

            // 3. Check borrowing limits (assuming max 5 books)
            List<Book> borrowedBooks = borrowDAO.getBorrowedBooks(userId);
            if (borrowedBooks.size() >= 5) {
                throw new LibraryException("Maximum borrowing limit (5 books) reached");
            }

            // 4. Execute borrow operation
            borrowDAO.borrowBook(userId, bookId);
            
            // 5. Update book availability
            book.setIsAvailable(false);
            bookDAO.updateBook(book);

        } catch (LibraryException e) {
            throw e; // Re-throw our custom exceptions
        } catch (Exception e) {
            throw new LibraryException("Borrowing failed: " + e.getMessage(), e);
        }
    }

    public void returnBook(int userId, int bookId) throws LibraryException {
        try {
            // 1. Verify the book is actually borrowed by this user
            boolean hasBook = borrowDAO.getBorrowedBooks(userId).stream()
                                    .anyMatch(b -> b.getId() == bookId);
            if (!hasBook) {
                throw new LibraryException("This user hasn't borrowed the specified book");
            }

            // 2. Execute return operation
            borrowDAO.returnBook(userId, bookId);
            
            // 3. Update book availability
            Book book = bookDAO.findBookById(bookId);
            book.setIsAvailable(true);
            bookDAO.updateBook(book);

        } catch (LibraryException e) {
            throw e;
        } catch (Exception e) {
            throw new LibraryException("Return failed: " + e.getMessage(), e);
        }
    }

    public List<Book> getBorrowedBooks(int userId) throws LibraryException {
        try {
            boolean userExists = userDAO.loadAllUsers().stream().anyMatch(u -> u.getId() == userId);
            if (!userExists) {
                throw new LibraryException("User not found");
            }
            return borrowDAO.getBorrowedBooks(userId);
        } catch (Exception e) {
            throw new LibraryException("Failed to get borrowed books: " + e.getMessage(), e);
        }
    }
 public List<Member> getAllMembers() throws LibraryException {
        try {
            return userDAO.loadAllUsers().stream()
                         .filter(u -> u instanceof Member)
                         .map(u -> (Member) u)
                         .toList();
        } catch (Exception e) {
            throw new LibraryException("Failed to retrieve members: " + e.getMessage(), e);
        }
    }

    public List<Book> getOverdueBooks() throws LibraryException {
        // Implementation would need a due_date column in borrowed_books table
        throw new UnsupportedOperationException("Overdue tracking not implemented yet");
    }
}