package model;
// all fields should be private with getters and setters
public class Book  {
    private int id;
    private String title;
    private String author;
    private String category;
    private int yearPublished;
    private boolean isAvailable;
    private String pdfPath;

    public Book(String title, String author, String category, int yearPublished,boolean isAvailable, String pdfPath){
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
        this.isAvailable = true;
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
        this.yearPublished = yearPublished;
    }

    public String getPdfPath() {
    return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }


    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author + " (" + yearPublished + ") - " + (isAvailable ? "Available" : "Borrowed") + "\nPath: " + pdfPath ;
    }
}