# Library-Management-System
Object-Oriented Programming final group project.
The Library Management System is a console-based Java application developed to manage library operations such as book registration, borrowing, and returning. It was designed and implemented using Object-Oriented Programming (OOP) principles to ensure modularity, scalability, and maintainability.

##  OOP Principles Applied

| Concept         | Example(s) Implemented                                              |
|----------------|----------------------------------------------------------------------|
| **Encapsulation** | All class fields are private with proper getters/setters         |
| **Abstraction**   | Abstract class `User`, interface `Displayable`                   |
| **Inheritance**   | `Member` and `Librarian` extend `User`                           |
| **Polymorphism**  | Overloaded methods in DAO, overridden methods in model classes   |


## 📁 Project Structure

```

├── abstracts/ # Abstract base classes
│ └── User.java
│
├── model/ # Domain classes
│ ├── Book.java
│ ├── Member.java
│ └── Librarian.java
│
├── interfaces/ # Interfaces for behavior contracts
│ └── Displayable.java
│
├── dao/ # DAO layer for DB operations
│ ├── BookDAO.java
│ ├── BorrowDAO.java
│ └── UserDAO.java
│
├── services/ # Core business logic
│ └── LibraryService.java
│ └── reportService.java
│
├── util/ # Utility classes
│ ├── DBUtil.java
│
├── ui/ # User interface (CLI)
│ ├── Auth.java
│ └── MainMenu.java
│ └── membermenu.java
│└── libmenu
├── Exception
│ ├── BookNotFoundException
│ ├── LibraryException
│ └── UserNotFoundException
```
This system allows users to:
- Register as a **Member** or **Librarian**
- Log in with credentials
- **Borrow** and **return** books
- **Add**, **view**, **update**, or **delete** books (Librarians only)

⚙️ Setup Instructions
1.Clone the repository
```bash
  Git clone https://github.com/fyorina21/Library-Management-System.git
```
2.Run the Main.java file found in src/library/Ui/Main.java

👥 Team Members
1.Fyori Negasi
2.Hildana Amare
3.Hikmet Abdu
4.Rahel Agonafr




