package model;

import interfaces.Borrowable; // Import your interface

// all fields should be private with getters and setters
public class Book implements Borrowable {
    private int id;
    private String title;
    private String author;
    private String category;
    private int yearPublished;
    private boolean isAvailable;
    private String pdfPath;

    public Book(String title, String author, String category, int yearPublished, boolean isAvailable, String pdfPath) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.yearPublished = yearPublished;
        this.isAvailable = isAvailable;
        this.pdfPath = pdfPath;
    }

    public Book() {}

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title= title;
    }

    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public boolean isAvailable(){
        return isAvailable;
    }
    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public int getYearPublished(){
        return yearPublished;
    }
    public void setYearPublished(int yearPublished){
        if(yearPublished > 0) {
            this.yearPublished = yearPublished;
        } else {
            throw new IllegalArgumentException("YEAR MUST BE POSITIVE.");
        }
    }

    public String getPdfPath() {
        return pdfPath;
    }
    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author + " (" + yearPublished + ") - " +
               (isAvailable ? "Available" : "Borrowed") + "\nPath: " + pdfPath ;
    }

    // -----------------------------
    // Borrowable interface methods
    // -----------------------------

    @Override
    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return !isAvailable;
    }
}
