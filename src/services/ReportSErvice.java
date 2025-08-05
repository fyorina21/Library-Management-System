// package services;

// import dao.BookDAO;
// import dao.BorrowDAO;
// import dao.UserDAO;
// import model.Book;
// import model.Member;
// import abstracts.User;
// import exception.LibraryException;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// public class ReportService {

//     private final BookDAO bookDAO;
//     private final BorrowDAO borrowDAO;
//     private final UserDAO userDAO;

//     public ReportService(BookDAO bookDAO, BorrowDAO borrowDAO, UserDAO userDAO) {
//         this.bookDAO = bookDAO;
//         this.borrowDAO = borrowDAO;
//         this.userDAO = userDAO;
//     }

//     /**
//      * Generates a report of borrowed books grouped by member.
//      */
//     public void printBorrowedBooksReport() throws LibraryException {
//         try {
//             List<User> users = userDAO.loadAllUsers();
//             boolean foundBorrowed = false;

//             for (User user : users) {
//                 if (user instanceof Member member) {
//                     List<Book> borrowedBooks = borrowDAO.getBorrowedBooks(member.getId());
//                     if (!borrowedBooks.isEmpty()) {
//                         foundBorrowed = true;
//                         System.out.println("\nMember: " + member.getFullName());
//                         for (Book book : borrowedBooks) {
//                             System.out.println("  - " + book.getTitle());
//                         }
//                     }
//                 }
//             }

//             if (!foundBorrowed) {
//                 System.out.println("\nNo borrowed books found.");
//             }
//         } catch (Exception e) {
//             throw new LibraryException("Failed to generate borrowed books report: " + e.getMessage(), e);
//         }
//     }

//     /**
//      * Generates a library summary report.
//      */
//     public void printLibrarySummary() throws LibraryException {
//         try {
//             List<Book> allBooks = bookDAO.getAllBooks();
//             List<User> allUsers = userDAO.loadAllUsers();

//             int totalBooks = allBooks.size();
//             int borrowedBooks = (int) allBooks.stream().filter(b -> !b.isAvailable()).count();
//             int availableBooks = totalBooks - borrowedBooks;
//             int totalMembers = (int) allUsers.stream().filter(u -> u instanceof Member).count();

//             System.out.println("\n=== Library Summary ===");
//             System.out.println("Total Books     : " + totalBooks);
//             System.out.println("Borrowed Books  : " + borrowedBooks);
//             System.out.println("Available Books : " + availableBooks);
//             System.out.println("Total Members   : " + totalMembers);
//         } catch (Exception e) {
//             throw new LibraryException("Failed to generate library summary: " + e.getMessage(), e);
//         }
//     }

//     /**
//      * Placeholder for overdue books.
//      */
//     public void printOverdueBooksReport() {
//         System.out.println("\nOverdue books tracking not yet implemented.");
//     }
// }

