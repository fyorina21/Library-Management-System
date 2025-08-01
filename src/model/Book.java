package model;
// all fields should be private with getters and setters
public class Book  {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;
    private String genre;
    private int publishedYear;

    public Book(String id,String title, String author,boolean isAvailable, String genre, int publishedYear){
        this.id = id;
        this.title = title;
        this.author =author;
        this.genre =genre;
        this.publishedYear = publishedYear;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
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
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public int getPublishedYear(){
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear){
        this.publishedYear = publishedYear;
    }

}
