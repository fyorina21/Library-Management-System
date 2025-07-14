    package model;

    import abstracts.User;
    import exception.BookNotFoundException;
    import exception.UserNotFoundException;

    import java.util.ArrayList;

    public class Librarian extends User {
        // ✅ class-level fields (private with encapsulation)
        private ArrayList<Book> availableBooks = new ArrayList<>();
        private ArrayList<Member> members = new ArrayList<>();

        public Librarian(String userId, String name, String email, String role) {
            super(userId, name, email, role);
        }

        @Override
        public void displayInfo() {
            System.out.println("Librarian info: " + super.toString());
        }

        // ✅ Add book method
        public void addBook(Book b) throws BookNotFoundException {
            if (b == null || availableBooks.contains(b)) {
                throw new BookNotFoundException("Nothing to add or book already exists!");
            }
            availableBooks.add(b);
        }

        // ✅ Remove book method (fixed logic)
        public void removeBook(Book b) throws BookNotFoundException {
            if (b == null || !availableBooks.contains(b)) {
                throw new BookNotFoundException("Nothing to remove or book not found!");
            }
            availableBooks.remove(b);
        }

        // ✅ Register member method
        public void registerMember(Member m) throws UserNotFoundException {
            if (m == null || members.contains(m)) {
                throw new UserNotFoundException("Invalid member or already registered.");
            }
            members.add(m);
        }

        // ✅ Getter for members list
        public ArrayList<Member> getMembers() {
            return members;
        }

        // ✅ Display all books
        public void viewAllBooks() {
            System.out.println("Books in store:");
            for (Book b : availableBooks) {
                System.out.println("- " + b.getTitle());
            }
        }
    }
